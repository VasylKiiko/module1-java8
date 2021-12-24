package com.epam.cdp.m2.hw2.aggregator;

import javafx.util.Pair;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Java7ParallelAggregator implements Aggregator {

    @Override
    public int sum(List<Integer> numbers) {
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        SumTask sumTask = new SumTask(numbers);
        return forkJoinPool.invoke(sumTask);
    }

    @Override
    public List<Pair<String, Long>> getMostFrequentWords(List<String> words, long limit) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> getDuplicates(List<String> words, long limit) {
        throw new UnsupportedOperationException();
    }

    // implementation of Fork/Join Framework
    public class SumTask extends RecursiveTask<Integer> {
        private static final int SEQUENTIAL_THRESHOLD = 3;
        private final List<Integer> numbers;

        public SumTask(List<Integer> numbers) {
            this.numbers = numbers;
        }

        private int computeSum() {
            int sum = 0;
            for (int num : numbers) {
                sum += num;
            }
            return sum;
        }

        @Override
        protected Integer compute() {
            if (numbers.size() <= SEQUENTIAL_THRESHOLD) {
                return computeSum();
            } else {
                int middle = numbers.size() / 2;
                SumTask firstTask = new SumTask(numbers.subList(0, middle));
                SumTask secondTask = new SumTask(numbers.subList(middle, numbers.size()));

                firstTask.fork();
                return secondTask.compute() + firstTask.join();
            }
        }
    }
}
