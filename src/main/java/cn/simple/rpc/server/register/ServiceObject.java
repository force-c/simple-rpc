package cn.simple.rpc.server.register;

/**
 *服务持有对象，保存具体的服务信息备用
 *
 * @author guochuang
 * @version v1
 * @date 2021/09/22 13:39:00
 */
public class ServiceObject {

    /**
     * 服务名称
     */
    private String name;

    /**
     * 服务Class
     */
    private Class<?> clazz;

    /**
     * 具体服务
     */
    private Object obj;

    public ServiceObject() {
    }

    public ServiceObject(String name, Class<?> clazz, Object obj) {
        this.name = name;
        this.clazz = clazz;
        this.obj = obj;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}