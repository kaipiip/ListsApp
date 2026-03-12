package fi.jyu.ohj2.kahepiip.listsapp.model;

/**
 * A list of ingredient-objects, saved as a recipe.
 */
public class Recipe extends ListOfItems{

    public Recipe(){
    }

    public Recipe(String recipeName){
        this.setListHeader(recipeName);
        Listener(this.getItems());
    }

    @Override
    public void addItem(String itemName) {
        this.getItems().add(new Ingredient(itemName.trim(), false));
    }
}
