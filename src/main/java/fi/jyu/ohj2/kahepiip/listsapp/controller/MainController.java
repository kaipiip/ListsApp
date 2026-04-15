package fi.jyu.ohj2.kahepiip.listsapp.controller;

import fi.jyu.ohj2.kahepiip.listsapp.App;
import fi.jyu.ohj2.kahepiip.listsapp.model.*;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;

import java.net.URL;
import java.util.ResourceBundle;


@SuppressWarnings("ALL")
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
    private final TableColumn<ListItem, ListItem> itemNameColumn = new TableColumn<>("Item");

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

        itemNameColumn.setCellValueFactory(cd -> new SimpleObjectProperty<>(cd.getValue()));
        // Text color change for completed items:
        itemNameColumn.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(ListItem item, boolean empty) {
                super.updateItem(item, empty);
                /*
                For the following, I got help from this StackOverflow q and a:
                https://stackoverflow.com/questions/67977644/javafx-tablecell-linked-to-more-than-one-property
                 */
                textProperty().unbind();
                styleProperty().unbind();

                if (item == null || empty) {
                    setText(null);
                    setStyle("");
                } else {
                    textProperty().bind(item.nameProperty());
                    styleProperty().bind(
                            Bindings.when(item.completionProperty())
                                    .then("-fx-text-fill: rgba(0,0,0,0.3);").otherwise("")
                    );
                }
            }
        });
        itemNameColumn.setMinWidth(240);


        itemTable.getColumns().addAll(completedColumn, itemNameColumn);

        // Hides delete item -button, if row not  selected.
        deleteItemBtn.setVisible(false);
        itemTable.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    deleteItemBtn.setVisible(newValue != null);
                });

        saveBtn.setDisable(true);
        savedListsBtn.setDisable(true);

        // Clicking empty row clear's selection.
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

    /**
     * Removes chosen item from shopping-list.
     */
    private void removeChosenItem(){
        ListItem chosenItem = itemTable.getSelectionModel().getSelectedItem();
        if(chosenItem == null){
            return;
        }
        shoppingList.removeItem(chosenItem);
    }
}
