/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bignumbercalculator;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author adari
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML private Label operator;
    @FXML private TextField num1;
    @FXML private TextField num2;
    @FXML private TextField result;
    @FXML private Button add;
    @FXML private Button sub;
    @FXML private Button mul;
    @FXML private Button div;
    @FXML private Button bigger;
    @FXML private Button smaller;
    @FXML private Button equal;
    @FXML private Button clear;
    @FXML private Button compute;
    @FXML private Button close;
    
    static short flag = 0;
    
    @FXML
    private void handleButtonAddAction(ActionEvent event) {
        operator.setText("+");
        flag = 1;
    }
    @FXML
    private void handleButtonSubAction(ActionEvent event) {
        operator.setText("-");
        flag = 2;
    }
    @FXML
    private void handleButtonMulAction(ActionEvent event) {
        operator.setText("x");
        flag = 3;
    }
    @FXML
    private void handleButtonDivAction(ActionEvent event) {
        operator.setText("/");
        flag = 4;
    }
    @FXML
    private void handleButtonBiggerAction(ActionEvent event) {
        operator.setText(">");
        flag = 5;
    }
    @FXML
    private void handleButtonSmallerAction(ActionEvent event) {
        operator.setText("<");
        flag = 6;
    }
    @FXML
    private void handleButtonEqualAction(ActionEvent event) {
        operator.setText("==");
        flag = 7;
    }
    @FXML
    private void handleButtonComputeAction(ActionEvent event) {
        
        result.clear();
        
        BigNumber number1 = new BigNumber(num1.getText());
        BigNumber number2 = new BigNumber(num2.getText());
        
        if(number1.inputErr == true || number2.inputErr == true) {
            result.setText("Invalid input !");
            return;
        }
        
        String res = null;
        Boolean res2 = null;
        
        switch(flag) {
            case 1:
                res = number1.plus(number2);
                break;
            case 2:
                res = number1.minus(number2);
                break;
            case 3:
                res = number1.mul(number2);
                break;
            case 4:
                res = number1.div(number2);
                break;
            case 5:
                res2 = number1.greaterThan(number2);
                break;
            case 6:
                res2 = number1.lessThan(number2);
                break;
            case 7:
                res2 = number1.equals(number2);
                break;
            default:
                break;
        }
        if(flag >= 1 && flag <= 7 && res != null)
            result.setText(res);
        else if(flag >= 1 && flag <= 7 && res2 != null)
            result.setText(Boolean.toString(res2));
        else
            result.setText("Atleast one operator should be selected!!");
    }
    @FXML
    private void handleTextExceedLengthAction(KeyEvent event) {
        int MAX_LENGTH = 44;
        if(num1.getText().length() > MAX_LENGTH)
            num1.setText(num1.getText().substring(1, MAX_LENGTH + 1));
        if(num2.getText().length() > MAX_LENGTH)
            num2.setText(num2.getText().substring(1, MAX_LENGTH + 1));
    }
    @FXML
    private void handleButtonClearAction(ActionEvent event) {
        num1.clear();
        num2.clear();
        result.clear();
    }
    @FXML
    private void handleButtonCloseAction(ActionEvent event) {
        Platform.exit();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}