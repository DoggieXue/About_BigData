package org.cloud.xue.flink;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

/**
 * @ClassName StreamWordCount
 * @Description: 流处理
 * @Author: Doggie
 * @Date: 2023年08月23日 14:47:16
 * @Version 1.0
 **/
public class StreamWordCount {
    public static void main(String[] args) throws Exception {
        //创建执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        //读取文件
        String filePath = "/Users/xuexiao/Work/QDBank/UEMP/flink-app/src/main/resources/data/hello-flink.txt";
        DataStream<String> inputDataStream = env.readTextFile(filePath);

        DataStream<Tuple2<String, Integer>> resultStream =
                inputDataStream.flatMap(new MyFlatMapFunction()).keyBy(item -> item.f0).sum(1);

        resultStream.print();

        env.execute();
    }

    public static class MyFlatMapFunction implements FlatMapFunction<String, Tuple2<String, Integer>> {

        @Override
        public void flatMap(String s, Collector<Tuple2<String, Integer>> out) throws Exception {
            String[] words = s.split(" ");
            for (String word : words) {
                out.collect(new Tuple2<String, Integer>(word, 1));
            }
        }
    }
}
