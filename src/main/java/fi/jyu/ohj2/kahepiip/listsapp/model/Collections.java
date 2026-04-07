package fi.jyu.ohj2.kahepiip.listsapp.model;

import javafx.collections.ObservableList;

import java.util.List;

/**
 * Interface for Recipes and ItemCollections
 */
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

    /**
     * Save items to JSON-file.
     */
    void save();

    /**
     * Read and set items from JSON-file.
     */
    void load();

    /**
     *
     * @return items as ObservableList
     */
    ObservableList<ListItem> getItems();

    /**
     * Sets List of ListItems to ObservableList
     * @param items List of ListItems
     */
    void setItems(List<ListItem> items);


}
