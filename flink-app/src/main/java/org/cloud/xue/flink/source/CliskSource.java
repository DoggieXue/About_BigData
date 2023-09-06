package org.cloud.xue.flink.source;

import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.cloud.xue.dto.Event;

import java.util.Calendar;
import java.util.Random;

/**
 * @ClassName CliskSource
 * @Description: 自定义数据源
 * @Author: Doggie
 * @Date: 2023年09月05日 17:52:28
 * @Version 1.0
 **/
public class CliskSource implements SourceFunction<Event> {

    private Boolean running = true;

    @Override
    public void run(SourceContext sourceContext) throws Exception {
        Random random = new Random();
        String[] users = {"Mary","Tommy","Bob"};
        String[] urls = {"./index","./start","./card","./fav","./prod?id=1", "./prod?id=2"};

        while (running) {
            Thread.sleep(500);
            sourceContext.collect(new Event(
                    users[random.nextInt(users.length)],
                    urls[random.nextInt(urls.length)],
                    Calendar.getInstance().getTimeInMillis()
            ));
        }
    }

    @Override
    public void cancel() {
        running = false;
    }
}
