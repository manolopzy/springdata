package com.worldexplorer.springboot.data.redis;

import redis.clients.jedis.Jedis;

/**
 * podemos almacenar y manipular bits en redis
 * por ejemplo:
 * 1100001
 * en el siguiente comando "mybit" es el clave, 10 es el 
 * numero de posicion del bit 第幾位, el comando modifica 
 * el decimo a 1
 * 將第十位設置爲1
 * setbit mybit 10 1
 * 將第三位設置爲3
 * asigna la tercena posicion el valor 10, pero no es valida 
 * la operacion porque un bit puede ser solo 0 o 1.
 * setbit mybit 3 10
 * (error) ERR bit is not an integer or out of range
 * @author tanku
 *
 */
public class TestStoreBits {

	private static final Jedis jedis = new Jedis();

	public static void main(String[] args) {

		String key = "mybits";
		//
		jedis.setbit(key, 25, true);
		
		jedis.get(key);
		//get the bit at tenth position
		jedis.getbit(key, 10);
		System.out.println(jedis.get(key));
		System.out.println(jedis.getbit(key, 10));
	}
}
