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

public class Sredvnper implements Calculator {
	@Override
	public void showWindow(Stage PrimaryStage, Label lbl) {
		Stage stg = new Stage();

		Label qpsytLabel = new Label("Среднесуточный грузопоток прибытия (т/сут): ");
		Label qosytLabel = new Label("Среднесуточный грузопоток по отправлению (т/сут): ");
		Label kperLabel = new Label(
				"Коэффициент внутрискладских перевалок, сколько операци совершается в тех. цикле: ");
		TextField qpsyt = new TextField();
		TextField qosyt = new TextField();
		TextField kper = new TextField();
		Button calculateButton = new Button("Рассчитать");
		calculateButton.setOnAction(event -> {
			calculateAndSetLabel(lbl, qpsyt.getText(), qosyt.getText(), kper.getText());
		});

		GridPane root = new GridPane();
		root.setHgap(8);
		root.setVgap(8);
		root.setPadding(new Insets(5));
		root.addRow(0, qpsytLabel, qpsyt);
		root.addRow(1, qosytLabel, qosyt);
		root.addRow(2, kperLabel, kper);
		root.add(calculateButton, 0, 3, 2, 1);
		root.add(lbl, 0, 4, 2, 1);

		Scene scn = new Scene(root, 650, 170);
		stg.setScene(scn);
		stg.setTitle("Среднесуточная складская грузопереработка");
		stg.initOwner(PrimaryStage);
		stg.initModality(Modality.WINDOW_MODAL);
		stg.setResizable(false);
		stg.showAndWait();
	}
	public void calculateAndSetLabel(Label lbl, String qpsyt, String qosyt, String kper) {
		try {
		Double Qpsyt = Double.parseDouble(qpsyt);
		Double Qosyt = Double.parseDouble(qosyt);
		Double Kper = Double.parseDouble(kper);
		Double Qvsyt = (Qpsyt + Qosyt) * Kper;
		String qvsyt = String.valueOf(Qvsyt);
		lbl.setText("Среднесуточная грузопереработка: " + qvsyt + " т/сут" );
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
	}
}
