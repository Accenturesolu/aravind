package com.accenture.sts.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.accenture.sts.entity.Employee;
import com.accenture.sts.entity.Upload_ticket;
import com.accenture.sts.exception.STSGenericException;
import com.accenture.sts.message.Messages;
import com.accenture.sts.service.LoginService;
import com.accenture.sts.service.TicketService;
import com.accenture.sts.service.UploadService;


/**
 * 
 * @author kaviarasan.kannairam
 * @since - 25-01-2017
 * @version - 1.0
 */
@Controller
public class UploadController {
	private static Logger log = Logger.getLogger(StatusController.class);
	@Autowired
	private UploadService uploadService;
	@Autowired
	private LoginService loginService;
	@Autowired
	private TicketService ticketService;
	
	
	/**
	 * This method is to upload the tickets
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return - ModelAndView
	 * @throws STSGenericException
	 */
	@RequestMapping(value="/upload.htm", method=RequestMethod.POST)
	public ModelAndView SaveOrUpdateEvent(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("file") MultipartFile file) throws STSGenericException{
		HttpSession session = request.getSession();
		ModelAndView modelandview = new ModelAndView("uploadfile");
		
		Messages message = new Messages();
		
		String emp_Id = (String) session.getAttribute("emp_ID");
		System.out.println(emp_Id);
		
		

		Employee employee = new Employee();
		employee = ticketService.getEmployeeDetails(emp_Id);
		String employeename = employee.getEmployee_name();
		System.out.println(employeename);
		try{
		
		final String FILE_NAME = file.getOriginalFilename();
		//System.out.println("Success"+FILE_NAME);
		if((FILE_NAME.endsWith(".xlsx"))||(FILE_NAME.endsWith(".xlsm")))
		{
	    //System.out.println("Inside If"+FILE_NAME);
		InputStream in = file.getInputStream();
		//System.out.println("file:"+in);
		Workbook workbook  = new XSSFWorkbook(in);
		List<Upload_ticket> tUpload = uploadService.readData(workbook.getSheetAt(0),employeename);
		in.close();
		boolean okToUpload = true;
		//System.out.println("tUpload Size :" +tUpload.size());
		if(tUpload.size()== 0){
			okToUpload = false;
			
		}
		if(okToUpload){
			for(int i = 0; i < tUpload.size(); i++) {
				uploadService.saveorupdate(tUpload.get(i));
			}
		}
		List<Upload_ticket> showDate = loginService.showDate();
		List<Upload_ticket> showCreate = loginService.showCreate();
		
		modelandview.addObject("employeename", employeename);
		modelandview.addObject("showDate", showDate);
		modelandview.addObject("showCreate", showCreate);
		
		modelandview.addObject("SUCCESS", message.getUploadsuccess());
		log.info("Successfully uploaded");
		}
		else{
			modelandview.addObject("MESSAGE", message.getUploadfail());
		}
		System.out.println("Outside If :" +modelandview);
		
		
	}catch(IOException e){
		e.printStackTrace();
	}
		
	return modelandview ;
	}
}