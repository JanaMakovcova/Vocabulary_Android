package cz.janamakovcova.vocabulary;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Word {
    @PrimaryKey (autoGenerate = true)
    private int wid;
    private String word_english;
    private String word_czech;

    public Word() {
    }

    public void setWid(int wid) {
        this.wid = wid;
    }

    public int getWid() {
        return wid;
    }

    public String getWord_english() {
        return word_english;
    }

    public void setWord_english(String word_english) {
        this.word_english = word_english;
    }

    public String getWord_czech() {
        return word_czech;
    }

    public void setWord_czech(String word_czech) {
        this.word_czech = word_czech;
    }
}
