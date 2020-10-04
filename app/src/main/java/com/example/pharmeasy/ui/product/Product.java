package com.example.pharmeasy.ui.product;

public class Product {
    public String category,price,productName,description;
    public String productImage;

    public Product(){

    }

    public Product(String category, String price, String productName, String product_image, String description) {
        this.category = category;
        this.price = price;
        this.productName = productName;
        this.productImage = product_image;
        this.description= description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String product_image) {
        this.productImage = product_image;
    }
}
