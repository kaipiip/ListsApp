package fi.jyu.ohj2.kahepiip.listsapp.model;

import javafx.beans.property.*;

/**
 * Abstract class for single items and ingredients.
 * Each item has a name, amount, unit of measure and completion status.
 */
public abstract class Item {

    private StringProperty itemName = new SimpleStringProperty();
    private BooleanProperty completion = new SimpleBooleanProperty();
    private DoubleProperty amount = new SimpleDoubleProperty();
    private ObjectProperty<Unit> unit = new SimpleObjectProperty<>(Unit.NULL);

    public void setItemName(String itemName){this.itemName.set(itemName);}
    public String getItemName() {return this.itemName.get();}
    public StringProperty ItemNameProperty() {return this.itemName;}

    public void setCompletion(boolean completion) {this.completion.set(completion);}
    public boolean getCompletion() {return this.completion.get();}
    public BooleanProperty completionProperty() {return this.completion;}

    public DoubleProperty amountProperty() {return this.amount;}
    public ObjectProperty<Unit> unitProperty(){return this.unit;}
}
