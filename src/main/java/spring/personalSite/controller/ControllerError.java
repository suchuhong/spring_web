package spring.personalSite.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.personalSite.util.Utils;

@Controller
@RequestMapping("/error")
public class ControllerError implements ErrorController {

    @Override
    public String getErrorPath() {
        Utils.log("进入自定义错误页面");
        return "error/error";
    }

    @RequestMapping
    public String error() {
        return getErrorPath();
    }
}