package com.max.springmvc.converter;

import com.max.springmvc.restful.model.EmployeeDAO;
import com.max.springmvc.restful.model.EmployeeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Controller
public class SpringMVCTest {

    @Autowired
    private EmployeeDAO emdao;

    @Autowired
    private ResourceBundleMessageSource messageSource;

    @RequestMapping(value = "testFileUpload")
    public String testFileUpload(@RequestParam("desc") String desc, @RequestParam("file") MultipartFile file) throws IOException{

        System.out.println("desc = " + desc);
        System.out.println("原始文件名稱 = " + file.getOriginalFilename());
        System.out.println("原始文件輸入流 = " + file.getInputStream());
        return "success";
    }

    @RequestMapping(value = "/i18n")
    public String testI18n(Locale locale){
        String val = messageSource.getMessage("i18n.user",null,locale);
        System.out.println("val = " + val);
        return "i18n";
    }

    @RequestMapping(value = "/testResponseEntity")
    public ResponseEntity<byte[]> testResponseEntity(HttpSession session) throws IOException {
        byte [] body = null;
        ServletContext servletContext = session.getServletContext();
        InputStream is = servletContext.getResourceAsStream("/files/article.txt");
        body = new byte[is.available()];
        is.read(body);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Cotent-Disposition","attachment;filename=article.txt");  //下載

        HttpStatus statusCode = HttpStatus.OK;


        ResponseEntity<byte[]> response = new ResponseEntity<>(body,headers,statusCode);
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/testHttpMessageConverter")
    public String testHttpMessageConverter(@RequestBody String body){
        System.out.println("body = " + body);
        return "helloworld" + new Date();   //return的字串不是返回的頁面名稱，而是把字直接回傳給客戶端
    }

    @ResponseBody   ///可以把請求結果寫入Http的ResponseBody
    @RequestMapping("/testJson")
    public List<EmployeeVO> testJson(){

        return emdao.getAll();
    }

    @RequestMapping(value = "/testConversionServiceConverter")
    public String testConverter(@RequestParam("employee") EmployeeVO employee){
        System.out.println("新增:" + employee);
        emdao.save(employee);
        return "redirect:/emps";
    }
}
