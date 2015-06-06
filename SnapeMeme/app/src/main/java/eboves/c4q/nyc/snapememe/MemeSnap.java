package eboves.c4q.nyc.snapememe;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.net.Uri;
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

    protected ImageView imageHold;
    private Button buttonVanella;
    private Button buttonDemotivational;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meme_snap);


        imageHold = (ImageView)findViewById(R.id.ivHolder);
        Intent saveImg = new Intent(this, MainActivity.class);
        //Uri saveImage = saveImg.






        //imageHold = (ImageView)findViewById(R.id.ivHolder);
        byte[] byteArray = getIntent().getByteArrayExtra("image");
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        imageHold.setImageBitmap(bmp);














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
}
