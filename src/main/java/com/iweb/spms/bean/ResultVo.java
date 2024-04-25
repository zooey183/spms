package com.iweb.spms.bean;

import lombok.Data;

/**
 * @author Wang
 * @date 2024/4/21 19:59
 */
@Data
public class ResultVo<T> {
    private String mess;
    private T t;
    private boolean isOk;
}
