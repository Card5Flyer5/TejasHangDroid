package project.tejashangdroid;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    Button btnOnePlayer;
    private RatingBar ratingBar;
    private TextView txtRatingValue;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Button playBtn = (Button)findViewById(R.id.onePlayerButton);
        playBtn.setOnClickListener(this);



;

    }



    @Override
    public void onClick(View view)
    {
        //handle clicks
        if(view.getId()==R.id.onePlayerButton){
            Intent playIntent = new Intent(this, NextScreenActivity.class);
            this.startActivity(playIntent);
        }
    }

    public void startMultiPlayerGame(View view)
    {
        //explicit intent sends a message to start the activity
        Intent intent = new Intent(this, MultiPlayerActivity.class);
        startActivity(intent);
    }

    public void startTextPlayerGame(View view)
    {
        //explicit intent sends a message to start the activity
        Intent intent = new Intent(this, TextActivity.class);
        startActivity(intent);
    }

    public void startContactPlayerGame(View view)
    {
        //explicit intent sends a message to start the activity
        Intent intent = new Intent(this, ContactsActivity.class);
        startActivity(intent);
    }

    public void openScores(View view)
    {
        //explicit intent sends a message to start the activity
        Intent intent = new Intent(this, ScoresActivity.class);
        startActivity(intent);
    }

    public void onBackPressed()
    {
        Toast.makeText(this, "You can't go back. ", Toast.LENGTH_SHORT).show();
    }


    @Override
      public boolean onCreateOptionsMenu(Menu menu)
{
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
}

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        super.onOptionsItemSelected(item);
        switch (item.getItemId())
        {
            case R.id.action_about:
                aboutMenuItem();
                break;
            case R.id.action_settings:
                settingsMenuItem();
                break;
        }
        return true;
    }



    private void aboutMenuItem()
    {
        AlertDialog.Builder aboutBuild = new AlertDialog.Builder(this);
        aboutBuild.setTitle("Hangdroid");
        aboutBuild.setMessage("This is Tejas's Hangdroid, made in CSIS 2630-F15.");
        aboutBuild.setNeutralButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });
        aboutBuild.show();

    }

    private void settingsMenuItem()
    {
        AlertDialog.Builder settingsBuild = new AlertDialog.Builder(this);
        settingsBuild.setTitle("Settings");
        settingsBuild.setMessage("This is the Setting icon.");
        settingsBuild.setNeutralButton("OK", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {

            }
        });
        settingsBuild.show();

    }







}
