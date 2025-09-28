package org.example.bench;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.results.format.ResultFormatType;

public class BenchRunner {
    public static void main(String[] args) throws Exception {
        Options opt = new OptionsBuilder()
                .include(SelectVsSortBench.class.getSimpleName())
                .forks(1)
                .warmupIterations(2)
                .measurementIterations(5)
                .result("bench-results.csv")
                .resultFormat(ResultFormatType.CSV)
                .build();

        new Runner(opt).run();
    }
}
