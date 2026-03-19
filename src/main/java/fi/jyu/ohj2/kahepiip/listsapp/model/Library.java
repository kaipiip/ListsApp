package fi.jyu.ohj2.kahepiip.listsapp.model;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import tools.jackson.databind.ObjectMapper;

/**
 * Abstract class for all Library sheets.
 */
public class Library extends ObList implements LibraryInterface {

    private String header;
    private boolean recipe = false;
    private ObservableList<ObList> library = observeTitleAndCompletion();

    /**
     * Library(Observable List) of recipes or sheets that's titles are being listened to.
     */
    public Library(){}

    /**
     * Library(Observable List) of recipes or sheets that are being listened to.
     * @param header Library name
     */
    public Library(String header){
        this.header = header;
        listener(library);
    }

    public void setHeader(String header){this.header = header;}
    public String getHeader(){return header;}

    @Override
    public void listener(ObservableList<ObList> ob){
        ob.addListener((ListChangeListener<ObList>) change ->{
            save();
        });
    }

    @Override
    public void save(){
        ObjectMapper mapper = new ObjectMapper();
    }

    @Override
    public void addMember(String title) {
        this.getItems().add(new Sheet(title.trim()));
    }

    @Override
    public void removeMember(Library member) {
        if (member == null){
            return;
        }
        this.getItems().remove(member);
    }

    /**
     * Sets specified list's boolean variable recipe as true.
     */
    public void setAsRecipe(){
        this.recipe = true;
    }
}
