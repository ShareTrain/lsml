package com.lisong.component.office.excel;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

@Slf4j
public class ExcelUtils {

    /**
     * 读取 Excel(多个 sheet)
     *
     * @param excel 文件
     * @param rowModel 实体类映射，继承 BaseRowModel 类
     * @return Excel 数据 list
     */
    public static List<Object> readExcel(MultipartFile excel, BaseRowModel rowModel) {
        ExcelListener excelListener = new ExcelListener();
        ExcelReader reader = getReader(excel, excelListener);
        if (reader == null) {
            return null;
        }
        for (Sheet sheet : reader.getSheets()) {
            if (rowModel != null) {
                sheet.setClazz(rowModel.getClass());
            }
            reader.read(sheet);
        }
        return excelListener.getDatas();
    }

    /**
     * 读取某个 sheet 的 Excel
     *
     * @param excel 文件
     * @param rowModel 实体类映射，继承 BaseRowModel 类
     * @param sheetNo sheet 的序号 从1开始
     * @return Excel 数据 list
     */
    public static List<Object> readExcel(MultipartFile excel, BaseRowModel rowModel, int sheetNo) {
        return readExcel(excel, rowModel, sheetNo, 1);
    }

    /**
     * 读取某个 sheet 的 Excel
     *
     * @param excel 文件
     * @param rowModel 实体类映射，继承 BaseRowModel 类
     * @param sheetNo sheet 的序号 从1开始
     * @param headLineNum 表头行数，默认为1
     * @return Excel 数据 list
     */
    public static List<Object> readExcel(
            MultipartFile excel, BaseRowModel rowModel, int sheetNo, int headLineNum) {
        ExcelListener excelListener = new ExcelListener();
        ExcelReader reader = getReader(excel, excelListener);
        if (reader == null) {
            return null;
        }
        reader.read(new Sheet(sheetNo, headLineNum, rowModel.getClass()));
        return excelListener.getDatas();
    }

    /**
     * 读取某个 sheet 的 Excel
     *
     * @param inputStream 文件流
     * @param rowModel 实体类映射，继承 BaseRowModel 类
     * @param sheetNo sheet 的序号 从1开始
     * @param headLineNum 表头行数，默认为1
     * @return Excel 数据 list
     */
    public static List<Object> readExcel(
            InputStream inputStream, BaseRowModel rowModel, int sheetNo, int headLineNum) {
        ExcelListener excelListener = new ExcelListener();
        ExcelReader reader = getReader(inputStream, excelListener);
        reader.read(new Sheet(sheetNo, headLineNum, rowModel.getClass()));
        return excelListener.getDatas();
    }

    /**
     * 导出 Excel ：一个 sheet，带表头
     *
     * @param response HttpServletResponse
     * @param list 数据 list，每个元素为一个 BaseRowModel
     * @param fileName 导出的文件名
     * @param sheetName 导入文件的 sheet 名
     * @param object 映射实体类，Excel 模型
     */
    public static void writeExcelNoHead(
            HttpServletResponse response,
            List<? extends BaseRowModel> list,
            String fileName,
            String sheetName,
            BaseRowModel object) {
        CustomExcelWriter writer =
                new CustomExcelWriter(
                        getOutputStream(fileName, response), ExcelTypeEnum.XLSX, false);
        Sheet sheet = new Sheet(1, 0, object.getClass());
        sheet.setSheetName(sheetName);
        writer.write(list, sheet);
        writer.finish();
    }

    /**
     * 导出 Excel ：一个 sheet，带表头
     *
     * @param response HttpServletResponse
     * @param list 数据 list，每个元素为一个 BaseRowModel
     * @param fileName 导出的文件名
     * @param sheetName 导入文件的 sheet 名
     * @param object 映射实体类，Excel 模型
     */
    public static void writeExcel(
            HttpServletResponse response,
            List<? extends BaseRowModel> list,
            String fileName,
            String sheetName,
            BaseRowModel object) {
        CustomExcelWriter writer =
                new CustomExcelWriter(getOutputStream(fileName, response), ExcelTypeEnum.XLSX);
        Sheet sheet = new Sheet(1, 0, object.getClass());
        sheet.setSheetName(sheetName);
        writer.write(list, sheet);
        writer.finish();
    }

    /**
     * 导出 Excel ：多个 sheet，带表头
     *
     * @param response HttpServletResponse
     * @param list 数据 list，每个元素为一个 BaseRowModel
     * @param fileName 导出的文件名
     * @param sheetName 导入文件的 sheet 名
     * @param object 映射实体类，Excel 模型
     */
    public static ExcelWriterFactroy writeExcelWithSheets(
            HttpServletResponse response,
            List<? extends BaseRowModel> list,
            String fileName,
            String sheetName,
            BaseRowModel object) {
        ExcelWriterFactroy writer =
                new ExcelWriterFactroy(getOutputStream(fileName, response), ExcelTypeEnum.XLSX);
        Sheet sheet = new Sheet(1, 0, object.getClass());
        sheet.setSheetName(sheetName);
        writer.write(list, sheet);
        return writer;
    }

    /** 导出文件时为Writer生成OutputStream */
    private static OutputStream getOutputStream(String fileName, HttpServletResponse response) {
        try {
            response.setContentType("application/octet-stream");
            String encodeFileName =
                    response.encodeURL(
                            new String(fileName.concat(".xlsx").getBytes(), "ISO8859_1"));
            response.setHeader(
                    "Content-disposition",
                    String.format("attachment; filename=%s", encodeFileName));
            return response.getOutputStream();
        } catch (IOException e) {
            log.error("【ExcelUtils】获取应答流失败！", e);
            throw new ExcelException("获取应答流失败！");
        }
    }

    /**
     * 返回 ExcelReader
     *
     * @param excel 需要解析的 Excel 文件
     * @param excelListener new ExcelListener()
     */
    private static ExcelReader getReader(MultipartFile excel, ExcelListener excelListener) {
        String filename = excel.getOriginalFilename();
        if (filename == null
                || (!filename.toLowerCase().endsWith(".xls")
                        && !filename.toLowerCase().endsWith(".xlsx"))) {
            throw new ExcelException("文件格式错误！");
        }
        InputStream inputStream;
        try {
            inputStream = excel.getInputStream();
            return new ExcelReader(inputStream, null, excelListener, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 返回 ExcelReader
     *
     * @param inputStream 需要解析的 Excel 文件流
     * @param excelListener new ExcelListener()
     */
    private static ExcelReader getReader(InputStream inputStream, ExcelListener excelListener) {
        return new ExcelReader(inputStream, ExcelTypeEnum.XLSX, null, excelListener, false);
    }

    /**
     * 下载Excel文件.
     *
     * <p>创建时间: <font style="color:#00FFFF">20190412 12:48</font><br>
     * [请在此输入功能详述]
     *
     * @param fileName - 下载的名称
     * @param response - 输出流
     * @param inputStream - 导出文件的输入流
     * @author Rushing0711
     * @since 1.0.0
     */
    public static void downloadExcel(
            String fileName, HttpServletResponse response, InputStream inputStream) {
        OutputStream outputStream = getOutputStream(fileName, response);
        try {
            FileCopyUtils.copy(inputStream, outputStream);
        } catch (IOException e) {
            log.error("下载文件异常", e);
            throw new ExcelException("下载文件异常:".concat(e.getMessage()));
        }
    }
}
