package fi.jyu.ohj2.kahepiip.listsapp.controller;

import fi.jyu.ohj2.kahepiip.listsapp.model.Sheet;
import fi.jyu.ohj2.kahepiip.listsapp.model.SheetItem;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;

import java.net.URL;
import java.util.List;
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
    private TableView<SheetItem> itemTable;

    @FXML
    private CheckBox cb;

    @FXML
    private void handleAddItemBtn(ActionEvent event){
        String item = itemTxt.getText();

        if(item == null || item.isBlank()){
            itemTxt.clear();
            return;
        }
    }

    @FXML
    private void handleEmptyBtn(ActionEvent event){
    }

    @FXML
    private void handleRecipesBtn(ActionEvent event) {
    }

    @FXML
    private void handleNewRecipeBtn(ActionEvent event) {

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        TableColumn<SheetItem, Boolean> completedColumn = new TableColumn<>("Check box");
        completedColumn.setCellValueFactory(cd -> cd.getValue().completionProperty());
        completedColumn.setCellFactory(CheckBoxTableCell.forTableColumn(completedColumn));
        itemTable.getColumns().add(completedColumn);

        TableColumn<SheetItem, String> itemName = new TableColumn<>("Item");
        itemName.setCellValueFactory(cd -> cd.getValue().titleProperty());
        itemTable.getColumns().add(itemName);

    }
}
