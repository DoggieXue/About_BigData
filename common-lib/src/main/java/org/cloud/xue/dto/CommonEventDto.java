package org.cloud.xue.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName CommonEventDto
 * @Description: 埋点事件基类
 * @Author: Doggie
 * @Date: 2023年09月01日 15:08:01
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
public class CommonEventDto {
    //设备ID（Android使用androidID，IOS使用uuid）
    private String deviceId;
    //mPaaS设备标识
    private String mpaasId;
    //设备型号
    private String deviceType;
    //设备平台 android、ios
    private String platform;
    //设备上操作系统版本号
    private String osVersion;
    //手机银行APP版本号
    private String appVersion;
    //核心客户号
    private String userId;
    //时间时间
    private long timing;
}
