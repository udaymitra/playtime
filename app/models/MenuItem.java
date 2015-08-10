package models;

/**
 * Created by soumya on 8/9/15.
 */
public class MenuItem {
    public Long itemId;
    public String name;
    public String description;
    public double price;

    public MenuItem(Long itemId, String name, String description, double price) {
        this.itemId = itemId;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    // empty constructor for Jackson
    public MenuItem() {
    }
}
