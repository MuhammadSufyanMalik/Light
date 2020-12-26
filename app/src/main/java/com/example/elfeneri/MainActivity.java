package com.example.elfeneri;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import android.widget.ImageButton;

import androidx.annotation.RequiresApi;

public class MainActivity extends Activity {
    private CameraManager mCameraManager;
    private String mCameraId;
    private ImageButton mTorchOnOffButton;
    private Boolean isTorchOn = false;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_main);

        new mybackground().execute();
        finish();

    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    class mybackground extends AsyncTask<Void, Void, String>
    {


        protected void onPreExecute ()
        {
            super.onPreExecute();
//
            Boolean isFlashAvailable = getApplication().getPackageManager()
                    .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);

            if (!isFlashAvailable) {

                AlertDialog alert = new AlertDialog.Builder(getApplicationContext()).create();
                alert.setTitle("Hata Mesaj!!");
                alert.setMessage(getString(R.string.Mesaj));
                alert.setButton(DialogInterface.BUTTON_POSITIVE, "Tamam",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // closing the application
                                System.exit(0);
                            }
                        });
                alert.show();

            }

        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        protected String doInBackground(Void...arg0)
        {

            mCameraManager = (CameraManager) getApplicationContext().getSystemService(getApplicationContext().CAMERA_SERVICE);
            try {
                mCameraId = mCameraManager.getCameraIdList()[0];
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }

            try {
                if (isTorchOn) {
                    turnOffFlashLight();
                    isTorchOn = false;
                } else {
                    turnOnFlashLight();
                    isTorchOn = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


//            finish();

            return "";
        }

        protected void onPostExecute(String msg)
        {

            if(isTorchOn){
                turnOffFlashLight();
            }
            else {
                turnOnFlashLight();
            }
        }
    }

    public void turnOnFlashLight() {

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mCameraManager.setTorchMode(mCameraId, true);
//                playOnOffSound();
//                mTorchOnOffButton.setImageResource(R.drawable.on);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void turnOffFlashLight() {

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mCameraManager.setTorchMode(mCameraId, false);
//                playOnOffSound();
//                mTorchOnOffButton.setImageResource(R.drawable.off);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}