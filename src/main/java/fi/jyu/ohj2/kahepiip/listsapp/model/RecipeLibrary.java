package fi.jyu.ohj2.kahepiip.listsapp.model;

import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import tools.jackson.core.JacksonException;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Observable list for Recipes
 */
public class RecipeLibrary {
    private final Path path = Path.of("recipeLibrary.json");
    private final ObjectMapper mapper = new ObjectMapper();

    private ObjectProperty<Recipe> recipe = new SimpleObjectProperty<>();

    private ObservableList<Recipe> recipes = FXCollections.observableArrayList(
            recipe -> new Observable[]{
                    recipe.recipeNameProperty(),
                    recipe.categoryProperty(),
                    recipe.ingredientProperty()
            });

    public RecipeLibrary(){
        recipes.addListener((ListChangeListener<Recipe>) change -> {
            // save ();
        });
    }

    public void setRecipe(Recipe recipe){this.recipe.set(recipe);}
    public Recipe getRecipe(){return this.recipe.get();}
    public ObjectProperty<Recipe> recipeObjectProperty(){return this.recipe;}

    public ObservableList<Recipe> getRecipes() {return this.recipes;}
    public void setRecipes(ObservableList<Recipe> recipes) {this.recipes = recipes;}

    public void addRecipe(Recipe recipe){recipes.add(recipe);}
    public void removeRecipe(Recipe recipe){
        if(recipe == null){
            return;
        }
        recipes.remove(recipe);
    }

    public void saveRecipes(){
        mapper.writeValue(path, recipes);
    }

    public void loadRecipes() {
        if(Files.notExists(path)){
            return;
        }
        try {
            List<Recipe> allRecipes = mapper.readValue(path, new TypeReference<>() {});
            recipes.addAll(allRecipes);
        }catch (JacksonException e){
            IO.println("Failed to read JSON-file: " + e.getMessage());
        }
    }
}
