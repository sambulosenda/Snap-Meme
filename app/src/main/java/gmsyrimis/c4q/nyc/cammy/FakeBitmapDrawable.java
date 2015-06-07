package gmsyrimis.c4q.nyc.cammy;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;

public class FakeBitmapDrawable extends BitmapDrawable {

    private int degree;
    private Bitmap bitmap;
    private Paint paint;

    FakeBitmapDrawable(Bitmap bitmap, int degree) {
        super(bitmap);
        this.bitmap = bitmap;
        this.paint = new Paint();
        this.degree = degree;
    }
    @Override
    public int getIntrinsicWidth() {
        if (degree == 90 || degree == 270) {
            return bitmap.getHeight();
        } else
            return bitmap.getWidth();
    }
    @Override
    public int getIntrinsicHeight() {
        if (degree == 90 || degree == 270) {
            return bitmap.getWidth();
        } else
            return bitmap.getHeight();
    }
    @Override
    public void draw(Canvas canvas) {
        switch (degree) {
            case 90:
                canvas.rotate(degree);
                canvas.drawBitmap(bitmap, 0, -bitmap.getHeight(), paint);
                break;
            case 180:
                canvas.rotate(degree);
                canvas.drawBitmap(bitmap, -bitmap.getWidth(), -bitmap.getHeight(), paint);
                break;
            case 270:
                canvas.rotate(degree);
                canvas.drawBitmap(bitmap, -bitmap.getWidth(), 0, paint);
                break;
            default:
                canvas.drawBitmap(bitmap, 0, 0, paint);
                break;
        }
    }
}