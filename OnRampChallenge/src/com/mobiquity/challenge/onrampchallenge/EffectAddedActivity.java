package com.mobiquity.challenge.onrampchallenge;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

 

public class EffectAddedActivity extends Activity {

    String path;
    String effect_choosen;
    ImageView changed;
    Bitmap out;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.final_view);
 
        Intent i=getIntent();
        path=i.getStringExtra("path");
        effect_choosen=i.getStringExtra("effect");
        
        changed=(ImageView)findViewById(R.id.view);
        Log.i("path of file in effects added.....................", path);
        Log.i("effect chosen in effects added.....................", effect_choosen);

        Bitmap thumbnail = (BitmapFactory.decodeFile(path));
        if(effect_choosen.equalsIgnoreCase("Red Filter"))
        {
           out=addEffect(thumbnail,5,10.0,0.0,0.0);  //only red
        }
        else if(effect_choosen.equalsIgnoreCase("Green Filter"))
        {
            out=addEffect(thumbnail,5,0.0,10.0,0.0);  //only green
        }
        else if(effect_choosen.equalsIgnoreCase("Blue Filter"))
        {
            out=addEffect(thumbnail,5,0.0,0.0,10.0);  //only blue
        }
        else if(effect_choosen.equalsIgnoreCase("Depth"))
        {
            out=addEffect(thumbnail,15,5.0,0.0,10.0);  //red,green,no blue, depth increased
        }
        else if(effect_choosen.equalsIgnoreCase("Gray"))
        {
            out=addEffect(thumbnail,5,15.0,15.0,15.0);  //saturated
        }
        changed.setImageBitmap(out);
    }
    
    public static Bitmap addEffect(Bitmap src, int depth, double red, double green, double blue) {

    int width = src.getWidth();
    int height = src.getHeight();

    Bitmap finalBitmap = Bitmap.createBitmap(width, height, src.getConfig());
    final double grayScale_Red = 0.3;
    final double grayScale_Green = 0.59;
    final double grayScale_Blue = 0.11;

    int channel_aplha, channel_red, channel_green, channel_blue;
    int pixel;
    for(int x = 0; x < width; ++x) {
    	for(int y = 0; y < height; ++y) {
    		pixel = src.getPixel(x, y);               
    		channel_aplha = Color.alpha(pixel);
            channel_red = Color.red(pixel);
            channel_green = Color.green(pixel);
            channel_blue = Color.blue(pixel);
            channel_blue = channel_green = channel_red = (int)(grayScale_Red * channel_red + grayScale_Green * channel_green + grayScale_Blue * channel_blue);
            channel_red += (depth * red);
            if(channel_red > 255)
            {
            	channel_red = 255;
            }
            channel_green += (depth * green);
            if(channel_green > 255)
            {
            	channel_green = 255;
            }
            channel_blue += (depth * blue);
            if(channel_blue > 255)
            {
            	channel_blue = 255;
            }
            finalBitmap.setPixel(x, y, Color.argb(channel_aplha, channel_red, channel_green, channel_blue));
    	}
    }     
    return finalBitmap;
  }
}