package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {
    private final LogDemoService logDemoService;
    private final MyLogger myLogger; // proxy 사용중
    //private final ObjectProvider<MyLogger> myLoggerProvider; // request는 요청이 들어올 때 생성되므로 생성된 후 가져와야 된다. 그 전에 받아오면(주입) request 가 없으므로 생성 불가능

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request){
        String requestURL = request.getRequestURL().toString();
        //MyLogger myLogger = myLoggerProvider.getObject(); // 빈의 생성 지연
        myLogger.setRequestURL(requestURL);

        myLogger.log("Contoller Test");
        logDemoService.logic("Test Id");
        return "OK";
    }
}
