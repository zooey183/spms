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
public class PartTimeJob {
    private String partTimeJobId;
    private String addTime;
    private String enterpriseId;
    private String enterpriseName;
    private String contacts;
    private String contactsPhone;
    private String contactsEmail;
    private String partTimeJobName;
    private String partTimeJobHeadSculpture;
    private String partTimeJobNumber;
    private String partTimeJobContent;
    private String partTimeJobLocation;
    private String partTimeJobTime;
    private String partTimeJobSalary;
}
