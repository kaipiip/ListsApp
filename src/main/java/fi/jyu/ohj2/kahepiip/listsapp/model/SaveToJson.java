package fi.jyu.ohj2.kahepiip.listsapp.model;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import tools.jackson.databind.ObjectMapper;

import java.nio.file.Path;

public class SaveToJson<T> {
    private Path path = null;
    private ObservableList<T> list;
    private final ObjectMapper mapper = new ObjectMapper();

    public SaveToJson(Path path, ObservableList<T> list){
        this.path = path;
        this.list = list;
        list.addListener((ListChangeListener<T>) change -> {
            save();
        });
    }

    private void save() {
        mapper.writeValue(path, list);
    }
}
