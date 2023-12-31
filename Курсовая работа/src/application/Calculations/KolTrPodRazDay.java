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

public class KolTrPodRazDay implements Calculator {
	@Override
	public void showWindow(Stage PrimaryStage, Label lbl) {
		Stage stg = new Stage();

		Label qLabel = new Label("Годовой грузооборот (т): ");
		Label knerLabel = new Label("Коэффициент вариации грузопотока: ");
		Label dtLabel = new Label("Грузовместимость одного транспортного средства (т): ");
		TextField Q = new TextField();
		TextField Kner = new TextField();
		TextField Dt = new TextField();
		Button calculateButton = new Button("Рассчитать");
		calculateButton.setOnAction(event -> {
			calculateAndSetLabel(lbl, Q.getText(), Kner.getText(), Dt.getText());
		});

		GridPane root = new GridPane();
		root.setHgap(8);
		root.setVgap(8);
		root.setPadding(new Insets(5));
		root.addRow(0, qLabel, Q);
		root.addRow(1, knerLabel, Kner);
		root.addRow(2, dtLabel, Dt);
		root.add(calculateButton, 0, 3, 2, 1);
		root.add(lbl, 0, 4, 2, 1);

		Scene scn = new Scene(root, 500, 160);
		stg.setScene(scn);
		stg.setTitle("Число транспортных средств на разгрузку в сутки");
		stg.initOwner(PrimaryStage);
		stg.initModality(Modality.WINDOW_MODAL);
		stg.setResizable(false);
		stg.showAndWait();
	}

	public void calculateAndSetLabel(Label lbl, String q, String kner, String dt) {
		try {
			Double Q = Double.parseDouble(q);
			Double Kner = Double.parseDouble(kner);
			Double Dt = Double.parseDouble(dt);
			Double Ntr = (Q * Kner) / ((double) 365 * Dt);
			String ntr = String.valueOf(Ntr);
			lbl.setText("Число транспортных средств на разгрузку в сутки: " + ntr);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}
}
