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

public class SredGruzprib implements Calculator {
	@Override
	public void showWindow(Stage PrimaryStage, Label lbl) {
		Stage stg = new Stage();

		Label qpgodLabel = new Label("Годовой грузопоток склада по прибытию (т/год): ");
		Label knerpLabel = new Label("Коэффициент неравномерности по приему грузов (1,2 - 1,5): ");
		Label tpLabel = new Label("Число дней работы склада на прием грузов: ");
		TextField qpgod = new TextField();
		TextField knerp = new TextField();
		TextField tp = new TextField();
		Button calculateButton = new Button("Рассчитать");
		calculateButton.setOnAction(event -> {
			calculateAndSetLabel(lbl, qpgod.getText(), knerp.getText(), tp.getText());
		});

		GridPane root = new GridPane();
		root.setHgap(8);
		root.setVgap(8);
		root.setPadding(new Insets(5));
		root.addRow(0, qpgodLabel, qpgod);
		root.addRow(1, knerpLabel, knerp);
		root.addRow(2, tpLabel, tp);
		root.add(calculateButton, 0, 3, 2, 1);
		root.add(lbl, 0, 4, 2, 1);

		Scene scn = new Scene(root, 550, 170);
		stg.setScene(scn);
		stg.setTitle("Среднесуточный грузопоток прибытия");
		stg.initOwner(PrimaryStage);
		stg.initModality(Modality.WINDOW_MODAL);
		stg.setResizable(false);
		stg.showAndWait();
	}
	public void calculateAndSetLabel(Label lbl, String qpgod, String knerp, String tp) {
		try {
		Double Qpgod = Double.parseDouble(qpgod);
		Double Knerp = Double.parseDouble(knerp);
		Double Tp = Double.parseDouble(tp);
		Double Qpsyt = (Qpgod * Knerp)/Tp;
		String qpsyt = String.valueOf(Qpsyt);
		lbl.setText("Среднесуточный грузопоток прибытия: " + qpsyt + " т/сут" );
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
	}
}
