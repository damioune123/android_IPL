package com.example.damie.fiestasms.model;

import com.example.damie.fiestasms.model.content_provider.LaFeteDbHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by damien on 29.03.17.
 */
public class ParticipantModel {
    private LaFeteDbHelper feteDb;
    private List<SMSModelChangeObserver> observers = new ArrayList<>();
    public ParticipantModel(LaFeteDbHelper feteDb) {
        this.feteDb= feteDb;
    }

    public void registerObserver(SMSModelChangeObserver obs){observers.add(obs);}

    public void unregister(SMSModelChangeObserver obs){
        observers.remove(obs);
    }

    public void addParticipant(Participant participant){
        feteDb.insertParticipant(participant);
        notifyAllObservers();
    }
    public void updateParticipantCursor(){
        feteDb.fetchLast10();
    }

    private void notifyAllObservers(){
        for(SMSModelChangeObserver obs : observers){
            obs.notifyChange();
        }
    }
    public interface SMSModelChangeObserver { //sert à notifier les gens qui nous observent qu'il y a un changement
        public void notifyChange();
    }
}
