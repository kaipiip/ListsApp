package fi.jyu.ohj2.kahepiip.listsapp.model;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * ItemCollection-object. Contains ObservableList of ListItems.
 */
public class ItemCollection implements Collections {
    private Path path = Path.of("shopping-list.json");
    private final ObjectMapper mapper = new ObjectMapper();

    /**
     * ObservableList for ListItems
     * Observes item's title and completion -properties
     */
    private ObservableList<ListItem> items = FXCollections.observableArrayList(
            item -> new Observable[]{
                    item.titleProperty(),
                    item.completionProperty()
            });

    /**
     * New ItemCollection-object which contains listener for ObservableList's properties.
     * Changes are saved.
     */
    public ItemCollection(){
            items.addListener((ListChangeListener<ListItem>) change -> {
                save();
            });
    }

    public ItemCollection(String path){
        this.path = Path.of(path);
        items.addListener((ListChangeListener<ListItem>) change -> {
            save();
        });

    }

    /**
     *
     * @return ListItems as ObservableList
     */
    @Override
    public ObservableList<ListItem> getItems() {return this.items;}

    /**
     * Sets List of ListItems to ItemCollection's ObservableList
     * @param items List of ListItems
     */
    @Override
    public void setItems(List<ListItem> items){this.items.setAll(items);}

    /**
     * Creates new ListItem and adds it to this ItemCollection.
     * @param title ListItem title
     */
    @Override
    public void addItem(String title) {items.add(new ListItem(title.trim()));}

    /**
     * Adds existing ListItem to this ItemCollection.
     * @param item item to be added to collection.
     */
    @Override
    public void addItem(ListItem item) {items.add(item);}

    /**
     * Removes specified ListItem from this ItemCollection
     * @param item item to be removed from collection
     */
    @Override
    public void removeItem(ListItem item) {
        if (item == null){
            return;
        }
        items.remove(item);
    }

    /**
     * Saves ListItems to JSON-file: shopping-list.json
     */
    @Override
    public void save() {
        mapper.writeValue(path, items);
    }

    /**
     * Reads JSON-file 'shopping-list.json' and sets items to this ItemCollection's
     * ObservableList.
     */
    @Override
    public void load() {
        if(Files.notExists(path)){
            return;
        }
        try {
            ListItem[] allItems = mapper.readValue(path.toFile(), ListItem[].class);
            items.setAll(allItems);
        }catch (JacksonException e){
            IO.println("Failed to read JSON-file: " + e.getMessage());
        }
    }
}