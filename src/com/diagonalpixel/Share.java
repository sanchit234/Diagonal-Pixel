package com.diagonalpixel;

import java.io.File;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Share extends Gallery {
	/** Called when the activity is first created. */
	   @Override
	   public void onCreate(Bundle savedInstanceState) {
	       super.onCreate(savedInstanceState);

	       setContentView(R.layout.share);
	       ImageView iv = (ImageView)findViewById(R.id.imageViewshare);
	       iv.setImageURI(uri);

	       ImageButton share = (ImageButton)findViewById(R.id.imageButtonshare);
	       share.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				android.content.Intent intent = new android.content.Intent(android.content.Intent.ACTION_SEND);
				intent.setType("image/jpeg"); 
				intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Sent via 'Diagonal Pixel'");
				intent.putExtra(android.content.Intent.EXTRA_STREAM, uri);
				startActivity(android.content.Intent.createChooser(intent, "Share Using"));
			}
		});
	}
}
