package com.giuelino.roll20nestedmacrohelper;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public class MacroHelper extends Application {

    public static Locale locale;
    public static ResourceBundle Bundle;

    @Override
    public void start(Stage stage) throws IOException {

        Locale systemLocale = Locale.getDefault();
        if(systemLocale.equals(Locale.ENGLISH)) {
            locale = Locale.ENGLISH;
        }
        else if(systemLocale.equals(Locale.ITALIAN)){
            locale = Locale.ITALIAN;
        }
        else{
            locale = Locale.ENGLISH;
        }
        Bundle=ResourceBundle.getBundle("com.giuelino.roll20nestedmacrohelper.Bundle", locale);

        FXMLLoader fxmlLoader = new FXMLLoader(MacroHelper.class.getResource("UI.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle(Bundle.getString("Title"));
        String css = MacroHelper.class.getResource("cupertino-light.css").toExternalForm();
        scene.getStylesheets().add(css);
        Image icon = new Image(Objects.requireNonNull(MacroHelper.class.getResourceAsStream("logo.png")));
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();   
    }
}