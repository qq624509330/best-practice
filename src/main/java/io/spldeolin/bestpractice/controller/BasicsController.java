package io.spldeolin.bestpractice.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import io.spldeolin.bestpractice.entity.RequestResult;
import io.spldeolin.bestpractice.input.AgeBitrhdayInput;
import io.spldeolin.bestpractice.input.InteractionInput;
import io.spldeolin.bestpractice.input.NameDateInput;
import io.spldeolin.bestpractice.po.User;
import io.spldeolin.bestpractice.service.UserService;
import io.spldeolin.bestpractice.util.HttpSessionUtil;
import lombok.extern.log4j.Log4j2;
import redis.clients.jedis.Jedis;

/**
 * 控制器：基础设施模块<br>
 * 定义了Best Practice所有演示Demo的请求方法
 *
 * @author Deolin
 */
@Controller
@Log4j2
public class BasicsController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 请求：转发到首页
     *
     * @return 转发地址
     */
    @RequestMapping(value = "index")
    public String index() {
        return "/html/index.html";
    }

    /**
     * 请求：转发到404页面
     *
     * @return 转发地址
     */
    @RequestMapping("404")
    public String page404() {
        log.info("进入404页面");
        return "/html/http404.html";
    }

    /**
     * 请求：转发到500页面
     *
     * @return 转发地址
     */
    @RequestMapping("500")
    public String page500() {
        log.info("进入500页面");
        return "/html/http500.html";
    }

    /**
     * 演示接口<br>
     * ajax、数据绑定、jsr303、日志、mybatis、事务、返回值转换为json
     *
     * @author Deolin
     */
    @PostMapping("simple_ajax")
    @ResponseBody
    public String simpleAjax(@RequestBody @Valid NameDateInput input, BindingResult checker) {
        log.info("请求参数：" + input);
        for (ObjectError error : checker.getAllErrors()) {
            log.warn(error.getDefaultMessage());
        }
        List<User> users = null;
        try {
            users = userService.getBatch();
            userService.batchCreate();
        } catch (Exception e) {
            log.warn("异常抛出前插入的数据被回滚了，没有数据被插入");
        }
        return users.toString();
    }

    /**
     * 演示接口<br>
     * 异步上传文件、HttpSessionUtil工具类
     *
     * @author Deolin
     */
    @PostMapping("file_upload")
    @ResponseBody
    public void fileUpload(@RequestParam MultipartFile file) throws IllegalStateException, IOException {
        log.info("上传");
        HttpSessionUtil.setAttribute("ses", "绘画");
        file.transferTo(new File("C:\\" + file.getOriginalFilename()));
    }

    /**
     * 演示接口<br>
     * 下载文件、HttpSessionUtil工具类
     *
     * @author Deolin
     */
    @GetMapping("file_download")
    public String fileDownload(Integer mode, HttpServletRequest request, HttpServletResponse response) {
        log.info(HttpSessionUtil.getAttribute("ses"));
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
            log.error("I/O异常");
            return "redirect:/500";
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    log.error("I/O异常");
                    return "redirect:/500";
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    log.error("I/O异常");
                    return "redirect:/500";
                }
            }
        }
        return null;
    }

    /**
     * 演示接口<br>
     * 通过Jedis向Redis存入一条数据
     *
     * @author Deolin
     */
    @GetMapping("set_cache")
    @ResponseBody
    public void setCache() {
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.set("ma", "aaaa");
        jedis.close();
    }

    /**
     * 演示接口<br>
     * 通过Jedis从Redis取出一条数据
     *
     * @author Deolin
     */
    @GetMapping("get_cache")
    @ResponseBody
    public void getCache() {
        Jedis jedis = new Jedis("localhost", 6379);
        log.info("Deolin    " + jedis.get("ma"));
        jedis.close();
    }

    /**
     * 演示接口<br>
     * 中文乱码
     *
     * @author Deolin
     */
    @GetMapping("zh_encoding")
    @ResponseBody
    public String zhEncoding() throws Exception {
        /*
         * 如果注释掉dispatcherservlet-servlet.xml中的supportedMediaTypes，
         * 返回值到了浏览器会变成乱码。 但是，无论supportedMediaTypes是否被注释， 如果将汉字包装在实体类对象里面，都会不乱码。
         */
        return "汉字";
    }

    /**
     * 演示接口<br>
     * (at)ControllerAdvice注解
     *
     * @author Deolin
     */
    @GetMapping("controller_advice")
    @ResponseBody
    public String controllerAdvice() throws Exception {
        Integer.valueOf("a");
        return null;
    }

    /**
     * 演示接口<br>
     * 取得Http请求中的Cookie
     *
     * @author Deolin
     */
    @GetMapping("get_cookie")
    @ResponseBody
    public String getCookie(@CookieValue(value = "token", required = false) String token) throws Exception {
        if (token == null) {
            return "cookie取不到，超时或未点击“Set Cookie”。";
        }
        return token;
    }

    /**
     * 演示接口<br>
     * Input类使用非String类型域绑定请求参数时，参数绑定失败的处理方式。
     *
     * @author Deolin
     */
    @PostMapping("error_bind")
    @ResponseBody
    public String errorBind(@RequestBody AgeBitrhdayInput input) throws Exception {
        /*
         * 这个示例测试的是参数绑定失败的情况，所以不再会进入这个方法
         * 而是进入ControllerExceptionHandler#processHttpMessageNotReadableException
         * (HttpMessageNotReadableException)
         */
        return "汉字";
    }

    /**
     * 演示接口<br>
     * 返回汉字String对象时乱码问题的解决方式。
     *
     * @author Deolin
     */
    @GetMapping(value = "easy_return", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String easyReturn() {
        return "汉字";
    }

    @PostMapping("interaction")
    @ResponseBody
    public RequestResult interaction(@RequestBody @Valid InteractionInput input, BindingResult checker) {
        // 这里只是为了演示，实际上这段解析BindingResult对象的代码最好抽到共通类中
        if (checker.hasFieldErrors()) {
            for (FieldError error : checker.getFieldErrors()) {
                String errmsg = error.getDefaultMessage();
                log.error(errmsg);
                return RequestResult.failure(errmsg);
            }
        }
        log.info(input);
        return RequestResult.success("交互成功。（实际开发中data参数可以放各种想要传给前端的对象）");
    }

}