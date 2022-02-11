package com.example.fragmentexample1updated;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button nOpenButton;
    private boolean isFragmentDisplayed;
    public static  final String FRAGMENT_STATE = "fragment_state";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nOpenButton = findViewById(R.id.open_button);

        if (savedInstanceState !=null){
            isFragmentDisplayed = savedInstanceState.getBoolean(FRAGMENT_STATE);

            if(isFragmentDisplayed){
                displayFragment();
            }
        }

        nOpenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isFragmentDisplayed) {
                    displayFragment();
                } else {
                    closeFragment();
                }
            }
        });

    }
    public void displayFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        SimpleFragment simpleFragment = SimpleFragment.newInstance();
        fragmentTransaction.add(R.id.fragment_container, simpleFragment).addToBackStack(null).commit();

        nOpenButton.setText(R.string.close);
        isFragmentDisplayed = true;

    }

    public void closeFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        SimpleFragment simpleFragment = (SimpleFragment) fragmentManager.findFragmentById(R.id.fragment_container);
        fragmentTransaction.remove(simpleFragment).commit();

        nOpenButton.setText(R.string.open);
        isFragmentDisplayed = false;
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putBoolean(FRAGMENT_STATE,isFragmentDisplayed);
        super.onSaveInstanceState(outState);
    }
}
