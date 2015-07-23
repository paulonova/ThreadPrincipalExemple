package br.exemplothreadprincipal;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private Handler handler = new Handler();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void loadImage(View view){
		new Thread(){
			public void run(){
				try {
                    //http://vigesimalsoft.com/wp-content/uploads/2012/12/Android-icon.png
                    // http://www.thiengo.com.br/img/system/logo/thiengo-80-80.png
					
					URL url = new URL("http://vigesimalsoft.com/wp-content/uploads/2012/12/Android-icon.png");
					HttpURLConnection connection;
					connection = (HttpURLConnection) url.openConnection();
					connection.setDoInput(true);
			        connection.connect();
			        InputStream input = connection.getInputStream();
			        final Bitmap image = BitmapFactory.decodeStream(input);
			        
			        Log.i("Image", "load image ..");
			        
			        final ImageView iv = (ImageView) findViewById(R.id.image1);
			        
			        handler.post(new Runnable(){
			        	public void run(){
			        		iv.setImageBitmap(image);
			        	}
			        });
				}
				catch(MalformedURLException e) {
					e.printStackTrace();
				}
				catch(IOException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

}
