package com.epam.cdp.m2.hw2.aggregator;

import java.util.*;
import java.util.stream.Collectors;

import javafx.util.Pair;

public class Java8ParallelAggregator implements Aggregator {

    @Override
    public int sum(List<Integer> numbers) {
        return numbers.parallelStream().reduce(0, Integer::sum);
    }

    @Override
    public List<Pair<String, Long>> getMostFrequentWords(List<String> words, long limit) {
        return words.parallelStream()
                .collect(Collectors.toConcurrentMap(word -> word, word -> 1L, Long::sum))
                .entrySet().parallelStream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .sequential()
                .limit(limit)
                .map(entry -> new Pair<>(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getDuplicates(List<String> words, long limit) {
        Set<String> uniqueValues = Collections.synchronizedSet(new HashSet<>());
        return words.parallelStream()
                .map(String::toUpperCase)
                .filter(word -> !uniqueValues.add(word))
                .sorted(Comparator.comparing(String::length).thenComparing(String::compareTo))
                .sequential()
                .limit(limit)
                .collect(Collectors.toList());
    }
}
