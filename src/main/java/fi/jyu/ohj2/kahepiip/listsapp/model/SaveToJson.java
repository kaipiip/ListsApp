package fi.jyu.ohj2.kahepiip.listsapp.model;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import tools.jackson.databind.ObjectMapper;

import java.nio.file.Path;

/**
 * Writes lists to json-files, and saves changes.
 * @param <T> ListItem or Recipe
 */
public class SaveToJson<T> {
    /*------- ATTRIBUTES -------------------------------------------------------------------------------------------*/
    private final Path path;
    private final ObservableList<T> list;
    private final ObjectMapper mapper = new ObjectMapper();

    /*------- CONSTRUCTORS ---------------------------------------------------------------------------------------*/

    /**
     * Object saves changes in the list given as parameter, to given path of json-file.
     * @param path path for saving list.
     * @param list list that is to be written to json-file
     */
    public SaveToJson(Path path, ObservableList<T> list){
        this.path = path;
        this.list = list;
        list.addListener((ListChangeListener<T>) change -> {
            save();
        });
    }

    /*------- METHODS -------------------------------------------------------------------------------------------*/

    /**
     * Method for saving observable lists.
     */
    private void save() {
        mapper.writeValue(path, list);
    }
}
