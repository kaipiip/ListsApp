package fi.jyu.ohj2.kahepiip.listsapp.model;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * RecipeLibrary-object. Library contains Recipe-objects
 */
public class RecipeLibrary {
    private final Path library = Path.of("recipe-library.json");
    private final ObjectMapper mapper = new ObjectMapper();

    /**
     * ObservableList for Recipes. Observes Recipe-objects name and category -properties.
     */
    private ObservableList<Recipe> recipes = FXCollections.observableArrayList(
            recipe -> new Observable[]{
                    recipe.nameProperty(),
                    recipe.categoryProperty()
            }
    );

    /**
     * New RecipeLibrary-object which contains listener for Recipe's properties.
     * Changes are saved.
     */
    public RecipeLibrary(){
        recipes.addListener((ListChangeListener<Recipe>) change -> {
            saveRecipes();
        });
    }

    /**
     *
     * @return Recipe-objects as ObservableList
     */
    public ObservableList<Recipe> getRecipes() {return this.recipes;}

    /**
     * Sets a list of Recipes to RecipeLibrary's ObservableList.
     * @param recipes List of Recipes.
     */
    public void setRecipes(List<Recipe> recipes) {this.recipes.setAll(recipes);}

    /**
     * Adds specified recipe to RecipeLibrary.
     * @param recipe Recipe-object
     */
    public void addRecipe(Recipe recipe){recipes.add(recipe);}

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

    /**
     * Saves Recipes to JSON-file: recipe-library.json
     */
    public void saveRecipes(){mapper.writeValue(library, recipes);}

    /**
     * Reads JSON-file 'recipe-library.json' and sets items to this RecipeLibrary's
     * ObservableList.
     */
    public void loadRecipes() {
        if(Files.notExists(library)){
            return;
        }
        try {
            Recipe[] r = mapper.readValue(library.toFile(), Recipe[].class);
            recipes.setAll(r);
        }catch (JacksonException e){
            IO.println("Failed to read JSON-file: " + e.getMessage());
        }
    }
}
