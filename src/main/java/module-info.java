module com.giuelino.roll20nestedmacrohelper {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    opens com.giuelino.roll20nestedmacrohelper to javafx.fxml;
    exports com.giuelino.roll20nestedmacrohelper;
    exports com.giuelino.roll20nestedmacrohelper.Controller;
    opens com.giuelino.roll20nestedmacrohelper.Controller to javafx.fxml;
}