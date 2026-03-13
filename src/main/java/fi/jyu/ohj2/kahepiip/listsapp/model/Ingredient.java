package fi.jyu.ohj2.kahepiip.listsapp.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * An object on a recipe -list.
 */
public class Ingredient extends SheetItem {

    private DoubleProperty amount = new SimpleDoubleProperty();
    private ObjectProperty<Unit> unit = new SimpleObjectProperty<>(Unit.NULL);

    /**
     * An ingredient in a recipe
     */
    public Ingredient() {
    }

    /**
     *  An ingredient in a recipe
     * @param ingredientName Name of this ingredient
     * @param isBought Status of this ingredient
     */
    public Ingredient(String ingredientName, boolean isBought) {
        super(ingredientName, isBought);
        listener(this.getItems());
    }

    public void setAmount(double amount) {this.amount.set(amount);}
    public double getAmount() {return this.amount.get();}

    public void setUnit(Unit unit) {this.unit.set(unit);}
    public Unit getUnit() {return this.unit.get();}

}
