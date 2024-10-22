package com.giuelino.roll20nestedmacrohelper.Controller;

import com.giuelino.roll20nestedmacrohelper.MacroHelper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.Objects;
import java.util.ResourceBundle;

public class MacroHelperController {
    @FXML
    private Label TopLabel;

    @FXML
    private ImageView TopImage;

    @FXML
    private TextArea MacroField;

    @FXML
    private TextArea ModField;

    @FXML
    private Button CopyButton;
    @FXML
    private Button ComputeButton;

    @FXML
    private Label bottomLabel;

    private Integer first;
    private Integer last;

    ResourceBundle Bundle = MacroHelper.Bundle;
    String firstString= Bundle.getString("firstText");
    String firstMod= Bundle.getString("ModText");

    @FXML
    public void initialize() {
        MacroField.setText(firstString);
        MacroField.setWrapText(true);
        ModField.setWrapText(true);
        ModField.setEditable(false);
        TopLabel.setText(Bundle.getString("Welcome"));
        try {
            TopImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/giuelino/roll20nestedmacrohelper/logo.png"))));
        } catch (Exception e) {
            e.printStackTrace();
        }
        ModField.setText(firstMod);
        CopyButton.setText(Bundle.getString("Copy"));
        ComputeButton.setText(Bundle.getString("Compute"));
        ComputeButton.setDefaultButton(true);
    }


    @FXML
    protected void onComputeButtonClick() {
        if(MacroField.getText().isEmpty()){
            System.out.println("Please enter a Macro");
        }
        else{
            first=MacroField.getText().length();
            parser();
            last=ModField.getText().length();
            bottomLabel.setText(Bundle.getString("After") + first + "\n"+Bundle.getString("Before")  + last);
        }
    }

    @FXML
    protected void onCopyButtonClick() {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(new StringSelection(ModField.getText()), null);

    }

    @FXML
    protected void MacroFieldClicked(){
        if(MacroField.getText().equals(firstString)) {
            MacroField.setText("");
        }
    }


    @FXML
    protected void ResetButtonClick() {
        MacroField.setText("");
        bottomLabel.setText("");
        ModField.setText("");
    }




    private void parser(){
        String input =  MacroField.getText();
        StringBuilder output= new StringBuilder();
        boolean AttributeOrAbilities = false;


        for(char c: input.toCharArray()){
            if(c == '@' || c == '%'){
                output.append(c);
                AttributeOrAbilities = true;
            }
            else if( (c == '}' || c == '|' || c == ',') && AttributeOrAbilities){
                output.append(c);
                if(c == '}') AttributeOrAbilities = false;
            }
            else if( (c == '}' || c == '|' || c == ',') && !AttributeOrAbilities){
                if(c =='}'){
                    output.append("&#125;");
                }
                else if(c =='|'){
                    output.append("&#124;");
                }
                else if(c ==','){
                    output.append("&#44;");
                }
            }
            else {
                output.append(c);
            }
        }

        ModField.setText(output.toString());
    }




}