module ProyectoBDD {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires java.sql;
	requires java.desktop;
	requires javafx.base;
	
	opens application to javafx.graphics, javafx.fxml;
	opens dataClass to javafx.graphics, javafx.fxml;

    exports application;
    exports dataClass;
}
