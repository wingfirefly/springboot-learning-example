package org.spring.springboot.web;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Spring Boot HelloWorld 案例
 *
 * Created by bysocket on 16/4/26.
 */
@RestController
public class HelloWorldController {

    @RequestMapping("/api/{id}")
    public String sayHello(@PathVariable("id") long id) {
        System.out.println(id);
        return "Hello,World!";
    }

    @RequestMapping("/api")
    public String testHello(@RequestBody String userName, @RequestBody String passWd){
        System.out.println(userName + "," + passWd);
        return "{\"123\":\"456\"}";
    }

    @ResponseBody
    @RequestMapping(value = "/api/dataJson", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String getByJSON(@RequestBody JSONObject jsonParam) {
        // 直接将json信息打印出来
        System.out.println(jsonParam.toJSONString());

        // 将获取的json数据封装一层，然后在给返回
        JSONObject result = new JSONObject();
        result.put("msg", "请求成功！");
        result.put("code", "0000");
        result.put("data", jsonParam);

        return result.toJSONString();
    }

    @ResponseBody
    @RequestMapping(value = "/api/data", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String getByRequest(HttpServletRequest request) {

        //获取到JSONObject
        JSONObject jsonParam = this.getJSONParam(request);

        JSONObject result = new JSONObject();
        result.put("msg", "请求成功！");
        result.put("code", "0000");
        result.put("data", jsonParam);

        return result.toJSONString();
    }

    /**
     * 创建日期:2018年4月6日<br/>
     * 代码创建:黄聪<br/>
     * 功能描述:通过request来获取到json数据<br/>
     * @param request
     * @return
     */
    public JSONObject getJSONParam(HttpServletRequest request) {
        JSONObject jsonParam = null;
        try {
            // 获取输入流
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));

            // 写入数据到sb
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = streamReader.readLine()) != null) {
                sb.append(line);
            }
            jsonParam = JSONObject.parseObject(sb.toString());
            // 直接将json信息打印出来
            System.out.println(jsonParam.toJSONString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonParam;
    }
}
