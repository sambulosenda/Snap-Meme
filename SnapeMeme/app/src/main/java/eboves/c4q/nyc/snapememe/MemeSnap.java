package eboves.c4q.nyc.snapememe;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;


public class MemeSnap extends ActionBarActivity {

    private File file;
    private ImageView imageHold;
    private Button buttonVanella;
    private Button buttonDemotivational;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meme_snap);
        imageHold = (ImageView)findViewById(R.id.ivHolder);
        //file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/myImage.jpg");

        buttonVanella = (Button) findViewById(R.id.btVanella);
        buttonVanella.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentVanella = new Intent(MemeSnap.this, MainActivity.class);
                startActivity(intentVanella);
            }
        });

        buttonDemotivational = (Button) findViewById(R.id.btDemotivational);
        buttonDemotivational.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentDemotivational = new Intent(MemeSnap.this, MainActivity.class);
                startActivity(intentDemotivational);
            }
        });

    }

//    public void displayCameraBitmap() {
//        int degree = 0;
//        try {
//            ExifInterface exifInterface = new ExifInterface(file.getAbsolutePath());
//            int orientation =   exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, 0);
//            switch (orientation){
//                case ExifInterface.ORIENTATION_ROTATE_90:
//                    degree = 90;
//                    break;
//                case ExifInterface.ORIENTATION_ROTATE_180:
//                    degree = 180;
//                    break;
//                case ExifInterface.ORIENTATION_ROTATE_270:
//                    degree = 270;
//                    break;
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//        BitmapFactory.Options options = new BitmapFactory.Options();
//        // INSTEAD OF FILE GET IT FROM BUNDLE
//        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), options);
//
////        MemeSnap memeSnapClass = new MemeSnap();
////
////        memeSnapClass.onCreate(Bundle.);
//
//        imageHold.setImageDrawable(new FakeBitmapDrawable(bitmap, degree));
//    }

}
