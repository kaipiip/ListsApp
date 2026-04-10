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

public class EditRecipeController implements Initializable{

    @FXML
    private TextField addAmountTxt;

    @FXML
    private Button addNewIngredientBtn;

    @FXML
    private ComboBox<Unit> addUnitCombo;

    @FXML
    private TextField addingredientTxt;

    @FXML
    private ComboBox<Category> editCategoryCombo;

    @FXML
    private TextField editNameTxt;

    @FXML
    private TableView<ListItem> editRecipeTable;

    @FXML
    private final TableColumn<ListItem, Double> amountColumn = new TableColumn<>("Amount");

    @SuppressWarnings("unused")
    @FXML
    private final TableColumn<ListItem, Unit> unitColumn = new TableColumn<>("Unit");

    @SuppressWarnings("unused")
    @FXML
    private final TableColumn<ListItem, String> nameColumn = new TableColumn<>("Ingredient");

    @FXML
    private Button removeIngredientBtn;

    @FXML
    private Button returnToRecipesBtn;

    @FXML
    private Button saveAndAddBtn;

    @FXML
    private Button saveEditedBtn;

    @FXML
    void handleAddNewIngredientBtn(ActionEvent event) {
        Platform.runLater(addingredientTxt::requestFocus);
        String ingredientName = addingredientTxt.getText();
        ListItem ingredient = new ListItem(ingredientName, true);
        double amount;

        try{
            amount = Double.parseDouble(addAmountTxt.getText());
            ingredient.setAmount(amount);
        } catch (Exception e) {
            IO.println("Give amount as integer or decimal: " + e.getMessage());
        }

        if(ingredientName == null || ingredientName.isBlank()){
            addingredientTxt.clear();
            return;
        }
        ingredient.setUnit(addUnitCombo.getValue());

        addingredientTxt.clear();
        addAmountTxt.clear();

        recipe.addItem(ingredient);
    }

    @FXML
    void handleRemoveIngredientBtn(ActionEvent event) {
        removeChosenItem();
    }

    @FXML
    void handleReturnToRecipesBtn(ActionEvent event) throws Exception {
        returnToRecipeView(event);
    }

    @FXML
    void handleSaveEditedAndAddBtn(ActionEvent event) throws Exception{
        if(!ValidateRecipe()){
            returnToRecipeView(event);
            return;
        }

        Recipe conveyList = new Recipe();
        conveyList.load();
        conveyList.getItems()
                .forEach(item ->
                        shoppingList.addItem(item.toString()));


        saveEditedRecipe();
        returnToMainView(event);
    }

    @FXML
    void handleSaveEditedBtn(ActionEvent event) throws Exception{
        saveEditedRecipe();
        returnToRecipeView(event);
    }

    RecipeLibrary recipeLibrary = new RecipeLibrary();
    ItemCollection shoppingList = new ItemCollection();
    private Recipe recipe = new Recipe();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        recipeLibrary.loadRecipes();
        editNameTxt.requestFocus();
        editRecipeTable.setItems(recipe.getItems());
        shoppingList.load();

        amountColumn.setCellValueFactory(cd -> cd.getValue().amountProperty().asObject());
        unitColumn.setCellValueFactory(cd -> cd.getValue().unitProperty());
        nameColumn.setCellValueFactory(cd -> cd.getValue().nameProperty());
        editRecipeTable.getColumns().addAll(amountColumn, unitColumn, nameColumn);

        addUnitCombo.setItems(FXCollections.observableArrayList(Unit.values()));
        addUnitCombo.setValue(Unit.NULL);
        editCategoryCombo.setItems(FXCollections.observableArrayList(Category.values()));
        editCategoryCombo.setValue(Category.UNCATEGORIZED);

        // Hides delete -button, if row not selected.
        removeIngredientBtn.setVisible(false);
        editRecipeTable.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if(newValue == null){
                        removeIngredientBtn.setVisible(false);
                    } else {
                        removeIngredientBtn.setVisible(true);
                    }
                });

        // Clicking empty row clear's selection.
        editRecipeTable.setRowFactory(tv -> {
            TableRow<ListItem> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if(row.isEmpty()){
                    editRecipeTable.getSelectionModel().clearSelection();
                }
            });
            return row;
        });
    }

    /**
     * Change scene to recipeView.fxml
     * @param event event Button
     * @throws Exception exception
     */
    private void returnToRecipeView(ActionEvent event) throws Exception{
        FXMLLoader loader = new FXMLLoader(App.class.getResource("recipeView.fxml"));
        Parent mainView = loader.load();
        Scene currentScene = ((Node) event.getSource()).getScene();
        currentScene.setRoot(mainView);
    }

    /**
     * Change scene to main.fxml
     * @param event event Button
     * @throws Exception exception
     */
    private void returnToMainView(ActionEvent event) throws Exception{
        FXMLLoader loader = new FXMLLoader(App.class.getResource("main.fxml"));
        Parent mainView = loader.load();
        Scene currentScene = ((Node) event.getSource()).getScene();
        currentScene.setRoot(mainView);
    }

    /**
     * Creates and saves new Recipe from current TableView ListItems,
     * TextField (recipe name) and comboBox(category).
     */
    private void saveEditedRecipe(){
        String recipeName = editNameTxt.getText();

        if(!ValidateRecipe()){
            return;
        }
        recipe.setName(recipeName);
        recipe.setCategory(editCategoryCombo.getValue());
        recipe.setItems(editRecipeTable.getItems());

        editNameTxt.clear();
        editRecipeTable.getItems().clear();
    }

    /**
     * Removes chosen ingredient from ingredient list.
     */
    private void removeChosenItem(){
        ListItem chosenItem = editRecipeTable.getSelectionModel().getSelectedItem();
        if(chosenItem == null){
            return;
        }
        recipe.removeItem(chosenItem);
    }

    public void setRecipeToEdit(Recipe recipe){
        this.recipe = recipe;
        editNameTxt.setText(recipe.getName());
        editCategoryCombo.setValue(recipe.getCategory());
        editRecipeTable.setItems(recipe.getItems());
    }

    /**
     * Validates creating/saving a recipe.
     * @return If Recipe has no name or ingredients returns false, else true.
     */
    private boolean ValidateRecipe(){
        String  recipeName = editNameTxt.getText();

        if(recipeName == null || recipeName.isBlank()){
            editNameTxt.clear();
            return false;
        }
        if(recipe.getItems().isEmpty()){
            return false;
        }

        return true;
    }
}

