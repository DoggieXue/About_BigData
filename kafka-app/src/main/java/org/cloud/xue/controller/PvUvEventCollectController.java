package org.cloud.xue.controller;

import lombok.extern.slf4j.Slf4j;
import org.cloud.xue.dto.PvAndUvDto;
import org.cloud.xue.dto.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName EventCollect
 * @Description:
 * @Author: Doggie
 * @Date: 2023年09月01日 14:46:07
 * @Version 1.0
 **/
@RestController
@Slf4j
@RequestMapping("/kafka")
public class PvUvEventCollectController {

    private static final String TOPIC_NAME = "event_pvuvmau";

    @Autowired
    private KafkaTemplate<String, PvAndUvDto> kafkaTemplate;

    @RequestMapping(value = "/pvuv", method = RequestMethod.POST)
    public Result producerMsg(@RequestBody PvAndUvDto puDto) {
        log.info("send pvuv message = {}, topic = {}", puDto, TOPIC_NAME);
        kafkaTemplate.send(TOPIC_NAME, puDto);
        return Result.success(puDto);
    }
}
