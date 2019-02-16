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


/**
 *
 * @author Kasatkina Alyona
 */
public class MethodPosition {

    protected MethodSupporting methodSupporting = new MethodSupporting();

    public Map<Integer, String> getReferat(Map<Integer, String> numberAndParagraphs, Map<Integer, String> numberAndSentence) {
      //  Sentence sentence = new Sentence();
        Text text = new Text();
       // sentence.extractSentencesFromText(numberAndParagraphs);
       text.splitTextBySentences(numberAndParagraphs);
        List<List<Integer>> sentenceInParagraph = text.getSentencesInParagraph();

        Map<Integer, String> referat = new HashMap<>();
        for (int i = 0; i < sentenceInParagraph.size(); i++) {
            if (sentenceInParagraph.get(i).size() > 1) {
                referat.put(sentenceInParagraph.get(i).get(0), numberAndSentence.get(sentenceInParagraph.get(i).get(0)));
                referat.put(sentenceInParagraph.get(i).get(sentenceInParagraph.get(i).size() - 1), numberAndSentence.get(sentenceInParagraph.get(i).get(sentenceInParagraph.get(i).size() - 1)));
            } else {
                referat.put(sentenceInParagraph.get(i).get(0), numberAndSentence.get(sentenceInParagraph.get(i).get(0)));
            }
        }

        Map<Integer, String> sortReferatPosition;
        sortReferatPosition = methodSupporting.sortByNumberOfSentence(referat);
        return sortReferatPosition;
    }

    protected Map<Integer, String> getReferatMorePosition(int sizePositAndStat, Map<Integer, String> referatOfMethodPosition, Map<Integer, Float> weightOfSentence, Map<Integer, String> numberAndSentence) {
        List<Integer> keysStatistic = new ArrayList<>(weightOfSentence.keySet());
        for (int i = 0; i < keysStatistic.size(); i++) {
            if (!referatOfMethodPosition.containsKey(keysStatistic.get(i)) && sizePositAndStat != 0) {
                referatOfMethodPosition.put(keysStatistic.get(i), numberAndSentence.get(keysStatistic.get(i)));
                sizePositAndStat--;
            }
        }
        return referatOfMethodPosition;
    }

    protected Map<Integer, String> getReferatLessPosition(int sizePositAndStat, Map<Integer, String> referatOfMethodPosition, Map<Integer, Float> weightOfSentence) {
        List<Integer> keysStatistic = new ArrayList<>(weightOfSentence.keySet());
        for (int i = 1; i <= keysStatistic.size(); i++) {
            if (referatOfMethodPosition.containsKey(keysStatistic.get(keysStatistic.size() - i)) && sizePositAndStat != 0) {
                referatOfMethodPosition.remove(keysStatistic.get(keysStatistic.size() - i));
                sizePositAndStat--;
            }
        }
        return referatOfMethodPosition;
    }

}
