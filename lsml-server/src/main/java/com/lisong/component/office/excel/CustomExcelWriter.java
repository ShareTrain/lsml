package com.lisong.component.office.excel;

import com.alibaba.excel.context.GenerateContext;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.Table;
import com.alibaba.excel.parameter.GenerateParam;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.ExcelBuilder;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class CustomExcelWriter {

    private ExcelBuilder excelBuilder;

    public CustomExcelWriter(OutputStream outputStream, ExcelTypeEnum typeEnum) {
        this(outputStream, typeEnum, true);
    }

    private Class<? extends BaseRowModel> objectClass;

    private String sheetName;

    public CustomExcelWriter(GenerateParam generateParam) {
        this(generateParam.getOutputStream(), generateParam.getType(), true);
        this.objectClass = generateParam.getClazz();
        this.sheetName = generateParam.getSheetName();
    }

    public CustomExcelWriter(OutputStream outputStream, ExcelTypeEnum typeEnum, boolean needHead) {
        excelBuilder = new CustomExcelBuilderImpl();
        excelBuilder.init(null, outputStream, typeEnum, needHead);
    }

    public CustomExcelWriter(
            InputStream templateInputStream,
            OutputStream outputStream,
            ExcelTypeEnum typeEnum,
            boolean needHead) {
        excelBuilder = new CustomExcelBuilderImpl();
        excelBuilder.init(templateInputStream, outputStream, typeEnum, needHead);
    }

    public CustomExcelWriter write(List<? extends BaseRowModel> data, Sheet sheet) {
        excelBuilder.addContent(data, sheet);
        return this;
    }

    @Deprecated
    public CustomExcelWriter write(List data) {
        if (objectClass != null) {
            return this.write(data, new Sheet(1, 0, objectClass));
        } else {
            return this.write0(data, new Sheet(1, 0, objectClass));
        }
    }

    public CustomExcelWriter write1(List<List<Object>> data, Sheet sheet) {
        excelBuilder.addContent(data, sheet);
        return this;
    }

    public CustomExcelWriter write0(List<List<String>> data, Sheet sheet) {
        excelBuilder.addContent(data, sheet);
        return this;
    }

    public CustomExcelWriter write(List<? extends BaseRowModel> data, Sheet sheet, Table table) {
        excelBuilder.addContent(data, sheet, table);
        return this;
    }

    public CustomExcelWriter write0(List<List<String>> data, Sheet sheet, Table table) {
        excelBuilder.addContent(data, sheet, table);
        return this;
    }

    public CustomExcelWriter write1(List<List<Object>> data, Sheet sheet, Table table) {
        excelBuilder.addContent(data, sheet, table);
        return this;
    }

    public void finish() {
        excelBuilder.finish();
    }

    public GenerateContext getContext() {
        return ((CustomExcelBuilderImpl) excelBuilder).getContext();
    }
}
