/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package summarizaton;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import ru.library.text.word.Word;

/**
 *
 * @author Kasatkina Alyona
 */
public class TextSummarization {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String inputFile = "Antarctida.txt";
       // String inputFile = "Mouse.txt";
       // String inputFile = "mor.txt";
       // String inputFile = "organicHimia.txt";
       //  String inputFile = "ффф.txt";

        /*MethodsOfSummarization methodsOfSummarization = new MethodsOfSummarization();
        Map<Integer, String> referat0 = methodsOfSummarization.getReferatOfMethodStatistic(inputFile, 30);
        for (Entry<Integer, String> entry : referat0.entrySet()) {
            System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
        }
        Map<Integer, String> referat1 = methodsOfSummarization.getReferatOfMethodStatistic(inputFile, 50);
        for (Entry<Integer, String> entry : referat1.entrySet()) {
            System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
        }
        Map<Integer, String> referat2 = methodsOfSummarization.getReferatOfMethodStatistic(inputFile, 70);
        for (Entry<Integer, String> entry : referat2.entrySet()) {
            System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
        }*/
        // ***************************************************************************************************************************************
        /*Map<Integer, String> referat3 = methodsOfSummarization.getReferatOfMethodSymmetric(inputFile, 32);
        for (Entry<Integer, String> entry : referat3.entrySet()) {
            System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
        }
        Map<Integer, String> referat4 = methodsOfSummarization.getReferatOfMethodSymmetric(inputFile, 64);
        for (Entry<Integer, String> entry : referat4.entrySet()) {
            System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
        }
        Map<Integer, String> referat5 = methodsOfSummarization.getReferatOfMethodSymmetric(inputFile, 70);
        for (Entry<Integer, String> entry : referat5.entrySet()) {
            System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
        }*/
        //****************************************************************************************************************************************
       /* Map<Integer, String> referat6 = methodsOfSummarization.getReferatOfMethodPosition(inputFile);
        for (Entry<Integer, String> entry : referat6.entrySet()) {
            System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
        }*/
        //***************************************************************************************************************************************** 
       /* Map<Integer, String> referat7 = methodsOfSummarization.getReferatOfMethodPositionAndStatistic(inputFile, 30);
        for (Entry<Integer, String> entry : referat7.entrySet()) {
            System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
        }
        Map<Integer, String> referat8 = methodsOfSummarization.getReferatOfMethodPositionAndStatistic(inputFile, 50);
        for (Entry<Integer, String> entry : referat8.entrySet()) {
            System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
        }
        Map<Integer, String> referat9 = methodsOfSummarization.getReferatOfMethodPositionAndStatistic(inputFile, 70);
        for (Entry<Integer, String> entry : referat9.entrySet()) {
            System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
        }*/
        //*******************************************************************************************************************************************
       /* Map<Integer, String> referat10 = methodsOfSummarization.getReferatOfMethodPositionAndSymmetric(inputFile, 30);
        for (Entry<Integer, String> entry : referat10.entrySet())
        {
            System.out.println("Key : " + entry.getKey() + " Value : "+ entry.getValue());
        }
         Map<Integer, String> referat11 = methodsOfSummarization.getReferatOfMethodPositionAndSymmetric(inputFile, 50);
        for (Entry<Integer, String> entry : referat11.entrySet())
        {
            System.out.println("Key : " + entry.getKey() + " Value : "+ entry.getValue());
        }
         Map<Integer, String> referat12 = methodsOfSummarization.getReferatOfMethodPositionAndSymmetric(inputFile, 70);
        for (Entry<Integer, String> entry : referat12.entrySet())
        {
            System.out.println("Key : " + entry.getKey() + " Value : "+ entry.getValue());
        }*/
        //**********************************************************************************************************************************************
        /*Map<Integer, String> referat13 = methodsOfSummarization.getReferatOfMethodIntegration(inputFile, 30);
        for (Entry<Integer, String> entry : referat13.entrySet()) {
            System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
        }
        Map<Integer, String> referat14 = methodsOfSummarization.getReferatOfMethodIntegration(inputFile, 50);
        for (Entry<Integer, String> entry : referat14.entrySet()) {
            System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
        }
        Map<Integer, String> referat15 = methodsOfSummarization.getReferatOfMethodIntegration(inputFile, 70);
        for (Entry<Integer, String> entry : referat15.entrySet()) {
            System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
        */
        //**********************************************************************************************************************************************
       /* Map<Integer, String> referat16 = methodsOfSummarization.getReferatOfMethodIntegrationRandom(inputFile, 30);
        for (Entry<Integer, String> entry : referat16.entrySet()) {
            System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
        }
        Map<Integer, String> referat17 = methodsOfSummarization.getReferatOfMethodIntegrationRandom(inputFile, 50);
        for (Entry<Integer, String> entry : referat17.entrySet()) {
            System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
        }
        Map<Integer, String> referat18 = methodsOfSummarization.getReferatOfMethodIntegrationRandom(inputFile, 70);
        for (Entry<Integer, String> entry : referat18.entrySet()) {
            System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
        }*/
       //**********************************************************************************************************************************************
     Text text = new Text();
      List<String> allText = text.readFromFile(inputFile);

      Map<Integer, String> numberAndParagraphs = text.splitTextByParagraphs(allText);
      //Sentence sentence = new Sentence();
      //Map<Integer, String>  numberAndSentence = sentence.extractSentencesFromText(numberAndParagraphs);
      Map<Integer, String>  numberAndSentence = text.splitTextBySentences(numberAndParagraphs);
       for(int i = 0; i < numberAndSentence.size(); i++){
          System.out.println(i + "!   " + numberAndSentence.get(i));
      }
       
       List<List<Integer>> sentenceInParagraph = new ArrayList<>();
       sentenceInParagraph = text.getSentencesInParagraph();
       for(int i = 0; i < sentenceInParagraph.size(); i++){
       System.out.println(i + "Parag " + sentenceInParagraph.get(i));
       }
       
       Sentence sentence = new Sentence();
       List<List<String>> wordsInSentence = new ArrayList<>();
       wordsInSentence = sentence.splitSentenceByWords(numberAndSentence);
       for (int i = 0; i < wordsInSentence.size(); i++) {
            System.out.println(i + " " + wordsInSentence.get(i));
        }
       
       List<List<Word>> wordsInSentenceInInitialForm = new ArrayList<>();
       wordsInSentenceInInitialForm = sentence.splitSentenceByWordsInInitialForm(wordsInSentence);
       for (int i = 0; i < wordsInSentenceInInitialForm.size(); i++) {
            System.out.println(i + " " + wordsInSentenceInInitialForm.get(i));
        }
       
       MethodIndicator methodIndicator = new MethodIndicator();
     /*List<Float> indicator = methodIndicator.getWeightOfSentenceByIndicators(numberAndSentence);
       for (int i = 0; i < indicator.size(); i++) {
            System.out.println(i + " " + indicator.get(i));
        }
     //  List<List<Word>> wordsInSentence  = sentence.extractWordsInSentence(numberAndSentence);*/
     System.out.println("----------------------------------------------------------");
     String encoding = System.getProperty("console.encoding", "windows-1251");
      System.out.println("Введите через запятую ключевые слова: ");    
        Scanner in = new Scanner(System.in, encoding);
        String markers = in.nextLine();
       List<Float> markersMethod = methodIndicator.getWeightOfSentenceByMarkers(wordsInSentenceInInitialForm, wordsInSentence, markers);
       for (int i = 0; i < markersMethod.size(); i++) {
            System.out.println(i + " " + markersMethod.get(i));
        }
       
      /* List<Float> connectors = methodIndicator.getWeightSentenceByConnectors(wordsInSentenceInInitialForm);
       for(int i = 0; i < connectors.size(); i++){
           System.out.println(i + " " + connectors.get(i));
       }
       
       Map<Integer, Float> weightSent = methodIndicator.getWeightOfSentences(numberAndSentence, wordsInSentenceInInitialForm);
       for (Entry<Integer, Float> entry : weightSent.entrySet()) {
            System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
        }
      
    /*  Map<Integer, String> referatInd = methodIndicator.getReferat(numberAndSentence, wordsInSentenceInInitialForm, 30);
       for (Entry<Integer, String> entry : referatInd.entrySet()) {
            System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
        }
       
       Map<Integer, String> referatInd1 = methodIndicator.getReferat(numberAndSentence, wordsInSentenceInInitialForm, 50);
       for (Entry<Integer, String> entry : referatInd1.entrySet()) {
            System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
        }
       
       Map<Integer, String> referatInd2 = methodIndicator.getReferat(numberAndSentence, wordsInSentenceInInitialForm, 70);
       for (Entry<Integer, String> entry : referatInd2.entrySet()) {
            System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
        }*/
       
    }

}
