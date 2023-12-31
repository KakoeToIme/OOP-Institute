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

public class AmountDayOfWork implements Calculator {
	@Override
	public void showWindow(Stage PrimaryStage, Label lbl) {
		Stage stg = new Stage();

		Label tkLabel = new Label("Число календарных дней в году: ");
		Label tvLabel = new Label("Число выходных дней в году: ");
		Label tprLabel = new Label("Число праздничных дней в году: ");
		TextField Tk = new TextField();
		TextField Tv = new TextField();
		TextField Tpr = new TextField();
		Button calculateButton = new Button("Рассчитать");
		calculateButton.setOnAction(event -> {
			calculateAndSetLabel(lbl, Tk.getText(), Tv.getText(), Tpr.getText());
		});

		GridPane root = new GridPane();
		root.setHgap(8);
		root.setVgap(8);
		root.setPadding(new Insets(5));
		root.addRow(0, tkLabel, Tk);
		root.addRow(1, tvLabel, Tv);
		root.addRow(2, tprLabel, Tpr);
		root.add(calculateButton, 0, 3, 2, 1);
		root.add(lbl, 0, 4, 2, 1);

		Scene scn = new Scene(root, 400, 170);
		stg.setScene(scn);
		stg.setTitle("Число дней работы склада");
		stg.initOwner(PrimaryStage);
		stg.initModality(Modality.WINDOW_MODAL);
		stg.setResizable(false);
		stg.showAndWait();
	}

	public void calculateAndSetLabel(Label lbl, String tk, String tv, String tpr) {
		try {
			Double Tk = Double.parseDouble(tk);
			Double Tv = Double.parseDouble(tv);
			Double Tpr = Double.parseDouble(tpr);
			Double Tpo = Tk - Tv - Tpr;
			String tpo = String.valueOf(Tpo);
			lbl.setText("Число рабочих дней склада: " + tpo);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

	}
}
