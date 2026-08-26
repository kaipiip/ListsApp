package fi.jyu.ohj2.kahepiip.listsapp.model;

import javafx.beans.property.*;

/**
 * ListItem-object with title, completion, amount and unit-properties.
 */
public class ListItem extends RecipeParent {
    /*------- ATTRIBUTES -------*/
    /**
     * ListItem-object's completion status.
     */
    private final BooleanProperty completion = new SimpleBooleanProperty(false);

    /**
     * ListItem-objects status as ingredient or item.
     * False = item.
     * True = ingredient.
     */
    private boolean ingredient = false;

    /* Following properties apply only to items which have ingredient value true.*/
    /**
     * Ingredient's amount.
     */
    private final DoubleProperty amount = new SimpleDoubleProperty();

    /**
     * Ingredient's unit of measure.
     */
    private final ObjectProperty<Unit> unit = new SimpleObjectProperty<>(Unit.UNIT);

    /*------- CONSTRUCTORS -------*/
    public ListItem(){
    }

    /**
     * New ListItem
     * @param title item title
     */
    public ListItem(String title){
        this.setName(title);
    }

    /**
     * New ListItem with specification for ingredient status.
     * @param title item title
     * @param ingredient is/isn't an ingredient
     */
    public ListItem(String title, boolean ingredient){
        this.setName(title);
        this.ingredient = ingredient;
    }

    /*------- METHODS ----------*/
    /*------- GETTERS -------*/
    public boolean getCompletion() {return this.completion.get();}
    public BooleanProperty completionProperty() {return this.completion;}
    public double getAmount() {return this.amount.get();}
    public DoubleProperty amountProperty() {return this.amount;}
    public Unit getUnit() {return this.unit.get();}
    public ObjectProperty<Unit> unitProperty(){return this.unit;}

    /**
     *
     * @return boolean value of item's ingredient status.
     */
    public boolean isIngredient() {return ingredient;}

    /*------- SETTERS -------*/
    public void setCompletion(boolean completion) {this.completion.set(completion);}
    public void setAmount(double amount) {this.amount.set(amount);}
    public void setUnit(Unit unit) {this.unit.set(unit);}

    /**
     * Sets ListItem's ingredient status
     * @param ingredient ListItem is/isn't an ingredient
     */
    public void setIngredient(boolean ingredient){this.ingredient = ingredient;}

    /*------- OTHERS -------*/

    /**
     * Object variables to String.
     * @return ListItem's amount, unit and name as String.
     */
    @Override
    public String toString() {
        return (getAmount() + " " + getUnit().toString().toLowerCase() + " " + getName());
    }
}
