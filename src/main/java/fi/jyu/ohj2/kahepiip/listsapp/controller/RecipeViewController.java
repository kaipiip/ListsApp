package fi.jyu.ohj2.kahepiip.listsapp.controller;

import fi.jyu.ohj2.kahepiip.listsapp.App;
import fi.jyu.ohj2.kahepiip.listsapp.model.ListItem;
import fi.jyu.ohj2.kahepiip.listsapp.model.Recipe;
import fi.jyu.ohj2.kahepiip.listsapp.model.RecipeLibrary;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class RecipeViewController implements Initializable {

    @FXML
    private ComboBox<String> arrangeCombo;

    @FXML
    private void handleArrangeCombo(ActionEvent event){
        IO.println("Arrange recipes");
    }

    @FXML
    private TreeView<Recipe> recipeTree;

    @FXML
    private CheckBoxTreeItem<ListItem> cbTree;

    @FXML
    private TextField searchTxt;

    @FXML
    private Button searchBtn;

    @FXML
    private Button newRecipeBtn;

    @FXML
    private Button removeBtn;

    @FXML
    private Button editBtn;

    @FXML
    private Button returnBtn;

    @FXML
    private void handleSearchBtn(ActionEvent event){
        IO.println("Search for a recipe");
    }

    @FXML
    private void handleNewRecipeBtn(ActionEvent event) throws Exception{
        IO.println("Create a new recipe");
        Parent recipeView = FXMLLoader.load(App.class.getResource("newRecipeView.fxml"));
        Scene currentScene = ((Node) event.getSource()).getScene();
        currentScene.setRoot(recipeView);
    }

    @FXML
    private void handleRemoveBtn(ActionEvent event){
        IO.println("Remove recipe");
    }

    @FXML
    private void handleEditBtn(ActionEvent event){
        IO.println("Edit recipe");
    }

    @FXML
    private void handleReturnBtn(ActionEvent event) throws Exception{
        IO.println("return to main view");
        Parent mainView = FXMLLoader.load(App.class.getResource("main.fxml"));
        Scene currentScene = ((Node) event.getSource()).getScene();
        currentScene.setRoot(mainView);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        RecipeLibrary n = new RecipeLibrary();
        n.loadRecipes();

    }
}
