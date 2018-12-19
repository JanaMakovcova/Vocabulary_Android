package cz.janamakovcova.vocabulary;

import android.arch.persistence.room.Room;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GameActivity extends AppCompatActivity {

    @BindView(R.id.word_guess)
    public TextView word_guess;

    @BindView(R.id.textView_choice1)
    public TextView textView_choice1;

    @BindView(R.id.textView_choice2)
    public TextView textView_choice2;

    @BindView(R.id.textView_choice3)
    public TextView textView_choice3;

    @BindView(R.id.btn_game)
    public Button btn_game;

    private VocabularyDatabase vocabulary;
    private int randomInteger;

    @BindView(R.id.speechButton)
    public ImageButton speechButton;

    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ButterKnife.bind(this);

        vocabulary = Room.databaseBuilder(this, VocabularyDatabase.class, "db_vocabulary").allowMainThreadQueries().build();
        randomInteger = newGame();
        textView_choice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (randomInteger == 0){
                    Toast.makeText(GameActivity.this, "Good job!", Toast.LENGTH_SHORT).show();
                    textView_choice2.setText("");
                    textView_choice3.setText("");
                } else {
                    Toast.makeText(GameActivity.this, "Try again", Toast.LENGTH_SHORT).show();
                }
            }
        });
        textView_choice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (randomInteger == 1){
                    Toast.makeText(GameActivity.this, "Good job!", Toast.LENGTH_SHORT).show();
                    textView_choice1.setText("");
                    textView_choice3.setText("");
                } else {
                    Toast.makeText(GameActivity.this, "Try again", Toast.LENGTH_SHORT).show();
                }
            }
        });

        textView_choice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (randomInteger == 2){
                    Toast.makeText(GameActivity.this, "Výborně!", Toast.LENGTH_SHORT).show();
                    textView_choice1.setText("");
                    textView_choice2.setText("");
                } else {
                    Toast.makeText(GameActivity.this, "Špatně", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                randomInteger = newGame();
            }
        });

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(Locale.UK);
                }
            }
        });

        speechButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toSpeak = word_guess.getText().toString();
                Toast.makeText(getApplicationContext(), toSpeak,Toast.LENGTH_SHORT).show();
                textToSpeech.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
            }
        });

    }

    private int newGame() {
        List<Word> gameData;
        gameData = vocabulary.wordDao().getRandom3();
        Random r = new Random();
        final int randomInteger = r.nextInt(2);
        word_guess.setText(gameData.get(randomInteger).getWord_english());
        textView_choice1.setText(gameData.get(0).getWord_czech());
        textView_choice2.setText(gameData.get(1).getWord_czech());
        textView_choice3.setText(gameData.get(2).getWord_czech());
        return randomInteger;
    }
    public void onPause(){
        if(textToSpeech !=null){
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onPause();
    }

}
