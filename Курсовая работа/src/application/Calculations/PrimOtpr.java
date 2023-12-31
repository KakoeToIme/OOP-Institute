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

public class PrimOtpr implements Calculator{
	@Override
	public void showWindow (Stage primaryStage, Label lbl) {
		Stage stg = new Stage();
		Label qyearLabel = new Label("Годовое поступление товара на склад: ");
		Label knerLabel = new Label("Коэффициент неравномерности поступления продукции на склад: ");
		Label pLabel = new Label("Укрупненный показатель нагрузок на 1 м^2 в экспедиционных помещениях (тонн/м^2): ");
		Label thrLabel = new Label("Время хранения (1-2 дня): ");
		TextField qyear = new TextField();
		TextField kner = new TextField();
		TextField p = new TextField();
		TextField thr = new TextField();
		Button calculateButton = new Button("Рассчитать");
		calculateButton.setOnAction(event -> {
			calculateAndSetLabel(lbl, qyear.getText(), kner.getText(), p.getText(), thr.getText());
		});
		
		GridPane root = new GridPane();
		root.setHgap(8);
		root.setVgap(8);
		root.setPadding(new Insets(5));
		root.addRow(0, qyearLabel, qyear);
		root.addRow(1, knerLabel, kner);
		root.addRow(2, pLabel, p);
		root.addRow(3, thrLabel, thr);
		root.add(calculateButton, 0, 4, 2, 1);
		root.add(lbl, 0, 5, 2, 1);
		
		Scene scn = new Scene(root, 700, 200);
		stg.setScene(scn);
		stg.setTitle("Площадь приемо-отправочных площадок");
		stg.initOwner(primaryStage);
		stg.initModality(Modality.WINDOW_MODAL);
		stg.setResizable(false);
		stg.showAndWait();
	}
	private void calculateAndSetLabel(Label lbl, String qyear, String kner, String p, String thr) {
		try {
		Double Qyear = Double.parseDouble(qyear);
		Double Kner = Double.parseDouble(kner);
		Double P = Double.parseDouble(p);
		Double Thr = Double.parseDouble(thr);
		Double Sprot = (Qyear * Kner * Thr) / ((double) 365 * P);
		String sprot = String.valueOf(Sprot);
		lbl.setText("Площадь приемо-отправочных площадок: " + sprot + " м^2");
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}
}
