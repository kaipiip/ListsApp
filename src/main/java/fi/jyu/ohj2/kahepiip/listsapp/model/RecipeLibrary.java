package fi.jyu.ohj2.kahepiip.listsapp.model;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

import java.nio.file.Path;
import java.util.List;

/**
 * Observable list for Recipes
 */
public class RecipeLibrary {
    private final Path library = Path.of("recipe-library.json");
    private final ObjectMapper mapper = new ObjectMapper();

    private ObservableList<Recipe> recipes = FXCollections.observableArrayList(
            recipe -> new Observable[]{
                    recipe.recipeNameProperty(),
                    recipe.categoryProperty()
            }
    );

    public RecipeLibrary(){
        recipes.addListener((ListChangeListener<Recipe>) change -> {
            saveRecipes();
        });
    }

    public ObservableList<Recipe> getRecipes() {return this.recipes;}
    public void setRecipes(List<Recipe> recipes) {this.recipes.setAll(recipes);}

    public void addRecipe(Recipe recipe){recipes.add(recipe);}
    public void removeRecipe(Recipe recipe){
        if(recipe == null){
            return;
        }
        recipes.remove(recipe);
    }

    public void saveRecipes(){mapper.writeValue(library, recipes);}

    public void loadRecipes() {
        try {
            Recipe[] r = mapper.readValue(library.toFile(), Recipe[].class);
            recipes.setAll(r);
        }catch (JacksonException e){
            IO.println("Failed to read JSON-file: " + e.getMessage());
        }
    }
}
