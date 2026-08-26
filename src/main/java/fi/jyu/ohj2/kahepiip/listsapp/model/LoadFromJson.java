package fi.jyu.ohj2.kahepiip.listsapp.model;

import javafx.collections.ObservableList;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Path;

public class LoadFromJson<T> {
    /*------- ATTRIBUTES -------*/
    private final Path path;
    private final ObjectMapper mapper = new ObjectMapper();

    /*------- CONSTRUCTORS -------*/

    public LoadFromJson(Path path){
        this.path = path;
    }

    /*------- METHODS ----------*/

    public ObservableList<T> loadListItems(ItemCollection c){

        if(Files.notExists(path)){
            return null;
        }
        try {
            ListItem[] allItems = mapper.readValue(path.toFile(), ListItem[].class);

            for (ListItem item : allItems) {
                c.addItem(item);
            }

        }catch (JacksonException e){
            IO.println("Failed to read JSON-file: " + e.getMessage());
        }

        return null;
    }

    public ObservableList<T> loadRecipe(Recipe r){

        if(Files.notExists(path)){
            return null;
        }
        try {
            ListItem[] allItems = mapper.readValue(path.toFile(), ListItem[].class);

            for (ListItem item : allItems) {
                r.addItem(item);
            }

        }catch (JacksonException e){
            IO.println("Failed to read JSON-file: " + e.getMessage());
        }

        return null;
    }

    public ObservableList<T> loadLibrary(RecipeLibrary l){

        if(Files.notExists(path)){
            return null;
        }
        try {
            Recipe[] recipes = mapper.readValue(path.toFile(), Recipe[].class);

            for (Recipe item : recipes) {
                l.addRecipe(item);
            }

        }catch (JacksonException e){
            IO.println("Failed to read JSON-file: " + e.getMessage());
        }

        return null;
    }
}
