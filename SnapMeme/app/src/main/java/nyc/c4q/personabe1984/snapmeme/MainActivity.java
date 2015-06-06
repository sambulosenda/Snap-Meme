package nyc.c4q.personabe1984.snapmeme;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends Activity implements TopFragment.TopInterface {

    LinearLayout memeView;
    Bitmap bmp;
    int mvWidth, mvHeight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        memeView = (LinearLayout) findViewById(R.id.memeView);
        mvWidth = memeView.getWidth();
        mvHeight = memeView.getHeight();

        bmp = screenView(memeView, mvWidth, mvHeight);


//        I am saving this code since I want to work on it in the future.
//        Bitmap combined = Draw(bgBitmap, bmp);
//        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.hansface);
//        Bitmap alteredBitmap = Bitmap.createBitmap(bm.getWidth(), bm.getHeight(), bm.getConfig());
//        Canvas canvas = new Canvas(alteredBitmap);
//        Paint paint = new Paint();
//        canvas.drawBitmap(bm, 0, 0, paint);
//        paint.setColor(Color.BLACK);
//        paint.setTextSize(20);
//        canvas.drawText("Some Text", 10, 25, paint);
    }

    // The condensed version of Draw class
    public static Bitmap screenView(View v, int width, int height) {
        Bitmap screenshot = Bitmap.createBitmap(width , height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(screenshot);
        v.layout(0, 0, v.getLayoutParams().width, v.getLayoutParams().height);
        v.draw(c);
        return screenshot;
    }

    public void SaveButton(View memeView, int width, int height) {


        Bitmap sharable = screenView(memeView, width, height);

        String imageFileName = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss").format(new Date());

        String filename = "Snapmeme" + imageFileName + ".jpeg";
        String directory = "memefyme";
        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + File.separator + directory;
        File outputDir= new File(path);

        outputDir.mkdirs();
        File newFile = new File(path+"/"+ filename);
        Uri resultUri = Uri.fromFile(newFile);

        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        mediaScanIntent.setData(resultUri);
        this.sendBroadcast(mediaScanIntent);

        FileOutputStream out = null;
        try {
            out = new FileOutputStream(newFile);
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


        Intent intent = new Intent(this, ShareActivity.class);
        intent.putExtra("uri", resultUri);
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void meme(String top, String bottom) {
        BottomFragment bottomFragment = (BottomFragment) getFragmentManager().findFragmentById(R.id.fragment2);
        bottomFragment.setmeme(top, bottom);
    }
}

/*
resources:
http://developer.android.com/reference/android/view/View.html#getDrawingCache()
http://code.neenbedankt.com/how-to-render-an-android-view-to-a-bitmap/
http://developer.android.com/guide/topics/graphics/2d-graphics.html#drawables
http://stackoverflow.com/questions/10988847/view-getdrawingcache-only-works-once
 */