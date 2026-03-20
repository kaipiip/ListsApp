package fi.jyu.ohj2.kahepiip.listsapp.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * An ingredient which can be added/saved to a recipe. Ingredient's name, completion,
 * amount and unit variables are being listened to.
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
     * @param ingredientName Name of this ingredient
     */
    public Ingredient(String ingredientName) {
        super(ingredientName);
    }

    public void setAmount(double amount) {this.amount.set(amount);}
    public double getAmount() {return this.amount.get();}
    public DoubleProperty amountProperty() {return this.amount;}

    public void setUnit(Unit unit) {this.unit.set(unit);}
    public Unit getUnit() {return this.unit.get();}
    public ObjectProperty<Unit> unitProperty(){return this.unit;}

}
