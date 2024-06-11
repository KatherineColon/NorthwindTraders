package com.pluralsight;
import java.sql.*;
import javax.sql.DataSource;
public class App {
    public static void main(String[] args) {
        String url = "jdbc:mysql://127.0.0.1:3306/northwind";
        String user = "root";
        String password = args[0];


        try {
            Connection connection;
            connection = DriverManager.getConnection(url, user, password);

            Statement statement = connection.createStatement();

            String query = "SELECT ProductID, ProductName, UnitPrice, UnitsInStock FROM Products";

            ResultSet results = statement.executeQuery(query);

            System.out.printf("%-4s %-40s %-8s %-6s%n", "Id", "Name", "Price", "Stock");
            System.out.println("---- ---------------------------------------- ------- ------");

            while(results.next()) {
                int id = results.getInt("ProductID");
                String name = results.getString("ProductName");
                double price = results.getDouble("UnitPrice");
                int stock = results.getInt("UnitsInStock");

                System.out.printf("%-4d %-40s %-8.2f %-6d%n", id, name, price, stock);
            }

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
