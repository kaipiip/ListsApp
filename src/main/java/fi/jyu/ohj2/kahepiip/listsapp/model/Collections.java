package fi.jyu.ohj2.kahepiip.listsapp.model;

import javafx.collections.ObservableList;

import java.awt.*;
import java.util.List;

public interface Collections {

    /**
     * Creates and adds item to collection.
     * @param title item title
     */
    void addItem(String title);

    /**
     * Adds specified item to collection
     * @param item item to be added to collection.
     */
    void addItem(ListItem item);

    /**
     * Removes specified item  from collection.
     * @param item item to be removed from collection
     */
    void removeItem(ListItem item);
    void save();
    void load();
    ObservableList<ListItem> getItems();
    void setItems(ObservableList<ListItem> items);


}
