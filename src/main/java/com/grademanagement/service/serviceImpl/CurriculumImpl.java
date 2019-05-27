package com.grademanagement.service.serviceImpl;

import com.grademanagement.controller.util.MD5;
import com.grademanagement.mapper.AchievementMapper;
import com.grademanagement.mapper.CurriculumMapper;
import com.grademanagement.mapper.UserMapper;
import com.grademanagement.mapper.UserRoleMapper;
import com.grademanagement.pojo.*;
import com.grademanagement.service.CurriculumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CurriculumImpl implements CurriculumService {
    @Autowired
    private CurriculumMapper curriculumMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AchievementMapper achievementMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;


    @Override
    public List<User> getCurriculumInfo() {


        return curriculumMapper.getCurriculumInfo();
    }

    @Override
    public List<Curriculum> findAll() {
        return curriculumMapper.selectByExample(new CurriculumExample());
    }

    @Override
    public List<ExcelDto> downLoad(Map map) {
        return curriculumMapper.getCurriculumInfo2(map);
    }

    @Override
    @Transactional
    public String insertMulitPart(List<ExcelDto> excelDtoList) {

        User user = null;
        Achievement achievement=null;
        int score=0;
        Long cid=null;
        Long id=null;
        for (ExcelDto e :
                excelDtoList) {
            //添加用户
            user = new User();
            user.setName(e.getName());//表中的用户名
            user.setPassword(MD5.toPassWord("1111"));//密码默认设置为1111
            userMapper.insertSelective(user);//插入进入数据库之中


            //添加achievement中的关系数据
            //1.获取刚才插入到user中的user表的user的id，通过姓名
             id= getUserID(user);
            //给学生添加统一默认权限
            UserRoleKey userRoleKey=new UserRoleKey();
            userRoleKey.setUid(id);
            userRoleKey.setRid(3l);
            userRoleMapper.insertSelective(userRoleKey);
            //获取课程的id
            cid =getCourseID(e.getCname());
            //获取表单传上来的成绩
            score= e.getScore();
            //将id,cid,score封装进入achievement对象中
            achievement=new Achievement();
            achievement.setCid(cid);
            achievement.setUid(id);
            achievement.setScore(score);
            achievementMapper.insertSelective(achievement);
        }
        return "添加成功";
    }

    @Override
    public ExcelDto getModifyCourseInfo(Long id, Long cid) {
        ExcelDto excelDto=new ExcelDto();
        excelDto.setCid(cid.intValue());
        excelDto.setId(id.intValue());
        return curriculumMapper.getModifyCourseInfo(excelDto);
    }

    @Override
    public int selectDataCount( Map hashMap) {
       Integer i = curriculumMapper.getCurriculumInfoCount(hashMap);
        return i;
    }
/*
删除课程信息并且将成绩表中关于这门课程的成绩也进行删除
 */
    @Override
    @Transactional
    public int delCourse(String id) {
        AchievementExample example = new AchievementExample();
        AchievementExample.Criteria criteria = example.createCriteria();
        criteria.andCidEqualTo(Long.valueOf(id));
        int i1 = achievementMapper.deleteByExample(example);
        int i = curriculumMapper.deleteByPrimaryKey(Long.valueOf(id));
        return i;
    }
/*
添加课程信息
 */
    @Override
    public Boolean insertCurriculum(Curriculum curriculum) {
        int i = curriculumMapper.insertSelective(curriculum);
        return i>0?true:false;
    }

    private Long getCourseID(String cname) {
        CurriculumExample curriculumExample = new CurriculumExample();
        CurriculumExample.Criteria criteria = curriculumExample.createCriteria();
        criteria.andNameEqualTo(cname);
        List<Curriculum> curriculumList = curriculumMapper.selectByExample(curriculumExample);
        if(curriculumList.size()>0){
            return  curriculumList.get(0).getId();
        }
        return null;
    }

    private Long getUserID(User user) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andNameEqualTo(user.getName());
        //执行查询获取用户的id
        List<User> users = userMapper.selectByExample(userExample);
        Long id = null;
        if (users .size()> 0) {
            //限制：不能有重名的人
            return id = users.get(0).getId();
        }
        return id;
    }

}
