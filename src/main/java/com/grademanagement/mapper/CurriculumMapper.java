package com.grademanagement.mapper;

import com.grademanagement.pojo.Curriculum;
import com.grademanagement.pojo.CurriculumExample;
import java.util.List;
import java.util.Map;

import com.grademanagement.pojo.ExcelDto;
import com.grademanagement.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CurriculumMapper {
    long countByExample(CurriculumExample example);

    int deleteByExample(CurriculumExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Curriculum record);

    int insertSelective(Curriculum record);

    List<Curriculum> selectByExample(CurriculumExample example);

    Curriculum selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Curriculum record, @Param("example") CurriculumExample example);

    int updateByExample(@Param("record") Curriculum record, @Param("example") CurriculumExample example);

    int updateByPrimaryKeySelective(Curriculum record);

    int updateByPrimaryKey(Curriculum record);
    List<User> getCurriculumInfo();
    List<ExcelDto>getCurriculumInfo2(Map map);

    ExcelDto getModifyCourseInfo(ExcelDto excelDto);

    Integer getCurriculumInfoCount(Map hashMap);
}