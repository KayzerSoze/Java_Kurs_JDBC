package ru.skorikov;

import org.junit.Test;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Alex Skorikov.
 * @date: 26.04.18
 */
public class ConnectionTest {

    /**
     * Trying connect and init database.
     */
    @Test
    public void tryConnectAndInitDataBase() {
        File config = new File("src/main/resources/config.xml");
        try (Tracker tracker = new Tracker(config)) {
            tracker.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Trying Add Item.
     */
    @Test
    public void tryingAddItemToDB() {
        File config = new File("src/main/resources/config.xml");
        try (Tracker tracker = new Tracker(config)) {
            Input input = new StubInput(new String[]{"0", "test_name", "test_description", "y"});
            new StartUI(input, tracker).init();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Trying show all Items.
     */
    @Test
    public void tryingshowAllItemsFromDB() {
        File config = new File("src/main/resources/config.xml");
        try (Tracker tracker = new Tracker(config)) {
            Input input = new StubInput(new String[]{"1", "y"});
            new StartUI(input, tracker).init();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Trying show Item by ID.
     */
    @Test
    public void tryingShowItemByIdFromDB() {
        File config = new File("src/main/resources/config.xml");
        try (Tracker tracker = new Tracker(config)) {
            Input input = new StubInput(new String[]{"4", "10", "y"});
            new StartUI(input, tracker).init();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Trying editing Item by ID.
     */
    @Test
    public void tryingEditItem() {
        File config = new File("src/main/resources/config.xml");
        try (Tracker tracker = new Tracker(config)) {
            Input input = new StubInput(new String[]{"2", "10", "test_2", "descript_2", "y"});
            new StartUI(input, tracker).init();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Trying delete Item.
     */
    @Test
    public void tryingDeleteItem() {
        File config = new File("src/main/resources/config.xml");
        try (Tracker tracker = new Tracker(config)) {
            Input input = new StubInput(new String[]{"3", "10", "y"});
            new StartUI(input, tracker).init();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Trying find Item by name.
     */
    @Test
    public void tryingFindItemByName() {
        File config = new File("src/main/resources/config.xml");
        try (Tracker tracker = new Tracker(config)) {
            Input input = new StubInput(new String[]{"5", "10", "y"});
            new StartUI(input, tracker).init();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
