package com.example.damie.calculatricev1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private double currentDisplay;
    private double currentOperande;
    private char operation;

    private HashMap<Integer, Button> mesBouttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentDisplay = Double.parseDouble(changeDisplay("0"));
        currentOperande = 0.0;
        mesBouttons = new HashMap<Integer,Button>();
        mesBouttons.put(R.id.buttonCE,(Button) findViewById(R.id.buttonCE));
        mesBouttons.put(R.id.buttonC,(Button) findViewById(R.id.buttonC));
        mesBouttons.put(R.id.buttonBack,(Button) findViewById(R.id.buttonBack));
        mesBouttons.put(R.id.buttonDiv,(Button) findViewById(R.id.buttonDiv));
        mesBouttons.put(R.id.buttonMult,(Button) findViewById(R.id.buttonMult));
        mesBouttons.put(R.id.buttonPlus,(Button) findViewById(R.id.buttonPlus));
        mesBouttons.put(R.id.buttonMin,(Button) findViewById(R.id.buttonMin));
        mesBouttons.put(R.id.buttonEqual,(Button) findViewById(R.id.buttonEqual));
        mesBouttons.put(R.id.buttonColon,(Button) findViewById(R.id.buttonColon));
        mesBouttons.put(R.id.buttonOpp,(Button) findViewById(R.id.buttonOpp));
        mesBouttons.put(R.id.button0,(Button) findViewById(R.id.button0));
        mesBouttons.put(R.id.button1,(Button) findViewById(R.id.button1));
        mesBouttons.put(R.id.button2,(Button) findViewById(R.id.button2));
        mesBouttons.put(R.id.button3,(Button) findViewById(R.id.button3));
        mesBouttons.put(R.id.button4,(Button) findViewById(R.id.button4));
        mesBouttons.put(R.id.button5,(Button) findViewById(R.id.button5));
        mesBouttons.put(R.id.button6,(Button) findViewById(R.id.button6));
        mesBouttons.put(R.id.button7,(Button) findViewById(R.id.button7));
        mesBouttons.put(R.id.button8,(Button) findViewById(R.id.button8));
        mesBouttons.put(R.id.button9,(Button) findViewById(R.id.button9));

        for(Map.Entry<Integer, Button> entreeBoutton : mesBouttons.entrySet()) {
            Button value = entreeBoutton.getValue();
            value.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button0:
                if(currentDisplay ==0.0) currentDisplay = Double.parseDouble(changeDisplay("0"));
                else currentDisplay = Double.parseDouble(appendDisplay("0"));
                break;
            case R.id.button1:
                if(currentDisplay ==0.0) currentDisplay = Double.parseDouble(changeDisplay("1"));
                else currentDisplay = Double.parseDouble(appendDisplay("1"));
                break;
            case R.id.button2:
                if(currentDisplay ==0.0) currentDisplay = Double.parseDouble(changeDisplay("2"));
                else currentDisplay = Double.parseDouble(appendDisplay("2"));
                break;
            case R.id.button3:
                if(currentDisplay ==0.0) currentDisplay = Double.parseDouble(changeDisplay("3"));
                else currentDisplay = Double.parseDouble(appendDisplay("3"));
                break;
            case R.id.button4:
                if(currentDisplay ==0.0) currentDisplay = Double.parseDouble(changeDisplay("4"));
                else currentDisplay = Double.parseDouble(appendDisplay("4"));
                break;
            case R.id.button5:
                if(currentDisplay ==0.0) currentDisplay = Double.parseDouble(changeDisplay("5"));
                else currentDisplay = Double.parseDouble(appendDisplay("5"));
                break;
            case R.id.button6:
                if(currentDisplay ==0.0) currentDisplay = Double.parseDouble(changeDisplay("6"));
                else currentDisplay = Double.parseDouble(appendDisplay("6"));
                break;
            case R.id.button7:
                if(currentDisplay ==0.0) currentDisplay = Double.parseDouble(changeDisplay("7"));
                else currentDisplay = Double.parseDouble(appendDisplay("7"));
                break;
            case R.id.button8:
                if(currentDisplay ==0.0) currentDisplay = Double.parseDouble(changeDisplay("8"));
                else currentDisplay = Double.parseDouble(appendDisplay("8"));
                break;
            case R.id.button9:
                if(currentDisplay ==0.0) currentDisplay = Double.parseDouble(changeDisplay("9"));
                else currentDisplay = Double.parseDouble(appendDisplay("9"));
                break;
            case R.id.buttonMult:
                break;
            case R.id.buttonMin:
                break;
            case R.id.buttonDiv:
                break;
            case R.id.buttonPlus:
                break;
            case R.id.buttonOpp:
                break;
            case R.id.buttonCE:
                currentDisplay = Double.parseDouble(changeDisplay("0"));
                currentOperande = 0.0;
                operation = '\u0000';
                break;
            case R.id.buttonC:
                currentDisplay = Double.parseDouble(changeDisplay("0"));
                break;
            case R.id.buttonBack:
                currentDisplay = Double.parseDouble(backDisplay());
                break;
            case R.id.buttonColon:
                break;
            case R.id.buttonEqual:
                break;
        }

    }

    private String changeDisplay(String newtext){
        TextView current = (TextView) findViewById(R.id.calculDisplay);
        current.setText(newtext);
        return newtext;
    }

    private String appendDisplay(String newtext){
        TextView current = (TextView) findViewById(R.id.calculDisplay);
        String currentDisp = current.getText().toString();
        current.setText(currentDisp+newtext);
        return currentDisp+newtext;
    }

    private String backDisplay(){
        TextView current = (TextView) findViewById(R.id.calculDisplay);
        String currentDisp = current.getText().toString();
        if(currentDisp.length()<=1) currentDisp ="0";
        else currentDisp = currentDisp.substring(0,currentDisp.length()-1);
        current.setText(currentDisp);
        return currentDisp;
    }
}
