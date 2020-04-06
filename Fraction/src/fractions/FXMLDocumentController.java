/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fractions;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 *
 * @author adari
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML private TextField txtNum1;
    @FXML private TextField txtNum2;
    @FXML private TextField txtNum3;
    @FXML private TextField txtDen1;
    @FXML private TextField txtDen2;
    @FXML private TextField txtDen3;
    @FXML private RadioButton addOp;
    @FXML private RadioButton subOp;
    @FXML private RadioButton mulOp;
    @FXML private RadioButton divOp;
    @FXML private Label opsLabel;
    @FXML private ToggleGroup opsToggle;
    
    @FXML
    private void handleButtonComputeAction(ActionEvent event) {
       try{
       Fraction f1 = new Fraction(Integer.parseInt(txtNum1.getText()),Integer.parseInt(txtDen1.getText()));
       Fraction f2 = new Fraction(Integer.parseInt(txtNum2.getText()),Integer.parseInt(txtDen2.getText()));
            if(this.opsToggle.getSelectedToggle().equals(this.addOp)){
                Fraction sum = f1.add(f2);
                this.txtNum3.setText(Integer.toString(sum.getNumerator()));
                this.txtDen3.setText(Integer.toString(sum.getDenominator()));
            }
            else if(this.opsToggle.getSelectedToggle().equals(this.subOp)){
                Fraction sub = f1.sub(f2);
                this.txtNum3.setText(Integer.toString(sub.getNumerator()));
                this.txtDen3.setText(Integer.toString(sub.getDenominator()));
            }
            else if(this.opsToggle.getSelectedToggle().equals(this.mulOp)){
                Fraction mul = f1.mul(f2);
                this.txtNum3.setText(Integer.toString(mul.getNumerator()));
                this.txtDen3.setText(Integer.toString(mul.getDenominator()));
            }
            else if(this.opsToggle.getSelectedToggle().equals(this.divOp)){
                Fraction div = f1.div(f2);
                this.txtNum3.setText(Integer.toString(div.getNumerator()));
                this.txtDen3.setText(Integer.toString(div.getDenominator()));
            }
       }
       catch(NumberFormatException ex){
       Alert alert = new Alert(Alert.AlertType.INFORMATION);
       alert.setTitle("Error");
       alert.setHeaderText("Something Wrong");
       alert.setContentText(ex.getMessage());
       alert.showAndWait();
       }
    }
        
    @FXML
    private void handleButtonQuitAction(ActionEvent event) {
        Platform.exit();
    }
    
    public void radioButtonChanged(){
        if (this.opsToggle.getSelectedToggle().equals(this.addOp))
            opsLabel.setText("+");
        if (this.opsToggle.getSelectedToggle().equals(this.subOp))
            opsLabel.setText("-");
        if (this.opsToggle.getSelectedToggle().equals(this.mulOp))
            opsLabel.setText("X");
        if (this.opsToggle.getSelectedToggle().equals(this.divOp))
            opsLabel.setText("/");    
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        opsLabel.setText("");
        opsToggle = new ToggleGroup();
        this.addOp.setToggleGroup(opsToggle);
        this.subOp.setToggleGroup(opsToggle);
        this.mulOp.setToggleGroup(opsToggle);
        this.divOp.setToggleGroup(opsToggle);
    }    
    
}
