package cn.jinglingwang.eureka.provider.controller;

import cn.jinglingwang.eureka.provider.dto.UserDTO;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;

/**
 * @author : jingling
 * @Date : 2020/11/24
 */
@Controller
public class HelloController{
    @Value("${server.port}")
    private int serverPort;

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, path = "hello")
    public String hello(HttpServletRequest request) throws UnsupportedEncodingException{
        Enumeration<String> names = request.getHeaderNames();
        while(names.hasMoreElements()){
            System.out.println(names.nextElement());
        }
        String header = request.getHeader("Authorization");
        if(header != null && header.length() > 6){
            String authorization = new String(Base64.decode(header.substring(6).getBytes("UTF-8")),"UTF-8");
            System.out.println(authorization);
        }
        return "hello, my name is eureka provider! my server port:"+serverPort;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, path = "query")
    public String query(UserDTO user){
        String name = user.getName();
        Integer age = user.getAge();
        return "user: " + name + " exists！age:"+age;
    }
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, path = "queryNoReturn")
    public void queryNoReturn(UserDTO user){
        String name = user.getName();
        Integer age = user.getAge();
        System.out.println( "user: " + name + " exists！age:"+age);
    }


    @ResponseBody
    @GetMapping("queryPort")
    public String queryPort(){
        return "hei, jinglingwang, my server port is:"+serverPort;
    }


    public static void main(String[] args) throws ScriptException{
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName( "JavaScript" );

        System.out.println( engine.getClass().getName() );
        System.out.println( "Result:" + engine.eval( "function f() { return 1; }; f() + 1;" ) );
    }
}
