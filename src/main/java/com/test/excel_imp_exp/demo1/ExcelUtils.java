package com.test.excel_imp_exp.demo1;

import org.apache.poi.hssf.usermodel.HSSFDataFormatter;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * author zw
 */
public class ExcelUtils {

    /**
     * 验证上传的文件
     * @param fileName
     */
    public static void valExcelFile(String fileName) throws Exception{
        if(!"".equals(fileName) && fileName != null){
            String suffix = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
            if((!"".equals(suffix) && suffix != null) || (!".xlsx".equals(suffix) && !".xls".equals(suffix))){
                throw new Exception("上传文件格式错误！");
            }
        }
    }

    /**
     * 根据不同的excel文件创建不同的Workbook
     * @param mulFile
     * @return
     */
    public static Workbook createWorkbook(MultipartFile mulFile){
        Workbook workbook = null;
        String fileName = mulFile.getOriginalFilename();
        //获取扩展名
        String suffix = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
        try {
            if(".xls".equals(suffix)){//使用xls方式写入
                workbook = new HSSFWorkbook(mulFile.getInputStream());
            }else if(".xlsx".equals(suffix)){ //使用xlsx方式写入
                workbook = new XSSFWorkbook(mulFile.getInputStream());
            }
        }catch (IOException e) {
           e.printStackTrace();
        }
        return workbook;
    }

    /**
     * Workbook转List<Row>
     * @param workbook
     * @return
     */
    public static List<Row> readExcelRows(Workbook workbook){
        List<Row> rowList = new ArrayList<Row>();
        Sheet sheet = null;
        sheet = workbook.getSheetAt(0);//操作第一个sheet
        int rowNum = sheet.getLastRowNum();//获得总行数

        if(rowNum < 1){
            System.out.println("excel无数据!");
        }else {
            for(int i=0;i<=rowNum;i++){
                Row row = sheet.getRow(i);
                rowList.add(row);
            }
        }
        return rowList;
    }

    /***
     * 读取单元格的值
     * @Title: getCellValue
     * @param cell
     * @return
     */
    private static String getCellValue(Cell cell) {
        Object result = "";
        if(cell == null) {
            return "";
        } else {
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_STRING:
                    result = cell.getStringCellValue();
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {//处理日期
                        Date date = cell.getDateCellValue();
                        result = date.toString();
                    } else {//数值
                        HSSFDataFormatter dataFormatter = new HSSFDataFormatter();
                        String cellFormatted = dataFormatter.formatCellValue(cell);
                        if(cellFormatted.indexOf("%") > 0){
                            cellFormatted = cellFormatted.replaceAll("%","");
                        }
                        result = cellFormatted;
                    }
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                    result = cell.getBooleanCellValue();
                    break;
                case Cell.CELL_TYPE_FORMULA:
                    result = cell.getCellFormula();
                    break;
                case Cell.CELL_TYPE_ERROR:
                    result = cell.getErrorCellValue();
                    break;
                case Cell.CELL_TYPE_BLANK:
                    break;
                default:
                    result = cell.getStringCellValue();
                    break;
            }
        }
        return result.toString();
    }
}