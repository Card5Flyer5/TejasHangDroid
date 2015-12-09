package project.tejashangdroid;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MultiPlayerActivity extends AppCompatActivity
{

    EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_player);





        FragmentManager fragmentManager = getFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Configuration configuration =getResources().getConfiguration();
        if(configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
        {
            hideSidePanel();
        }


        fragmentTransaction.commit();




        editText = (EditText) findViewById(R.id.editTextWord);

        editText.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {
                Log.d("MYLOG", "beforeTextChanged" + "Start" + start + "Count" + count + "After" + after);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                Log.d("MYLOG", "onTextChanged" + "Start" + start + "Before" + before + "Count" + count);
            }

            @Override
            public void afterTextChanged(Editable s)
            {
                Log.d("MYLOG", "afterTextChanged" + s);
            }
        });
    }

    private void hideSidePanel()
    {
        View sidePane = findViewById(R.id.side_panel);
        if(sidePane.getVisibility()== View.VISIBLE)
        {
            sidePane.setVisibility(View.GONE);
        }
    }

    public void startMultiPlayerGame(View view)
    {
        //Create new editText object from XML
        EditText editText = (EditText) findViewById(R.id.editTextWord);
        //get word and cast word to a String
        String wordToGuess = editText.getText().toString();
        //debug
        Log.d("MYLOG", "Multi-Player Word: " + wordToGuess);
        //create intent
        Intent intent = new Intent(this, GameMultiActivity.class);
        intent.putExtra("GUESS_WORD", wordToGuess);
        //start activity
        startActivity(intent);

    }

    public void onBackPressed()
    {
        AlertDialog.Builder backBuild = new AlertDialog.Builder(this);
        backBuild.setTitle("Back");
        backBuild.setMessage("Do you want to go back to the main menu");
        backBuild.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id)
            {
                nextActivity();
            }
        });
        backBuild.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        backBuild.show();

    }

    public void nextActivity()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }




}
