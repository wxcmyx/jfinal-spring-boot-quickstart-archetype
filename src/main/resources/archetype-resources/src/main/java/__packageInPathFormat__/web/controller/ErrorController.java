package ${package}.web.controller;

import org.sidao.springboot.web.rest.Result;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
@EnableAutoConfiguration
public class ErrorController {

    @RequestMapping("/404")
    @ResponseBody
    public Result error404() {
        return new Result().set404();
    }
    @RequestMapping("/500")
    @ResponseBody
    public Result error500() {
        return new Result().set500();
    }
}
