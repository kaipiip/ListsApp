package fi.jyu.ohj2.kahepiip.listsapp.model;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.nio.file.Path;
import java.util.List;

/**
 * RecipeLibrary-object. Library contains Recipe-objects
 */
@SuppressWarnings("unused")
public class RecipeLibrary extends ListBase {
    /*------- ATTRIBUTES -------*/
    private Path path = Path.of("recipe-library.json");
    private final SaveToJson<Recipe> saveFile;

    /**
     * ObservableList for Recipes. Observes Recipe-objects name and category -properties.
     */
    private final ObservableList<Recipe> recipes = FXCollections.observableArrayList(
            recipe -> new Observable[]{
                    recipe.getTitleProperty(),
                    recipe.categoryProperty()
            }
    );

    /*------- CONSTRUCTORS -------*/
    /**
     * New RecipeLibrary-object which contains listener for Recipe's properties.
     * Changes are saved.
     */
    public RecipeLibrary(){
        saveFile = new SaveToJson<>(path, recipes);
    }

    /**
     * RecipeLibrary-object for Unit Testing
     * @param path path to test JSON -file
     */
    public RecipeLibrary(String path){
        this.path = Path.of(path);
        saveFile = new SaveToJson<>(this.path, recipes);
    }

    /*------- METHODS ----------*/
    /*------- GETTERS -------*/
    /**
     *
     * @return Recipe-objects as ObservableList
     */
    public ObservableList<Recipe> getRecipes() {return this.recipes;}

    /**
     * Finds specified Recipe Object, that matches RecipeParent
     * nameProperty.
     * @param recipe RecipeParent that's name is used as keyword
     * @return Recipe with same name as parameter's.
     */
    public Recipe findRecipe(ListBase recipe){
        for(Recipe r : recipes){
            if(r.getTitle().equals(recipe.getTitle())){
                return r;
            }
        }
        return null;
    }

    /**
     *
     * @return Object path to json-file.
     */
    public Path getPath() {return this.path;}

    /*------- SETTERS -------*/
    /**
     * Sets a list of Recipes to RecipeLibrary's ObservableList.
     * @param recipes List of Recipes.
     */
    public void setRecipes(List<Recipe> recipes) {this.recipes.setAll(recipes);}

    /*------- OTHERS --------*/
    /**
     * Adds specified recipe to RecipeLibrary.
     * @param recipe Recipe-object
     */
    public void addRecipe(Recipe recipe){
        if(recipe == null || recipe.getItems().isEmpty() || recipe.getTitle() == null){
            return;
        }
        recipes.add(recipe);
    }

    /**
     * Removes Recipe-object from RecipeLibrary
     * @param recipe Recipe to be removed from Library.
     */
    public void removeRecipe(Recipe recipe){
        if(recipe == null){
            return;
        }
        recipes.remove(recipe);
    }

}
