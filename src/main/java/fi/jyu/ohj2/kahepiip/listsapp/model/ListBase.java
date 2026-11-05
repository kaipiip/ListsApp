package fi.jyu.ohj2.kahepiip.listsapp.model;

import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.nio.file.Path;
import java.util.List;

/**
 * Object for lists, works as a parent for Recipes and ListLibraries.
 */
public class ListBase {
    /*------- ATTRIBUTES -------*/
    protected final StringProperty title = new SimpleStringProperty();
    protected final Path path = Path.of("list-base.json");
    protected ObservableList<ListItem> items = FXCollections.observableArrayList(
            item -> new Observable[]{
                    item.getNameProperty(),
                    item.getCompletionProperty()
            }
    );

    private SaveToJson<ListItem> saveFile;

    /*----- CONSTRUCTORS ----*/

    /**
     * List-object for ListItems, listens and saves changes in contents.
     */
    public ListBase(){
        saveFile = new SaveToJson<>(path, items);
    }

    /*------- SETTERS -------*/
    private void setTitle(String title){
        if(title.isBlank()){
            return;
        }
        this.title.set(title);
    }

    /**
     * Set's new title for this list.
     * @param title new title
     */
    public void rename(String title){
        this.setTitle(title);
    }

    /**
     * @param items List of ListItems.
     */
    public void setItems(List<ListItem> items){
        this.items.setAll(items);
    }

    /*------- GETTERS -------*/
    public String getTitle(){return this.title.get();}
    public StringProperty getTitleProperty(){return this.title;}
    public ObservableList<ListItem> getItems(){return items;}
    public Path getPath(){return path;}

    /*------- OTHERS -------*/
    /**
     * Creates and adds item to this List.
     * @param name item name
     */
    public void createAndAddItem(String name){
        String text = name.trim();
        if(text.isBlank()){
            return;
        }
        items.add(new ListItem(text));
    };

    /**
     * Adds existing ListItem to this List.
     * @param item ListItem.
     */
    public void addItem(ListItem item) {items.add(item);}

    /**
     * Removes specified ListItem from this List
     * @param item ListItem to be removed
     */
    public void removeItem(ListItem item) {
        if (item == null){
            return;
        }
        items.remove(item);
    }
}
