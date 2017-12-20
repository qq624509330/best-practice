package io.spldeolin.bestpractice.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import io.spldeolin.bestpractice.entity.RequestResult;
import io.spldeolin.bestpractice.input.AgeBitrhdayInput;
import io.spldeolin.bestpractice.input.InteractionInput;
import io.spldeolin.bestpractice.input.NameDateInput;
import io.spldeolin.bestpractice.po.TimePo;
import io.spldeolin.bestpractice.service.TimeService;
import io.spldeolin.bestpractice.service.UserService;
import io.spldeolin.bestpractice.util.HttpSessionUtil;
import redis.clients.jedis.Jedis;

/**
 * 控制器：基础设施模块<br>
 * 定义了Best Practice所有演示Demo的请求方法
 *
 * @author Deolin
 */
@Controller
public class BasicsController {

    private static final Logger LOG = LogManager.getLogger(BasicsController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private TimeService timeService;

    /**
     * 请求：转发到首页
     *
     * @return 转发地址
     */
    @RequestMapping(value = "index")
    public String index() {
        HttpSessionUtil.setAttribute("ses", "接收到了index()中存放的Session值");
        return "/html/index.html";
    }

    /**
     * 请求：转发到404页面
     *
     * @return 转发地址
     */
    @RequestMapping("404")
    public String page404() {
        LOG.info("进入404页面");
        LOG.info(HttpSessionUtil.getAttribute("ses"));
        return "/html/http404.html";
    }

    /**
     * 请求：转发到500页面
     *
     * @return 转发地址
     */
    @RequestMapping("500")
    public String page500() {
        LOG.info("进入500页面");
        LOG.info(HttpSessionUtil.getAttribute("ses"));
        return "/html/http500.html";
    }

    /**
     * 请求：测试<br>
     * ajax、数据绑定、jsr303、jsr310（mybatis）、日志、mybatis、事务、返回值转换为json
     *
     * @author Deolin
     */
    @RequestMapping(value = "simple_ajax", method = RequestMethod.POST)
    @ResponseBody
    public String simple_ajax(@RequestBody @Valid NameDateInput input, BindingResult checker) {
        for (ObjectError error : checker.getAllErrors()) {
            LOG.warn(error.getDefaultMessage());
        }
        try {
            LOG.info(userService.getBatch().get(1));
            userService.batchCreate();
        } catch (Exception e) {
            LOG.warn("异常抛出前插入的数据被回滚了，没有数据被插入");
        }
        LOG.info("timeService.isLocalDateTimeLeapYear()的返回值：" + timeService.isLocalDateTimeLeapYear());
        return input.toString();
    }

    /**
     * 请求：测试<br>
     * jsr310 (json)
     *
     * @author Deolin
     */
    @RequestMapping(value = "localdatetime", method = RequestMethod.GET)
    @ResponseBody
    public TimePo localdatetime() {
        TimePo po = new TimePo();
        po.setDatetime_field(LocalDateTime.now());
        LocalTime lt = LocalTime.now();
        LocalDateTime ldt = LocalDateTime.of(LocalDate.MIN, lt);
        LOG.info(ldt);
        return po;
    }

    /**
     * 请求：测试<br>
     * 异步上传文件
     *
     * @author Deolin
     */
    @RequestMapping(value = "file_upload", method = RequestMethod.POST)
    @ResponseBody
    public void file_upload(@RequestParam MultipartFile file) throws IllegalStateException, IOException {
        LOG.info("上传");
        file.transferTo(new File("C:\\" + file.getOriginalFilename()));
    }

    /**
     * 请求：测试<br>
     * 下载文件
     *
     * @author Deolin
     */
    @RequestMapping(value = "download", method = RequestMethod.GET)
    public String download(Integer mode, HttpServletRequest request, HttpServletResponse response) {
        String fileName;
        File file;
        if (mode == 1) {
            // 绝对路径文件
            fileName = "hosts";
            file = new File("C:\\Windows\\System32\\drivers\\etc\\" + fileName);
        } else {
            // 相对路径文件
            fileName = "jquery-3.2.1.min.js";
            file = new File(request.getServletContext().getRealPath("/lib/" + fileName));
        }
        response.setContentType("application/force-download");
        response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
        byte[] buffer = new byte[1024];
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        try {
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            OutputStream os = response.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
        } catch (Exception e) {
            LOG.error("I/O异常");
            return "redirect:/500";
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    LOG.error("I/O异常");
                    return "redirect:/500";
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    LOG.error("I/O异常");
                    return "redirect:/500";
                }
            }
        }
        return null;
    }

    /**
     * 请求：测试<br>
     * 通过Jedis向Redis存入一条数据
     *
     * @author Deolin
     */
    public void setCache() {
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.set("ma", "aaaa");
        jedis.close();
    }

    /**
     * 请求：测试<br>
     * 通过Jedis从Redis取出一条数据
     *
     * @author Deolin
     */
    public void getCache() {
        Jedis jedis = new Jedis("localhost", 6379);
        LOG.info("Deolin    " + jedis.get("ma"));
        jedis.close();
    }

    /**
     * 请求：测试<br>
     * 读redis缓存 （代替读DB）
     *
     * @author Deolin
     */
    @RequestMapping(value = "redis_read", method = RequestMethod.GET)
    @ResponseBody
    public void redis_read() throws Exception {
        /*
         * 这里，验证缓存需要把log4j2调整为DEBUG模式。 第一次请求这个方法时，日志会输出“JDBC Connection
         * [...”之类的内容，说明在访问DB 之后的10秒内，每次请求时，日志只会输出“Opening
         * RedisConnection”之类的内容，说明命中了对缓存。
         * “10秒”指的是key失效时间，通过cacheManager.setDefaultExpiration(10);设置，
         * key失效后，再次请求会重新通过访问DB来取得数据
         */
        LOG.info(userService.getUserByNickname("nickname1"));
        LOG.info(userService.getUserByPassword("password1"));
    }

    /**
     * 请求：测试<br>
     * 写redis缓存
     *
     * @author Deolin
     */
    @RequestMapping(value = "redis_write", method = RequestMethod.GET)
    @ResponseBody
    public void redis_write() throws Exception {
        userService.createUser("nickname1", "password1");
    }

    /**
     * 请求：测试<br>
     * 中文乱码
     *
     * @author Deolin
     */
    @RequestMapping(value = "zh_encoding", method = RequestMethod.GET)
    @ResponseBody
    public String zh_encoding() throws Exception {
        /*
         * 如果注释掉dispatcherservlet-servlet.xml中的supportedMediaTypes，
         * 返回值到了浏览器会变成乱码。 但是，无论supportedMediaTypes是否被注释， 如果将汉字包装在实体类对象里面，都会不乱码。
         */
        return "汉字";
    }

    /**
     * 请求：测试<br>
     * (at)ControllerAdvice注解
     *
     * @author Deolin
     */
    @RequestMapping(value = "controller_advice", method = RequestMethod.GET)
    @ResponseBody
    public String controller_advice() throws Exception {
        Integer.valueOf("a");
        return null;
    }

    /**
     * 请求：测试<br>
     * 取得Http请求中的Cookie
     *
     * @author Deolin
     */
    @RequestMapping(value = "get_cookie", method = RequestMethod.GET)
    @ResponseBody
    public String get_cookie(@CookieValue(value = "token", required = false) String token) throws Exception {
        if (token == null) {
            return "cookie取不到，超时或未点击“Set Cookie”。";
        }
        return token;
    }

    /**
     * 请求：测试<br>
     * Input类使用非String类型域绑定请求参数时，参数绑定失败的处理方式。
     *
     * @author Deolin
     */
    @RequestMapping(value = "error_bind", method = RequestMethod.POST)
    @ResponseBody
    public String error_bind(@RequestBody AgeBitrhdayInput input) throws Exception {
        /*
         * 这个示例测试的是参数绑定失败的情况，所以不再会进入这个方法
         * 而是进入ControllerExceptionHandler#processHttpMessageNotReadableException
         * (HttpMessageNotReadableException)
         */
        return "汉字";
    }

    /**
     * 请求：测试<br>
     *
     * @ResponseBody 返回汉字String对象时乱码问题的解决方式。
     * @author Deolin
     */
    @RequestMapping(value = "easy_return", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String easyReturn() {
        return "汉字";
    }

    @RequestMapping(value = "interaction", method = RequestMethod.POST)
    @ResponseBody
    public RequestResult interaction(@RequestBody @Valid InteractionInput input, BindingResult checker) {
        // 这里只是为了演示，实际上这段解析BindingResult对象的代码最好抽到共通类中
        if (checker.hasFieldErrors()) {
            for (FieldError error : checker.getFieldErrors()) {
                String errmsg = error.getDefaultMessage();
                LOG.error(errmsg);
                return RequestResult.failure(errmsg);
            }
        }
        LOG.info(input);
        return RequestResult.success("交互成功。（实际开发中data参数可以放各种想要传给前端的对象）");
    }

}