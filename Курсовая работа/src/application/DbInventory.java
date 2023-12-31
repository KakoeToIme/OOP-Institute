package application;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import application.StylishController.Bd;

public class DbInventory {

    String url = "jdbc:mysql://127.0.0.1:3306/warehouse";
    String user = "root";
    String password = "HybridsN11";

    static int id;
    static String name;
    static int cpucore;
    static int cputhreads;
    static String memorytype;
    static double clockspeed;
    static double cashesize;
    static int tdp;
    static int amount;
    static double price;

    public List<Bd> fetchDataFromDatabase() {
    	List<Bd> dataFromDatabase = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM items");

            while (resultSet.next()) {

                id = resultSet.getInt("id");
                name = resultSet.getString("name");
                cpucore = resultSet.getInt("cpucore");
                cputhreads = resultSet.getInt("cputhreads");
                memorytype = resultSet.getString("memorytype");
                clockspeed = resultSet.getDouble("clockspeed");
                cashesize = resultSet.getDouble("cashesize");
                tdp = resultSet.getInt("tdp");
                amount = resultSet.getInt("amount");
                price = resultSet.getDouble("price");
                
                Bd bd = new Bd(id, name, cpucore, cputhreads, memorytype, clockspeed, cashesize, tdp, amount, price);
                dataFromDatabase.add(bd);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Ошибка при выполнении SQL-запроса:");
            e.printStackTrace();
        }
        return dataFromDatabase;
    }

    public static List<Bd> getAllDataFromDatabase(){
    	DbInventory dbInventory = new DbInventory();
    	return dbInventory.fetchDataFromDatabase();
    }
}
