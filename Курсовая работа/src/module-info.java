module InventoryControl {
	requires javafx.controls;
	requires jdk.httpserver;
	requires java.sql;
	opens application to javafx.graphics, javafx.fxml; requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
}
