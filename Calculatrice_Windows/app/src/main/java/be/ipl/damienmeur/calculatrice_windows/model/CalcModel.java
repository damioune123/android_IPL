package be.ipl.damienmeur.calculatrice_windows.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class CalcModel {
    private boolean operandeLeftActif =true, operandeLeftDisplay=true;
    private char operator;
    private String operandeLeft="0", operandeRight="";
    private List<CalcModelChangeObserver> observers = new ArrayList<>();

    public void registerObserver(CalcModelChangeObserver obs){
        observers.add(obs);
    }

    public void unregister(CalcModelChangeObserver obs){
        observers.remove(obs);
    }

    public void notifyAllObservers(){
        for(CalcModelChangeObserver obs : observers){
            obs.notifyChange();
        }
    }
    public void calc() {
        Double res;
        if(operandeRight.equals("")) return;
        switch(operator){
            case '+':
                res =Double.parseDouble(operandeLeft)+Double.parseDouble(operandeRight);
                break;
            case '/':
                res =Double.parseDouble(operandeLeft)/Double.parseDouble(operandeRight);
                break;
            case 'X':
                res =Double.parseDouble(operandeLeft)*Double.parseDouble(operandeRight);
                break;
            case '-':
                res =Double.parseDouble(operandeLeft)-Double.parseDouble(operandeRight);
                break;
            default: return;
        }
        operandeLeft = res.toString();
        operandeLeftActif=true;
        //
    }
    public boolean isOperandeLeftActif() {
        return operandeLeftActif;
    }

    public boolean isOperandeLeftDisplay() {
        return operandeLeftDisplay;
    }

    public char getOperator() {
        return operator;
    }

    public String getOperandeLeft() {
        return operandeLeft;
    }

    public String getOperandeRight() {
        return operandeRight;
    }

    public interface CalcModelChangeObserver { //sert à notifier les gens qui nous observent qu'il y a un changement
        void notifyChange();
    }

    public void setOperandeLeftActif(boolean operandeLeftActif) {
        this.operandeLeftActif = operandeLeftActif;
    }

    public void setOperandeLeftDisplay(boolean operandeLeftDisplay) {
        this.operandeLeftDisplay = operandeLeftDisplay;
    }

    public void setOperator(char operator) {
        this.operator = operator;
    }

    public void setOperandeLeft(String operandeLeft) {
        this.operandeLeft = operandeLeft;
    }

    public void setOperandeRight(String operandeRight) {
        this.operandeRight = operandeRight;
    }

    public List<CalcModelChangeObserver> getObservers() {
        return observers;
    }

    public void setObservers(List<CalcModelChangeObserver> observers) {
        this.observers = observers;
    }
}
