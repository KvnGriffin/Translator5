package com.kevin.translator5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class Translator5Activity extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
	 static final String TAG = "Translator";
	 private EditText MyInputText = null;
	 private Button MyTranslateButton = null;
	 private TextView MyOutputText = null;
     private RadioButton toIrishButton, toEnglishButton;
     private static final String URL = "https://www.googleapis.com/language/translate/v2?key=AIzaSyDIoMMdU1TM_8pCtVK--TLjF08Nb2rWOao";
 	private static final String INPUT = "&q=";
 	private static final String TO = "&target=";
     private static final String FROM = "&source=";
  
   
    @Override
    public void onCreate(Bundle icicle) {
    	
        super.onCreate(icicle);
        setContentView(R.layout.main);
        MyInputText = (EditText) findViewById(R.id.inputText); 
        MyOutputText = (EditText) findViewById(R.id.translation);
        MyTranslateButton = (Button) findViewById(R.id.button1); 
        MyTranslateButton.setOnClickListener(this);
        toIrishButton = (RadioButton) findViewById(R.id.radio0); 
        toEnglishButton = (RadioButton) findViewById(R.id.radio1); 
       
      
    }
    
    private class TranslateAsync extends AsyncTask<String, Void, String> {
    	
    	protected String doInBackground(String... url) {
    		String translated = null;
    		try {
    			 
        		  BufferedReader input = null;
        		  StringBuilder response  = new StringBuilder();
                  URL urlToSend = new URL(url[0]);
                  HttpURLConnection httpconn = (HttpURLConnection)urlToSend.openConnection();
                  Log.i("Hello","I am here");
                  int responseCode = httpconn.getResponseCode();
                  System.out.println("Respose code:" + responseCode);
          
                   Log.i("Inside Translate class", "1");
                   try {
                	   Log.i("Inside Translate class", "1A");
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
                		    httpconn.getInputStream().close();
                	}
                	
                	
                      Log.i("Inside Translate class", "2");
                     String responseText = response.toString();
                      try {
              			   JSONObject data = new JSONObject(responseText).getJSONObject("data");
              			   JSONArray translations = data.getJSONArray("translations");
              			   JSONObject textJson = translations.getJSONObject(0);
              		       translated = textJson.getString("translatedText");
              		      } catch (JSONException e) {
              			   
              			       e.printStackTrace();
              			       Log.i("Inside Translate class", "3hello");
              		         } // end catch
                      
             } catch (Exception ex) {
       	          try {
			          throw ex;
	            	    } catch (Exception e) {
			               
			                e.printStackTrace();
		                   } 
               }
        return translated.toString();    
       
       } // end doInBackground
    	    
    		protected void onPostExecute(String result) {
    			MyOutputText.setText(result);
    		}
    	
    } // end static class TranslateAsync
    
	
    
    @Override
	public void onClick(View v) {
    	TranslateAsync task = new TranslateAsync();
    	String input = MyInputText.getText().toString();
    	   StringBuilder fullURL = new StringBuilder();
    	   
    	   if(toIrishButton.isChecked()) {
    		   fullURL.append(URL).append(FROM).append("en").append(TO).append("ga").append(INPUT).append(input);
    		   String uRL1 = fullURL.toString();
    		   task.execute(uRL1);
    	   } else if (toEnglishButton.isChecked()) {
    		   fullURL.append(URL).append(FROM).append("ga").append(TO).append("en").append(INPUT).append(input);
    		   String uRL2 = fullURL.toString();
           task.execute(uRL2);
    }
 
  }
	
} // end of class Translator5