package cz.janamakovcova.vocabulary;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface WordDao {

    @Insert
    void insertAll(Word... words);

    @Delete
    void delete(Word word);

    @Query("SELECT * FROM word")
    List<Word> getAll();

    @Query("SELECT * FROM word WHERE wid = :wid")
    Word getByWid(int wid);

    @Query("SELECT * FROM word WHERE word_english = :word_english AND word_czech = :word_czech")
    Word getByTranslate(String word_english, String word_czech);

    @Query("DELETE FROM word WHERE wid = :wid")
    void deleteQuery(int wid);

}
