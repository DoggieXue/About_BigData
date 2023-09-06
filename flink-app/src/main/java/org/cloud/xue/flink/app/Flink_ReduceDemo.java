package org.cloud.xue.flink.app;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.cloud.xue.dto.Event;
import org.cloud.xue.flink.source.CliskSource;

/**
 * @ClassName Flink_ReduceDemo
 * @Description: 使用reduce实现sum功能：统计每个用户的访问频次
 *               使用reduce实现maxBy功能：记录所有用户访问频次最高的那个
 * @Author: Doggie
 * @Date: 2023年09月06日 10:04:22
 * @Version 1.0
 **/
public class Flink_ReduceDemo {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env  = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);

        DataStreamSource<Event> source = env.addSource(new CliskSource());

        //DataStream<Event> --> DataStream<Tuple2<String, Long>>
        SingleOutputStreamOperator<Tuple2<String, Long>> mapSource = source.map(new MapFunction<Event, Tuple2<String, Long>>() {
            @Override
            public Tuple2<String, Long> map(Event event) throws Exception {
                return new Tuple2<>(event.user, 1L);
            }
        }).returns(Types.TUPLE(Types.STRING, Types.LONG));//避免泛型擦除导致Flink无法推断元组类型

        //按照用户ID分组，即使用元组的第一个元素分组
        KeyedStream<Tuple2<String, Long>, String> keyedStream = mapSource.keyBy(new KeySelector<Tuple2<String, Long>, String>() {

            @Override
            public String getKey(Tuple2<String, Long> t) throws Exception {
                return t.f0;
            }
        });
        //聚合运算
        keyedStream.reduce(new ReduceFunction<Tuple2<String, Long>>() {
            @Override
            public Tuple2<String, Long> reduce(Tuple2<String, Long> lastreduceResult, Tuple2<String, Long> newTuple) throws Exception {
                //来一条数据，用户访问次数加一
                return Tuple2.of(lastreduceResult.f0, lastreduceResult.f1 + newTuple.f1);
            }
        }).keyBy(r -> true) //将所有数据的key都制定为true，这样聚合结果会发送到一条流中去
                .reduce(new ReduceFunction<Tuple2<String, Long>>() {
            @Override
            public Tuple2<String, Long> reduce(Tuple2<String, Long> t1, Tuple2<String, Long> t2) throws Exception {
                //所有统计结果分发到新的流中后，找出出最大访问次数，然后更新
                return t1.f1 > t2.f1 ? t1 : t2;
            }
        }).print();

        env.execute();
    }
}
