package weekn.wreport.service.imp;


import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import weekn.wreport.service.RedisService;


@Service
public class RedisServiceImpl implements RedisService {
	private String host="192.168.181.234";
	@Override
	public void set(String key, String value) {
		Jedis jedis = new Jedis(this.host);
		jedis.setex(key, 3000, value);
		jedis.close();
		
	}

	@Override
	public Object get(String key) {
		Jedis jedis = new Jedis(this.host);
		String res= jedis.get(key);
		jedis.close();
		return res;
		
	}

    
}
