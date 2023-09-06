package org.cloud.xue.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @ClassName Event
 * @Description:
 * @Author: Doggie
 * @Date: 2023年09月05日 17:53:33
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    public String user;
    public String url;
    public long timestamp;

    @Override
    public String toString() {
        return "Event{" +
                "user='" + user + '\'' +
                ", url='" + url + '\'' +
                ", timestamp=" + System.currentTimeMillis() +
                '}';
    }
}
