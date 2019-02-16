/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package summarizaton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ru.library.text.word.Word;

/**
 *
 * @author Kasatkina Alyona
 */
public class MethodSymmetric {

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
        Map<Integer, String> sortReferatSimmetr = new HashMap<>();
        sortReferatSimmetr = methodSupporting.sortByNumberOfSentence(referat);
        return sortReferatSimmetr;
    }

    private List<List<Word>> countWeightOfKeyWords(List<List<Word>> keyWordsInSentence) {
        Map<Word, Float> mapKeyWordsWeight = new HashMap<>();
        float weight = 0;
        for (int i = 0; i < keyWordsInSentence.size(); i++) {
            for (int j = 0; j < keyWordsInSentence.get(i).size(); j++) {
                if (!mapKeyWordsWeight.containsKey(keyWordsInSentence.get(i).get(j))) {
                    mapKeyWordsWeight.put(keyWordsInSentence.get(i).get(j), weight);
                } else {
                    mapKeyWordsWeight.put(keyWordsInSentence.get(i).get(j), mapKeyWordsWeight.get(keyWordsInSentence.get(i).get(j)) + 1);
                }
            }
        }
        for (Map.Entry<Word, Float> entry : mapKeyWordsWeight.entrySet()) {
            for (int i = 0; i < keyWordsInSentence.size(); i++) {
                for (int j = 0; j < keyWordsInSentence.get(i).size(); j++) {
                    if (entry.getKey().getWord().compareTo(keyWordsInSentence.get(i).get(j).getWord()) == 0) {
                        keyWordsInSentence.get(i).get(j).setScope(entry.getValue());
                    }
                }
            }
        }
        return keyWordsInSentence;
    }

    public Map<Integer, Float> getWeightOfSentences(List<List<Word>> keyWordsInSentence, Map<Integer, String> numberAndSentence) {
        List<List<Word>> keyWordsInSentenceSymmetric;
        keyWordsInSentenceSymmetric = countWeightOfKeyWords(keyWordsInSentence);
        List<Integer> keysNumberAndSentance = new ArrayList<>(numberAndSentence.keySet());
        float weightSentence;
        for (int i = 0; i < keyWordsInSentenceSymmetric.size(); i++) {
            weightSentence = 0;
            for (int j = 0; j < keyWordsInSentenceSymmetric.get(i).size(); j++) {
                weightSentence = weightSentence + keyWordsInSentenceSymmetric.get(i).get(j).getScope();
            }
            numberAndWeightOfSentence.put(keysNumberAndSentance.get(i), weightSentence);
        }
        Map<Integer, Float> sortWeightSentence;
        sortWeightSentence = methodSupporting.sortByWeightOfSentences(numberAndWeightOfSentence);
        return sortWeightSentence;
    }

}
