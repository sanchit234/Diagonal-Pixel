package com.diagonalpixel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Splashscreen extends Activity {
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
        
        Thread splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    while(true && (waited < 3000)) {
                        sleep(100);
                        if(true) {
                            waited += 100;
                        }
                    }
                } catch(InterruptedException e) {
                    // do nothing
                } finally {
                    finish();
                    finish();  
                    Intent i = new Intent();  
                    i.setClassName("com.diagonalpixel", "com.diagonalpixel.Gallery");  
                    startActivity(i); 
                }
                }
        };
        splashTread.start();
    }
}