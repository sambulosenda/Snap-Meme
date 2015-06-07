package gmsyrimis.c4q.nyc.cammy;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DemotivationalActivity extends Activity {


    //ELEMENTS
    private ImageView pictureHolder;
    private Bitmap bitmap;
    private LinearLayout layoutHolder;
    private Button saveBTN;
    private Button shareBt;
    private EditText topEditText;
    private EditText bottomEditText;
    // KEY VALUE PAIRS
    private String imageUri = "";
    public static String IMAGE_URI_KEY = "uri";
    private String topString;
    public static String TOP_TEXT_KEY = "top";
    private String bottomString;
    public static String BOTTOM_TEXT_KEY = "bottom";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demotivational);
        // FINDING VIEWS
        layoutHolder = (LinearLayout) findViewById(R.id.demotivational_full);
        pictureHolder = (ImageView) findViewById(R.id.demotivational_custom_image);
        topEditText = (EditText) findViewById(R.id.demotivational_top_text);
        bottomEditText = (EditText) findViewById(R.id.demotivational_bottom_text);
        shareBt = (Button) findViewById(R.id.buttonShare);
        saveBTN = (Button) findViewById(R.id.btSave);
        // LOADING
        if (savedInstanceState == null) {
            imageUri = getIntent().getExtras().getString(IMAGE_URI_KEY);
            topString = "";
            bottomString = "";
        } else {
            imageUri = savedInstanceState.getString(IMAGE_URI_KEY);
            topString = savedInstanceState.getString(TOP_TEXT_KEY);
            bottomString = savedInstanceState.getString(BOTTOM_TEXT_KEY);
        }
        // SETTING VALUES
        topEditText.setText(topString);
        bottomEditText.setText(bottomString);
        try {
            bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), Uri.parse(imageUri));
        } catch (IOException e) {
            e.printStackTrace();
        }
        bitmap = Bitmap.createScaledBitmap(bitmap, 330, 210, true);
        pictureHolder.setImageDrawable(new FakeBitmapDrawable(bitmap, 0));
        // SAVE BUTTON
        saveBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveIt(layoutHolder, layoutHolder.getWidth(), layoutHolder.getHeight());
            }
        });

    }

    public static Bitmap screenView(View v, int width, int height) {
        Bitmap screenshot = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(screenshot);
        v.layout(0, 0, v.getLayoutParams().width, v.getLayoutParams().height);
        v.draw(c);
        return screenshot;
    }

    public void saveIt(View memeView, int width, int height) {
        Bitmap sharable = screenView(memeView, width, height);
        // FILE SETUP
        String imageFileName = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss").format(new Date());
        String filename = "Snapmeme" + imageFileName + ".jpeg";
        File picDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File outputFile = null;
        try {
            outputFile = File.createTempFile(filename, ".jpg", picDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // STREAM
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(outputFile);
            sharable.compress(Bitmap.CompressFormat.JPEG, 100, out);
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
        // URI TO BE PASSED
        Uri resultUri = Uri.fromFile(outputFile);
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        mediaScanIntent.setData(resultUri);
        this.sendBroadcast(mediaScanIntent);
    }

//    public void process(View view){
//
//
//
//
//
//        if(view.getId() == R.id.sendImage){
//            Uri imageUri = Uri.parse("android.resource://com.example.android.implicitintentexample/drawable" + R.mipmap.ic_launcher);
//            intent = new Intent(Intent.ACTION_SEND);
//            intent.setType("image/*");
//            intent.putExtra(Intent.EXTRA_STREAM,imageUri);
//            intent.putExtra(Intent.EXTRA_TEXT,"Hey I have attached this picture");
//            chooser = Intent.createChooser(intent,"Send Picture");
//            startActivity(chooser);
//        }
//    }



    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(IMAGE_URI_KEY, imageUri);
        outState.putString(TOP_TEXT_KEY, topString);
        outState.putString(BOTTOM_TEXT_KEY, bottomString);
    }


}
