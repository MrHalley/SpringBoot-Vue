package org.sang.exception;

import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.sang.model.UploadMessage;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @ControllerAdvice 全局异常处理
 */
@ControllerAdvice
public class CustomExceptionHandler {
    /**
     * 处理MaxUploadSizeExceededException类型的异常
     * 返回值可以是一段json、一个ModelAndView、一个逻辑视图名等
     * */
    //@ExceptionHandler(MaxUploadSizeExceededException.class)
    public void uploadException(MaxUploadSizeExceededException e, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=utf-8");
        try(PrintWriter out = resp.getWriter()){
            out.write("上传文件大小超出限制");
            //out.flush();
        }
    }
    //@ExceptionHandler(MaxUploadSizeExceededException.class)
    public String uploadException2(MaxUploadSizeExceededException e, Model model){
        UploadMessage uploadMessage = new UploadMessage();
        uploadMessage.setStatus(1);
        uploadMessage.setMessage("文件"+e.getMaxUploadSize()+"上传失败"+e.getMessage());
        model.addAttribute("uploadMessage",uploadMessage);
        return "error";
    }
    //@ExceptionHandler(MaxUploadSizeExceededException.class)
    public ModelAndView uploadException3(MaxUploadSizeExceededException e){
        ModelAndView mv = new ModelAndView();
        UploadMessage uploadMessage = new UploadMessage();
        uploadMessage.setStatus(0);
        uploadMessage.setMessage("文件上传失败"+e.getMaxUploadSize());
        mv.addObject("uploadMessage",uploadMessage);
        mv.setViewName("error");
        return mv;
    }
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseBody
    public UploadMessage uploadMessage4(){
        UploadMessage uploadMessage = new UploadMessage();
        uploadMessage.setStatus(0);
        uploadMessage.setMessage("文件上传失败，超出文件大小限制");
        return uploadMessage;
    }
}
