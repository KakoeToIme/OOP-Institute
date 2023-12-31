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
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RedactorItemDb {
	
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
	 
	static String url = "jdbc:mysql://127.0.0.1:3306/warehouse";
	static String user = "root";
	static String password = "HybridsN11";
	
	public static List<Bd> fetchIdDataFromDatabase(TextField idText) {
    	List<Bd> dataFromDatabase = new ArrayList<>();
        try {
        	Connection connection = DriverManager.getConnection(url, user, password);

            String idValue = idText.getText();

            if (!idValue.isEmpty()) {
                String query = "SELECT * FROM items WHERE ID=?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setInt(1, Integer.parseInt(idValue));

                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
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
	
	public static List<Bd> fetchNameDataFromDatabase(TextField nameText) {
    	List<Bd> dataFromDatabase = new ArrayList<>();
        try {
        	Connection connection = DriverManager.getConnection(url, user, password);

            String nameValue = nameText.getText();

            if (!nameValue.isEmpty()) {
                String query = "SELECT * FROM items WHERE name=?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, nameValue);

                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
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
	
	public void showWindow(Stage primaryStage, Stage PrimaryStage, Label lbl) {
		
		Stage stg = new Stage();
		
		Label idLabel = new Label("Введите Id");
		Label andLabel = new Label("или"); 
		Label nameLabel = new Label("Введите Name");
		
		TextField idText = new TextField();
		TextField nameText = new TextField();
		
		TableView<Bd> tableView = new TableView<Bd>();
		
		Button RedactButton = new Button("Изменить данный товар");
		
		BooleanBinding isButtonDisabled = Bindings.createBooleanBinding(() ->
        idText.getText().isEmpty() && nameText.getText().isEmpty(), idText.textProperty(), nameText.textProperty());
		
		RedactButton.disableProperty().bind(isButtonDisabled);
		
		RedactButton.setOnAction(event -> {
			stg.hide();
			
			Stage Stg = new Stage();
			
			Label nameRow = new Label("Name");
			Label cpucoreRow = new Label("Cpu core");
			Label cputhreadsRow = new Label("Cpu threads");
			Label memorytypeRow = new Label("Memory type");
			Label clockspeedRow = new Label("Clock speed");
			Label cashesizeRow = new Label("Cashe size");
			Label tdpRow = new Label("Tdp");
			Label amountRow = new Label("Amount");
			Label priceRow = new Label("Price");

			TextField name = new TextField();
			TextField cpucore = new TextField();
			TextField cputhreads = new TextField();
			TextField memorytype = new TextField();
			TextField clockspeed = new TextField();
			TextField cashesize = new TextField();
			TextField tdp = new TextField();
			TextField amount = new TextField();
			TextField price = new TextField();
			
			Button Redact = new Button("Внести изменения");
			
			Redact.setOnAction(actionEvent -> {
				
				 try {
				        Connection connection = DriverManager.getConnection(url, user, password);

				        StringBuilder queryBuilder = new StringBuilder("UPDATE items SET ");

				        List<String> updateFields = new ArrayList<>();

				        if (!name.getText().isEmpty()) {
				            updateFields.add("name=?");
				        }
				        if (!cpucore.getText().isEmpty()) {
				            updateFields.add("cpucore=?");
				        }
				        if (!cputhreads.getText().isEmpty()) {
				            updateFields.add("cputhreads=?");
				        }
				        if (!memorytype.getText().isEmpty()) {
				            updateFields.add("memorytype=?");
				        }
				        if (!clockspeed.getText().isEmpty()) {
				            updateFields.add("clockspeed=?");
				        }
				        if (!cashesize.getText().isEmpty()) {
				            updateFields.add("cashesize=?");
				        }
				        if (!tdp.getText().isEmpty()) {
				            updateFields.add("tdp=?");
				        }
				        if (!amount.getText().isEmpty()) {
				            updateFields.add("amount=?");
				        }
				        if (!price.getText().isEmpty()) {
				            updateFields.add("price=?");
				        }

				        if (!updateFields.isEmpty()) {
				            queryBuilder.append(String.join(", ", updateFields));
				            queryBuilder.append(" WHERE id=?");

				            try (PreparedStatement preparedStatement = connection.prepareStatement(queryBuilder.toString())) {
				                int parameterIndex = 1;

				                if (!name.getText().isEmpty()) {
				                    preparedStatement.setString(parameterIndex++, name.getText());
				                }
				                if (!cpucore.getText().isEmpty()) {
				                    preparedStatement.setInt(parameterIndex++, Integer.parseInt(cpucore.getText()));
				                }
				                if (!cputhreads.getText().isEmpty()) {
				                    preparedStatement.setInt(parameterIndex++, Integer.parseInt(cputhreads.getText()));
				                }
				                if (!memorytype.getText().isEmpty()) {
				                    preparedStatement.setString(parameterIndex++, memorytype.getText());
				                }
				                if(!clockspeed.getText().isEmpty()) {
				                	preparedStatement.setDouble(parameterIndex++, Double.parseDouble(clockspeed.getText()));
				                }
				                if(!cashesize.getText().isEmpty()) {
				                	preparedStatement.setDouble(parameterIndex++, Double.parseDouble(cashesize.getText()));
				                }
				                if (!tdp.getText().isEmpty()) {
				                    preparedStatement.setInt(parameterIndex++, Integer.parseInt(tdp.getText()));
				                }
				                if (!amount.getText().isEmpty()) {
				                    preparedStatement.setInt(parameterIndex++, Integer.parseInt(amount.getText()));
				                }
				                if(!price.getText().isEmpty()) {
				                	preparedStatement.setDouble(parameterIndex++, Double.parseDouble(price.getText()));
				                }
				                
				                preparedStatement.setInt(parameterIndex, id); 

				                int rowsAffected = preparedStatement.executeUpdate();
				                if (rowsAffected > 0) {
				                    System.out.println("Запись успешно обновлена");
				                    lbl.setText("Запись успешно обновлена");
				                } else {
				                    System.out.println("Запись не обновлена");
				                }
				            }
				        } else {
				            System.out.println("Не указаны поля для обновления");
				        }

				        connection.close();
				    } catch (SQLException | NumberFormatException e) {
				        System.out.println("Ошибка при выполнении SQL-запроса:");
				        e.printStackTrace();
				    }
			});
			
			if(!idText.getText().isEmpty()) {
				
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

			ObservableList<Bd> data = FXCollections.observableArrayList(RedactorItemDb.fetchIdDataFromDatabase(idText));
			tableView.setItems(data);
			
			}
			else if (!nameText.getText().isEmpty()) {
				
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

				ObservableList<Bd> data = FXCollections.observableArrayList(RedactorItemDb.fetchNameDataFromDatabase(nameText));

				tableView.setItems(data);
			}
			
			GridPane root = new GridPane();
			root.setHgap(8);
			root.setVgap(8);
			root.setPadding(new Insets(5));
			root.add(tableView, 0, 0, 2, 1);
			root.addRow(1, nameRow, name);
			root.addRow(2, cpucoreRow, cpucore);
			root.addRow(3, cputhreadsRow, cputhreads);
			root.addRow(4, memorytypeRow, memorytype);
			root.addRow(5, clockspeedRow, clockspeed);
			root.addRow(6, cashesizeRow, cashesize);
			root.addRow(7, tdpRow, tdp);
			root.addRow(8, amountRow, amount);
			root.addRow(9, priceRow, price);
			root.add(Redact, 0, 10, 2, 1);
			root.add(lbl, 0, 11, 2, 1);
			
			Scene scn = new Scene(root, 800, 620);
			Stg.setScene(scn);
			Stg.setTitle("Выбор параметров для изменения");
			Stg.initOwner(PrimaryStage);
	        Stg.initModality(Modality.WINDOW_MODAL);
	        Stg.setResizable(false);
			Platform.runLater(() -> {
	        Stg.showAndWait();
			});
		});
		
		GridPane root = new GridPane();
		root.setHgap(8);
		root.setVgap(8);
		root.setPadding(new Insets(5));
		root.addRow(0, idLabel, idText);
		root.addRow(1, andLabel);
		root.addRow(2, nameLabel, nameText);
		root.add(RedactButton, 0, 3, 2, 1);
		
		Scene scn = new Scene(root, 300, 150);
		stg.setScene(scn);
		stg.setTitle("Изменение информации о товаре");
		stg.initOwner(primaryStage);
        stg.initModality(Modality.WINDOW_MODAL);
        stg.setResizable(false);
        Platform.runLater(() -> {
        stg.showAndWait();
        });
	}
}
