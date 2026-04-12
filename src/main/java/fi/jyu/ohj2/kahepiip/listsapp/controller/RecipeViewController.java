package fi.jyu.ohj2.kahepiip.listsapp.controller;

import fi.jyu.ohj2.kahepiip.listsapp.App;
import fi.jyu.ohj2.kahepiip.listsapp.model.*;
import javafx.collections.FXCollections;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTreeCell;

import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

public class RecipeViewController implements Initializable {

    @SuppressWarnings("unused")
    @FXML
    private ComboBox<Category> arrangeCombo;

    @SuppressWarnings("unused")
    @FXML
    private TreeView<RecipeParent> recipeTree;

    @SuppressWarnings("unused")
    @FXML
    private TreeItem<RecipeParent> libraryRoot;

    @SuppressWarnings("unused")
    @FXML
    private CheckBoxTreeItem<RecipeParent> cbRecipe;

    @SuppressWarnings("unused")
    @FXML
    private CheckBoxTreeItem<RecipeParent> cbIngredient;

    @SuppressWarnings("unused")
    @FXML
    private TextField searchTxt;

    @SuppressWarnings("unused")
    @FXML
    private Button searchBtn;

    @SuppressWarnings("unused")
    @FXML
    private Button newRecipeBtn;

    @SuppressWarnings("unused")
    @FXML
    private Button removeBtn;

    @SuppressWarnings("unused")
    @FXML
    private Button editBtn;

    @SuppressWarnings("unused")
    @FXML
    private Button returnBtn;

    @SuppressWarnings("unused")
    @FXML
    private void handleSearchBtn(ActionEvent event){
        searchForRecipe();
    }

    @FXML
    private void handleSearchTxt(ActionEvent event){
        searchForRecipe();
    }

    @SuppressWarnings("unused")
    @FXML
    private void handleNewRecipeBtn(ActionEvent event) throws Exception{
        Parent recipeView = FXMLLoader.load(App.class.getResource("newRecipeView.fxml"));
        Scene currentScene = ((Node) event.getSource()).getScene();
        currentScene.setRoot(recipeView);
    }

    @SuppressWarnings("unused")
    @FXML
    private void handleRemoveBtn(ActionEvent event){
        removeChosenRecipe();
    }

    @SuppressWarnings("unused")
    @FXML
    private void handleEditBtn(ActionEvent event){
        editRecipe(event);
    }

    @SuppressWarnings("unused")
    @FXML
    private void handleReturnBtn(ActionEvent event) throws Exception{
        Parent mainView = FXMLLoader.load(App.class.getResource("main.fxml"));
        Scene currentScene = ((Node) event.getSource()).getScene();
        currentScene.setRoot(mainView);
    }

    private RecipeLibrary recipeLibrary = new RecipeLibrary();
    private ItemCollection shoppingList = new ItemCollection();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        recipeLibrary.loadRecipes();
        shoppingList.load();
        libraryRoot = new TreeItem<>();

        /*
        Creates TreeView with Recipe-objects as branches and ListItems (ingredients)
        as leaves.
         */
        recipeTree.setCellFactory(CheckBoxTreeCell.forTreeView());
        for (Recipe r : recipeLibrary.getRecipes()){
            cbRecipe = new CheckBoxTreeItem<>(r);
            libraryRoot.getChildren().add(cbRecipe);

            for(ListItem ingredient : r.getItems()){
                // Create's a copy of ingredient, with amount and unit of measure
                // in item's name for adding to shopping list.
                ListItem item = new ListItem();
                item.setName(ingredient.toString());

                cbIngredient = new CheckBoxTreeItem<>(ingredient);
                cbRecipe.getChildren().add(cbIngredient);
                cbIngredient.selectedProperty().addListener((observable, oldValue, newValue)-> {
                    // CheckBox listener for adding checked ingredients to shopping list.
                    if(newValue == true){
                        shoppingList.addItem(item);
                    } else if (oldValue == true){ // if item is checked, and later unchecked.
                        shoppingList.removeItem(item);
                    }
                });
            }
        }

        arrangeCombo.setItems(FXCollections.observableArrayList(Category.values()));


        recipeTree.setRoot(libraryRoot);
        recipeTree.setShowRoot(false);
        recipeTree.setEditable(true);

        // Disables remove -button, if branch not selected.
        removeBtn.setDisable(true);
        recipeTree.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if(newValue == null || newValue.isLeaf()){
                        removeBtn.setDisable(true);
                    } else {
                        removeBtn.setDisable(false);
                    }
                });

        // Disables edit recipe -button, if branch not selected.
        editBtn.setDisable(true);
        recipeTree.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if(newValue == null || newValue.isLeaf()){
                        editBtn.setDisable(true);
                    } else {
                        editBtn.setDisable(false);
                    }
                });
    }

    /**
     * Removes chosen recipe from RecipeLibrary and TreeView.
     */
    private void removeChosenRecipe(){
        TreeItem<RecipeParent> chosenRecipe = recipeTree.getSelectionModel().getSelectedItem();
        if(chosenRecipe == null){
            return;
        }
        RecipeParent recipeP = chosenRecipe.getValue();
        recipeLibrary.removeRecipe(recipeLibrary.getRecipe(recipeP));
        libraryRoot.getChildren().remove(chosenRecipe);
    }

    private void editRecipe(ActionEvent event){
        TreeItem<RecipeParent> chosenRecipe = recipeTree.getSelectionModel().getSelectedItem();
        if(chosenRecipe == null){
            return;
        }
        RecipeParent recipeP = chosenRecipe.getValue();
        // Save ingredients for flatMapping.
        recipeLibrary.getRecipe(recipeP).save();

        try{
            FXMLLoader loader = new FXMLLoader(App.class.getResource("editRecipe.fxml"));
            Parent recipeView = loader.load();
            Scene currentScene = ((Node) event.getSource()).getScene();
            EditRecipeController controller = loader.getController();
            controller.setRecipeToEdit(recipeLibrary.getRecipe(recipeP));
            currentScene.setRoot(recipeView);
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    private void searchForRecipe(){
        String wanted = searchTxt.getText().trim().toLowerCase();

        if(wanted.isBlank()){
            searchTxt.clear();
            return;
        }
        // Collapse expanded branches
        libraryRoot.getChildren()
                .forEach(tb -> tb.setExpanded(false));

        // Search and expand Recipes which name contain search word.
        libraryRoot.getChildren()
                .stream()
                .filter(tb -> tb.getValue()
                        .getName()
                        .toLowerCase()
                        .contains(wanted))
                .forEach(tb -> {
                    tb.setExpanded(true);
                });

        // Search and expand Recipes which have ingredients that contain search word.
        libraryRoot.getChildren()
                .forEach(tb -> tb.getChildren()
                        .stream()
                        .filter(tl -> tl.getValue()
                                .getName()
                                .toLowerCase()
                                .contains(wanted))
                        .forEach(tl -> {
                            tl.getParent().setExpanded(true);
                        }));
    }
}
