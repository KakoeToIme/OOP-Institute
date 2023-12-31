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

public class KromSecur implements Calculator {
	@Override
	public void showWindow(Stage primaryStage, Label lbl) {
		Stage stg = new Stage();

		Label ofLabel = new Label("Фактический обьем выпуска и реализации продукции: ");
		Label qstrixLabel = new Label("Точка безубыточности: ");
		TextField Of = new TextField();
		TextField Qstrix = new TextField();
		Button calculateButton = new Button("Рассчитать");
		calculateButton.setOnAction(event -> {
			calculateAndSetLabel(lbl, Of.getText(), Qstrix.getText());
		});

		GridPane root = new GridPane();
		root.setHgap(8);
		root.setVgap(8);
		root.setPadding(new Insets(5));
		root.addRow(0, ofLabel, Of);
		root.addRow(1, qstrixLabel, Qstrix);
		root.add(calculateButton, 0, 2, 2, 1);
		root.add(lbl, 0, 3, 2, 1);

		Scene scn = new Scene(root, 500, 140);
		stg.setScene(scn);
		stg.setTitle("Кромка безопасности");
		stg.initOwner(primaryStage);
		stg.initModality(Modality.WINDOW_MODAL);
		stg.setResizable(false);
		stg.showAndWait();
	}

	private void calculateAndSetLabel(Label lbl, String of, String qstrix) {
		try {
			Double Of = Double.parseDouble(of);
			Double Qstrix = Double.parseDouble(qstrix);
			Double Kb = Of - Qstrix;
			String kb = String.valueOf(Kb);
			lbl.setText("Кромка безопасности: " + kb);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}
}
