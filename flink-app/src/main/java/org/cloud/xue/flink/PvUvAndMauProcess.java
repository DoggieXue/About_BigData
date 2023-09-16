package org.cloud.xue.flink;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.connector.kafka.source.KafkaSource;
import org.apache.flink.connector.kafka.source.enumerator.initializer.OffsetsInitializer;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;
import org.cloud.xue.dto.PvAndUvDto;
import org.cloud.xue.utils.JsonHelper;

import java.util.Properties;

/**
 * @ClassName PvUvAndMauProcess
 * @Description:
 * @Author: Doggie
 * @Date: 2023年09月04日 14:09:14
 * @Version 1.0
 **/
public class PvUvAndMauProcess {
    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        KafkaSource<String> source = KafkaSource.<String>builder()
                .setBootstrapServers("localhost:9092")
                .setTopics("event_pvuvmau")
                .setGroupId("my-group")
                .setStartingOffsets(OffsetsInitializer.earliest())
                .setValueOnlyDeserializer(new SimpleStringSchema())
                .build();

        DataStreamSource<String> kafka_source = env.fromSource(source, WatermarkStrategy.noWatermarks(), "Kafka Source");

        DataStream<PvAndUvDto> dtoSource = kafka_source.map(new MapFunction<String, PvAndUvDto>() {
            @Override
            public PvAndUvDto map(String s) throws Exception {
                return JsonHelper.parse(s);
            }
        });

        dtoSource.flatMap((PvAndUvDto dto, Collector<Tuple2<String, Long>> collector) -> {
            collector.collect(new Tuple2<>(dto.getDeviceId(), 1L));
        });


//        kafka_source.flatMap(new FlatMapFunction<String, Tuple2<String, Long>>() {
//            @Override
//            public void flatMap(String s, Collector<Tuple2<String, Long>> collector) throws Exception {
//                PvAndUvDto pvAndUvDto = JsonHelper.parse(s);
//                if (null != pvAndUvDto) {
//                    collector.collect(new Tuple2<>(pvAndUvDto.getDeviceId(),1L));
//                }
//            }
//        }).print();
//        .keyBy(0).timeWindow(Time.seconds(5)).apply((WindowFunction<Tuple2<String, Long>, Tuple2<String, Long>, Tuple, TimeWindow>) (tuple, window, input, out) -> {
//            long sum = 0L;
//            for (Tuple2<String, Long> record: input) {
//                sum += record.f1;
//            }
//
//            Tuple2<String, Long> result = input.iterator().next();
//            result.f1 = sum;
//            out.collect(result);
//        }).print();

        env.execute();

    }
}
