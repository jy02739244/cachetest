package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.TraceCountService;

/**
 * @Title Created by xzm
 * @date 2017/3/3.
 */
@Controller
public class TraceCountController {

    @Autowired
    private TraceCountService traceCountService;

    @RequestMapping(value = "count",produces={"application/json;charset=UTF-8"})
    @ResponseBody
    public String count(String company){
       return traceCountService.getTotalCountByCompany(company);
    }
}
