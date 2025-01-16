package com.example.calculatorapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HistoryScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.history_screen);


        SharedPreferences preferences=getApplicationContext().getSharedPreferences("Calculationinfo", Context.MODE_PRIVATE);

        String calculations=preferences.getString("History","None");

        TextView history_text=findViewById(R.id.TextHistory);
        if(calculations.isEmpty()){
            history_text.setText("None");
        }
        else{
            history_text.setText(calculations);
        }


        Button BtBack=findViewById(R.id.History_Back);
        BtBack.setOnClickListener(v->{
            finish();
        });

        Button Btclearhis=findViewById(R.id.btn_ClearHistory);
        Btclearhis.setOnClickListener(v->{
            SharedPreferences.Editor editor=preferences.edit();
            editor.putString("History","");
            editor.apply();
            history_text.setText("None");
        });

    }
}