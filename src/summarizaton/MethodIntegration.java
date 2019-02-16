/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package summarizaton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ru.library.text.word.Word;

/**
 *
 * @author Kasatkina Alyona
 */
public class MethodIntegration {

    private final MethodSupporting methodSupporting = new MethodSupporting();

    public Map<Integer, String> getReferat(Map<Integer, String> numberAndParagraphs, List<List<Word>> keyWordsInSentence, Map<Integer, String> numberAndSentence, int procentOfText) {
        MethodStatistic methodStatistic = new MethodStatistic();
        Map<Integer, String> referatOfMethodStatistic;
        referatOfMethodStatistic = methodStatistic.getReferat(keyWordsInSentence, numberAndSentence, procentOfText);

        MethodSymmetric methodSymmetric = new MethodSymmetric();
        Map<Integer, String> referatOfMethodSimmetr;
        referatOfMethodSimmetr = methodSymmetric.getReferat(keyWordsInSentence, numberAndSentence, procentOfText);

        MethodPosition methodPosition = new MethodPosition();
        Map<Integer, String> referatOfMethodPosition;
        referatOfMethodPosition = methodPosition.getReferat(numberAndParagraphs, numberAndSentence);

        MethodPositionAndStatistic methodPositionAnadStatistic = new MethodPositionAndStatistic();
        Map<Integer, String> referatOfMethodPositionAndStatistic;
        referatOfMethodPositionAndStatistic = methodPositionAnadStatistic.getReferat(numberAndParagraphs, keyWordsInSentence, numberAndSentence, procentOfText);

        MethodPositionAndSymmetric methodPositionAndSymmetric = new MethodPositionAndSymmetric();
        Map<Integer, String> referatOfMethodPositionAndSimmetr;
        referatOfMethodPositionAndSimmetr = methodPositionAndSymmetric.getReferat(numberAndParagraphs, keyWordsInSentence, numberAndSentence, procentOfText);

        List<Integer> sentenceInStatistic = new ArrayList<>(referatOfMethodStatistic.keySet());
        List<Integer> sentenceInSymmetric = new ArrayList<>(referatOfMethodSimmetr.keySet());
        List<Integer> sentenceInPosition = new ArrayList<>(referatOfMethodPosition.keySet());
        List<Integer> sentenceInPositionAndStatistic = new ArrayList<>(referatOfMethodPositionAndStatistic.keySet());
        List<Integer> sentenceInPositionAndSymmetric = new ArrayList<>(referatOfMethodPositionAndSimmetr.keySet());

        List<Integer> sentenceOfAllMethod = new ArrayList<>();
        sentenceOfAllMethod.addAll(sentenceInStatistic);
        sentenceOfAllMethod.addAll(sentenceInSymmetric);
        sentenceOfAllMethod.addAll(sentenceInPosition);
        sentenceOfAllMethod.addAll(sentenceInPositionAndStatistic);
        sentenceOfAllMethod.addAll(sentenceInPositionAndSymmetric);

        Map<Integer, Integer> mapSentenceAll = new HashMap<>();
        for (int i = 0; i < sentenceOfAllMethod.size(); i++) {
            int oneSentence = sentenceOfAllMethod.get(i);
            if (!mapSentenceAll.containsKey(oneSentence)) {
                mapSentenceAll.put(oneSentence, 1);
            } else {
                mapSentenceAll.put(oneSentence, mapSentenceAll.get(oneSentence) + 1);
            }
        }
        List<Integer> keysMapSentenceAll = new ArrayList<>(mapSentenceAll.values());
        List<Integer> thresholdValues = methodSupporting.getListThresholdValues(keysMapSentenceAll);
        int thresholdValue = thresholdValues.get(0) - 1;

        Map<Integer, String> referat = new HashMap<>();
        for (Map.Entry<Integer, Integer> entry : mapSentenceAll.entrySet()) {
            if (entry.getValue() > thresholdValue) {
                int oneSentence = entry.getKey();
                referat.put(oneSentence, numberAndSentence.get(oneSentence));
            }
        }

        int size = numberAndSentence.size();
        int sizeReferat = (int) ((size * procentOfText) / 100);
        int sizeIntegration;
        if (referat.size() > sizeReferat) {
            sizeIntegration = referat.size() - sizeReferat;
            referat = getReferatLessIntegration(referat, sizeIntegration, thresholdValues, mapSentenceAll);
        } else {
            if (referat.size() < sizeReferat) {
                sizeIntegration = sizeReferat - referat.size();
                for (int i = 0; i < thresholdValues.size(); i++) {
                    if (thresholdValues.get(i) > thresholdValue) {
                        thresholdValues.remove(Integer.valueOf(thresholdValues.get(i)));
                    }
                }
                referat = getReferatMoreIntegration(referat, sizeIntegration, thresholdValues, mapSentenceAll, numberAndSentence);
            }
        }
        Map<Integer, String> sortReferatIntegration;
        sortReferatIntegration = methodSupporting.sortByNumberOfSentence(referat);
        return sortReferatIntegration;
    }

    private Map<Integer, String> getReferatLessIntegration(Map<Integer, String> referat, int sizeIntegration, List<Integer> thresholdValues, Map<Integer, Integer> mapSentenceAll) {
        Collections.sort(thresholdValues);
        for (int j = 0; j < thresholdValues.size(); j++) {
            for (Map.Entry<Integer, Integer> entry : mapSentenceAll.entrySet()) {
                if (entry.getValue() == thresholdValues.get(j) && sizeIntegration != 0) {
                    int oneKey = entry.getKey();
                    if (referat.containsKey(oneKey)) {
                        referat.remove(oneKey);
                        sizeIntegration--;
                    }
                }
            }
        }
        return referat;
    }

    private Map<Integer, String> getReferatMoreIntegration(Map<Integer, String> referat, int sizeIntegration, List<Integer> thresholdValues, Map<Integer, Integer> mapSentenceAll, Map<Integer, String> numberAndSentence) {
        List<Integer> keysReferat = new ArrayList<>(referat.keySet());
        for (int i = 0; i < keysReferat.size(); i++) {
            mapSentenceAll.remove(keysReferat.get(i));
        }
        Collections.sort(thresholdValues, Collections.reverseOrder());
        for (int j = 0; j < thresholdValues.size(); j++) {
            for (Map.Entry<Integer, Integer> entry : mapSentenceAll.entrySet()) {
                if (entry.getValue() == thresholdValues.get(j) && sizeIntegration != 0) {
                    int oneKey = entry.getKey();
                    referat.put(oneKey, numberAndSentence.get(oneKey));
                    sizeIntegration--;
                }
            }
        }
        return referat;
    }
}
