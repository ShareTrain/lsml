package com.lisong.component.office.excel;

import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFSheet;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Slf4j
public class ExcelWriterFactroy extends CustomExcelWriter {

    private OutputStream outputStream;
    private int sheetNo = 1;

    public ExcelWriterFactroy(OutputStream outputStream, ExcelTypeEnum typeEnum) {
        super(outputStream, typeEnum);
        this.outputStream = outputStream;
    }

    public ExcelWriterFactroy write(
            List<? extends BaseRowModel> list, String sheetName, BaseRowModel object) {
        this.sheetNo++;
        try {
            Sheet sheet = new Sheet(sheetNo, 0, object.getClass());
            sheet.setSheetName(sheetName);
            this.write(list, sheet);
        } catch (Exception ex) {
            log.error("【ExcelWriterFactroy】创建Sheet异常！", ex);
            try {
                outputStream.flush();
            } catch (IOException e) {
                log.error("【ExcelWriterFactroy】outputStream flush异常！", e);
            }
        }
        return this;
    }

    public ExcelWriterFactroy mergeCellRegion(List<CellRangeAddress> cellRangeAddressList) {
        SXSSFSheet oSheet = (SXSSFSheet) this.getContext().getCurrentSheet();
        cellRangeAddressList.forEach(oSheet::addMergedRegion);
        return this;
    }

    public ExcelWriterFactroy removeCell(List<Integer> cellIndexList) {
        SXSSFSheet oSheet = (SXSSFSheet) this.getContext().getCurrentSheet();

        cellIndexList.forEach(
                cellIndex -> {
                    for (int rId = 0; rId <= oSheet.getLastRowNum(); rId++) {
                        Row row = oSheet.getRow(rId);
                        for (int cId = cellIndex; cId <= row.getLastCellNum(); cId++) {
                            Cell cell = row.getCell(cId);
                            if (cell != null) {
                                row.removeCell(cell);
                            }
                        }
                    }
                });
        return this;
    }

    @Override
    public void finish() {
        super.finish();
        try {
            outputStream.flush();
        } catch (IOException e) {
            log.error("【ExcelWriterFactroy】outputStream flush异常！", e);
        }
    }
}
