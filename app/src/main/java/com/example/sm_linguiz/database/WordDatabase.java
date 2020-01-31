package com.example.sm_linguiz.database;

import android.content.Context;
import android.content.res.AssetManager;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = {Word.class}, version = 1, exportSchema = false)
public abstract class WordDatabase extends RoomDatabase {
    public abstract WordDao wordDao();

    private static volatile WordDatabase INSTANCE;
    public static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    static AssetManager assetManager;
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            databaseWriteExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    WordDao dao = INSTANCE.wordDao();
//                    dao.deleteAll();//todo usunac te linie
//
//                    String[] levels = {"A1", "A2", "B1", "B2", "C1", "C2"};
//                    for (String level : levels) {
//
//                        Scanner scanner = null;
//                        try {
//                            scanner = new Scanner(
//                                    assetManager.open(level.toLowerCase() + "dictionary")
//                            );
//                        } catch (IOException ignore) {
//                            System.err.println("[CONSOLE DEBUG]Scanner is null");
//                        }
//                        String line;
//                        while (scanner.hasNext()) {
//                            line = scanner.nextLine();
//                            if (!line.startsWith("#") && !line.isEmpty()) {
//                                String[] columns = line.split("=");
//                                dao.insert(new Word(columns[0], columns[1], level));
//                            }
//                        }
//                        scanner.close();
//                    }
                }
            });
        }
    };

    static WordDatabase getDatabase(final Context context) {

        assetManager = context.getAssets();

        if (INSTANCE == null) {
            synchronized (WordDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WordDatabase.class, "word_db")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
