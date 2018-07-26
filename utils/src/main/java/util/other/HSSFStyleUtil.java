package util.other;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HSSFStyleUtil {

    private static volatile HSSFStyleUtil hssfStyleUtil = null;

    /**
     * 单元格样式
     * @param workbook
     * @return
     */
    public HSSFCellStyle getStyle(HSSFWorkbook workbook){
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        HSSFFont font = getFontStyle(workbook,"微软雅黑");
//        cellStyle.setFillForegroundColor(HSSFBorderFormatting.BORDER_MEDIUM);
        cellStyle.setFont(font);
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);        //单元格垂直居中
//        cellStyle.setAlignment(CellStyle.ALIGN_CENTER); // 水平布局：居中
//        cellStyle.setWrapText(true);
        return cellStyle;
    }

    /**
     * 字体样式
     * @param workbook
     * @return
     */
    public HSSFFont getFontStyle(HSSFWorkbook workbook, String...fName){
        HSSFFont font = workbook.createFont();
        if (fName==null){
            font.setFontName("Arial");
        }
        String s = font.getFontName();
        Pattern pattern = Pattern.compile("[a-zA-Z]");
        Matcher matcher = pattern.matcher(""+s);
        while (matcher.find()){
            if (matcher.group()==null){//中文或者非英文
                font.setCharSet(HSSFFont.DEFAULT_CHARSET);//设置中文字体，那必须还要再对单元格进行编码设置
            }
        }
        font.setFontHeightInPoints((short)12);                //字体大小
//        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);             //加粗
        return font;
    }

    private HSSFStyleUtil(){}

    public static HSSFStyleUtil getHssfStyleUtil() {
        if (hssfStyleUtil==null)hssfStyleUtil=new HSSFStyleUtil();
        return hssfStyleUtil;
    }
}
