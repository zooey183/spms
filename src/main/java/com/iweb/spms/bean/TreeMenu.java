package com.iweb.spms.bean;

import lombok.Data;

import java.util.List;

/**
 * @author Wang
 * @date 2024/4/21 20:01
 */
@Data
public class TreeMenu {
    private String id;
    private String text;
    private boolean expanded;
    private List<TreeMenu> children;
}
