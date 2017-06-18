package be.ipl.damienmeur.calculatrice_windows.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import be.ipl.damienmeur.calculatrice_windows.Builder;
import be.ipl.damienmeur.calculatrice_windows.R;
import be.ipl.damienmeur.calculatrice_windows.controller.CalcController;
import be.ipl.damienmeur.calculatrice_windows.model.CalcModel;

public class CalcActivity extends AppCompatActivity implements CalcModel.CalcModelChangeObserver {

    private CalcModel model;
    private CalcController controller;
    private TextView displayTextView;

    private View.OnClickListener onOperatorClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            controller.addOperator(((Button) view).getTag().toString().charAt(0));
        }
    };
    private View.OnClickListener onDigitClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            controller.addDigit(((Button) v).getText().toString().charAt(0));//

        }
    };
    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayTextView = (TextView) findViewById(R.id.calculDisplay);
        model = ((Builder)getApplication()).getModel();
        model.registerObserver(this);
        controller = ((Builder)getApplication()).getController();
        displayTextView.setText("");
        //Register digits buttons
        int[] digitIds = {R.id.button0,R.id.button1, R.id.button2, R.id.button3,R.id.button4,R.id.button5,R.id.button6,R.id.button7,R.id.button8,R.id.button9};
        for(int id : digitIds) {
            Button button = (Button) findViewById(id);
            button.setOnClickListener(onDigitClickListener);
        }
        //Register operator buttons
        int[] operatorsIds = {R.id.buttonMult, R.id.buttonDiv, R.id.buttonPlus, R.id.buttonMin, R.id.buttonEqual, R.id.buttonOpp, R.id.buttonCE, R.id.buttonC, R.id.buttonColon, R.id.buttonBack};
        for(int id : operatorsIds){
            Button button = (Button) findViewById(id);
            button.setOnClickListener(onOperatorClickListener);
        }
    }

    @Override
    public void notifyChange() {
        String display;
        displayTextView =(TextView) findViewById(R.id.calculDisplay);
        if( model.isOperandeLeftDisplay()){
            display = model.getOperandeLeft();
        }
        else{
            display = model.getOperandeRight();
        }
        displayTextView.setText(display);
    }
}
