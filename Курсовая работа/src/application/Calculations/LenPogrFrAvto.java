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

public class LenPogrFrAvto implements Calculator {
	@Override
	public void showWindow(Stage PrimaryStage, Label lbl) {
		Stage stg = new Stage();

		Label naLabel = new Label("Количество автомашин, отправляемых и поступающих в час: ");
		Label knerLabel = new Label("Коэффициент вариации грузопотока: ");
		Label tLabel = new Label("Время нахождения транспорта под погрузкой/разгрузкой (ч): ");
		TextField Na = new TextField();
		TextField Kner = new TextField();
		TextField T = new TextField();
		Button calculateButton = new Button("Рассчитать");
		calculateButton.setOnAction(event -> {
			calculateAndSetLabel(lbl, Na.getText(), Kner.getText(), T.getText());
		});

		GridPane root = new GridPane();
		root.setHgap(8);
		root.setVgap(8);
		root.setPadding(new Insets(5));
		root.addRow(0, naLabel, Na);
		root.addRow(1, knerLabel, Kner);
		root.addRow(2, tLabel, T);
		root.add(calculateButton, 0, 3, 2, 1);
		root.add(lbl, 0, 4, 2, 1);

		Scene scn = new Scene(root, 550, 160);
		stg.setScene(scn);
		stg.setTitle("Длина погрузочно-разгрузочного фронта автомобильной платформы");
		stg.initOwner(PrimaryStage);
		stg.initModality(Modality.WINDOW_MODAL);
		stg.setResizable(false);
		stg.showAndWait();
	}

	public void calculateAndSetLabel(Label lbl, String na, String kner, String t) {
		try {
			Double Na = Double.parseDouble(na);
			Double Kner = Double.parseDouble(kner);
			Double T = Double.parseDouble(t);
			Double L  = Na * Kner * T * (double) 4.5;
			String l = String.valueOf(L);
			lbl.setText("Длина погрузочно-разгрузочного фронта автомобильной платформы: " + l + " м");
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}
}
