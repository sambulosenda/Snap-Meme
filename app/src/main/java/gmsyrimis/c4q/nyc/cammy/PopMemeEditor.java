package gmsyrimis.c4q.nyc.cammy;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;


public class PopMemeEditor extends Activity {

    ImageView ivCustomPopular;
    Bitmap bitmap;

    private String imageUri="";
    public static String IMAGE_URI_KEY = "uri";

    private String topText="";
    public static String TOP_TEXT_KEY = "top";

    private String bottomText="";
    public static String BOTTOM_TEXT_KEY = "bottom";

    EditText topRow;
    EditText bottomRow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_meme_editor);

        ivCustomPopular = (ImageView) findViewById(R.id.iv_custom_popular);

        if (savedInstanceState == null) {
            imageUri = getIntent().getExtras().getString(IMAGE_URI_KEY);
            topText = "";
            bottomText = "";
        } else {
            imageUri = savedInstanceState.getString(IMAGE_URI_KEY);
            topText = savedInstanceState.getString(TOP_TEXT_KEY);
            bottomText = savedInstanceState.getString(BOTTOM_TEXT_KEY);
        }

        topRow = (EditText) findViewById(R.id.top_pop);
        topRow.setText(topText);
        bottomRow = (EditText) findViewById(R.id.bottom_pop);
        bottomRow.setText(bottomText);

        int popId = Integer.parseInt(imageUri);
        ivCustomPopular.setImageDrawable(getResources().getDrawable(popId));


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(IMAGE_URI_KEY, imageUri);
        outState.putString(TOP_TEXT_KEY, topText);
        outState.putString(BOTTOM_TEXT_KEY, bottomText);
    }

}
