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

public class KromNaFactOb implements Calculator {
	@Override
	public void showWindow(Stage primaryStage, Label lbl) {
		Stage stg = new Stage();

		Label ofLabel = new Label("Фактический обьем выпуска и реализации продукции: ");
		Label kbLabel = new Label("Кромка безопасности: ");
		TextField Of = new TextField();
		TextField Kb = new TextField();
		Button calculateButton = new Button("Рассчитать");
		calculateButton.setOnAction(event -> {
			calculateAndSetLabel(lbl, Of.getText(), Kb.getText());
		});

		GridPane root = new GridPane();
		root.setHgap(8);
		root.setVgap(8);
		root.setPadding(new Insets(5));
		root.addRow(0, ofLabel, Of);
		root.addRow(1, kbLabel, Kb);
		root.add(calculateButton, 0, 2, 2, 1);
		root.add(lbl, 0, 3, 2, 1);

		Scene scn = new Scene(root, 500, 140);
		stg.setScene(scn);
		stg.setTitle("Отношение кромки безопасности к фактическому обьему");
		stg.initOwner(primaryStage);
		stg.initModality(Modality.WINDOW_MODAL);
		stg.setResizable(false);
		stg.showAndWait();
	}

	private void calculateAndSetLabel(Label lbl, String of, String kb) {
		try {
			Double Of = Double.parseDouble(of);
			Double Kb = Double.parseDouble(kb);
			Double Kper = (Kb / Of) * (double) 100;
			String kper = String.valueOf(Kper);
			lbl.setText("Отношение кромки безопасности к фактическому обьему: " + kper + " %");
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}
}
