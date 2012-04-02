package com.kevin.translator5;


import java.io.BufferedReader; 
import java.io.IOException;
import java.io.InputStreamReader; 
import java.net.HttpURLConnection; 
import java.net.URL; 
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject; 
import android.util.Log;

public class Translate {
	private static final String URL = "https://www.googleapis.com/language/translate/v2?key=AIzaSyDIoMMdU1TM_8pCtVK--TLjF08Nb2rWOao";
	private static final String INPUT = "&q=";
	private static final String TO = "&target=";
    private static final String FROM = "&source=";
    
    public static String translate(String text, String from, String to) throws Exception
    {
    	try {
    		String translated = null;
    		BufferedReader input = null;
    		StringBuilder response  = new StringBuilder();
            StringBuilder fullURL = new StringBuilder();
            fullURL.append(URL).append(FROM).append(from).append(TO).append(to).append(INPUT).append(text);
            URL url = new URL(fullURL.toString());
            
            HttpURLConnection httpconn = (HttpURLConnection)url.openConnection();
            Log.i("Hello","I am here");
            int responseCode = httpconn.getResponseCode();
            System.out.println("Respose code:" + responseCode);
          /* if (httpconn.getResponseCode() != 100)
            {
            	Log.i("response", "NOT WORKING");
            	throw new IOException(httpconn.getResponseMessage());
            	
            }
           Log.i("Translate",httpconn.getResponseMessage());*/
            Log.i("Inside Translate class", "1");
            try {
            	Log.i("Inside Translate class", "1A");
            	try {
                input = new BufferedReader(new InputStreamReader(httpconn.getInputStream(),"UTF-8"));
            	Log.i("Inside Translate class", "1AA");
                String strLine = null;
                Log.i("Inside Translate class", "1AAA");
                while ((strLine = input.readLine()) != null)
                   {
                	  Log.i("Inside Translate class", "1AAAA");
                      response.append(strLine);
                   }
            	} finally {
            		input.close();
            	}
            	
            	
                  Log.i("Inside Translate class", "2");
                 String responseText = response.toString();
                  try {
          			JSONObject data = new JSONObject(responseText).getJSONObject("data");
          			JSONArray translations = data.getJSONArray("translations");
          			JSONObject textJson = translations.getJSONObject(0);
          			translated = textJson.getString("translatedText");
          		} catch (JSONException e) {
          			// TODO Auto-generated catch block
          			e.printStackTrace();
          			Log.i("Inside Translate class", "3hello");
          		}
                  
                  translated = "Dia guit";
                  return translated.toString();
    	        } finally {
    		         httpconn.getInputStream().close();
    	        } // end of finally
        } catch (Exception ex) {
        	throw ex;
        } // end of catch
    	
    	
    	
    	
  } // end of method translate
} // end of class Translate
