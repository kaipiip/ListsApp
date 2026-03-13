package fi.jyu.ohj2.kahepiip.listsapp.model;

import javafx.collections.ObservableList;

/**
 * A list of ingredient-objects, saved as a recipe.
 */
public class Recipe extends Sheet implements LibraryInterface{

    private ObservableList<ObList> ingredients = observeAll();

    public Recipe(){
    }

    public Recipe(String recipeName){
        super(recipeName);
        listener(ingredients);
        setAsRecipe();
    }

    @Override
    public void addMember(String title) {
        this.getItems().add(new Ingredient(title.trim(), false));
    }
}
