package gmsyrimis.c4q.nyc.cammy;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;


public class DemotivationalActivity extends Activity {

    ImageView ivDemotivational;
    Bitmap bitmap;

    private String imageUri="";
    public static String IMAGE_URI_KEY = "uri";

    private String topText;
    public static String TOP_TEXT_KEY = "top";

    private String bottomText;
    public static String BOTTOM_TEXT_KEY = "bottom";

    EditText topRow;
    EditText bottomRow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demotivational);

        ivDemotivational = (ImageView) findViewById(R.id.demotivational_custom_image);

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
        ivDemotivational.setImageDrawable(new FakeBitmapDrawable(bitmap,0));
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(IMAGE_URI_KEY, imageUri);
        outState.putString(TOP_TEXT_KEY, topText);
        outState.putString(BOTTOM_TEXT_KEY, bottomText);
    }
}
