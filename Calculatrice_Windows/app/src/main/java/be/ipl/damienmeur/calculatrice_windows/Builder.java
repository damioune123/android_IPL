package be.ipl.damienmeur.calculatrice_windows;

import android.app.Application;

import be.ipl.damienmeur.calculatrice_windows.controller.CalcController;
import be.ipl.damienmeur.calculatrice_windows.model.CalcModel;

/**
 * Created by damien on 15.03.17.
 */
public class Builder  extends Application{

    CalcModel model;
    CalcController controller;
    @Override
    public void onCreate() {
        super.onCreate();
        model = new CalcModel();
        controller = new CalcController(model);
    }

    public CalcModel getModel() {
        return model;
    }
    public CalcController getController() {
        return controller;
    }
}
