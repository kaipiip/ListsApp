package fi.jyu.ohj2.kahepiip.listsapp.model;

import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

/**
 * Observable list for Recipes
 */
public class RecipeLibrary {

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

    public void addRecipe(Recipe recipe){recipes.add(recipe);}
    public void removeRecipe(Recipe recipe){
        if(recipe == null){
            return;
        }
        recipes.remove(recipe);
    }
}
