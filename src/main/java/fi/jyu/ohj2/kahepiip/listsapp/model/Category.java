package fi.jyu.ohj2.kahepiip.listsapp.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Category {
    private final StringProperty categoryName = new SimpleStringProperty();

    public Category(){
    }

    public Category(String category){
        this.categoryName.set(category);
    }

    public void setCategoryName(String categoryName){
        this.categoryName.set(categoryName);
    }

    public String getCategoryName(){
        return categoryName.get();
    }

    public StringProperty categoryNameProperty(){
        return categoryName;
    }
}
