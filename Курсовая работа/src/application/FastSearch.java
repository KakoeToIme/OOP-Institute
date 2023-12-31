package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import application.StylishController.Bd;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FastSearch {
	
    static int ID;
    static String Name;
    static int cpucore;
    static int cputhreads;
    static String memorytype;
    static double clockspeed;
    static double cashesize;
    static int tdp;
    static int amount;
    static double price;
	
	static String url = "jdbc:mysql://127.0.0.1:3306/warehouse";
	static String user = "root";
    static String password = "HybridsN11";
    
	public static void showIdWindow(Stage primaryStage, Label lbl, String id) {
		Stage stg = new Stage();
		
		TableView<Bd> tableView = new TableView<Bd>();
		
		if (!id.isEmpty()) {
		TableColumn<Bd, Integer> idCol = new TableColumn<>("Id");
		idCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
		TableColumn<Bd, String> nameCol = new TableColumn<>("Название");
		nameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
		TableColumn<Bd, Integer> cpucoreCol = new TableColumn<>("Количество ядер");
		cpucoreCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCpucore()).asObject());
		TableColumn<Bd, Integer> cputhreadsCol = new TableColumn<>("Количество потоков");
		cputhreadsCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCputhreads()).asObject());
		TableColumn<Bd, String> memorytypeCol = new TableColumn<>("Тип данных");
		memorytypeCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMemorytype()));
		TableColumn<Bd, Double> clockspeedCol = new TableColumn<>("Тактовая частота");
		clockspeedCol.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getClockspeed()).asObject());
		TableColumn<Bd, Double> cashesizeCol = new TableColumn<>("Обьем кэша");
		cashesizeCol.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getCashesize()).asObject());
		TableColumn<Bd, Integer> tdpCol = new TableColumn<>("Мощность");
		tdpCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getTdp()).asObject());
		TableColumn<Bd, Integer> amountCol = new TableColumn<>("Количество на складе");
		amountCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getAmount()).asObject());
		TableColumn<Bd, Double> priceCol = new TableColumn<>("Цена");
		priceCol.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());

		List<TableColumn<Bd, ?>> columns = Arrays.asList(idCol, nameCol, cpucoreCol, cputhreadsCol, memorytypeCol,
				clockspeedCol, cashesizeCol, tdpCol, amountCol, priceCol);
		
		tableView.getColumns().addAll(columns);

		ObservableList<Bd> data = FXCollections.observableArrayList(FastSearch.fetchIdDataFromDatabase(id));
		tableView.setItems(data);
		}

		Scene scn = new Scene(tableView, 990, 200);
        stg.setScene(scn);
        stg.setTitle("Искомый товар");
        stg.initOwner(primaryStage);
        stg.initModality(Modality.WINDOW_MODAL);
        stg.setResizable(true);
        stg.showAndWait();
	}
	
	public static void showNameWindow(Stage primaryStage, Label lbl, String name) {
		Stage stg = new Stage();
		
		TableView<Bd> tableView = new TableView<Bd>();
		
		if (!name.isEmpty()) {
			
			TableColumn<Bd, Integer> idCol = new TableColumn<>("Id");
			idCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
			TableColumn<Bd, String> nameCol = new TableColumn<>("Название");
			nameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
			TableColumn<Bd, Integer> cpucoreCol = new TableColumn<>("Количество ядер");
			cpucoreCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCpucore()).asObject());
			TableColumn<Bd, Integer> cputhreadsCol = new TableColumn<>("Количество потоков");
			cputhreadsCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCputhreads()).asObject());
			TableColumn<Bd, String> memorytypeCol = new TableColumn<>("Тип данных");
			memorytypeCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMemorytype()));
			TableColumn<Bd, Double> clockspeedCol = new TableColumn<>("Тактовая частота");
			clockspeedCol.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getClockspeed()).asObject());
			TableColumn<Bd, Double> cashesizeCol = new TableColumn<>("Обьем кэша");
			cashesizeCol.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getCashesize()).asObject());
			TableColumn<Bd, Integer> tdpCol = new TableColumn<>("Мощность");
			tdpCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getTdp()).asObject());
			TableColumn<Bd, Integer> amountCol = new TableColumn<>("Количество на складе");
			amountCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getAmount()).asObject());
			TableColumn<Bd, Double> priceCol = new TableColumn<>("Цена");
			priceCol.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());

			List<TableColumn<Bd, ?>> columns = Arrays.asList(idCol, nameCol, cpucoreCol, cputhreadsCol, memorytypeCol,
					clockspeedCol, cashesizeCol, tdpCol, amountCol, priceCol);
			
			tableView.getColumns().addAll(columns);

			ObservableList<Bd> data = FXCollections.observableArrayList(FastSearch.fetchNameDataFromDatabase(name));

			tableView.setItems(data);
		}
		
		Scene scn = new Scene(tableView, 990, 200);
        stg.setScene(scn);
        stg.setTitle("Искомый товар");
        stg.initOwner(primaryStage);
        stg.initModality(Modality.WINDOW_MODAL);
        stg.setResizable(true);
        stg.showAndWait();
	}
	
	public static List<Bd> fetchIdDataFromDatabase(String id) {
    	List<Bd> dataFromDatabase = new ArrayList<>();
        try {
        	Connection connection = DriverManager.getConnection(url, user, password);

            if (!id.isEmpty()) {
                String query = "SELECT * FROM items WHERE ID=?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setInt(1, Integer.parseInt(id));

                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        while (resultSet.next()) {
                            ID = resultSet.getInt("id");
                            Name = resultSet.getString("name");
                            cpucore = resultSet.getInt("cpucore");
                            cputhreads = resultSet.getInt("cputhreads");
                            memorytype = resultSet.getString("memorytype");
                            clockspeed = resultSet.getDouble("clockspeed");
                            cashesize = resultSet.getDouble("cashesize");
                            tdp = resultSet.getInt("tdp");
                            amount = resultSet.getInt("amount");
                            price = resultSet.getDouble("price");

                            Bd bd = new Bd(ID, Name, cpucore, cputhreads, memorytype, clockspeed, cashesize, tdp, amount, price);
                            dataFromDatabase.add(bd);
                        }
                    }
                }
            }

            connection.close();
        } catch (SQLException | NumberFormatException e) {
            System.out.println("Ошибка при выполнении SQL-запроса:");
            e.printStackTrace();
        }
        return dataFromDatabase;
    }
	
	public static List<Bd> fetchNameDataFromDatabase(String name) {
    	List<Bd> dataFromDatabase = new ArrayList<>();
        try {
        	Connection connection = DriverManager.getConnection(url, user, password);

            if (!name.isEmpty()) {
                String query = "SELECT * FROM items WHERE name=?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, name);

                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        while (resultSet.next()) {
                            ID = resultSet.getInt("id");
                            Name = resultSet.getString("name");
                            cpucore = resultSet.getInt("cpucore");
                            cputhreads = resultSet.getInt("cputhreads");
                            memorytype = resultSet.getString("memorytype");
                            clockspeed = resultSet.getDouble("clockspeed");
                            cashesize = resultSet.getDouble("cashesize");
                            tdp = resultSet.getInt("tdp");
                            amount = resultSet.getInt("amount");
                            price = resultSet.getDouble("price");

                            Bd bd = new Bd(ID, Name, cpucore, cputhreads, memorytype, clockspeed, cashesize, tdp, amount, price);
                            dataFromDatabase.add(bd);
                        }
                    }
                }
            }

            connection.close();
        } catch (SQLException | NumberFormatException e) {
            System.out.println("Ошибка при выполнении SQL-запроса:");
            e.printStackTrace();
        }
        return dataFromDatabase;
    }
}
