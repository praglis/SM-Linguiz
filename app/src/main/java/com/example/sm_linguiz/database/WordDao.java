package com.example.sm_linguiz.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface WordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Word word);

    @Update
    public void update(Word word);

    @Delete
    public void delete(Word word);

    @Query("DELETE FROM word")
    public void deleteAll();

    @Query("SELECT * FROM word ORDER BY skill")
    public LiveData<List<Word>> findAll();

    @Query("SELECT * FROM word WHERE level LIKE :level ORDER BY skill")
    public LiveData<List<Word>> findWordsFromLevel(String level);
}
