package cn.simple.rpc.exception;

import sun.net.www.http.HttpClient;

/**
 * @author guochuang
 * @version v1
 * @modifier guochuang
 * @date 2021/08/16 13:02:00
 */
public class SimpleException extends RuntimeException{
    private static final long serialVersionUID = -698201743720368976L;

    public SimpleException(String message) {
        super(message);
    }

}
