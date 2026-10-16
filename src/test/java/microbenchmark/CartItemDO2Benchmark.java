package microbenchmark;


import com.alibaba.fastjson2.JSONB;
import com.alibaba.fastjson2.JSONWriter;

import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/*
@Benchamr: define the target benchmark. You can specify the benchmark mode
@Warmup: specify warmup operation
https://docs.oracle.com/en/java/javase/23/docs/specs/man/java.html


 */


// EXAMPLE IMPLEMENTATION
// https://github.com/openjdk/jmh/tree/master/jmh-samples/src/main/java/org/openjdk/jmh/samples
// CartItemDO2Benchmark.testCartItem    ss    5  1.394 ± 0.701  ms/op
// average time for a calla
// @BenchmarkMode(Mode.SingleShotTime)
// @Warmup(iterations = 5)
// Get a memory Copy
// @Fork(1)
// @Measurement(iterations = 5    , time = 5)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class CartItemDO2Benchmark {


    private static volatile List<CartItemDO2> list; // 使用 volatile 修饰确保可见性

    private static List<CartItemDO2> newCartsItem() {
        if (list != null) {
            return list;
        }

        synchronized (CartItemDO2Benchmark.class) {
            if (list == null) {
                list = new ArrayList<>();
                for (long i = 90000000000L; i < 90000000000L + 1000; i++) {
                    CartItemDO2 cartItemDO2 = new CartItemDO2();
                    cartItemDO2.setUserId(i);
                    cartItemDO2.setAttributes(new HashMap<>());
                    cartItemDO2.setCartId(i * 100);
                    cartItemDO2.setCityCode(i * 12);
                    cartItemDO2.setItemId(i * 3);
                    cartItemDO2.setMainType(11);
                    cartItemDO2.setQuantity(900);
                    cartItemDO2.setSkuId(i * 5);
                    cartItemDO2.setSubType(i * 6);
                    cartItemDO2.setTpId(i * 7);
                    cartItemDO2.setTrackId(String.valueOf(i * 8));
                    list.add(cartItemDO2);
                }
            }
        }
        return list;
    }

    @Benchmark
    public byte[] testCartItem() throws Exception {
        return JSONB.toBytes(
                newCartsItem(),
                JSONB.symbolTable("myId"),
                JSONWriter.Feature.BeanToArray
        );
    }


    public void exercise_one_b()  {
        try {
            Options opt = new OptionsBuilder()
                    .include(CartItemDO2Benchmark.class.getSimpleName())
                    // NO HIDE WARMUP
                    .mode(Mode.SingleShotTime)
                    .forks(1)
                    // HWO MANY TIME TO IMPROVE AND RUN JIT
                    .warmupIterations(5)
                    .measurementIterations(5)
                    .resultFormat(ResultFormatType.JSON)
                    .result("raw_results.json_b")
                    .build();

            new Runner(opt).run();
        }

        catch (RunnerException e) {
                System.out.println("Trow Exception");
        }

    }


    public void exercise_one_d()  {
        try {
            Options opt = new OptionsBuilder()
                    .include(CartItemDO2Benchmark.class.getSimpleName())
                    .mode(Mode.SingleShotTime)
                    .forks(1)
                    .warmupIterations(0)
                    .measurementIterations(10)
                    .resultFormat(ResultFormatType.JSON)
                    .result("raw_results.json_d")
                    .build();

            new Runner(opt).run();
        }

        catch (RunnerException e) {
            System.out.println("Trow Exception");
        }

    }


    public void exercise_one_e()  {
        try {
            Options opt = new OptionsBuilder()
                    .include(CartItemDO2Benchmark.class.getSimpleName())
                    .mode(Mode.SingleShotTime)
                    // without JIT compiler
                    .jvmArgs("-Xint")
                    .forks(1)
                    .warmupIterations(0)
                    .measurementIterations(10)
                    .resultFormat(ResultFormatType.JSON)
                    .result("raw_results.json_e")
                    .build();

            new Runner(opt).run();
        }

        catch (RunnerException e) {
            System.out.println("Trow Exception");
        }

    }


    public void exercise_two_a()  {
        try {
            Options opt = new OptionsBuilder()
                    .include(CartItemDO2Benchmark.class.getSimpleName())
                    .mode(Mode.SingleShotTime)
                    // without JIT compiler
                    //.jvmArgs("XX:+PrintCompilation")
                    .detectJvmArgs()
                    .forks(1)
                    .warmupIterations(0)
                    .measurementIterations(10)
                    .build();

            new Runner(opt).run();
        }

        catch (RunnerException e) {
            System.out.println("Trow Exception");
        }

    }


    public void exercise_two_b()  {
        try {
            Options opt = new OptionsBuilder()
                    .include(CartItemDO2Benchmark.class.getSimpleName())
                    .mode(Mode.SingleShotTime)
                    // without JIT compiler
                    .jvmArgs("−XX:TieredStopAtLevel=1")
                    .forks(1)
                    .warmupIterations(0)
                    .measurementIterations(10)
                    .resultFormat(ResultFormatType.JSON)
                    .build();

            new Runner(opt).run();
        }

        catch (RunnerException e) {
            System.out.println("Trow Exception");
        }

    }






    // Visualizing : https://mybinder.org/, the repo
    // https://github.com/DaGeRe/lzscc-339-data

    // What happens : https://howtodoinjava.com/java/library/jmh-java-microbenchmark-harness/

    public static void main(String[] args) throws RunnerException {
        CartItemDO2Benchmark cs = new CartItemDO2Benchmark();
        cs.exercise_one_b();
        cs.exercise_one_d();
        cs.exercise_one_e();
      //  cs.exercise_two_a();

        }
        // We can see

}
