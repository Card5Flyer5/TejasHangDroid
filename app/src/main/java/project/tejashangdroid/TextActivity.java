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
import android.widget.Toast;

public class TextActivity extends AppCompatActivity
{
    private EditText editText;
    private SharedPreferences preferences;
    private TextView textView;
    private String textWord;
    private String friendPhone;
    private String texterPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        preferences = getSharedPreferences("TEXT_MSGS", MODE_PRIVATE);

        friendPhone = getIntent().getStringExtra("Phone");

        Log.d("MYLOG", "Friend: " + friendPhone);

        getTextFromPref();
    }

    public void setTextMessage (View view)
    {
        getTextFromPref();
    }

    private void getTextFromPref()
    {
        textWord = preferences.getString("TextedWord", "NO WORD");
        texterPhone = preferences.getString("TexterPhone", "NO PHONE");
        textView = (TextView) findViewById(R.id.editTextWord);

        boolean phone = true;
        boolean word = true;
        boolean friend = true;
        if(textWord == "NO WORD") word = false;
        if(texterPhone == "NO PHONE") phone = false;
        if(friendPhone == null) friend = false;


        if(!friend && word)
        {
            textView.setText(textWord);
            textWord = "";
            texterPhone = "";
            return;
        }

        if(word && phone)
        {
            if(friendPhone.equals(texterPhone))
            {
                textView.setText(textWord);
                textWord = "";
                texterPhone = "";
            }
            else
            {
                Toast.makeText(this, "No Text from Selected Friend", Toast.LENGTH_LONG).show();
            }
            return;
        }
        if (!word)
        {
            Toast.makeText(this, "No Text Received", Toast.LENGTH_LONG).show();
        }

//        textView = (TextView) findViewById(R.id.editTextWord);
//        textView.setText(textWord);
    }

    public void startMultiPlayerGame(View view)
    {
        String textWord = textView.getText().toString();
        if(textWord.length()>0)
        {
            textView.setText("");

            preferences.edit().remove("TextedWord").commit();
            Log.d("MYLOG", "Removed Texted Word: " + textWord);
            //create intent
            Intent intent = new Intent(this, GameMultiActivity2.class);
            intent.putExtra("GUESS_WORD", textWord);
            //start activity
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this, "No Word Found - TRY GET NEW TEXT", Toast.LENGTH_LONG).show();
        }
    }

    public void onBackPressed()
    {
        AlertDialog.Builder backBuild = new AlertDialog.Builder(this);
        backBuild.setTitle("Back");
        backBuild.setMessage("Do you want to go back to the main menu");
        backBuild.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                nextActivity();
            }
        });
        backBuild.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
        {
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
