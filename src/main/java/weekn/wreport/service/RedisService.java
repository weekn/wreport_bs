package weekn.wreport.service;

public interface RedisService {

    public void set(String key, String value);  

    public Object get(String key);  

}
