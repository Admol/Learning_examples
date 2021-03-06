package cn.jinglingwang.eurelaclient.demo.controller;

import cn.jinglingwang.eurelaclient.demo.dto.UserDTO;
import cn.jinglingwang.eurelaclient.demo.feign.ProviderFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @author : jingling
 * @Date : 2020/11/24
 */
@RestController
public class SayHelloController{
    @Autowired
    private ProviderFeign providerFeign;

    @GetMapping("sayHello")
    public String sayHello(HttpServletRequest request){
        Enumeration<String> names = request.getHeaderNames();
        while(names.hasMoreElements()){
            System.out.println(names.nextElement());
        }
        return providerFeign.hello();
    }

    @GetMapping("query")
    public String query(){
        UserDTO user = new UserDTO();
        user.age = 18;
        user.name = "精灵王";
        return providerFeign.query(user);
    }
    @GetMapping("query2")
    public String query2(){
        UserDTO user = new UserDTO();
        user.age = 18;
        user.name = "精灵王";
        return providerFeign.query2(user);
    }
    @GetMapping("query3")
    public String query3(){
        UserDTO user = new UserDTO();
        user.age = 18;
        user.name = "精灵王";
        return providerFeign.query3(user);
    }
    @GetMapping("queryNoReturn")
    public String queryNoReturn(){
        UserDTO user = new UserDTO();
        user.age = 18;
        user.name = "精灵王";
        providerFeign.queryNoReturn(user);
        return "do queryNoReturn success!";
    }
    @GetMapping("queryPort")
    public String queryPort(){
        return providerFeign.queryPort();
    }

}
