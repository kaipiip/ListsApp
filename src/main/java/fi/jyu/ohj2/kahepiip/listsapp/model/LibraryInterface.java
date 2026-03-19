package fi.jyu.ohj2.kahepiip.listsapp.model;

import javafx.collections.ObservableList;

/**
 * Basic operations for collections and members of collections
 */
public interface LibraryInterface {

    /**
     * Creates a specified object and adds it to this list.
     * @param title New object's title
     */
    void addMember(String title);

    /**
     * Removes target from specified list.
     * @param member Library object
     */
    void removeMember(Library member);

    /**
     * Saves specified list to .json -file.
     */
    void save();

    /**
     * Creates a Listener for observable list.
     * @param ob Observable List
     */
    void listener(ObservableList<ObList> ob);
}
