package fi.jyu.ohj2.kahepiip.listsapp.model;

/*
Class for future development of the application. This is a simplified class for a saved list,
which is extended by the recipe -class.

Sovelluksen jatkokehitystä, eli tallennettavaa yksittäistä listaa varten yksinkertaisempi luokka,
jota käytetään yksittäisen reseptin yläluokkana.
 */

/**
 * A list of ListItem-objects, saved as a list of those items.
 */
public class ListOfItems extends List {


    public  ListOfItems(){
    }

    public ListOfItems(String listHeader){
        this.setListHeader(listHeader);
        Listener(this.getItems());
    }

    @Override
    public void addItem(String itemName) {
        this.getItems().add(new ListItem(itemName.trim(), false));
    }

}
