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
import java.util.List;


/**
 * Recipe -object. Recipe contains it's name, category and an ObservableList of ingredients.
 */
public class Recipe implements Collections {
    private final Path ingredientPath = Path.of("ingredient-list.json");
    private final ObjectMapper mapper = new ObjectMapper();

    /**
     * Recipe-object's name
     */
    private StringProperty recipeName = new SimpleStringProperty();
    /**
     * Recipe-object's category
     */
    private ObjectProperty<Category> category = new SimpleObjectProperty<>();

    /**
     * ObservableList for Recipe's ingredients.
     * Observes ingredients title, completion, amount and unit -properties.
     */
    private ObservableList<ListItem> ingredients = FXCollections.observableArrayList(
            ingredient -> new Observable[]{
                    ingredient.titleProperty(),
                    ingredient.completionProperty(),
                    ingredient.amountProperty(),
                    ingredient.unitProperty()
            });

    /**
     * new Recipe-object which contains listener for ingredient's properties.
     * Changes are saved.
     */
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

    /**
     *
     * @return Ingredients as ObservableList
     */
    @Override
    public ObservableList<ListItem> getItems() {return this.ingredients;}

    /**
     * Sets parameter items to Recipe's ObservableList.
     * @param items List of ListItem-objects
     */
    @Override
    public void setItems(List<ListItem> items){this.ingredients.setAll(items);}

    /**
     * Creates and adds new ingredient to Recipe.
     * @param title ingredient name
     */
    @Override
    public void addItem(String title) {
        ingredients.add(new ListItem(title.trim(), true));}

    /**
     * Adds existing ListItem to Recipe as an ingredient.
     * @param ingredient ingredient which is added to this Recipe.
     */
    @Override
    public void addItem(ListItem ingredient) {
        ingredient.setIngredient(true);
        ingredients.add(ingredient);}

    /**
     * Removes ListItem from Recipe.
     * @param item ingredient to be removed from Recipe
     */
    @Override
    public void removeItem(ListItem item) {
        if (item == null){
            return;
        }
        ingredients.remove(item);
    }

    /**
     * Saves ingredients to JSON-file: ingredient-list.json
     */
    @Override
    public void save() {
        mapper.writeValue(ingredientPath, ingredients);
    }

    /**
     * Reads JSON-file 'ingredient-list.json' and sets items to this Recipe-object's
     * ObservableList.
     */
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
