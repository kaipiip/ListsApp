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
        IO.println("Create a new recipe");
        Parent recipeView = FXMLLoader.load(App.class.getResource("newRecipeView.fxml"));
        Scene currentScene = ((Node) event.getSource()).getScene();
        currentScene.setRoot(recipeView);
    }

    @SuppressWarnings("unused")
    @FXML
    private void handleRemoveBtn(ActionEvent event){
        IO.println("Remove recipe");
    }

    @SuppressWarnings("unused")
    @FXML
    private void handleEditBtn(ActionEvent event){
        IO.println("Edit recipe");
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
    }
}
