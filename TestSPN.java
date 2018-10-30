/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testspn;

import static GUI.InputText.inputeFile;
import alexporechny.collocation.CombinationWords;
import alexporechny.collocation.Statistics;
import alexporechny.dataStructure.ManagingSentenceGraphs;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ru.textanalysis.tfwwt.jmorfsdk.jmorfsdk.JMorfSdk;
import ru.textanalysis.tfwwt.jmorfsdk.jmorfsdk.load.JMorfSdkLoad;
import ru.textanalysis.tfwwt.morphological.structures.internal.OmoForm;

/**
 *
 * @author Андрей
 */
public class TestSPN {
    
    
  
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String inputFile = "Antarctida.txt";
        //  ArrayList<String> x = new ArrayList<>();
        String line = null;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile), "windows-1251"));

            while ((line = reader.readLine()) != null) {
                System.out.println("***" + line);
                ManagingSentenceGraphs managing = null;
                inputeFile(inputFile);
                managing = new ManagingSentenceGraphs(line);
                ArrayList<CombinationWords> combWord = Statistics.compilationOfStatistics(managing.getArrSentence());
                Statistics.printCollection(combWord);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TestSPN.class.getName()).log(Level.SEVERE, "File not found. Name of file: " + inputFile, ex);
        } catch (IOException ex) {
            Logger.getLogger(TestSPN.class.getName()).log(Level.SEVERE, "Could not open file. Name of file: " + inputFile, ex);
        }

    }

}
