package fi.jyu.ohj2.kahepiip.listsapp.model;

/*
Class for future development of the application.

Sovelluksen jatkokehitystä, eli yksittäistä tuotetta varten yksinkertaisempi luokka.
 */

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * An item which can be added/saved to a shopping list
 * and of which completion status is being listened to. Item amount and
 * unit of measure are disregarded.
 */
public class SheetItem extends Sheet {

    private BooleanProperty completion = new SimpleBooleanProperty(false);
    /**
     * An item on a sheet.
     */
    public SheetItem(){}

    /**
     * An item on a sheet
     * @param itemName Name of this item
     * @param completion Status of this item
     */
    public SheetItem(String itemName, boolean completion){
        super(itemName);
        this.setCompletion(completion);
    }

    public void setCompletion(boolean completion) {this.completion.set(completion);}
    public boolean getCompletion() {return this.completion.get();}
}
