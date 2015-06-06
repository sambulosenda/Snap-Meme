package com.example.c4q_vanice.storage_example;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private EditText mName;
    private EditText mAge;
    private Button mCreateUser;
    private static int mUserCount;
    private List<User> userList = null;
    private TextView userCount;
    private Spinner mSpinner;
//  private SharedPreferences mSharedPreference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCreateUser = (Button) findViewById(R.id.submit);
        mName = (EditText)findViewById(R.id.username);
        mAge = (EditText) findViewById(R.id.userage);
        mSpinner = (Spinner)findViewById(R.id.spinner);
        userCount = (TextView)findViewById(R.id.count);

        userList = new ArrayList<>();
        ArrayAdapter<User> adapter = new ArrayAdapter<User>(MainActivity.this, android.R.layout.simple_list_item_1, userList);
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
}
