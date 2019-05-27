package com.grademanagement.controller.util;

import com.grademanagement.pojo.ExcelDto;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReadExcel{
    //总行数
    private int totalRows = 0;
    //总条数
    private int totalCells = 0;
    //错误信息接收器
    private String errorMsg;
    //构造方法
    public ReadExcel(){}
    //获取总行数
    public int getTotalRows()  { return totalRows;}
    //获取总列数
    public int getTotalCells() {  return totalCells;}
    //获取错误信息-暂时未用到暂时留着
    public String getErrorInfo() { return errorMsg; }

    /**
     * 读EXCEL文件，获取客户信息集合
     * @param
     * @return
     */
    public List<ExcelDto> getExcelInfo(MultipartFile Mfile){

        //把spring文件上传的MultipartFile转换成CommonsMultipartFile类型
        CommonsMultipartFile cf= (CommonsMultipartFile)Mfile; //获取本地存储路径
        File file = new  File("D:\\fileupload");
        //创建一个目录 （它的路径名由当前 File 对象指定，包括任一必须的父路径。）
        if (!file.exists()) file.mkdirs();
        //新建一个文件
        File file1 = new File("D:\\fileupload\\" + new Date().getTime() + ".xls");
        //将上传的文件写入新建的文件中
        try {
            cf.getFileItem().write(file1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //初始化客户信息的集合
        List<ExcelDto> excelDtoList=new ArrayList<ExcelDto>();
        //初始化输入流
        FileInputStream is = null;
        Workbook wb = null;
        try{
            //根据新建的文件实例化输入流
            is = new FileInputStream(file1);
            //根据excel里面的内容读取客户信息

            //当excel是2003时
            wb = new HSSFWorkbook(is);
            //当excel是2007时
            //wb = new XSSFWorkbook(is);

            //读取Excel里面客户的信息
            excelDtoList=readExcelValue(wb);
            is.close();
        }catch(Exception e){
            e.printStackTrace();
        } finally{
            if(is !=null)
            {
                try{
                    is.close();
                }catch(IOException e){
                    is = null;
                    e.printStackTrace();
                }
            }
        }
        return excelDtoList;
    }

    /**
     * 读取Excel里面客户的信息
     * @param wb
     * @return
     */
    private List<ExcelDto> readExcelValue(Workbook wb){
        //得到第一个shell
        Sheet sheet=wb.getSheetAt(0);

        //得到Excel的行数
        this.totalRows=sheet.getPhysicalNumberOfRows();

        //得到Excel的列数(前提是有行数)
        if(totalRows>=1 && sheet.getRow(0) != null){//判断行数大于一，并且第一行必须有标题（这里有bug若文件第一行没值就完了）
            this.totalCells=sheet.getRow(0).getPhysicalNumberOfCells();
        }else{
            return null;
        }

        List<ExcelDto> excelDtoList=new ArrayList<ExcelDto>();//声明一个对象集合
        ExcelDto excelDto;//声明一个对象

        //循环Excel行数,从第二行开始。标题不入库
        for(int r=1;r<totalRows;r++){
            Row row = sheet.getRow(r);
            if (row == null) continue;
            excelDto = new ExcelDto();

            //循环Excel的列
            for(int c = 0; c <=this.totalCells; c++){
                Cell cell = row.getCell(c);
                if (null != cell){
                    if(c==0){
                        excelDto.setId(Integer.valueOf(getValue(cell)));//得到行中第一个值
                    }
                    if(c==1){
                        excelDto.setName(getValue(cell));//得到行中第一个值
                    }else if(c==2){
                        excelDto.setCname(getValue(cell));//得到行中第二个值
                    }else if(c==3){
                        excelDto.setScore(Integer.valueOf(getValue(cell).substring(0,getValue(cell).indexOf("."))));//即成绩值
                       //得到行中第三个值
                    }else if(c==4){
                        excelDto.setTname(getValue(cell));;//得到行中第四个值
                    }
                }
            }
            //添加对象到集合中
            excelDtoList.add(excelDto);
        }
        return excelDtoList;
    }

    /**
     * 得到Excel表中的值
     *
     * @param cell
     *            Excel中的每一个格子
     * @return Excel中每一个格子中的值
     */
    @SuppressWarnings({ "static-access", "unused" })
    private String getValue(Cell cell) {
        if (cell.getCellType() == CellType.BOOLEAN) {
            // 返回布尔类型的值
            return String.valueOf(cell.getBooleanCellValue());
        } else if (cell.getCellType() == CellType.NUMERIC) {
            // 返回数值类型的值
            return String.valueOf(cell.getNumericCellValue());
        } else {
            // 返回字符串类型的值
            return String.valueOf(cell.getStringCellValue());
        }
    }


}
