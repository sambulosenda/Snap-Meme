package gmsyrimis.c4q.nyc.cammy;

import android.app.Activity;
import android.graphics.Bitmap;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.io.IOException;

public class VanillaActivity extends Activity {

    LinearLayout ivVanilla;
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

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(IMAGE_URI_KEY, imageUri);
        outState.putString(TOP_TEXT_KEY, topText);
        outState.putString(BOTTOM_TEXT_KEY, bottomText);
    }
}
