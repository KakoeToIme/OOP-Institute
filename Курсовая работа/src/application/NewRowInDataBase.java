package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class NewRowInDataBase {

	String url = "jdbc:mysql://127.0.0.1:3306/warehouse";
	String user = "root";
	String password = "HybridsN11";

	public void showWindow(Stage primaryStage, Label lbl) {
		Stage stg = new Stage();

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

		Button AddButton = new Button("Добавить");
		AddButton.setOnAction(event -> {
			try {
				String Name = name.getText();
				int CpuCore = Integer.parseInt(cpucore.getText());
				int CpuThreads = Integer.parseInt(cputhreads.getText());
				String MemoryType = memorytype.getText();
				double ClockSpeed = Double.parseDouble(clockspeed.getText());
				double CasheSize = Double.parseDouble(cashesize.getText());
				int Tdp = Integer.parseInt(tdp.getText());
				int Amount = Integer.parseInt(amount.getText());
				double Price = Double.parseDouble(price.getText());

				Connection connection = DriverManager.getConnection(url, user, password);
				String query = "INSERT INTO items (name, cpucore, cputhreads, memorytype, clockspeed, cashesize, tdp, amount, price) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
				try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
					preparedStatement.setString(1, Name);
					preparedStatement.setInt(2, CpuCore);
					preparedStatement.setInt(3, CpuThreads);
					preparedStatement.setString(4, MemoryType);
					preparedStatement.setDouble(5, ClockSpeed);
					preparedStatement.setDouble(6, CasheSize);
					preparedStatement.setInt(7, Tdp);
					preparedStatement.setInt(8, Amount);
					preparedStatement.setDouble(9, Price);

					int rowsInserted = preparedStatement.executeUpdate();
					if (rowsInserted > 0) {
						name.clear();
						cpucore.clear();
						cputhreads.clear();
						memorytype.clear();
						clockspeed.clear();
						cashesize.clear();
						tdp.clear();
						amount.clear();
						price.clear();

						lbl.setText("Запись успешно добавлена!");
					}
				} catch (SQLException | NumberFormatException e) {
					e.printStackTrace();
					lbl.setText("Ошибка при добавлении записи. Проверьте введенные значения.");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				lbl.setText("Ошибка при подключении к базе данных.");
			}
		});

		GridPane root = new GridPane();
		root.setHgap(8);
		root.setVgap(8);
		root.setPadding(new Insets(5));
		root.addRow(0, nameRow, name);
		root.addRow(1, cpucoreRow, cpucore);
		root.addRow(2, cputhreadsRow, cputhreads);
		root.addRow(3, memorytypeRow, memorytype);
		root.addRow(4, clockspeedRow, clockspeed);
		root.addRow(5, cashesizeRow, cashesize);
		root.addRow(6, tdpRow, tdp);
		root.addRow(7, amountRow, amount);
		root.addRow(8, priceRow, price);
		root.add(AddButton, 0, 9, 2, 1);
		root.add(lbl, 0, 10, 2, 1);

		Scene scn = new Scene(root, 370, 400);
		stg.setScene(scn);
		stg.setTitle("Добавление нового предмета в базу данных");
		stg.initOwner(primaryStage);
		stg.initModality(Modality.WINDOW_MODAL);
		stg.setResizable(true);
		stg.showAndWait();
	}
}
