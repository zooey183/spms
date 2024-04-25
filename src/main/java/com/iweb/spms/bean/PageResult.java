package com.iweb.spms.bean;

import lombok.Data;

import java.util.List;

/**
 * @author Wang
 * @date 2024/4/21 19:58
 */
@Data
public class PageResult<T> {
    private int total;
    private List<T> data;
}
