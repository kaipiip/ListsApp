import fi.jyu.ohj2.kahepiip.listsapp.model.ItemCollection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddAndRemoveItemTest {

    @Test
    void addsItemCorrectly(){
        ItemCollection items = new ItemCollection("test-collection.json");
        items.addItem("egg");
        assertEquals(1, items.getItems().size());
        assertEquals("egg", items.getItems().getFirst().getName());
    }
}
