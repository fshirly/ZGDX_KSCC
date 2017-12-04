package com.fable.kscc.bussiness.service.livebroadapprove;


import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STVerticalJc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fable.kscc.api.model.livebroadcast.FbsLiveBroadcast;
import com.mysql.jdbc.util.Base64Decoder;
@Service
public class ExportServiceImpl implements IExportService {
	private Logger logger = LoggerFactory.getLogger(ExportServiceImpl.class);

	@Override
	public void export2Word(HttpServletRequest request,
			HttpServletResponse response) {
		String title = request.getParameter("title");
		String subtitle = request.getParameter("subtitle");
		String colum = request.getParameter("colNames");
		String fields = request.getParameter("colFields");
		
		XWPFDocument doc=new XWPFDocument();
		//创建一个段落
	    XWPFParagraph para = doc.createParagraph();
	    para.setAlignment(ParagraphAlignment.CENTER);
	    //一个XWPFRun代表具有相同属性的一个区域。
	    XWPFRun run = para.createRun();
	    run.setBold(true); //加粗
	    run.setText(title);
	    run.setFontSize(20);
		// 设置使用何种字体
	    run.setFontFamily("Courier");
		// 设置上下两行之间的间距
	    run.setTextPosition(20);

	  //创建一个段落
	    XWPFParagraph para1 = doc.createParagraph();
	    para1.setAlignment(ParagraphAlignment.LEFT);
	    //一个XWPFRun代表具有相同属性的一个区域。
	    XWPFRun run1 = para1.createRun();
	    run1.setText(" " + subtitle.replace(",", "	"));
	    run1.setFontSize(12);
	    // 设置上下两行之间的间距
	    run.setTextPosition(20);

	    FbsLiveBroadcast list = (FbsLiveBroadcast) request.getSession().getAttribute("resultlist");
		//所有参与方信息
		String nameValue = "";
		List<Map<String, Object>> mapParticipants = (List<Map<String, Object>>)request.getSession().getAttribute("participantList");
		for(Map<String,Object> mapBean : mapParticipants){
			String name = mapBean.get("hospitalName").toString();
			nameValue += name+"，";
		}

		String[] columName = colum.split(",");//标题栏汉字

		XWPFParagraph para2 = doc.createParagraph();
		para2.setAlignment(ParagraphAlignment.LEFT);
		//一个XWPFRun代表具有相同属性的一个区域。
		XWPFRun run2 = para2.createRun();
		run2.setText("直播名称："+list.getTitle().trim());//直播名称
		run2.addBreak();
		run2.setTextPosition(20);

		//创建一个段落
		XWPFParagraph para3 = doc.createParagraph();
		para3.setAlignment(ParagraphAlignment.LEFT);
		//一个XWPFRun代表具有相同属性的一个区域。
		XWPFRun run3 = para3.createRun();
		run3.setText("申请方："+list.getName().trim());
		run3.addBreak();
		run3.setTextPosition(20);

		//创建一个段落
		XWPFParagraph para4 = doc.createParagraph();
		para4.setAlignment(ParagraphAlignment.LEFT);
		//一个XWPFRun代表具有相同属性的一个区域。
		XWPFRun run4 = para4.createRun();
		run4.setText("申请时间："+list.getCreatedTime().substring(0,16).trim());//申请时间
		run4.addBreak();
		run4.setTextPosition(20);

		XWPFParagraph para5 = doc.createParagraph();
		para5.setAlignment(ParagraphAlignment.LEFT);
		//一个XWPFRun代表具有相同属性的一个区域。
		XWPFRun run5 = para5.createRun();
		run5.setText("直播开始时间："+list.getStartTime().substring(0,16).trim());//直播开始时间
		run5.addBreak();
		run5.setTextPosition(20);

		XWPFParagraph para6 = doc.createParagraph();
		para6.setAlignment(ParagraphAlignment.LEFT);
		//一个XWPFRun代表具有相同属性的一个区域。
		XWPFRun run6 = para6.createRun();
		run6.setText("直播结束时间："+list.getEndTime().substring(0,16).trim());//直播开始时间
		run6.addBreak();
		run6.setTextPosition(20);

		String email = list.getEmail();
		String emailValue = "";
		if(email == ""||email==null){
			emailValue = "空";
		}else{
			emailValue = list.getEmail();
		}
		XWPFParagraph para7 = doc.createParagraph();
		para7.setAlignment(ParagraphAlignment.LEFT);
		//一个XWPFRun代表具有相同属性的一个区域。
		XWPFRun run7 = para7.createRun();
		run7.setText("邮箱："+emailValue.trim());//直播开始时间
		run7.addBreak();
		run7.setTextPosition(20);

        XWPFParagraph para8 = doc.createParagraph();
        para8.setAlignment(ParagraphAlignment.LEFT);
        //一个XWPFRun代表具有相同属性的一个区域。
        XWPFRun run8 = para8.createRun();
        run8.setText("参与方："+nameValue.substring(0,nameValue.length()-1).trim());
        run8.addBreak();
        run8.setTextPosition(20);

        //科室
		String departMentName =list.getDepartmentName().toString();
		String departNameValue ="";
		if(departMentName == ""||departMentName==null){
			departNameValue = "空";
		}else{
			departNameValue = list.getDepartmentName();
		}

		XWPFParagraph para9 = doc.createParagraph();
		para9.setAlignment(ParagraphAlignment.LEFT);
		//一个XWPFRun代表具有相同属性的一个区域。
		XWPFRun run9 = para9.createRun();
		run9.setText("科室："+departNameValue.trim());
		run9.addBreak();
		run9.setTextPosition(20);

		//联系方式

		XWPFParagraph para10 = doc.createParagraph();
		para10.setAlignment(ParagraphAlignment.LEFT);
		//一个XWPFRun代表具有相同属性的一个区域。
		XWPFRun run10 = para10.createRun();
		run10.setText("电话："+list.getPhone().trim());
		run10.addBreak();
		run10.setTextPosition(20);

		//医院网址
		String website = list.getHospitalWebsite();
		String websiteValue = "";
		if(website == ""||website==null){
			websiteValue = "空";
		}else{
			websiteValue = list.getHospitalWebsite();
		}
		XWPFParagraph para12 = doc.createParagraph();
		para12.setAlignment(ParagraphAlignment.LEFT);
		//一个XWPFRun代表具有相同属性的一个区域。
		XWPFRun run12 = para12.createRun();
		run12.setText("医院网址："+websiteValue.trim());
		run12.addBreak();
		run12.setTextPosition(20);

		//医院简介
		String introduction = list.getLiveIntroduction();
		String intValue = "";
		if(introduction == ""||introduction==null){
			intValue = "空";
		}else{
			intValue = list.getLiveIntroduction();
		}
		XWPFParagraph para13 = doc.createParagraph();
		para13.setAlignment(ParagraphAlignment.LEFT);
		//一个XWPFRun代表具有相同属性的一个区域。
		XWPFRun run13 = para13.createRun();
		run13.setText("直播简介："+intValue.trim());
		run13.addBreak();
		run13.setTextPosition(20);

		Map<String,Object> m = new HashMap<String,Object>();
		FbsLiveBroadcast bean = list;
		m.put("0", bean.getId());
		m.put("1", bean.getName());
		m.put("2", bean.getCreatedTime());
		m.put("3", bean.getStartTime());
		m.put("4", bean.getTitle());

		try {
			String docName = request.getParameter("fileName") + ".docx";
			String headerStr = "attachment;filename=" + docName;  
			response.reset();
			response.setContentType("application/vnd.ms-word");  
			response.addHeader("Content-Disposition",new String(headerStr.getBytes("GBK"), "ISO-8859-1"));
			OutputStream out=response.getOutputStream();
			doc.write(out);  
		    out.close();  
		} catch (IOException e) {
			e.printStackTrace();
		}  
	}

	public byte [] base64TOpic(String imgUrl) {
        Base64Decoder decoder = new Base64Decoder();
        byte [] ub = imgUrl.getBytes();
        //Base64解码
        byte[] buffer = decoder.decode(ub, 0, ub.length);

        return buffer;
    }

	public void imgExportExcel(XSSFWorkbook workbook , XSSFSheet sheet,String []imgUrls ,int rowStart ,int colNum) {
		if(imgUrls != null && imgUrls.length > 0){
			int r = colNum * 7 / 5; //图片在列宽为十五，行高默认的情况下，图片宽高比大概为7:1
			for(int i=0,len=imgUrls.length;i<len;i++){
				XSSFRow row = sheet.createRow(i * r + rowStart + 5);
				
				XSSFCell cells = row.createCell(0);
		        cells.setCellType(HSSFCell.CELL_TYPE_BLANK);
		        XSSFDrawing patri = sheet.createDrawingPatriarch();
		        XSSFClientAnchor anchor = new XSSFClientAnchor(5, 5, 5, 5,
		                (short) 0, rowStart + 5 + r * i, colNum, rowStart + r + 5 + r * i);
		        patri.createPicture(anchor, workbook.addPicture(base64TOpic(imgUrls[i])
		               , XSSFWorkbook.PICTURE_TYPE_PNG));
			}
		}
		
	}
	
	public void listExportExcel(XSSFWorkbook workbook , XSSFSheet sheet ,HttpServletRequest request){
		
		String title = request.getParameter("title");
		String subtitle = request.getParameter("subtitle");
		String col = request.getParameter("colNames");
		String fields = request.getParameter("colFields");
		String merges = request.getParameter("merges");
		int colIndex = 0;
		if(col != null && !col.contains(";")){
			listExportOne(title ,col , subtitle, sheet , workbook , request , fields , merges ,"resultlist");
		}
		else if(col.contains(";")){
			String []c = col.split(";");
			String []f = fields.split(";");
			String []m = merges.split(";");
			XSSFSheet sheet1 = workbook.createSheet(title+"明细");
			sheet1.setDefaultColumnWidth((short) 20);
			listExportOne(title ,c[0] , subtitle, sheet , workbook , request , f[0] , m[0] ,"resultlist");
			listExportOne(title ,c[1] , subtitle, sheet1 , workbook , request , f[1] , m[1] ,"secondresult");
		}
	}
	
	public void listExportOne(String title ,String col ,String subtitle ,XSSFSheet sheet ,XSSFWorkbook workbook ,HttpServletRequest request ,String fields ,String merges ,String listName){
		String [] colName = col.split(",");
		int listSize = 0;
		int colIndex = 0;
		// 产生表格标题行
		XSSFRow row = sheet.createRow(0);
		XSSFCellStyle style = workbook.createCellStyle(); //大标题样式
		
		//style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(XSSFCellStyle.BORDER_NONE);
		style.setBorderLeft(XSSFCellStyle.BORDER_NONE);
		style.setBorderRight(XSSFCellStyle.BORDER_NONE);
		style.setBorderTop(XSSFCellStyle.BORDER_NONE);
		style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		
		// 生成一个字体
		XSSFFont font = workbook.createFont();
		font.setFontHeightInPoints((short) 36);
		style.setFont(font);
		
		// 生成并设置另一个样式
		XSSFCellStyle style2 = workbook.createCellStyle(); //副标题样式
		//style2.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		style2.setBorderBottom(XSSFCellStyle.BORDER_NONE);
		style2.setBorderLeft(XSSFCellStyle.BORDER_NONE);
		style2.setBorderRight(XSSFCellStyle.BORDER_NONE);
		style2.setBorderTop(XSSFCellStyle.BORDER_NONE);
		style2.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		style2.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		
		// 生成另一个字体
		XSSFFont font2 = workbook.createFont();
		
		font.setFontHeightInPoints((short) 18);
		// 把字体应用到当前的样式
		style2.setFont(font2);
		
		XSSFCellStyle style3 = workbook.createCellStyle(); //单元格样式
		//style3.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		style3.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		style3.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		style3.setBorderRight(XSSFCellStyle.BORDER_THIN);
		style3.setBorderTop(XSSFCellStyle.BORDER_THIN);
		style3.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		style3.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		style3.setWrapText(true);
		if(title != null){ //大标题
			XSSFCell cell = row.createCell(0);
			cell.setCellStyle(style);
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, colName.length-1));
			XSSFRichTextString text = new XSSFRichTextString(title);
			cell.setCellValue(text);
			colIndex ++;
		}
		if(subtitle != null){ //副标题
			XSSFRow r = sheet.createRow(colIndex);
			XSSFCell cell = r.createCell(0);
			cell.setCellStyle(style2);
			if(!subtitle.contains(",")){
				sheet.addMergedRegion(new CellRangeAddress(colIndex, colIndex, 0, colName.length-1));
				XSSFRichTextString text = new XSSFRichTextString(subtitle);
				cell.setCellValue(text);
			}
			else{
				String [] subs = subtitle.split(",");
				String colSpan = request.getParameter("colSpan");
				if(colSpan != null && !colSpan.isEmpty()){
					String [] spans = colSpan.split(",");
					sheet.addMergedRegion(new CellRangeAddress(colIndex, colIndex, 0, Integer.parseInt(spans[0])));
					XSSFRichTextString text = new XSSFRichTextString(subs[0]);
					cell.setCellValue(text);
					
					cell = r.createCell(Integer.parseInt(spans[0]) + 1);
					sheet.addMergedRegion(new CellRangeAddress(colIndex, colIndex, colName.length-1 - Integer.parseInt(spans[1]),  colName.length-1));
					text = new XSSFRichTextString(subs[1]);
					cell.setCellValue(text);
				}
			}
			
			colIndex ++;
		}
		XSSFRow colRow = sheet.createRow(colIndex);
		for (short i = 0; i < colName.length; i++) {
			XSSFCell cell = colRow.createCell(i);
			cell.setCellStyle(style3);
			XSSFRichTextString text = new XSSFRichTextString(colName[i]);
			cell.setCellValue(text);
		}
		colIndex ++;
		List<Object> list = (List<Object>) request.getSession().getAttribute(listName);	//从session取出要展示的结果集
		if(list != null && list.size() > 0){
			if(!fields.contains(";")){
				if(list.get(0) instanceof Map){
					exportMap(fields ,list ,colIndex ,sheet ,style3);
				}
				else{
					exportBean(fields ,list ,colIndex ,sheet ,style3);
				}
				listSize = list.size();
				if(merges != null && !merges.contains(";")){
					String [] me = merges.split(",");
					String [] fl = fields.split(",");
					for(int i=0,len=me.length;i<len;i++){
						addMergedRegionX(workbook ,sheet,getColIndex(me[i] ,fl) , colIndex, listSize + colIndex - 1);
					}
				}
			}
		}
		
	}
	
	public void exportBean(String fields ,List<Object> list ,int colIndex ,XSSFSheet sheet ,XSSFCellStyle style3){
		
		if(fields != null){
			String [] fd = fields.split(","); 
			Class<? extends Object> c = list.get(0).getClass(); //通过结果集的第一个元素，取得元素的Class
			Field [] field = c.getDeclaredFields();
			
			if(list != null){
				for(int i=0,len=list.size();i<len;i++){
					XSSFRow r = sheet.createRow(colIndex + i);
					for(int j=0;j<fd.length;j++){
						Object bean = list.get(i);
						for(Field f : field){ //利用反射技术设置单元格的内容
							f.setAccessible(true);
							if(f.getName().equalsIgnoreCase(fd[j])){
								XSSFCell inner = r.createCell(j);
								inner.setCellStyle(style3);
								try {
									XSSFRichTextString text = new XSSFRichTextString(f.get(bean).toString());
									inner.setCellValue(text);
								} catch (IllegalArgumentException e) {
									e.printStackTrace();
								} catch (IllegalAccessException e) {
									e.printStackTrace();
								}
							}
						}
					}
				}
			}
		}
	}
	
	public void exportMap(String fields ,List<Object> list ,int colIndex ,XSSFSheet sheet ,XSSFCellStyle style3){
		
		if(fields != null){
			String [] fd = fields.split(","); 
			if(list != null){
				for(int i=0,len=list.size();i<len;i++){
					XSSFRow r = sheet.createRow(colIndex + i);
					for(int j=0;j<fd.length;j++){
						Map<String ,Object> map = (Map<String, Object>) list.get(i);
						XSSFCell inner = r.createCell(j);
						inner.setCellStyle(style3);
						String content = "";
						if(map.get(fd[j]) == null){
							content = "";
						}
						else
							content = map.get(fd[j]).toString();
						XSSFRichTextString text = new XSSFRichTextString(content);
						inner.setCellValue(text);
					}
				}
			}
		}
	}
	
	/**
	 * 逻辑与前端合并单元格相同，先记录相同的行数，然后根据该行数计算合并开始行和结束行
	 * @param workBook
	 * @param sheet
	 * @param cellLine 要合并的列
	 * @param startRow 开始行
	 * @param endRow   结束行
	 */
	private void addMergedRegion(XSSFWorkbook workBook, XSSFSheet sheet,int []cellLine, int startRow, int endRow) {
		
		XSSFCellStyle style = workBook.createCellStyle();
		style.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		style.setBorderRight(XSSFCellStyle.BORDER_THIN);
		style.setBorderTop(XSSFCellStyle.BORDER_THIN);
		style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		
		// 获取第一行的数据,以便后面进行比较
		String s_will = sheet.getRow(startRow).getCell(cellLine[0])
				.getStringCellValue();

		int count = 0;
		boolean flag = false;
		for (int i = startRow+1; i <= endRow; i++) {
			String s_current = sheet.getRow(i).getCell(cellLine[0]).getStringCellValue();
			if (s_will.equals(s_current)) {
				s_will = s_current;
				if (flag) {
					for(int j=0;j<cellLine.length;j++){
						sheet.addMergedRegion(new CellRangeAddress(
								startRow - count, startRow, cellLine[j], cellLine[j]));
						XSSFRow row = sheet.getRow(startRow - count);
						String cellValueTemp = sheet.getRow(startRow - count).getCell(cellLine[j]).getStringCellValue();
						XSSFCell cell = row.createCell(cellLine[j]);
						cell.setCellValue(cellValueTemp); // 跨单元格显示的数据
						cell.setCellStyle(style);
					}
					count = 0;
					flag = false;
				}
				startRow = i;
				count++;
			} else {
				flag = true;
				s_will = s_current;
			}
			// 由于上面循环中合并的单元放在有下一次相同单元格的时候做的，所以最后如果几行有相同单元格则要运行下面的合并单元格。
			if (i == endRow && count > 0) {
				for(int j=0;j<cellLine.length;j++){
					sheet.addMergedRegion(new CellRangeAddress(endRow - count,
							endRow, cellLine[j], cellLine[j]));
					String cellValueTemp = sheet.getRow(startRow - count)
							.getCell(cellLine[j]).getStringCellValue();
					XSSFRow row = sheet.getRow(startRow - count);
					XSSFCell cell = row.createCell(cellLine[j]);
					cell.setCellValue(cellValueTemp); // 跨单元格显示的数据
					cell.setCellStyle(style);
				}
			}
		}
	}
	
	/**
	 * 逻辑与前端合并单元格相同，先记录相同的行数，然后根据该行数计算合并开始行和结束行
	 * @param workBook
	 * @param sheet
	 * @param cellLine 要合并的列
	 * @param startRow 开始行
	 * @param endRow   结束行
	 */
	private void addMergedRegionX(XSSFWorkbook workBook, XSSFSheet sheet,int cellLine, int startRow, int endRow) {
		
		XSSFCellStyle style = workBook.createCellStyle();
		style.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		style.setBorderRight(XSSFCellStyle.BORDER_THIN);
		style.setBorderTop(XSSFCellStyle.BORDER_THIN);
		style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		
		// 获取第一行的数据,以便后面进行比较
		String s_will = sheet.getRow(startRow).getCell(cellLine)
				.getStringCellValue();

		int count = 0;
		boolean flag = false;
		for (int i = startRow + 1; i <= endRow; i++) {
			
			String s_current = sheet.getRow(i).getCell(cellLine).getStringCellValue();
			if (s_will.equals(s_current)) {
				s_will = s_current;
				count++;
				startRow = i;
			} else {
				if(count != 0){
					sheet.addMergedRegion(new CellRangeAddress(
							startRow - count, startRow, cellLine, cellLine));
					XSSFRow row = sheet.getRow(startRow - count);
					String cellValueTemp = sheet.getRow(startRow - count).getCell(cellLine).getStringCellValue();
					XSSFCell cell = row.createCell(cellLine);
					cell.setCellValue(cellValueTemp); // 跨单元格显示的数据
					cell.setCellStyle(style);
				}
				count = 0;
				s_will = s_current;
			}
			// 由于上面循环中合并的单元放在有下一次相同单元格的时候做的，所以最后如果几行有相同单元格则要运行下面的合并单元格。
			if (i == endRow && count > 0) {
					sheet.addMergedRegion(new CellRangeAddress(endRow - count,
							endRow, cellLine, cellLine));
					String cellValueTemp = sheet.getRow(startRow - count)
							.getCell(cellLine).getStringCellValue();
					XSSFRow row = sheet.getRow(startRow - count);
					XSSFCell cell = row.createCell(cellLine);
					cell.setCellValue(cellValueTemp); // 跨单元格显示的数据
					cell.setCellStyle(style);
			}
		}
	}
	
	private int getColIndex(String col ,String []fields){
		int index = 0;
		for(String f : fields){
			if(col.equals(f)) break;
			index ++;
		}
		return index;
	}
	
	public String getFieldValue(FbsLiveBroadcast obj,String fieldName){
		// 利用Java反射获得指标对应的值
		try {
			Field field = obj.getClass().getDeclaredField(fieldName);
			field.setAccessible(true);// 修改访问权限
			if(field != null){
				String result = "";
				result = String.valueOf(field.get(obj)) ;
				return result;
			}
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public void setCellText(XWPFTableCell cell) {
    	CTTc cttc =cell.getCTTc();
        CTTcPr ctPr = cttc.isSetTcPr() ? cttc.getTcPr() : cttc.addNewTcPr();
        ctPr.addNewVAlign().setVal(STVerticalJc.CENTER);
        cttc.getPList().get(0).addNewPPr().addNewJc().setVal(STJc.CENTER);
	}

}
