package fi.jyu.ohj2.kahepiip.listsapp.model;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
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
    /*------- ATTRIBUTES -------------------------------------------------------------------------------------------*/
    private Path path = Path.of("shopping-list.json");
    private final ObjectMapper mapper = new ObjectMapper();
    private final SaveToJson<ListItem> saveFile;

    /**
     * ObservableList for ListItems
     * Observes item's title and completion -properties
     */
    private final ObservableList<ListItem> items = FXCollections.observableArrayList(
            item -> new Observable[]{
                    item.nameProperty(),
                    item.completionProperty()
            });

    /*------- CONSTRUCTORS ---------------------------------------------------------------------------------------*/
    /**
     * New ItemCollection-object which contains listener for ObservableList's properties.
     * Changes are saved.
     */
    public ItemCollection(){
            saveFile = new SaveToJson<>(path, items);
    }

    /**
     * ItemCollection-object for Unit Testing
     * @param path path to test JSON -file
     */
    public ItemCollection(String path){
        this.path = Path.of(path);
        saveFile = new SaveToJson<>(this.path, items);
    }

    /*------- METHODS -------------------------------------------------------------------------------------------*/
    /*------- GETTERS -------------------------------------------------------------------------------------*/
    /**
     *
     * @return ListItems as ObservableList
     */
    @Override
    public ObservableList<ListItem> getItems() {return this.items;}

    /*------- SETTERS -------------------------------------------------------------------------------------*/
    /**
     * Sets List of ListItems to ItemCollection's ObservableList
     * @param items List of ListItems
     */
    @Override
    public void setItems(List<ListItem> items){this.items.setAll(items);}

    /*------- OTHERS -------------------------------------------------------------------------------------*/
    /**
     * Creates new ListItem and adds it to this ItemCollection.
     * @param title ListItem title
     */
    @Override
    public void addItem(String title) {
        String text = title.trim();
        if(text.isBlank()){
            return;
        }
        items.add(new ListItem(text));
    }

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