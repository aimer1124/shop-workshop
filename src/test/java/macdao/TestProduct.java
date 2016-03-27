package macdao;

import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

import static org.hamcrest.core.Is.is;


public class TestProduct {

    @Test
    public void testSetDBURL(){
        DaoProduct daoP = new DaoProduct();
        try {
            Assert.assertThat(daoP.setDBUrl(), is("jdbc:mysql://localhost/test?name=root&password=&useUnicode=true&characterEncoding=UTF8"));
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Test
    public void testGetProductDetail(){
        DaoProduct daoP = new DaoProduct();
        try {
            Assert.assertThat(daoP.selectProductInfoByID(2).getName(),is("orange"));
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}