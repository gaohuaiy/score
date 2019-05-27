import com.grademanagement.controller.UserController;
import com.grademanagement.mapper.*;
import com.grademanagement.pojo.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml","classpath:springmvc.xml"})
@WebAppConfiguration()
public class Test {


    public static void main(String[] args) throws IOException {



        //获得mybatis的环境配置文件
        String resource="mybaties.xml";
        //以流的方式获取recource(mybatis的环境配置文件)
        InputStream inputStream=Resources.getResourceAsStream(resource);
        //创建会话工厂
        SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
        //通过工厂得到SqlSession
        SqlSession sqlSession= sqlSessionFactory.openSession();

        CurriculumMapper mapper = sqlSession.getMapper(CurriculumMapper.class);
//        ExcelDto excelDto = new ExcelDto();
//        excelDto.setId(125);
//        excelDto.setCid(2);
//        ExcelDto modifyCourseInfo = mapper.getModifyCourseInfo(excelDto);
//        System.out.println(modifyCourseInfo);
//        List<Curriculum> curriculumList = mapper.selectByExample(new CurriculumExample());
//        for (Curriculum c:curriculumList
//             ) {
//            System.out.println(c+"=========");
//        }
//        Role role = mapper.selectByPrimaryKey(1l);
//        System.out.println(role.getName());
        //测试动态查询分页的sql,ok
//        Map map=new HashMap();
//        map.put("name","%z%");
//        map.put("cname","软件工程");
////        map.put("start",0);
////        map.put("size",4);
////        map.
        List<Curriculum> curricula = mapper.selectByExample(new CurriculumExample());
        curricula.forEach(curriculum -> {
            System.out.println(curriculum.gettName()+curriculum.getName());
        });


//        List<Role> roles = mapper.selectRolesByUID(1);
//    1    for (ExcelDto r:curriculumInfo2
//             ) {
//            System.out.println(r+"===============");
//        }

//        System.out.println(curriculum.gettName());

//        for (Achievement ac:achievements) {
//            System.out.println(ac.getScore());
//
//        }
//        CurriculumMapper curriculumMapper=sqlSession.getMapper(CurriculumMapper.class);
//        List<Curriculum> curricula = curriculumMapper.selectByExample(new CurriculumExample());
//        for (Curriculum c:curricula
//             ) {
//            System.out.println(c.getName());
//        }
//        User u=new User();
//        u.setId(3l);
//        List<Role> roles =roleMapper.selectRolesByUID(u.getId().intValue());
//        for (Role role:roles) {
//            System.out.println(role);
//        }
//        User user=userMapper.selectByPrimaryKey(2l);
//        System.out.println("''"+user.getName()+user.getPassword());
        //通过SqlSession操作数据库
        //selectOne 根据英语单词字面理解selectOne是只能查询出一条记录,也就是说返回结果为单条记录
        //Category.xml中的<select>标签的id名称不固定,顾名思义的起名字方便程序阅读

    }
MockMvc mockMvc;
    @Before
    public void setUp() {
    //    	 MockitoAnnotations.initMocks(this);
       mockMvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
    }

    /*@org.junit.Test
    public void demo() throws Exception {
        mockMvc.perform(get("/user/login")
                .param("user.name","zhang3")
                .param("user.password","12345")
        )
                .andExpect(status().isOk())
                .andDo(mvcResult -> System.out.println(mvcResult.getModelAndView()));
    }*/
}
