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

public class TochkaBezub implements Calculator {
	@Override
	public void showWindow(Stage PrimaryStage, Label lbl) {
		Stage stg = new Stage();

		Label fLabel = new Label("Совокупные переменные затраты: ");
		Label pLabel = new Label("Цена единицы продукции: ");
		Label vLabel = new Label("Переменные затраты на единицу продукции: ");
		TextField F = new TextField();
		TextField P = new TextField();
		TextField V = new TextField();
		Button calculateButton = new Button("Рассчитать");
		calculateButton.setOnAction(event -> {
			calculateAndSetLabel(lbl, F.getText(), P.getText(), V.getText());
		});

		GridPane root = new GridPane();
		root.setHgap(8);
		root.setVgap(8);
		root.setPadding(new Insets(5));
		root.addRow(0, fLabel, F);
		root.addRow(1, pLabel, P);
		root.addRow(2, vLabel, V);
		root.add(calculateButton, 0, 3, 2, 1);
		root.add(lbl, 0, 4, 2, 1);

		Scene scn = new Scene(root, 450, 160);
		stg.setScene(scn);
		stg.setTitle("Точка безубыточности");
		stg.initOwner(PrimaryStage);
		stg.initModality(Modality.WINDOW_MODAL);
		stg.setResizable(false);
		stg.showAndWait();
	}

	public void calculateAndSetLabel(Label lbl, String f, String p, String v) {
		try {
			Double F = Double.parseDouble(f);
			Double P = Double.parseDouble(p);
			Double V = Double.parseDouble(v);
			Double Qstrix = F / (P-V) ;
			String qstrix = String.valueOf(Qstrix);
			lbl.setText("Точка безубыточности: " + qstrix);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}
}
