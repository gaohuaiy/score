package com.grademanagement.service;

import com.grademanagement.pojo.ExcelDto;
import com.grademanagement.pojo.User;

import java.util.List;

public interface StuService {
    List<ExcelDto> getCourseIno(User user);
}
