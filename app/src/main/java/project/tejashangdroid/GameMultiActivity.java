package project.tejashangdroid;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class GameMultiActivity extends AppCompatActivity
{

    String theWord = "";
    int badLetterCount = 0;
    int goodLetterCount = 0;
    int points=0;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_game);
        //get the word from the intent
        String wordToGuess="";
        wordToGuess=getIntent().getStringExtra("GUESS_WORD");
        Log.d("MYLOG", "Word Sent: " + wordToGuess);

        createTextViews(wordToGuess);

        theWord = wordToGuess;
        //setWord();
    }

    private void createTextViews(String word)
    {
        //get the Layout
        LinearLayout layout = (LinearLayout) findViewById(R.id.layoutLetters);

        for(int i=0; i< word.length(); i++)
        {
            TextView textView = (TextView) getLayoutInflater().inflate(R.layout.single_letter,null);
            layout.addView(textView);
        }
    }


    //called when user clicks on button set in activity_multi_game.xml
    public void newLetter(View view)
    {


        //find the text box with a letter in it using the id... then cast it to an EditText object
        EditText editText = (EditText) findViewById(R.id.editTextLetter);

        //set it to my String variable
        String letter = editText.getText().toString();

        //blank out text field for my next guess
        editText.setText("");

        //test I am getting the letter
        Log.d("MYLOG", "The letter is: " + letter);

        //check the length of the letter.
        if(letter.length() == 1)
        {
            checkLetter(letter);
        }
        else //                  (context, text, duration)
        {
            Toast.makeText(this, "Please Enter a Single Letter", Toast.LENGTH_SHORT).show();
        }
    }
    //receives the users letter guess and checks if the letter given exist in the word
    public void checkLetter(String letter)
    {


        //phrase to char for comparison
        char aLetter = letter.charAt(0);

        //tracks if the letter was found in the word
        boolean letterGuessed = false;


        //loop to look for the new letter
        for(int i=0;i<theWord.length();i++)
        {
            if(theWord.charAt(i)==aLetter)
            {
                Log.d("MYLOG", "Letter found " + aLetter);
                letterGuessed=true;
                goodLetterCount++;
                showLetter(i, aLetter);
            }
        }

        if(!letterGuessed)
        {
            Log.d("MYLOG", "Letter Not Found " + aLetter);
            badLetterCount++;
            wrongLetter(Character.toString(aLetter));
        }
        

        letterGuessed = false;

        Log.d("MYLOG", "Check For Win:  " + goodLetterCount + " " + theWord.length());

        if (goodLetterCount == theWord.length())
        {

            points++;
            displayWin();
            //Intent intent = new Intent(this, GameOverActivity.class);
            //startActivity(intent);
        }
        if (goodLetterCount > theWord.length())
        {
            points++;
            displayWin();
        }
    }

    public void wrongLetter(String badLetter)
    {
        TextView textView = (TextView) findViewById(R.id.textViewWrong);

        //phrase to char for comparison
        String previousLetters = textView.getText().toString();

        //tracks if the letter was found in the word
        Log.d("MYLOG", "Bad Letter: " + badLetter + " " + badLetterCount);

        //loop to look for the new letter
        if (badLetterCount > 1)
        {
            textView.setText(previousLetters + " " + badLetter);
        }
        else
        {
            textView.setText(badLetter);
        }

        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        if(badLetterCount == 1)
            imageView.setImageResource(R.drawable.hatedroid_1);
        if(badLetterCount == 2)
            imageView.setImageResource(R.drawable.hatedroid_2);
        if(badLetterCount == 3)
            imageView.setImageResource(R.drawable.hatedroid_3);
        if(badLetterCount == 4)
            imageView.setImageResource(R.drawable.hatedroid_4);
        if(badLetterCount == 5)
            imageView.setImageResource(R.drawable.hatedroid_5);
        if(badLetterCount > 5)
        {

//            Intent intent = new Intent(this, GameOverActivity.class);
//            startActivity(intent);
            //gameOver();
            displayLoss();
        }
    }
    //position: position the letter is found
    //letterGuessed: user guessed letter
    //change the display layout with the new letter
    public void showLetter(int position, char letterGuessed)
    {
        //make a reference at the LinearLayout in the activity_game.xml using the R reference
        LinearLayout layout = (LinearLayout) findViewById(R.id.layoutLetters);

        //make a reference of the textView of the child within the layout that matches the position of the guessed letter
        TextView textView = (TextView) layout.getChildAt(position);

        //replace the "_" of the view of the guessed letter to the textView
        textView.setText(Character.toString(letterGuessed));
    }

//    public void youWin()
//    {
//        Intent intent = new Intent(this, WinMultiPlayerActivity.class);
//        intent.putExtra("PointsID", points);
//        startActivity(intent);
//
//
//        clearScreen();
//        setWord();
//
//    }

    private void setWord2()
    {
        //explicit intent sends a message to start the activity
        Intent intent = new Intent(this, MultiPlayerActivity.class);
        startActivity(intent);




    }




    public void displayWin()
    {
        AlertDialog.Builder winBuild = new AlertDialog.Builder(this);
        winBuild.setTitle("Congratulations");
        winBuild.setMessage("You win!");
        winBuild.setNeutralButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                clearScreen();
                setWord2();
            }
        });
        winBuild.show();
    }

//    public void gameOver()
//    {
//        Intent intent = new Intent(this, GameOverActivity.class);
//        intent.putExtra("PointsID", points);
//        startActivity(intent);
//
//        clearScreen();
//
//    }

    public void displayLoss()
    {
        AlertDialog.Builder lossBuild = new AlertDialog.Builder(this);
        lossBuild.setTitle("Sorry");
        lossBuild.setMessage("The other player won! Better luck next time!");
        lossBuild.setNeutralButton("OK", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                clearScreen();
                setWord2();
            }
        });
        lossBuild.show();
    }



    public void clearScreen()
    {
        TextView textView = (TextView) findViewById(R.id.textViewWrong);
        textView.setText("Guessed Letters");

        badLetterCount = 0;
        goodLetterCount = 0;

        LinearLayout layout = (LinearLayout) findViewById(R.id.layoutLetters);

        for(int i=0;i<layout.getChildCount();i++)
        {
            TextView childViewText = (TextView) layout.getChildAt(i);
            childViewText.setText("_");
        }

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.hangdroid_0);
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
        backBuild.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
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
