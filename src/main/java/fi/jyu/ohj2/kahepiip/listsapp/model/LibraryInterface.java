package fi.jyu.ohj2.kahepiip.listsapp.model;

import javafx.collections.ObservableList;

/**
 * Basic operations for collections and members of collections
 */
public interface LibraryInterface {

    void addMember(String title);

    void removeMember(Library member);

    void save();

    void listener(ObservableList<ObList> ob);
}
