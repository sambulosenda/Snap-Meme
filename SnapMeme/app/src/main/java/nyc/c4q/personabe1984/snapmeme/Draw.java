package nyc.c4q.personabe1984.snapmeme;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by c4q-vanice on 6/4/15.
 */
public class Draw {

    public Bitmap Draw(Bitmap bottomfrag, Bitmap topfrag) {

        int width = 0, height = 0;
        Bitmap image;

        width =  getWindowManager().getDefaultDisplay().getWidth();
        height = getWindowManager().getDefaultDisplay().getHeight();

        image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas comboImage = new Canvas(image);
        bottomfrag = Bitmap.createScaledBitmap(bottomfrag, width, height, true);
        comboImage.drawBitmap(bottomfrag, 0, 0, null);
        comboImage.drawBitmap(topfrag, matrix, null);

        return image;
    }
}