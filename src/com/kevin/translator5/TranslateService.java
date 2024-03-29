package com.kevin.translator5;


import android.app.Service; 
import android.content.Intent; 
import android.os.IBinder; 
import android.util.Log;

public class TranslateService extends Service {
	   public static final String TAG = "TranslateService";
	   private final ITranslate.Stub mBinder = new ITranslate.Stub() { 
	        public String translate(String text, String from, String to) { 
	            try { 
	            	Log.i("TAG", "Before Service Call");
	                return Translate.translate(text, from, to); 
	            } catch (Exception e) { 
	                Log.e(TAG, "Failed to translate: " + e.getMessage()); 
	                return null; 
	            } 
	        } 
	    };
	
	@Override
	public IBinder onBind(Intent intent) {
		return mBinder;
	}

}
