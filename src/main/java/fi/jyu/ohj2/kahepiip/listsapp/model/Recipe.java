package fi.jyu.ohj2.kahepiip.listsapp.model;

import javafx.beans.Observable;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Path;

public class Recipe implements Collections {
    private final Path ingredientPath = Path.of("ingredient-list.json");
    private final ObjectMapper mapper = new ObjectMapper();


    private StringProperty recipeName = new SimpleStringProperty();
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
            save();
        });
    }

    public void setRecipeName(String title){this.recipeName.set(title);}
    public String getRecipeName(){return this.recipeName.get();}
    public StringProperty recipeNameProperty(){return this.recipeName;}

    public void setCategory(Category category){this.category.set(category);}
    public Category getCategory(){return category.get();}
    public ObjectProperty<Category> categoryProperty(){return category;}

    @Override
    public ObservableList<ListItem> getItems() {return this.ingredients;}
    @Override
    public void setItems(ObservableList<ListItem> items){this.ingredients.setAll(items);}

    @Override
    public void addItem(String title) {
        ingredients.add(new ListItem(title.trim(), true));}

    @Override
    public void addItem(ListItem ingredient) {
        ingredient.setIngredient(true);
        ingredients.add(ingredient);}

    @Override
    public void removeItem(ListItem item) {
        if (item == null){
            return;
        }
        ingredients.remove(item);
    }

    @Override
    public void save() {
        mapper.writeValue(ingredientPath, ingredients);
    }

    @Override
    public void load() {
        if(Files.notExists(ingredientPath)){
            return;
        }
        try {
            ListItem[] allIngredients = mapper.readValue(ingredientPath.toFile(), ListItem[].class);
            ingredients.setAll(allIngredients);
        }catch (JacksonException e){
            IO.println("Failed to read JSON-file: " + e.getMessage());
        }
    }
}
