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

    @SuppressWarnings("unused")
    @FXML
    private TextField recipeNameTxt;

    @SuppressWarnings("unused")
    @FXML
    private TextField ingredientTxt;

    @SuppressWarnings("unused")
    @FXML
    private TextField amountTxt;

    @SuppressWarnings("unused")
    @FXML
    private ComboBox<Category> categoryCombo;

    @SuppressWarnings("unused")
    @FXML
    private ComboBox<Unit> unitCombo;

    @SuppressWarnings("unused")
    @FXML
    private TableView<ListItem> newRecipeTable;

    @SuppressWarnings("unused")
    @FXML
    private final TableColumn<ListItem, Double> amountColumn = new TableColumn<>("Amount");

    @SuppressWarnings("unused")
    @FXML
    private final TableColumn<ListItem, Unit> unitColumn = new TableColumn<>("Unit");

    @SuppressWarnings("unused")
    @FXML
    private final TableColumn<ListItem, String> nameColumn = new TableColumn<>("Ingredient");

    @SuppressWarnings("unused")
    @FXML
    private Button addIngredientBtn;

    @SuppressWarnings("unused")
    @FXML
    private Button saveBtn;

    @SuppressWarnings("unused")
    @FXML
    private Button saveAndAddBtn;

    @SuppressWarnings("unused")
    @FXML
    private Button returnBtn;

    @SuppressWarnings("unused")
    @FXML
    private Button deleteIngredientBtn;

    /**
     * Save created Recipe to RecipeLibrary and clear TableView and textFields
     * @param event saveBtn
     */
    @SuppressWarnings("unused")
    @FXML
    private void handleSaveBtn(ActionEvent event){
        newRecipe();
    }

    /**
     * Save recipe and move items to today's shopping list,
     * if no new recipe created, return to mainView without adding
     * ingredients to shopping list.
     * @param event SaveAndAddBtn
     * @throws Exception exception
     */
    @SuppressWarnings("unused")
    @FXML
    private void handleSaveAndAddBtn(ActionEvent event) throws Exception{
        String text = recipeNameTxt.getText();

        if(text == null || text.isBlank()){
            returnToMainView(event);
            return;
        }

        Recipe conveyList = new Recipe();
        conveyList.load();
        conveyList.getItems()
                .forEach(item ->
                        shoppingList.addItem(item.toString()));

        newRecipe();
        returnToMainView(event);
    }

    /**
     * Creates new ListItem as ingredient to Recipe with input from
     * TextFields and ComboBox if successful. Clears TextFields.
     * @param event addIngredientBtn
     */
    @SuppressWarnings("unused")
    @FXML
    private void handleAddIngredientBtn(ActionEvent event){
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

    /**
     * Change scene to main.fxml
     * @param event returnBtn
     * @throws Exception exception
     */
    @SuppressWarnings("unused")
    @FXML
    private void handleReturnBtn(ActionEvent event) throws Exception{
        returnToMainView(event);
    }

    /**
     * Remove chosen ingredient from recipe.
     * @param event deleteIngredientBtn
     */
    @SuppressWarnings("unused")
    @FXML
    private void handleDeleteIngredientBtn(ActionEvent event){
        removeChosenItem();
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
        nameColumn.setCellValueFactory(cd -> cd.getValue().nameProperty());
        newRecipeTable.getColumns().addAll(amountColumn, unitColumn, nameColumn);

        unitCombo.setItems(FXCollections.observableArrayList(Unit.values()));
        unitCombo.setValue(Unit.NULL);
        categoryCombo.setItems(FXCollections.observableArrayList(Category.values()));
        categoryCombo.setValue(Category.UNCATEGORIZED);

        /*
        Hides delete button, if row not selected.
         */
        deleteIngredientBtn.setVisible(false);
        newRecipeTable.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if(newValue == null){
                        deleteIngredientBtn.setVisible(false);
                    } else {
                        deleteIngredientBtn.setVisible(true);
                    }
                });

        /*
        Clicking empty row clear's selection.
         */
        newRecipeTable.setRowFactory(tv -> {
            TableRow<ListItem> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if(row.isEmpty()){
                    newRecipeTable.getSelectionModel().clearSelection();
                }
            });
            return row;
        });
    }

    /**
     * Change scene to main.fxml
     * @param event event Button
     * @throws Exception exception
     */
    private void returnToMainView(ActionEvent event) throws Exception{
        Parent mainView = loader.load();
        Scene currentScene = ((Node) event.getSource()).getScene();
        currentScene.setRoot(mainView);
    }

    /**
     * Creates and saves new Recipe from current TableView ListItems,
     * TextField (recipe name) and comboBox(category).
     */
    private void newRecipe(){
        Recipe newRecipe = new Recipe();
        String recipeName = recipeNameTxt.getText();

        if(recipeName == null || recipeName.isBlank()){
            recipeNameTxt.clear();
            return;
        }
        newRecipe.setName(recipeName);
        newRecipe.setCategory(categoryCombo.getValue());

        newRecipe.setItems(recipe.getItems());

        recipeLibrary.addRecipe(newRecipe);

        recipeNameTxt.clear();
        newRecipeTable.getItems().clear();
    }

    private void removeChosenItem(){
        ListItem chosenItem = newRecipeTable.getSelectionModel().getSelectedItem();
        if(chosenItem == null){
            return;
        }
        recipe.removeItem(chosenItem);
    }
}
