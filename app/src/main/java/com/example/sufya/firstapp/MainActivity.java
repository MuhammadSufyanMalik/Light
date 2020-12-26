package com.example.sufya.firstapp;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.AsyncTask;
import android.os.Build;

import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


import static java.security.AccessController.getContext;

public class MainActivity extends Activity {

    private CameraManager mCameraManager;
    private String mCameraId;
    private ImageButton mTorchOnOffButton;
    private Boolean isTorchOn = false;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        new mybackground().execute();
        finish();


//
//        Boolean isFlashAvailable = this.getPackageManager()
//                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
//
//        if (!isFlashAvailable) {
//
//            AlertDialog alert = new AlertDialog.Builder(this).create();
//            alert.setTitle("Error !!");
//            alert.setMessage("Your device doesn't support flash light!");
//            alert.setButton(DialogInterface.BUTTON_POSITIVE, "OK",
//                    new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int which) {
//                            // closing the application
//                            System.exit(0);
//                        }
//                    });
//            alert.show();
//
//        }
//        mCameraManager = (CameraManager) this.getSystemService(this.CAMERA_SERVICE);
//        try {
//            mCameraId = mCameraManager.getCameraIdList()[0];
//        } catch (CameraAccessException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            if (isTorchOn) {
//                turnOffFlashLight();
//                isTorchOn = false;
//            } else {
//                turnOnFlashLight();
//                isTorchOn = true;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//       finish();




    }
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
                alert.setTitle("Error !!");
                alert.setMessage("Your device doesn't support flash light!");
                alert.setButton(DialogInterface.BUTTON_POSITIVE, "OK",
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

//    private void playOnOffSound(){
//
//        mp = MediaPlayer.create(this,R.raw.flash_sound);
//        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//
//            @Override
//            public void onCompletion(MediaPlayer mp) {
//                // TODO Auto-generated method stub
//                mp.release();
//            }
//        });
//        mp.start();
//    }


//    public void onStop() {
//        super.onStop();
//        if(isTorchOn){
//            turnOffFlashLight();
//        }
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        if(isTorchOn){
//            turnOffFlashLight();
//        }
//    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        if(isTorchOn){
//            turnOffFlashLight();
//        }
//        else {
//            turnOnFlashLight();
//        }
//
//    }



    }


