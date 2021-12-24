package com.epam.cdp.m2.hw2.performance;

import com.epam.cdp.m2.hw2.aggregator.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SumPerformance {
    private final List<Aggregator> aggregators = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        aggregators.add(new Java8Aggregator());
        aggregators.add(new Java8ParallelAggregator());
        aggregators.add(new Java7Aggregator());
        aggregators.add(new Java7ParallelAggregator());
    }

    @Test
    public void testSumPerformance() {
        Random random = new Random();
        List<Integer> numbers = Stream.generate(() -> random.nextInt(10)).limit(100_000_000).collect(Collectors.toList());
        System.out.println(numbers.size());
        for (Aggregator aggregator: aggregators) {
            long start = System.currentTimeMillis();
            aggregator.sum(numbers);
            System.out.println("Executed sequentially in (ms): " + (System.currentTimeMillis() - start) + " in " + aggregator.getClass());
        }
    }
}
