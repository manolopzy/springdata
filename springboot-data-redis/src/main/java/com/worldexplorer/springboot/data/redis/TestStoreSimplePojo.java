package com.worldexplorer.springboot.data.redis;

import java.util.Random;

import com.worldexplorer.springboot.data.redis.domain.Equip;
import com.worldexplorer.springboot.data.redis.domain.Skill;
import com.worldexplorer.springboot.data.redis.manager.IdsGenerator;
import com.worldexplorer.springboot.data.redis.manager.JedisManager;

public class TestStoreSimplePojo {
    
    public static void main(String[] args) {
    	JedisManager.initialization();
    	IdsGenerator.initialization();
    	Random random = new Random();
    	
		Equip equip = new Equip(
				IdsGenerator.getAndIncrementEquipId(), 
				random.nextInt(), 
				"fantasy" + random.nextInt(10000), 
				random.nextInt(100000));
		
		JedisManager.save("equips", equip);
		
		System.out.println("equip = " + equip.toString());
		byte[] bytes = JedisManager.get("equips", equip.getId());
		if(bytes != null) {
			System.out.println("byte array size = " + bytes.length);
			for(int i = 0; i < bytes.length; i++) {
				System.out.println("byte " + i + " = " + bytes[i]);
			}
		}
		
		System.out.println(JedisManager.get("equips", equip.getId()));
		equip = new Equip();
		equip = equip.deserialize(bytes);
		System.out.println("equip = " + equip.toString());
		
		Skill skill = new Skill(
				IdsGenerator.getAndIncrementSkillId(), 
				random.nextInt(), 
				IdsGenerator.getAndIncrementUserId(), 
				random.nextInt(100000));
		System.out.println("let save the skill = " + skill.toString());
		JedisManager.save("skills", skill);
		
		bytes = JedisManager.get("skills", skill.getId());
		
		skill = new Skill();
		skill = skill.deserialize(bytes);
		
		System.out.println("skill = " + skill.toString());
	}
}
