package macdao;


import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TestCart {


    Product productOne = new Product();
    Product productTwo = new Product();
    Product productThree = new Product();
    DaoCart daoCart = new DaoCart();
    HashMap<Product,Integer> addProducts = new HashMap<>();
    Cart cart = new Cart();
    HashMap<Integer,Integer> casher = new HashMap<>();

    @Before
    public void initData(){

        productOne.setId(1);
        productOne.setName("CocoCola");
        productOne.setCategory("Drink");
        productOne.setPrice(3.00);
        productOne.setCode(80003);


        productTwo.setId(2);
        productTwo.setName("Badminton");
        productTwo.setCategory("Sport");
        productTwo.setPrice(1.00);
        productTwo.setCode(80004);

        productThree.setId(3);
        productThree.setName("apple");
        productThree.setCategory("Fruit");
        productThree.setPrice(5.50);
        productThree.setCode(80005);

    }

    @Test
    public void testAddCart(){

        addProducts.put(productOne,5);
        addProducts.put(productTwo,10);

        Cart newEmptyCart = daoCart.addCart(addProducts, cart);

        //添加两类商品至空购买车
        assertThat(newEmptyCart.getCartInfo().get(productOne),is(5));
        assertThat(newEmptyCart.getCartInfo().get(productTwo),is(10));
        assertThat(newEmptyCart.getCartInfo().size(),is(2));

        addProducts.put(productThree,5);
        Cart newNonEmptyCart = daoCart.addCart(addProducts, newEmptyCart);

        //添加一类商品至非空购买车
        assertThat(newNonEmptyCart.getCartInfo().get(productOne),is(10));
        assertThat(newNonEmptyCart.getCartInfo().get(productTwo),is(20));
        assertThat(newNonEmptyCart.getCartInfo().get(productThree),is(5));
        assertThat(newNonEmptyCart.getCartInfo().size(),is(3));

    }

    @Test
    public void testCalcPrice(){

        //购买一类商品,无优惠
        addProducts.put(productOne,2);
        assertThat(daoCart.calcPrice(daoCart.addCart(addProducts,cart),casher),is("6.00"));

        //购买一类商品,有优惠:买二送人,但数量不够
        addProducts.put(productOne,2);
        casher.put(1,1);
        assertThat(daoCart.calcPrice(daoCart.addCart(addProducts,cart),casher),is("6.00"));

        //购买一类商品,有优惠:买二送人,且数量够
        addProducts.put(productOne,3);
        casher.put(1,1);
        assertThat(daoCart.calcPrice(daoCart.addCart(addProducts,cart),casher),is("6.00"));

        //购买一类商品,有优惠:买二送人,且数量远超
        addProducts.put(productOne,7);
        casher.put(1,1);
        assertThat(daoCart.calcPrice(daoCart.addCart(addProducts,cart),casher),is("15.00"));


        //购买两类商品,无优惠
        addProducts.put(productOne,1);
        addProducts.put(productTwo,3);
        assertThat(daoCart.calcPrice(daoCart.addCart(addProducts,cart),casher),is("6.00"));

        //购买两类商品,分别有优惠 productOne:买二送一,productTwo:95折
        addProducts.put(productOne,4);
        addProducts.put(productTwo,3);
        casher.put(1,1);
        casher.put(2,2);
        assertThat(daoCart.calcPrice(daoCart.addCart(addProducts,cart),casher),is("11.85"));



        //购买三类商品,有优惠:买二送一  case1
        addProducts.put(productOne,3);
        addProducts.put(productTwo,5);
        addProducts.put(productThree,2);
        //清除优惠活动
        casher.clear();
        casher.put(1,1);
        casher.put(2,1);
        assertThat(daoCart.calcPrice(daoCart.addCart(addProducts,cart),casher),is("21.00"));

        //购买三类商品,无优惠 case2
        addProducts.put(productOne,3);
        addProducts.put(productTwo,5);
        addProducts.put(productThree,2);
        //清除优惠活动
        casher.clear();
        assertThat(daoCart.calcPrice(daoCart.addCart(addProducts,cart),casher),is("25.00"));


        //购买三类商品,有优惠:95折 case3
        addProducts.put(productOne,3);
        addProducts.put(productTwo,5);
        addProducts.put(productThree,2);
        //清除优惠活动
        casher.clear();
        casher.put(3,2);
        assertThat(daoCart.calcPrice(daoCart.addCart(addProducts,cart),casher),is("24.45"));


        //购买三类商品,有优惠:95折 case4
        addProducts.put(productOne,3);
        addProducts.put(productTwo,5);
        addProducts.put(productThree,2);
        //清除优惠活动
        casher.clear();
        casher.put(1,1);
        casher.put(2,3);
        casher.put(3,2);
        assertThat(daoCart.calcPrice(daoCart.addCart(addProducts,cart),casher),is("20.45"));


    }


}
