package fi.jyu.ohj2.kahepiip.listsapp.model;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tools.jackson.databind.ObjectMapper;

import java.nio.file.Path;
import java.util.List;

/**
 * RecipeLibrary-object. Library contains Recipe-objects
 */
@SuppressWarnings("unused")
public class RecipeLibrary extends RecipeParent {
    /*------- ATTRIBUTES -------*/
    private Path path = Path.of("recipe-library.json");
    private final ObjectMapper mapper = new ObjectMapper();
    private final SaveToJson<Recipe> saveFile;

    /**
     * ObservableList for Recipes. Observes Recipe-objects name and category -properties.
     */
    private final ObservableList<Recipe> recipes = FXCollections.observableArrayList(
            recipe -> new Observable[]{
                    recipe.nameProperty(),
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
    public Recipe getRecipe(RecipeParent recipe){
        for(Recipe r : recipes){
            if(r.getName().equals(recipe.getName())){
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
        if(recipe == null || recipe.getItems().isEmpty() || recipe.getName() == null){
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



//    /**
//     * Reads JSON-file 'recipe-library.json' and sets items to this RecipeLibrary's
//     * ObservableList.
//     */
//    public void loadRecipes() {
//        if(Files.notExists(library)){
//            return;
//        }
//        try {
//            Recipe[] r = mapper.readValue(library.toFile(), Recipe[].class);
//            recipes.setAll(r);
//        }catch (JacksonException e){
//            IO.println("Failed to read JSON-file: " + e.getMessage());
//        }
//    }
}
