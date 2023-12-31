package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import application.StylishController.BdStat;

public class DataStatsBd {
	String url = "jdbc:mysql://127.0.0.1:3306/warehouse";
	String user = "root";
	String password = "HybridsN11";

	static int id;
	static String product_name;
	static int quantityoff;
	static int quantityon;
	static String date;

	public List<BdStat> fetchDataFromDatabase() {
		List<BdStat> dataFromDatabase = new ArrayList<>();
		try {
			Connection connection = DriverManager.getConnection(url, user, password);
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM stats");

			while (resultSet.next()) {

				id = resultSet.getInt("id");
				product_name = resultSet.getString("product_name");
				quantityoff = resultSet.getInt("quantityoff");
				quantityon = resultSet.getInt("quantityon");
				date = resultSet.getString("date");

				BdStat bd = new BdStat(id, product_name, quantityoff, quantityon, date);
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

	public static List<BdStat> getAllDataFromDatabase() {
		DataStatsBd dbstats = new DataStatsBd();
		return dbstats.fetchDataFromDatabase();
	}
}
