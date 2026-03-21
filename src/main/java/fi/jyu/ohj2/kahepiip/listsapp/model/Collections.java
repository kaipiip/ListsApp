package fi.jyu.ohj2.kahepiip.listsapp.model;

import javafx.collections.ObservableList;

import java.awt.*;
import java.util.List;

public interface Collections {
    void save();
    void addItem(String title);
    void removeItem(ListItem item);
    void load();
    ObservableList<ListItem> getItems();

}
