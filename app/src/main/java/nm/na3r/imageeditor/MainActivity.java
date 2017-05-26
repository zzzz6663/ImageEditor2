package nm.na3r.imageeditor;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.GradientDrawable;
import android.icu.util.Calendar;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.FloatMath;
import android.graphics.Shader;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.BitmapShader;

import com.alexvasilkov.gestures.Settings;
import com.alexvasilkov.gestures.views.GestureFrameLayout;
import com.alexvasilkov.gestures.views.GestureImageView;
import com.rtugeek.android.colorseekbar.ColorSeekBar;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;


import editview.StickerImageView;
import editview.StickerTextView;
import swipe_mode.SwipePage;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    private Matrix savedMatrix = new Matrix();
    // we can be in one of these 3 states
    private static final int NONE = 0;
    private static final int DRAG = 1;
    private static final int ZOOM = 2;
    private int mode = NONE;
    // remember some things for zooming
    private PointF start = new PointF();
    private PointF mid = new PointF();
    private float oldDist = 1f;
    private float d = 0f;
      float newRot = 0f;
    private float[] lastEvent = null;
      ImageView view, fin;
       Bitmap bmap;
    int pagenumber;
    LinearLayout ln, mainLN,Lnpic1,root2;
    LinearLayout root1;
    ImageView img1, img2, img3, img4,select1,select2,select3,select4,select_in2_1,select_in2_2,select_in2_3,select_in2_4;
    Matrix matrix = new Matrix();
    float scale =1f;
    ScaleGestureDetector  scaleGestureDetector ;

    private static final String IMAGE_DIRECTORY = "/demonuts";
    private int GALLERY = 1, CAMERA = 2;

    boolean attach = true;
    boolean past = false;
    View text;
    Intent intent;
    int sel;
    int inlayout = 1;
    int positions;
    boolean cliked;
    ArrayList<Bitmap> Images1 = new ArrayList<>();
    ArrayList<Bitmap> Images2 = new ArrayList<>();
    ArrayList<Bitmap> Images3 = new ArrayList<>();
    ArrayList<Bitmap> Images4 = new ArrayList<>();
    ArrayList<Bitmap> Images5 = new ArrayList<>();
    ArrayList<Bitmap> Images6 = new ArrayList<>();
    ArrayList<Bitmap> Images7 = new ArrayList<>();
    ArrayList<Bitmap> Images8 = new ArrayList<>();
    ArrayList<Bitmap> Images9 = new ArrayList<>();
    private GestureDetector gestureDetector;
    SeekBar sbRadius ,sbHW;
    GradientDrawable gd = new GradientDrawable();
    GradientDrawable gd2 = new GradientDrawable();
    int radius;
      int width,width2,height2;
       int height;

      ViewGroup LL_in1,LL_in2 ;
      View child1,child2,LL_in2_Child_Radius ,LL_in2_Color_picker ;
    boolean childechange;
    Bitmap bitmap;
    FrameLayout Ln1;
    RoundedBitmapDrawable RBD;
    ColorSeekBar colorSeekBar;
    boolean colorSeekBarrun;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gestureDetector = new GestureDetector(this, new SingleTapConfirm());

        pagenumber = getIntent().getIntExtra("pagenumber", 1);

        Ln1 = (FrameLayout) findViewById(R.id.canvasView);
        ViewStub stub = (ViewStub) findViewById(R.id.stub);
        LL_in1 = (ViewGroup)findViewById(R.id.LL_in1);
        LL_in2 = (ViewGroup)findViewById(R.id.LL_in2);

            child1 = LayoutInflater.from(this).inflate(R.layout.in_layout1,  null, false);
            child2 = LayoutInflater.from(this).inflate(R.layout.in_layout2,  null, false);
        LL_in2_Child_Radius = LayoutInflater.from(this).inflate(R.layout.in_layout2_seekbar_radius,  LL_in2, false);
        LL_in2_Color_picker = LayoutInflater.from(this).inflate(R.layout.in_layout2_color_picker,  LL_in2, false);
        LL_in1.addView(child1);
        // add a stickerText to canvas









        //in baray ezafe kardane matn

        final StickerTextView tv_sticker = new StickerTextView(MainActivity.this);
        tv_sticker.setText("nkDroid");
        Ln1.addView(tv_sticker);

        //in baray ezafe kardane aks

        final StickerImageView Iv_sticker = new StickerImageView(MainActivity.this);
        Iv_sticker.setImageResource(R.drawable.a10);
        Ln1.addView(Iv_sticker);

















        switch (pagenumber) {
            case 1:
                stub.setLayoutResource(R.layout.pic1);
                break;
            case 2:
                stub.setLayoutResource(R.layout.pic2);
                break;
            case 3:
                stub.setLayoutResource(R.layout.pic3);
                break;
            case 4:
                stub.setLayoutResource(R.layout.pic4);
                break;
            case 5:
                stub.setLayoutResource(R.layout.pic5);
                break;

        }


        View inflated = stub.inflate();




        root1 = (LinearLayout) findViewById(R.id.root1);
//        root1.setBackgroundColor(Color.parseColor("#000000"));
//        root2 = (LinearLayout) findViewById(R.id.root2);


        mainLN = (LinearLayout) findViewById(R.id.mainLN);

        select1 = (ImageView) findViewById(R.id.select1);
        select2 = (ImageView) findViewById(R.id.select2);
        select3 = (ImageView) findViewById(R.id.select3);
        select4 = (ImageView) findViewById(R.id.select4);



        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);
        img3 = (ImageView) findViewById(R.id.img3);
        img4 = (ImageView) findViewById(R.id.img4);
        select1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, SwipePage.class);
                startActivity(intent);
            }
        });
        select2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              inlayout=2;
                LL_in1.removeAllViews();

                LL_in1.addView(child2);
                childechange=true;
                select_in2_1setonclick();
                select_in2_2setonclick();

            }
        });






        //              img1.setImageBitmap(Images1.get(1));
//                                if (attach){
//                    tv_sticker.setControlItemsHidden(true);}
//                else {
//                    tv_sticker.setControlItemsHidden(false);
//                }
//                attach =!(attach);



        img1.setOnTouchListener(MainActivity.this);

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!cliked){
                    if (Images1.size()==0){
                        sel = 1;
                        showPictureDialog();
                    }else {
                        positions=1;
                        openDialog();

                    }
                }


            }
        });





    }
    public  void select_in2_2setonclick(){
        select_in2_2 = (ImageView) findViewById(R.id.select_in2_2);

        select_in2_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LL_in2.removeAllViews();
                LL_in2.addView(LL_in2_Color_picker);
                colorSeekBarrun =true;

                colorSeekBar =(ColorSeekBar)findViewById(R.id.colorSlider);
                colorSeekBar.setMaxPosition(100);
                colorSeekBar.setColorSeeds(R.array.material_colors); // material_colors is defalut included in res/color,just use it.
                colorSeekBar.setColorBarPosition(10); //0 - maxValue
                colorSeekBar.setAlphaBarPosition(10); //0 - 255

                colorSeekBar.setBarHeight(5); //5dpi
                colorSeekBar.setThumbHeight(30); //30dpi
                colorSeekBar.setBarMargin(10);
                Log.i("log","colorSeekBarrun is"+colorSeekBarrun);
                if (colorSeekBarrun){
                    Log.i("log","colorSeekBarrun is"+colorSeekBarrun);
                    colorSeekBar.setOnColorChangeListener(new ColorSeekBar.OnColorChangeListener() {
                        @Override
                        public void onColorChangeListener(int colorBarPosition, int alphaBarPosition, int color) {
                            root1.setBackgroundColor(color);

                        }
                    });}

            }
        });




    };
    public void select_in2_1setonclick(){
        select_in2_1 = (ImageView) findViewById(R.id.select_in2_1);

        select_in2_3 = (ImageView) findViewById(R.id.select_in2_3);
        select_in2_4 = (ImageView) findViewById(R.id.select_in2_4);

     if (pagenumber>=1){
         root1.post(new Runnable() {
             @Override
             public void run() {
                 width = root1.getWidth();
                 height = root1.getHeight();
                 Log.i("log","width is  "+width);
                 Log.i("log","height is  "+height);

             }
         });
     }
       if (pagenumber>=2){
           root2.post(new Runnable() {
               @Override
               public void run() {
                   width2 = root2.getWidth();
                   height2 = root2.getHeight();
                   Log.i("log","width is  "+width);
                   Log.i("log","height is  "+height);

               }
           });
       }

        if (childechange){
        select_in2_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("log","select_in2_1 is clicked");
                LL_in2.removeAllViews();
                LL_in2.addView(LL_in2_Child_Radius);
                sbRadius= (SeekBar)findViewById(R.id.sbRadius);
                sbHW= (SeekBar)findViewById(R.id.sbHW);




                sbRadius.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        radius =progress*5;

                        GradientDrawable gd = new GradientDrawable();
                        gd.setCornerRadius(radius);
                        gd.setStroke(2, Color.WHITE);//

                        root1.setBackground(gd);




















                        //                        gd2.setCornerRadius(radius);
//                        gd2.setStroke(2, Color.WHITE);
//                        root1.bringToFront();


//                        Bitmap imageRounded = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
//                        Canvas canvas = new Canvas(imageRounded);
//                        Paint mpaint = new Paint();
//                        mpaint.setAntiAlias(true);
//                        mpaint.setShader(new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
//                        canvas.drawRoundRect((new RectF(0, 0, bitmap.getWidth(), bitmap.getHeight())), radius, radius, mpaint);
//                        img1.setImageBitmap(imageRounded);





                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });
                sbHW.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {


                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {





                      if (pagenumber>=1) {root1.setLayoutParams(new LinearLayout.LayoutParams(width-(progress*2),  height-(progress*2)));}

                        if (pagenumber>=2){root2.setLayoutParams(new LinearLayout.LayoutParams(width2-(progress*2),  height2-(progress*2)));}



                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });
            }
        });


        }
    }

    @Override
    public void onBackPressed() {
      if (childechange){
          LL_in1.removeView(child2);
          LL_in1.addView(child1);
          childechange=!childechange;
      }

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        ImageView view = (ImageView) v;
        view.setScaleType(ImageView.ScaleType.MATRIX);
        float scale;
        if (gestureDetector.onTouchEvent(event)) {
            // single tap
            cliked= false;}else {cliked= true;

        }
        // Dump touch event to log
        dumpEvent(event);

        // Handle touch events here...
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN: //first finger down only
                savedMatrix.set(matrix);
                start.set(event.getX(), event.getY());

                mode = DRAG;
                break;

            case MotionEvent.ACTION_POINTER_DOWN:
                oldDist = spacing(event);
                if (oldDist > 10f) {
                    savedMatrix.set(matrix);
                    midPoint(mid, event);
                    mode = ZOOM;
                }
                lastEvent = new float[4];
                lastEvent[0] = event.getX(0);
                lastEvent[1] = event.getX(1);
                lastEvent[2] = event.getY(0);
                lastEvent[3] = event.getY(1);
                d = rotation(event);
                break;

            case MotionEvent.ACTION_UP: //first finger lifted
            case MotionEvent.ACTION_POINTER_UP: //second finger lifted
                mode = NONE;

                break;


            case MotionEvent.ACTION_MOVE:
                if (mode == DRAG) {
                    // ...
                    matrix.set(savedMatrix);
                    matrix.postTranslate(event.getX() - start.x, event.getY()
                            - start.y);
                } else if (mode == ZOOM && event.getPointerCount() == 2) {
                    float newDist = spacing(event);
                    matrix.set(savedMatrix);
                    if (newDist > 10f) {
                        scale = newDist / oldDist;
                        matrix.postScale(scale, scale, mid.x, mid.y);
                    }
                    if (lastEvent != null) {
                        newRot = rotation(event);
                        float r = newRot - d;
                        matrix.postRotate(r, view.getMeasuredWidth() / 2,
                                view.getMeasuredHeight() / 2);
                    }
                }
                break;

        }
        // Perform the transformation
        view.setImageMatrix(matrix);

        return false; // indicate event was handled

    }
    private float rotation(MotionEvent event) {
        double delta_x = (event.getX(0) - event.getX(1));
        double delta_y = (event.getY(0) - event.getY(1));
        double radians = Math.atan2(delta_y, delta_x);

        return (float) Math.toDegrees(radians);
    }

    private float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float)Math.sqrt(x * x + y * y);

    }

    private void midPoint(PointF point, MotionEvent event) {
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        point.set(x/2, y/2);

    }


    /** Show an event in the LogCat view, for debugging */

    private void dumpEvent(MotionEvent event) {
        String names[] = { "DOWN" , "UP" , "MOVE" , "CANCEL" , "OUTSIDE" ,
                "POINTER_DOWN" , "POINTER_UP" , "7?" , "8?" , "9?" };
        StringBuilder sb = new StringBuilder();
        int action = event.getAction();
        int actionCode = action & MotionEvent.ACTION_MASK;
        sb.append("event ACTION_" ).append(names[actionCode]);
        if (actionCode == MotionEvent.ACTION_POINTER_DOWN
                || actionCode == MotionEvent.ACTION_POINTER_UP) {
            sb.append("(pid " ).append(
                    action >> MotionEvent.ACTION_POINTER_ID_SHIFT);
            sb.append(")" );
        }

        sb.append("[" );

        for (int i = 0; i < event.getPointerCount(); i++) {
            sb.append("#" ).append(i);
            sb.append("(pid " ).append(event.getPointerId(i));
            sb.append(")=" ).append((int) event.getX(i));
            sb.append("," ).append((int) event.getY(i));
            if (i + 1 < event.getPointerCount())

                sb.append(";" );
        }

        sb.append("]" );

    }
    private void showPictureDialog(){
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera" };
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallary();
                                break;
                            case 1:
                                takePhotoFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY);
    }

    private void takePhotoFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }

    public void openDialog() {
        final Dialog dialog = new Dialog(MainActivity.this); // Context, this, etc.
        dialog.setContentView(R.layout.options);
        dialog.setTitle("");
        LinearLayout cancelButton, changeButton, deleteButton;
        cancelButton= (LinearLayout)dialog.findViewById(R.id.cancelButton);
        changeButton= (LinearLayout)dialog.findViewById(R.id.changeButton);
        deleteButton= (LinearLayout)dialog.findViewById(R.id.deleteButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (positions){
                    case 1 : Images1.clear();
                }
                showPictureDialog();
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (positions){
                    case 1 : Images1.clear();
                        img1.setImageBitmap(null); break;
                }
                dialog.cancel();

            }
        });
        dialog.show();
    }




    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {

                      bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    String path = saveImage(bitmap);
                    Toast.makeText(MainActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                    Log.i("log","sel is :"+sel);
                    switch (sel){
                        case 1 :
                            Images1.clear();
                            Images1.add(bitmap);
                            img1.setImageBitmap(Images1.get(0));



                            break;
                      

                        case 2 :
                            Images2.clear();
                            Images2.add(bitmap);break;
                        case 3 :
                            Images3.clear();
                            Images3.add(bitmap);break;
                        case 4 :
                            Images4.clear();
                            Images4.add(bitmap);break;
                        case 5 :
                            Images5.clear();
                            Images5.add(bitmap);break;
                        case 6 :
                            Images6.clear();
                            Images6.add(bitmap);break;
                        case 7 :
                            Images7.clear();
                            Images7.add(bitmap);break;
                        case 8 :
                            Images8.clear();
                            Images8.add(bitmap);break;
                        case 9 :
                            Images9.clear();
                            Images9.add(bitmap);break;

                    }
                    Log.i("log","Images1 is :" + Images1);





                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            switch (sel){
                case 1 :
                    Images1.clear();
                    Images1.add(thumbnail);
                    img1.setImageBitmap(Images1.get(0));
                case 2 :
                    Images2.clear();
                    Images2.add(thumbnail);break;
                case 3 :
                    Images3.clear();
                    Images3.add(thumbnail);break;
                case 4 :
                    Images4.clear();
                    Images4.add(thumbnail);break;
                case 5 :
                    Images5.clear();
                    Images5.add(thumbnail);break;
                case 6 :
                    Images6.clear();
                    Images6.add(thumbnail);break;
                case 7 :
                    Images7.clear();
                    Images7.add(thumbnail);break;
                case 8 :
                    Images8.clear();
                    Images8.add(thumbnail);break;
                case 9 :
                    Images9.clear();
                    Images9.add(thumbnail);break;
            }


            saveImage(thumbnail);
            Toast.makeText(MainActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public String saveImage(Bitmap myBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }

        try {

            File f = new File(wallpaperDirectory, Calendar.getInstance()
                    .getTimeInMillis() + ".jpg");
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(this,
                    new String[]{f.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();
            Log.d("TAG", "File Saved::--->" + f.getAbsolutePath());

            return f.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }







    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private class SingleTapConfirm extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onSingleTapUp(MotionEvent event) {
            return true;
        }
    }
}
