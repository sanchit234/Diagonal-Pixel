package com.diagonalpixel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Gallery extends Activity {
    private static final int REQ_CODE_PICK_IMAGE = 0;
    String ImagePath;
	String selectedImagePath;
	private static final String TEMP_PHOTO_FILE = "temporary_holder.jpg";
	Bitmap bmp,bmp1,BMP,bmp2;
	public static final double PI = 3.14159d;
    public static final double RANGE = 256d;
    public static final double HALF_CIRCLE_DEGREE = 180d;
    private ProgressDialog progressDialog;
    public static int Tint=0;
    public static Uri uri; 
    /** Called when the activity is first created. */
    
    @Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		android.view.MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.drawable.menu, menu);
	    return true;
	}
    
    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		super.onOptionsItemSelected(item);
		switch(item.getItemId())
		{
			case R.id.how:
			howToUse();
			break;
		}
		return true;
	}
    
    private void howToUse() {
		Intent i = new Intent();
        i.setClassName("com.diagonalpixel","com.diagonalpixel.Use");
        startActivity(i);
		
	}
    public void save2()
    {
       	BitmapDrawable bmd = new BitmapDrawable(bmp2);
		final Bitmap bmpsave = ((BitmapDrawable)bmd).getBitmap();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		Date now = new Date();
		now.setTime(System.currentTimeMillis());
		saveimagename = sdf.format(now);

		
       	    		ContentValues v = new ContentValues();
       	    		v.put(Images.Media.TITLE, saveimagename);
       	    		v.put(Images.Media.DISPLAY_NAME, saveimagename);
       	    		v.put(Images.Media.MIME_TYPE, "image/png");
       	    		
       	    		String dirName = "/Diagonal Pixel Magic Effects/";
       	    		String root = Environment.getExternalStorageDirectory().toString();
       	    		new File(root + dirName).mkdirs();
       	    		File file = new File(root, dirName+"DP-"+saveimagename+".PNG");
       	    		uri = Uri.fromFile(file);
       	    		//uri = getContentResolver().insert(Images.Media.EXTERNAL_CONTENT_URI, v);
       	    		OutputStream outStream = null;
					try {
						outStream = getContentResolver().openOutputStream(uri);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

	   	   			bmpsave.compress(Bitmap.CompressFormat.PNG, 100, outStream);
	   	   			try {
						outStream.flush();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	   	   			try {
						outStream.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	   	   		sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://"+ Environment.getExternalStorageDirectory())));
	   	   			Toast.makeText(Gallery.this, "Image saved: " + uri.toString(),   Toast.LENGTH_LONG).show();
       	}
    
    
    public String saveimagename;
    public void save()
    {
    	bmp=bmp1;
    	final android.app.AlertDialog.Builder alert = new android.app.AlertDialog.Builder(this);

       	alert.setTitle("Save Image");
       	alert.setMessage("Enter Name With Which You Want To Save Image");
       	
       	// Set an EditText view to get user input 
       	final android.widget.EditText input = new android.widget.EditText(this);
       	alert.setView(input);
       	//if(bmp!=null)
       	ImageView yourimgView = (ImageView)findViewById(R.id.imageView1);
       	BitmapDrawable bmd = new BitmapDrawable(BMP);
		yourimgView.setImageDrawable(bmd);
		final Bitmap bmpsave = ((BitmapDrawable)bmd).getBitmap();
		
       	//v.setDrawingCacheEnabled(true);
       	//final Bitmap bmpsave = Bitmap.createBitmap(v.getDrawingCache());
       		
       	   	alert.setPositiveButton("Ok", new android.content.DialogInterface.OnClickListener() {
       	    	public void onClick(android.content.DialogInterface dialog, int whichButton) {
       	    	//	Toast.makeText(Gallery.this, "Image saved",Toast.LENGTH_LONG).show();
       	    		saveimagename = input.getText().toString();
       	    		ContentValues v = new ContentValues();
       	    		v.put(Images.Media.TITLE, saveimagename);
       	    		v.put(Images.Media.DISPLAY_NAME, saveimagename);
       	    		v.put(Images.Media.MIME_TYPE, "image/png");
       	    		
       	    		String dirName = "/Diagonal Pixel/";
       	    		String root = Environment.getExternalStorageDirectory().toString();
       	    		new File(root + dirName).mkdirs();
       	    		File file = new File(root, dirName+saveimagename+".PNG");
       	    		uri = Uri.fromFile(file);
       	    		//uri = getContentResolver().insert(Images.Media.EXTERNAL_CONTENT_URI, v);
       	    		OutputStream outStream = null;
					try {
						outStream = getContentResolver().openOutputStream(uri);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

	   	   			bmpsave.compress(Bitmap.CompressFormat.PNG, 100, outStream);
	   	   			try {
						outStream.flush();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	   	   			try {
						outStream.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	   	   		sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://"+ Environment.getExternalStorageDirectory())));
	   	   			Toast.makeText(Gallery.this, "Image saved: " + uri.toString(),   Toast.LENGTH_LONG).show();
	   	   			share();
       	    		
       	    	}
       	      });
       	    		
       	   	alert.setNegativeButton("Cancel", new android.content.DialogInterface.OnClickListener() {
       	    	  
       	   		public void onClick(android.content.DialogInterface dialog, int whichButton) {
       	    	    // Canceled.
       	    	  }
       	    	});

       	    	alert.show();

       	}
    
    
    public void share()
    {
    	Intent i = new Intent();
    	i.setClassName("com.diagonalpixel", "com.diagonalpixel.Share");  
        startActivity(i); 
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery);
        
        ImageButton save = (ImageButton)findViewById(R.id.ImageButton02);
        save.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				save(); 
			}
		});
        
        
        //A
        ActionItem rotateAction = new ActionItem();
        rotateAction.setTitle("Rotate");
        rotateAction.setIcon(getResources().getDrawable(R.drawable.ic_nothing));
        
        ActionItem flipAction = new ActionItem();
        flipAction.setTitle("Flip");
        flipAction.setIcon(getResources().getDrawable(R.drawable.ic_nothing));
        
        final QuickAction mQuickActionA  = new QuickAction(this);
        
		mQuickActionA.addActionItem(rotateAction);
		mQuickActionA.addActionItem(flipAction);
        
        //setup the action item click listener
        mQuickActionA.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {                 
                public void onItemClick(int pos) {
                		if (pos == 0) 
                        { //Rotate
                			
                			Toast.makeText(Gallery.this, "Click to rotate clockwise or counter-clockwise", Toast.LENGTH_SHORT).show();
                				ImageButton b1 = (ImageButton)findViewById(R.id.ImageButton03);                                
                				b1.setVisibility(View.INVISIBLE);
                				ImageButton b2 = (ImageButton)findViewById(R.id.ImageButton02);                                
                				b2.setVisibility(View.INVISIBLE);
                				final ImageView yourimgView = (ImageView)findViewById(R.id.imageView1);
                                
                                final ImageButton btnright = (ImageButton)findViewById(R.id.ImageButton05);
                                final ImageButton btnleft = (ImageButton)findViewById(R.id.ImageButton04);
                                final ImageButton btnhorizontal = (ImageButton)findViewById(R.id.ImageButton06);
                                final ImageButton btnvertical = (ImageButton)findViewById(R.id.ImageButton07);

                                btnhorizontal.setVisibility(View.GONE);
                                btnvertical.setVisibility(View.GONE);
                                btnright.setVisibility(View.VISIBLE);
                                btnright.setOnClickListener(new View.OnClickListener() {
									public void onClick(View v) {
										// TODO Auto-generated method stub
										Drawable draw=yourimgView.getDrawable();
                                		bmp1 = ((BitmapDrawable)draw).getBitmap();
                                		BMP = rotate(bmp1,-90);                                			
                                		BitmapDrawable bmd = new BitmapDrawable(BMP);
                                		yourimgView.setImageDrawable(bmd);
                                		bmp1 = ((BitmapDrawable)bmd).getBitmap();
									}
								});
                                btnleft.setVisibility(View.VISIBLE);
                                btnleft.setOnClickListener(new View.OnClickListener() {
									public void onClick(View v) {
										// TODO Auto-generated method stub
										Drawable draw=yourimgView.getDrawable();
                                		bmp1 = ((BitmapDrawable)draw).getBitmap();
                                		BMP = rotate(bmp1,90);                                			
                                		BitmapDrawable bmd = new BitmapDrawable(BMP);
                                		yourimgView.setImageDrawable(bmd);
                                		bmp1 = ((BitmapDrawable)bmd).getBitmap();
									}
								});
                             
                        } else if (pos == 1) { //Upload item selected
                        	Toast.makeText(Gallery.this, "Click to flip horizontal or vertical", Toast.LENGTH_SHORT).show();
                            
                        	ImageButton b1 = (ImageButton)findViewById(R.id.ImageButton03);                                
            				b1.setVisibility(View.INVISIBLE);
            				ImageButton b2 = (ImageButton)findViewById(R.id.ImageButton02);                                
            				b2.setVisibility(View.INVISIBLE);
                        	final ImageView yourimgView = (ImageView)findViewById(R.id.imageView1);
                            
                            final ImageButton btnright = (ImageButton)findViewById(R.id.ImageButton04);
                            final ImageButton btnleft = (ImageButton)findViewById(R.id.ImageButton05);
                            final ImageButton btnhorizontal = (ImageButton)findViewById(R.id.ImageButton06);
                            final ImageButton btnvertical = (ImageButton)findViewById(R.id.ImageButton07);

                            btnright.setVisibility(View.GONE);
                            btnleft.setVisibility(View.GONE);
                            
                            btnhorizontal.setVisibility(View.VISIBLE);
                            btnhorizontal.setOnClickListener(new View.OnClickListener() {
								public void onClick(View v) {
									// TODO Auto-generated method stub
									Drawable draw=yourimgView.getDrawable();
                            		bmp1 = ((BitmapDrawable)draw).getBitmap();
                            		BMP = flipHorizontal(bmp1);      			
                            		BitmapDrawable bmd = new BitmapDrawable(BMP);
                            		yourimgView.setImageDrawable(bmd);
                            		bmp1 = ((BitmapDrawable)bmd).getBitmap();
								}
							});
                            btnvertical.setVisibility(View.VISIBLE);
                            btnvertical.setOnClickListener(new View.OnClickListener() {
								public void onClick(View v) {
									// TODO Auto-generated method stub
									Drawable draw=yourimgView.getDrawable();
                            		bmp1 = ((BitmapDrawable)draw).getBitmap();
                            		BMP = flipVertical(bmp1);                                			
                            		BitmapDrawable bmd = new BitmapDrawable(BMP);
                            		yourimgView.setImageDrawable(bmd);
                            		bmp1 = ((BitmapDrawable)bmd).getBitmap();
								}
							});
                         
                    }       
                }
        });
        
        //B
        ActionItem brightness = new ActionItem();
        brightness.setTitle("Brightness");
        brightness.setIcon(getResources().getDrawable(R.drawable.ic_nothing));
        
        ActionItem contrast = new ActionItem();
        contrast.setTitle("Contrast");
        contrast.setIcon(getResources().getDrawable(R.drawable.ic_nothing));
        
        ActionItem tint = new ActionItem();
        tint.setTitle("Tint");
        tint.setIcon(getResources().getDrawable(R.drawable.ic_nothing));
        
        ActionItem bnw = new ActionItem();
        bnw.setTitle("Black & White");
        bnw.setIcon(getResources().getDrawable(R.drawable.ic_nothing));
        
        ActionItem invert = new ActionItem();
        invert.setTitle("Invert Color");
        invert.setIcon(getResources().getDrawable(R.drawable.ic_nothing));
        
        ActionItem colorfilter = new ActionItem();
        colorfilter.setTitle("Color Filter");
        colorfilter.setIcon(getResources().getDrawable(R.drawable.ic_nothing));
        
        ActionItem shadingfilter = new ActionItem();
        shadingfilter.setTitle("Shading Filter");
        shadingfilter.setIcon(getResources().getDrawable(R.drawable.ic_nothing));
        
        final QuickAction mQuickActionB  = new QuickAction(this);
        
		mQuickActionB.addActionItem(brightness);
		mQuickActionB.addActionItem(contrast);
		mQuickActionB.addActionItem(tint);
		mQuickActionB.addActionItem(bnw);
		mQuickActionB.addActionItem(invert);
		mQuickActionB.addActionItem(colorfilter);
		mQuickActionB.addActionItem(shadingfilter);
		
        //setup the action item click listener
        mQuickActionB.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {                 
                public void onItemClick(int pos) {
                		if (pos == 0) 
                        { //Brightness
                			final ImageView imgcontrols= (ImageView)findViewById(R.id.ImageView01);
                            imgcontrols.setVisibility(View.VISIBLE);
                				
                            ImageButton b1 = (ImageButton)findViewById(R.id.ImageButton03);                                
            				b1.setVisibility(View.INVISIBLE);
            				ImageButton b2 = (ImageButton)findViewById(R.id.ImageButton02);                                
            				b2.setVisibility(View.INVISIBLE);
                				final ImageView yourimgView = (ImageView)findViewById(R.id.imageView1);
                                Toast.makeText(Gallery.this, "Slide on image to adjust brightness", Toast.LENGTH_SHORT).show();
                                Drawable reset1=yourimgView.getDrawable();
                                final Bitmap reset=((BitmapDrawable)reset1).getBitmap();
                                imgcontrols.setOnTouchListener(new View.OnTouchListener() {
									public boolean onTouch(View v, MotionEvent event) {
										// TODO Auto-generated method stub
										bmp1 = reset;
										//Drawable draw=yourimgView.getDrawable();
		            	   				//bmp1 = ((BitmapDrawable)draw).getBitmap();
		            					float X=event.getX();
		            					float widthX=yourimgView.getWidth();
		            					BMP=doBrightness(bmp1,X-widthX/2);
		            					BitmapDrawable bmd = new BitmapDrawable(BMP);
		            					yourimgView.setImageDrawable(bmd);
		            					bmp1 = ((BitmapDrawable)bmd).getBitmap();
		            					return true;
									}
								});
                            } else if (pos == 1) { //Contrast
                            	final ImageView imgcontrols= (ImageView)findViewById(R.id.ImageView01);
                                imgcontrols.setVisibility(View.VISIBLE);
                            	
                                ImageButton b1 = (ImageButton)findViewById(R.id.ImageButton03);                                
                				b1.setVisibility(View.INVISIBLE);
                				ImageButton b2 = (ImageButton)findViewById(R.id.ImageButton02);                                
                				b2.setVisibility(View.INVISIBLE);
                            		final ImageView yourimgView = (ImageView)findViewById(R.id.imageView1);
	                            	Toast.makeText(Gallery.this, "Slide on image to adjust contrast", Toast.LENGTH_SHORT).show();
	                                Drawable reset1=yourimgView.getDrawable();
	                                final Bitmap reset=((BitmapDrawable)reset1).getBitmap();
	                                imgcontrols.setOnTouchListener(new View.OnTouchListener() {
										public boolean onTouch(View v, MotionEvent event) {
											// TODO Auto-generated method stub
											bmp1 = reset;
											//Drawable draw=yourimgView.getDrawable();
			            	   				//bmp1 = ((BitmapDrawable)draw).getBitmap();
			            	   			
			            					float X=event.getX();
			            					float widthX=yourimgView.getWidth();
			            					if(X-widthX/4 <= 0);
			            					else
			            						BMP=doContrast(bmp1,X - widthX/4);
			            					BitmapDrawable bmd = new BitmapDrawable(BMP);
			            					yourimgView.setImageDrawable(bmd);
			            					bmp1 = ((BitmapDrawable)bmd).getBitmap();
											return true;
										}
									});
	                           
                           }else if(pos == 2)
                           {
                        	   ImageButton b1 = (ImageButton)findViewById(R.id.ImageButton03);                                
               				b1.setVisibility(View.INVISIBLE);
               				ImageButton b2 = (ImageButton)findViewById(R.id.ImageButton02);                                
               				b2.setVisibility(View.INVISIBLE);
                        	   Toast.makeText(Gallery.this, "Click to increase or decrease tint", Toast.LENGTH_SHORT).show();
                        	   final ImageButton tintinc = (ImageButton)findViewById(R.id.ImageButton08);
                               final ImageButton tintdec = (ImageButton)findViewById(R.id.ImageButton09);
                               tintinc.setVisibility(View.VISIBLE);
                               tintdec.setVisibility(View.VISIBLE);
                               
                               final ImageView yourimgView = (ImageView)findViewById(R.id.imageView1);
                               Drawable draw=yourimgView.getDrawable();
                       	   	   bmp1 = ((BitmapDrawable)draw).getBitmap();
                   
                               tintinc.setOnClickListener(new View.OnClickListener() {
								
								public void onClick(View arg0) {
									progressDialog = ProgressDialog.show(Gallery.this, "", "Working...");
									Tint = Tint + 10;
									// TODO Auto-generated method stub
									   Thread t = new Thread() {
		                                   public void run() {
		                                	   BMP=tintImage(bmp1,Tint);
		                                	   mHandler.post(mUpdateResults2);
		                                   }
		                               };
		                               t.start();
		                          }	
							});
                               tintdec.setOnClickListener(new View.OnClickListener(){
                               public void onClick(View arg0) {
									// TODO Auto-generated method stub
                            	   progressDialog = ProgressDialog.show(Gallery.this, "", "Working...");
									Tint = Tint - 10;
									// TODO Auto-generated method stub
									   Thread t = new Thread() {
		                                   public void run() {
		                                	   BMP=tintImage(bmp1,Tint);
		                                	   mHandler.post(mUpdateResults2);
		                                   }
		                               };
		                               t.start();
		                          }	
							});
                        	   	    	   	
                        	   	BMP=tintImage(bmp1,10);
                        	   	BitmapDrawable bmd = new BitmapDrawable(BMP);
                        	   	yourimgView.setImageDrawable(bmd);
           						bmp1 = ((BitmapDrawable)bmd).getBitmap();
           				
                           }
                           else if (pos == 3) 
                           { //Black & white
                        	   ImageButton b1 = (ImageButton)findViewById(R.id.ImageButton03);                                
               				b1.setVisibility(View.INVISIBLE);
               				ImageButton b2 = (ImageButton)findViewById(R.id.ImageButton02);                                
               				b2.setVisibility(View.INVISIBLE);
                        	   progressDialog = ProgressDialog.show(Gallery.this, "", "Working...");
                        	   final ImageView yourimgView = (ImageView)findViewById(R.id.imageView1);
                        	   Drawable draw=yourimgView.getDrawable();
                        	   bmp1 = ((BitmapDrawable)draw).getBitmap();
                        	   Thread t = new Thread() {
                                   public void run() {
                                	   BMP=doGreyscale(bmp1);
                                	   mHandler.post(mUpdateResults);
                                   }
                               };
                               t.start();
                           }
                           else if (pos == 4) 
                           { //Invert colors
                        	   ImageButton b1 = (ImageButton)findViewById(R.id.ImageButton03);                                
               				b1.setVisibility(View.INVISIBLE);
               				ImageButton b2 = (ImageButton)findViewById(R.id.ImageButton02);                                
               				b2.setVisibility(View.INVISIBLE);
                        	   progressDialog = ProgressDialog.show(Gallery.this, "", "Working...");
                        	   final ImageView yourimgView = (ImageView)findViewById(R.id.imageView1);
                        	   Drawable draw=yourimgView.getDrawable();
                        	   bmp1 = ((BitmapDrawable)draw).getBitmap();
                        	   Thread t = new Thread() {
                                   public void run() {
                                	   BMP=doInvert(bmp1);
                                	   mHandler.post(mUpdateResults3);
                                   }
                               };
                               t.start();
                          }
                           else if(pos==5)
                           {
                        	   ImageButton b1 = (ImageButton)findViewById(R.id.ImageButton03);                                
               				b1.setVisibility(View.INVISIBLE);
               				ImageButton b2 = (ImageButton)findViewById(R.id.ImageButton02);                                
               				b2.setVisibility(View.INVISIBLE);
                        	   final ImageView yourimgView = (ImageView)findViewById(R.id.imageView1);
                        	   final LinearLayout lincolorfilter = (LinearLayout)findViewById(R.id.linearLayout2);
                               lincolorfilter.setVisibility(View.VISIBLE);
                               Drawable reset1=yourimgView.getDrawable();
                               final Bitmap reset=((BitmapDrawable)reset1).getBitmap();
                        	   	
                        	   Button red=(Button)findViewById(R.id.Button01);
                        	   Button green=(Button)findViewById(R.id.Button03);
                        	   Button blue=(Button)findViewById(R.id.Button04);
                        	   Button yellow=(Button)findViewById(R.id.Button02);
                        	   Button pink=(Button)findViewById(R.id.Button05);
                        	   Button cyan=(Button)findViewById(R.id.button1);
                        	   
                        	   red.setOnClickListener(new View.OnClickListener() {
								
								public void onClick(View arg0) {
									// TODO Auto-generated method stub
									bmp1=reset;
									progressDialog = ProgressDialog.show(Gallery.this, "", "Working...");
	                        	   	//Drawable draw=yourimgView.getDrawable();
	                       			//bmp1 = ((BitmapDrawable)draw).getBitmap();
	                        	   Thread t = new Thread() {
	                                   public void run() {
	                                	   BMP=doColorFilter(bmp1,1.0,0,0);
	                                	   mHandler.post(mUpdateResults4);
	                                   }
	                               };
	                               t.start();
								}
							});
                        	   green.setOnClickListener(new View.OnClickListener() {
   								
   								public void onClick(View arg0) {
   									// TODO Auto-generated method stub
   									bmp1=reset;
   									progressDialog = ProgressDialog.show(Gallery.this, "", "Working...");
   	                        	   	//Drawable draw=yourimgView.getDrawable();
   	                       			//bmp1 = ((BitmapDrawable)draw).getBitmap();
   	                        	   Thread t = new Thread() {
   	                                   public void run() {
   	                                	   BMP=doColorFilter(bmp1,0,1.0,0);
   	                                	   mHandler.post(mUpdateResults4);
   	                                   }
   	                               };
   	                               t.start();
   								}
   							});
                        	   blue.setOnClickListener(new View.OnClickListener() {
   								
   								public void onClick(View arg0) {
   									// TODO Auto-generated method stub
   									bmp1=reset;
   									progressDialog = ProgressDialog.show(Gallery.this, "", "Working...");
   	                        	   	//Drawable draw=yourimgView.getDrawable();
   	                       			//bmp1 = ((BitmapDrawable)draw).getBitmap();
   	                        	   Thread t = new Thread() {
   	                                   public void run() {
   	                                	   BMP=doColorFilter(bmp1,0,0,1.0);
   	                                	   mHandler.post(mUpdateResults4);
   	                                   }
   	                               };
   	                               t.start();
   								}
   							});
                        	   yellow.setOnClickListener(new View.OnClickListener() {
   								
   								public void onClick(View arg0) {
   									// TODO Auto-generated method stub
   									bmp1=reset;
   									progressDialog = ProgressDialog.show(Gallery.this, "", "Working...");
   	                        	   	//Drawable draw=yourimgView.getDrawable();
   	                       			//bmp1 = ((BitmapDrawable)draw).getBitmap();
   	                        	   Thread t = new Thread() {
   	                                   public void run() {
   	                                	   BMP=doColorFilter(bmp1,1.0,1.0,0);
   	                                	   mHandler.post(mUpdateResults4);
   	                                   }
   	                               };
   	                               t.start();
   								}
   							});
                        	   pink.setOnClickListener(new View.OnClickListener() {
   								
   								public void onClick(View arg0) {
   									// TODO Auto-generated method stub
   									bmp1=reset;
   									progressDialog = ProgressDialog.show(Gallery.this, "", "Working...");
   	                        	   	//Drawable draw=yourimgView.getDrawable();
   	                       			//bmp1 = ((BitmapDrawable)draw).getBitmap();
   	                        	   Thread t = new Thread() {
   	                                   public void run() {
   	                                	   BMP=doColorFilter(bmp1,1.0,0,1.0);
   	                                	   mHandler.post(mUpdateResults4);
   	                                   }
   	                               };
   	                               t.start();
   								}
   							});
                        	   cyan.setOnClickListener(new View.OnClickListener() {
   								
   								public void onClick(View arg0) {
   									// TODO Auto-generated method stub
   									bmp1=reset;
   									progressDialog = ProgressDialog.show(Gallery.this, "", "Working...");
   	                        	   	//Drawable draw=yourimgView.getDrawable();
   	                       			//bmp1 = ((BitmapDrawable)draw).getBitmap();
   	                        	   Thread t = new Thread() {
   	                                   public void run() {
   	                                	   BMP=doColorFilter(bmp1,0,1.0,1.0);
   	                                	   mHandler.post(mUpdateResults4);
   	                                   }
   	                               };
   	                               t.start();
   								}
   							});
                        	  
                           }
                           else if(pos==6)
                           {
                        	   ImageButton b1 = (ImageButton)findViewById(R.id.ImageButton03);                                
               				b1.setVisibility(View.INVISIBLE);
               				ImageButton b2 = (ImageButton)findViewById(R.id.ImageButton02);                                
               				b2.setVisibility(View.INVISIBLE);
                        	   final ImageView yourimgView = (ImageView)findViewById(R.id.imageView1);
                        	   final LinearLayout linshadingfilter = (LinearLayout)findViewById(R.id.LinearLayout01);
                               linshadingfilter.setVisibility(View.VISIBLE);
                               Drawable reset1=yourimgView.getDrawable();
                               final Bitmap reset=((BitmapDrawable)reset1).getBitmap();
                        	   	
                        	   Button a=(Button)findViewById(R.id.Button06);
                        	   Button b=(Button)findViewById(R.id.Button07);
                        	   Button c=(Button)findViewById(R.id.Button08);
                        	   Button d=(Button)findViewById(R.id.Button09);
                        	   
                        	   a.setOnClickListener(new View.OnClickListener() {
								
								public void onClick(View arg0) {
									// TODO Auto-generated method stub
									bmp1=reset;
									progressDialog = ProgressDialog.show(Gallery.this, "", "Working...");
	                        	   	//Drawable draw=yourimgView.getDrawable();
	                       			//bmp1 = ((BitmapDrawable)draw).getBitmap();
	                        	   Thread t = new Thread() {
	                                   public void run() {
	                                	   BMP=applyShadingFilter(bmp1,Color.rgb(0,191,255));
	                                	   mHandler.post(mUpdateResults4);
	                                   }
	                               };
	                               t.start();
								}
							});
                        	    b.setOnClickListener(new View.OnClickListener() {
   								
   								public void onClick(View arg0) {
   									// TODO Auto-generated method stub
   									bmp1=reset;
   									progressDialog = ProgressDialog.show(Gallery.this, "", "Working...");
   	                        	   	//Drawable draw=yourimgView.getDrawable();
   	                       			//bmp1 = ((BitmapDrawable)draw).getBitmap();
   	                        	   Thread t = new Thread() {
   	                                   public void run() {
   	                                	BMP=applyShadingFilter(bmp1,Color.rgb(124,252,0));
   	                                	   mHandler.post(mUpdateResults4);
   	                                   }
   	                               };
   	                               t.start();
   								}
   							});
                        	   c.setOnClickListener(new View.OnClickListener() {
   								
   								public void onClick(View arg0) {
   									// TODO Auto-generated method stub
   									bmp1=reset;
   									progressDialog = ProgressDialog.show(Gallery.this, "", "Working...");
   	                        	   	//Drawable draw=yourimgView.getDrawable();
   	                       			//bmp1 = ((BitmapDrawable)draw).getBitmap();
   	                        	   Thread t = new Thread() {
   	                                   public void run() {
   	                                	BMP=applyShadingFilter(bmp1,Color.rgb(199,21,133));
   	                                	   mHandler.post(mUpdateResults4);
   	                                   }
   	                               };
   	                               t.start();
   								}
   							});
                        	   d.setOnClickListener(new View.OnClickListener() {
   								
   								public void onClick(View arg0) {
   									// TODO Auto-generated method stub
   									bmp1=reset;
   									progressDialog = ProgressDialog.show(Gallery.this, "", "Working...");
   	                        	   	//Drawable draw=yourimgView.getDrawable();
   	                       			//bmp1 = ((BitmapDrawable)draw).getBitmap();
   	                        	   Thread t = new Thread() {
   	                                   public void run() {
   	                                	BMP=applyShadingFilter(bmp1,Color.rgb(255,127,80));
   	                                	   mHandler.post(mUpdateResults4);
   	                                   }
   	                               };
   	                               t.start();
   								}
   							});
                        	  
                           }   
            
                }
              	
        });
                
        
        //C
        ActionItem dark = new ActionItem();
        dark.setTitle("Dark Effect");
        dark.setIcon(getResources().getDrawable(R.drawable.ic_nothing));
        
        ActionItem blur = new ActionItem();
        blur.setTitle("Blur");
        blur.setIcon(getResources().getDrawable(R.drawable.ic_nothing));
        
        ActionItem sharpen = new ActionItem();
        sharpen.setTitle("Sharpen");
        sharpen.setIcon(getResources().getDrawable(R.drawable.ic_nothing));
          
        ActionItem mean = new ActionItem();
        mean.setTitle("Mean Removal");
        mean.setIcon(getResources().getDrawable(R.drawable.ic_nothing));
        
        ActionItem smooth = new ActionItem();
        smooth.setTitle("Smoothen");
        smooth.setIcon(getResources().getDrawable(R.drawable.ic_nothing));
          
        final QuickAction mQuickActionC  = new QuickAction(this);
        
		mQuickActionC.addActionItem(dark);
		mQuickActionC.addActionItem(blur);
		mQuickActionC.addActionItem(sharpen);
		mQuickActionC.addActionItem(mean);
		mQuickActionC.addActionItem(smooth);
		
		mQuickActionC.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {                 
            public void onItemClick(int pos) {
            		if(pos == 0) 
                    { 
            			ImageButton b1 = (ImageButton)findViewById(R.id.ImageButton03);                                
        				b1.setVisibility(View.INVISIBLE);
        				ImageButton b2 = (ImageButton)findViewById(R.id.ImageButton02);                                
        				b2.setVisibility(View.INVISIBLE);
            			// TODO Auto-generated method stub
            			ImageView iv= (ImageView)findViewById(R.id.imageView1);
            			Drawable draw=iv.getDrawable();
                		bmp1 = ((BitmapDrawable)draw).getBitmap();
            			BMP= applyDarkEffect(bmp1);
            			BitmapDrawable bmd = new BitmapDrawable(BMP);
                		iv.setImageDrawable(bmd);
                		bmp1 = ((BitmapDrawable)bmd).getBitmap();
            		}
            		else if(pos==1)
            		{
            			ImageButton b1 = (ImageButton)findViewById(R.id.ImageButton03);                                
        				b1.setVisibility(View.INVISIBLE);
        				ImageButton b2 = (ImageButton)findViewById(R.id.ImageButton02);                                
        				b2.setVisibility(View.INVISIBLE);
            			ImageView iv= (ImageView)findViewById(R.id.imageView1);
            			Drawable draw=iv.getDrawable();
                		bmp1 = ((BitmapDrawable)draw).getBitmap();
                		progressDialog = ProgressDialog.show(Gallery.this, "", "Working...");
                   	   	//Drawable draw=yourimgView.getDrawable();
                  			//bmp1 = ((BitmapDrawable)draw).getBitmap();
                   	   	Thread t = new Thread() {
                              public void run() {
                            	  BMP= applyGaussianBlur(bmp1);
                            	  mHandler.post(mUpdateResults5);
                              }
                          };
                          t.start();            
            		}
            		else if(pos==2){
            			ImageButton b1 = (ImageButton)findViewById(R.id.ImageButton03);                                
        				b1.setVisibility(View.INVISIBLE);
        				ImageButton b2 = (ImageButton)findViewById(R.id.ImageButton02);                                
        				b2.setVisibility(View.INVISIBLE);
            			ImageView iv= (ImageView)findViewById(R.id.imageView1);
            			Drawable draw=iv.getDrawable();
                		bmp1 = ((BitmapDrawable)draw).getBitmap();
                		progressDialog = ProgressDialog.show(Gallery.this, "", "Working...");
                   	   	//Drawable draw=yourimgView.getDrawable();
                  			//bmp1 = ((BitmapDrawable)draw).getBitmap();
                   	   	Thread t = new Thread() {
                              public void run() {
                            	  BMP= sharpen(bmp1,11);
                            	  mHandler.post(mUpdateResults5);
                              }
                          };
                          t.start();
            		}
            		else if(pos==3){
            			ImageButton b1 = (ImageButton)findViewById(R.id.ImageButton03);                                
        				b1.setVisibility(View.INVISIBLE);
        				ImageButton b2 = (ImageButton)findViewById(R.id.ImageButton02);                                
        				b2.setVisibility(View.INVISIBLE);
            			ImageView iv= (ImageView)findViewById(R.id.imageView1);
            			Drawable draw=iv.getDrawable();
                		bmp1 = ((BitmapDrawable)draw).getBitmap();
                		progressDialog = ProgressDialog.show(Gallery.this, "", "Working...");
                   	   	//Drawable draw=yourimgView.getDrawable();
                  			//bmp1 = ((BitmapDrawable)draw).getBitmap();
                   	   	Thread t = new Thread() {
                              public void run() {
                            	  BMP= applyMeanRemoval(bmp1);
                            	  mHandler.post(mUpdateResults5);
                              }
                          };
                          t.start();
            		}
            		else if(pos==4){
            			ImageButton b1 = (ImageButton)findViewById(R.id.ImageButton03);                                
        				b1.setVisibility(View.INVISIBLE);
        				ImageButton b2 = (ImageButton)findViewById(R.id.ImageButton02);                                
        				b2.setVisibility(View.INVISIBLE);
            			ImageView iv= (ImageView)findViewById(R.id.imageView1);
            			Drawable draw=iv.getDrawable();
                		bmp1 = ((BitmapDrawable)draw).getBitmap();
                		progressDialog = ProgressDialog.show(Gallery.this, "", "Working...");
                   	   	//Drawable draw=yourimgView.getDrawable();
                  			//bmp1 = ((BitmapDrawable)draw).getBitmap();
                   	   	Thread t = new Thread() {
                              public void run() {
                            	  BMP= smooth(bmp1,100);
                            	  mHandler.post(mUpdateResults5);
                              }
                          };
                          t.start();
            		}
            }
            
		});
		
        //D
        ActionItem round = new ActionItem();
        round.setTitle("Round Corners");
        round.setIcon(getResources().getDrawable(R.drawable.ic_nothing));
        
        ActionItem circle = new ActionItem();
        circle.setTitle("Circle Frame");
        circle.setIcon(getResources().getDrawable(R.drawable.ic_nothing));
        
        ActionItem round2 = new ActionItem();
        round2.setTitle("Round Edges");
        round2.setIcon(getResources().getDrawable(R.drawable.ic_nothing));
        
        ActionItem emboss = new ActionItem();
        emboss.setTitle("Emboss");
        emboss.setIcon(getResources().getDrawable(R.drawable.ic_nothing));
        
        ActionItem engrave = new ActionItem();
        engrave.setTitle("Engrave");
        engrave.setIcon(getResources().getDrawable(R.drawable.ic_nothing));
        
        ActionItem sepiabrown = new ActionItem();
        sepiabrown.setTitle("Sepia Brown");
        sepiabrown.setIcon(getResources().getDrawable(R.drawable.ic_nothing));
        
        ActionItem sepiagreen = new ActionItem();
        sepiagreen.setTitle("Sepia Green");
        sepiagreen.setIcon(getResources().getDrawable(R.drawable.ic_nothing));
        
        final QuickAction mQuickActionD  = new QuickAction(this);
        
		mQuickActionD.addActionItem(round);
		mQuickActionD.addActionItem(circle);
		mQuickActionD.addActionItem(round2);
		mQuickActionD.addActionItem(emboss);
		mQuickActionD.addActionItem(engrave);
		mQuickActionD.addActionItem(sepiabrown);
		mQuickActionD.addActionItem(sepiagreen);
		
		mQuickActionD.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {                 
            public void onItemClick(int pos) {
            		if(pos == 0) 
                    { //Round Edge
            			ImageButton b1 = (ImageButton)findViewById(R.id.ImageButton03);                                
        				b1.setVisibility(View.INVISIBLE);
        				ImageButton b2 = (ImageButton)findViewById(R.id.ImageButton02);                                
        				b2.setVisibility(View.INVISIBLE);
            			ImageView iv= (ImageView)findViewById(R.id.imageView1);
            			Drawable draw=iv.getDrawable();
                		bmp1 = ((BitmapDrawable)draw).getBitmap();
            			BMP= roundCorner(bmp1,45);
            			iv= (ImageView)findViewById(R.id.imageView1);
            			BitmapDrawable bmd = new BitmapDrawable(BMP);
                		iv.setImageDrawable(bmd);
                		bmp1 = ((BitmapDrawable)bmd).getBitmap();
            		}
            		else if(pos == 1) 
                    { //Round Edge
            			ImageButton b1 = (ImageButton)findViewById(R.id.ImageButton03);                                
        				b1.setVisibility(View.INVISIBLE);
        				ImageButton b2 = (ImageButton)findViewById(R.id.ImageButton02);                                
        				b2.setVisibility(View.INVISIBLE);
            			ImageView iv= (ImageView)findViewById(R.id.imageView1);
            			Drawable draw=iv.getDrawable();
                		bmp1 = ((BitmapDrawable)draw).getBitmap();
            			BMP= drawCircle(bmp1);
            			iv= (ImageView)findViewById(R.id.imageView1);
            			BitmapDrawable bmd = new BitmapDrawable(BMP);
                		iv.setImageDrawable(bmd);
                		bmp1 = ((BitmapDrawable)bmd).getBitmap();
            		}
            		else if(pos == 2) 
                    { //Round Edge
            			ImageButton b1 = (ImageButton)findViewById(R.id.ImageButton03);                                
        				b1.setVisibility(View.INVISIBLE);
        				ImageButton b2 = (ImageButton)findViewById(R.id.ImageButton02);                                
        				b2.setVisibility(View.INVISIBLE);
            			ImageView iv= (ImageView)findViewById(R.id.imageView1);
            			Drawable draw=iv.getDrawable();
                		bmp1 = ((BitmapDrawable)draw).getBitmap();
            			BMP= drawFrame1(bmp1);
            			iv= (ImageView)findViewById(R.id.imageView1);
            			BitmapDrawable bmd = new BitmapDrawable(BMP);
                		iv.setImageDrawable(bmd);
                		bmp1 = ((BitmapDrawable)bmd).getBitmap();
            		}
            		else if(pos == 3) 
                    { //Round Edge
            			ImageButton b1 = (ImageButton)findViewById(R.id.ImageButton03);                                
        				b1.setVisibility(View.INVISIBLE);
        				ImageButton b2 = (ImageButton)findViewById(R.id.ImageButton02);                                
        				b2.setVisibility(View.INVISIBLE);
               			ImageView iv= (ImageView)findViewById(R.id.imageView1);
            			Drawable draw=iv.getDrawable();
                		bmp1 = ((BitmapDrawable)draw).getBitmap();
                		progressDialog = ProgressDialog.show(Gallery.this, "", "Working...");
                   	   	//Drawable draw=yourimgView.getDrawable();
                  			//bmp1 = ((BitmapDrawable)draw).getBitmap();
                   	   	Thread t = new Thread() {
                              public void run() {
                            	  BMP= emboss(bmp1);
                            	  mHandler.post(mUpdateResults5);
                              }
                          };
                          t.start();
            		}
            		else if(pos == 4) 
                    { //Round Edge
            			ImageButton b1 = (ImageButton)findViewById(R.id.ImageButton03);                                
        				b1.setVisibility(View.INVISIBLE);
        				ImageButton b2 = (ImageButton)findViewById(R.id.ImageButton02);                                
        				b2.setVisibility(View.INVISIBLE);
               			ImageView iv= (ImageView)findViewById(R.id.imageView1);
            			Drawable draw=iv.getDrawable();
                		bmp1 = ((BitmapDrawable)draw).getBitmap();
                		progressDialog = ProgressDialog.show(Gallery.this, "", "Working...");
                   	   	//Drawable draw=yourimgView.getDrawable();
                  			//bmp1 = ((BitmapDrawable)draw).getBitmap();
                   	   	Thread t = new Thread() {
                              public void run() {
                            	  BMP= engrave(bmp1);
                            	  mHandler.post(mUpdateResults5);
                              }
                          };
                          t.start();
            		}
            		else if(pos == 5) 
                    { //Round Edge
            			ImageButton b1 = (ImageButton)findViewById(R.id.ImageButton03);                                
        				b1.setVisibility(View.INVISIBLE);
        				ImageButton b2 = (ImageButton)findViewById(R.id.ImageButton02);                                
        				b2.setVisibility(View.INVISIBLE);
               			ImageView iv= (ImageView)findViewById(R.id.imageView1);
            			Drawable draw=iv.getDrawable();
                		bmp1 = ((BitmapDrawable)draw).getBitmap();
                		progressDialog = ProgressDialog.show(Gallery.this, "", "Working...");
                   	   	//Drawable draw=yourimgView.getDrawable();
                  			//bmp1 = ((BitmapDrawable)draw).getBitmap();
                   	   	Thread t = new Thread() {
                              public void run() {
                            	  BMP= createSepiaToningEffect(bmp1, 75, 0.6, 0.6, 0);
                            	  mHandler.post(mUpdateResults5);
                              }
                          };
                          t.start();
            		}
            		else if(pos == 6) 
                    { //Round Edge
            			ImageButton b1 = (ImageButton)findViewById(R.id.ImageButton03);                                
        				b1.setVisibility(View.INVISIBLE);
        				ImageButton b2 = (ImageButton)findViewById(R.id.ImageButton02);                                
        				b2.setVisibility(View.INVISIBLE);
               			ImageView iv= (ImageView)findViewById(R.id.imageView1);
            			Drawable draw=iv.getDrawable();
                		bmp1 = ((BitmapDrawable)draw).getBitmap();
                		progressDialog = ProgressDialog.show(Gallery.this, "", "Working...");
                   	   	//Drawable draw=yourimgView.getDrawable();
                  			//bmp1 = ((BitmapDrawable)draw).getBitmap();
                   	   	Thread t = new Thread() {
                              public void run() {
                            	  BMP= createSepiaToningEffect(bmp1, 75, 0.3, 0.6, 0);
                            	  mHandler.post(mUpdateResults5);
                              }
                          };
                          t.start();
            		}
 
 
            }
		});
           
		
		//E
        ActionItem effect1 = new ActionItem();
        effect1.setTitle("Sketch Effect");
        effect1.setIcon(getResources().getDrawable(R.drawable.ic_nothing));
        
        ActionItem effect2 = new ActionItem();
        effect2.setTitle("Metallic Effect");
        effect2.setIcon(getResources().getDrawable(R.drawable.ic_nothing));
        
        ActionItem effect3 = new ActionItem();
        effect3.setTitle("Back 1836");
        effect3.setIcon(getResources().getDrawable(R.drawable.ic_nothing));
        
        final QuickAction mQuickActionE  = new QuickAction(this);
        
		mQuickActionE.addActionItem(effect1);
		mQuickActionE.addActionItem(effect2);
		mQuickActionE.addActionItem(effect3);
		
		mQuickActionE.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {                 
            public void onItemClick(int pos) {
            		if(pos == 0) 
                    {
            			Toast.makeText(Gallery.this, "Image will be saved with the sketch effect in 'Diagonal Pixel Magic Effects' folder",   Toast.LENGTH_LONG).show();
            			ImageView iv= (ImageView)findViewById(R.id.imageView1);
            			final float widthX= iv.getWidth();
            		//	Toast.makeText(Gallery.this, Float.toString(widthX),   Toast.LENGTH_LONG).show();
            			Drawable draw=iv.getDrawable();
                		bmp2 = ((BitmapDrawable)draw).getBitmap();
                   	   	Thread t = new Thread() {
                              public void run() {
                            	  
                            	  bmp2= applyMeanRemoval(bmp2);
                            	  bmp2= doGreyscale(bmp2);
                            	  bmp2= doBrightness(bmp2,120);
                            	  bmp2= doBrightness(bmp2,-120);
                            	  mHandler.post(mUpdateResults6);
                              }
                          };
                          t.start();
                    }
            		else if(pos == 1) 
                    {
            			Toast.makeText(Gallery.this, "Image will be saved with the metallic effect in 'Diagonal Pixel Magic Effects' folder",   Toast.LENGTH_LONG).show();
            			ImageView iv= (ImageView)findViewById(R.id.imageView1);
            			final float widthX= iv.getWidth();
            			Drawable draw=iv.getDrawable();
                		bmp2 = ((BitmapDrawable)draw).getBitmap();
                   	   	Thread t = new Thread() {
                              public void run() {
                            	  bmp2= doBrightness(bmp2,84);
                            	  bmp2= doContrast(bmp2,100);
                            	  bmp2= applyShadingFilter(bmp1,Color.rgb(0,191,255));
                            	  bmp2= doGreyscale(bmp2);
                            	  mHandler.post(mUpdateResults6);
                              }
                          };
                          t.start();
                    }
            		else if(pos == 2)
            		{
            			Toast.makeText(Gallery.this, "Image will be saved with the back-1836 effect in 'Diagonal Pixel Magic Effects' folder",   Toast.LENGTH_LONG).show();
            			ImageView iv= (ImageView)findViewById(R.id.imageView1);
            			final float widthX= iv.getWidth();
            			Drawable draw=iv.getDrawable();
                		bmp2 = ((BitmapDrawable)draw).getBitmap();
                   	   	Thread t = new Thread() {
                              public void run() {
                            	  bmp2= doBrightness(bmp2,-40);
                            	  bmp2= doContrast(bmp2,0);
                            	  bmp2= createSepiaToningEffect(bmp2, 30, 0.65, 0.4, 0);
                            	  mHandler.post(mUpdateResults6);
                              }
                          };
                          t.start();            			
            		}
            }
		});
        ////////////////////////////////
        
        
        Button btnA = (Button)findViewById(R.id.A);
        btnA.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final ImageView imgcontrols= (ImageView)findViewById(R.id.ImageView01);
                imgcontrols.setVisibility(View.GONE);
                final ImageButton tintinc = (ImageButton)findViewById(R.id.ImageButton09);
                final ImageButton tintdec = (ImageButton)findViewById(R.id.ImageButton08);
                tintinc.setVisibility(View.GONE);
                tintdec.setVisibility(View.GONE);
                final LinearLayout lincolorfilter = (LinearLayout)findViewById(R.id.linearLayout2);
                lincolorfilter.setVisibility(View.GONE);
                final LinearLayout linshadingfilter = (LinearLayout)findViewById(R.id.LinearLayout01);
                linshadingfilter.setVisibility(View.GONE);
                mQuickActionA.show(v);
                mQuickActionA.setAnimStyle(QuickAction.ANIM_GROW_FROM_CENTER);
                
			}
		});
        
        Button btnB = (Button)findViewById(R.id.B);
        btnB.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
            	
                final ImageButton btnright = (ImageButton)findViewById(R.id.ImageButton04);
			    final ImageButton btnleft = (ImageButton)findViewById(R.id.ImageButton05);
			    final ImageButton btnhorizontal = (ImageButton)findViewById(R.id.ImageButton06);
			    final ImageButton btnvertical = (ImageButton)findViewById(R.id.ImageButton07);
                btnright.setVisibility(View.GONE);
                btnleft.setVisibility(View.GONE);
                btnhorizontal.setVisibility(View.GONE);
                btnvertical.setVisibility(View.GONE);
                final LinearLayout lincolorfilter = (LinearLayout)findViewById(R.id.linearLayout2);
                lincolorfilter.setVisibility(View.GONE);
                final LinearLayout linshadingfilter = (LinearLayout)findViewById(R.id.LinearLayout01);
                linshadingfilter.setVisibility(View.GONE);
                
			    mQuickActionB.show(v);
                mQuickActionB.setAnimStyle(QuickAction.ANIM_GROW_FROM_CENTER);
			}
		});
        
        Button btnC = (Button)findViewById(R.id.C);
        btnC.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
            	
                final ImageButton btnright = (ImageButton)findViewById(R.id.ImageButton04);
			    final ImageButton btnleft = (ImageButton)findViewById(R.id.ImageButton05);
			    final ImageButton btnhorizontal = (ImageButton)findViewById(R.id.ImageButton06);
			    final ImageButton btnvertical = (ImageButton)findViewById(R.id.ImageButton07);
                btnright.setVisibility(View.GONE);
                btnleft.setVisibility(View.GONE);
                btnhorizontal.setVisibility(View.GONE);
                btnvertical.setVisibility(View.GONE);
                final ImageView imgcontrols= (ImageView)findViewById(R.id.ImageView01);
                imgcontrols.setVisibility(View.GONE);
                final ImageButton tintinc = (ImageButton)findViewById(R.id.ImageButton09);
                final ImageButton tintdec = (ImageButton)findViewById(R.id.ImageButton08);
                final LinearLayout lincolorfilter = (LinearLayout)findViewById(R.id.linearLayout2);
                lincolorfilter.setVisibility(View.GONE);
                final LinearLayout linshadingfilter = (LinearLayout)findViewById(R.id.LinearLayout01);
                linshadingfilter.setVisibility(View.GONE);
                tintinc.setVisibility(View.GONE);
                tintdec.setVisibility(View.GONE);
                
			    mQuickActionC.show(v);
                mQuickActionC.setAnimStyle(QuickAction.ANIM_GROW_FROM_CENTER);
			}
		});
        
        
        Button btnD = (Button)findViewById(R.id.D);
        btnD.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
            	
                final ImageButton btnright = (ImageButton)findViewById(R.id.ImageButton04);
			    final ImageButton btnleft = (ImageButton)findViewById(R.id.ImageButton05);
			    final ImageButton btnhorizontal = (ImageButton)findViewById(R.id.ImageButton06);
			    final ImageButton btnvertical = (ImageButton)findViewById(R.id.ImageButton07);
                btnright.setVisibility(View.GONE);
                btnleft.setVisibility(View.GONE);
                btnhorizontal.setVisibility(View.GONE);
                btnvertical.setVisibility(View.GONE);
                final ImageView imgcontrols= (ImageView)findViewById(R.id.ImageView01);
                imgcontrols.setVisibility(View.GONE);
                final ImageButton tintinc = (ImageButton)findViewById(R.id.ImageButton09);
                final ImageButton tintdec = (ImageButton)findViewById(R.id.ImageButton08);
                final LinearLayout lincolorfilter = (LinearLayout)findViewById(R.id.linearLayout2);
                lincolorfilter.setVisibility(View.GONE);
                final LinearLayout linshadingfilter = (LinearLayout)findViewById(R.id.LinearLayout01);
                linshadingfilter.setVisibility(View.GONE);
                tintinc.setVisibility(View.GONE);
                tintdec.setVisibility(View.GONE);
				
			    mQuickActionD.show(v);
                mQuickActionD.setAnimStyle(QuickAction.ANIM_GROW_FROM_CENTER);
			}
		});
        
        Button btnE = (Button)findViewById(R.id.E);
        btnE.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
            	
                final ImageButton btnright = (ImageButton)findViewById(R.id.ImageButton04);
			    final ImageButton btnleft = (ImageButton)findViewById(R.id.ImageButton05);
			    final ImageButton btnhorizontal = (ImageButton)findViewById(R.id.ImageButton06);
			    final ImageButton btnvertical = (ImageButton)findViewById(R.id.ImageButton07);
                btnright.setVisibility(View.GONE);
                btnleft.setVisibility(View.GONE);
                btnhorizontal.setVisibility(View.GONE);
                btnvertical.setVisibility(View.GONE);
                final ImageView imgcontrols= (ImageView)findViewById(R.id.ImageView01);
                imgcontrols.setVisibility(View.GONE);
                final ImageButton tintinc = (ImageButton)findViewById(R.id.ImageButton09);
                final ImageButton tintdec = (ImageButton)findViewById(R.id.ImageButton08);
                final LinearLayout lincolorfilter = (LinearLayout)findViewById(R.id.linearLayout2);
                lincolorfilter.setVisibility(View.GONE);
                final LinearLayout linshadingfilter = (LinearLayout)findViewById(R.id.LinearLayout01);
                linshadingfilter.setVisibility(View.GONE);
                tintinc.setVisibility(View.GONE);
                tintdec.setVisibility(View.GONE);
				
			    mQuickActionE.show(v);
                mQuickActionE.setAnimStyle(QuickAction.ANIM_GROW_FROM_CENTER);
			}
		});
        
        ImageButton right = (ImageButton)findViewById(R.id.ImageButton01);
        right.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final ImageButton btnright = (ImageButton)findViewById(R.id.ImageButton04);
			    final ImageButton btnleft = (ImageButton)findViewById(R.id.ImageButton05);
			    final ImageButton btnhorizontal = (ImageButton)findViewById(R.id.ImageButton06);
			    final ImageButton btnvertical = (ImageButton)findViewById(R.id.ImageButton07);
                btnright.setVisibility(View.GONE);
                btnleft.setVisibility(View.GONE);
                btnhorizontal.setVisibility(View.GONE);
                btnvertical.setVisibility(View.GONE);
                
                ImageButton b1 = (ImageButton)findViewById(R.id.ImageButton03);                                
				b1.setVisibility(View.VISIBLE);
				ImageButton b2 = (ImageButton)findViewById(R.id.ImageButton02);                                
				b2.setVisibility(View.VISIBLE);
                
                final ImageView imgcontrols= (ImageView)findViewById(R.id.ImageView01);
                imgcontrols.setVisibility(View.GONE);
                
                final ImageButton tintinc = (ImageButton)findViewById(R.id.ImageButton09);
                final ImageButton tintdec = (ImageButton)findViewById(R.id.ImageButton08);
                tintinc.setVisibility(View.GONE);
                tintdec.setVisibility(View.GONE);
                
                final LinearLayout lincolorfilter = (LinearLayout)findViewById(R.id.linearLayout2);
                lincolorfilter.setVisibility(View.GONE);
                final LinearLayout linshadingfilter = (LinearLayout)findViewById(R.id.LinearLayout01);
                linshadingfilter.setVisibility(View.GONE);

                bmp=bmp1;
            /*    final ImageView yourimgView = (ImageView)findViewById(R.id.imageView1);
                yourimgView.setImageBitmap(bmp);*/
			}
		});
        
        ImageButton cross = (ImageButton)findViewById(R.id.imageButton1);
        cross.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final ImageButton btnright = (ImageButton)findViewById(R.id.ImageButton04);
			    final ImageButton btnleft = (ImageButton)findViewById(R.id.ImageButton05);
			    final ImageButton btnhorizontal = (ImageButton)findViewById(R.id.ImageButton06);
			    final ImageButton btnvertical = (ImageButton)findViewById(R.id.ImageButton07);
                btnright.setVisibility(View.GONE);
                btnleft.setVisibility(View.GONE);
                btnhorizontal.setVisibility(View.GONE);
                btnvertical.setVisibility(View.GONE);
                
                ImageButton b1 = (ImageButton)findViewById(R.id.ImageButton03);                                
				b1.setVisibility(View.VISIBLE);
				ImageButton b2 = (ImageButton)findViewById(R.id.ImageButton02);                                
				b2.setVisibility(View.VISIBLE);
                
                
                final ImageView imgcontrols= (ImageView)findViewById(R.id.ImageView01);
                imgcontrols.setVisibility(View.GONE);
                
                final ImageButton tintinc = (ImageButton)findViewById(R.id.ImageButton09);
                final ImageButton tintdec = (ImageButton)findViewById(R.id.ImageButton08);
                tintinc.setVisibility(View.GONE);
                tintdec.setVisibility(View.GONE);
                
                final LinearLayout lincolorfilter = (LinearLayout)findViewById(R.id.linearLayout2);
                lincolorfilter.setVisibility(View.GONE);
                final LinearLayout linshadingfilter = (LinearLayout)findViewById(R.id.LinearLayout01);
                linshadingfilter.setVisibility(View.GONE);
                final ImageView yourimgView = (ImageView)findViewById(R.id.imageView1);
                yourimgView.setImageBitmap(bmp);
			}
		});
        
        
        ImageButton exit = (ImageButton)findViewById(R.id.imageButton1);
        exit.setOnLongClickListener(new View.OnLongClickListener() {
			
			public boolean onLongClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
				return false;
			}
		});
        
        /////////////////////////////////
    /*    Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.putExtra("crop", "true");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, getTempUri());
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
    	startActivityForResult(intent,0);
  */
        ImageButton ib2 = (ImageButton)findViewById(R.id.ImageButton03);

        
        ib2.setOnClickListener(new View.OnClickListener() {
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(Intent.ACTION_GET_CONTENT,null);
	        intent.setType("image/*");
	        //intent.setAction();
	        intent.putExtra("crop", "true");
	        intent.putExtra(MediaStore.EXTRA_OUTPUT, getTempUri());
	        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
	    		
	        startActivityForResult(intent,0);
		}
	});
    }
 
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent eturnedIntent) { 
        super.onActivityResult(requestCode, resultCode, eturnedIntent); 

        switch(requestCode) { 
        case REQ_CODE_PICK_IMAGE:
            if(resultCode == RESULT_OK){  
	            String filePath= Environment.getExternalStorageDirectory() + "/temporary_holder.jpg";
				
				ImageView iv=(ImageView)findViewById(R.id.imageView1);
				Bitmap yourSelectedImage = BitmapFactory.decodeFile(filePath);

                iv.setImageBitmap(yourSelectedImage);
                
        	    int height = yourSelectedImage.getHeight();
            	int width = yourSelectedImage.getWidth();
        	    
                float scale;
         
                if(height>width)
                	scale = 480/(float)height;
                else
                	scale = 480/(float)width;
                // load cached small image
                Matrix matrix = new Matrix();
                matrix.postScale(scale,scale);
                bmp = Bitmap.createBitmap(yourSelectedImage, 0, 0, width, height, matrix, true);
                iv.setImageBitmap(bmp);
                bmp1=bmp;
                Toast.makeText(Gallery.this, "After applying any effect click on tick to apply or else click on cross to remove the applied effect", Toast.LENGTH_LONG).show();
                }
            }
        }
    
    
    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
    
    private Uri getTempUri() {
	    return Uri.fromFile(getTempFile());
	    }

	    private File getTempFile() {
	    if (isSDCARDMounted()) {
	    	File f = new File(Environment.getExternalStorageDirectory(),TEMP_PHOTO_FILE);
	    	try {
	    			f.createNewFile();
	    	} catch (java.io.IOException e) {
	    }
	    return f;
	    } 	
	    else {
	    return null;
	    	}
	}

	    private boolean isSDCARDMounted(){
	    String status = Environment.getExternalStorageState();
	    if (status.equals(Environment.MEDIA_MOUNTED))
	    return true;
	    return false;
	    }

    

	    final Handler mHandler = new Handler();

	    // Create runnable for posting
	    final Runnable mUpdateResults = new Runnable() {
	        public void run() {
	            updateResultsInUiBlacknWhite();
	        }
	    };
	    
	    final Runnable mUpdateResults2 = new Runnable() {
	        public void run() {
	            updateResultsInUiTint();
	        }
	    };
	    
	    final Runnable mUpdateResults3 = new Runnable() {
	        public void run() {
	            updateResultsInUiInvert();
	        }
	    };
	    
	    final Runnable mUpdateResults4 = new Runnable() {
	        public void run() {
	            updateResultsInUiColorFilter();
	        }
	    };
	    
	    final Runnable mUpdateResults5 = new Runnable() {
	        public void run() {
	            updateResultsInUiD();
	        }
	    };
	
	    final Runnable mUpdateResults6 = new Runnable() {
	        public void run() {
	            save2();
	        }
	    };
	
	    private void updateResultsInUiD() {
	    	final ImageView yourimgView = (ImageView)findViewById(R.id.imageView1);
     	   	BitmapDrawable bmd = new BitmapDrawable(BMP);
     	   	yourimgView.setImageDrawable(bmd);
     	   	bmp1 = ((BitmapDrawable)bmd).getBitmap();
     	   	progressDialog.dismiss();
	        // Back in the UI thread -- update our UI elements based on the data in mResults
	    }
	    
	    private void updateResultsInUiBlacknWhite() {
	    	final ImageView yourimgView = (ImageView)findViewById(R.id.imageView1);
     	   	BitmapDrawable bmd = new BitmapDrawable(BMP);
     	   	yourimgView.setImageDrawable(bmd);
     	   	bmp1 = ((BitmapDrawable)bmd).getBitmap();
     	   	progressDialog.dismiss();
	        // Back in the UI thread -- update our UI elements based on the data in mResults
	    }
	    
	    private void updateResultsInUiColorFilter() {
	    	final ImageView yourimgView = (ImageView)findViewById(R.id.imageView1);
     	   	BitmapDrawable bmd = new BitmapDrawable(BMP);
     	   	yourimgView.setImageDrawable(bmd);
     	   	bmp1 = ((BitmapDrawable)bmd).getBitmap();
     	   	progressDialog.dismiss();
	        // Back in the UI thread -- update our UI elements based on the data in mResults
	    }
	    
	    private void updateResultsInUiTint() {
	    	final ImageView yourimgView = (ImageView)findViewById(R.id.imageView1);
     	   	BitmapDrawable bmd = new BitmapDrawable(BMP);
     	   	yourimgView.setImageDrawable(bmd);
     	   	bmp1 = ((BitmapDrawable)bmd).getBitmap();
     	   	progressDialog.dismiss();
	        // Back in the UI thread -- update our UI elements based on the data in mResults
	    }
	    

	    private void updateResultsInUiInvert() {
	    	final ImageView yourimgView = (ImageView)findViewById(R.id.imageView1);
     	   	BitmapDrawable bmd = new BitmapDrawable(BMP);
     	   	yourimgView.setImageDrawable(bmd);
     	   	bmp1 = ((BitmapDrawable)bmd).getBitmap();
     	   	progressDialog.dismiss();
	        // Back in the UI thread -- update our UI elements based on the data in mResults
	    }
	    
	    
	    //functions
    public static Bitmap rotate(Bitmap src,float degree) {
   		// create new matrix
   		Matrix matrix = new Matrix();
   		// setup rotation degree
   		matrix.postRotate(degree);
   		// return new bitmap rotated using matrix
   		return Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), matrix, true);
   	}
    
    public static Bitmap flipVertical(Bitmap src) {
		// create new matrix for transformation
		Matrix matrix = new Matrix();
				
			matrix.preScale(1.0f, -1.0f);
		
		// return transformed image
		return Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), matrix, true);
	}
	
	public static Bitmap flipHorizontal(Bitmap src) {
		// create new matrix for transformation
		Matrix matrix = new Matrix();
				
			matrix.preScale(-1.0f, 1.0f);
		
		// return transformed image
		return Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), matrix, true);
	}
	
	public Bitmap doBrightness(Bitmap src, float brightness) {
    	int width = src.getWidth();
    	int height = src.getHeight();
    	Bitmap bmOut = Bitmap.createBitmap(width, height, src.getConfig());
    	Canvas canvas = new Canvas(bmOut);
        Paint paint = new Paint();
        ColorMatrix cm = new ColorMatrix();
        float scale = brightness;
        float translate = (.5f * scale + .5f) * 2.55f;
        cm.set(new float[] { 1, 0, 0, 0, translate, 
        		0, 1, 0, 0, translate, 
        		0, 0 , 1, 0, translate, 
        		0, 0, 0, 1, 0 });
        
        paint.setColorFilter(new ColorMatrixColorFilter(cm));
        Matrix matrix = new Matrix();
        canvas.drawBitmap(src, matrix, paint);
    	// return final image
    	return bmOut;
    }
    
    public Bitmap doContrast(Bitmap src, float contrast) {
    	int width = src.getWidth();
    	int height = src.getHeight();
    	Bitmap bmOut = Bitmap.createBitmap(width, height, src.getConfig());
    	Canvas canvas = new Canvas(bmOut);
        Paint paint = new Paint();
        ColorMatrix cm = new ColorMatrix();
        float translate = contrast/50 + 0.5f;
        cm.set(new float[] { translate, 0, 0, 0, 0, 
        		0, translate, 0, 0, 0, 
        		0, 0 , translate, 0, 0, 
        		0, 0, 0, 1, 0 });
        
        paint.setColorFilter(new ColorMatrixColorFilter(cm));
        Matrix matrix = new Matrix();
        canvas.drawBitmap(src, matrix, paint);
    	// return final image
    	return bmOut;
    }
    
    public static Bitmap applyDarkEffect(Bitmap source) {
    	// get image size
    	int width = source.getWidth();
    	int height = source.getHeight();
    	int[] pixels = new int[width * height];
    	// get pixel array from source
    	source.getPixels(pixels, 0, width, 0, 0, width, height);
    	// random object
    	
    	int color = Color.rgb(65, 50, 29);
    	int index = 0;

    	// iteration through pixels
    	for(int y = 0; y < height; ++y) {
    		for(int x = 0; x < width; x++) {
    			// get current index in 2D-matrix
    			index = y * width + x;
    			
    				pixels[index] &= color;
    										
    		}
    	}
    	// output bitmap				
    	Bitmap bmOut = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
    	bmOut.setPixels(pixels, 0, width, 0, 0, width, height);
    	return bmOut;
    }
    
 	public static Bitmap tintImage(Bitmap src, int degree) {

	    int width = src.getWidth();
		int height = src.getHeight();

	    int[] pix = new int[width * height];
	    src.getPixels(pix, 0, width, 0, 0, width, height);

	    int RY, GY, BY, RYY, GYY, BYY, R, G, B, Y;
	    double angle = (PI * (double)degree) / HALF_CIRCLE_DEGREE;
	   
	    int S = (int)(RANGE * Math.sin(angle));
	    int C = (int)(RANGE * Math.cos(angle));

	    for (int y = 0; y < height; y++)
	        for (int x = 0; x < width; x++) {
		    	int index = y * width + x;
		    	int r = ( pix[index] >> 16 ) & 0xff;
		    	int g = ( pix[index] >> 8 ) & 0xff;
		    	int b = pix[index] & 0xff;
		    	RY = ( 70 * r - 59 * g - 11 * b ) / 100;
		    	GY = (-30 * r + 41 * g - 11 * b ) / 100;
		    	BY = (-30 * r - 59 * g + 89 * b ) / 100;
		    	Y  = ( 30 * r + 59 * g + 11 * b ) / 100;
		    	RYY = ( S * BY + C * RY ) / 256;
		    	BYY = ( C * BY - S * RY ) / 256;
		    	GYY = (-51 * RYY - 19 * BYY ) / 100;
		    	R = Y + RYY;
		    	R = ( R < 0 ) ? 0 : (( R > 255 ) ? 255 : R );
		    	G = Y + GYY;
		    	G = ( G < 0 ) ? 0 : (( G > 255 ) ? 255 : G );
		    	B = Y + BYY;
		    	B = ( B < 0 ) ? 0 : (( B > 255 ) ? 255 : B );
		    	pix[index] = 0xff000000 | (R << 16) | (G << 8 ) | B;
	    	}
	    
	    Bitmap outBitmap = Bitmap.createBitmap(width, height, src.getConfig());	   
	    outBitmap.setPixels(pix, 0, width, 0, 0, width, height);
	   
	    pix = null;
	   
	    return outBitmap;
	}
 	
 	public static Bitmap doGreyscale(Bitmap src) {
		// constant factors
		final double GS_RED = 0.299;
		final double GS_GREEN = 0.587;
		final double GS_BLUE = 0.114;

		// create output bitmap
		Bitmap bmOut = Bitmap.createBitmap(src.getWidth(), src.getHeight(), src.getConfig());
		// pixel information
		int A, R, G, B;
		int pixel;

		// get image size
		int width = src.getWidth();
		int height = src.getHeight();

		// scan through every single pixel
		for(int x = 0; x < width; ++x) {
			for(int y = 0; y < height; ++y) {
				// get one pixel color
				pixel = src.getPixel(x, y);
				// retrieve color of all channels
				A = Color.alpha(pixel);
				R = Color.red(pixel);
				G = Color.green(pixel);
				B = Color.blue(pixel);
				// take conversion up to one single value
				R = G = B = (int)(GS_RED * R + GS_GREEN * G + GS_BLUE * B);
				// set new pixel color to output bitmap
				bmOut.setPixel(x, y, Color.argb(A, R, G, B));
			}
		}

		// return final image
		return bmOut;
	}
 	
	public static Bitmap doInvert(Bitmap src) {
		// create new bitmap with the same settings as source bitmap
		Bitmap bmOut = Bitmap.createBitmap(src.getWidth(), src.getHeight(), src.getConfig());
		// color info
		int A, R, G, B;
		int pixelColor;
		// image size
		int height = src.getHeight();
		int width = src.getWidth();

		// scan through every pixel
	    for (int y = 0; y < height; y++)
	    {
	        for (int x = 0; x < width; x++)
	        {
	        	// get one pixel
	            pixelColor = src.getPixel(x, y);
	            // saving alpha channel
	            A = Color.alpha(pixelColor);
	            // inverting byte for each R/G/B channel
	            R = 255 - Color.red(pixelColor);
	            G = 255 - Color.green(pixelColor);
	            B = 255 - Color.blue(pixelColor);
	            // set newly-inverted pixel to output image
	            bmOut.setPixel(x, y, Color.argb(A, R, G, B));
	        }
	    }

	    // return final bitmap
	    return bmOut;
	}
	
	public static Bitmap roundCorner(Bitmap src, float round) {
		// image size
		int width = src.getWidth();
		int height = src.getHeight();
		// create bitmap output
	    Bitmap result = Bitmap.createBitmap(width, height, Config.ARGB_8888);
	    // set canvas for painting
	    Canvas canvas = new Canvas(result);
	    canvas.drawARGB(0, 0, 0, 0);

	    // config paint
	    final Paint paint = new Paint();
	    paint.setAntiAlias(true);
	    paint.setColor(Color.BLACK);

	    // config rectangle for embedding
	    final Rect rect = new Rect(0, 0, width, height);
	    final RectF rectF = new RectF(rect);

	    // draw rect to canvas
	    canvas.drawRoundRect(rectF, round, round, paint);

	    // create Xfer mode
	    paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
	    // draw source image to canvas
	    canvas.drawBitmap(src, rect, rect, paint);

	    // return final image
	    return result;
	}
	
	public static Bitmap drawCircle(Bitmap src) {
		// image size
		int width = src.getWidth();
		int height = src.getHeight();
		// create bitmap output
	    Bitmap result = Bitmap.createBitmap(width, height, Config.ARGB_8888);
	    // set canvas for painting
	    Canvas canvas = new Canvas(result);
	    canvas.drawARGB(0, 0, 0, 0);

	    // config paint
	    final Paint paint = new Paint();
	    paint.setAntiAlias(true);
	    paint.setColor(Color.BLACK);

	    // config rectangle for embedding
	    final Rect rect = new Rect(0, 0, width, height);

	    if(width/2 < height/2 )
	    	canvas.drawCircle(width/2, height/2, width/2, paint);
	    else 
	    	canvas.drawCircle(width/2, height/2, height/2, paint);
	    // create Xfer mode
	    paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
	    // draw source image to canvas
	    canvas.drawBitmap(src, rect, rect, paint);

	    // return final image
	    return result;
	}
	
	public static Bitmap drawFrame1(Bitmap src) {
		// image size
		int width = src.getWidth();
		int height = src.getHeight();
		float roundH = height/20, roundW=width/20;
		// create bitmap output
	    Bitmap result = Bitmap.createBitmap(width, height, Config.ARGB_8888);
	    // set canvas for painting
	    Canvas canvas = new Canvas(result);
	    canvas.drawARGB(0, 0, 0, 0);

	    // config paint
	    final Paint paint = new Paint();
	    paint.setAntiAlias(true);
	    paint.setColor(Color.BLACK);

	    // config rectangle for embedding
	    final Rect rect = new Rect(15, 15, width-15, height-15);
	    final RectF rectF = new RectF(rect);
	    
	    // draw rect to canvas
	    canvas.drawRect(rectF, paint);
	    // create Xfer mode
	    
	    // draw source image to canvas
	    float i;
	    for(i=roundW; i<width;i=i+2*roundW)
	    {
	    	canvas.drawCircle(i, roundW, roundW, paint);
	    	canvas.drawCircle(i, height-roundW, roundW, paint);
	    }
	    for(i=roundH; i<height;i=i+2*roundH)
	    {
	    	canvas.drawCircle(roundH, i, roundH, paint);
	    	canvas.drawCircle(width-roundH, i, roundH, paint);
	    }
	    
	    paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
	    
	    final Rect frame1 = new Rect(0, 0, width, height);
	    
	    canvas.drawBitmap(src, frame1, frame1, paint);
	    // return final image
	    return result;
	}
	
	public static Bitmap doColorFilter(Bitmap src, double red, double green, double blue) {
		// image size
		int width = src.getWidth();
		int height = src.getHeight();
		// create output bitmap
		Bitmap bmOut = Bitmap.createBitmap(width, height, src.getConfig());
		// color information
		int A, R, G, B;
		int pixel;

		// scan through all pixels
		for(int x = 0; x < width; ++x) {
			for(int y = 0; y < height; ++y) {
				// get pixel color
				pixel = src.getPixel(x, y);
				// apply filtering on each channel R, G, B
				A = Color.alpha(pixel);
				R = (int)(Color.red(pixel) * red);
				G = (int)(Color.green(pixel) * green);
				B = (int)(Color.blue(pixel) * blue);
				// set new color pixel to output bitmap
				bmOut.setPixel(x, y, Color.argb(A, R, G, B));
			}
		}

		// return final image
		return bmOut;
	}
	
	public static Bitmap applyGaussianBlur(Bitmap src) {
		double[][] GaussianBlurConfig = new double[][] {
			{ 1, 2, 1 },
			{ 2, 4, 2 },
			{ 1, 2, 1 }
		};
		ConvolutionMatrix convMatrix = new ConvolutionMatrix(3);
		convMatrix.applyConfig(GaussianBlurConfig);
		convMatrix.Factor = 16;
		convMatrix.Offset = 0;
		return ConvolutionMatrix.computeConvolution3x3(src, convMatrix);
	}
	
	public static Bitmap sharpen(Bitmap src, double weight) {
		double[][] SharpConfig = new double[][] {
			{ 0 , -2    , 0  },
			{ -2, weight, -2 },
			{ 0 , -2    , 0  }
		};
		ConvolutionMatrix convMatrix = new ConvolutionMatrix(3);
		convMatrix.applyConfig(SharpConfig);
		convMatrix.Factor = weight - 8;
		return ConvolutionMatrix.computeConvolution3x3(src, convMatrix);
	}


	public static Bitmap applyMeanRemoval(Bitmap src) {
		double[][] MeanRemovalConfig = new double[][] {
			{ -1 , -1, -1 },
			{ -1 ,  9, -1 },
			{ -1 , -1, -1 }
		};
		ConvolutionMatrix convMatrix = new ConvolutionMatrix(3);
		convMatrix.applyConfig(MeanRemovalConfig);
		convMatrix.Factor = 1;
		convMatrix.Offset = 0;
		return ConvolutionMatrix.computeConvolution3x3(src, convMatrix);
	}
	
	public static Bitmap applyShadingFilter(Bitmap source, int shadingColor) {
		// get image size
		int width = source.getWidth();
		int height = source.getHeight();
		int[] pixels = new int[width * height];
		// get pixel array from source
		source.getPixels(pixels, 0, width, 0, 0, width, height);

		int index = 0;
		// iteration through pixels
		for(int y = 0; y < height; ++y) {
			for(int x = 0; x < width; ++x) {
				// get current index in 2D-matrix
				index = y * width + x;
				// AND
				pixels[index] &= shadingColor;
			}
		}
		// output bitmap
		Bitmap bmOut = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
		bmOut.setPixels(pixels, 0, width, 0, 0, width, height);
		return bmOut;
	}
	
	public static Bitmap smooth(Bitmap src, double value) {
		ConvolutionMatrix convMatrix = new ConvolutionMatrix(3);
		convMatrix.setAll(1);
		convMatrix.Matrix[1][1] = value;
		convMatrix.Factor = value + 8;
		convMatrix.Offset = 1;
		return ConvolutionMatrix.computeConvolution3x3(src, convMatrix);
	}

	public static Bitmap emboss(Bitmap src) {
		double[][] EmbossConfig = new double[][] {
			{ -1 ,  0, -1 },
			{  0 ,  4,  0 },
			{ -1 ,  0, -1 }
		};
		ConvolutionMatrix convMatrix = new ConvolutionMatrix(3);
		convMatrix.applyConfig(EmbossConfig);
		convMatrix.Factor = 1;
		convMatrix.Offset = 127;
		return ConvolutionMatrix.computeConvolution3x3(src, convMatrix);
	}

	public static Bitmap engrave(Bitmap src) {
		ConvolutionMatrix convMatrix = new ConvolutionMatrix(3);
		convMatrix.setAll(0);
		convMatrix.Matrix[0][0] = -2;
		convMatrix.Matrix[1][1] = 2;
		convMatrix.Factor = 1;
		convMatrix.Offset = 95;
		return ConvolutionMatrix.computeConvolution3x3(src, convMatrix);
	}

	public static Bitmap createSepiaToningEffect(Bitmap src, int depth, double red, double green, double blue) {
		// image size
		int width = src.getWidth();
		int height = src.getHeight();
		// create output bitmap
		Bitmap bmOut = Bitmap.createBitmap(width, height, src.getConfig());
		// constant grayscale
		final double GS_RED = 0.3;
		final double GS_GREEN = 0.59;
		final double GS_BLUE = 0.11;
		// color information
		int A, R, G, B;
		int pixel;

		// scan through all pixels
		for(int x = 0; x < width; ++x) {
			for(int y = 0; y < height; ++y) {
				// get pixel color
				pixel = src.getPixel(x, y);
				// get color on each channel
				A = Color.alpha(pixel);
				R = Color.red(pixel);
				G = Color.green(pixel);
				B = Color.blue(pixel);
				// apply grayscale sample
				B = G = R = (int)(GS_RED * R + GS_GREEN * G + GS_BLUE * B);

				// apply intensity level for sepid-toning on each channel
				R += (depth * red);
				if(R > 255) { R = 255; }

				G += (depth * green);
				if(G > 255) { G = 255; }

				B += (depth * blue);
				if(B > 255) { B = 255; }

				// set new pixel color to output image
				bmOut.setPixel(x, y, Color.argb(A, R, G, B));
			}
		}

		// return final image
		return bmOut;
	}

}