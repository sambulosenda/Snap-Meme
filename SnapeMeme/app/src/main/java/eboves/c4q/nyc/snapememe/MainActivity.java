package eboves.c4q.nyc.snapememe;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends ActionBarActivity {


    static final int REQUEST_IMAGE_CAPTURE = 1;
    private String file;
    private ImageView ivCamera;
    private ImageView ivGallery;
    private ImageView ivExisting;
    Uri capturedImageUri;
    MemeSnap memeSnapClass = new MemeSnap();
    private ImageView pictureHolder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //  displayCameraBitmap();






        pictureHolder = (ImageView) findViewById(R.id.ivMain);

        ivCamera = (ImageView) findViewById(R.id.ivCamera);
        ivCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                File file = new File(Environment.getExternalStorageDirectory() + ".jpg");
                if (!file.exists()) {
                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                } else {
                    file.delete();
                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                capturedImageUri = Uri.fromFile(file);
                Intent i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                i.putExtra(MediaStore.EXTRA_OUTPUT, capturedImageUri);
                startActivityForResult(i, REQUEST_IMAGE_CAPTURE);

            }
        });

        ivGallery = (ImageView) findViewById(R.id.ivGallery);
        ivGallery.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intentGallery = new Intent(MainActivity.this, MemeSnap.class);
                startActivity(intentGallery);
            }
        });

        ivExisting = (ImageView) findViewById(R.id.ivExisting);
        ivExisting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intentExisting = new Intent(MainActivity.this, MemeSnap.class);

                startActivity(intentExisting);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {

            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");

            String imageFileName = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss").format(new Date());

            String filename = "Snapmeme" + imageFileName + ".jpeg";
            String directory = "memefyme";
            String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + File.separator + directory;
            File outputDir = new File(path);

            outputDir.mkdirs();
            File newFile = new File(path + "/" + filename);
            Uri resultUri = Uri.fromFile(newFile);

            Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            mediaScanIntent.setData(resultUri);
            this.sendBroadcast(mediaScanIntent);

            FileOutputStream out = null;
            try {
                out = new FileOutputStream(newFile);
                thumbnail.compress(Bitmap.CompressFormat.JPEG, 100, out);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (out != null) {
                        out.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            Intent intent = new Intent(this, MemeSnap.class);
            intent.putExtra("uri", resultUri);
            startActivity(intent);


        }
    }


//            ByteArrayOutputStream stream = new ByteArrayOutputStream();
//            //displayCameraBitmap();
//            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
//            thumbnail.compress(Bitmap.CompressFormat.JPEG, 100, stream);
//            byte[] byteArray = stream.toByteArray();
//            Intent in1 = new Intent(this, MemeSnap.class);
//            in1.putExtra("image",byteArray);


}

//
//    private void displayCameraBitmap() {
//        int degree = 0;
//        try {
//            ExifInterface exifInterface = new ExifInterface(file);
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
//        BitmapFactory.Options options = new BitmapFactory.Options();
//        Bitmap bitmap = BitmapFactory.decodeFile(file, options);
//        pictureHolder.setImageDrawable(new FakeBitmapDrawable(bitmap, degree));
//    }




