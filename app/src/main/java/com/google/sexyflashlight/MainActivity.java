package com.google.sexyflashlight;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.AutoFocusCallback;
import android.os.PowerManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.os.PowerManager.WakeLock;
import android.widget.TextView;

import android.widget.CompoundButton;

import java.util.List;


public class MainActivity extends ActionBarActivity {
    private boolean lightOn;
    private Camera mCamera;
    private View flashButton;
    private WakeLock wakeLock;
    TextView debugText;
    private static final String WAKE_LOCK_TAG = "TORCH_WAKE_LOCK";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        debugText = (TextView)findViewById(R.id.debugText);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void onToggleClicked(View view) {
        boolean on = ((ToggleButton) view).isChecked();

        if (on) {
            debugText.setText("ON!!!!!!!");
            ledon();
        } else {
            debugText.setText("OFF!!!!!!!");
            ledoff();
        }
    }
    Camera cam;
    void ledon() {
        cam = Camera.open();
        Parameters params = cam.getParameters();
        params.setFlashMode(Parameters.FLASH_MODE_ON);
        cam.setParameters(params);
        cam.startPreview();
        cam.autoFocus(new AutoFocusCallback() {
            public void onAutoFocus(boolean success, Camera camera) {
            }
        });
    }
    void ledoff() {
        cam.stopPreview();
        cam.release();
    }
}
