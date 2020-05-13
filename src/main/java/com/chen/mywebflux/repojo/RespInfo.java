package com.chen.mywebflux.repojo;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * @Author: Aaron chen
 * @Date: 2020/5/14 2:16
 */
@Data
public class RespInfo {
    private Integer code;
    private String msg;
    private JSONObject data;
}
