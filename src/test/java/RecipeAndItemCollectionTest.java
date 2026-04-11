import fi.jyu.ohj2.kahepiip.listsapp.model.ItemCollection;
import fi.jyu.ohj2.kahepiip.listsapp.model.ListItem;
import fi.jyu.ohj2.kahepiip.listsapp.model.Recipe;
import fi.jyu.ohj2.kahepiip.listsapp.model.RecipeLibrary;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RecipeAndItemCollectionTest {

    @Test
    void addItemCorrectly(){
        Recipe recipe = new Recipe("test-ingredients.json");
        recipe.addItem("egg");

        ItemCollection items = new ItemCollection("test-collection.json");
        items.addItem("egg");

        assertEquals(1, recipe.getItems().size());
        assertEquals("egg", recipe.getItems().getFirst().getName());

        assertEquals(1, items.getItems().size());
        assertEquals("egg", items.getItems().getFirst().getName());
    }

    @Test
    void removeItemCorrectly(){
        Recipe recipe = new Recipe("test-ingredients.json");
        recipe.addItem("egg");
        ListItem ingredient = recipe.getItems().getFirst();
        recipe.removeItem(ingredient);

        ItemCollection items = new ItemCollection("test-collection.json");
        items.addItem("egg");
        ListItem item = items.getItems().getFirst();
        items.removeItem(item);

        assertEquals(0, recipe.getItems().size());
        assertEquals(0, items.getItems().size());
    }

    @Test
    void addItem_notEmpty(){
        Recipe recipe = new Recipe("test-ingredients.json");
        recipe.addItem("    ");

        ItemCollection items = new ItemCollection("test-collection.json");
        items.addItem("    ");

        assertEquals(0, recipe.getItems().size());
        assertEquals(0, items.getItems().size());
    }

    @Test
    void addItem_Trimmed(){
        Recipe recipe = new Recipe("test-ingredients.json");
        recipe.addItem(" egg  ");

        ItemCollection items = new ItemCollection("test-collection.json");
        items.addItem("  egg  ");

        assertEquals(1, recipe.getItems().size());
        assertEquals("egg", recipe.getItems().getFirst().getName());

        assertEquals(1, items.getItems().size());
        assertEquals("egg", items.getItems().getFirst().getName());
    }

    @Test
    void addItem_Duplicate(){
        Recipe recipe = new Recipe("test-ingredients.json");
        recipe.addItem("egg");
        recipe.addItem("egg");

        ItemCollection items = new ItemCollection("test-collection.json");
        items.addItem("egg");
        items.addItem("egg");

        assertEquals(2, recipe.getItems().size());
        assertEquals("egg", recipe.getItems().getFirst().getName());
        assertEquals("egg", recipe.getItems().get(1).getName());

        assertEquals(2, items.getItems().size());
        assertEquals("egg", items.getItems().getFirst().getName());
        assertEquals("egg", items.getItems().get(1).getName());
    }

    @Test
    void setCompletionCorrectly(){
        Recipe recipe = new Recipe("test-ingredients.json");
        recipe.addItem("egg");
        recipe.getItems().getFirst().setCompletion(true);

        ItemCollection items = new ItemCollection("test-collection.json");
        items.addItem("egg");
        items.getItems().getFirst().setCompletion(true);

        assertTrue(recipe.getItems().getFirst().getCompletion());
        assertTrue(items.getItems().getFirst().getCompletion());
    }

    @Test
    void setDuplicateCompletionCorrectly(){
        ItemCollection items = new ItemCollection("test-collection.json");
        ListItem item1 = new ListItem("egg");
        ListItem item2 = new ListItem("egg");
        items.addItem(item1);
        items.addItem(item2);
        items.getItems().getFirst().setCompletion(true);

        Recipe recipe = new Recipe("test-ingredients.json");
        ListItem item3 = new ListItem("egg");
        ListItem item4 = new ListItem("egg");
        recipe.addItem(item3);
        recipe.addItem(item4);
        recipe.getItems().getFirst().setCompletion(true);

        assertTrue(item1.getCompletion());
        assertFalse(item2.getCompletion());
        assertTrue(item3.getCompletion());
        assertFalse(item4.getCompletion());
    }

    @Test
    void addRecipeToLibrary(){
        RecipeLibrary library = new RecipeLibrary("test-library.json");
        Recipe recipe = new Recipe("test-ingredients.json");
        recipe.setName("Omelet");
        ListItem item = new ListItem("egg", true);
        recipe.addItem(item);
        library.addRecipe(recipe);

        assertEquals(1, library.getRecipes().size());
    }

    @Test
    void removeRecipe(){
        RecipeLibrary library = new RecipeLibrary("test-library.json");
        Recipe recipe = new Recipe("test-ingredients.json");
        recipe.setName("Omelet");
        ListItem item = new ListItem("egg", true);
        recipe.addItem(item);
        library.addRecipe(recipe);

        library.removeRecipe(recipe);
        assertEquals(0, library.getRecipes().size());
    }

    @Test
    void addRecipe_notEmpty(){
        RecipeLibrary library = new RecipeLibrary("test-library.json");
        Recipe recipe = new Recipe("test-ingredients.json");
        recipe.setName("Omelet");
        library.addRecipe(recipe);
        assertEquals(0, library.getRecipes().size());
    }

    @Test
    void addRecipe_notNameless(){
        RecipeLibrary library = new RecipeLibrary("test-library.json");
        Recipe recipe = new Recipe("test-ingredients.json");
        ListItem item = new ListItem("egg", true);
        recipe.addItem(item);

        library.addRecipe(recipe);
        assertEquals(0, library.getRecipes().size());
    }
}
