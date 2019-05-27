package com.grademanagement.controller;


import com.grademanagement.controller.util.ExportExcel;
import com.grademanagement.controller.util.PageBean;
import com.grademanagement.controller.util.ReadExcel;
import com.grademanagement.controller.util.WDWUtil;
import com.grademanagement.mapper.AchievementMapper;
import com.grademanagement.pojo.Achievement;
import com.grademanagement.pojo.AchievementExample;
import com.grademanagement.pojo.Curriculum;
import com.grademanagement.pojo.ExcelDto;
import com.grademanagement.service.CurriculumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/curriculum")
public class CurriculumController {
    @Autowired
    private CurriculumService curriculumService;
    @Autowired
    private AchievementMapper AchievementMapper;


    Map hashMap = new HashMap();//用来存放动态查询条件，供下载excel的使用
@RequestMapping("modifyPage")
    public String  modifyPage(String id, String cid, HttpSession session){

    session.setAttribute("id",id);
    session.setAttribute("cid",cid);
    System.out.println(id+cid);
    return "modifyPage";
}

/*
根据id,cid查询到此用户所选择的课程的信息，老师的名称
 */
    @RequestMapping("/getmodifyInfo")
    @ResponseBody
    public ExcelDto modifyPath(HttpSession session){
        String id = (String) session.getAttribute("id");
        String cid = (String) session.getAttribute("cid");
        ExcelDto excelDtos = curriculumService.getModifyCourseInfo(Long.valueOf(id),Long.valueOf(cid));

        return excelDtos;
    }
    /**
     * 查询所有的学生的成绩信息
     */
    @RequestMapping("/info")
    public ModelAndView curriculaInfo() {
        hashMap = null;
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("currlist", curriculumService.getCurriculumInfo());
        modelAndView.setViewName("curriculuminfo");
        return modelAndView;
    }

    @RequestMapping(value = "/{path}")
    public String pathParam(@PathVariable String path) {
        return path;
    }

    /**
     * 获取课程的信息
     *
     * @return
     */
    @RequestMapping("/curriculs")
    @ResponseBody
    public List<Curriculum> curriculs() {
        List<Curriculum> cus = curriculumService.findAll();
        return cus;
    }
    @RequestMapping("/modifyScore")
    @ResponseBody
    public String modifyScore(ExcelDto excelDto) {
        AchievementExample example = new AchievementExample();
        AchievementExample.Criteria criteria = example.createCriteria();
        criteria.andUidEqualTo(Long.valueOf(excelDto.getId()));
        criteria.andCidEqualTo(Long.valueOf(excelDto.getCid()));
        Achievement achievement = new Achievement();
        achievement.setScore(excelDto.getScore());
        int i = AchievementMapper.updateByExampleSelective(achievement, example);
        System.out.println(i+"=======||||||||||||||||||||||||||||==========");
        return "";
    }

    /**
     * 该方法获取到动态查询的数据提供给用户进行下载
     *
     * @param response
     */
    @RequestMapping("/downloadExcel")
    @ResponseBody
    public void downloadExcel(HttpServletResponse response) {
        ExcelDto excelDto = new ExcelDto();
        List<ExcelDto> excelDtos = curriculumService.downLoad(hashMap);
        ExportExcel<ExcelDto> ee = new ExportExcel<ExcelDto>();
        String[] headers = {"id","name", "cname", "score", "tname"};
        String fileName = "myExcel";
        ee.exportExcel(headers, excelDtos, fileName, response);
    }

    /**
     * 用于动态查询的结果
     *
     * @param subject  课程名称
     * @param username 用户名称
     * @param stuGrade 所有课程的成绩线
     * @return
     */
    @RequestMapping("/criteriaQuery")
    @ResponseBody
    public PageBean<ExcelDto> toHelpDownLoad(String subject, String username, String stuGrade, String curPage) {

        PageBean<ExcelDto> pageBean = new PageBean();

        //将课程的查询条件添加进入map中
        System.out.println(subject + "???");
        Map criteriaMap = new HashMap();
        System.out.println(username + "!!!");
        //传递进来的subject没有值的情况默认占三个空格
        if (subject != null && subject != "") {
            criteriaMap.put("cname", subject);
        }
        //将用户姓名的查询条件添加进入map
        if (username != "" && username != null) {
            criteriaMap.put("name","%"+ username+"%");
        }
        Integer score = null;
        if (stuGrade != "" && stuGrade != null) {
            score = Integer.valueOf(stuGrade);
        }
        criteriaMap.put("score", score);

        hashMap = criteriaMap;//将查询的条件传递给全局变量hashMap,下载的数据就是动态查询的所有数据，不受分页影响
        pageBean.setTotalCount(5);
        //查询记录总数
        int count=curriculumService.selectDataCount(criteriaMap);
        pageBean.setCountPage(count);
        //添加分页条件
        if(curPage!=null&&curPage!=""){
            System.out.println(curPage+"第二次");
            pageBean.setCurPage(Integer.valueOf(curPage));
            criteriaMap.put("start",(pageBean.getCurPage()-1)*pageBean.getTotalCount());
            criteriaMap.put("size",pageBean.getTotalCount());//五条数据
        }else if(curPage==null){
            pageBean.setCurPage(1);
            criteriaMap.put("start",pageBean.getCurPage()-1);
            criteriaMap.put("size",pageBean.getTotalCount());//五条数据
        }

        pageBean.setSqlProjection(criteriaMap);
        List<ExcelDto> excelDtos = curriculumService.downLoad(pageBean.getSqlProjection());//查询条件存放进入获取返回的结果
        criteriaMap.remove("start");
        criteriaMap.remove("size");
        criteriaMap.remove("score");


        pageBean.setPageData(excelDtos);
//        //放进入pageBean的数据中,提供给下载项
//        page.setPageData(excelDtos);
        return pageBean;
    }

    @RequestMapping("/upLoadExcel")
    public String upLoadExcel(@RequestParam(value = "filename") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        String Msg = null;
        boolean b = false;

        //判断文件是否为空
        if (file == null) {
            Msg = "文件是为空！";
            request.getSession().setAttribute("msg", Msg);
            return "client/client";
        }

        //获取文件名
        String name = file.getOriginalFilename();

        //进一步判断文件是否为空（即判断其大小是否为0或其名称是否为null）验证文件名是否合格
        long size = file.getSize();
        if (name == null || ("").equals(name) && size == 0 && !WDWUtil.validateExcel(name)) {
            Msg = "文件格式不正确！请使用.xls或.xlsx后缀文档。";
            request.getSession().setAttribute("msg", Msg);
            return "client/client";
        }

        //创建处理EXCEL
        ReadExcel readExcel = new ReadExcel();
        //解析excel，获取客户信息集合。
        List<ExcelDto> excelDtoList = readExcel.getExcelInfo(file);
        if (excelDtoList != null && !excelDtoList.toString().equals("[]") && excelDtoList.size() >= 1) {
            b = true;
        }
        if (b) {//证明上传成功，可以在集合中获取到所有的数据，添加进入数据库中
            //迭代添加客户信息（注：实际上这里也可以直接将customerList集合作为参数，在Mybatis的相应映射文件中使用foreach标签进行批量添加。）
            //将从excel中的数据放到service中进行多表的插入操作
            String tag = curriculumService.insertMulitPart(excelDtoList);
//            for (ExcelDto e:
//                    excelDtoList) {
//                System.out.println("1:"+e.getCname()+"2:"+e.getName()+"3:"+e.getTname()+"4:"+e.getScore());
//            }
        } else {

        }
        return "redirect:curriculuminfo";
    }
    //成绩删除
    @RequestMapping("/del")
    @ResponseBody
    public String del(String id,String cid){
        System.out.println(id+"-----------------------------"+cid);
        Achievement achievement = new Achievement();
        achievement.setCid(Long.valueOf(cid));
        achievement.setUid(Long.valueOf(id));
        AchievementExample example = new AchievementExample();
        AchievementExample.Criteria criteria = example.createCriteria();
        criteria.andCidEqualTo(achievement.getCid());
        criteria.andUidEqualTo(achievement.getUid());
        AchievementMapper.deleteByExample(example);
        return "delete success!!";
    }

}
