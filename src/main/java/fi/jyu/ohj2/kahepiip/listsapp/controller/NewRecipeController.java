package fi.jyu.ohj2.kahepiip.listsapp.controller;

import fi.jyu.ohj2.kahepiip.listsapp.App;
import fi.jyu.ohj2.kahepiip.listsapp.model.ListItem;
import fi.jyu.ohj2.kahepiip.listsapp.model.Recipe;
import fi.jyu.ohj2.kahepiip.listsapp.model.Unit;
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
    }

    @FXML
    private void handleSaveAndAddBtn(ActionEvent event) throws Exception{
        IO.println("Save and move to today's shopping list");
        returnToMainView(event);
    }

    @FXML
    private void handleAddIngredientBtn(ActionEvent event){
        IO.println("Add new Ingredient");
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        newRecipeTable.setItems(recipe.getItems());

        nameColumn.setCellValueFactory(cd -> cd.getValue().titleProperty());
        newRecipeTable.getColumns().add(nameColumn);

        amountColumn.setCellValueFactory(cd -> cd.getValue().amountProperty().asObject());
        newRecipeTable.getColumns().add(amountColumn);

        unitColumn.setCellValueFactory(cd -> cd.getValue().unitProperty());
        newRecipeTable.getColumns().add(unitColumn);
    }

    public void returnToMainView(ActionEvent event) throws Exception{
        Parent mainView = FXMLLoader.load(App.class.getResource("main.fxml"));
        Scene currentScene = ((Node) event.getSource()).getScene();
        currentScene.setRoot(mainView);
    }
}
