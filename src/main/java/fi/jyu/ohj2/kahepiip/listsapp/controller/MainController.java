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
import javafx.scene.control.cell.TextFieldTableCell;

import java.net.URL;
import java.util.ResourceBundle;


public class MainController implements Initializable {

    @SuppressWarnings("unused")
    @FXML
    private TextField itemTxt;

    @SuppressWarnings("unused")
    @FXML
    private Button addItemBtn;

    @SuppressWarnings("unused")
    @FXML
    private Button emptyBtn;

    @SuppressWarnings("unused")
    @FXML
    private Button recipesBtn;

    @SuppressWarnings("unused")
    @FXML
    private Button newRecipeBtn;

    @SuppressWarnings("unused")
    @FXML
    private Button deleteItemBtn;

    /*
    To be developed later
     */
    @SuppressWarnings("unused")
    @FXML
    private Button savedListsBtn;

    /*
    To be developed later
     */
    @SuppressWarnings("unused")
    @FXML
    private Button saveBtn;

    @SuppressWarnings("unused")
    @FXML
    private TableView<ListItem> itemTable;

    @SuppressWarnings("unused")
    @FXML
    private final TableColumn<ListItem, Boolean> completedColumn = new TableColumn<>("Check");

    @SuppressWarnings("unused")
    @FXML
    private final TableColumn<ListItem, String> itemNameColumn = new TableColumn<>("Item");

    @SuppressWarnings("unused")
    @FXML
    private void handleAddItemBtn(ActionEvent event){
        addItemOnList();
    }

    @SuppressWarnings("unused")
    @FXML
    private void handleItemTxt(ActionEvent event){
        addItemOnList();
    }

    @SuppressWarnings("unused")
    @FXML
    private void handleDeleteItemBtn(ActionEvent event) {
        removeChosenItem();
    }

    /**
     * Clears TableView and ItemCollection
     * @param event emptyBtn
     */
    @SuppressWarnings("unused")
    @FXML
    private void handleEmptyBtn(ActionEvent event){
        itemTable.getItems().removeAll(shoppingList.getItems());
    }

    /**
     * Change scene to recipeView.fxml
     * @param event recipesBtn
     * @throws Exception exception
     */
    @SuppressWarnings("unused")
    @FXML
    private void handleRecipesBtn(ActionEvent event) throws Exception {
        IO.println("Go to Recipe View.");
        Parent recipeView = FXMLLoader.load(App.class.getResource("recipeView.fxml"));
        Scene currentScene = ((Node) event.getSource()).getScene();
        currentScene.setRoot(recipeView);
    }

    /**
     * Change scene to newRecipeView.fxml
     * @param event newRecipeBtn
     * @throws Exception exception
     */
    @SuppressWarnings("unused")
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
    @SuppressWarnings("unused")
    @FXML
    private void handleSavedListsBtn(ActionEvent event){
    }

    /*
    To be developed later
    */
    @SuppressWarnings("unused")
    @FXML
    private void handleSaveBtn(ActionEvent event){
    }

    /**
     * ItemCollection of ListItems for TableView
     */
    private final ItemCollection shoppingList = new ItemCollection();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        itemTable.setItems(shoppingList.getItems());
        itemTable.setEditable(true);

        completedColumn.setCellValueFactory(cd -> cd.getValue().completionProperty());
        completedColumn.setCellFactory(CheckBoxTableCell.forTableColumn(completedColumn));
        completedColumn.setPrefWidth(50);

        itemNameColumn.setCellValueFactory(cd -> cd.getValue().nameProperty());
        // MITEN ESTÄÄ TYHJÄKSI JÄTTÄMINEN?
        itemNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        itemNameColumn.setPrefWidth(240);

        itemTable.getColumns().addAll(completedColumn, itemNameColumn);

        /*
        Hides delete item button, if row not  selected.
         */
        deleteItemBtn.setVisible(false);
        itemTable.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if(newValue == null){
                        deleteItemBtn.setVisible(false);
                    } else {
                      deleteItemBtn.setVisible(true);
                    }
                });

        saveBtn.setDisable(true);
        savedListsBtn.setDisable(true);

        /*
        Clicking empty row clear's selection.
         */
        itemTable.setRowFactory(tv -> {
            TableRow<ListItem> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if(row.isEmpty()){
                    itemTable.getSelectionModel().clearSelection();
                }
            });
            return row;
        });

        shoppingList.load();

    }

    /**
     * Creates new ListItem to Today's shopping list (ItemCollection) from TextField input.
     * If TextField is null or blank, no new ListItems are created.
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

    private void removeChosenItem(){
        ListItem chosenItem = itemTable.getSelectionModel().getSelectedItem();
        if(chosenItem == null){
            return;
        }
        shoppingList.removeItem(chosenItem);
    }
}
