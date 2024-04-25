package com.iweb.spms.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Wang
 * @date 2024/3/26 15:22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Enterprise {
    private String enterpriseId;
    private String addTime;
    private String enterpriseName;
    private String enterprisePassword;
    private String enterpriseType;
    private String numbers;
    private String enterpriseHeadSculpture;
    private String contacts;
    private String contactsPhone;
    private String contactsEmail;
    private String enterpriseAddress;
}
