package fi.jyu.ohj2.kahepiip.listsapp.controller;

import fi.jyu.ohj2.kahepiip.listsapp.App;
import fi.jyu.ohj2.kahepiip.listsapp.model.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
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
    private final TableColumn<ListItem, Double> amountColumn = new TableColumn<>("Amount");

    @FXML
    private final TableColumn<ListItem, Unit> unitColumn = new TableColumn<>("Unit");

    @FXML
    private final TableColumn<ListItem, String> nameColumn = new TableColumn<>("Ingredient");

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
        addRecipe();
    }

    @FXML
    private void handleSaveAndAddBtn(ActionEvent event) throws Exception{
        IO.println("Save and move to today's shopping list");
        addRecipe();
        returnToMainView(event);
    }

    @FXML
    private void handleAddIngredientBtn(ActionEvent event){
        IO.println("Adding new Ingredient");
        Platform.runLater(ingredientTxt::requestFocus);
        String ingredientName = ingredientTxt.getText();
        double amount;

        try{
            amount = Double.parseDouble(amountTxt.getText());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if(ingredientName == null || ingredientName.isBlank()){
            ingredientTxt.clear();
            return;
        }

        ListItem ingredient = new ListItem(ingredientName, true);
        ingredient.setAmount(amount);
        ingredient.setUnit(unitCombo.getValue());

        recipe.addItem(ingredient);
        ingredientTxt.clear();
        amountTxt.clear();
    }

    @FXML
    private void handleReturnBtn(ActionEvent event) throws Exception{
        IO.println("Return to main view");
        returnToMainView(event);
    }

    @FXML
    private void handleCategoryCombo(ActionEvent event){
        IO.println("Choose a category for recipe");
    }

    @FXML
    private void handleUnitCombo(ActionEvent event){
        IO.println("Choose unit of measure for ingredient");
    }

    Recipe recipe = new Recipe();
    RecipeLibrary recipeCollection = new RecipeLibrary();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        recipeNameTxt.requestFocus();
        newRecipeTable.setItems(recipe.getItems());

        amountColumn.setCellValueFactory(cd -> cd.getValue().amountProperty().asObject());
        unitColumn.setCellValueFactory(cd -> cd.getValue().unitProperty());
        nameColumn.setCellValueFactory(cd -> cd.getValue().titleProperty());
        newRecipeTable.getColumns().addAll(amountColumn, unitColumn, nameColumn);

        unitCombo.setItems(FXCollections.observableArrayList(Unit.values()));
        unitCombo.setValue(Unit.NULL);
    }

    public void returnToMainView(ActionEvent event) throws Exception{
        Parent mainView = FXMLLoader.load(App.class.getResource("main.fxml"));
        Scene currentScene = ((Node) event.getSource()).getScene();
        currentScene.setRoot(mainView);
    }

    public void addRecipe(){
        String recipeName = recipeNameTxt.getText();
        if(recipeName == null || recipeName.isBlank()){
            recipeNameTxt.clear();
            return;
        }
        recipe.setRecipeName(recipeName);
        recipeCollection.addRecipe(recipe);
        recipeNameTxt.clear();
        newRecipeTable.getItems().removeAll(recipe.getItems());
        // Lisää tallennus!
    }
}
