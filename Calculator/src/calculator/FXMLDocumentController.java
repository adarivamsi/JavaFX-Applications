/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 *
 * @author adari
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML public TextField input;
    
    @FXML private Button add;
    @FXML private Button sub;
    @FXML private Button mul;
    @FXML private Button div;
    @FXML private Button percent;
    @FXML private Button changeSign;
    @FXML private Button sqroot;
    @FXML private Button clear;
    @FXML private Button equal;
    
    @FXML private Button point;
    @FXML private Button zero;    
    @FXML private Button one;
    @FXML private Button two;
    @FXML private Button three;
    @FXML private Button four;    
    @FXML private Button five;
    @FXML private Button six;
    @FXML private Button seven;
    @FXML private Button eight;
    @FXML private Button nine;
    
    @FXML public float data;
    @FXML public int operation;
    @FXML public float display;    
    @FXML public float number;
    
    @FXML
    public void handleButtonAction(ActionEvent event) throws IOException
    {
        if(event.getSource()==one)
        {
       if("0".equals(input.getText()))
        {
                input.clear();
                input.setText("1");
                number=1;
            }
            else{
                input.setText(input.getText()+"1");
                number=1;   
            }    
        }
      else if(event.getSource()==two)
        {
          input.setText(input.getText()+"2");
          number=2;
        }
      else if(event.getSource()==three)
        {
          input.setText(input.getText()+"3");
          number=3;
        }
     else if(event.getSource()==four)
        {
          input.setText(input.getText()+"4");
          number=4;
        }
     else if(event.getSource()==five)
        {
          input.setText(input.getText()+"5");
          number=5;
        }
     else if(event.getSource()==six)
        {
          input.setText(input.getText()+"6");
          number=6;
        }
     else if(event.getSource()== seven)
        {
          input.setText(input.getText()+"7");
          number=7;
        }
     else if(event.getSource()== eight)
        {
          input.setText(input.getText()+"8");
          number=8;
        }
     else if(event.getSource()== nine)
        {
          input.setText(input.getText()+"9");
          number=9;
        }
     else if(event.getSource()== zero)
        {
          input.setText(input.getText()+"0");
          number=0;
        }
      else if(event.getSource()== clear)
        { 
            input.clear();
            number=0;
            data=0;
            input.setText("0");
         }
      else if(event.getSource()== add)
        {
          input.setText(input.getText()+ "+");
          operation=1;
        }
      else if(event.getSource()== sub)
        {
          input.setText(input.getText()+ "-");
          operation=2;
        }
      else if(event.getSource()== mul)
        {
          input.setText(input.getText()+ "*");
          operation=3;
        }
     else if(event.getSource()== div)
        {
           data=data + number;         
          input.setText(input.getText()+ "/");
          operation=4;
        }
      else if(event.getSource()== percent)
        {
          input.setText("%");
          display = number / 100;
          input.setText(String.valueOf(display));
        }
      else if(event.getSource()== changeSign)
       {
          double a=0;
          String positiveNegative=input.getText();
          try
           {
             a = Double.parseDouble(positiveNegative);
             a=a*(-1);
             input.setText(Double.toString(a));
           }
          catch(NumberFormatException e)
           {
              System.out.println("Wrong Operation");
           }
       }
      else if(event.getSource()== equal)
       {        
           try{
           System.out.println("Input : "+input.getText());
               String input1 = " "+input.getText()+" ";
               String strr1=division(input1);
               System.out.println("String 1 is: "+strr1);
               strr1=" "+strr1+" ";
               String strr2=mul(strr1);
               System.out.println("String 2 is: " +strr2);
               strr2=" "+strr2+" ";
               String strr3=addition(strr2);
               strr3=" "+strr3+" ";
               System.out.println("String 3 is: " + strr3);
               String strr4=subtraction(strr3);
               System.out.println("Final String: "+strr4);
               input.setText(strr4.trim());
               String result = input1+" = "+strr4;
               System.out.println(""+result);
               writeUsingFile(result);
           }
           catch(IOException e)
           {
               System.out.println("Wrong Input!");
               input.setText("Wrong Input!");
           }
        }
        else if(event.getSource()== point)
        {
          input.setText(input.getText()+ ".");
        }
        else if(event.getSource()== sqroot)
        {
          double sqRoot;
          String squareRoot=input.getText();
          try
           {
             sqRoot = Double.parseDouble(squareRoot);
             Double root = Math.sqrt(sqRoot);
             input.setText(Double.toString(root));
           }
          catch(NumberFormatException e)
           {
              System.out.println("Wrong Operation");
           }
        }
    }

        
    public static String division(String input)
    {
        String str1 = ""; 
        String str2 = "";
        String str3 = "";
        double a=0;
        double b=0;
        double result;
        int i=0,j=0,k=0,count=0;
        System.out.println("Input : "+input);
        for(i=0; i<input.length(); i++){
        System.out.println("Length : "+input.length());
        char c = input.charAt(i);
        System.out.println("i is: "+i);
        if(c == '/'){
        count++;
        for(j=i-1; 
                (input.charAt(j) == '1' || 
                 input.charAt(j) == '2' || 
                 input.charAt(j) == '3' || 
                 input.charAt(j) == '4' || 
                 input.charAt(j) == '5' || 
                 input.charAt(j) == '6' || 
                 input.charAt(j) == '7' || 
                 input.charAt(j) == '8' || 
                 input.charAt(j) == '9' || 
                 input.charAt(j) == '0' || 
                 input.charAt(j) == '.') && j>=0 
                ; j--)
                {    
                    System.out.println("j :"+j);    
                    str1 = str1 + input.substring(j,j+1);
                    System.out.println("String 1 : "+str1);
                }

        for(k=i+1;  
                input.charAt(k) == '1' || 
                input.charAt(k) == '2' || 
                input.charAt(k) == '3' || 
                input.charAt(k) == '4' || 
                input.charAt(k) == '5' || 
                input.charAt(k) == '6' || 
                input.charAt(k) == '7' || 
                input.charAt(k) == '8' || 
                input.charAt(k) == '9' || 
                input.charAt(k) == '0' || 
                input.charAt(k) == '.' 
                ; k++)
        {         
            System.out.println("STR2 is: "+str2);
            str2 = str2 + input.substring(k,k+1);
        }
            StringBuilder sb1 = new StringBuilder(str1);
            str1 = sb1.reverse().toString();
            a = Double.parseDouble(str1);
            System.out.println("A is :"+a);
            b = Double.parseDouble(str2);
            System.out.println("B is :"+b);
            if(b==0)
            {
                return "Undefined";
            }
            else
            {
            result=a/b;
            }
            System.out.println("Result : "+result);
            str3=input.substring(0,j+1)+Double.toString(result)+input.substring((k),input.length());
            System.out.println("Final String :"+str3);
            if(str3.contains("/"))
            {
               str3=division(str3); 
            }
            return str3;
            }
        }
        if(count>0)
        {
        return str3;
        }
        else
        {
        return input;
        }
   }
          
    public static String mul(String input){
        String str1 = ""; 
        String str2 = "";
        String str3 = "";
        double a=0;
        double b=0;
        double result;
        int i=0,j=0,k=0,count=0;
        for(i=0; i<input.length(); i++){
            char c = input.charAt(i);
            if(c == '*'){
                count++;
                for(j=i-1; 
                        input.charAt(j) == '1' || 
                        input.charAt(j) == '2' || 
                        input.charAt(j) == '3' || 
                        input.charAt(j) == '4' || 
                        input.charAt(j) == '5' || 
                        input.charAt(j) == '6' || 
                        input.charAt(j) == '7' || 
                        input.charAt(j) == '8' || 
                        input.charAt(j) == '9' || 
                        input.charAt(j) == '0' || 
                        input.charAt(j) == '.' 
                        ; j--){
                                str1 = str1 + input.substring(j,j+1);

                              }
                for(k=i+1;  
                        input.charAt(k) == '1' || 
                        input.charAt(k) == '2' || 
                        input.charAt(k) == '3' || 
                        input.charAt(k) == '4' || 
                        input.charAt(k) == '5' || 
                        input.charAt(k) == '6' || 
                        input.charAt(k) == '7' || 
                        input.charAt(k) == '8' || 
                        input.charAt(k) == '9' || 
                        input.charAt(k) == '0' || 
                        input.charAt(k) == '.' 
                        ; k++)
                            {         
                              str2 = str2 + input.substring(k,k+1);
                            }

                    StringBuilder sb1 = new StringBuilder(str1);
                    str1 = sb1.reverse().toString();
                    a = Double.parseDouble(str1);
                    System.out.println("A : "+ a);
                    b = Double.parseDouble(str2);
                    System.out.println("B : "+ b);
                    result=a*b;
                    System.out.println("Result : "+result);
                    str3=input.substring(0,j+1)+Double.toString(result)+input.substring((k),input.length());
                    System.out.println("Final String :"+str3);
                    if(str3.contains("*"))
                    {
                       str3=mul(str3); 
                    }
                    return str3;
            }
        }
        if(count>0){
            return str3;
        }else{
        return input;
        }
    }
 
    public static String addition(String input)
    {
        String str1 = ""; 
        String str2 = "";
        String str3 = "";
        double a=0;
        double b=0;
        double result;
        int i=0,j=0,k=0,count=0,z=0;
        for(int x=0; x<input.length(); x++){
            if(input.charAt(x) == '+' && input.charAt(x-1)==' ')
            {
                System.out.println("Inside ");
                z=x;
                break;
            }
            else
            {
                System.out.println("Outside ");
                z=0;
            }       
        }
        for(i=z+1; i<input.length(); i++)
        {
            char c = input.charAt(i);
            if(c == '+'){
                count++;
                for(j=i-1; 
                        input.charAt(j) == '1' || 
                        input.charAt(j) == '2' || 
                        input.charAt(j) == '3' || 
                        input.charAt(j) == '4' || 
                        input.charAt(j) == '5' || 
                        input.charAt(j) == '6' || 
                        input.charAt(j) == '7' || 
                        input.charAt(j) == '8' || 
                        input.charAt(j) == '9' || 
                        input.charAt(j) == '0' || 
                        input.charAt(j) == '.' 
                        ; j--){
                    if(input.charAt(j-1)=='+' || input.charAt(j-1)=='-')
                    {
                        str1 = str1 + input.substring(j,j+1);
                        System.out.println("String 1"+str1);
                        str1 = str1 + input.substring(j-1,j);
                        System.out.println("String 2"+str1);
                    }
                    else
                    {
                        System.out.println("String 1: "+str1);
                        str1 = str1 + input.substring(j,j+1);
                        System.out.println("String 1: "+str1);
                    }     
                }
                for(k=i+1;  
                        input.charAt(k) == '1' || 
                        input.charAt(k) == '2' || 
                        input.charAt(k) == '3' || 
                        input.charAt(k) == '4' || 
                        input.charAt(k) == '5' || 
                        input.charAt(k) == '6' || 
                        input.charAt(k) == '7' || 
                        input.charAt(k) == '8' || 
                        input.charAt(k) == '9' || 
                        input.charAt(k) == '0' || 
                        input.charAt(k) == '.' 
                        ; k++)
                {         
                    str2 = str2 + input.substring(k,k+1);
               }
                StringBuilder sb1 = new StringBuilder(str1);
                System.out.println("String 1 reverse: "+str1);
                str1 = sb1.reverse().toString();
                a = Double.parseDouble(str1);
                b = Double.parseDouble(str2);
                result=a+b;
                System.out.println("Result: "+result);
                    if(input.charAt(j)=='+' || input.charAt(j)=='-'){
                        if(result>0){
                            str3=input.substring(0,j)+"+"+Double.toString(result)+input.substring((k),input.length());
                        }
                        else if(result==0){
                            str3=input.substring(0,j)+input.substring((k),input.length());
                        }
                        else{
                            str3=input.substring(0,j)+Double.toString(result)+input.substring((k),input.length());
                        }        
                    }
                    else{
                        str3=input.substring(0,j+1)+Double.toString(result)+input.substring((k),input.length());
                    }
                    System.out.println("Final String:"+str3);
                    if(str3.contains("+")){
                        System.out.println("Length :"+str3.length());
                       str3=addition(str3); 
                    }
                    return str3;   
            }
        }
        if(count>0){
            return str3;
        }else{
        return input;
        }
    } 
           
    public static String subtraction(String input){
         String str1 = ""; 
         String str2 = "";
         String str3 = "";
         double a=0;
         double b=0;
         double result;
         int i=0,j=0,k=0,count=0,z=0;
         for(int x=0; x<input.length(); x++){
             if(input.charAt(x) == '-' && input.charAt(x-1)==' '){
                 System.out.println("Inside ");
                 z=x;
                 break;
             }
             else{
                 System.out.println("Outside ");
                 z=0;
             }       
         }
         for(i=z+1; i<input.length(); i++){
             char c = input.charAt(i);
             if(c == '-'){
                 count++;
                 for(j=i-1; 
                         input.charAt(j) == '1' || 
                         input.charAt(j) == '2' || 
                         input.charAt(j) == '3' || 
                         input.charAt(j) == '4' || 
                         input.charAt(j) == '5' || 
                         input.charAt(j) == '6' || 
                         input.charAt(j) == '7' || 
                         input.charAt(j) == '8' || 
                         input.charAt(j) == '9' || 
                         input.charAt(j) == '0' || 
                         input.charAt(j) == '.' 
                         ; j--){
                     if(input.charAt(j-1)=='+' || input.charAt(j-1)=='-'){
                         str1 = str1 + input.substring(j,j+1);
                         System.out.println("String 1"+str1);
                         str1 = str1 + input.substring(j-1,j);
                     }
                     else{
                         System.out.println("str1: "+str1);
                         str1 = str1 + input.substring(j,j+1);
                         System.out.println("String 1"+str1);
                     } 
                 }
                 for(k=i+1;  
                         input.charAt(k) == '1' || 
                         input.charAt(k) == '2' || 
                         input.charAt(k) == '3' || 
                         input.charAt(k) == '4' || 
                         input.charAt(k) == '5' || 
                         input.charAt(k) == '6' || 
                         input.charAt(k) == '7' || 
                         input.charAt(k) == '8' || 
                         input.charAt(k) == '9' || 
                         input.charAt(k) == '0' || 
                         input.charAt(k) == '.' 
                         ; k++)
                 {         
                     str2 = str2 + input.substring(k,k+1);
                 }
                 StringBuilder sb1 = new StringBuilder(str1);
                 System.out.println("String 1 Reverse"+str1);
                 str1 = sb1.reverse().toString();
                 a = Double.parseDouble(str1);
                 b = Double.parseDouble(str2);
                 result=a-b;
                 System.out.println("Result : "+result);
                 if(input.charAt(j)=='+' || input.charAt(j)=='-'){
                     if(result>0){
                        str3=input.substring(0,j)+"+"+Double.toString(result)+input.substring((k),input.length());
                     }
                     else if(result==0){
                        str3=input.substring(0,j)+input.substring((k),input.length());
                     }
                     else{
                        str3=input.substring(0,j)+Double.toString(result)+input.substring((k),input.length());
                     }
                 }
                 else{
                     str3=input.substring(0,j+1)+Double.toString(result)+input.substring((k),input.length());
                 }
                 System.out.println("Final String :"+str3);
                     if(str3.contains("-")){
                        str3=subtraction(str3); 
                     }
                     return str3;
             }
         }
         if(count>0){
             return str3;
         }else{
             System.out.println("Finally ");
             return input;
         }
    } 
    
    public static void writeUsingFile(String s) throws IOException
    {
        BufferedWriter out = new BufferedWriter(new FileWriter("calculator.txt",true));
        out.write(s);
        out.newLine();
        out.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try(FileWriter fw = new FileWriter("calculator.txt");
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter out = new PrintWriter(bw))
                    {
                     System.out.println("File Opened!");
                     System.out.println();
                     out.print("");
                     out.close();
                    } 
         catch (Exception e) {
                    System.out.println("Wrong Input ");
                    }
    }    
    
}
