package nyc.c4q.personabe1984.snapmeme;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity implements TopFragment.TopInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bitmap bmp = Bitmap.createBitmap(mEditText.getDrawingCache());

        Bitmap combined = Draw(bgBitmap, bmp);

//        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.hansface);
//        Bitmap alteredBitmap = Bitmap.createBitmap(bm.getWidth(), bm.getHeight(), bm.getConfig());
//        Canvas canvas = new Canvas(alteredBitmap);
//        Paint paint = new Paint();
//        canvas.drawBitmap(bm, 0, 0, paint);
//        paint.setColor(Color.BLACK);
//        paint.setTextSize(20);
//        canvas.drawText("Some Text", 10, 25, paint);
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

    @Override
    public void meme(String top, String bottom) {
        BottomFragment bottomFragment = (BottomFragment) getSupportFragmentManager().findFragmentById(R.id.fragment2);
        bottomFragment.setmeme(top, bottom);
    }
}

/*
This is what I have been going through 
http://developer.android.com/reference/android/view/View.html#getDrawingCache()
http://code.neenbedankt.com/how-to-render-an-android-view-to-a-bitmap/
http://developer.android.com/guide/topics/graphics/2d-graphics.html#drawables
http://stackoverflow.com/questions/10988847/view-getdrawingcache-only-works-once
 */