package fi.jyu.ohj2.kahepiip.listsapp.model;

/*
Class for future development of the application.

Sovelluksen jatkokehitystä, eli yksittäistä tuotetta varten yksinkertaisempi luokka.
 */

/**
 * An item which can be added/saved to a shopping list
 * and of which completion status is being listened to. Item amount and
 * unit of measure are disregarded.
 */
public class ListItem extends Item{

    /**
     * An item on a created list.
     */
    public ListItem(){}

    /**
     * An item on a created list
     * @param itemName Name of the item
     * @param completion Status of this item
     */
    public ListItem(String itemName, boolean completion){
        this.setItemName(itemName);
        this.setCompletion(completion);
    }
}
