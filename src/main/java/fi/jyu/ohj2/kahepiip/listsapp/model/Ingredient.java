package fi.jyu.ohj2.kahepiip.listsapp.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Ingredient extends ListItem{
    /*------- ATTRIBUTES -------*/
    /**
     * Ingredient's amount.
     */
    private final DoubleProperty amount = new SimpleDoubleProperty();

    /**
     * Ingredient's unit of measure.
     */
    private final ObjectProperty<Unit> unit = new SimpleObjectProperty<>(Unit.UNIT);

    /*------- METHODS ----------*/
    /*-------- GETTERS --------*/
    public double getAmount() {return this.amount.get();}
    public DoubleProperty getAmountProperty() {return this.amount;}
    public Unit getUnit() {return this.unit.get();}
    public ObjectProperty<Unit> getUnitProperty(){return this.unit;}

    /*-------- SETTERS --------*/
    public void setAmount(double amount) {this.amount.set(amount);}
    public void setUnit(Unit unit) {this.unit.set(unit);}

    /*-------- OTHERS --------*/
    public ListItem convertToListItem(){
        ListItem item = new ListItem(this.getName());
        return item;
    }
}
