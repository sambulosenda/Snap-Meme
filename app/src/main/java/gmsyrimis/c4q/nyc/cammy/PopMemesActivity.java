package gmsyrimis.c4q.nyc.cammy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PopMemesActivity extends Activity {
    public static String IMAGE_URI_KEY = "uri";
    private ListView memeListView;
    private String imageUri = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_memes);


        memeListView = (ListView) findViewById(R.id.meme_list_view);

        new DatabaseTask().execute();

    }

    class MemeAdapter extends ArrayAdapter<Integer> {
        public MemeAdapter(Context context, List<Integer> memeList) {
            super(context, 0, memeList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            int singleMeme = getItem(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_item, parent, false);
            }
            ImageView appIcon = (ImageView) convertView.findViewById(R.id.meme_thumb);
            appIcon.setImageResource(singleMeme);
            return convertView;
        }
    }
    //all database updating(add/remove) and fetching should happen in the background
    private class DatabaseTask extends AsyncTask<Void, Void, List<PopMeme>>{

        @Override
        protected List<PopMeme> doInBackground(Void... params) {
            //we include .getinstance bc there is one ONE helper
            DatabaseOpenHelper helper = DatabaseOpenHelper.getInstance(getApplicationContext());
            try {
                if (helper.getDao(PopMeme.class).queryForAll().size() == 0){
                    //insert data here
                    helper.insertRow(R.drawable.gridone);
                    helper.insertRow(R.drawable.gridtwo);
                    helper.insertRow(R.drawable.gridthree);
                    helper.insertRow(R.drawable.girdfour);
                    helper.insertRow(R.drawable.gridfive);
                    helper.insertRow(R.drawable.gridsix);
                    helper.insertRow(R.drawable.gridseven);
                    helper.insertRow(R.drawable.grideight);
                    helper.insertRow(R.drawable.gridnine);
                    helper.insertRow(R.drawable.gridten);
                    helper.insertRow(R.drawable.grideleven);
                    helper.insertRow(R.drawable.gridtwelve);
                    helper.insertRow(R.drawable.gridthirteen);
                    helper.insertRow(R.drawable.gridforteen);
                    helper.insertRow(R.drawable.gridfifteen);
                    helper.insertRow(R.drawable.gridsixteen);


                }
                return helper.getDao(PopMeme.class).queryForAll();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return  null;
        }

        @Override
        protected void onPostExecute(List<PopMeme> popMemes) {
            //This adapter is specific to the PopmemesActivity
            List<Integer> resources = new ArrayList<>();
            for (PopMeme meme : popMemes) {resources.add(meme.getPicture());
            }
            MemeAdapter memeAdapter = new MemeAdapter(PopMemesActivity.this, resources);
            memeListView.setAdapter(memeAdapter);

            memeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    int meme = (Integer) parent.getItemAtPosition(position);
                    Intent gotoPopMeme = new Intent(PopMemesActivity.this, PopMemeEditor.class);
                    imageUri += meme;
                    gotoPopMeme.putExtra(IMAGE_URI_KEY, imageUri);
                    startActivity(gotoPopMeme);
                }
            });
        }
    }
}
