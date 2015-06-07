package gmsyrimis.c4q.nyc.cammy;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VanillaActivity extends Activity {

    LinearLayout ivVanilla;
    Bitmap bitmap;

    private String imageUri = "";
    public static String IMAGE_URI_KEY = "uri";

    private String topText;
    public static String TOP_TEXT_KEY = "top";

    private String bottomText;
    public static String BOTTOM_TEXT_KEY = "bottom";

    EditText topRow;
    EditText bottomRow;
    private Button shareBt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vanilla);

        ivVanilla = (LinearLayout) findViewById(R.id.vanilla_custom_image);

        if (savedInstanceState == null) {
            imageUri = getIntent().getStringExtra(IMAGE_URI_KEY);
            topText = "";
            bottomText = "";
        } else {
            imageUri = savedInstanceState.getString(IMAGE_URI_KEY);
            topText = savedInstanceState.getString(TOP_TEXT_KEY);
            bottomText = savedInstanceState.getString(BOTTOM_TEXT_KEY);
        }

        topRow = (EditText) findViewById(R.id.vanilla_top_text);
        topRow.setText(topText);
        bottomRow = (EditText) findViewById(R.id.vanilla_bottom_text);
        bottomRow.setText(bottomText);

        try {
            bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), Uri.parse(imageUri));
        } catch (IOException e) {
            e.printStackTrace();
        }

        DisplayMetrics metrics = getBaseContext().getResources().getDisplayMetrics();
        bitmap = Bitmap.createScaledBitmap(bitmap, metrics.widthPixels, metrics.heightPixels, true);
        ivVanilla.setBackground(new FakeBitmapDrawable(bitmap, 0));

        shareBt = (Button) findViewById(R.id.btShare);
        shareBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process(ivVanilla);
            }
        });

    }


    public void process(View view){

        Bitmap screenshot = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(screenshot);
        view.layout(0, 0, view.getLayoutParams().width, view.getLayoutParams().height);
        view.draw(canvas);
        String imageFileName = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss").format(new Date());
        String filename = "Snapmeme" + imageFileName + ".jpeg";
        File picDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File outputFile = null;
        try {
            outputFile = File.createTempFile(filename, ".jpg", picDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(outputFile);
            screenshot.compress(Bitmap.CompressFormat.JPEG, 100, out);
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
        //if(view.getId() == R.id.sendImage){
            Uri imageUri = Uri.fromFile(outputFile);
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_STREAM,imageUri);
            intent.putExtra(Intent.EXTRA_TEXT,"Hey I have attached this picture");
            Intent chooser = Intent.createChooser(intent,"Send Picture");
            startActivity(chooser);
       // }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(IMAGE_URI_KEY, imageUri);
        outState.putString(TOP_TEXT_KEY, topText);
        outState.putString(BOTTOM_TEXT_KEY, bottomText);
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

        String imageFileName = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss").format(new Date());

        String filename = "Snapmeme" + imageFileName + ".jpeg";

        File picDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        File outputFile = null;
        try {
            outputFile = File.createTempFile(filename, ".jpg", picDir);
        } catch (IOException e) {
            e.printStackTrace();
        }

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

}
