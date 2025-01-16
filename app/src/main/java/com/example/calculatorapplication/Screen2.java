package com.example.calculatorapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Screen2 extends AppCompatActivity {
    String value="";
    String result="";

    String calculations="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.screen2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView textview=findViewById(R.id.ShowValue);
        textview.setText("Enter the Value");

        TextView Answerview=findViewById(R.id.AnsValue);
        Answerview.setText("0");

        int [] number_bt={
                R.id.btn_1,R.id.btn_2,R.id.btn_3,R.id.btn_4,R.id.btn_5,R.id.btn_6,R.id.btn_7,R.id.btn_8,R.id.btn_9,R.id.btn_0,R.id.btn_dot,R.id.btn_add,R.id.btn_subtract,R.id.btn_multiply,R.id.btn_divide
        };

        for(int id:number_bt){
            findViewById(id).setOnClickListener(v -> {
                Button bt=findViewById(id);

                value=value+bt.getText().toString();
                textview.setText(value);
            });
        }

        SharedPreferences preferences=getApplicationContext().getSharedPreferences("Calculationinfo", Context.MODE_PRIVATE);

        Button total=findViewById(R.id.btn_equals);
        total.setOnClickListener(v->{
            result="";
            if(value.matches(".*[*/+\\-].*")){
                result=evaluateExpression(value);
                Answerview.setText(result);
            }
            else{
                Answerview.setText(value);
            }
            calculations=preferences.getString("History","None");
            calculations=value+"="+result+"\n\n"+calculations;
            SharedPreferences.Editor editor=preferences.edit();
            editor.putString("History",calculations);
            editor.apply();
        });

        Button BtnClear=findViewById(R.id.btn_clear);
        BtnClear.setOnClickListener(v->{
            textview.setText("Enter the value");
            Answerview.setText("0");
            value="";
            result="";
        });

        Button BtnExit=findViewById(R.id.btn_Back);
        BtnExit.setOnClickListener(v->{
            finish();
        });

        Button BtnHistory=findViewById(R.id.btn_history);
        BtnHistory.setOnClickListener(v->{
            Intent intent=new Intent(this, HistoryScreen.class);
            startActivity(intent);
        });
    }
    public static String evaluateExpression(String tvalue) {
        if(tvalue.matches(".*[/].*")){
            int index=tvalue.indexOf("/");

            //23*45/8+9
            String start=tvalue.substring(0,index);
            String end=tvalue.substring(index+1);

            int lastindex=lastindexofoprator(start);
            if(lastindex==Integer.MIN_VALUE){
                lastindex=-1;
            }
            start=tvalue.substring(0,lastindex+1);

            int firstindex=Firstindexofoprator(end);
            if(firstindex==Integer.MAX_VALUE){
                firstindex=tvalue.length()-index-1;
            }
            end=tvalue.substring(index+firstindex+1);

            String str1=tvalue.substring(lastindex+1,index);
            String str2=tvalue.substring(index+1,index+firstindex+1);

            double num1=Double.valueOf(str1);
            double num2=Double.valueOf(str2);

            String middle=String.valueOf(num1/num2);
            return evaluateExpression(start+middle+end);
        }
        if(tvalue.matches(".*[*].*")){
            int index=tvalue.indexOf("*");

            String start=tvalue.substring(0,index);
            String end=tvalue.substring(index+1);

            int lastindex=lastindexofoprator(start);
            if(lastindex==Integer.MIN_VALUE){
                lastindex=-1;
            }
            start=tvalue.substring(0,lastindex+1);

            int firstindex=Firstindexofoprator(end);
            if(firstindex==Integer.MAX_VALUE){
                firstindex=tvalue.length()-index-1;
            }
            end=tvalue.substring(index+firstindex+1);

            String str1=tvalue.substring(lastindex+1,index);
            String str2=tvalue.substring(index+1,index+firstindex+1);

            double num1=Double.valueOf(str1);
            double num2=Double.valueOf(str2);

            String middle=String.valueOf(num1*num2);
            return evaluateExpression(start+middle+end);

        }
        if(tvalue.matches(".*[+].*")){
            int index=tvalue.indexOf("+");

            String start=tvalue.substring(0,index);
            String end=tvalue.substring(index+1);

            int lastindex=lastindexofoprator(start);
            if(lastindex==Integer.MIN_VALUE){
                lastindex=-1;
            }
            //12*3*7/4+2
            start=tvalue.substring(0,lastindex+1);

            int firstindex=Firstindexofoprator(end);
            if(firstindex==Integer.MAX_VALUE){
                firstindex=tvalue.length()-index-1;
            }
            end=tvalue.substring(index+firstindex+1);

            String str1=tvalue.substring(lastindex+1,index);
            String str2=tvalue.substring(index+1,index+firstindex+1);

            double num1=Double.valueOf(str1);
            double num2=Double.valueOf(str2);

            String middle=String.valueOf(num1+num2);
            return evaluateExpression(start+middle+end);
        }
        if(tvalue.matches(".*[\\-].*")){
            int index=tvalue.indexOf("-");

            String start=tvalue.substring(0,index);
            String end=tvalue.substring(index+1);

            int lastindex=lastindexofoprator(start);
            if(lastindex==Integer.MIN_VALUE){
                lastindex=-1;
            }
            start=tvalue.substring(0,lastindex+1);

            int firstindex=Firstindexofoprator(end);
            if(firstindex==Integer.MAX_VALUE){
                firstindex=tvalue.length()-index-1;
            }
            end=tvalue.substring(index+firstindex+1);

            String str1=tvalue.substring(lastindex+1,index);
            String str2=tvalue.substring(index+1,index+firstindex+1);

            double num1=Double.valueOf(str1);
            double num2=Double.valueOf(str2);

            String middle=String.valueOf(num1-num2);
            System.out.println(middle);
            return evaluateExpression(start+middle+end);
        }
        return tvalue;
    }
    static int lastindexofoprator(String expression){
        int[] a = new int[4];
        a[0] = (expression.lastIndexOf('*')!=-1) ? (expression.lastIndexOf('*')):(Integer.MIN_VALUE);
        a[1] = (expression.lastIndexOf('/')!=-1) ? (expression.lastIndexOf('/')):(Integer.MIN_VALUE);
        a[2] = (expression.lastIndexOf('+')!=-1) ? (expression.lastIndexOf('+')):(Integer.MIN_VALUE);
        a[3] = (expression.lastIndexOf('-')!=-1) ? (expression.lastIndexOf('-')):(Integer.MIN_VALUE);

        Arrays.sort(a);
        return a[3];
    }

    static int Firstindexofoprator(String expression){
        int[] a=new int[4];
        a[0] = (expression.indexOf('*')!=-1) ? (expression.indexOf('*')):(Integer.MAX_VALUE);
        a[1] = (expression.indexOf('/')!=-1) ? (expression.indexOf('/')):(Integer.MAX_VALUE);
        a[2] = (expression.indexOf('+')!=-1) ? (expression.indexOf('+')):(Integer.MAX_VALUE);
        a[3] = (expression.indexOf('-')!=-1) ? (expression.indexOf('-')):(Integer.MAX_VALUE);

        Arrays.sort(a);
        return a[0];
    }
}