package be.ipl.damienmeur.calculatrice_windows.controller;

import be.ipl.damienmeur.calculatrice_windows.model.CalcModel;

/**
 * Created by damien on 18/06/2017.
 */

public class CalcController {
    private CalcModel model;
    public CalcController(CalcModel model){
        this.model=model;
    }
    private void operation(char operator){
        if(model.isOperandeLeftActif()){
            model.setOperator(operator);
            model.setOperandeLeftActif(false);
            model.setOperandeRight("");
        }else{
            if(!model.getOperandeRight().equals("")){
                model.calc();
                model.setOperandeLeftDisplay(true);
                model.setOperandeRight("");
            }
        }
    }
    public void addOperator(char operator){
        switch(operator){
            case '+':
                operation('+');
                break;
            case '-':
                operation('-');
                break;
            case 'X':
                operation('X');
                break;
            case '/':
                operation('/');
                break;
            case '=':
                if(!model.getOperandeRight().equals("")){
                    model.calc();
                    model.setOperandeLeftDisplay(true);
                    model.setOperandeRight("");
                }
                break;
            case 'b':
                if(model.isOperandeLeftDisplay()){
                    if(model.getOperandeLeft().length()>=1)
                        model.setOperandeLeft(model.getOperandeLeft()
                                .substring(0, model.getOperandeLeft().length()-1));
                }
                else{
                    if(model.getOperandeRight().length()>=1)
                        model.setOperandeRight(model.getOperandeRight().substring(0, model.getOperandeRight().length()-1));
                }
                break;
            case ',':
                if(model.isOperandeLeftDisplay() )
                    model.setOperandeLeft(String.valueOf((Double) 1.0 *Double.valueOf(model.getOperandeLeft())));
                else
                    model.setOperandeRight(String.valueOf((Double) 1.0 *Double.valueOf(model.getOperandeRight())));
                break;
            case 'c':
                if(model.isOperandeLeftDisplay())
                    model.setOperandeLeft("");
                else
                    model.setOperandeRight("");
                break;
            case ';':
                if(model.isOperandeLeftDisplay())
                    model.setOperandeLeft("");
                else
                    model.setOperandeRight("");
                break;
        }
        model.notifyAllObservers();
    }
    public void addDigit(char digit){
        model.setOperandeLeftDisplay(model.isOperandeLeftActif());
        if(model.isOperandeLeftActif()){
            if(model.getOperandeLeft().equals("0"))
                model.setOperandeLeft("");
            model.setOperandeLeft(model.getOperandeLeft()+digit);
        }
        else{
            model.setOperandeRight(model.getOperandeRight()+digit);
        }
        model.notifyAllObservers();
    }

}
