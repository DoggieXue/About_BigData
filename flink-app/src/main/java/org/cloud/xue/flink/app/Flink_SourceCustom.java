package org.cloud.xue.flink.app;

import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.cloud.xue.dto.Event;
import org.cloud.xue.flink.source.CliskSource;

/**
 * @ClassName Flink_SourceCustom
 * @Description:
 * @Author: Doggie
 * @Date: 2023年09月05日 20:09:41
 * @Version 1.0
 **/
public class Flink_SourceCustom {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        env.setParallelism(1);

        DataStreamSource<Event> source = env.addSource(new CliskSource());

        source.print("SourceCustom");

        env.execute();
    }
}
