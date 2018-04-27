package ru.skorikov;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Alex Skorikov.
 * @date: 26.04.18
 */
public class Item {
    /**
     * Item name.
     */
    private String name;
    /**
     * Item description.
     */
    private String description;
    /**
     * Item ID.
     */
    private Integer id;

    /**
     * Constructor.
     *
     * @param name        name.
     * @param description description.
     */
    Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Second constructor.
     *
     * @param id          item ID.
     * @param name        item name.
     * @param description item description.
     */
    Item(Integer id, String name, String description) {
        this.name = name;
        this.description = description;
        this.id = id;
    }

    /**
     * Get Item name.
     *
     * @return имя.
     */
    public String getName() {
        return name;
    }

    /**
     * Set Item name.
     *
     * @param name имя.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get Item description.
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set Item description.
     *
     * @param description description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get Item ID.
     *
     * @return ID.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Set Item ID.
     *
     * @param id ID.
     */
    public void setId(Integer id) {
        this.id = id;
    }
}
