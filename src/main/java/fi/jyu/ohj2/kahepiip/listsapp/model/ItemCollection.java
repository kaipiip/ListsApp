package fi.jyu.ohj2.kahepiip.listsapp.model;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

/**
 * Observable list for ListItems
 */
public class ItemCollection implements Collections {
    private ObservableList<ListItem> items = FXCollections.observableArrayList(
            item -> new Observable[]{
                    item.titleProperty(),
                    item.completionProperty()
            });

    public ItemCollection(){
        items.addListener((ListChangeListener<ListItem>) change -> {
        // save();
        });
    }

    @Override
    public ObservableList<ListItem> getItems() {return this.items;}
    @Override
    public void addItem(String title) {items.add(new ListItem(title.trim()));}

    @Override
    public void removeItem(ListItem item) {
        if (item == null){
            return;
        }
        items.remove(item);
    }

    @Override
    public void save() {}

    @Override
    public void load() {}
}
