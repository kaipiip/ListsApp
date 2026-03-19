package fi.jyu.ohj2.kahepiip.listsapp.model;

import javafx.collections.ObservableList;

/**
 * A list of ingredient-objects, saved as a recipe.
 */
public class Recipe extends Sheet implements LibraryInterface{

    private ObservableList<ObList> ingredients = observeAll();

    /**
     * Observable list for recipe ingredients. Ingredient name, completion, amount and unit values
     * are being listened to.
     */
    public Recipe(){
    }

    /**
     * Observable list for recipe ingredients. Ingredient name, completion, amount and unit values
     * are being listened to.
     * @param recipeName Recipe name
     */
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
