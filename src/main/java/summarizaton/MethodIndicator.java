/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package summarizaton;

import ru.library.text.word.Word;
import ru.textanalysis.tfwwt.jmorfsdk.jmorfsdk.JMorfSdk;
import ru.textanalysis.tfwwt.jmorfsdk.jmorfsdk.load.JMorfSdkLoad;
import ru.textanalysis.tfwwt.morphological.structures.grammeme.MorfologyParameters;
import ru.textanalysis.tfwwt.morphological.structures.grammeme.MorfologyParameters.TypeOfSpeech;
import ru.textanalysis.tfwwt.morphological.structures.internal.IOmoForm;
import ru.textanalysis.tfwwt.morphological.structures.storage.OmoFormList;

import java.util.*;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Kasatkina Alyona
 */
public class MethodIndicator {

    private final Map<Integer, Float> numberAndWeightOfSentence = new HashMap<>();
    private final Logger log = Logger.getLogger(Sentence.class.getName());
    //private final String encoding = System.getProperty("console.encoding", "windows-1251");
    private final MethodSupporting methodSupporting = new MethodSupporting();
    private final JMorfSdk jMorfSdk = JMorfSdkLoad.loadFullLibrary();
    
    public Map<Integer, String> getReferat(Map<Integer, String> numberAndSentence, List<List<Word>> wordsInSentenceInInitialForm, int procentOfText, List<List<String>> wordsInSentence, String markers){
        Map<Integer, Float> weightOfSentence = getWeightOfSentences(numberAndSentence, wordsInSentenceInInitialForm, wordsInSentence, markers);
        List<Integer> keysWeightOfSentence = new ArrayList<>(weightOfSentence.keySet());
        List<Integer> keysNumberAndSentence = new ArrayList<>(numberAndSentence.keySet());
        Map<Integer, String> referat = new HashMap<>();
        int size = numberAndSentence.size();
        int sizeReferat = (int) ((size * procentOfText) / 100);
        for(int i = 0; i < sizeReferat; i++){
            for(int j = 0; j < keysNumberAndSentence.size(); j++){
                if(keysWeightOfSentence.get(i) ==  keysNumberAndSentence.get(j)){
                    referat.put(keysWeightOfSentence.get(i), numberAndSentence.get(keysNumberAndSentence.get(j)));
                }
            }
        }
        Map<Integer, String> sortReferatIndicator;
        sortReferatIndicator = methodSupporting.sortByNumberOfSentence(referat);
        return sortReferatIndicator;
    }
    
    public Map<Integer, Float> getWeightOfSentences (Map<Integer, String> numberAndSentence, List<List<Word>> wordsInSentenceInInitialForm, List<List<String>> wordsInSentence, String markers ){
        List<Float> indicators = getWeightOfSentenceByIndicators(numberAndSentence);
        List<Float> markersWeight = getWeightOfSentenceByMarkers(wordsInSentenceInInitialForm, wordsInSentence, markers);
        List<Float> connectors = getWeightSentenceByConnectors(wordsInSentenceInInitialForm);
        for(int i = 0; i < numberAndSentence.size(); i++){
            numberAndWeightOfSentence.put(i, indicators.get(i) + markersWeight.get(i) + connectors.get(i));
        }
        Map<Integer, Float> sortWeightOfSentence = methodSupporting.sortByWeightOfSentences(numberAndWeightOfSentence);
        for (Entry<Integer, Float> entry : sortWeightOfSentence.entrySet()) {
            System.out.println("KeyW : " + entry.getKey() + " ValueW : " + entry.getValue());
        }
        return sortWeightOfSentence;
    }

    public List<Float> getWeightOfSentenceByIndicators(Map<Integer, String> numberAndSentence) {
        List<Float> weightOfSentenceByIndicators = new ArrayList<>();
        Text text = new Text();
        List<String> introductoryWords;
        String inputFile = "ВводныеСлова.txt";
        introductoryWords = text.readFromFile(inputFile);
        float weightSentence;
        List<String> numberAndSentenceValues = new ArrayList<>(numberAndSentence.values());
        
                
        for(int i = 0; i < numberAndSentenceValues.size(); i++){
            String lineToLowerCase = numberAndSentenceValues.get(i).toLowerCase();
            weightSentence = 0;
            for (int j = 0; j < introductoryWords.size(); j++) {
                Pattern pattern = Pattern.compile("\\b" + introductoryWords.get(j) + "\\b");
                Matcher matcher = pattern.matcher(lineToLowerCase);
                while(matcher.find()){
                    weightSentence = weightSentence + 1;
                    System.out.println(introductoryWords.get(j) + i);
                    System.out.println(lineToLowerCase);
                }
            }
        }
        return weightOfSentenceByIndicators;
    }

    public List<Float> getWeightOfSentenceByMarkers(List<List<Word>> wordsInSentenceInInitialForm, List<List<String>> wordsInSentence, String markers) {
        List<Float> weightOfSentenceByMarkers = new ArrayList<>();
       /* System.out.println("Введите через запятую ключевые слова: ");    
        Scanner in = new Scanner(System.in, encoding);
        String markers = in.nextLine();*/
        System.out.println("+++++ " + markers);
        if (markers.isEmpty()) {
            System.out.println("Вы не ввели ключевые слова");
            float emptyMarkers = 0;
            for (int i = 0; i < wordsInSentenceInInitialForm.size(); i++) {
                weightOfSentenceByMarkers.add(emptyMarkers);
            }
        } else {
            Pattern pattern = Pattern.compile(",? ");
            String[] listMarkers = pattern.split(markers);
            weightOfSentenceByMarkers = countWeightOfSentenceByMarkersAndConnectors(listMarkers, wordsInSentenceInInitialForm, wordsInSentence);
        }
        return weightOfSentenceByMarkers;
    }
    
    public List<Float> getWeightSentenceByConnectors(List<List<Word>> wordsInSentenceInInitialForm){
        List<Float> weightOfSentenceByConnectors = new ArrayList<>();
        System.out.println("Введите через точку с запятой группы синонимов: ");
        String encoding = System.getProperty("console.encoding", "windows-1251");
        Scanner in = new Scanner(System.in, encoding);
        String connectors = in.nextLine();
        if(connectors.isEmpty()){
            System.out.println("Вы не ввели синонимы");
            float emptyConnectors = 0;
            for(int i = 0; i < wordsInSentenceInInitialForm.size(); i++){
                weightOfSentenceByConnectors.add(emptyConnectors);
            }
        } else {
            Pattern pattern = Pattern.compile(",?;? ");
            String[] listConnectors = pattern.split(connectors);
            //weightOfSentenceByConnectors = countWeightOfSentenceByMarkersAndConnectors(listConnectors, wordsInSentenceInInitialForm);
        }
        return weightOfSentenceByConnectors;
    }

    public List<Float> countWeightOfSentenceByMarkersAndConnectors(String[] listWords, List<List<Word>> wordsInSentenceInInitialForm, List<List<String>>wordsInSentence) {
        List<Float> weightOfSentences = new ArrayList<>();
        String wordToLowerCase;
        List<Word> words = new ArrayList<>();
        String wordInInitialForm;
        List<Word> words1 = new ArrayList<>();
        for (int i = 0; i < listWords.length; i++) {
            wordToLowerCase = listWords[i].toLowerCase();
            Word temp = new Word(wordToLowerCase);
            words1.add(temp);
            List<String> wordsInInitialForm = jMorfSdk.getStringInitialForm(wordToLowerCase);
            if (!wordsInInitialForm.isEmpty()) {
                wordInInitialForm = wordsInInitialForm.get(0);
            } else {
                log.log(Level.WARNING, "В словаре отсутствует слово: " + wordToLowerCase + " или слово написано неправильно");
                wordInInitialForm = wordToLowerCase;
            }
            Word oneWord = new Word(wordInInitialForm);
            words.add(oneWord);
        }
        for (int i = 0; i < words.size(); i++) {
            System.out.println(i + " " + words.get(i));
        }
        System.out.println("Важен ли род?");
        String encoding = System.getProperty("console.encoding", "windows-1251");
        Scanner in = new Scanner(System.in, encoding);
        String answer = "да";
        float weightSentence;
        for (int i = 0; i < wordsInSentenceInInitialForm.size(); i++) {
            weightSentence = 0;
            for (int k = 0; k < wordsInSentenceInInitialForm.get(i).size(); k++) {
                for (int j = 0; j < words.size(); j++) {
                    if (wordsInSentenceInInitialForm.get(i).get(k).getWord().equals(words.get(j).getWord())) {
                        if (answer.equals("да")) {
                            System.out.println("wordsInSentence: " + wordsInSentence.get(i).get(k) + i);
                            long a = getRod(wordsInSentence.get(i).get(k));                
                            System.out.println("род: " + a);
                            System.out.println("marker: " + words1.get(j).getWord());
                            long b = getRod(words1.get(j).getWord());                           
                            System.out.println("род: " + b);                           
                            if (a == b) {
                                weightSentence = weightSentence + 1;
                            }
                        } else{
                        weightSentence = weightSentence + 1;
                        }
                    }
                }
            }
            weightOfSentences.add(weightSentence);
        }
        return weightOfSentences;
    }

    private long getRod(String wordToLowerCase) {
        OmoFormList omoForms = jMorfSdk.getAllCharacteristicsOfForm(wordToLowerCase);
        System.out.println("omoForms: " + omoForms);
        for(IOmoForm omoForm : omoForms) {
            if(omoForm.getTypeOfSpeech() == TypeOfSpeech.ADJECTIVEFULL || omoForm.getTypeOfSpeech() == TypeOfSpeech.ADJECTIVESHORT ) {              
                return omoForm.getTheMorfCharacteristics(MorfologyParameters.Gender.class);
            }
        }
        return 80000000;
      
    }
}

