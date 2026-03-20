package fi.jyu.ohj2.kahepiip.listsapp.controller;

import fi.jyu.ohj2.kahepiip.listsapp.model.Ingredient;
import fi.jyu.ohj2.kahepiip.listsapp.model.Unit;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class NewRecipeController implements Initializable {

    @FXML
    private TextField recipeNameTxt;

    @FXML
    private TextField ingredientTxt;

    @FXML
    private TextField amountTxt;

    @FXML
    private ComboBox<String> categoryCombo;

    @FXML
    private ComboBox<Unit> unitCombo;

    @FXML
    private TableView<Ingredient> newRecipeTable;

    @FXML
    private TableColumn<Ingredient, DoubleProperty> amountColumn;

    @FXML
    private TableColumn<Ingredient, ObjectProperty<Unit>> unitColumn;

    @FXML
    private TableColumn<Ingredient, StringProperty> nameColumn;

    @FXML
    private Button addIngredientBtn;

    @FXML
    private Button saveBtn;

    @FXML
    private Button saveAndAddBtn;

    @FXML
    private Button returnBtn;

    @FXML
    private void handleSaveBtn(ActionEvent event){
    }

    @FXML
    private void handleSaveAndAddBtn(ActionEvent event){
    }

    @FXML
    private void handleAddIngredientBtn(ActionEvent event){
    }

    @FXML
    private void handleReturnBtn(ActionEvent event){
    }

    @FXML
    private void handleCategoryCombo(ActionEvent event){
    }

    @FXML
    private void handleUnitCombo(ActionEvent event){
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
