package fi.jyu.ohj2.kahepiip.listsapp.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class ListItem {
    private StringProperty title;
    private BooleanProperty completion;

    public ListItem(){
    }

    public ListItem(String title){
        this.title = new SimpleStringProperty(title);
        this.completion = new SimpleBooleanProperty(false);
    }

    public void setTitle(String title){this.title.set(title);}
    public String getTitle(){return title.get();}
    public StringProperty titleProperty() {return this.title;}

    public void setCompletion(boolean completion) {this.completion.set(completion);}
    public boolean getCompletion() {return this.completion.get();}
    public BooleanProperty completionProperty() {return this.completion;}
}
