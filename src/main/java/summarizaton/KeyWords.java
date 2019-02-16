/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package summarizaton;

import ru.library.methods.frequency.RangFrequency;
import ru.library.methods.intems.Intems;
import ru.library.methods.textrank.TextRank;
import ru.library.methods.textrank.TextRankWithIDF;
import ru.library.methods.textrank.TextRankWithMultiplyWindows;
import ru.library.methods.tfidf.TFIDF;
import ru.library.text.word.Word;
import ru.library.utils.filters.TypeSpeechFilter;
import ru.library.utils.filters.TypeSpeechFilterImp;
import ru.textanalysis.tfwwt.jmorfsdk.jmorfsdk.JMorfSdk;
import ru.textanalysis.tfwwt.jmorfsdk.jmorfsdk.load.JMorfSdkLoad;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Список ключевых слов текста
 *
 * @author Kasatkina Alyona
 */
public class KeyWords {

    private final List<Word> keyWords = new ArrayList<>();

    public List<Word> extractKeyWords(List<Word> words, int numberKeyWords, String inputFile) {

        Intems intems = new Intems();
        List<Word> keyWordsIntem = new ArrayList<>();
        try {
            keyWordsIntem = intems.extractKeywords(words, numberKeyWords);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        RangFrequency rangFrequency = new RangFrequency();
        List<Word> keyWordsRF = new ArrayList<>();
        keyWordsRF = rangFrequency.extractKeywords(words, numberKeyWords);

        TextRank textRank = new TextRank();
        List<Word> keyWordsTR = new ArrayList<>();
        try {
            keyWordsTR = textRank.extractKeywords(words, numberKeyWords);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        TextRankWithMultiplyWindows textRankWMW = new TextRankWithMultiplyWindows();
        List<Word> keyWordsTrWMW = new ArrayList<>();
        try {
            keyWordsTrWMW = textRankWMW.extractKeywords(words, numberKeyWords);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        TextRankWithIDF textRankIDF = new TextRankWithIDF();
        HashMap<String, List<Word>> documents = new HashMap<String, List<Word>>();
        Map<String, List<Word>> mapTextRankIDF;
        List<Word> keyWordsTextRankIDFResult = new ArrayList<>();

        List<Word> words1 = new ArrayList<>();
        try {
            documents.put(inputFile, words);
            documents.put("", words1);
            mapTextRankIDF = textRankIDF.extractKeywords(documents, numberKeyWords);
            List<List<Word>> keyWordsTextRankIDF = new ArrayList<>(mapTextRankIDF.values());
            for (int i = 0; i < keyWordsTextRankIDF.size(); i++) {
                keyWordsTextRankIDFResult = keyWordsTextRankIDF.get(i);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        TFIDF tfidf = new TFIDF();
        Map<String, List<Word>> mapTFIDF = new HashMap<String, List<Word>>();
        List<Word> keyWordsTFIDFResult = new ArrayList<>();
        try {
            documents.put(inputFile, words);
            documents.put("", words1);
            mapTFIDF = tfidf.extractKeywords(documents, numberKeyWords);
            List<List<Word>> keyWordsTFIDF = new ArrayList<>(mapTFIDF.values());
            for (int i = 0; i < keyWordsTFIDF.size(); i++) {
                keyWordsTFIDFResult = keyWordsTFIDF.get(i);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        List<Word> keyWordsAll = new ArrayList<>();
        keyWordsAll.addAll(keyWordsIntem);
        keyWordsAll.addAll(keyWordsRF);
        keyWordsAll.addAll(keyWordsTR);
        keyWordsAll.addAll(keyWordsTrWMW);
        keyWordsAll.addAll(keyWordsTextRankIDFResult);
        keyWordsAll.addAll(keyWordsTFIDFResult);

        List<Byte> partOfSpeech;
        Byte oneWordPartOfSpeech;
        JMorfSdk jMorfSdk = JMorfSdkLoad.loadFullLibrary();
        for (int i = 0; i < keyWordsAll.size(); i++) {
            try {
                partOfSpeech = jMorfSdk.getTypeOfSpeechs(keyWordsAll.get(i).getWord());
                oneWordPartOfSpeech = partOfSpeech.get(0);
                keyWordsAll.get(i).setPartOfSpeech(oneWordPartOfSpeech);
            } catch (Exception ex) {
                System.out.println("нет части речи у слова: " + keyWordsAll.get(i));
                ex.printStackTrace();
            }
        }
        TypeSpeechFilter filter = new TypeSpeechFilterImp();
        keyWordsAll = filter.getNounsAndAdj(keyWordsAll);

        Map<String, Integer> mapKeyWordsAll = new HashMap<>();
        for (int i = 0; i < keyWordsAll.size(); i++) {
            Word oneKeyWord = keyWordsAll.get(i);
            if (!mapKeyWordsAll.containsKey(oneKeyWord.getWord())) {
                mapKeyWordsAll.put(oneKeyWord.getWord(), 1);
            } else {
                mapKeyWordsAll.put(oneKeyWord.getWord(), mapKeyWordsAll.get(oneKeyWord.getWord()) + 1);
            }
        }

        Map<String, Float> mapKeyWordsAllWeight = new HashMap<>();
        for (int i = 0; i < keyWordsAll.size(); i++) {
            Word oneKeyWordWeight = keyWordsAll.get(i);
            if (!mapKeyWordsAllWeight.containsKey(oneKeyWordWeight.getWord())) {
                mapKeyWordsAllWeight.put(oneKeyWordWeight.getWord(), oneKeyWordWeight.getScope());
            } else {
                mapKeyWordsAllWeight.put(oneKeyWordWeight.getWord(), mapKeyWordsAllWeight.get(oneKeyWordWeight.getWord()) + oneKeyWordWeight.getScope());
            }
        }

        MethodSupporting methodSupporting = new MethodSupporting();
        List<Integer> keysMapSentenceAll = new ArrayList<>(mapKeyWordsAll.values());
        int thresholdValue = methodSupporting.getListThresholdValues(keysMapSentenceAll).get(0) - 1;

        List<Word> keyWordsThresholdValue = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : mapKeyWordsAll.entrySet()) {
            if (entry.getValue() > thresholdValue) {
                Word kwWord = new Word(entry.getKey());
                keyWordsThresholdValue.add(kwWord);
            }
        }

        for (Map.Entry<String, Float> entry : mapKeyWordsAllWeight.entrySet()) {
            for (int i = 0; i < keyWordsThresholdValue.size(); i++) {
                Word oneKeyWord = new Word(entry.getKey());
                if (oneKeyWord.getWord().compareTo(keyWordsThresholdValue.get(i).getWord()) == 0) {
                    oneKeyWord.setScope(entry.getValue());
                    keyWords.add(oneKeyWord);
                }
            }
        }
        return keyWords;
    }
}
