package application;

import java.util.Arrays;
import java.util.List;

import application.StylishController.BdStat;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class StatsPerDay {
	public static void showWindow(Stage primaryStage) {
		Stage stg = new Stage();
		
		TableView<BdStat> tableView = new TableView<BdStat>();
		TableColumn<BdStat, Integer> idCol = new TableColumn<>("Id");
		idCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
		TableColumn<BdStat, String> product_nameCol = new TableColumn<>("Название");
		product_nameCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProductName()));
		TableColumn<BdStat, Integer> quantityoffCol = new TableColumn<>("Списано продукции");
		quantityoffCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getQuantityOff()).asObject());
		TableColumn<BdStat, Integer> quantityonCol = new TableColumn<>("Посутпило продукции");
		quantityonCol.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getQuantityOn()).asObject());
		TableColumn<BdStat, String> dateCol = new TableColumn<>("Дата");
		dateCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDate()));

		List<TableColumn<BdStat, ?>> columns = Arrays.asList(idCol, product_nameCol, quantityoffCol, quantityonCol, dateCol);
		
		tableView.getColumns().addAll(columns);

		ObservableList<BdStat> data = FXCollections.observableArrayList(DataStatsBd.getAllDataFromDatabase());
		tableView.setItems(data);
		
		Scene scn = new Scene(tableView, 990, 600);
        stg.setScene(scn);
        stg.setTitle("База данных склада");
        stg.initOwner(primaryStage);
        stg.initModality(Modality.WINDOW_MODAL);
        stg.setResizable(true);
        stg.showAndWait();
	}
}
