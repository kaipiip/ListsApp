package fi.jyu.ohj2.kahepiip.listsapp.model;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class Recipe implements Collections {
    private ObservableList<ListItem> ingredients = FXCollections.observableArrayList(
            ingredient -> new Observable[]{
                    ingredient.titleProperty(),
                    ingredient.completionProperty(),
                    ingredient.amountProperty(),
                    ingredient.unitProperty()
            });

    public Recipe(){
        ingredients.addListener((ListChangeListener<ListItem>) change -> {
            // save();
        });
    }

    @Override
    public ObservableList<ListItem> getItems() {return this.ingredients;}

    @Override
    public void addItem(String title) {ingredients.add(new ListItem(title.trim(), true));}

    @Override
    public void removeItem(ListItem item) {
        if (item == null){
            return;
        }
        ingredients.remove(item);
    }

    @Override
    public void save() {}

    @Override
    public void load() {}
}
