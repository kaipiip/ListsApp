package fi.jyu.ohj2.kahepiip.listsapp.model;

import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

/**
 * Basic operations for item lists and recipes
 */
public abstract class List {

    private StringProperty listHeader = new SimpleStringProperty();

    public void setListHeader(String header){this.listHeader.set(header);}
    public String getListHeader(){return this.listHeader.get();}
    public StringProperty ListHeaderProperty(){return this.listHeader;}

    private ObservableList<Item> items = FXCollections.observableArrayList(
            item -> new Observable[] {
                    item.ItemNameProperty(),
                    item.completionProperty(),
                    item.amountProperty(),
                    item.unitProperty()
            }
    );

    public ObservableList<Item> getItems() {
        return this.items;
    }

    /**
     * Listens to changes in object properties, and saves new changes.
     * @param observableList A list which's changes to observe
     */
    public void Listener(ObservableList<Item> observableList){
        observableList.addListener((ListChangeListener<Item>) change ->{
            save(observableList);
        });
    }


    /**
     * Creates and adds object to specified list.
     * @param itemName Item name
     */
    public void addItem(String itemName){
    }

    /**
     * Removes object from specified list.
     * @param item object type (ListItem/Ingredient)
     */
    public void removeItem(Item item) {
        if (item == null){
            return;
        }
        items.remove(item);
    }

    /**
     * Saves specified list to .json
     * @param observableList List to be saved
     */
    public void save(ObservableList<Item> observableList){
    }
}
