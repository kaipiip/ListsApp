package fi.jyu.ohj2.kahepiip.listsapp.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Abstract parent-class for Lists.
 */
public abstract class RecipeParent {
    /*------- ATTRIBUTES -------*/
    protected final StringProperty name = new SimpleStringProperty();

    /*------- SETTERS -------*/
    public void setName(String name){
        if(name.isBlank()){
            return;
        }
        this.name.set(name);
    }

    /*------- GETTERS -------*/
    public String getName(){return this.name.get();}
    public StringProperty nameProperty(){return this.name;}
}
