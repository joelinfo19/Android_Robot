package com.example.newrobot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfInt;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.RotatedRect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgproc.Moments;
import org.opencv.objdetect.CascadeClassifier;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    static {
        if (OpenCVLoader.initDebug()) {
            Log.d("MainActivity: ", "Opencv is loaded");
        } else {
            Log.d("MainActivity: ", "Opencv failed to load");
        }
    }

    private Button camera_button;
    EditText et1;
    EditText et2;
    String st1;
    String st2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        camera_button = findViewById(R.id.button);
        et1 = findViewById(R.id.editTextTextPersonName);
        et2 = findViewById(R.id.editTextTextPersonName2);

        camera_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CameraActivityMain2Activity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                st1 = et1.getText().toString();
                st2 = et2.getText().toString();
                i.putExtra("value1", st1);
                i.putExtra("value2", st2);
                startActivity(i);

            }
        });

    }
}
//    private static final String TAG="MainActivity";
//
//    private Mat mRgba;
//    private Mat mGray;
//    private Mat mGray2;
//    private CameraBridgeViewBase mOpenCvCameraView;
//    private CascadeClassifier cascadeClassifier_eye;
//    private CascadeClassifier cascadeClassifier;
//    private Socket socket;
//    private PrintWriter printWriter;
//    private double t_hmin;
//    private double t_smin;
//    private double t_vmin;
//    private double t_hmax;
//    private double t_smax;
//    private double t_vmax;
//    private double o_hmin;
//    private double o_smin;
//    private double o_vmin;
//    private double o_hmax;
//    private double o_smax;
//    private double o_vmax;
//    Scalar scalarLow,scalarHigh;
//    Mat mat1,mat2;
//    private BaseLoaderCallback mLoaderCallback =new BaseLoaderCallback(this) {
//        @Override
//        public void onManagerConnected(int status) {
//            switch (status){
//                case LoaderCallbackInterface
//                        .SUCCESS:{
//                    Log.i(TAG,"OpenCv Is loaded");
//                    mOpenCvCameraView.enableView();
//                }
//                default:
//                {
//                    super.onManagerConnected(status);
//
//                }
//                break;
//            }
//        }
//    };
//    public MainActivity(){
//        Log.i(TAG,"Instantiated new "+this.getClass());
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
//        scalarLow=new Scalar(100,122,10);
//        scalarHigh=new Scalar(120,250,255);
//        int MY_PERMISSIONS_REQUEST_CAMERA=0;
//        // if camera permission is not given it will ask for it on device
//        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA)
//                == PackageManager.PERMISSION_DENIED){
//            ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
//        }
//
//        setContentView(R.layout.activity_main);
//
//        mOpenCvCameraView=(CameraBridgeViewBase) findViewById(R.id.frame_Surface);
//        mOpenCvCameraView.setVisibility(SurfaceView.VISIBLE);
//        mOpenCvCameraView.setCvCameraViewListener(this);
//    }
//    @Override
//    protected void onResume() {
//        super.onResume();
//        if (OpenCVLoader.initDebug()){
//            //if load success
//            Log.d(TAG,"Opencv initialization is done");
//            mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
//        }
//        else{
//            //if not loaded
//            Log.d(TAG,"Opencv is not loaded. try again");
//            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_4_0,this,mLoaderCallback);
//        }
//    }
//    @Override
//    protected void onPause() {
//        super.onPause();
//        if (mOpenCvCameraView !=null){
//            mOpenCvCameraView.disableView();
//        }
//    }
//    public void onDestroy(){
//        super.onDestroy();
//        if(mOpenCvCameraView !=null){
//            mOpenCvCameraView.disableView();
//        }
//
//    }
//
//    public void onCameraViewStarted(int width ,int height){
//        //tamanio de la matriz y cvtype es el tipo de datos en abajo se usa unsigned char 8 bit long sin firmar
//        // y cada pixel tiene 4 de estos para formar los 4 canales
//   mRgba=new Mat(width,height, CvType.CV_8UC3);//CV_[The number of bits per item][Signed or Unsigned][Type Prefix]C[The channel number]
//   mGray =new Mat(width,height,CvType.CV_8UC3);
//   mGray2=new Mat(width,height,CvType.CV_8UC3);
//    }
//    public void onCameraViewStopped(){
//        mRgba.release();
//    }
//    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame){
//       // mRgba=inputFrame.rgba();
//        //mGray=inputFrame.gray();
////        mRgba=inputFrame.rgba();
////        mat1=inputFrame.rgba().t();
////        Core.flip(mRgba, mat1, 1);
////        Core.flip(mat1, mat1, 2);
////        imgProcess(mat1.getNativeObjAddr(),mGray.getNativeObjAddr());
//// 1    Imgproc.cvtColor(inputFrame.rgba(),mRgba,Imgproc.COLOR_BGR2HSV);
////  1   Core.inRange(mRgba,scalarLow,scalarHigh,mGray);
//       // Imgproc.rectangle(mGray,);
//        t_hmin=(50*180)/360;
//        t_smin=(65*255)/100;
//        t_vmin=(50*255)/100;
//        t_hmax=(120*180)/360;
//        t_smax=(100*255)/100;
//        t_vmax=(100*255)/100;
//        o_hmin=(5*180)/360;
//        o_smin=(60*255)/100;
//        o_vmin=(60*255)/100;
//        o_hmax=(45*180)/360;
//        o_smax=(100*255)/100;
//        o_vmax=(100*255)/100;
//
//        mRgba = inputFrame.rgba();
//        //Mat src1 = mRgba.clone();
//        Imgproc.cvtColor (mRgba, mRgba, Imgproc.COLOR_RGB2HSV);
//        //Imgproc.medianBlur (src, src, 5);
//
//        //Mat mat2;
//    //    Core.inRange (mRgba, new Scalar(20,122,10), new Scalar(75,255,255), mGray);//green
//        Core.inRange (mRgba, new Scalar(t_hmin,t_smin,t_vmin), new Scalar(t_hmax,t_smax,t_vmax), mGray);//green
//
//        mat1=mGray;
//     //   Core.inRange (mRgba, new Scalar(5,122,10), new Scalar(20,250,255), mGray2);//orange
//        Core.inRange (mRgba, new Scalar(o_hmin,o_smin,o_vmin), new Scalar(o_hmax,o_smax,o_vmax), mGray2);//orange
//
//        mat2=mGray2;
//        dibujar(mat1,new Scalar(255,0,0));
//        dibujar(mat2,new Scalar(0,255,255));


//        dibujar(mat1,new Scalar(0,255,0));
//          dibujar(mat2,new Scalar(0,255,255));
//        Mat hierarchy = Mat.zeros (new Size(5,5), CvType.CV_8UC1);
//        List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
//        Imgproc.findContours (mat1, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_TC89_L1);
//        Scalar color = new Scalar (200,20,100);
//
//        Imgproc.moments(new Mat());
//        int i = 0;
//     //   MatOfPoint i;
//        int index = -1;
//        double area = 0;
//    //    Point p=new Point(1,3);
//        for (i = 0;i<contours.size ();i ++)
//       // for (MatOfPoint contour : contours)
//        {
//            double tmp = Imgproc.contourArea (contours.get (i));
//            if (tmp>3000) {
//                Moments m=Imgproc.moments(contours.get(i));
//                if(m.m00==0)
//                    m.m00=1;
//                int x=(int)(m.m10/m.m00);
//                int y=(int)(m.m01/m.m00);
//                Point p=new Point(x,y);
//                Imgproc.circle(mRgba,p,7,new Scalar(0,255,0),-1);
//                Imgproc.putText(mRgba, String.format("hola", p),new Point(x+10,y),Core.FONT_HERSHEY_SIMPLEX,0.75,new Scalar(0, 255, 0, 255));
//                //MatOfInt hull = new MatOfInt();
//                //Imgproc.convexHull(contours.get(i),hull);
//
//
//               // Point[] contourArray = i;
////                Point[] hullPoints = new Point[hull.rows()];
////                List<Integer> contours2 = hull.toList();
////                List<MatOfPoint> hullList = new ArrayList<>();
////
////
////                for (int j = 0; j < contours2.size(); j++) {
////                    hullPoints[i] = contourArray[contours2.get(i)];
////                }
////                hullList.add(new MatOfPoint(hullPoints));
//                Imgproc.drawContours(mRgba,contours,0,new Scalar(255,0,0),3);
////                area = tmp;
////                index = i;
//            }
//        }





//        if (index!= -1) {
//            MatOfPoint ptmat = contours.get (index);
//            color = new Scalar (0,200,0);
//            MatOfPoint2f ptmat2 = new MatOfPoint2f (ptmat.toArray ());
//            RotatedRect bbox = Imgproc.minAreaRect (ptmat2);
//            Imgproc.circle(mat1, bbox.center, 5, color, -1);
//        }


        //new
//        Core.inRange (src, new Scalar(20,122,10), new Scalar(75,255,255), src);
//        Mat hierarchy2 = Mat.zeros (new Size(5,5), CvType.CV_8UC1);
//        List<MatOfPoint> contours2 = new ArrayList<MatOfPoint>();
//        Imgproc.findContours (src, contours2, hierarchy2, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_TC89_L1);
//        Scalar color2 = new Scalar (200,20,100);
//
//        int i2 = 0;
//        int index2 = -1;
//        double area2 = 0;
//        for (i2 = 0;i2<contours2.size ();i2 ++)
//        {
//            double tmp2 = Imgproc.contourArea (contours2.get (i2));
//            if (area2<tmp2) {
//                area2 = tmp2;
//                index2 = i2;
//            }
//        }
//        if (index2!= -1) {
//            MatOfPoint ptmat = contours2.get (index2);
//            color2 = new Scalar (0,200,0);
//            MatOfPoint2f ptmat21 = new MatOfPoint2f (ptmat.toArray ());
//            RotatedRect bbox2 = Imgproc.minAreaRect (ptmat21);
//            Imgproc.circle(src, bbox2.center, 5, color2, -1);
//        }
        //Imgproc.drawContours();
        //Imgproc.cvtColor (mRgba, mRgba, Imgproc.COLOR_HSV2RGB);
       // Log.d(TAG, "dibujar: tennis");
//        return mRgba;

   //1 return mGray;
   //     return mRgba;

//    }
//    public void dibujar(Mat a,Scalar b){
//        Mat hierarchy = Mat.zeros (new Size(5,5), CvType.CV_8UC1);
//        List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
//        Imgproc.findContours (a, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_TC89_L1);
//        Scalar color = new Scalar (200,20,100);
//
//        Imgproc.moments(new Mat());
//        int i = 0;
//        //   MatOfPoint i;
//        int index = -1;
//        double area = 0;
//        //    Point p=new Point(1,3);
//        for (i = 0;i<contours.size ();i ++)
//        // for (MatOfPoint contour : contours)
//        {
//            double tmp = Imgproc.contourArea (contours.get (i));
//            if (tmp>3000) {
//                Moments m=Imgproc.moments(contours.get(i));
//                if(m.m00==0)
//                    m.m00=1;
//                int x=(int)(m.m10/m.m00);
//                int y=(int)(m.m01/m.m00);
//                Point p=new Point(x,y);
//                Imgproc.circle(mRgba,p,7,new Scalar(0,255,0),-1);
//                /*TODO if for name green
//                *Imgproc.putText(mRgba, String.format("{%f}{%f}", p.x,p.y),new Point(x+10,y),Core.FONT_HERSHEY_SIMPLEX,0.75,new Scalar(0, 255, 0, 255));
//                *else:
//                * another color
//                *  */
//                Imgproc.putText(mRgba, String.format("{%f}{%f}", p.x,p.y),new Point(x+10,y),Core.FONT_HERSHEY_SIMPLEX,0.75,new Scalar(0, 255, 0, 255));
//                //MatOfInt hull = new MatOfInt();
//                //Imgproc.convexHull(contours.get(i),hull);
//
//
//                // Point[] contourArray = i;
////                Point[] hullPoints = new Point[hull.rows()];
////                List<Integer> contours2 = hull.toList();
////                List<MatOfPoint> hullList = new ArrayList<>();
////
////
////                for (int j = 0; j < contours2.size(); j++) {
////                    hullPoints[i] = contourArray[contours2.get(i)];
////                }
////                hullList.add(new MatOfPoint(hullPoints));
//                Imgproc.drawContours(mRgba,contours,0,b,3);
//                if(new Scalar(255, 0, 0).equals(b)){
//                    /*TODO
//                    * only color
//                    * */
//                    Log.d(TAG, "dibujar: tennis");
//                    new Thread(new Runnable() {
//                        @Override
//                        public void run() {
//                            try {
//                                socket=new Socket("192.168.0.108",80);
//                                printWriter=new PrintWriter(socket.getOutputStream());
//                                printWriter.write('t' );
//                                printWriter.flush();
//                                printWriter.close();
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//
//                        }
//                    }).start();
//                }
//                if(new Scalar(0, 255, 255).equals(b)){
//
//                    /*TODO
//                     * only color
//                     * */
//                    Log.d(TAG, "dibujar: naranja");
//                    new Thread(new Runnable() {
//                        @Override
//                        public void run() {
//                            try {
//                                socket=new Socket("192.168.0.108",80);
//                                printWriter=new PrintWriter(socket.getOutputStream());
//                                printWriter.write('n' );
//                                printWriter.flush();
//                                printWriter.close();
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//
//                        }
//                    }).start();
//                }
////                area = tmp;
////                index = i;
//            }
//        }
//    }
