package project.tejashangdroid;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class GameOverActivity extends AppCompatActivity
{
    private int playerPoints=0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        int points = getIntent().getIntExtra("PointsID", 0);

        TextView textView = (TextView) findViewById(R.id.textViewPoints);
        textView.setText(String.valueOf(points));

        playerPoints = points;
        if(playerPoints==0)
        {
            AlertDialog.Builder aboutBuild = new AlertDialog.Builder(this);
            aboutBuild.setTitle("No points");
            aboutBuild.setMessage("You lost and did not get at least 1 point. Try again!");
            aboutBuild.setNeutralButton("OK", new DialogInterface.OnClickListener()
            {
                public void onClick(DialogInterface dialog, int id)
                {
                    nextActivity();
                }
            });
            aboutBuild.show();

        }
    }

    public void saveScore(View view)
    {
        SharedPreferences preferences = getSharedPreferences("WORD_SCORES", MODE_PRIVATE);

        EditText editText = (EditText) findViewById(R.id.editTextName);

        String name = editText.getText().toString();

        SharedPreferences.Editor editor = preferences.edit();

        String previousScores = preferences.getString("SCORES", "");
        Log.d("MYLOG","Previous Scores: " + previousScores);

        editor.putString("SCORES", name + " " + playerPoints + " POINTS\n" + previousScores);

        editor.commit();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);





    }

//    public void startMain(View view)
//    {
//        AlertDialog.Builder blNextTimeBuild = new AlertDialog.Builder(this);
//        blNextTimeBuild.setTitle("You lost :(");
//        blNextTimeBuild.setMessage("I hope you had fun, but better luck next time");
//        blNextTimeBuild.show();
//        Runnable w3s = new Runnable() {
//            @Override
//            public void run() {
//                nextActivity();
//            }
//        };
//
//        Handler handler = new Handler();
//        handler.postDelayed(w3s, 10000);
//        //explicit intent sends a message to start the activity
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
//    }
//
    public void nextActivity()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }



    @Override
    public void onBackPressed()
    {

    }



}
