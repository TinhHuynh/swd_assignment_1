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
public class Product {

    private int id;
    private String name;
    private String unit;
    private float unitPrice;
    private float unitsInStock;
    private Category category;
    private String origin;
    private String description;
    private boolean available;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public float getUnitsInStock() {
        return unitsInStock;
    }

    public void setUnitsInStock(float unitsInStock) {
        this.unitsInStock = unitsInStock;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
    
    public static class Builder {

        private int id;
        private String name;
        private String unit;
        private float unitPrice;
        private float unitsInStock;
        private Category category;
        private String origin;
        private String description;
        private boolean available;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder unit(String unit) {
            this.unit = unit;
            return this;

        }

        public Builder unitPrice(float unitPrice) {
            this.unitPrice = unitPrice;
            return this;

        }

        public Builder unitsInStock(float unitsInStock) {
            this.unitsInStock = unitsInStock;
            return this;

        }

        public Builder category(Category category) {
            this.category = category;
            return this;
        }

        public Builder origin(String origin) {
            this.origin = origin;
            return this;

        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }
        
        public Builder available(boolean available) {
            this.available = available;
            return this;
        }
        
        public Product build(){
            Product product = new Product();
            product.setId(this.id);
            product.setName(this.name);
            product.setUnit(this.unit);
            product.setUnitPrice(this.unitPrice);
            product.setUnitsInStock(this.unitsInStock);
            product.setCategory(this.category);
            product.setOrigin(this.origin);
            product.setDescription(this.description);
            product.setAvailable(this.available);
            return product;
        }

    }
}
