package com.example.pharmeasy.ui.cart;

public class Cart {
    public String cartId,price,productName,description,quantity;
    public String productImage;


    public Cart(){

    }

    public Cart(String price, String productName, String product_image, String desc, String cartId, String quantity) {
        this.price = price;
        this.productName = productName;
        this.productImage = product_image;
        this.description=desc;
        this.cartId=cartId;
        this.quantity=quantity;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
