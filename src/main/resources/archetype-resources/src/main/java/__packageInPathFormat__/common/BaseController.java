package ${package}.common;

import com.jfinal.captcha.CaptchaRender;
import com.jfinal.core.ActionException;
import com.jfinal.core.ForwardActionRender;
import com.jfinal.core.Injector;
import com.jfinal.core.converter.TypeConverter;
import com.jfinal.kit.Kv;
import com.jfinal.kit.StrKit;
import com.jfinal.render.ContentType;
import com.jfinal.render.ErrorRender;
import com.jfinal.render.Render;
import com.jfinal.render.RenderFactory;
import com.jfinal.template.Engine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.ParseException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;

/**
 * 基于jfinal使用的controller
 */
public class BaseController {
    private static final Logger log = LoggerFactory.getLogger(BaseController.class);
    @Autowired
    private HttpServletResponse response;
    @Autowired
    private HttpServletRequest request;

    private final static RenderFactory renderFactory=new RenderFactory();

    private static String captchaName = "_wixct_com_captcha";

    private String urlPara;
    private String[] urlParaArray;
    private String rawData;

    private static final String[] NULL_URL_PARA_ARRAY = new String[0];
    private static final String URL_PARA_SEPARATOR = "#";//TODO 配置文件设置

    /**
     * 获取 http 请求 body 中的原始数据，通常用于接收 json String 这类数据<br>
     * 可多次调用此方法，避免掉了 HttpKit.readData(...) 方式获取该数据时多次调用
     * 引发的异常
     * @return http 请求 body 中的原始数据
     */
    public String getRawData() {
        if (rawData == null) {
            rawData = com.jfinal.kit.HttpKit.readData(request);
        }
        return rawData;
    }

    public BaseController setAttr(String name, Object value) {
        request.setAttribute(name, value);
        return this;
    }

    public BaseController removeAttr(String name) {
        request.removeAttribute(name);
        return this;
    }

    public BaseController setAttrs(Map<String, Object> attrMap){
        for(Map.Entry<String, Object> entry : attrMap.entrySet()){
            request.setAttribute(entry.getKey(), entry.getValue());
        }
        return this;
    }

    public String getPara(String name) {
        return request.getParameter(name);
    }

    public String getPara(String name, String defaultValue) {
        String result = request.getParameter(name);
        return result != null && !"".equals(result) ? result : defaultValue;
    }

    public Map<String, String[]> getParaMap() {
        return request.getParameterMap();
    }

    public Enumeration<String> getParaNames() {
        return request.getParameterNames();
    }

    public String[] getParaValues(String name) {
        return request.getParameterValues(name);
    }

    public Integer[] getParaValuesToInt(String name) {
        String[] values = request.getParameterValues(name);
        if (values == null) {
            return null;
        }
        Integer[] result = new Integer[values.length];
        for (int i=0; i<result.length; i++) {
            result[i] = Integer.parseInt(values[i]);
        }
        return result;
    }

    public Long[] getParaValuesToLong(String name) {
        String[] values = request.getParameterValues(name);
        if (values == null) {
            return null;
        }
        Long[] result = new Long[values.length];
        for (int i=0; i<result.length; i++) {
            result[i] = Long.parseLong(values[i]);
        }
        return result;
    }

    public Enumeration<String> getAttrNames() {
        return request.getAttributeNames();
    }

    @SuppressWarnings("unchecked")
    public <T> T getAttr(String name) {
        return (T)request.getAttribute(name);
    }

    public String getAttrForStr(String name) {
        return (String)request.getAttribute(name);
    }

    public Integer getAttrForInt(String name) {
        return (Integer)request.getAttribute(name);
    }

    public String getHeader(String name) {
        return request.getHeader(name);
    }

    private Integer toInt(String value, Integer defaultValue) {
        if (StrKit.isBlank(value)) {
            return defaultValue;
        }
        value = value.trim();
        if (value.startsWith("N") || value.startsWith("n")) {
            return -Integer.parseInt(value.substring(1));
        }
        return Integer.parseInt(value);
    }

    public Integer getParaToInt(String name) {
        return toInt(request.getParameter(name), null);
    }

    public Integer getParaToInt(String name, Integer defaultValue) {
        return toInt(request.getParameter(name), defaultValue);
    }

    private Long toLong(String value, Long defaultValue) {
        if (StrKit.isBlank(value)) {
            return defaultValue;
        }
        value = value.trim();
        if (value.startsWith("N") || value.startsWith("n")) {
            return -Long.parseLong(value.substring(1));
        }
        return Long.parseLong(value);
    }

    public Long getParaToLong(String name) {
        return toLong(request.getParameter(name), null);
    }

    public Long getParaToLong(String name, Long defaultValue) {
        return toLong(request.getParameter(name), defaultValue);
    }

    private Boolean toBoolean(String value, Boolean defaultValue) {
        if (StrKit.isBlank(value)) {
            return defaultValue;
        }
        value = value.trim().toLowerCase();
        if ("1".equals(value) || "true".equals(value)) {
            return Boolean.TRUE;
        } else if ("0".equals(value) || "false".equals(value)) {
            return Boolean.FALSE;
        }
        throw new IllegalArgumentException("Can not parse the parameter \"" + value + "\" to Boolean value.");
    }

    public Boolean getParaToBoolean(String name) {
        return toBoolean(request.getParameter(name), null);
    }

    public Boolean getParaToBoolean(String name, Boolean defaultValue) {
        return toBoolean(request.getParameter(name), defaultValue);
    }

    /**
     * Get all para from url and convert to Boolean
     */
    public Boolean getParaToBoolean() {
        return toBoolean(getPara(), null);
    }

    /**
     * Get para from url and conver to Boolean. The first index is 0
     */
    public Boolean getParaToBoolean(int index) {
        return toBoolean(getPara(index), null);
    }

    /**
     * Get para from url and conver to Boolean with default value if it is null.
     */
    public Boolean getParaToBoolean(int index, Boolean defaultValue) {
        return toBoolean(getPara(index), defaultValue);
    }

    private Date toDate(String value, Date defaultValue) {
        try {
            if (StrKit.isBlank(value)) {
                return defaultValue;
            }
            return new java.text.SimpleDateFormat("yyyy-MM-dd").parse(value.trim());
        } catch (Exception e) {
            throw new IllegalArgumentException("Can not parse the parameter \"" + value + "\" to Date value.");
        }
    }

    public Date getParaToDate(String name) {
        return toDate(request.getParameter(name), null);
    }

    public Date getParaToDate(String name, Date defaultValue) {
        return toDate(request.getParameter(name), defaultValue);
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public HttpSession getSession() {
        return request.getSession();
    }

    public HttpSession getSession(boolean create) {
        return request.getSession(create);
    }

    @SuppressWarnings("unchecked")
    public <T> T getSessionAttr(String key) {
        HttpSession session = request.getSession(false);
        return session != null ? (T)session.getAttribute(key) : null;
    }

    public BaseController setSessionAttr(String key, Object value) {
        request.getSession(true).setAttribute(key, value);
        return this;
    }

    public BaseController removeSessionAttr(String key) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute(key);
        }
        return this;
    }

    public String getCookie(String name, String defaultValue) {
        Cookie cookie = getCookieObject(name);
        return cookie != null ? cookie.getValue() : defaultValue;
    }

    public String getCookie(String name) {
        return getCookie(name, null);
    }

    public Integer getCookieToInt(String name) {
        String result = getCookie(name);
        return result != null ? Integer.parseInt(result) : null;
    }

    public Integer getCookieToInt(String name, Integer defaultValue) {
        String result = getCookie(name);
        return result != null ? Integer.parseInt(result) : defaultValue;
    }

    public Long getCookieToLong(String name) {
        String result = getCookie(name);
        return result != null ? Long.parseLong(result) : null;
    }

    public Long getCookieToLong(String name, Long defaultValue) {
        String result = getCookie(name);
        return result != null ? Long.parseLong(result) : defaultValue;
    }

    public Cookie getCookieObject(String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return cookie;
                }
            }
        }
        return null;
    }

    public Cookie[] getCookieObjects() {
        Cookie[] result = request.getCookies();
        return result != null ? result : new Cookie[0];
    }

    public BaseController setCookie(String name, String value, int maxAgeInSeconds, boolean isHttpOnly) {
        return doSetCookie(name, value, maxAgeInSeconds, null, null, isHttpOnly);
    }

    public BaseController setCookie(String name, String value, int maxAgeInSeconds) {
        return doSetCookie(name, value, maxAgeInSeconds, null, null, null);
    }

    public BaseController setCookie(Cookie cookie) {
        response.addCookie(cookie);
        return this;
    }

    public BaseController setCookie(String name, String value, int maxAgeInSeconds, String path, boolean isHttpOnly) {
        return doSetCookie(name, value, maxAgeInSeconds, path, null, isHttpOnly);
    }

    public BaseController setCookie(String name, String value, int maxAgeInSeconds, String path) {
        return doSetCookie(name, value, maxAgeInSeconds, path, null, null);
    }

    public BaseController setCookie(String name, String value, int maxAgeInSeconds, String path, String domain, boolean isHttpOnly) {
        return doSetCookie(name, value, maxAgeInSeconds, path, domain, isHttpOnly);
    }

    public BaseController removeCookie(String name) {
        return doSetCookie(name, null, 0, null, null, null);
    }

    public BaseController removeCookie(String name, String path) {
        return doSetCookie(name, null, 0, path, null, null);
    }

    public BaseController removeCookie(String name, String path, String domain) {
        return doSetCookie(name, null, 0, path, domain, null);
    }

    private BaseController doSetCookie(String name, String value, int maxAgeInSeconds, String path, String domain, Boolean isHttpOnly) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAgeInSeconds);
        // set the default path value to "/"
        if (path == null) {
            path = "/";
        }
        cookie.setPath(path);

        if (domain != null) {
            cookie.setDomain(domain);
        }
        if (isHttpOnly != null) {
            cookie.setHttpOnly(isHttpOnly);
        }
        response.addCookie(cookie);
        return this;
    }
// --------

    /**
     * Get all para with separator char from url
     */
    public String getPara() {
        if("".equals(urlPara)){	// urlPara maybe is "" see ActionMapping.getAction(String)
            urlPara = null;
        }
        return urlPara;
    }

    /**
     * Get para from url. The index of first url para is 0.
     */
    public String getPara(int index) {
        if (index < 0) {
            return getPara();
        }

        if (urlParaArray == null) {
            if (urlPara == null || "".equals(urlPara)) {    // urlPara maybe is "" see ActionMapping.getAction(String)
                urlParaArray = NULL_URL_PARA_ARRAY;
            }else {
                urlParaArray = urlPara.split(URL_PARA_SEPARATOR);
            }
            for (int i=0; i<urlParaArray.length; i++) {
                if ("".equals(urlParaArray[i])) {
                    urlParaArray[i] = null;
                }
            }
        }
        return urlParaArray.length > index ? urlParaArray[index] : null;
    }

    /**
     * Get para from url with default value if it is null or "".
     */
    public String getPara(int index, String defaultValue) {
        String result = getPara(index);
        return result != null && !"".equals(result) ? result : defaultValue;
    }

    /**
     * Get para from url and conver to Integer. The first index is 0
     */
    public Integer getParaToInt(int index) {
        return toInt(getPara(index), null);
    }

    /**
     * Get para from url and conver to Integer with default value if it is null.
     */
    public Integer getParaToInt(int index, Integer defaultValue) {
        return toInt(getPara(index), defaultValue);
    }

    /**
     * Get para from url and conver to Long.
     */
    public Long getParaToLong(int index) {
        return toLong(getPara(index), null);
    }

    /**
     * Get para from url and conver to Long with default value if it is null.
     */
    public Long getParaToLong(int index, Long defaultValue) {
        return toLong(getPara(index), defaultValue);
    }

    /**
     * Get all para from url and convert to Integer
     */
    public Integer getParaToInt() {
        return toInt(getPara(), null);
    }

    /**
     * Get all para from url and convert to Long
     */
    public Long getParaToLong() {
        return toLong(getPara(), null);
    }


    public <T> T getModel(Class<T> modelClass) {
        return (T) Injector.injectModel(modelClass, request, false);
    }

    public <T> T getModel(Class<T> modelClass, String modelName) {
        return (T)Injector.injectModel(modelClass, modelName, request, false);
    }

    public <T> T getModel(Class<T> modelClass, String modelName, boolean skipConvertError) {
        return (T)Injector.injectModel(modelClass, modelName, request, skipConvertError);
    }

    public <T> T getModel(Class<T> modelClass, boolean skipConvertError) {
        return (T)Injector.injectModel(modelClass, request, skipConvertError);
    }

    public <T> T getBean(Class<T> beanClass) {
        return (T)Injector.injectBean(beanClass, request, false);
    }

    public <T> T getBean(Class<T> beanClass, boolean skipConvertError) {
        return (T)Injector.injectBean(beanClass, request, skipConvertError);
    }

    public <T> T getBean(Class<T> beanClass, String beanName) {
        return (T)Injector.injectBean(beanClass, beanName, request, false);
    }

    public <T> T getBean(Class<T> beanClass, String beanName, boolean skipConvertError) {
        return (T)Injector.injectBean(beanClass, beanName, request, skipConvertError);
    }
    /**
     * 获取被 Kv 封装后的参数，便于使用 Kv 中的一些工具方法
     *
     * 由于 Kv 继承自 HashMap，也便于需要使用 HashMap 的场景，
     * 例如：
     * Record record = new Record().setColumns(getKv());
     */
    public Kv getKv() {
        Kv kv = new Kv();
        Map<String, String[]> paraMap = request.getParameterMap();
        for (Map.Entry<String, String[]> entry : paraMap.entrySet()) {
            String[] values = entry.getValue();
            String value = (values != null && values.length > 0) ? values[0] : null;
            kv.put(entry.getKey(), "".equals(value) ? null : value);
        }
        return kv;
    }

    public BaseController keepPara() {
        Map<String, String[]> map = request.getParameterMap();
        for (Map.Entry<String, String[]> e: map.entrySet()) {
            String[] values = e.getValue();
            if (values.length == 1) {
                request.setAttribute(e.getKey(), values[0]);
            } else {
                request.setAttribute(e.getKey(), values);
            }
        }
        return this;
    }

    public BaseController keepPara(String... names) {
        for (String name : names) {
            String[] values = request.getParameterValues(name);
            if (values != null) {
                if (values.length == 1) {
                    request.setAttribute(name, values[0]);
                } else {
                    request.setAttribute(name, values);
                }
            }
        }
        return this;
    }

    public BaseController keepPara(Class<?> type, String name) {
        String[] values = request.getParameterValues(name);
        if (values != null) {
            if (values.length == 1) {
                try {request.setAttribute(name, TypeConverter.me().convert(type, values[0]));} catch (ParseException e) {com.jfinal.kit.LogKit.logNothing(e);}
            } else {
                request.setAttribute(name, values);
            }
        }
        return this;
    }

    public BaseController keepPara(Class<?> type, String... names) {
        if (type == String.class) {
            return keepPara(names);
        }

        if (names != null) {
            for (String name : names) {
                keepPara(type, name);
            }
        }
        return this;
    }

    public BaseController keepModel(Class<? extends com.jfinal.plugin.activerecord.Model<?>> modelClass, String modelName) {
        if (StrKit.notBlank(modelName)) {
            Object model = Injector.injectModel(modelClass, modelName, request, true);
            request.setAttribute(modelName, model);
        } else {
            keepPara();
        }
        return this;
    }

    public BaseController keepModel(Class<? extends com.jfinal.plugin.activerecord.Model<?>> modelClass) {
        String modelName = StrKit.firstCharToLowerCase(modelClass.getSimpleName());
        keepModel(modelClass, modelName);
        return this;
    }

    public BaseController keepBean(Class<?> beanClass, String beanName) {
        if (StrKit.notBlank(beanName)) {
            Object bean = Injector.injectBean(beanClass, beanName, request, true);
            request.setAttribute(beanName, bean);
        } else {
            keepPara();
        }
        return this;
    }

    public BaseController keepBean(Class<?> beanClass) {
        String beanName = StrKit.firstCharToLowerCase(beanClass.getSimpleName());
        keepBean(beanClass, beanName);
        return this;
    }

    public boolean isParaBlank(String paraName) {
        return StrKit.isBlank(request.getParameter(paraName));
    }

    public boolean isParaExists(String paraName) {
        return request.getParameterMap().containsKey(paraName);
    }
    /**
     * Render template to String content, it is useful for:
     * 1: Generate HTML fragment for AJAX request
     * 2: Generate email, short message and so on
     */
    public String renderToString(String template, Map data) {
        //TODO renderToString(String template, Map data) 待实现
        return Engine.use().getTemplate(template).renderToString(data);
    }

    /**
     * Render with JFinal template
     */
    public void renderTemplate(String template) {
        renderFactory.getTemplateRender(template).setContext(request,response).render();
    }

    /**
     * Render with jsp view
     */
    public void renderJsp(String view) {
        renderFactory.getJspRender(view).setContext(request,response).render();
    }

    /**
     * Render with freemarker view
     */
    public void renderFreeMarker(String view) {
        renderFactory.getFreeMarkerRender(view).setContext(request,response).render();
    }

    /**
     * Render with velocity view
     */
    public void renderVelocity(String view) {
        renderFactory.getVelocityRender(view).setContext(request,response).render();
    }

    public void renderJson(){
        renderFactory.getJsonRender().setContext(request,response).render();
    }

    public void renderJson(Object object) {
        renderFactory.getJsonRender(object).setContext(request,response).render();
    }

    /**
     * Render with attributes set by setAttr(...) before.
     * <p>
     * Example: renderJson(new String[]{"blogList", "user"});
     */
    public void renderJson(String[] attrs) {
        renderFactory.getJsonRender(attrs).setContext(request,response).render();
    }

    /**
     * Render with json text.
     * <p>
     * Example: renderJson("{\"message\":\"Please input password!\"}");
     */
    public void renderJson(String jsonText) {
        renderFactory.getJsonRender(jsonText).setContext(request,response).render();
    }
    /**
     * Render with text. The contentType is: "text/plain".
     */
    public void renderText(String text) {
        renderFactory.getTextRender(text).setContext(request,response).render();
    }

    /**
     * Render with text and content type.
     * <p>
     * Example: renderText("&lt;user id='5888'&gt;James&lt;/user&gt;", "application/xml");
     */
    public void renderText(String text, String contentType) {
        renderFactory.getTextRender(text, contentType).setContext(request,response).render();
    }

    /**
     * Render with text and ContentType.
     * <p>
     * Example: renderText("&lt;html&gt;Hello James&lt;/html&gt;", ContentType.HTML);
     */
    public void renderText(String text, ContentType contentType) {
        renderFactory.getTextRender(text, contentType).setContext(request,response).render();
    }

    /**
     * Forward to an action
     */
    public void forwardAction(String actionUrl) {
        new ForwardActionRender(actionUrl).setContext(request,response).render();
    }

    /**
     * Render with file
     */
    public void renderFile(String fileName) {
        renderFactory.getFileRender(fileName).setContext(request,response).render();
    }

    /**
     * Render with file, using the new file name to the client
     */
    public void renderFile(String fileName, String downloadFileName) {
        renderFactory.getFileRender(fileName, downloadFileName).setContext(request,response).render();
    }

    /**
     * Render with file
     */
    public void renderFile(File file) {
        renderFactory.getFileRender(file).setContext(request,response).render();
    }

    /**
     * Render with file, using the new file name to the client
     */
    public void renderFile(File file, String downloadFileName) {
        renderFactory.getFileRender(file, downloadFileName).setContext(request,response).render();
    }

    /**
     * Redirect to url
     */
    public void redirect(String url) {
        renderFactory.getRedirectRender(url).setContext(request,response).render();
    }

    /**
     * Redirect to url
     */
    public void redirect(String url, boolean withQueryString) {
        renderFactory.getRedirectRender(url, withQueryString).setContext(request,response).render();
    }

    /**
     * Render with view and status use default type Render configured in JFinalConfig
     */
    public void render(String view, int status) {
        renderFactory.getRender(view).setContext(request,response).render();
        response.setStatus(status);
    }

    /**
     * Render with url and 301 status
     */
    public void redirect301(String url) {
        renderFactory.getRedirect301Render(url).setContext(request,response).render();
    }

    /**
     * Render with url and 301 status
     */
    public void redirect301(String url, boolean withQueryString) {
        renderFactory.getRedirect301Render(url, withQueryString).setContext(request,response).render();
    }

    /**
     * Render with view and errorCode status
     */
    public void renderError(int errorCode, String view) {
        throw new ActionException(errorCode, renderFactory.getErrorRender(errorCode, view));
    }

    /**
     * Render with render and errorCode status
     */
    public void renderError(int errorCode, Render render) {
        throw new ActionException(errorCode, render);
    }

    /**
     * Render with view and errorCode status configured in JFinalConfig
     */
    public void renderError(int errorCode) {
        new ErrorRender(errorCode,null).setContext(request, response).render();

    }

    /**
     * Render nothing, no response to browser
     */
    public void renderNull() {
        renderFactory.getNullRender().setContext(request,response).render();
    }

    /**
     * Render with javascript text. The contentType is: "text/javascript".
     */
    public void renderJavascript(String javascriptText) {
        renderFactory.getJavascriptRender(javascriptText).setContext(request,response).render();
    }

    /**
     * Render with html text. The contentType is: "text/html".
     */
    public void renderHtml(String htmlText) {
        renderFactory.getHtmlRender(htmlText).setContext(request,response).render();
    }

    /**
     * Render with xml view using freemarker.
     */
    public void renderXml(String view) {
        renderFactory.getXmlRender(view).setContext(request,response).render();
    }

    public void renderCaptcha() {
        CaptchaRender.setCaptchaName(captchaName);
        renderFactory.getCaptchaRender().setContext(request,response).render();
    }

    /**
     * 渲染二维码
     * @param content 二维码中所包含的数据内容
     * @param width 二维码宽度，单位为像素
     * @param height 二维码高度，单位为像素
     */
    public void renderQrCode(String content, int width, int height) {
        renderFactory.getQrCodeRender(content, width, height).setContext(request,response).render();
    }

    /**
     * 渲染二维码，并指定纠错级别
     * @param content 二维码中所包含的数据内容
     * @param width 二维码宽度，单位为像素
     * @param height 二维码高度，单位为像素
     * @param errorCorrectionLevel 纠错级别，可设置的值从高到低分别为：'H'、'Q'、'M'、'L'，具体的纠错能力如下：
     *  H = ~30%
     *  Q = ~25%
     *  M = ~15%
     *  L = ~7%
     */
    public void renderQrCode(String content, int width, int height, char errorCorrectionLevel) {
        renderFactory.getQrCodeRender(content, width, height, errorCorrectionLevel).setContext(request,response).render();
    }

    public boolean validateCaptcha(String paraName) {
        String captchaKey = getCookie(captchaName);
        if (CaptchaRender.validate(captchaKey, getPara(paraName))) {
            removeCookie(captchaName);
            return true;
        }
        return false;
    }

    public void checkUrlPara(int minLength, int maxLength) {
        getPara(0);
        if (urlParaArray.length < minLength || urlParaArray.length > maxLength) {
            renderError(404);
        }
    }

    public void checkUrlPara(int length) {
        checkUrlPara(length, length);
    }

    /**
     * 为了进一步省代码，创建与 setAttr(...) 功能一模一样的缩短版本 set(...)
     */
    public BaseController set(String attributeName, Object attributeValue) {
        request.setAttribute(attributeName, attributeValue);
        return this;
    }

    // --- 以下是为了省代码，为 getPara 系列方法创建的缩短版本

    public String get(String name) {
        return getPara(name);
    }

    public String get(String name, String defaultValue) {
        return getPara(name, defaultValue);
    }

    public Integer getInt(String name) {
        return getParaToInt(name);
    }

    public Integer getInt(String name, Integer defaultValue) {
        return getParaToInt(name, defaultValue);
    }

    public Long getLong(String name) {
        return getParaToLong(name);
    }

    public Long getLong(String name, Long defaultValue) {
        return getParaToLong(name, defaultValue);
    }

    public Boolean getBoolean(String name) {
        return getParaToBoolean(name);
    }

    public Boolean getBoolean(String name, Boolean defaultValue) {
        return getParaToBoolean(name, defaultValue);
    }

    public Date getDate(String name) {
        return getParaToDate(name);
    }

    public Date getDate(String name, Date defaultValue) {
        return getParaToDate(name, defaultValue);
    }

    // --- 以下是 getPara 系列中获取 urlPara 的缩短版本

    /* 为了让继承类可以使用名为 get 的 action 注掉此方法，可使用 get(-1) 来实现本方法的功能
    public String get() {
        return getPara();
    } */
    public String get(int index) {
        return getPara(index);
    }

    public String get(int index, String defaultValue) {
        return getPara(index, defaultValue);
    }

    public Integer getInt() {
        return getParaToInt();
    }

    public Integer getInt(int index) {
        return getParaToInt(index);
    }

    public Integer getInt(int index, Integer defaultValue) {
        return getParaToInt(index, defaultValue);
    }

    public Long getLong() {
        return getParaToLong();
    }

    public Long getLong(int index) {
        return getParaToLong(index);
    }

    public Long getLong(int index, Long defaultValue) {
        return getParaToLong(index, defaultValue);
    }

    public Boolean getBoolean() {
        return getParaToBoolean();
    }

    public Boolean getBoolean(int index) {
        return getParaToBoolean(index);
    }

    public Boolean getBoolean(int index, Boolean defaultValue) {
        return getParaToBoolean(index, defaultValue);
    }
}
