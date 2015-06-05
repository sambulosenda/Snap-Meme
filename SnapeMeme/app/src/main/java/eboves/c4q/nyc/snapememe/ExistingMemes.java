package eboves.c4q.nyc.snapememe;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;


public class ExistingMemes extends BaseAdapter {

    private Context mContext;

    public ExistingMemes(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.gridone, R.drawable.girdfour,
            R.drawable.gridtwo, R.drawable.gridfive,
            R.drawable.gridthree, R.drawable.gridsix,
            R.drawable.gridseven, R.drawable.grideight,
            R.drawable.gridnine, R.drawable.gridten,
            R.drawable.grideleven, R.drawable.gridtwelve,
            R.drawable.gridthirteen, R.drawable.gridforteen,
            R.drawable.gridfifteen, R.drawable.gridsixteen,
    };
}