package com.max.springmvc.converter;

import com.max.springmvc.exception.UserNameNotMatchPasswordException;
import com.max.springmvc.restful.model.EmployeeDAO;
import com.max.springmvc.restful.model.EmployeeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Controller
public class SpringMVCTest {

    @Autowired
    private EmployeeDAO emdao;

    @Autowired
    private ResourceBundleMessageSource messageSource;

    //會自動將錯誤訊息放入ModelAndView裡，放入的key為exception
    @RequestMapping(value = "/testSimpleMappingExceptionResolver")
    public String testSimpleMappingExceptionResolver(@RequestParam("i") int i){
        String [] vals = new String[10];
        System.out.println(vals[i]);
        return "success";
    }


    @RequestMapping(value = "/testDefaultHandlerExceptionResolver",method = RequestMethod.POST)
    public String testDefaultHandlerExceptionResolver(){

        System.out.println("測試DefaultHandlerExceptionResolver");
        return "success";
    }

    @ResponseStatus(reason = "測試",value = HttpStatus.NOT_FOUND)
    @RequestMapping(value = "/testResponseStatusExceptionResolver")
    public String testResponseStatusExceptionResolver(@RequestParam("i") int i){
        if(i == 13){
            throw new UserNameNotMatchPasswordException();
        }
        System.out.println("正常執行tResponseStatusExceptionResolver");

        return "success";
    }

//    @ExceptionHandler({RuntimeException.class})
//    public ModelAndView HandleArithmeticException2(Exception ex){
//
//        System.out.println("[例外為]:" + ex);
//        ModelAndView modelAndView = new ModelAndView("error");
//        modelAndView.addObject("exception",ex);
//        return modelAndView;
//    }


    /*
        1.在 @ExceptionHandler方法參數中可以加入Exception類型的參數，該參數即發生異常的對象
        2.@ExceptionHandler的參數中不能傳入Map，若希望把異常訊息傳到頁面上，需要使用MdoeAndView
        3.標記的異常有優先度的問題，EX 會先進入ArithmeticException.class更先進入其父類別的異常
        4.如果在當前handler找不到ExceptionHandler方法來處理當前方法出現的異常，則將去@ControllerAdvice標記的類別中
        找尋@ExceptionHandler標記的方法來處理異常

     */
    @ExceptionHandler({ArithmeticException.class})
    public ModelAndView HandleArithmeticException(Exception ex){

        System.out.println("例外為:" + ex);
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("exception",ex);
        return modelAndView;
    }

    @RequestMapping(value = "/testExceptionHandlerExceptionResolver")
    public String testExceptionHandlerExceptionResolver(@RequestParam("i") int i){
        System.out.println("result = " + (10/i));
        return "success";
    }

    @RequestMapping(value = "/testFileUpload")
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
