package macdao;

import java.text.DecimalFormat;
import java.util.HashMap;

public class DaoCart {


    public Cart addCart(HashMap<Product,Integer> newProducts, Cart cart){
        Cart newCart = new Cart();

        for(Product product:newProducts.keySet()){
            Integer productInitNum;
            Integer productAddNum = newProducts.get(product);

            if (null == cart.getCartInfo().get(product) )
                productInitNum = 0;
            else
                productInitNum = cart.getCartInfo().get(product);

            if(cart.getCartInfo().keySet().contains(product)) {
                newCart.cartInfo.put(product, productInitNum + productAddNum);
            }
            else{
                newCart.getCartInfo().put(product,productAddNum);
            }
        }

        return newCart;
    }


    public String calcPrice(Cart cart,HashMap<Integer,Integer> casher){
        double price = 0;
        for(Product product: cart.getCartInfo().keySet()){
            int productNum = cart.getCartInfo().get(product);
            if (casher.keySet().contains(product.getId())){
                int privilege = casher.get(product.getId());
                price += getPrice(product.getPrice(), productNum, privilege);
            }
            else {
                price += product.getPrice() * productNum;
            }
        }
        return new DecimalFormat("#.00").format(price);
    }

    public double getPrice(double productPrice, int productNum, int privilege) {
        double price = 0;
        if (privilege == 1 | privilege == 3){
            //买二送一或两种优惠都有
            if (productNum < 3) {
                price += productNum * productPrice;
            }
            //95折
            else {
                price += productPrice * (2 * (productNum / 3) + productNum % 3);
            }
        }
        else {
            price += 0.95 * productPrice * productNum ;
        }
        return price;
    }


}
