package com.du.bagunee0917.service;

import com.du.bagunee0917.domain.CartItem;
import com.du.bagunee0917.domain.Product;

import java.util.List;

public interface ShopService {
    List<Product> getProducts();
    List<CartItem> getCartItems();
    void addToCart(Long ProductId, int quantity);
    void removeFromCart(Long cartItemId);
}
