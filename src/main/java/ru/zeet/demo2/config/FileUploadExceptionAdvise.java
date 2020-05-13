package ru.zeet.demo2.config;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//@ControllerAdvice
public class FileUploadExceptionAdvise {

    @ExceptionHandler()
    public String handleMaxSizeException(Model model) {
        model.addAttribute("message", "File too lage");
        return "image";
    }
}
