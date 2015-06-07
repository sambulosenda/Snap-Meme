package gmsyrimis.c4q.nyc.cammy;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
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
    private Button shareBt;

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

        shareBt = (Button)findViewById(R.id.btsharing);
        shareBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("image/jpeg");
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                File f = new File(Environment.getExternalStorageDirectory() + File.separator + "temporary_file.jpg");
                try {
                    f.createNewFile();
                    FileOutputStream fo = new FileOutputStream(f);
                    fo.write(bytes.toByteArray());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                share.putExtra(Intent.EXTRA_STREAM, Uri.parse("file:///sdcard/temporary_file.jpg"));
                startActivity(Intent.createChooser(share, "Share Image"));

            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(IMAGE_URI_KEY, imageUri);
        outState.putString(TOP_TEXT_KEY, topText);
        outState.putString(BOTTOM_TEXT_KEY, bottomText);
    }

}
