package cn.n2b.rpc.client;

import com.alibaba.fastjson.JSON;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class RcpClientHttp {
    /**
     * 获取列表类型的数据
     *
     * @param url  请求url(填写完整)
     * @param type 反序列化的类型
     * @param <T>
     * @return
     */
    public static <T> List<T> postList(String url, Class<T> type) {
        return postList(url, null, type);
    }

    /**
     * 获取列表类型的数据
     *
     * @param url    请求url(填写完整)
     * @param params 请求参数
     * @param type   反序列化的类型
     * @param <T>
     * @return
     */
    public static <T, E> List<T> postList(String url, E params, Class<T> type) {
        List<T> list = null;
        try {
            String res = post(url, params);
            if (res != null && res.startsWith("[")) {
                list = JSON.parseArray(res, type);
            }
        } catch (Exception e) {
            throw e;
        }
        return list;
    }

    /**
     * 获取字符串类型的数据
     *
     * @param url    请求url(填写完整)
     * @param params 请求参数
     * @return
     */
    public static <E> String postString(String url, E params) {
        String res = null;
        try {
            res = post(url, params);
        } catch (Exception e) {
            throw e;
        }
        return res;
    }

    /**
     * 获取字符串类型的数据
     *
     * @param url 请求url(填写完整)
     * @return
     */
    public static String postString(String url) {
        return postString(url, null);
    }

    /**
     * 获取实体类型的数据
     *
     * @param url  请求url(填写完整)
     * @param type 反序列化的类型
     * @param <T>
     * @return
     */
    public static <T> T postObject(String url, Class<T> type) {
        return postObject(url, null, type);
    }

    /**
     * 获取列表类型的数据
     *
     * @param url  请求url(填写完整)
     * @param type 反序列化的类型
     * @param <T>
     * @return
     */
    public static <T> List<T> getList(String url, Class<T> type) {
        return getList(url, null, type);
    }

    /**
     * 获取列表类型的数据
     *
     * @param url    请求url(填写完整)
     * @param params 请求参数
     * @param type   反序列化的类型
     * @param <T>
     * @return
     */
    public static <T> List<T> getList(String url, Class<?> params, Class<T> type) {
        List<T> list = null;
        try {
            String res = get(url, params);
            if (res != null && res.startsWith("[")) {
                list = JSON.parseArray(res, type);
            }
        } catch (Exception e) {
            throw e;
        }
        return list;
    }

    /**
     * 获取字符串类型的数据
     *
     * @param url    请求url(填写完整)
     * @param params 请求参数
     * @return
     */
    public static String getString(String url, Class<?> params) {
        String res = null;
        try {
            res = get(url, params);
        } catch (Exception e) {
            throw e;
        }
        return res;
    }

    /**
     * 获取字符串类型的数据
     *
     * @param url 请求url(填写完整)
     * @return
     */
    public static String getString(String url) {
        return getString(url, null);
    }

    /**
     * 获取实体类型的数据
     *
     * @param url  请求url(填写完整)
     * @param type 反序列化的类型
     * @param <T>
     * @return
     */
    public static <T> T getObject(String url, Class<T> type) {
        return getObject(url, null, type);
    }

    /**
     * 获取实体类型的数据
     *
     * @param url    请求url(填写完整)
     * @param params 请求参数
     * @param type   反序列化的类型
     * @param <T>
     * @return
     */
    public static <T, E> T getObject(String url, E params, Class<T> type) {
        try {
            String res = get(url, params);
            if (res != null && res.startsWith("{")) {
                T a = JSON.parseObject(res, type);
                return a;
            }
        } catch (Exception e) {
            throw e;
        }
        return null;
    }

    /**
     * 删除
     *
     * @param url(完整的url)
     * @param params      请求参数
     */
    public static void delete(String url, Object... params) {
        RestTemplate restTemplate = getRestTemplate(url);
        restTemplate.delete(url, params);
    }

    /**
     * 发送put请求
     *
     * @param url(完整url)
     */
    public static <T> void put(String url,
                               T params,
                               Object... uriVariables) {
        RestTemplate restTemplate = getRestTemplate(url);
        restTemplate.put(url, params, uriVariables);
    }

    /**
     * 发送get请求
     *
     * @param url(完整url)
     * @param params(参数)
     * @return
     */
    private static String get(String url, Object... params) {
        RestTemplate restTemplate = getRestTemplate(url);
        ResponseEntity<String> rest = restTemplate.getForEntity(url, String.class, params);
        return rest.getBody();
    }


    /**
     * 获取实体类型的数据
     *
     * @param url    请求url(填写完整)
     * @param params 请求参数
     * @param type   反序列化的类型
     * @param <T>
     * @return
     */
    public static <T> T postObject(String url, T params, Class<T> type) {
        try {
            String res = post(url, params);
            if (res != null && res.startsWith("{")) {
                T a = JSON.parseObject(res, type);
                return a;
            }
        } catch (Exception e) {
            throw e;
        }
        return null;
    }

    private static <T> String post(String url, T params) {
        RestTemplate restTemplate = getRestTemplate(url);
        ResponseEntity<String> rest = restTemplate.postForEntity(url, params, String.class);
        rest.getStatusCode();
        return rest.getBody();
    }

    private static RestTemplate getRestTemplate(String url) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }

}
