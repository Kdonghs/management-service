package com.stock.managementservice.utill;

import net.minidev.json.JSONObject;
import net.minidev.json.JSONArray;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class JsonReader {

    final String korToken = new serviceKey().korServiceKey;
    final String usToken = new serviceKey().usServiceKey;

    public JSONObject korStockObjectRead(String ticker,int count, int no){
        try {
            BufferedReader bf;
            URL request = new URL("http://apis.data.go.kr/1160100/service/GetStockSecuritiesInfoService/getStockPriceInfo?" +
                    "numOfRows="+count+"&pageNo="+no+"&resultType=json&likeItmsNm="
                    +ticker+"&serviceKey="+korToken);
            bf = new BufferedReader(new InputStreamReader(request.openStream(), "UTF-8"));
            String result = bf.readLine();

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject)jsonParser.parse(result);
            JSONObject a = (JSONObject)jsonObject.get("response");
            JSONObject b = (JSONObject)a.get("body");
            JSONObject c = (JSONObject)b.get("items");
            JSONArray Arr = (JSONArray)c.get("item");
            if (Arr.isEmpty()){
                return new JSONObject();
            }
            System.out.println(Arr);

            return (JSONObject) Arr.get(0);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return new JSONObject();
    }

    public JSONObject usStockObjectRead(String code){
        try {
            BufferedReader bf;
            URL request = new URL("https://api.nasdaq.com/api/autocomplete/slookup/5?search="+code + "&key=" + usToken);
            bf = new BufferedReader(new InputStreamReader(request.openStream()));
            String result = bf.readLine();
            System.out.println(request);
            System.out.println(result);

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject)jsonParser.parse(result);
            JSONArray a = (JSONArray) jsonObject.get("data");
            System.out.println(a);

            return (JSONObject)a.get(0);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return new JSONObject();
    }
    /*public JSONArray stockArrayRead(String code){
        try {
            BufferedReader bf;
            URL request = new URL("https://cloud.iexapis.com/stable/"+code+"?token="+korToken);
            bf = new BufferedReader(new InputStreamReader(request.openStream(), "UTF-8"));
            String  result = bf.readLine();

            JSONParser jsonParser = new JSONParser();
            JSONArray array = (JSONArray) jsonParser.parse(result);

            return array;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return new JSONArray();
    }*/
}
