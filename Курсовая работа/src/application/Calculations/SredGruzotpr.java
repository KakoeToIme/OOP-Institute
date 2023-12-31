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

public class SredGruzotpr implements Calculator {
	@Override
	public void showWindow(Stage PrimaryStage, Label lbl) {
		Stage stg = new Stage();

		Label qogodLabel = new Label("Годовой грузопоток склада по отправке грузов (т/год): ");
		Label kneroLabel = new Label("Коэффициент неравномерности по отправке грузов (1,1 - 1,2): ");
		Label toLabel = new Label("Число дней работы склада на отправку грузов: ");
		TextField qogod = new TextField();
		TextField knero = new TextField();
		TextField to = new TextField();
		Button calculateButton = new Button("Рассчитать");
		calculateButton.setOnAction(event -> {
			calculateAndSetLabel(lbl, qogod.getText(), knero.getText(), to.getText());
		});

		GridPane root = new GridPane();
		root.setHgap(8);
		root.setVgap(8);
		root.setPadding(new Insets(5));
		root.addRow(0, qogodLabel, qogod);
		root.addRow(1, kneroLabel, knero);
		root.addRow(2, toLabel, to);
		root.add(calculateButton, 0, 3, 2, 1);
		root.add(lbl, 0, 4, 2, 1);

		Scene scn = new Scene(root, 550, 170);
		stg.setScene(scn);
		stg.setTitle("Среднесуточный грузопоток по отправлению");
		stg.initOwner(PrimaryStage);
		stg.initModality(Modality.WINDOW_MODAL);
		stg.setResizable(false);
		stg.showAndWait();
	}

	public void calculateAndSetLabel(Label lbl, String qogod, String knero, String to) {
		try {
			Double Qogod = Double.parseDouble(qogod);
			Double Knero = Double.parseDouble(knero);
			Double To = Double.parseDouble(to);
			Double Qosyt = (Qogod * Knero) / To;
			String qosyt = String.valueOf(Qosyt);
			lbl.setText("Среднесуточный грузопоток по отправлению: " + qosyt + " т/сут");
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}
}
