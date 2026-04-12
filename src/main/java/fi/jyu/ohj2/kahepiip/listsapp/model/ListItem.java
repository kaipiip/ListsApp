package fi.jyu.ohj2.kahepiip.listsapp.model;

import javafx.beans.property.*;

/**
 * ListItem-object with title, completion, amount and unit-properties.
 */
public class ListItem extends RecipeParent {

    /**
     * ListItem-object's completion status.
     */
    private BooleanProperty completion = new SimpleBooleanProperty(false);

    /**
     * ListItem-objects status as ingredient or item.
     * False = item.
     * True = ingredient.
     */
    private boolean ingredient = false;
    // Following properties apply only to items which have ingredient value true.

    /**
     * Ingredient's amount.
     */
    private DoubleProperty amount = new SimpleDoubleProperty();

    /**
     * Ingredients unit of measure.
     */
    private ObjectProperty<Unit> unit = new SimpleObjectProperty<>(Unit.UNIT);

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

    public void setCompletion(boolean completion) {this.completion.set(completion);}
    public boolean getCompletion() {return this.completion.get();}
    public BooleanProperty completionProperty() {return this.completion;}

    public void setAmount(double amount) {this.amount.set(amount);}
    public double getAmount() {return this.amount.get();}
    public DoubleProperty amountProperty() {return this.amount;}

    public void setUnit(Unit unit) {this.unit.set(unit);}
    public Unit getUnit() {return this.unit.get();}
    public ObjectProperty<Unit> unitProperty(){return this.unit;}

    /**
     *
     * @return boolean value of item's ingredient status.
     */
    public boolean isIngredient() {return ingredient;}

    /**
     * Sets ListItem's ingredient status
     * @param ingredient ListItem is/isn't an ingredient
     */
    public void setIngredient(boolean ingredient){this.ingredient = ingredient;}

    /**
     * Object variables to String.
     * @return ListItem's amount, unit and name as String.
     */
    @Override
    public String toString() {
        return (getAmount() + " " + getUnit().toString().toLowerCase() + " " + getName());
    }
}
