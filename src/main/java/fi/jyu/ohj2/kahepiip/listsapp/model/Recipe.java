package fi.jyu.ohj2.kahepiip.listsapp.model;

import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class Recipe implements Collections {
    private StringProperty recipeName = new SimpleStringProperty();
    private ObjectProperty<ListItem> ingredient = new SimpleObjectProperty<>();
    private ObjectProperty<Category> category = new SimpleObjectProperty<>();

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

    public void setRecipeName(String title){this.recipeName.set(title);}
    public String getRecipeName(){return this.recipeName.get();}
    public StringProperty recipeNameProperty(){return this.recipeName;}

    public void setIngredient(ListItem ingredient) {this.ingredient.set(ingredient);}
    public ListItem getIngredient() {return ingredient.get();}
    public ObjectProperty<ListItem> ingredientProperty() {return ingredient;}

    public void setCategory(Category category){this.category.set(category);}
    public Category getCategory(){return category.get();}
    public ObjectProperty<Category> categoryProperty(){return category;}

    @Override
    public ObservableList<ListItem> getItems() {return this.ingredients;}

    @Override
    public void addItem(String title) {ingredients.add(new ListItem(title.trim(), true));}

    @Override
    public void addItem(ListItem ingredient) {ingredients.add(ingredient);}

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
