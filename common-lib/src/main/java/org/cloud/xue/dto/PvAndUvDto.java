package org.cloud.xue.dto;

import lombok.Data;

/**
 * @ClassName PvAndUvAndMau
 * @Description: PVUVMAU类
 * @Author: Doggie
 * @Date: 2023年09月01日 15:21:14
 * @Version 1.0
 **/
@Data
public class PvAndUvDto extends CommonEventDto{
    //操作类型：pv/uv/mau
    private String action;
    //操作场景：启动页-start、首页-index、理财页-finance
    private String scene;
}
