package gmsyrimis.c4q.nyc.cammy;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;


public class MainActivity extends Activity {

    ImageView gotoCamera;
    ImageView gotoGallery;

    private String imageUri="";

    public static String IMAGE_URI_KEY = "uri";

    private static final int CAMERA_REQUEST = 1;
    private static final int SELECT_SINGLE_PICTURE = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gotoGallery = (ImageView) findViewById(R.id.iv_gallery);
        gotoGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                if (galleryIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(galleryIntent, SELECT_SINGLE_PICTURE);
                }
            }
        });

        gotoCamera = (ImageView) findViewById(R.id.iv_camera);
        gotoCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent capture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(capture, CAMERA_REQUEST);
            }
        });
    }

    private void gotoSelectStyle(){
        Intent selectStyle = new Intent(MainActivity.this,SelectStyleActivity.class);
        selectStyle.putExtra(IMAGE_URI_KEY, imageUri);
        startActivity(selectStyle);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
                Uri selectedImage = data.getData();
                imageUri = selectedImage.toString();
                gotoSelectStyle();
            }
            else if (requestCode == SELECT_SINGLE_PICTURE && resultCode == RESULT_OK && null != data) {
                Uri selectedImage = data.getData();
                imageUri = selectedImage.toString();
                gotoSelectStyle();
            }
        }
        catch (Exception e) {
        }
    }
}

