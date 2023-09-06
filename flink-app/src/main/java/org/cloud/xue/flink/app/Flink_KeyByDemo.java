package org.cloud.xue.flink.app;

import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.cloud.xue.dto.Event;
import org.cloud.xue.flink.source.CliskSource;

/**
 * @ClassName Flink_KeyByProcess
 * @Description:
 * @Author: Doggie
 * @Date: 2023年09月06日 08:44:28
 * @Version 1.0
 **/
public class Flink_KeyByDemo {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

//        DataStreamSource<Event> source = env.fromElements(
//                new Event("Tom", "./index", System.currentTimeMillis()),
//                new Event("Mary", "./card", System.currentTimeMillis()),
//                new Event("Bob", "./home", System.currentTimeMillis())
//        );
        DataStreamSource<Event> source = env.addSource(new CliskSource());

        //DataStream按照key生成一个逻辑分区，只有基于KeyedStream，才可以做聚合
        KeyedStream<Event, String> keyedStream = source.keyBy(new KeySelector<Event, String>() {

            @Override
            public String getKey(Event event) throws Exception {
                return event.user;
            }
        });

        keyedStream.maxBy("timestamp")//通过制定名称来聚合
                .print();

        env.execute();
    }
}
