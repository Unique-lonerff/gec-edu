package personal.zx.myocean.utils;

import java.io.Serializable;

/**
 * @author 小z
 * @date 2024年10月29日 上午10:34
 */

public class RequestContext implements Serializable {

    private static ThreadLocal<String> remoteAddr = new ThreadLocal<>();
    //获取ip
    public static String getRemoteAddr() {
        return remoteAddr.get();
    }
    //存储远程ip字符串
    public static void setRemoteAddr(String remoteAddr) {
        RequestContext.remoteAddr.set(remoteAddr);
    }


}
