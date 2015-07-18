package gmsyrimis.c4q.nyc.cammy;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by charlynbuchanan on 7/17/15.
 */
public class DatabaseOpenHelper extends OrmLiteSqliteOpenHelper {
    private static final String MYDB = "mydb.db";
    private static final int VERSION = 1;
    private static DatabaseOpenHelper mHelper;
    //This is the constructor that is called by mHelper
    //then, it will evaluate if there is a database, and the version #
    private DatabaseOpenHelper(Context context) {
        super(context, MYDB, null, VERSION);
    }

    //This ensures thers is only one helper in this class. We prevent making another on in Main
    //Will create a helper ONLY if there isn't one already
    public static DatabaseOpenHelper getInstance(Context context){
        if (mHelper == null) {
            mHelper = new DatabaseOpenHelper(context.getApplicationContext());
        }
        return mHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        //WE don't make the column. A class will do that for us based on PopMeme class
        try {
            TableUtils.createTable(connectionSource, PopMeme.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        //deletes the entire table if it exists, and create it again
        //then call onCreate to make the table
        try {
            TableUtils.dropTable(connectionSource, PopMeme.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertRow(int picture) {
        //create instance of popmeme
        PopMeme meme = new PopMeme(picture);
        try {
            getDao(PopMeme.class).create(meme);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

}
