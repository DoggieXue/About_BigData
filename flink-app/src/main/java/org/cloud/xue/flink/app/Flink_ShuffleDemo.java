package org.cloud.xue.flink.app;

import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @ClassName Flink_ShuffleDemo
 * @Description: 经历过shuffle之后，每次打印的结果都不同
 * 2> 1
 * 1> 3
 * 1> 6
 * 2> 2
 * 2> 4
 * 2> 5
 * @Author: Doggie
 * @Date: 2023年09月06日 11:22:18
 * @Version 1.0
 **/
public class Flink_ShuffleDemo {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<Integer> stream = env.fromElements(1, 2, 3, 4, 5, 6).setParallelism(1);

        stream.shuffle().print().setParallelism(2);

        env.execute("Shuffle Demo");
    }
}
