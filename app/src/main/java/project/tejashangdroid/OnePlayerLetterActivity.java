package project.tejashangdroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class OnePlayerLetterActivity extends AppCompatActivity
{

    EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_player_letter);

        editText = (EditText) findViewById(R.id.editTextWord);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.d("MYLOG", "beforeTextChanged" + "Start" + start + "Count" + count + "After" + after);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d("MYLOG", "onTextChanged" + "Start" + start + "Before" + before + "Count" + count);
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d("MYLOG", "afterTextChanged" + s);
            }
        });
    }

    public void startMultiPlayerGame(View view)
    {
        //Create new editText object from XML
        EditText editText = (EditText) findViewById(R.id.editTextWord);
        //get word and cast word to a String
        String wordToGuess = editText.getText().toString();
        //debug
        Log.d("MYLOG", "One-Player Word: " + wordToGuess);
        //create intent
        Intent intent = new Intent(this, SinglePlayerActivity.class);
        intent.putExtra("GUESS_WORD", wordToGuess);
        //start activity
        startActivity(intent);

    }




}
