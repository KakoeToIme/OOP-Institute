package application.Calculations;

import application.Calculator;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class UdMarzgDoh implements Calculator {

	@Override
	public void showWindow(Stage primaryStage, Label lbl) {
		Stage stg = new Stage();

		Label pLabel = new Label("Цена единицы продукции: ");
		Label vLabel = new Label("Переменные затраты на единицу продукции: ");
		TextField P = new TextField();
		TextField V = new TextField();
		Button calculateButton = new Button("Рассчитать");
		calculateButton.setOnAction(event -> {
			calculateAndSetLabel(lbl, P.getText(), V.getText());
		});

		GridPane root = new GridPane();
		root.setHgap(8);
		root.setVgap(8);
		root.setPadding(new Insets(5));
		root.addRow(0, pLabel, P);
		root.addRow(1, vLabel, V);
		root.add(calculateButton, 0, 2, 2, 1);
		root.add(lbl, 0, 3, 2, 1);

		Scene scn = new Scene(root, 450, 140);
		stg.setScene(scn);
		stg.setTitle("Удельный маржинальный доход");
		stg.initOwner(primaryStage);
		stg.initModality(Modality.WINDOW_MODAL);
		stg.setResizable(false);
		stg.showAndWait();
	}

	private void calculateAndSetLabel(Label lbl, String p, String v) {
		try {
			Double P = Double.parseDouble(p);
			Double V = Double.parseDouble(v);
			Double M = P - V;
			String m = String.valueOf(M);
			lbl.setText("Удельный маржинальный доход: " + m);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}
}
