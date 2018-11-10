package cz.janamakovcova.vocabulary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class WordAdapter extends BaseAdapter {

    private Context context;
    private List<Word> data;

    public WordAdapter(Context context, List<Word> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Word getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).getWid();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_words, parent, false);

        TextView english = view.findViewById(R.id.list_words_english);
        TextView czech = view.findViewById(R.id.list_words_czech);


        Word word = getItem(position);

        english.setText(word.getWord_english());
        czech.setText(word.getWord_czech());
        return view;
    }
}
