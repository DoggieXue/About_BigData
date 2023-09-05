package org.cloud.xue.flink;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

/**
 * @ClassName RealTimeStreamWordCount
 * @Description: 实时流处理
 * @Author: Doggie
 * @Date: 2023年08月23日 15:00:56
 * @Version 1.0
 **/
public class RealTimeStreamWordCount {
    public static void main(String[] args) throws Exception{
        // 获取执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        // 设置并行度
        //env.setMaxParallelism(2);

        // 监听socket端口，获取socket文本流数据
        DataStream<String> socketStream = env.socketTextStream("127.0.0.1", 8888);

        // 将实时数据流扁平化成元组
        // 按照元组的第一个元素分组
        // 将分组后的数据求和
        DataStream<Tuple2<String, Integer>> resultStream =
                socketStream.flatMap(new FlatMapFunction<String, Tuple2<String, Integer>>() {
                    @Override
                    public void flatMap(String s, Collector<Tuple2<String, Integer>> out) throws Exception {
                        String[] words = s.split(" ");
                        for (String word : words) {
                            out.collect(new Tuple2<String, Integer>(word, 1));
                        }
                    }
                })
                        .keyBy(item -> item.f0)
                        .sum(1);
        resultStream.print();

        env.execute();
    }
}
