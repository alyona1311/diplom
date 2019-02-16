/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package summarizaton;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ru.library.text.word.Word;

/**
 *
 * @author Kasatkina Alyona
 */
public class MethodPositionAndStatistic extends MethodPosition {

    private int sizePositionAndStatistic = 0;

    public Map<Integer, String> getReferat(Map<Integer, String> numberAndParagraphs, List<List<Word>> keyWordsInSentence, Map<Integer, String> numberAndSentance, int procentOfText) {
        Map<Integer, String> referatOfMethodPosition = new HashMap<>();
        referatOfMethodPosition = getReferat(numberAndParagraphs, numberAndSentance);

        Map<Integer, Float> weightOfSentenceStatistic = new HashMap<>();
        MethodStatistic mS = new MethodStatistic();
        weightOfSentenceStatistic = mS.getWeightOfSentences(keyWordsInSentence, numberAndSentance);

        int size = numberAndSentance.size();
        int sizeReferat = (int) ((size * procentOfText) / 100);

        int sizePosition = referatOfMethodPosition.size();
        Map<Integer, String> referat = new HashMap<>();
        if (sizeReferat > sizePosition) {
            sizePositionAndStatistic = sizeReferat - sizePosition;
            referat = getReferatMorePosition(sizePositionAndStatistic, referatOfMethodPosition, weightOfSentenceStatistic, numberAndSentance);
        } else {
            if (sizeReferat < sizePosition) {
                sizePositionAndStatistic = sizePosition - sizeReferat;
                referat = getReferatLessPosition(sizePositionAndStatistic, referatOfMethodPosition, weightOfSentenceStatistic);
            } else {
                if (sizeReferat == sizePosition) {
                    return referatOfMethodPosition;
                }
            }
        }
        Map<Integer, String> sortReferatPositionAndStatistic;
        sortReferatPositionAndStatistic = methodSupporting.sortByNumberOfSentence(referat);
        return sortReferatPositionAndStatistic;
    }

}
