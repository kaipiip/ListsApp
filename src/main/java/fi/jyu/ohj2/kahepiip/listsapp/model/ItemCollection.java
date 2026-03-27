package fi.jyu.ohj2.kahepiip.listsapp.model;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import tools.jackson.core.JacksonException;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Observable list for ListItems
 */
public class ItemCollection implements Collections {
    private final Path path = Path.of("itemCollection.json");
    private final ObjectMapper mapper = new ObjectMapper();

    private ObservableList<ListItem> items = FXCollections.observableArrayList(
            item -> new Observable[]{
                    item.titleProperty(),
                    item.completionProperty()
            });


    public ItemCollection(){
            items.addListener((ListChangeListener<ListItem>) change -> {
                save();
            });
    }

    @Override
    public ObservableList<ListItem> getItems() {return this.items;}
    @Override
    public void addItem(String title) {items.add(new ListItem(title.trim()));}
    @Override
    public void addItem(ListItem item) {items.add(item);}

    @Override
    public void removeItem(ListItem item) {
        if (item == null){
            return;
        }
        items.remove(item);
    }

    @Override
    public void save() {
        mapper.writeValue(path, items);
    }

    @Override
    public void load() {
        if(Files.notExists(path)){
            return;
        }
        try {
            List<ListItem> allItems = mapper.readValue(path, new TypeReference<>() {});
            items.addAll(allItems);
        }catch (JacksonException e){
            IO.println("Failed to read JSON-file: " + e.getMessage());
        }
    }
}
