package fi.jyu.ohj2.kahepiip.listsapp.controller;

import fi.jyu.ohj2.kahepiip.listsapp.App;
import fi.jyu.ohj2.kahepiip.listsapp.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTreeCell;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RecipeViewController implements Initializable {

    @SuppressWarnings("unused")
    @FXML
    private ComboBox<String> arrangeCombo;

    @SuppressWarnings("unused")
    @FXML
    private void handleArrangeCombo(ActionEvent event){
        IO.println("Arrange recipes");
    }

    @SuppressWarnings("unused")
    @FXML
    private TreeView<RecipeParent> recipeTree;

    @SuppressWarnings("unused")
    @FXML
    private TreeItem<RecipeParent> libraryRoot;

    @SuppressWarnings("unused")
    @FXML
    private CheckBoxTreeItem<RecipeParent> cbRecipe;

    @SuppressWarnings("unused")
    @FXML
    private CheckBoxTreeItem<RecipeParent> cbIngredient;

    @SuppressWarnings("unused")
    @FXML
    private TextField searchTxt;

    @SuppressWarnings("unused")
    @FXML
    private Button searchBtn;

    @SuppressWarnings("unused")
    @FXML
    private Button newRecipeBtn;

    @SuppressWarnings("unused")
    @FXML
    private Button removeBtn;

    @SuppressWarnings("unused")
    @FXML
    private Button editBtn;

    @SuppressWarnings("unused")
    @FXML
    private Button returnBtn;

    @SuppressWarnings("unused")
    @FXML
    private void handleSearchBtn(ActionEvent event){
        IO.println("Search for a recipe");
    }

    @SuppressWarnings("unused")
    @FXML
    private void handleNewRecipeBtn(ActionEvent event) throws Exception{
        Parent recipeView = FXMLLoader.load(App.class.getResource("newRecipeView.fxml"));
        Scene currentScene = ((Node) event.getSource()).getScene();
        currentScene.setRoot(recipeView);
    }

    @SuppressWarnings("unused")
    @FXML
    private void handleRemoveBtn(ActionEvent event){
        removeChosenRecipe();
    }

    @SuppressWarnings("unused")
    @FXML
    private void handleEditBtn(ActionEvent event){
        editRecipe(event);
    }

    @SuppressWarnings("unused")
    @FXML
    private void handleReturnBtn(ActionEvent event) throws Exception{
        IO.println("return to main view");
        Parent mainView = FXMLLoader.load(App.class.getResource("main.fxml"));
        Scene currentScene = ((Node) event.getSource()).getScene();
        currentScene.setRoot(mainView);
    }

    RecipeLibrary recipeLibrary = new RecipeLibrary();
    ItemCollection shoppingList = new ItemCollection();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        recipeLibrary.loadRecipes();
        shoppingList.load();
        libraryRoot = new TreeItem<>();

        /*
        Creates TreeView with Recipe-objects as branches and ListItems (ingredients)
        as leaves.
         */
        recipeTree.setCellFactory(CheckBoxTreeCell.forTreeView());
        for (Recipe r : recipeLibrary.getRecipes()){
            cbRecipe = new CheckBoxTreeItem<>(r);
            libraryRoot.getChildren().add(cbRecipe);

            for(ListItem ingredient : r.getItems()){
                cbIngredient = new CheckBoxTreeItem<>(ingredient);
                cbRecipe.getChildren().add(cbIngredient);
            }
        }

        recipeTree.setRoot(libraryRoot);
        recipeTree.setShowRoot(false);
        recipeTree.setEditable(true);

        // Disables remove -button, if branch not selected.
        removeBtn.setDisable(true);
        recipeTree.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if(newValue == null || newValue.isLeaf()){
                        removeBtn.setDisable(true);
                    } else {
                        removeBtn.setDisable(false);
                    }
                });

        // Disables edit recipe -button, if branch not selected.
        editBtn.setDisable(true);
        recipeTree.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if(newValue == null || newValue.isLeaf()){
                        editBtn.setDisable(true);
                    } else {
                        editBtn.setDisable(false);
                    }
                });
    }

    /**
     * Removes chosen recipe from RecipeLibrary and TreeView.
     */
    private void removeChosenRecipe(){
        TreeItem<RecipeParent> chosenRecipe = recipeTree.getSelectionModel().getSelectedItem();
        if(chosenRecipe == null){
            return;
        }
        RecipeParent recipeP = chosenRecipe.getValue();
        recipeLibrary.removeRecipe(recipeLibrary.getRecipe(recipeP));
        libraryRoot.getChildren().remove(chosenRecipe);
    }

    private void editRecipe(ActionEvent event){
        TreeItem<RecipeParent> chosenRecipe = recipeTree.getSelectionModel().getSelectedItem();
        if(chosenRecipe == null){
            return;
        }
        RecipeParent recipeP = chosenRecipe.getValue();

        try{
            FXMLLoader loader = new FXMLLoader(App.class.getResource("editRecipe.fxml"));
            Parent recipeView = loader.load();
            Scene currentScene = ((Node) event.getSource()).getScene();
            EditRecipeController controller = loader.getController();
            controller.setRecipeToEdit(recipeLibrary.getRecipe(recipeP));
            currentScene.setRoot(recipeView);
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

}
