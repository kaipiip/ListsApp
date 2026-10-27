package fi.jyu.ohj2.kahepiip.listsapp.model;

import javafx.beans.property.*;

/**
 * ListItem-object with name, completion, amount and unit-properties.
 */
public class ListItem {
    /*------- ATTRIBUTES -------*/
    /**
     * ListItem-object's name
     */
    private final StringProperty name = new SimpleStringProperty();
    /**
     * ListItem-object's completion status.
     */
    private final BooleanProperty completion = new SimpleBooleanProperty(false);

    /*------- CONSTRUCTORS -------*/

    /**
     * New ListItem
     */
    public ListItem(){
    }

    /**
     * New ListItem
     * @param name item name
     */
    public ListItem(String name){
        this.setName(name);
    }

    /*------- METHODS ----------*/
    /*------- GETTERS -------*/
    public String getName(){return this.name.get();}
    public StringProperty getNameProperty(){return this.name;}
    public boolean getCompletion() {return this.completion.get();}
    public BooleanProperty getCompletionProperty() {return this.completion;}

    /*------- SETTERS -------*/
    public void setName(String name){this.name.set(name);}
    public void setCompletion(boolean completion) {this.completion.set(completion);}
}
