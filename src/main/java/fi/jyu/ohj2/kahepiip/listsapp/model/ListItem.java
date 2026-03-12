package fi.jyu.ohj2.kahepiip.listsapp.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/*
Class For future development of the application. This is a simplified class for an item on a list,
which will be extended by the class for an ingredient.

Sovelluksen jatkokehitystä, eli yksittäistä tuotetta varten yksinkertaisempi luokka,
jota käytetään reseptin ainesosan yläluokkana.
 */

/**
 * An item on a created list.
 */
public class ListItem {

    private StringProperty itemName = new SimpleStringProperty();
    private BooleanProperty completed = new SimpleBooleanProperty();

    /**
     * An item on a created list.
     */
    public ListItem(){}

    /**
     * An item on a created list
     * @param itemName Name of the item
     * @param completed Status of this item
     */
    public ListItem(String itemName, boolean completed){
        this.itemName.set(itemName);
        this.completed.set(completed);
    }

    public void setItemName(String itemName){this.itemName.set(itemName);}
    public String getItemName() {return this.itemName.get();}
    public StringProperty ItemNameProperty() {return this.itemName;}

    public void setCompleted(boolean completed) {this.completed.set(completed);}
    public boolean getCompleted() {return this.completed.get();}
    public BooleanProperty readyProperty() {return this.completed;}
}
