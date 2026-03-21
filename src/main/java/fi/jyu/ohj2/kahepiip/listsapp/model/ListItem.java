package fi.jyu.ohj2.kahepiip.listsapp.model;

import javafx.beans.property.*;

/**
 * An item which can be added/saved to a list. Item's name and completion
 * variables are being listened to.
 */
public class ListItem {
    private StringProperty title = new SimpleStringProperty();
    private BooleanProperty completion = new SimpleBooleanProperty(false);

    private boolean ingredient = false;

    private DoubleProperty amount = new SimpleDoubleProperty();
    private ObjectProperty<Unit> unit = new SimpleObjectProperty<>(Unit.NULL);

    public ListItem(){
    }

    /**
     * An observable item on a list.
     *
     * @param title item title
     */
    public ListItem(String title){
        this.title.set(title);
    }

    /**
     * An observable item on a recipe
     * @param title item title
     * @param ingredient is/isn't an ingredient
     */
    public ListItem(String title, boolean ingredient){
        this.title.set(title);
        this.ingredient = ingredient;
    }

    public void setTitle(String title){this.title.set(title);}
    public String getTitle(){return title.get();}
    public StringProperty titleProperty() {return this.title;}

    public void setCompletion(boolean completion) {this.completion.set(completion);}
    public boolean getCompletion() {return this.completion.get();}
    public BooleanProperty completionProperty() {return this.completion;}

    public void setAmount(double amount) {this.amount.set(amount);}
    public double getAmount() {return this.amount.get();}
    public DoubleProperty amountProperty() {return this.amount;}

    public void setUnit(Unit unit) {this.unit.set(unit);}
    public Unit getUnit() {return this.unit.get();}
    public ObjectProperty<Unit> unitProperty(){return this.unit;}

    public boolean isIngredient() {return ingredient;}
    public void setIngredient(boolean ingredient){this.ingredient = ingredient;}
}
