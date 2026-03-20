package fi.jyu.ohj2.kahepiip.listsapp.controller;

import fi.jyu.ohj2.kahepiip.listsapp.model.Ingredient;
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

    }

    @FXML
    private TreeView<Recipe> recipeTree;

    @FXML
    private CheckBoxTreeItem<Ingredient> cbTree;

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

    }

    @FXML
    private void handleNewRecipeBtn(ActionEvent event){

    }

    @FXML
    private void handleRemoveBtn(ActionEvent event){

    }

    @FXML
    private void handleEditBtn(ActionEvent event){

    }

    @FXML
    private void handleReturnBtn(ActionEvent event){

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
