package fi.jyu.ohj2.kahepiip.listsapp.model;

/**
 * An item which can be added/saved to a sheet.
 * Item's name and completion status is being listened to.
 */
public class SheetItem extends ListItem {
    /**
     * An item on a sheet.
     */
    public SheetItem(){}

    /**
     * An item on a sheet
     * @param itemName Name of this item
     */
    public SheetItem(String itemName){
        super(itemName);
    }
}
