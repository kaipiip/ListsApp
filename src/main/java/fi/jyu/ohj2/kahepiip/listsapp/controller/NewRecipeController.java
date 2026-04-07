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
import tools.jackson.databind.ObjectMapper;

import java.net.URL;
import java.nio.file.Path;
import java.util.ResourceBundle;

public class NewRecipeController implements Initializable {

    @FXML
    private TextField recipeNameTxt;

    @FXML
    private TextField ingredientTxt;

    @FXML
    private TextField amountTxt;

    @FXML
    private ComboBox<Category> categoryCombo;

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
        newRecipe();
    }

    /**
     * Save recipe and move items to today's shopping list
     * @param event SaveAndAddBtn
     * @throws Exception exception
     */
    @FXML
    private void handleSaveAndAddBtn(ActionEvent event) throws Exception{
        Recipe conveyList = new Recipe();
        conveyList.load();
        conveyList.getItems()
                .forEach(item ->
                        shoppingList.addItem(item.toString()));

        returnToMainView(event);
    }

    @FXML
    private void handleAddIngredientBtn(ActionEvent event){
        IO.println("Adding new Ingredient");
        Platform.runLater(ingredientTxt::requestFocus);
        String ingredientName = ingredientTxt.getText();
        ListItem ingredient = new ListItem(ingredientName, true);
        double amount;

        try{
            amount = Double.parseDouble(amountTxt.getText());
            ingredient.setAmount(amount);
        } catch (Exception e) {
            IO.println("Give amount as integer or decimal: " + e.getMessage());
        }

        if(ingredientName == null || ingredientName.isBlank()){
            ingredientTxt.clear();
            return;
        }
        ingredient.setUnit(unitCombo.getValue());

        ingredientTxt.clear();
        amountTxt.clear();

        recipe.addItem(ingredient);
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

    FXMLLoader loader = new FXMLLoader(App.class.getResource("main.fxml"));
    Recipe recipe = new Recipe();
    RecipeLibrary recipeLibrary = new RecipeLibrary();
    ItemCollection shoppingList = new ItemCollection();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        recipeLibrary.loadRecipes();
        recipeNameTxt.requestFocus();
        newRecipeTable.setItems(recipe.getItems());
        shoppingList.load();

        amountColumn.setCellValueFactory(cd -> cd.getValue().amountProperty().asObject());
        unitColumn.setCellValueFactory(cd -> cd.getValue().unitProperty());
        nameColumn.setCellValueFactory(cd -> cd.getValue().titleProperty());
        newRecipeTable.getColumns().addAll(amountColumn, unitColumn, nameColumn);

        unitCombo.setItems(FXCollections.observableArrayList(Unit.values()));
        unitCombo.setValue(Unit.NULL);
        categoryCombo.setItems(FXCollections.observableArrayList(Category.values()));
        categoryCombo.setValue(Category.UNCATEGORIZED);
    }

    public void returnToMainView(ActionEvent event) throws Exception{
        Parent mainView = loader.load();
        Scene currentScene = ((Node) event.getSource()).getScene();
        currentScene.setRoot(mainView);
    }

    public void newRecipe(){
        Recipe newRecipe = new Recipe();
        String recipeName = recipeNameTxt.getText();

        if(recipeName == null || recipeName.isBlank()){
            recipeNameTxt.clear();
            return;
        }
        newRecipe.setRecipeName(recipeName);
        newRecipe.setCategory(categoryCombo.getValue());

        newRecipe.setItems(recipe.getItems());

        recipeLibrary.addRecipe(newRecipe);

        recipeNameTxt.clear();
        newRecipeTable.getItems().clear();
    }
}
