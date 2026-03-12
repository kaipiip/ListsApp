package fi.jyu.ohj2.kahepiip.listsapp.model;

import javafx.beans.property.*;

/**
 * An item on a recipe.
 */
public class Ingredient extends ListItem {

    private DoubleProperty amount = new SimpleDoubleProperty();
    private ObjectProperty<Unit> unit = new SimpleObjectProperty<>(Unit.NULL);

    /**
     * An ingredient in a recipe
     */
    public Ingredient() {
    }

    /**
     *  An ingredient in a recipe
     * @param ingredientName Name of the ingredient
     * @param isBought Status of this ingredient
     */
    public Ingredient(String ingredientName, boolean isBought) {
        this.setItemName(ingredientName);
        this.setCompleted(isBought);
    }

    public void setAmount(double amount) {this.amount.set(amount);}
    public double getAmount() {return this.amount.get();}
    public DoubleProperty amountProperty() {return this.amount;}


    public void setUnit(Unit unit) {this.unit.set(unit);}
    public Unit getUnit() {return this.unit.get();}
    public ObjectProperty<Unit> unitProperty(){return this.unit;}
}
