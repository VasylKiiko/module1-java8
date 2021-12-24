package com.epam.cdp.m2.hw2.aggregator;

import javafx.util.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class Java8Aggregator implements Aggregator {

    @Override
    public int sum(List<Integer> numbers) {
        return numbers.stream().reduce(0, Integer::sum);
    }

    @Override
    public List<Pair<String, Long>> getMostFrequentWords(List<String> words, long limit) {
        return words.stream()
                .collect(Collectors.groupingBy(word -> word, HashMap::new, Collectors.counting())) // create frequency map
                .entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .limit(limit)
                .map(entry -> new Pair<>(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getDuplicates(List<String> words, long limit) {
        Set<String> uniqueValues = new HashSet<>();
        return words.stream()
                .map(String::toUpperCase)
                .filter(word -> !uniqueValues.add(word))
                .sorted(Comparator.comparing(String::length).thenComparing(String::compareTo))
                .limit(limit)
                .collect(Collectors.toList());
    }
}