package gmsyrimis.c4q.nyc.cammy;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DemotivationalActivity extends Activity {

    ImageView ivDemotivational;
    Bitmap bitmap;
    private Button bt_save;

    private String imageUri="";
    public static String IMAGE_URI_KEY = "uri";

    private String topText;
    public static String TOP_TEXT_KEY = "top";

    private String bottomText;
    public static String BOTTOM_TEXT_KEY = "bottom";

    EditText topRow;
    EditText bottomRow;
    private Button shareBt;
    private LinearLayout demo_lin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demotivational);

        demo_lin = (LinearLayout)findViewById(R.id.demotivational_full);
        ivDemotivational = (ImageView) findViewById(R.id.demotivational_custom_image);
        shareBt = (Button) findViewById(R.id.buttonShare);
        bt_save = (Button) findViewById(R.id.btSave);

        if (savedInstanceState == null) {
            imageUri = getIntent().getExtras().getString(IMAGE_URI_KEY);
            topText = "";
            bottomText = "";
        } else {
            imageUri = savedInstanceState.getString(IMAGE_URI_KEY);
            topText = savedInstanceState.getString(TOP_TEXT_KEY);
            bottomText = savedInstanceState.getString(BOTTOM_TEXT_KEY);
        }

        topRow = (EditText) findViewById(R.id.demotivational_top_text);
        topRow.setText(topText);
        bottomRow = (EditText) findViewById(R.id.demotivational_bottom_text);
        bottomRow.setText(bottomText);

        try {
            bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), Uri.parse(imageUri));
        } catch (IOException e) {
            e.printStackTrace();
        }
        bitmap = Bitmap.createScaledBitmap(bitmap, 330, 210, true);
        ivDemotivational.setImageDrawable(new FakeBitmapDrawable(bitmap, 0));


        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveIt(demo_lin, demo_lin.getWidth(), demo_lin.getHeight());
            }
        });

    }

              public static Bitmap screenView(View v, int width, int height) {
                  Bitmap screenshot = Bitmap.createBitmap(width , height, Bitmap.Config.ARGB_8888);
                  Canvas c = new Canvas(screenshot);
                  v.layout(0, 0, v.getLayoutParams().width, v.getLayoutParams().height);
                  v.draw(c);
                  return screenshot;
              }

              public void saveIt(View memeView, int width, int height) {


                  Bitmap sharable = screenView(memeView, width, height);

                  String imageFileName = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss").format(new Date());

                  String filename = "Snapmeme" + imageFileName + ".jpeg";

                  File picDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

                  File outputFile = null;
                  try {
                      outputFile = File.createTempFile(filename, ".jpg", picDir);
                  } catch (IOException e) {
                      e.printStackTrace();
                  }


                  //outputDir.mkdirs();


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

                  Uri resultUri = Uri.fromFile(outputFile);
                  // this part is adding the completed meme picture to the gallery
                  Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                  mediaScanIntent.setData(resultUri);
                  this.sendBroadcast(mediaScanIntent);

              }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(IMAGE_URI_KEY, imageUri);
        outState.putString(TOP_TEXT_KEY, topText);
        outState.putString(BOTTOM_TEXT_KEY, bottomText);
    }



}
