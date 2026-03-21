package fi.jyu.ohj2.kahepiip.listsapp.controller;

import fi.jyu.ohj2.kahepiip.listsapp.model.ListItem;
import fi.jyu.ohj2.kahepiip.listsapp.model.Recipe;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private void handleNewRecipeBtn(ActionEvent event){
        IO.println("Create a new recipe");
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
    private void handleReturnBtn(ActionEvent event){
        IO.println("return to main view");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
