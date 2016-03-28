package com.xsbweb.util;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.log4j.Logger;

import com.xsbweb.common.serialize.ListTranscoder;
import com.xsbweb.controller.app.AppCustomerController;
import com.xsbweb.vo.Menu;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Transaction;
import redis.clients.jedis.exceptions.JedisException;


public class RedisUtil {

	protected static JedisPool jedisPool;
	
	private Logger log = Logger.getLogger(RedisUtil.class);
	
	public RedisUtil(){}
	
	 /**
    * 获取连接池.
    * @return 连接池实例
    */
	private JedisPool getPool(String ip,int port,int timeout,String password) {
	   JedisPoolConfig config = new JedisPoolConfig();
	   //控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
	   //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
	   config.setTestWhileIdle(true);
	   config.setMinEvictableIdleTimeMillis(60000);
	   config.setTimeBetweenEvictionRunsMillis(30000);
	   config.setNumTestsPerEvictionRun(-1);
	   //控制一个pool最多有多少个状态为idle(空闲)的jedis实例
	   config.setMaxIdle(100);  
	   config.setMaxTotal(60000);
	   //config.setMaxWaitMillis(3000);
	   //表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException
	   config.setTestOnBorrow(true);
	   config.setTestOnReturn(true);
	   try{  
	       /**
	        *如果你遇到 java.net.SocketTimeoutException: Read timed out exception的异常信息
	        *请尝试在构造JedisPool的时候设置自己的超时值. JedisPool默认的超时时间是2秒(单位毫秒)
	        */
		   jedisPool = new JedisPool(config, "127.0.0.1", 6379);
		  //jedisPool = new JedisPool(config, ip, port,timeout , password);
	   } catch(Exception e) {
	       e.printStackTrace();
	   }
       return jedisPool;
   }
	
	protected Jedis getJedis(){
    	return getPool(ConfigUtil.configMap.get("jedis_url"), Integer.parseInt(ConfigUtil.configMap.get("jedis_port")),Integer.parseInt(ConfigUtil.configMap.get("jedis_timeout")),ConfigUtil.configMap.get("jedis_password")).getResource();
    }

//	protected Jedis getJedis() throws JedisException {
//		Jedis jedis = null;
//		try {
//			jedis = new Jedis(ConfigUtil.configMap.get("jedis_url"), Integer.parseInt(ConfigUtil.configMap.get("jedis_port")));
//		} catch (JedisException e) {
//			if (jedis != null) {
//				jedisPool.returnBrokenResource(jedis);
//			}
//			throw e;
//		}
//		return jedis;
//	}

	protected void release(Jedis jedis, boolean isBroken) {
		if (jedis != null) {
			if (isBroken) {
				jedisPool.returnBrokenResource(jedis);
			} else {
				jedisPool.returnResource(jedis);
			}
		}
	}
	
	/**
	 * 公共方法：存String值
	 * @param key	键
	 * @param value	值
	 * @param cacheSeconds	-1是永久有效
	 * @param methodName	功能说明或执行方法名
	 * @return
	 */
	public void setString(String key, String value, int cacheSeconds, String methodName) {
		Jedis jedis = null;
		boolean isBroken = false;
		//log.redislog("redis正在执行" + methodName + " ...");
		try {
			jedis = this.getJedis();
			jedis.set(key, value);
			if (cacheSeconds >= 0) {
				jedis.expire(key, cacheSeconds);
			}
//			log.redislog("redis执行" + methodName + "成功");
		} catch (JedisException e) {
			isBroken = true;
			e.printStackTrace();
//			log.error("redis执行" + methodName + "失败", e);
		} finally {
			release(jedis, isBroken);
		}
	}
	
	public String getString(String key) {
		Jedis jedis = null;
		boolean isBroken = false;
		String lastVal = null;
		try {
			jedis = this.getJedis();
			lastVal = jedis.get(key);
		} catch (Exception e) {
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}
		return lastVal;
	}
	/**
	 * 公共方法：让int的值自动加1
	 * @param key	键
	 * @return
	 */
	public void setIncr(String key, String methodName) {
		Jedis jedis = null;
		boolean isBroken = false;
//		log.redislog("redis正在执行" + methodName + " ...");
		try {
			jedis = this.getJedis();
			jedis.incr(key);
//			log.redislog("redis执行" + methodName + "成功");
		} catch (JedisException e) {
			isBroken = true;
//			log.error("redis执行" + methodName + "失败", e);
		} finally {
			release(jedis, isBroken);
		}
	}
	/**
	 * 公共方法：存String哈希值
	 * @param key
	 * @param field
	 * @param value
	 * @param methodName	功能说明或执行方法名
	 */
	public void setHashByField(String key, String field, String value, String methodName) {
		boolean isBroken = false;
		Jedis jedis = null;
//		log.redislog("redis正在执行" + methodName + " ...");
		try {
			jedis = this.getJedis();
			jedis.hset(key, field, value);
//			log.redislog("redis执行" + methodName + "成功");
		} catch (JedisException e) {
			isBroken = true;
//			log.error("redis执行" + methodName + "失败", e);
		} finally {
			release(jedis, isBroken);
		}
	}
	
	/**
	 * 公共方法：根据多个域存哈希值
	 * @param key
	 * @param map	多个域值
	 * @param cacheSeconds	-1是永久有效
	 * @param methodName	功能说明或执行方法名
	 */
	public void setHashByMap(String key, Map<String, String> map,
			int cacheSeconds, String methodName) {
		boolean isBroken = false;
		Jedis jedis = null;
//		log.redislog("redis正在执行" + methodName + " ...");
		if (map != null && map.size() > 0) {
			try {
				jedis = this.getJedis();
				jedis.hmset(key, map);
				if (cacheSeconds >= 0) {
					jedis.expire(key, cacheSeconds);
				}
//				log.redislog("redis执行" + methodName + "成功");
			} catch (JedisException e) {
				isBroken = true;
//				log.error("redis执行" + methodName + "失败", e);
			} finally {
				release(jedis, isBroken);
			}
		}
	}
	
	/**
	 * 公共方法：根据key获取所有域的值
	 * @param key
	 * @param methodName	功能说明或执行方法名
	 * @return	当key没有对应的值会返回 {}
	 */
	public Map<String, String> getHashMapByKey(String key, String methodName) {
		boolean isBroken = false;
		Jedis jedis = null;
//		log.redislog("redis正在执行" + methodName + " ...");
		try {
			jedis = this.getJedis();
			Map<String, String> valueMap = jedis.hgetAll(key);
//			log.redislog("redis执行" + methodName + "成功");
			return valueMap;
		} catch (JedisException e) {
			isBroken = true;
//			log.error("redis执行" + methodName + "失败", e);
		} finally {
			release(jedis, isBroken);
		}
		return null;
	}
	/**
	 * 公共方法：根据key删除jedis数据
	 * @param key
	 * @param methodName	功能说明或执行方法名
	 */
	public void deleteJedisByKey(String key, String methodName) {
		boolean isBroken = false;
		Jedis jedis = null;
//		log.redislog("redis正在执行" + methodName + " ...");
		try {
			jedis = this.getJedis();
			jedis.del(key);
//			log.redislog("redis执行" + methodName + "成功");
		} catch (Exception e) {
			isBroken = true;
//			log.error("redis执行" + methodName + "失败", e);
		} finally {
			release(jedis, isBroken);
		}
	}

	/**
	 * 把string类型的保存进List类型的jedis
	 * 
	 * @param key
	 * @param value
	 * @param cacheSeconds
	 * @param methodName
	 */
	public void addStringToListJedis(String key, String value,
			int cacheSeconds, String methodName) {
		Jedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = this.getJedis();
			Pipeline pipeline = jedis.pipelined();
			pipeline.sync();
		} catch (Exception e) {
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}
	}
	
	public Long pushDataToListJedis(String key, String data, int cacheSeconds,
			String methodName) {
		Jedis jedis = null;
		boolean isBroken = false;
		Long result = null;
		try {
			jedis = this.getJedis();
			result = jedis.rpush(key, data);
			if (cacheSeconds != 0) {
				jedis.expire(key, cacheSeconds);
			}
		} catch (Exception e) {
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}
		return result;
	}
	
	public List<String> getListFromJedis(String key, String methodName) {
		List<String> list = null;
		boolean isBroken = false;
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			if (jedis.exists(key)) {
				list = jedis.lrange(key, 0, -1);
			}
		} catch (JedisException e) {
			e.printStackTrace();
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}
		return list;
	}

	public void addListToJedis(String key, List<String> list, int cacheSeconds,
			String methodName) {
		if (list != null && list.size() > 0) {
			Jedis jedis = null;
			boolean isBroken = false;
			try {
				jedis = this.getJedis();
				/*// jedis.del("java framework");  
				System.out.println(jedis.lrange("java framework",0,-1));  
				//先向key java framework中存放三条数据  
				jedis.lpush("java framework","spring");  
				jedis.lpush("java framework","struts");  
				 jedis.lpush("java framework","hibernate");  
				 //再取出所有数据jedis.lrange是按范围取出，  
				// 第一个是key，第二个是起始位置，第三个是结束位置，jedis.llen获取长度 -1表示取得所有  
				System.out.println(jedis.lrange("java framework",0,-1));  
				 jedis.del("java framework");
				jedis.rpush("java framework","spring");  
				jedis.rpush("java framework","struts");  
				jedis.rpush("java framework","hibernate"); 
				System.out.println(jedis.lrange("java framework",0,-1));*/
				
				if (jedis.exists(key)) {
					jedis.del(key);
				}
				for (String aList : list) {
					jedis.lpush(key, aList);
				}
				/*System.out.println(jedis.lrange(key,0,-1));
				boolean a = jedis.exists(key);*/
			/*	String[] arr = new String[list.size()];
				String[] arrlist = list.toArray(arr);
				jedis.lpush(key, arrlist);*/
				//jedis.rpush(key, arrlist);
				if (cacheSeconds != 0) {
					jedis.expire(key, cacheSeconds);
				}
			} catch (JedisException e) {
				isBroken = true;
			} catch (Exception e) {
				isBroken = true;
			} finally {
				release(jedis, isBroken);
			}
		}
	}

	public void addToSetJedis(String key, String[] value, String methodName) {
		Jedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = this.getJedis();
			jedis.sadd(key, value);
		} catch (Exception e) {
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}
	}

	public Long removeSetJedis(String key, String value, String methodName) {
		Jedis jedis = null;
		boolean isBroken = false;
		Long result = null;
		try {
			jedis = this.getJedis();
			result = jedis.srem(key, value);
		} catch (Exception e) {
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}

		return result;
	}


	/*public Long pushDataToListJedis(String key, List<String> batchData,
			int cacheSeconds, String methodName) {
		Jedis jedis = null;
		boolean isBroken = false;
		Long result = null;
		try {
			jedis = this.getJedis();
			jedis.del(key);
			result = jedis.lpush(key,
					batchData.toArray(new String[batchData.size()]));
			if (cacheSeconds != 0)
				jedis.expire(key, cacheSeconds);
		} catch (Exception e) {
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}
		return result;
	}
*/
	/**
	 * 删除list中的元素
	 * 
	 * @param key
	 * @param values
	 * @param methodName
	 */
	public void deleteDataFromListJedis(String key, List<String> values,
			String methodName) {
		Jedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = this.getJedis();
			Pipeline pipeline = jedis.pipelined();
			if (values != null && !values.isEmpty()) {
				for (String val : values) {
					pipeline.lrem(key, 0, val);
				}
			}
			pipeline.sync();
		} catch (Exception e) {
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}
	}

	/**
	 * 删除set中的元素
	 * 
	 * @param key
	 * @param values
	 * @param methodName
	 */
	public Long deleteDataFromSetJedis(String key, String member,
			String methodName) {
		Jedis jedis = null;
		boolean isBroken = false;
		Long result = null;
		try {
			jedis = this.getJedis();
			result = jedis.srem(key, member);
		} catch (Exception e) {
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}
		return result;
	}

	public String addHashMapToJedis(String key, Map<String, String> map,
			int cacheSeconds, String methodName) {
		boolean isBroken = false;
		Jedis jedis = null;
		String result = null;

		if (map != null && map.size() > 0) {
			try {
				jedis = this.getJedis();
				result = jedis.hmset(key, map);
				if (cacheSeconds >= 0)
					jedis.expire(key, cacheSeconds);
			} catch (Exception e) {
				isBroken = true;
			} finally {
				release(jedis, isBroken);
			}
		}
		return result;
	}

	public void addHashMapToJedis(String key, String field, String value,
			int cacheSeconds, String methodName) {
		boolean isBroken = false;
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			if (jedis != null) {
				jedis.hset(key, field, value);
				if (cacheSeconds != 0) {
					jedis.expire(key, cacheSeconds);
				}
			}
		} catch (Exception e) {
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}
	}

	public void updateHashMapToJedis(String key, String incrementField,
			long incrementValue, String dateField, String dateValue,
			String methodName) {
		boolean isBroken = false;
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			jedis.hincrBy(key, incrementField, incrementValue);
			jedis.hset(key, dateField, dateValue);
		} catch (Exception e) {
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}
	}


	public List<String> getStringFromJedis(String[] keys, String methodName) {
		Jedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = this.getJedis();
			return jedis.mget(keys);
		} catch (Exception e) {
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}
		return null;
	}
	
	public Set<String> getSetFromJedis(String key, String methodName) {
		Set<String> list = null;
		boolean isBroken = false;
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			if (jedis.exists(key)) {
				list = jedis.smembers(key);
			}
		} catch (Exception e) {
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}
		return list;
	}

	public Map<String, String> getHashMapFromJedis(String key, String methodName) {
		Map<String, String> hashMap = null;
		boolean isBroken = false;
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			hashMap = jedis.hgetAll(key);
		} catch (Exception e) {
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}
		return hashMap;
	}

	public String getHashMapValueFromJedis(String key, String field,
			String methodName) {
		String value = null;
		boolean isBroken = false;
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			if (jedis != null) {
				if (jedis.exists(key)) {
					value = jedis.hget(key, field);
				}
			}
		} catch (Exception e) {
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}
		return value;
	}

	public List<String> getHashMapValueByFieldsFromJedis(String key,
			String[] field, String methodName) {
		List<String> value = null;
		boolean isBroken = false;
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			if (jedis != null) {
				if (jedis.exists(key)) {
					value = jedis.hmget(key, field);
				}
			}
		} catch (Exception e) {
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}
		return value;
	}

	public Long getIdentifyId(String identifyName, String methodName) {
		boolean isBroken = false;
		Jedis jedis = null;
		Long identify = null;
		try {
			jedis = this.getJedis();
			if (jedis != null) {
				identify = jedis.incr(identifyName);
				if (identify == 0) {
					return jedis.incr(identifyName);
				} else {
					return identify;
				}
			}
		} catch (Exception e) {
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}
		return null;
	}

	/**
	 * 根据dbIndex flushDB每个shard
	 * 
	 * @param dbIndex
	 */
	public void flushDBFromJedis(int dbIndex) {
		Jedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = this.getJedis();
			jedis.select(dbIndex);
			jedis.flushDB();
		} catch (Exception e) {
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}
	}

	public boolean existKey(String key, String methodName) {
		Jedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = this.getJedis();
			return jedis.exists(key);
		} catch (Exception e) {
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}
		return false;
	}

	/**
	 * 阻塞型抛出 这个列表的阻塞弹出原语，是rpop函数的阻塞版本当列表中没有任何元素可供弹出的时候，
	 * brpop将会被阻塞，直到等待超时或发现可弹出元素，keys有多个参数时候， 服务器会依次检查各个列表，弹出第一个非空头元素，无超时设置
	 * 
	 * @param timeout
	 *            被阻塞等待的时间以秒为单位
	 * @param key
	 *            需要弹出元素的关键字
	 * @param methodName
	 */
	protected List<String> brpop(int timeout, String key, String methodName) {
		List<String> list = null;
		boolean isBroken = false;
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
//			if (jedis.exists(key)) {
				list = jedis.brpop(timeout, key);
//			}
		} catch (Exception e) {
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}
		return list;
	}

	/**
	 * 右抛出,移除并返回列表 key 的尾元素。
	 * 
	 * @param key
	 *            需要弹出元素的关键字
	 * @param methodName
	 */
	protected String rpop(String key, String methodName) {
		String str = null;
		boolean isBroken = false;
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			if (jedis.exists(key)) {
				str = jedis.rpop(key);
			}
		} catch (Exception e) {
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}
		return str;
	}

	/**
	 * 有序set类型jedis 将一个或多个member元素及其score值加入到有序集key当中，如果某个member已经是有序集的成员，
	 * 那么更新这个member的score值，并通过重新插入这个member 元素，来保证该member在正确的位置上。 score
	 * 值可以是整数值或双精度浮点数。如果key不存在，则创建一个空的有序集并执行ZADD操作。 当key存在但不是有序集类型时，返回一个错误
	 * 
	 * @param key
	 *            有序集合的关键字
	 * @param score
	 *            有序集合的score值，排序的先后根据这个进行
	 * @param member
	 *            有序集合的字符串值
	 * @param methodName
	 * @return 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员
	 */
	protected Long zadd(String key, double score, String member,
			String methodName) {
		Long result = null;
		boolean isBroken = false;
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			result = jedis.zadd(key, score, member);
		} catch (Exception e) {
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}
		return result;
	}

	/**
	 * 返回有序集 key 中，指定区间内的成员其中成员的位置按 score 值递增(从小到大)来排序
	 * 
	 * @param key
	 *            有序集合的关键字
	 * @param start
	 *            集合中的开始位置
	 * @param end
	 *            集合中的结束位置
	 * @param methodName
	 * @return 指定区间内的有序集成员
	 */
	protected Set<String> zrange(String key, long start, long end,
			String methodName) {
		Set<String> result = null;
		boolean isBroken = false;
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			result = jedis.zrange(key, start, end);
		} catch (Exception e) {
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}
		return result;
	}

	/**
	 * 返回有序集 key 中，指定区间内的成员其中成员的位置按 score 值递增(从大到小)来排序
	 * 
	 * @param key
	 *            有序集合的关键字
	 * @param start
	 *            集合中的开始位置
	 * @param end
	 *            集合中的结束位置
	 * @param methodName
	 * @return 指定区间内的有序集成员
	 */
	protected Set<String> zrevrange(String key, long start, long end,
			String methodName) {
		Set<String> result = null;
		boolean isBroken = false;
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			result = jedis.zrevrange(key, start, end);
		} catch (Exception e) {
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}
		return result;
	}

	/**
	 * 返回有序集 key 中，指定区间内的成员其中成员的位置按 score 值递增(从小到大)来排序
	 * 
	 * @param key
	 *            有序集合的关键字
	 * @param minScore
	 *            集合中的最小score
	 * @param maxScore
	 *            集合中的最大score
	 * @param methodName
	 * @return 指定区间内的有序集成员
	 */
	protected Set<String> zrangeByScore(String key, double minScore,
			double maxScore, String methodName) {
		Set<String> result = null;
		boolean isBroken = false;
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			result = jedis.zrangeByScore(key, minScore, maxScore);
		} catch (Exception e) {
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}
		return result;
	}

	/**
	 * 移除有序集 key 中的一个或多个成员，不存在的成员将被忽略，当 key 存在但不是有序集类型时，返回一个错误
	 * 
	 * @param key
	 * @param member
	 * @param methodName
	 * @return 被成功移除的成员的数量，不包括被忽略的成员
	 */
	protected Long zrem(String key, String member, String methodName) {
		Jedis jedis = null;
		boolean isBroken = false;
		Long result = null;
		try {
			jedis = this.getJedis();
			result = jedis.zrem(key, member);
		} catch (Exception e) {
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}
		return result;
	}

	/**
	 * 获取集合中元素的数量
	 * 
	 * @param key
	 * @param methodName
	 * @return 集合的元素个数。当 key 不存在时,返回 0
	 */
	protected Long scard(String key, String methodName) {
		Jedis jedis = null;
		boolean isBroken = false;
		Long result = null;
		try {
			jedis = this.getJedis();
			result = jedis.scard(key);
		} catch (Exception e) {
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}
		return result;
	}

	/**
	 * 返回一个集合的全部成员，该集合是所有给定集合之间的差集，不存在的 key 被视为空集
	 * 
	 * @param methodName
	 * @param keys
	 *            keys 差集合关键字，可以是多个关键字
	 * @return 返回一个集合，里面包含差集成员
	 */
	protected Set<String> sdiff(String methodName, final String... keys) {
		Set<String> list = null;
		boolean isBroken = false;
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			list = jedis.sdiff(keys);
		} catch (Exception e) {
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}
		return list;
	}

	/**
	 * 在特定的key上设置超时时间seconds，时间以秒为单位，在超时时间之后该key会自动在server端删除.
	 * 
	 * @param key
	 * @param seconds
	 *            超时的时间，以秒为单位
	 * @param methodName
	 * @return 如果超时时间被设定了则返回1 如果该key不存在或者timeout时间没设定则返回0
	 */
	protected Long expire(String key, int seconds, String methodName) {
		Long result = null;
		boolean isBroken = false;
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			result = jedis.expire(key, seconds);
		} catch (Exception e) {
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}
		return result;
	}

	/**
	 * 和sdiff类似，但它将结果保存到 dstkey集合，而不是简单地返回结果集。如果 dstkey集合已经存在，则将其覆盖。dstkey可以是
	 * key 本身
	 * 
	 * @param dstkey
	 *            目标集合关键字
	 * @param methodName
	 * @param keys
	 *            keys 差集合关键字，可以是多个关键字
	 * @return 目标集合的元素个数
	 */
	protected Long sdiffstore(String dstkey, String methodName,
			final String... keys) {
		Long result = null;
		boolean isBroken = false;
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			result = jedis.sdiffstore(dstkey, keys);
		} catch (Exception e) {
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}
		return result;
	}

	/**
	 * 查看哈希表key中，给定域field是否存在
	 * 
	 * @param key
	 *            目标集合关键字
	 * @param field
	 *            差集合关键字，可以是多个关键字
	 * @param methodName
	 * @return 如果哈希表含有给定域，返回true 如果哈希表不含有给定域，或 key不存在，返回false
	 */
	protected Boolean hexists(String key, String field, String methodName) {
		Boolean result = false;
		boolean isBroken = false;
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			result = jedis.hexists(key, field);
		} catch (Exception e) {
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}
		return result;
	}

	/**
	 * 返回列表 key 中，下标为 index 的元素,下标(index)参数 start 和 stop 都以 0 为底， 也就是说，以 0
	 * 表示列表的第一个元素，以 1 表示列表的第二个元素，以此类推.你也可以使用负数下标， 以 -1 表示列表的最后一个元素， -2
	 * 表示列表的倒数第二个元素，以此类推.如果 key 不是列表类型， 返回一个错误
	 * 
	 * @param key
	 *            列表类型的关键字
	 * @param index
	 *            列表中的下标
	 * @param methodName
	 * @return 列表中下标为 index 的元素,如果 index 参数的值不在列表的区间范围内(out of range),返回null.
	 */
	protected String lindex(String key, long index, String methodName) {
		String result = "";
		boolean isBroken = false;
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			result = jedis.lindex(key, index);
		} catch (Exception e) {
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}
		return result;
	}

	/**
	 * 返回列表key中指定区间内的元素，区间以偏移量start和end指定，start与end的说明和ltrim函数一样
	 * 
	 * @param key
	 *            列表类型的关键字
	 * @param start
	 *            开始位置
	 * @param end
	 *            结束位置
	 * @param methodName
	 * @return 一个列表，包含指定区间内的元素
	 */
	protected List<String> lrange(String key, long start, long end,
			String methodName) {
		List<String> result = null;
		boolean isBroken = false;
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			result = jedis.lrange(key, start, end);
		} catch (Exception e) {
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}
		return result;
	}

	/**
	 * 返回列表key中指定区间内的元素，区间以偏移量start和end指定，start与end的说明和ltrim函数一样
	 * 
	 * @param key
	 *            列表类型的关键字
	 * @param start
	 *            开始位置
	 * @param end
	 *            结束位置
	 * @param methodName
	 * @return 一个列表，包含指定区间内的元素
	 */
	protected Long llen(String key, String methodName) {
		Long result = 0L;
		boolean isBroken = false;
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			result = jedis.llen(key);
		} catch (Exception e) {
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}
		return result;
	}

	/**
	 * 获得指定有序序列中的member的下标值
	 * 
	 * @param key
	 *            列表类型的关键字
	 * @param member
	 *            member
	 * @return 下标值，从0开始
	 */
	protected Long zrank(String key, String member) {
		Long result = 0L;
		boolean isBroken = false;
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			result = jedis.zrank(key, member);
		} catch (Exception e) {
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}
		return result;
	}

	/**
	 * 获得指定有序序列中的member的score
	 * 
	 * @param key
	 *            列表类型的关键字
	 * @param member
	 *            member
	 * @return 指定member的score值
	 */
	protected String zscore(String key, String member) {
		String result = "";
		boolean isBroken = false;
		Jedis jedis = null;
		try {
			jedis = this.getJedis();
			result = jedis.zscore(key, member) + "";
		} catch (Exception e) {
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}
		return result;
	}

	public Long hdelHashMapToJedis(String key, String field) {
		boolean isBroken = false;
		Jedis jedis = null;
		Long returnNum = null;
		try {
			jedis = this.getJedis();
			if (jedis != null) {
				returnNum = jedis.hdel(key, field);
			}
		} catch (Exception e) {
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}

		return returnNum;
	}

	public Long addToSetJedisForStringValue(String key, String value,
			String methodName) {
		Jedis jedis = null;
		boolean isBroken = false;
		Long returnNum = null;

		try {
			jedis = this.getJedis();
			returnNum = jedis.sadd(key, value);
		} catch (Exception e) {
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}

		return returnNum;
	}

	/**
	 * 获取集合中元素的数量
	 * 
	 * @param key
	 * @param methodName
	 * @return 集合的元素个数。当 key 不存在时,返回 0
	 */
	protected String spopUtils(String key, String methodName) {
		Jedis jedis = null;
		boolean isBroken = false;
		String result = null;
		try {
			jedis = this.getJedis();
			result = jedis.spop(key);
		} catch (Exception e) {
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}
		return result;
	}

	/**
	 * 获取集合中元素的数量
	 * 
	 * @param key
	 * @param methodName
	 * @return 集合的元素个数。当 key 不存在时,返回 0
	 */
	protected String srandmemberUtils(String key, String methodName) {
		Jedis jedis = null;
		boolean isBroken = false;
		String result = null;
		try {
			jedis = this.getJedis();
			result = jedis.srandmember(key);
		} catch (Exception e) {
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}
		return result;
	}


	/**
	 * 从左边取一个值
	 * 
	 * @param key
	 * @param value
	 * @return 返回一个值
	 */
	protected String lpopUtils(String key) {
		Jedis jedis = null;
		boolean isBroken = false;
		String result = null;
		try {
			jedis = this.getJedis();
			result = jedis.lpop(key);
		} catch (Exception e) {
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}
		return result;
	}

	/**
	 * 执行以下两个动作，将列表srckey中的最后一个元素(尾元素)弹出，并返回给客户端. 将 srckey弹出的元素插入到列表 dstkey，作为
	 * dstkey列表的的头元素, 如果 srckey不存在，值 nil 被返回，并且不执行其他动作. 如果
	 * srckey和dstkey相同，则列表中的表尾元素被移动到表头，并返回该元素， 可以把这种特殊情况视作列表的旋转(rotation)操作
	 * 
	 * @param srckey
	 *            源列表关键字
	 * @param dstkey
	 *            目标列表关键字
	 * @return 被弹出的元素
	 */
	protected String rpoplpush(String srckey, String dstkey) {
		Jedis jedis = null;
		boolean isBroken = false;
		String result = null;
		try {
			jedis = this.getJedis();
			result = jedis.rpoplpush(srckey, dstkey);
		} catch (Exception e) {
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}
		return result;
	}

	/**
	 * 获取字符串值
	 * 
	 * @param key
	 * @return String
	 * @author EX-wanghu001
	 */
	protected String getUtils(String key) {
		Jedis jedis = null;
		boolean isBroken = false;
		String result = null;
		try {
			jedis = this.getJedis();
			result = jedis.get(key);
		} catch (Exception e) {
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}
		return result;
	}

	/**
	 * 获得事物
	 * 
	 * @return 返回一个Transaction类型的对象
	 */
	protected Transaction getTransaction() {
		return this.getJedis().multi();
	}
	
	public <T> void addToListJedis(String key, List<T> menuList) {
		ListTranscoder listTranscoder = new ListTranscoder();
		byte[] result = null;
		if(menuList==null){
			result = null;
		}else{
			result = listTranscoder.serialize(menuList);
		}
		Jedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = this.getJedis();
			jedis.set(key.getBytes(), result);
		} catch (Exception e) {
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}
	}
	public <T> List<T> getToListJedis(String key) {
		ListTranscoder listTranscoder = new ListTranscoder();
		Jedis jedis = null;
		List results = null;
		boolean isBroken = false;
		try {
			jedis = this.getJedis();	
			byte[] byts = jedis.get(key.getBytes());
			results = listTranscoder.deserialize(byts);
		} catch (Exception e) {
			isBroken = true;
		} finally {
			release(jedis, isBroken);
		}
		return results;
	}
}