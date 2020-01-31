package com.example.sm_linguiz.ui;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.sm_linguiz.R;
import com.example.sm_linguiz.database.DictionaryViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    public static final String LEARN_OR_TEST = "learn_or_test";

    public static int[] skillSums = new int[6];
    private DictionaryViewModel dictionaryViewModel;
    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("MainActivity", "onCreate");

        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

//        for (String level : Level.levels) {
//            dictionaryViewModel = new ViewModelProvider(this).get(DictionaryViewModel.class);
//            dictionaryViewModel.getSkillSumByLevel(level).observe((LifecycleOwner) this, new Observer<Integer>() {
//                @Override
//                public void onChanged(@Nullable final Integer skillSum) {
//                    Log.d("MainActivity", "onCreate->onChange[PR]");
//                    if (skillSum != null) skillSums[i] += skillSum;
//                }
//            });
//            i++;
//        }
    }
}
