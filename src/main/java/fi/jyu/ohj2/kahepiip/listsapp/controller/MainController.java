package fi.jyu.ohj2.kahepiip.listsapp.controller;

import fi.jyu.ohj2.kahepiip.listsapp.model.SheetItem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

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
    private TableView<SheetItem> itemTable;

    @FXML
    private CheckBox cb;

    @FXML
    private void handleAddItemBtn(ActionEvent event){
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

    }
}
