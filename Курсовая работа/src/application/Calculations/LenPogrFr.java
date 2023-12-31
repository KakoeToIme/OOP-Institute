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

public class LenPogrFr implements Calculator {
	@Override
	public void showWindow(Stage PrimaryStage, Label lbl) {
		Stage stg = new Stage();

		Label lLabel = new Label("Длина транспортного средства (м): ");
		Label nLabel = new Label("Количество транспортных средств, одновременно подаваемых под разгрузку: ");
		Label loneLabel = new Label("Длина между одновременно разгружаемыми транспортными средствами (м): ");
		TextField L = new TextField();
		TextField N = new TextField();
		TextField Lone = new TextField();
		Button calculateButton = new Button("Рассчитать");
		calculateButton.setOnAction(event -> {
			calculateAndSetLabel(lbl, L.getText(), N.getText(), Lone.getText());
		});

		GridPane root = new GridPane();
		root.setHgap(8);
		root.setVgap(8);
		root.setPadding(new Insets(5));
		root.addRow(0, lLabel, L);
		root.addRow(1, nLabel, N);
		root.addRow(2, loneLabel, Lone);
		root.add(calculateButton, 0, 3, 2, 1);
		root.add(lbl, 0, 4, 2, 1);

		Scene scn = new Scene(root, 650, 170);
		stg.setScene(scn);
		stg.setTitle("Длина погрузочно-разгрузочного фронта");
		stg.initOwner(PrimaryStage);
		stg.initModality(Modality.WINDOW_MODAL);
		stg.setResizable(false);
		stg.showAndWait();
	}

	public void calculateAndSetLabel(Label lbl, String l, String n, String lone) {
		try {
			Double L = Double.parseDouble(l);
			Double N = Double.parseDouble(n);
			Double Lone = Double.parseDouble(lone);
			Double Lpr = (N*L) + (N-L) * Lone;
			String lpr = String.valueOf(Lpr);
			lbl.setText("Длина погрузочно-разгрузочного фронта: " + lpr + " м");
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

	}
}
