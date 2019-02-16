/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package summarizaton;

import ru.library.text.word.Word;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Андрей
 */
public class Text {
     private final Logger log = Logger.getLogger(Text.class.getName());
     private final Map<Integer, String> numberAndSentence = new HashMap<>();
     private final Map<Integer, String> numberAndParagraphs = new HashMap<>();
     private final List<List<Integer>> sentenceInParagraph = new ArrayList<>();
     private final List<Word> wordsInText = new ArrayList<>();

    public List<String> readFromFile(String inputFile) {
        BufferedReader reader;
        String line;
        List<String> allText = new ArrayList<>();
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile), "windows-1251"));
            while ((line = reader.readLine()) != null) {
                if (!line.trim().equals("")) {
                    allText.add(line);
                }
            }
        } catch (FileNotFoundException ex) {
            log.log(Level.SEVERE, "File not found. Name of file: " + inputFile, ex);
            ex.printStackTrace();
        } catch (IOException ex) {
            log.log(Level.SEVERE, "Could not open file. Name of file: " + inputFile, ex);
            ex.printStackTrace();
        }
        return allText;
    }

    public Map<Integer, String> splitTextByParagraphs(List<String> allText) {
        //Map<Integer, String> numberAndParagraphs = new HashMap<>();
        for (int i = 0; i < allText.size(); i++) {
            numberAndParagraphs.put(i, allText.get(i));
        }
        return numberAndParagraphs;
    }

    public Map<Integer, String> splitTextBySentences(Map<Integer, String> numberAndParagraphs){
        List<String> sentenceInText = new ArrayList<>();
        Pattern pattern = Pattern.compile("([А-Я*]|[\\d*]|[—]).+[\\. |! |\\? |\\... ]");
        // Pattern pattern = Pattern.compile("([А-Я]|[\\d*]).+(([А-Я]\\.)+\\s[А-Я].+)?[\\. |\\! |\\? | \\... ]");
        String[] sentencesArray;
        int counter = 0;
        for (int i = 0; i < numberAndParagraphs.size(); i++) {
            Matcher matcher = pattern.matcher(numberAndParagraphs.get(i));
            sentenceInParagraph.add(new ArrayList<>());
            while (matcher.find()) {
                sentencesArray = numberAndParagraphs.get(i).split("(?<=\\. |! |\\? | \\... )");
                if (sentencesArray.length > 1) {
                    for (int j = 0; j < sentencesArray.length; j++) {
                        sentenceInText.add(sentencesArray[j]);
                        sentenceInParagraph.get(i).add(counter);
                        counter++;
                    }
                } else {
                    sentenceInText.add(numberAndParagraphs.get(i));
                    sentenceInParagraph.get(i).add(counter);
                    counter++;
                }
            }
        }
        for (int i = 0; i < sentenceInText.size(); i++) {
            String sentenceInTextReplace = sentenceInText.get(i).replaceAll(" +", " ");
            numberAndSentence.put(i, sentenceInTextReplace);
        }
        return numberAndSentence;
        
    }
    
    public List<List<Integer>> getSentencesInParagraph(){
        return sentenceInParagraph;
    }
    
    public List<Word> splitTextByWords(List<List<Word>> wordsInSentenceInInitialForm){
        for (int i = 0; i < wordsInSentenceInInitialForm.size(); i++) {
            for (int j = 0; j < wordsInSentenceInInitialForm.get(i).size(); j++) {
                wordsInText.add(wordsInSentenceInInitialForm.get(i).get(j));
            }
        }
        return wordsInText;
    }

    
}
