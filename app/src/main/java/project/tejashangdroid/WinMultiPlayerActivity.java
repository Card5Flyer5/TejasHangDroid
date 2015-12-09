package project.tejashangdroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class WinMultiPlayerActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win_multi_player);


        int points = getIntent().getIntExtra("PointsID", 0);

        TextView textView = (TextView) findViewById(R.id.textViewPoints);
        textView.setText(String.valueOf(points));
    }

    public void startMain(View view)
    {
        //explicit intent sends a message to start the activity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void startMultiPlayerGame(View view)
    {
        //explicit intent sends a message to start the activity
        Intent intent = new Intent(this, MultiPlayerActivity.class);
        startActivity(intent);


    }

}
