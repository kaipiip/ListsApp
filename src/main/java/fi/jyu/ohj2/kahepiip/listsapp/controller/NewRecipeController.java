package fi.jyu.ohj2.kahepiip.listsapp.controller;

import fi.jyu.ohj2.kahepiip.listsapp.model.ListItem;
import fi.jyu.ohj2.kahepiip.listsapp.model.Unit;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.List;
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
    private TableView<ListItem> newRecipeTable;

    @FXML
    private TableColumn<ListItem, DoubleProperty> amountColumn;

    @FXML
    private TableColumn<ListItem, ObjectProperty<Unit>> unitColumn;

    @FXML
    private TableColumn<ListItem, StringProperty> nameColumn;

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
        IO.println("Save Recipe");
    }

    @FXML
    private void handleSaveAndAddBtn(ActionEvent event){
        IO.println("Save and move to today's shopping list");
    }

    @FXML
    private void handleAddIngredientBtn(ActionEvent event){
        IO.println("Add new Ingredient");
    }

    @FXML
    private void handleReturnBtn(ActionEvent event){
        IO.println("Return to main view");
    }

    @FXML
    private void handleCategoryCombo(ActionEvent event){
        IO.println("Choose a category for recipe");
    }

    @FXML
    private void handleUnitCombo(ActionEvent event){
        IO.println("Choose unit of measure for ingredient");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
