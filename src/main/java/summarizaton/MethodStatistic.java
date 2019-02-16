/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package summarizaton;

import ru.library.text.word.Word;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Kasatkina Alyona
 */
public class MethodStatistic {

    private final Map<Integer, Float> numberAndWeightOfSentence = new HashMap<>();
    private final MethodSupporting methodSupporting = new MethodSupporting();

    public Map<Integer, String> getReferat(List<List<Word>> keyWordsInSentence, Map<Integer, String> numberAndSentence, int procentOfText) {
        Map<Integer, Float> weightOfSentence;
        weightOfSentence = getWeightOfSentences(keyWordsInSentence, numberAndSentence);
        List<Integer> keysNumberAndSentence = new ArrayList<>(numberAndSentence.keySet());
        List<Integer> keysWeightOfSentence = new ArrayList<>(weightOfSentence.keySet());
        Map<Integer, String> referat = new HashMap<>();
        int size = numberAndSentence.size();
        int sizeReferat = (int) ((size * procentOfText) / 100);
        for (int i = 0; i < sizeReferat; i++) {
            for (int j = 0; j < keysNumberAndSentence.size(); j++) {
                if (keysWeightOfSentence.get(i) == keysNumberAndSentence.get(j)) {
                    referat.put(keysWeightOfSentence.get(i), numberAndSentence.get(keysNumberAndSentence.get(j)));
                }
            }
        }
        Map<Integer, String> sortReferatStatistic;
        sortReferatStatistic = methodSupporting.sortByNumberOfSentence(referat);
        return sortReferatStatistic;
    }

    public Map<Integer, Float> getWeightOfSentences(List<List<Word>> keyWordsInSentence, Map<Integer, String> numberAndSentence) {
        List<Integer> keysNumberAndSentance = new ArrayList<>(numberAndSentence.keySet());
        float weightSentence;
        for (int i = 0; i < keyWordsInSentence.size(); i++) {
            weightSentence = 0;
            for (int j = 0; j < keyWordsInSentence.get(i).size(); j++) {
                weightSentence = weightSentence + keyWordsInSentence.get(i).get(j).getScope();
            }
            numberAndWeightOfSentence.put(keysNumberAndSentance.get(i), weightSentence);
        }
        Map<Integer, Float> sortWeightSentence;
        sortWeightSentence = methodSupporting.sortByWeightOfSentences(numberAndWeightOfSentence);

        return sortWeightSentence;
    }

}
