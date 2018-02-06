/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fruit_shop.model.database.product;

/**
 *
 * @author TINH HUYNH
 */
public enum Category {
    UNKNOWN(0, "Unknown"), BERRIES(1, "Berries"), PITS(2, "Pits"), CORE(3, "Core"), CIRTUS_FRUITS(4, "Citrus Fruits"),
    MELONS(5, "Melons"), TROPICAL(6, "Tropical Fruits");

    private String value;
    private int id;

    Category(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public String value() {
        return value;
    }

    public int id() {
        return id;
    }

    public static Category parseCategory(String value) {
        for (Category category : Category.values()) {
            if (category.value.equals(value)) {
                return category;
            }
        }
        return UNKNOWN;
    }

    public static Category parseCategory(int id) {
        for (Category category : Category.values()) {
            if (category.id == id) {
                return category;
            }
        }
        return UNKNOWN;
    }
}
