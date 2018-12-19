package cz.janamakovcova.vocabulary;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AllActivity extends AppCompatActivity {

    @BindView(R.id.list_all)
    public ListView list_all;

    private WordAdapter mainAdapter;
    private VocabularyDatabase vocabulary;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all);
        ButterKnife.bind(this);
        vocabulary = Room.databaseBuilder(this, VocabularyDatabase.class, "db_vocabulary").allowMainThreadQueries().build();

        mainAdapter = new WordAdapter(this, vocabulary);

        list_all.setAdapter(mainAdapter);

    }
}
