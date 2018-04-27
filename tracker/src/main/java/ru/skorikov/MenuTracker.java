package ru.skorikov;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Alex Skorikov.
 * @date: 26.04.18
 */
public class MenuTracker {
    /**
     * Interface Input.
     */
    private Input input;
    /**
     * New instance of the class Tracker.
     */
    private Tracker tracker;

    /**
     * New instance of the class UserAction.
     */
    private ArrayList<UserAction> userActions = new ArrayList<>();

    /**
     * Create range for StartUI.
     *
     * @return array int[].
     */
    public int[] getRangeOfUserActions() {
        int[] range = new int[userActions.size()];
        for (int i = 0; i < range.length; i++) {
            range[i] = i;
        }
        return range;
    }

    /**
     * Constructor.
     *
     * @param atInput   Input
     * @param atTracker Tracker.
     */
    MenuTracker(Input atInput, Tracker atTracker) {
        this.input = atInput;
        this.tracker = atTracker;
    }

    /**
     * Menu from array of innernal classes.
     */
    public void fillActions() {
        userActions.add(new AddItem("Add Item", 0));
        userActions.add(new ShowAllItems("Show All Items", 1));
        userActions.add(new EditItem("Edit Item", 2));
        userActions.add(new DeleteItem("Delete Item", 3));
        userActions.add(new FindById("Find By Id", 4));
        userActions.add(new FindByName("Find By Name", 5));

    }

    /**
     * Select element menu.
     *
     * @param key key.
     */
    public void select(int key) {
        this.userActions.get(key).execute(this.input, this.tracker);
    }

    /**
     * Show menu.
     */
    public void show() {
        for (UserAction action : this.userActions) {
            System.out.println(action.info());
        }
    }

    /**
     * 0. Inner class Add Item.
     */
    private class AddItem extends BaseAction {
        /**
         * Constructor.
         *
         * @param name name.
         * @param key  key.
         */
        AddItem(String name, int key) {
            super(name, key);
        }

        /**
         * Add Item to DB.
         *
         * @param atInput   input.
         * @param atTracker tracker.
         */
        public void execute(Input atInput, Tracker atTracker) {
            String name = input.ask("Enter Item name:");
            String description = input.ask("Enter Item description:");
            Item item = new Item(name, description);
            tracker.add(item);
        }

        /**
         * 1st menu item on screen.
         *
         * @return string.
         */
        public String info() {
            return String.format("%s.\t %s\t", this.key(), this.name());
        }
    }

    /**
     * 1. Inner class show all items.
     */
    private class ShowAllItems extends BaseAction {
        /**
         * Constructor.
         *
         * @param name name.
         * @param key  key.
         */
        ShowAllItems(String name, int key) {
            super(name, key);
        }

        /**
         * Show all items.
         *
         * @param atInput   input.
         * @param atTracker tracker.
         */
        public void execute(Input atInput, Tracker atTracker) {
            tracker.findAll();
        }

        /**
         * 2nd menu item on screen.
         *
         * @return string
         */
        public String info() {
            return String.format("%s.\t %s\t", this.key(), this.name());
        }
    }

    /**
     * 2. Inner class edit Item
     */
    private class EditItem extends BaseAction {
        /**
         * Constructor.
         *
         * @param name name.
         * @param key  key.
         */
        EditItem(String name, int key) {
            super(name, key);
        }

        /**
         * Edit Item.
         *
         * @param atInput   input.
         * @param atTracker tracker.
         */
        public void execute(Input atInput, Tracker atTracker) {
            Integer idItem = null;
            boolean validateID = false;
            while (!validateID) {
                try {
                    idItem = Integer.valueOf(input.ask("Enter ID item:"));
                    validateID = true;
                } catch (Exception e) {
                    System.out.println("Enter the Number.");
                }
            }
            String newName = input.ask("Enter new name:");
            String newDescription = input.ask("Enter new description");
            Item editItem = new Item(idItem, newName, newDescription);
            tracker.update(editItem);
        }

        /**
         * 3rd menu item on screen.
         *
         * @return string.
         */
        public String info() {
            return String.format("%s.\t %s\t", this.key(), this.name());
        }
    }


    /**
     * 3. Inner class delete item.
     */
    private class DeleteItem extends BaseAction {
        /**
         * Constructor.
         *
         * @param name name.
         * @param key  key.
         */
        DeleteItem(String name, int key) {
            super(name, key);
        }

        /**
         * Delete Item.
         *
         * @param atInput   input
         * @param atTracker tracker
         */
        public void execute(Input atInput, Tracker atTracker) {
            Integer findIdItem = Integer.valueOf(input.ask("Enter ID item:"));
            Item item = new Item(findIdItem, "", "");
            tracker.delete(item);

        }

        /**
         * 4th menu element on screen.
         *
         * @return string.
         */
        public String info() {
            return String.format("%s.\t %s\t", this.key(), this.name());
        }
    }

    /**
     * 4. Innser class Search item by ID.
     */
    private class FindById extends BaseAction {
        /**
         * Constructor.
         *
         * @param name name.
         * @param key  key.
         */
        FindById(String name, int key) {
            super(name, key);
        }

        /**
         * Search item by ID.
         *
         * @param atInput   ввод
         * @param atTracker трекер
         */
        public void execute(Input atInput, Tracker atTracker) {
            Integer findItemById = Integer.valueOf(input.ask("Enter ID item:"));
            tracker.findeById(findItemById);
        }

        /**
         * 5th menu element on screen.
         *
         * @return string.
         */
        public String info() {
            return String.format("%s.\t %s\t", this.key(), this.name());
        }
    }

    /**
     * 5. Inner class Search item by name.
     */
    private class FindByName extends BaseAction {
        /**
         * Constructor.
         *
         * @param name name.
         * @param key  key.
         */
        FindByName(String name, int key) {
            super(name, key);
        }

        /**
         * Search item by name.
         *
         * @param atInput   input.
         * @param atTracker tracker.
         */
        public void execute(Input atInput, Tracker atTracker) {
            String findItemByName = input.ask("Enter Name item:");

            tracker.findeByName(findItemByName);
        }

        /**
         * 6x menu element on screen.
         *
         * @return string.
         */
        public String info() {
            return String.format("%s.\t %s\t", this.key(), this.name());
        }
    }

}
