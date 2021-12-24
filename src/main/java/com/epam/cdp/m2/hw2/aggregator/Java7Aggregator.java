package com.epam.cdp.m2.hw2.aggregator;

import java.util.*;

import javafx.util.Pair;

public class Java7Aggregator implements Aggregator {

    @Override
    public int sum(List<Integer> numbers) {
        int sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        return sum;
    }

    @Override
    public List<Pair<String, Long>> getMostFrequentWords(List<String> words, long limit) {
        Map<String, Long> frequencyMap = new HashMap<>();
        // count words frequency
        for (String word : words) {
            if (frequencyMap.containsKey(word)) {
                frequencyMap.put(word, frequencyMap.get(word) + 1L);
            } else {
                frequencyMap.put(word, 1L);
            }
        }

        List<Pair<String, Long>> frequencyList = new ArrayList<>();
        for (Map.Entry<String, Long> entry : frequencyMap.entrySet()) {
            frequencyList.add(new Pair<>(entry.getKey(), entry.getValue()));
        }

        frequencyList.sort((pair1, pair2) -> (int) (pair2.getValue() - pair1.getValue()));
        return limit < frequencyList.size() ? frequencyList.subList(0, (int) limit)
                : frequencyList.subList(0, frequencyList.size());
    }

    @Override
    public List<String> getDuplicates(List<String> words, long limit) {
        Set<String> uniqueValues = new HashSet<>();
        List<String> duplicatesList = new ArrayList<>();

        // find unique words
        for (String word : words) {
            if (uniqueValues.contains(word.toUpperCase(Locale.ROOT))) {
                duplicatesList.add(word.toUpperCase(Locale.ROOT));
            } else {
                uniqueValues.add(word.toUpperCase(Locale.ROOT));
            }
        }

        int limitIndex = limit > duplicatesList.size() ? duplicatesList.size() : (int) limit;
        // sort values according to task (?based on tests?)
        duplicatesList.sort((str1, str2) -> {
            if (str1.length() == str2.length()) {
                return str1.compareTo(str2);
            }
            return str1.length() - str2.length();
        });

        return duplicatesList.subList(0, limitIndex);
    }

}
