package fi.jyu.ohj2.kahepiip.listsapp.model;

/*
Class for future development of the application. This is a simplified class for a saved list,
which is extended by the recipe -class.

Sovelluksen jatkokehitystä, eli tallennettavaa yksittäistä listaa varten yksinkertaisempi luokka,
jota käytetään yksittäisen reseptin yläluokkana.
 */

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

/**
 * A list of ListItem-objects, saved as a list of those items.
 */
public class Sheet extends Library implements LibraryInterface{

    private StringProperty sheetTitle = new SimpleStringProperty();
    private ObservableList<ObList> items = observeTitleAndCompletion();

    public Sheet(){
    }

    public Sheet(String title){
        this.setTitle(title);
        listener(items);
    }

    public void setTitle(String title){this.sheetTitle.set(title);}
    public String getTitle(){return sheetTitle.get();}

    @Override
    public void addMember(String title) {
        this.getItems().add(new SheetItem(title.trim(), false));
    }

}
