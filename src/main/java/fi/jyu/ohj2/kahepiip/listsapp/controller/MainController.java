package fi.jyu.ohj2.kahepiip.listsapp.controller;

import fi.jyu.ohj2.kahepiip.listsapp.App;
import fi.jyu.ohj2.kahepiip.listsapp.model.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;

import java.net.URL;
import java.util.ResourceBundle;


public class MainController implements Initializable {

    @FXML
    private TextField itemTxt;

    @FXML
    private Button addItemBtn;

    @FXML
    private Button emptyBtn;

    @FXML
    private Button recipesBtn;

    @FXML
    private Button newRecipeBtn;

    /*
    To be developed later
     */
    @FXML
    private Button savedListsBtn;

    /*
    To be developed later
     */
    @FXML
    private Button saveBtn;

    @FXML
    private TableView<ListItem> itemTable;

    @FXML
    private CheckBox cb;

    @FXML
    private void handleAddItemBtn(ActionEvent event){
        addItemOnList();
    }

    @FXML
    private void handleItemTxt(ActionEvent event){
        addItemOnList();
    }

    @FXML
    private void handleEmptyBtn(ActionEvent event){
        itemTable.getItems().removeAll(shoppingList.getItems());
        IO.println("Trying to remove.");
    }

    @FXML
    private void handleRecipesBtn(ActionEvent event) throws Exception {
        IO.println("Go to Recipe View.");
        Parent recipeView = FXMLLoader.load(App.class.getResource("recipeView.fxml"));
        Scene currentScene = ((Node) event.getSource()).getScene();
        currentScene.setRoot(recipeView);
    }

    @FXML
    private void handleNewRecipeBtn(ActionEvent event) throws Exception{
        IO.println("Create a new recipe.");
        Parent recipeView = FXMLLoader.load(App.class.getResource("newRecipeView.fxml"));
        Scene currentScene = ((Node) event.getSource()).getScene();
        currentScene.setRoot(recipeView);

    }

    /*
    To be developed later
    */
    @FXML
    private void handleSavedListsBtn(ActionEvent event){
    }

    /*
    To be developed later
    */
    @FXML
    private void handleSaveBtn(ActionEvent event){
    }

    ItemCollection shoppingList = new ItemCollection();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        itemTable.setItems(shoppingList.getItems());
        itemTable.setEditable(true);

        TableColumn<ListItem, Boolean> completedColumn = new TableColumn<>("Check box");
        completedColumn.setCellValueFactory(cd -> cd.getValue().completionProperty());
        completedColumn.setCellFactory(CheckBoxTableCell.forTableColumn(completedColumn));

        TableColumn<ListItem, String> itemNameColumn = new TableColumn<>("Item");
        itemNameColumn.setCellValueFactory(cd -> cd.getValue().titleProperty());
        itemTable.getColumns().addAll(completedColumn, itemNameColumn);

    }

    /**
     * Action event handler for adding item on Today's shopping list.
     */
    private void addItemOnList(){
        Platform.runLater(itemTxt::requestFocus);
        String item = itemTxt.getText();

        if(item == null || item.isBlank()){
            itemTxt.clear();
            return;
        }
        shoppingList.addItem(item);
        itemTxt.clear();
    }
}
