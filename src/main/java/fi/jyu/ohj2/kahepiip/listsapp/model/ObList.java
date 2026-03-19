package fi.jyu.ohj2.kahepiip.listsapp.model;

import javafx.beans.Observable;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public abstract class ObList {
    private StringProperty title = new SimpleStringProperty();
    private BooleanProperty completion = new SimpleBooleanProperty();
    private DoubleProperty amount = new SimpleDoubleProperty();
    private ObjectProperty<Unit> unit = new SimpleObjectProperty<>(Unit.NULL);

    //Voiko muuttaaa private, kun listan luominen on public?
    public StringProperty titleProperty() {return this.title;}
    public BooleanProperty completionProperty() {return this.completion;}
    public DoubleProperty amountProperty() {return this.amount;}
    public ObjectProperty<Unit> unitProperty(){return this.unit;}


    private ObservableList<ObList> list;

    /**
     * Get observable list variables.
     * @return This observable list.
     */
    public ObservableList<ObList> getItems(){return this.list;}

    /**
     * Creates observable list for two observable variables.
     * @return Observable list for title and completion properties.
     */
    public ObservableList<ObList> observeTitleAndCompletion(){
        list = FXCollections.observableArrayList(
                item -> new Observable[]{
                        item.titleProperty(),
                        item.completionProperty()
                });
        return list;
    }

    /**
     * Creates observable list for all four observable variables.
     * @return Observable list for title, completion, amount and unit properties.
     */
    public ObservableList<ObList> observeAll(){
        list = FXCollections.observableArrayList(
                item -> new Observable[]{
                        item.titleProperty(),
                        item.completionProperty(),
                        item.amountProperty(),
                        item.unitProperty()
                });
        return list;
    }
}
