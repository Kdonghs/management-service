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
    final String IEXtoken = "pk_64cdce253bee495fa66866eecf1928f6";

    public JSONObject stockObjectRead(String code){
        try {
            BufferedReader bf;
            URL request = new URL("https://cloud.iexapis.com/stable/"+code+"?token="+IEXtoken);
            bf = new BufferedReader(new InputStreamReader(request.openStream(), "UTF-8"));
            String  result = bf.readLine();

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject)jsonParser.parse(result);
            System.out.println(jsonObject);

            return jsonObject;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return new JSONObject();
    }
    public JSONArray stockArrayRead(String code){
        try {
            BufferedReader bf;
            URL request = new URL("https://cloud.iexapis.com/stable/"+code+"?token="+IEXtoken);
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
