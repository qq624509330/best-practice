package io.spldeolin.bestpractice.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * HttpSession的代理工具类<br>
 * 用于设置、获取、移除HttpSession对象的属性（Attribute），以及获取HttpSession对象
 *
 * @author Deolin
 */
public class HttpSessionUtil {

    private static final int scope = RequestAttributes.SCOPE_SESSION;

    /**
     * 设置属性<br>
     * 调用这个方法等价于<code>request.getSession().setAttribute(key, value);</code>
     *
     * @param key 属性的键
     * @param value 属性值
     */
    public static void setAttribute(String key, Object value) {
        RequestAttributes ra = RequestContextHolder.currentRequestAttributes();
        ra.setAttribute(key, value, scope);
    }

    /**
     * 获取属性<br>
     * 调用这个方法等价于<code>request.getSession().getAttribute(key, value);</code>
     *
     * @param key 属性的键
     * @return Object 属性值
     */
    public static Object getAttribute(String key) {
        RequestAttributes ra = RequestContextHolder.currentRequestAttributes();
        return ra.getAttribute(key, scope);
    }

    /**
     * 移除属性<br>
     * 调用这个方法等价于<code>request.getSession().removeAttribute(key, value);</code>
     *
     * @param key 键
     */
    public static void removeAttribute(String key) {
        RequestAttributes ra = RequestContextHolder.currentRequestAttributes();
        ra.removeAttribute(key, scope);
    }

    /**
     * 获取HttpSession对象<br>
     * 这个方法等价于<code>request.getSession()</code>
     */
    public static HttpSession instance() {
        RequestAttributes ra = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) ra).getRequest();
        HttpSession session = request.getSession();
        return session;
    }

}
