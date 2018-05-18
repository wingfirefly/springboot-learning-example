package demo.springboot.web;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Spring Boot Hello案例
 *
 * Created by bysocket on 26/09/2017.
 */
@RestController
public class HelloBookController {

    @RequestMapping(value = "/book/hello",method = RequestMethod.GET)
    public String sayHello() {
        return "Hello，《Spring Boot 2.x 核心技术实战 - 上 基础篇》！";
    }
    
    @RequestMapping(value = "/xxniu/hello/get",method = RequestMethod.GET)
    public String httpGet(@RequestParam Long id) {
    	return "{\"status\":0,\"msg\":\"SUCCESS\",\"id\":\""+ id +"\"}";
    }
    @RequestMapping(value = "/xxniu/hello/post",method = RequestMethod.POST)
    public String httpPost() {
    	return "{\"status\":0,\"msg\":\"SUCCESS\"}";
    }
}
