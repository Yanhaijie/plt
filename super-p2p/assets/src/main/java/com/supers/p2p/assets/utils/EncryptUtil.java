package com.supers.p2p.assets.utils;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
import java.sql.Timestamp;
import java.util.*;

public class EncryptUtil {
    private final byte[] DESIV = new byte[] { 0x12, 0x34, 0x56, 120, (byte) 0x90, (byte) 0xab, (byte) 0xcd, (byte) 0xef };// 向量

    private AlgorithmParameterSpec iv = null;// 加密算法的参数接口
    private Key key = null;

    private String charset = "utf-8";

    /**
     * 初始化
     * @param deSkey
     * @param charset
     * @throws Exception
     */
    public EncryptUtil(String deSkey, String charset) throws Exception {
        if (StringUtils.isNotBlank(charset)) {
            this.charset = charset;
        }
        DESKeySpec keySpec = new DESKeySpec(deSkey.getBytes(this.charset));// 设置密钥参数
        iv = new IvParameterSpec(DESIV);// 设置向量
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");// 获得密钥工厂
        key = keyFactory.generateSecret(keySpec);// 得到密钥对象
    }

    /**
     * 加密
     * @param data
     * @return
     * @throws Exception
     */
    public String encode(String data) throws Exception {
        Cipher enCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");// 得到加密对象Cipher
        enCipher.init(Cipher.ENCRYPT_MODE, key, iv);// 设置工作模式为加密模式，给出密钥和向量
        byte[] pasByte = enCipher.doFinal(data.getBytes("utf-8"));
        BASE64Encoder base64Encoder = new BASE64Encoder();
        return base64Encoder.encode(pasByte);
    }

    /**
     * 解密
     * @param data
     * @return
     * @throws Exception
     */
    public String decode(String data) throws Exception {
        Cipher deCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        deCipher.init(Cipher.DECRYPT_MODE, key, iv);
        BASE64Decoder base64Decoder = new BASE64Decoder();
        byte[] pasByte = deCipher.doFinal(base64Decoder.decodeBuffer(data));
        return new String(pasByte, "UTF-8");
    }

    public static void main(String[] args) {
        try {
            String test = "ershuai";
            String key = "9ba45bfd500642328ec03ad8ef1b6e75";// 自定义密钥
            EncryptUtil des = new EncryptUtil(key, "utf-8");
            System.out.println("加密前的字符：" + test);
            System.out.println("加密后的字符：" + des.encode(test));
            System.out.println("解密后的字符：" + des.decode(des.encode(test)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Created by wenyuhao on 2018-04-12.
     */
    @Component
    public static class ExcelUtil1 {

        /**
         * @param response
         * @param fileName excel文件名
         * @param headMap  表头map
         * @param dataList 表格数据
         */
        public static void exportXlsx(HttpServletResponse response, String fileName, Map<String, String> headMap, List<Map<String, Object>> dataList, Map<String, Object> searchMap) {
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
                if(org.apache.commons.lang.StringUtils.isNotEmpty(titleIndex)){
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
         * @param response
         * @param fileName excel文件名
         * @param dataList 表格数据
         */
        public static void exportXlsx(HttpServletResponse response, String fileName, String[] titles, List<Map<String, Object>> dataList,String[] dataKeys) {
            Workbook workbook = exportXlsx(fileName, titles, dataList,dataKeys);
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
         * @param dataList
         */
        public static Workbook exportXlsx(String sheetName,  String[] titles, List<Map<String, Object>> dataList, String[] dataKeys) {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet(sheetName);
            int rowIndex = 0, columnIndex = 0;
            //表头
            Row row = sheet.createRow(rowIndex++);
            for (int i = 0; i < titles.length; i++) {
                Cell cell = row.createCell(columnIndex++);
                cell.setCellValue(titles[i]);
            }
            //内容
            if (dataList != null && !dataList.isEmpty()) {
                for (Map<String, Object> map : dataList) {
                    row = sheet.createRow(rowIndex++);
                    columnIndex = 0;
                    for (int i = 0; i < dataKeys.length; i++) {
                        Cell cell = row.createCell(columnIndex++);
                        setCellValue(cell, map.get(dataKeys[i]));
                    }
                }
            }
            return workbook;
        }
    }
}
