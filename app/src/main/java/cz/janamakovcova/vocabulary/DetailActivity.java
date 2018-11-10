package cz.janamakovcova.vocabulary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {
    @BindView(R.id.detail_english)
    public TextView detail_english;
    @BindView(R.id.detail_czech)
    public TextView detail_czech;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);


        detail_english = findViewById(R.id.detail_english);
        detail_czech = findViewById(R.id.detail_czech);

        detail_english.setText(getIntent().getStringExtra("english"));
        detail_czech.setText(getIntent().getStringExtra("czech"));
    }
}
