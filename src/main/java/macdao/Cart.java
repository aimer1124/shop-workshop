package macdao;

import java.util.HashMap;

public class Cart {

    HashMap<Product,Integer> cartInfo = new HashMap<Product,Integer>();

    public HashMap<Product, Integer> getCartInfo() {
        return cartInfo;
    }

    public void setCartInfo(HashMap<Product, Integer> cartInfo) {
        this.cartInfo = cartInfo;
    }
}
