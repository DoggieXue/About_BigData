package org.cloud.xue.flink.app;

import org.apache.flink.connector.jdbc.JdbcConnectionOptions;
import org.apache.flink.connector.jdbc.JdbcExecutionOptions;
import org.apache.flink.connector.jdbc.JdbcSink;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.cloud.xue.dto.Event;
import org.cloud.xue.flink.source.CliskSource;

/**
 * @ClassName Flink_To_MySQL
 * @Description:
 * @Author: Doggie
 * @Date: 2023年09月06日 13:45:23
 * @Version 1.0
 **/
public class Flink_SinkToMySQL {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);

        DataStreamSource<Event> stream = env.addSource(new CliskSource());

        DataStreamSource<Event> elementStream = env.fromElements(
                new Event("Dami", "./cart", System.currentTimeMillis()),
                new Event("Boluo", "./home", System.currentTimeMillis())
        );

        stream.addSink(
                JdbcSink.sink(
                        "INSERT INTO CLICKS (click_user, click_url) VALUES (?, ?)",
                        (statement, r) -> {
                            statement.setString(1, r.user);
                            statement.setString(2, r.url);
                        },
                        JdbcExecutionOptions.builder()
                                .withBatchSize(1000)
                                .withBatchIntervalMs(200)
                                .build(),
                        new JdbcConnectionOptions.JdbcConnectionOptionsBuilder()
                                .withUrl("jdbc:mysql://localhost:3306/mybatis_plus")
                                .withDriverName("com.mysql.jdbc.Driver")
                                .withUsername("root")
                                .withPassword("123456")
                                .build()
                )

        );

        env.execute();
    }
}
