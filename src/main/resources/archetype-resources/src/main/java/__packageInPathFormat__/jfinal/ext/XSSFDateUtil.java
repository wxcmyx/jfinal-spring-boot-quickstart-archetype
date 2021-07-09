${package}jfinal.ext;

import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;

import java.util.Date;

public class XSSFDateUtil {
    public static boolean isCellDateFormatted(XSSFCell xssfCell) {
        return DateUtil.isCellDateFormatted(xssfCell);
    }

    public static Date getJavaDate(double numericCellValue) {
        return new Date(Long.parseLong(String.valueOf(numericCellValue)));
    }
}
