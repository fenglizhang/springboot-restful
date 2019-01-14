package telemetry.server.controller;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

import telemetry.server.bo.JsonResult;
import telemetry.server.bo.MyException;

/**
 * 本controller为demo样例。里面有基本的日志记录，异常处理，获取请求的json数据（入参可传可不传），返回的样例：{"code":0,"data":{},"msg":"ok!"}
 * @date 2019-01-10
 * @author zhanglf
 *
 */
@RestController
@RequestMapping(value = "/api/log")
public class showLogController {
	
	private final static Logger logger = LoggerFactory.getLogger(showLogController.class);
	
	@ResponseBody
	@RequestMapping(path="/showLogs")
	public String returnJson(@RequestParam(value = "logInfo",required=false) String logInfo) {
		System.out.println(logInfo);
		logger.info("--------进入demo------------------logInfo:"+logInfo);
		
		/* Exception异常会自动拦截，这里只是做个测试自定义异常*/
        if (0<1) {
            try {
				throw new MyException("999", "异常信息测试");
			} catch (MyException e) {
				logger.error(JsonResult.failed(Integer.parseInt(e.getCode()), e.getMsg()));
			}
        } 
		return JSON.toJSONString(new JsonResult(0, "your words:"+logInfo, new HashMap<>(0)));
	}
	
}
