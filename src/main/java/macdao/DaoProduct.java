package macdao;

import java.sql.*;


public class DaoProduct{

    public String setDBUrl() throws SQLException{

        try{
            Class.forName("com.mysql.jdbc.Driver") ;

        }catch(ClassNotFoundException e){
            System.out.println("Class driver invoke fail");
            e.printStackTrace() ;
        }

        String dbURL = "jdbc:mysql://localhost/test?name=root&password=&useUnicode=true&characterEncoding=UTF8";

        return dbURL;
    }

    public Product selectProductInfoByID(Integer id) throws SQLException{
        Connection con = null;
        Product product = new Product();
        try {
            con = DriverManager.getConnection(setDBUrl());

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from product where id=" + id);
            while (rs.next()) {
                System.out.println(rs.getInt(1));
                product.setId(rs.getInt(1));
                product.setName(rs.getString(2));
                product.setPrice(rs.getInt(3));
                product.setCategory(rs.getString(4));
                product.setCode(rs.getInt(5));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            con.close();
        }
        return product;
    }



}
