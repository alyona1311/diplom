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
public class MethodPositionAndSymmetric extends MethodPosition {

    private int sizePositionAndSymm = 0;

    public Map<Integer, String> getReferat(Map<Integer, String> numberAndParagraphs, List<List<Word>> keyWordsInSentence, Map<Integer, String> numberAndSentence, int procentOfText) {
        Map<Integer, String> referatOfMethodPosition;
        referatOfMethodPosition = getReferat(numberAndParagraphs, numberAndSentence);

        Map<Integer, Float> weightOfSentenceSymmetric;
        MethodSymmetric mP = new MethodSymmetric();
        weightOfSentenceSymmetric = mP.getWeightOfSentences(keyWordsInSentence, numberAndSentence);

        int size = numberAndSentence.size();
        int sizeReferat = (int) ((size * procentOfText) / 100);
        int sizePosition = referatOfMethodPosition.size();

        Map<Integer, String> referat = new HashMap<>();
        if (sizeReferat > sizePosition) {
            sizePositionAndSymm = sizeReferat - sizePosition;
            referat = getReferatMorePosition(sizePositionAndSymm, referatOfMethodPosition, weightOfSentenceSymmetric, numberAndSentence);
        } else {
            if (sizeReferat < sizePosition) {
                sizePositionAndSymm = sizePosition - sizeReferat;
                referat = getReferatLessPosition(sizePositionAndSymm, referatOfMethodPosition, weightOfSentenceSymmetric);
            } else {
                if (sizeReferat == sizePosition) {
                    return referatOfMethodPosition;
                }
            }
        }
        Map<Integer, String> sortReferatPositionAndSymmetric;
        sortReferatPositionAndSymmetric = methodSupporting.sortByNumberOfSentence(referat);
        return sortReferatPositionAndSymmetric;
    }
}
