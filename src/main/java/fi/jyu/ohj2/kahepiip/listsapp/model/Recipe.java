package fi.jyu.ohj2.kahepiip.listsapp.model;

import javafx.beans.Observable;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.nio.file.Path;
import java.util.List;


/**
 * Recipe -object. Recipe contains it's name, category and an ObservableList of ingredients.
 */
public class Recipe extends ListBase implements Collections {
    /*------- ATTRIBUTES -------*/
    private Path path = Path.of("ingredient-list.json");
    private final SaveToJson<ListItem> saveFile;

    /**
     * Recipe-object's category
     */
    private final ObjectProperty<Category> category = new SimpleObjectProperty<>();

    /**
     * ObservableList for Recipe's ingredients.
     * Observes ingredients title, completion, amount and unit -properties.
     */
    private final ObservableList<ListItem> ingredients = FXCollections.observableArrayList(
            ingredient -> new Observable[]{
                    ingredient.nameProperty(),
                    ingredient.getCompletionProperty(),
                    ingredient.getAmountProperty(),
                    ingredient.getUnitProperty()
            });

    /*------- CONSTRUCTORS -------*/
    /**
     * new Recipe-object which contains listener for ingredient's properties.
     * Changes are saved.
     */
    public Recipe(){
        saveFile = new SaveToJson<>(path, ingredients);
    }

    /**
     * Recipe-object for Unit Testing
     * @param path path to test JSON -file
     */
    public Recipe(String path){
        this.path = Path.of(path);
        saveFile = new SaveToJson<>(this.path, ingredients);
    }

    /*------- METHODS ----------*/
    /*------- GETTERS -------*/
    public Category getCategory(){return category.get();}
    public ObjectProperty<Category> categoryProperty(){return category;}

    /**
     *
     * @return Ingredients as ObservableList
     */
    @Override
    public ObservableList<ListItem> getItems() {return this.ingredients;}

    /**
     *
     * @return Object path to json-file.
     */
    public Path getPath() {return this.path;}

    /*------- SETTERS -------*/
    public void setCategory(Category category){this.category.set(category);}

    /**
     * Sets parameter items to Recipe's ObservableList.
     * @param items List of ListItem-objects
     */
    @Override
    public void setItems(List<ListItem> items){this.ingredients.setAll(items);}


    /*------- OTHERS -------*/
    /**
     * Creates and adds new ingredient to Recipe.
     * @param name ingredient name
     */
    @Override
    public void createAndAddItem(String name) {
        String text = name.trim();
        if(text.isBlank()){
            return;
        }
        ingredients.add(new ListItem(text, true));
    }

    /**
     * Adds existing ListItem to Recipe as an ingredient.
     * @param ingredient ingredient which is added to this Recipe.
     */
    @Override
    public void addItem(ListItem ingredient) {
        ingredient.setIngredient(true);
        ingredients.add(ingredient);
    }

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

    @Override
    public String toString(){
        return getTitle();
    }

//    /**
//     * Reads JSON-file 'ingredient-list.json' and sets items to this Recipe-object's
//     * ObservableList.
//     */
//    @Override
//    public void load() {
//        if(Files.notExists(path)){
//            return;
//        }
//        try {
//            ListItem[] allIngredients = mapper.readValue(path.toFile(), ListItem[].class);
//            ingredients.setAll(allIngredients);
//        }catch (JacksonException e){
//            IO.println("Failed to read JSON-file: " + e.getMessage());
//        }
//    }
}
