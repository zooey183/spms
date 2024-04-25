package com.iweb.spms.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Wang
 * @date 2024/3/26 15:32
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Manager {
    private String managerId;
    private String addTime;
    private String managerName;
    private String managerPassword;
    private String managerSex;
    private String img;
}
