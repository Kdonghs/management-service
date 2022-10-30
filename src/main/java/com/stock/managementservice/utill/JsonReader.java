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

    final String korToken = "AI4sP7yimuGTh5b6bfWubhdv7Roc8UF4Uu2h52P5Khbkij2tMlroPBR1Di%2BcgmEZn0M9QLD3aOltxhUrCt2aWQ%3D%3D";

    public JSONObject stockObjectRead(String code){
        try {
            BufferedReader bf;
            URL request = new URL("http://apis.data.go.kr/1160100/service/GetStockSecuritiesInfoService/getStockPriceInfo?" +
                    "numOfRows=1&pageNo=1&resultType=json&"
                    +code+"&serviceKey="+korToken);
            bf = new BufferedReader(new InputStreamReader(request.openStream(), "UTF-8"));
            String  result = bf.readLine();

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject)jsonParser.parse(result);
            JSONObject a = (JSONObject)jsonObject.get("response");
            JSONObject b = (JSONObject)a.get("body");
            JSONObject c = (JSONObject)b.get("items");
            JSONArray Arr = (JSONArray)c.get("item");
            System.out.println((JSONObject) Arr.get(0));


            return (JSONObject) Arr.get(0);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return new JSONObject();
    }
    public JSONArray stockArrayRead(String code){
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
    }
}
