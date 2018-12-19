package cz.janamakovcova.vocabulary;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.editText_english)
    public EditText ediText_english;

    @BindView(R.id.editText_czech)
    public EditText ediText_czech;

    @BindView(R.id.btn_add)
    public Button btn_add;

    @BindView(R.id.btn_show)
    public Button btn_show;

    @BindView(R.id.list_main)
    public ListView list_main;

    @BindView(R.id.btn_game)
    public Button btn_game;

    private Word word;

    private VocabularyDatabase vocabulary;
    private WordAdapter mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        vocabulary = Room.databaseBuilder(this, VocabularyDatabase.class, "db_vocabulary").allowMainThreadQueries().build();
        mainAdapter = new WordAdapter(this, vocabulary);
        list_main.setAdapter(mainAdapter);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String english = ediText_english.getText().toString();
                String czech = ediText_czech.getText().toString();

                word = new Word();
                word.setWord_english(english);
                word.setWord_czech(czech);


                if (vocabulary.wordDao().getByTranslate(english, czech)==null) {
                    vocabulary.wordDao().insertAll(word);
                    ediText_english.setText("");
                    ediText_czech.setText("");
                    mainAdapter.notifyDataSetChanged();

                } else {

                    Toast.makeText(MainActivity.this, "Allready in vocabulary", Toast.LENGTH_SHORT).show();
                }

            }
        });
        list_main.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {


                vocabulary.wordDao().delete(vocabulary.wordDao().getByWid((int)id));
                mainAdapter.notifyDataSetChanged();
                return true;
            }
        });

        list_main.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("english", vocabulary.wordDao().getByWid((int) id).getWord_english());
                intent.putExtra("czech", vocabulary.wordDao().getByWid((int)id).getWord_czech());
                startActivity(intent);

            }
        });

        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AllActivity.class);
                startActivity(intent);
            }
        });
        btn_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(vocabulary.wordDao().getAll().size() > 3) {
                    Intent intent = new Intent(MainActivity.this, GameActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Málo slovíček pro hru", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}
