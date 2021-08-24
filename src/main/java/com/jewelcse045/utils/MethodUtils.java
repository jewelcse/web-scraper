package com.jewelcse045.utils;


import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.http.HttpStatus;


public class MethodUtils {

    MethodUtils(){

    }
    public static String prepareErrorJSON(HttpStatus status, Exception ex) {
        JSONObject errorJSON=new JSONObject();
        try {
            errorJSON.put("success",false);
            errorJSON.put("message",ex.getMessage());
            errorJSON.put("status_code",status.value());
        } catch (JSONException e) {

            e.printStackTrace();
        }

        return errorJSON.toString();

    }

}
