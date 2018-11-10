package cz.janamakovcova.vocabulary;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(version = 1,entities = {Word.class})
public abstract class VocabularyDatabase extends RoomDatabase {
    public abstract WordDao wordDao();

}
