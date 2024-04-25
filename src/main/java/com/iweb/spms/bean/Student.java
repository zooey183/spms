package com.iweb.spms.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author Wang
 * @date 2024/4/21 19:46
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private String studentId;
    private String addTime;
    private String studentName;
    private String studentPassword;
    private String studentSex;
    private String studentHeadSculpture;
    private String school;
    private String major;
    private String phone;
    private String email;
}
