package com.example.mapuserselectlocation;


import java.util.List;

import android.app.ActionBar.LayoutParams;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;



public class ThirdActivity extends MapActivity {
	
	private Button btnSwitcher;
    private Button btnSwitcher2;
    private Button btnSwitcher3;
    private Button btnSwitcher4;
    
    MapView mapView;
    MapController mc;
    GeoPoint p;

    @Override
    public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    
 // Get hold of your switcher button
    btnSwitcher = (Button) findViewById(R.id.button1);
    btnSwitcher2 = (Button) findViewById(R.id.button2);
    btnSwitcher3 = (Button) findViewById(R.id.button3);
    btnSwitcher4 = (Button) findViewById(R.id.button4);

    // Set an onClick listener so the button will switch to the other activity
    btnSwitcher.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
            // Switch to Other Activity
            Intent myIntent = new Intent(v.getContext(), MainActivity.class);
            startActivityForResult(myIntent, 0);
        }
    });
    
    
    
    btnSwitcher2.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
            // Switch to Other Activity
            Intent myIntent = new Intent(v.getContext(), SecondActivity.class);
            startActivityForResult(myIntent, 0);
        }
    });
    
    btnSwitcher3.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
            // Switch to Other Activity
            Intent myIntent = new Intent(v.getContext(), ThirdActivity.class);
            startActivityForResult(myIntent, 0);
        }
    });
    
    btnSwitcher4.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
            // Switch to Other Activity
            Intent myIntent = new Intent(v.getContext(), FourthActivity.class);
            startActivityForResult(myIntent, 0);
        }
    });
    
    
    
    mapView = (MapView) findViewById(R.id.mapview);
    LinearLayout zoomLayout = (LinearLayout)findViewById(R.id.zoom);  
    View zoomView = mapView.getZoomControls(); 

    zoomLayout.addView(zoomView, 
        new LinearLayout.LayoutParams(
            LayoutParams.WRAP_CONTENT, 
            LayoutParams.WRAP_CONTENT)); 
    mapView.displayZoomControls(true);

    mc = mapView.getController();        
    String coordinates[] = {"19.432608","-99.133208"};
    double lat = Double.parseDouble(coordinates[0]);
    double lng = Double.parseDouble(coordinates[1]);

    p = new GeoPoint(
        (int) (lat * 1E6), 
        (int) (lng * 1E6));

    mc.animateTo(p);
    mc.setZoom(9); 
    //---Add a location marker---
    MapOverlay mapOverlay = new MapOverlay();
    List<Overlay> listOfOverlays = mapView.getOverlays();
    listOfOverlays.clear();
    listOfOverlays.add(mapOverlay);        
    mapView.invalidate();
    }

    class MapOverlay extends com.google.android.maps.Overlay
{
    @Override
    public boolean draw(Canvas canvas, MapView mapView, 
    boolean shadow, long when) 
    {
        super.draw(canvas, mapView, shadow);                   

        //---translate the GeoPoint to screen pixels---
        Point screenPts = new Point();
        mapView.getProjection().toPixels(p, screenPts);
        //--------------draw circle----------------------            

        Point pt=mapView.getProjection().toPixels(p,screenPts);

        Paint circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        circlePaint.setColor(0x30000000);
        circlePaint.setStyle(Style.FILL_AND_STROKE);
        canvas.drawCircle(screenPts.x, screenPts.y, 10, circlePaint);           

        //---add the marker---
        Bitmap bmp = BitmapFactory.decodeResource(
            getResources(), R.drawable.beachflag);            
        canvas.drawBitmap(bmp, screenPts.x, screenPts.y-bmp.getHeight(), null);              
        super.draw(canvas,mapView,shadow);

        return true;

    }
}
@Override
protected boolean isRouteDisplayed() {
    // TODO Auto-generated method stub
    return false;
}
}