/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package summarizaton;

import ru.library.text.word.Word;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Kasatkina Alyona
 */
public class MethodsOfSummarization {

    public Map<Integer, String> getNumberAndParagraphs(String inputFile) {
        Text text = new Text();
        List<String> allText = text.readFromFile(inputFile);
        Map<Integer, String> numberAndParagraphs = text.splitTextByParagraphs(allText);
        return numberAndParagraphs;
    }

    public Map<Integer, String> getNumberAndSentence(Map<Integer, String> numberAndParagraphs) {
        Text text = new Text();
        Map<Integer, String> numberAndSentence = text.splitTextBySentences(numberAndParagraphs);
        return numberAndSentence;
    }

    public List<List<Word>> getKeyWordsInSentence(String inputFile, Map<Integer, String> numberAndSentence) {
        Sentence sentence = new Sentence();
        List<List<String>> wordsInSentence = sentence.splitSentenceByWords(numberAndSentence);
        List<List<Word>> wordsInSentenceInInitialForm = sentence.splitSentenceByWordsInInitialForm(wordsInSentence);
        
        Text text = new Text();
        List<Word> words = text.splitTextByWords(wordsInSentenceInInitialForm);//Word

        KeyWords keyWord = new KeyWords();
        int numberOfKeyWords = 10;
        List<Word> keyWords = keyWord.extractKeyWords(words, numberOfKeyWords, inputFile);

        List<List<Word>> keyWordsInSentence = sentence.extractKeyWordsInSentence(wordsInSentenceInInitialForm, keyWords);//Word
        return keyWordsInSentence;
    }

    public Map<Integer, String> getReferatOfMethodStatistic(String inputFile, int procentOfText) {
        Map<Integer, String> numberAndParagraphsSt = getNumberAndParagraphs(inputFile);
        Map<Integer, String> numberAndSentenceSt = getNumberAndSentence(numberAndParagraphsSt);
        List<List<Word>> keyWordsInSentenceSt = getKeyWordsInSentence(inputFile, numberAndSentenceSt);

        MethodStatistic methodStatistic = new MethodStatistic();
        Map<Integer, String> referatOfMethodStatistic = methodStatistic.getReferat(keyWordsInSentenceSt, numberAndSentenceSt, procentOfText);
        return referatOfMethodStatistic;
    }

    public Map<Integer, String> getReferatOfMethodSymmetric(String inputFile, int procentOfText) {
        Map<Integer, String> numberAndParagraphsSm = getNumberAndParagraphs(inputFile);
        Map<Integer, String> numberAndSentenceSm = getNumberAndSentence(numberAndParagraphsSm);
        List<List<Word>> keyWordsInSentenceSm = getKeyWordsInSentence(inputFile, numberAndSentenceSm);

        MethodSymmetric methodSimmetr = new MethodSymmetric();
        Map<Integer, String> referatOfMethodSimmetr = methodSimmetr.getReferat(keyWordsInSentenceSm, numberAndSentenceSm, procentOfText);
        return referatOfMethodSimmetr;
    }

    public Map<Integer, String> getReferatOfMethodPosition(String inputFile) {
        Map<Integer, String> numberAndParagraphsPos = getNumberAndParagraphs(inputFile);
        Map<Integer, String> numberAndSentencePos = getNumberAndSentence(numberAndParagraphsPos);

        MethodPosition methodPosition = new MethodPosition();
        Map<Integer, String> referatOfMethodPosition = methodPosition.getReferat(numberAndParagraphsPos, numberAndSentencePos);
        return referatOfMethodPosition;
    }

    public Map<Integer, String> getReferatOfMethodPositionAndStatistic(String inputFile, int procentOfText) {
        Map<Integer, String> numberAndParagraphsPosSt = getNumberAndParagraphs(inputFile);
        Map<Integer, String> numberAndSentencePosSt = getNumberAndSentence(numberAndParagraphsPosSt);
        List<List<Word>> keyWordsInSentencePosSt = getKeyWordsInSentence(inputFile, numberAndSentencePosSt);

        MethodPositionAndStatistic methodPositionAndStatistic = new MethodPositionAndStatistic();
        Map<Integer, String> referatOfMethodPositionAndStatistic = methodPositionAndStatistic.getReferat(numberAndParagraphsPosSt, keyWordsInSentencePosSt, numberAndSentencePosSt, procentOfText);
        return referatOfMethodPositionAndStatistic;
    }

    public Map<Integer, String> getReferatOfMethodPositionAndSymmetric(String inputFile, int procentOfText) {
        Map<Integer, String> numberAndParagraphsPosSm = getNumberAndParagraphs(inputFile);
        Map<Integer, String> numberAndSentencePosSm = getNumberAndSentence(numberAndParagraphsPosSm);
        List<List<Word>> keyWordsInSentencePosSm = getKeyWordsInSentence(inputFile, numberAndSentencePosSm);

        MethodPositionAndSymmetric methodPositionAndSimmetr = new MethodPositionAndSymmetric();
        Map<Integer, String> referatOfMethodPositionAndStatistic = methodPositionAndSimmetr.getReferat(numberAndParagraphsPosSm, keyWordsInSentencePosSm, numberAndSentencePosSm, procentOfText);
        return referatOfMethodPositionAndStatistic;
    }

    public Map<Integer, String> getReferatOfMethodIntegration(String inputFile, int procentOfText) {
        Map<Integer, String> numberAndParagraphsIn = getNumberAndParagraphs(inputFile);
        Map<Integer, String> numberAndSentenceIn = getNumberAndSentence(numberAndParagraphsIn);
        List<List<Word>> keyWordsInSentenceIn = getKeyWordsInSentence(inputFile, numberAndSentenceIn);

        MethodIntegration methodIntegration = new MethodIntegration();
        Map<Integer, String> referatOfMethodIntegration = methodIntegration.getReferat(numberAndParagraphsIn, keyWordsInSentenceIn, numberAndSentenceIn, procentOfText);
        return referatOfMethodIntegration;
    }

    public Map<Integer, String> getReferatOfMethodIntegrationRandom(String inputFile, int procentOfText) {
        Map<Integer, String> numberAndParagraphsInRan = getNumberAndParagraphs(inputFile);
        Map<Integer, String> numberAndSentenceInRan = getNumberAndSentence(numberAndParagraphsInRan);
        List<List<Word>> keyWordsInSentenceInRan = getKeyWordsInSentence(inputFile, numberAndSentenceInRan);

        MethodIntegrationRandom methodIntegrationRandom = new MethodIntegrationRandom();
        Map<Integer, String> referatOfMethodIntegration = methodIntegrationRandom.getReferat(numberAndParagraphsInRan, keyWordsInSentenceInRan, numberAndSentenceInRan, procentOfText);
        return referatOfMethodIntegration;
    }
}
