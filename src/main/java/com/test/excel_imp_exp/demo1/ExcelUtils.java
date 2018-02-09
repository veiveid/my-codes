package com.test.excel_imp_exp.demo1;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;

/**
 * author zw
 */
public class ExcelUtils {

    public static String NO_DEFINE = "no_define";//未定义的字段
    public static String DEFAULT_DATE_PATTERN="yyyy年MM月dd日";//默认日期格式
    public static int DEFAULT_COLOUMN_WIDTH = 17;

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


    /**
     * Web 导出excel  浏览器下载 数据格式JSONArray
     * @param title 文件名称
     * @param headMap  excel标题头
     * @param ja  数据
     * @param response
     */
    public static void downloadExcelFile(String title, Map<String,String> headMap, JSONArray ja, HttpServletResponse response){
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ExcelUtils.exportExcel(title,headMap,ja,null,0,os);
            byte[] content = os.toByteArray();
            InputStream is = new ByteArrayInputStream(content);
            // 设置response参数，可以打开下载页面
            response.reset();

            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename="+ new String((title + ".xlsx").getBytes(), "iso-8859-1"));
            response.setContentLength(content.length);
            ServletOutputStream outputStream = response.getOutputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            BufferedOutputStream bos = new BufferedOutputStream(outputStream);
            byte[] buff = new byte[8192];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);

            }
            bis.close();
            bos.close();
            outputStream.flush();
            outputStream.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 导出Excel 2007 OOXML (.xlsx)格式
     * @param title 标题行
     * @param headMap 属性-列头
     * @param jsonArray 数据集
     * @param datePattern 日期格式，传null值则默认 年月日
     * @param colWidth 列宽 默认 至少17个字节
     * @param out 输出流
     */
    public static void exportExcel(String title,Map<String, String> headMap,JSONArray jsonArray,String datePattern,int colWidth, OutputStream out) {
        if(datePattern==null) datePattern = DEFAULT_DATE_PATTERN;
        // 声明一个工作薄
        SXSSFWorkbook workbook = new SXSSFWorkbook(1000);//缓存
        workbook.setCompressTempFiles(true);
        //表头样式
        CellStyle titleStyle = workbook.createCellStyle();
        titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        Font titleFont = workbook.createFont();
        titleFont.setFontHeightInPoints((short) 20);
        titleFont.setBoldweight((short) 700);
        titleStyle.setFont(titleFont);
        // 列头样式
        CellStyle headerStyle = workbook.createCellStyle();
        //headerStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        headerStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        headerStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        headerStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        headerStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        Font headerFont = workbook.createFont();
        headerFont.setFontHeightInPoints((short) 12);
        headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        headerStyle.setFont(headerFont);
        // 单元格样式
        CellStyle cellStyle = workbook.createCellStyle();
        //cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        Font cellFont = workbook.createFont();
        cellFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        cellStyle.setFont(cellFont);
        // 生成一个(带标题)表格
        SXSSFSheet sheet = workbook.createSheet();
        //设置列宽
        int minBytes = colWidth<DEFAULT_COLOUMN_WIDTH?DEFAULT_COLOUMN_WIDTH:colWidth;//至少字节数
        int[] arrColWidth = new int[headMap.size()];
        // 产生表格标题行,以及设置列宽
        String[] properties = new String[headMap.size()];
        String[] headers = new String[headMap.size()];
        int ii = 0;
        for (Iterator<String> iter = headMap.keySet().iterator(); iter.hasNext();) {
            String fieldName = iter.next();

            properties[ii] = fieldName;
            headers[ii] = headMap.get(fieldName);

            int bytes = fieldName.getBytes().length;
            arrColWidth[ii] =  bytes < minBytes ? minBytes : bytes;
            sheet.setColumnWidth(ii,arrColWidth[ii]*256);
            ii++;
        }
        // 遍历集合数据，产生数据行
        int rowIndex = 0;
        for (Object obj : jsonArray) {
            if(rowIndex == 65535 || rowIndex == 0){
                if ( rowIndex != 0 ) sheet = workbook.createSheet();//如果数据超过了，则在第二页显示

                /*SXSSFRow titleRow = sheet.createRow(0);//表头 rowIndex=0
                titleRow.createCell(0).setCellValue(title);
                titleRow.getCell(0).setCellStyle(titleStyle);
                sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, headMap.size() - 1));*/

                SXSSFRow headerRow = sheet.createRow(0); //列头 rowIndex =1
                for(int i=0;i<headers.length;i++) {
                    headerRow.createCell(i).setCellValue(headers[i]);
                    headerRow.getCell(i).setCellStyle(headerStyle);

                }
                rowIndex = 1;//数据内容从 rowIndex=1开始
            }
            JSONObject jo = (JSONObject) JSONObject.toJSON(obj);
            SXSSFRow dataRow = sheet.createRow(rowIndex);
            for (int i = 0; i < properties.length; i++)
            {
                SXSSFCell newCell = dataRow.createCell(i);

                Object o =  jo.get(properties[i]);
                String cellValue = "";
                if(o==null) cellValue = "";
                else if(o instanceof Date) cellValue = new SimpleDateFormat(datePattern).format(o);
                else if(o instanceof Float || o instanceof Double)
                    cellValue= new BigDecimal(o.toString()).setScale(2,BigDecimal.ROUND_HALF_UP).toString();
                else cellValue = o.toString();

                newCell.setCellValue(cellValue);
                newCell.setCellStyle(cellStyle);
            }
            rowIndex++;
        }
        // 自动调整宽度
        /*for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }*/
        try {
            workbook.write(out);
            workbook.close();
            workbook.dispose();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            int count = 10;
            String title = "测试";
            long start = System.currentTimeMillis();

            //组装标题头
            Map<String,String> headMap = new LinkedHashMap<String,String>();
            headMap.put("id","编号");
            headMap.put("name","姓名");
            headMap.put("age","年龄");

            //组装数据
            JSONArray ja = new JSONArray();
            for(int i=0;i<count;i++){
                Student s = new Student();
                s.setId(i);
                s.setName("POI"+i);
                s.setAge(i);
                ja.add(s);
            }

            OutputStream outXlsx = null;
            //一、作为浏览器下载的方法：
            //URL url = ExcelUtils.class.getClassLoader().getResource("tmpl.xlsx");//读取一个工程里的空文件作为模板
            //File file = new File(url.getFile());
            //outXlsx = new FileOutputStream(file);
            //HttpServletResponse response = null;//默认要从springMVC Controller类的方法参数里获取
            //ExcelUtils.downloadExcelFile(title,headMap,ja,response);

            //二、导出到系统目录里
            outXlsx = new FileOutputStream("D://"+title+".xlsx");
            ExcelUtils.exportExcel(title,headMap,ja,null,0,outXlsx);
            System.out.println("导出"+ja.size()+"条数据，耗时："+(System.currentTimeMillis()-start)+"ms");
            outXlsx.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}