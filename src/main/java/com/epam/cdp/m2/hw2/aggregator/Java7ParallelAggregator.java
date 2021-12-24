package com.epam.cdp.m2.hw2.aggregator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import javafx.util.Pair;

public class Java7ParallelAggregator implements Aggregator {

    @Override
    public int sum(List<Integer> numbers) {
//        // just for example using 2 threads
//        int numOfThreads = 2;
//
//        ExecutorService executorService = Executors.newFixedThreadPool(numOfThreads);
//        List<Future<Integer>> results = new ArrayList<>();
//
//        //dynamic dividing can be implemented with large for+id construction, so
//        // I decided just to hardcode splitting list into 2 parts
//        results.add(executorService.submit(new CallableCalculator(numbers.subList(0, numbers.size() / 2))));
//        results.add(executorService.submit(new CallableCalculator(numbers.subList(numbers.size() / 2, numbers.size()))));
//
//        int sum = 0;
//        for (Future<Integer> future : results) {
//            try {
//                sum += future.get();
//            } catch (InterruptedException | ExecutionException e) {
//                System.err.println(e.getMessage());
//            }
//        }
//        return sum;
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Pair<String, Long>> getMostFrequentWords(List<String> words, long limit) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> getDuplicates(List<String> words, long limit) {
        throw new UnsupportedOperationException();
    }

    protected static class CallableCalculator implements Callable<Integer> {
        private final List<Integer> numbers;

        public CallableCalculator(List<Integer> numbers) {
            this.numbers = numbers;
        }

        @Override
        public Integer call() throws Exception {
            int sum = 0;
            for (int num : numbers) {
                sum += num;
            }
            return sum;
        }
    }
}
