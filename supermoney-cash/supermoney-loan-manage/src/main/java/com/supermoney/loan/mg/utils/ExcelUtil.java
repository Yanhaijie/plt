package com.supermoney.loan.mg.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by wenyuhao on 2018-04-12.
 */
@Component
public class ExcelUtil{

    private final static String excel2003L =".xls";    //2003- 版本的excel
    private final static String excel2007U =".xlsx";   //2007+ 版本的excel

    /**
     * @param response
     * @param fileName excel文件名
     * @param headMap  表头map
     * @param dataList 表格数据
     */
    public static void exportXlsx(HttpServletResponse response, String fileName, Map<String, String> headMap, List<Map<String, Object>> dataList,Map<String, Object> searchMap) {
        Workbook workbook = exportXlsx(fileName, headMap, dataList,searchMap);
        response.setContentType("application/binary;charset=ISO8859_1");
        OutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            String fn = new String(fileName.getBytes(), "ISO8859_1");
            response.setHeader("Content-disposition", "attachment; filename=" + fn + ".xlsx");
            workbook.write(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 导出数据
     *
     * @param headMap
     * @param dataList
     */
    public static Workbook exportXlsx(String sheetName, Map<String, String> headMap, List<Map<String, Object>> dataList,Map<String, Object> searchMap) {
        //自定义表头
        String[] titleList = null;
        if (searchMap != null && searchMap.get("titleIndex") != null){
            String titleIndex = searchMap.get("titleIndex").toString().trim();
            if(StringUtils.isNotEmpty(titleIndex)){
                titleList = titleIndex.split(",");
            }
        }
        //默认表头
        if (headMap == null && dataList != null && dataList.size() > 0){ //采用数据集中的key作为表头
            headMap = new LinkedHashMap<String, String>();
            Map<String,Object> objectMap = dataList.get(0);
            for (String key : objectMap.keySet()) {
                headMap.put(key,key);
            }
        }
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet(sheetName);
        int rowIndex = 0, columnIndex = 0;
        Set<String> keys = headMap.keySet();
        //表头
        Row row = sheet.createRow(rowIndex++);
        if(titleList != null){
            for (String key : titleList) {
                Cell cell = row.createCell(columnIndex++);
                cell.setCellValue(headMap.get(key));
            }
        }else{
            for (String key : keys) {
                Cell cell = row.createCell(columnIndex++);
                cell.setCellValue(headMap.get(key));
            }
        }
        //内容
        if (dataList != null && !dataList.isEmpty()) {
            for (Map<String, Object> map : dataList) {
                row = sheet.createRow(rowIndex++);
                columnIndex = 0;
                if(titleList != null){
                    for (String key : titleList) {
                        Cell cell = row.createCell(columnIndex++);
                        setCellValue(cell, map.get(key));
                    }
                }else{
                    for (String key : keys) {
                        Cell cell = row.createCell(columnIndex++);
                        setCellValue(cell, map.get(key));
                    }
                }
            }
        }
        return workbook;
    }

    private static void setCellValue(Cell cell, Object obj) {
        if (obj == null) {
            return;
        }
        if (obj instanceof String) {
            cell.setCellValue((String) obj);
        } else if (obj instanceof Date) {
            Date date = (Date) obj;
            if (date != null) {
                cell.setCellValue(DateFormatUtils.format(date,"yyyy-MM-dd HH:mm:ss"));
            }
        } else if (obj instanceof Calendar) {
            Calendar calendar = (Calendar) obj;
            if (calendar != null) {
                cell.setCellValue(DateFormatUtils.format(calendar,"yyyy-MM-dd HH:mm:ss"));
            }
        } else if (obj instanceof Timestamp) {
            Timestamp timestamp = (Timestamp) obj;
            if (timestamp != null) {
                cell.setCellValue(DateFormatUtils.format(timestamp.getTime(),"yyyy-MM-dd HH:mm:ss"));
            }
        } else if (obj instanceof Double) {
            cell.setCellValue((Double) obj);
        } else {
            cell.setCellValue(obj.toString());
        }
    }

    /**
     * 获取excel内容中的某一列
     * @param in
     * @param fileName
     * @param columnIdx
     * @return
     */
    public static  List<Object> getListByExcel(InputStream in, String fileName,int columnIdx,int rowIdx)throws Exception{
        List<Object> result=new ArrayList<>();
        List<List<Object>> list=getListByExcel(in,fileName);

        for(int i=rowIdx;i<list.size();i++){
            List<Object> item=list.get(i);
            result.add( item.get(columnIdx));
        }

        return  result;
    }
    /**
     * 读取excel内容,装成 List<List<Object>>
     * @param in
     * @param fileName
     * @return
     * @throws Exception
     */
    public static   List<List<Object>> getListByExcel(InputStream in, String fileName) throws Exception{
        List<List<Object>> list = null;

        //创建Excel工作薄
        Workbook work = getWorkbook(in,fileName);
        if(null == work){
            throw new Exception("创建Excel工作薄为空！");
        }
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;

        list = new ArrayList<List<Object>>();
        //遍历Excel中所有的sheet
        for (int i = 0; i <work.getNumberOfSheets(); i++) {
            sheet = work.getSheetAt(i);
            if(sheet==null){continue;}

            //遍历当前sheet中的所有行
            for (int j = sheet.getFirstRowNum(); j <=sheet.getLastRowNum(); j++) {
                row = sheet.getRow(j);
                if(row==null){continue;}

                //遍历所有的列
                List<Object> li = new ArrayList<Object>();
                for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
                    cell = row.getCell(y);
                    li.add(getCellValue(cell));
                }
                list.add(li);
            }
        }
        work.close();
        return list;
    }
    /**
     * 描述：根据文件后缀，自适应上传文件的版本
     * @param inStr,fileName
     * @return
     * @throws Exception
     */
    public static Workbook getWorkbook(InputStream inStr,String fileName) throws Exception{
        Workbook wb = null;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if(excel2003L.equals(fileType)){
            wb = new HSSFWorkbook(inStr);  //2003-
        }else if(excel2007U.equals(fileType)){
            wb = new XSSFWorkbook(inStr);  //2007+
        }else{
            throw new Exception("解析的文件格式有误！");
        }
        return wb;
    }
    /**
     * 描述：对表格中数值进行格式化
     * @param cell
     * @return
     */
    private static final int CELL_TYPE_BLANK=3;//Cell.CELL_TYPE_BLANK;
    private static final  int CELL_TYPE_STRING=1;//Cell.CELL_TYPE_STRING;
    private static final  int CELL_TYPE_NUMERIC=0;//Cell.CELL_TYPE_NUMERIC;
    private static final  int CELL_TYPE_BOOLEAN=4;//Cell.CELL_TYPE_BOOLEAN;
    public static   Object getCellValue(Cell cell){
        Object value = null;
        DecimalFormat df = new DecimalFormat("0");  //格式化number String字符
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");  //日期格式化
        DecimalFormat df2 = new DecimalFormat("0.00");  //格式化数字
        int cellType=cell.getCellType().getCode();
        switch (cellType) {
            case CELL_TYPE_STRING:
                value = cell.getRichStringCellValue().getString();
                break;
            case CELL_TYPE_NUMERIC:
                if("General".equals(cell.getCellStyle().getDataFormatString())){
                    value = df.format(cell.getNumericCellValue());
                }else if("m/d/yy".equals(cell.getCellStyle().getDataFormatString())){
                    value = sdf.format(cell.getDateCellValue());
                }else{
                    value = df2.format(cell.getNumericCellValue());
                }
                break;
            case CELL_TYPE_BOOLEAN:
                value = cell.getBooleanCellValue();
                break;
            case CELL_TYPE_BLANK:
                value = "";
                break;
            default:
                break;
        }
        return value;
    }

}