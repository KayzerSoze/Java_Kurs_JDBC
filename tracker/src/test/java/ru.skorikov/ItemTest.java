package ru.skorikov;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Alex Skorikov.
 * @date: 26.04.18
 */
public class ItemTest {
    /**
     * Trying create Item.
     */
    @Test
    public void thenCreateItemwhenReturnItem() {
        Integer id = 12;
        String name = "name";
        String descr = "descr";
        Item item = new Item(id, name, descr);
        Item item1 = new Item(name, descr);

        Assert.assertThat(item.getName(), is("name"));
        Assert.assertThat(item1.getDescription(), is("descr"));
    }

    /**
     * Trying to update Item.
     */
    @Test
    public void thenUpdateItemWhenReturnUpdate() {
        Item item = new Item("name", "description");

        item.setName("name_2");
        item.setDescription("new description");

        Assert.assertThat(item.getName(), is("name_2"));
        Assert.assertThat(item.getDescription(), is("new description"));
    }
}