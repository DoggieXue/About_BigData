package org.cloud.xue.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.cloud.xue.dto.PvAndUvDto;

/**
 * @ClassName JsonHelper
 * @Description:
 * @Author: Doggie
 * @Date: 2023年09月04日 14:56:10
 * @Version 1.0
 **/
public class JsonHelper {

    public static long getTimeLongFromKafkaMessage(String message) throws JsonProcessingException {
        PvAndUvDto pvAndUvDto = parse(message);
        return null == pvAndUvDto ? 0L : pvAndUvDto.getTiming();
    }

    public static PvAndUvDto parse(String message){
        PvAndUvDto pvAndUvDto = null;

        if (message != null) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                pvAndUvDto = mapper.readValue(message, PvAndUvDto.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        return pvAndUvDto;
    }
}
