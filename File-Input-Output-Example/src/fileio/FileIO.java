/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileio;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adari
 */
public class FileIO {
    
    public static void readFile() throws FileNotFoundException, IOException{
        BufferedReader ins = new BufferedReader(new FileReader("xanadu.txt"));
        while(ins.ready())
        {
            String s = ins.readLine();
            System.out.println(s);
        }
        ins.close();
    }
    
    public static void writeFile() throws IOException{
        String s = "I Adore Programming";
        BufferedWriter outs = new BufferedWriter(new FileWriter("xanadu.txt"));
        outs.write(s);
        outs.close();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            writeFile();
            readFile();
        } catch (IOException ex) {
            Logger.getLogger(FileIO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}
