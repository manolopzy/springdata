package com.worldexplorer.springboot.data.redis.helper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.google.gson.Gson;

public class SerializerUtils {

	/**
	 * Gson is typically used by first
	 * constructing a Gson instance and then invoking 
	 * {@link #toJson(Object)} or
	 * {@link #fromJson(String, Class)} methods on it.
	 * Gson instances are Thread-safe so you can reuse 
	 * them freely across multiple threads.
	 */
	private static final Gson GSON = new Gson();

	/**
	 * deserialize an object from a byte array
	 * 
	 * @param bytes
	 * @return
	 */
	public static Object bytesToObject(byte[] bytes) {
		ByteArrayInputStream bais = null;
		try {
			bais = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bais);
			return ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * serialize an object to byte array
	 * 
	 * @param obj
	 * @return
	 */
	public static byte[] objectToBytes(Object obj) {
		ObjectOutputStream out = null;
		ByteArrayOutputStream bytesOut = null;
		try {
			bytesOut = new ByteArrayOutputStream();
			// the object can be read or reconstructed using {@link ObjectInputStream}
			out = new ObjectOutputStream(bytesOut);
			out.writeObject(obj);
			out.flush();
			// or simply using the following
			// oos.writeObject(object);
			byte[] bytes = bytesOut.toByteArray();
			System.out.println("write bytes = " + bytes.length);
			bytesOut.close();
			out.close();
			return bytes;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String objectToJson(Object obj) {
		return GSON.toJson(obj);
	}

	public static <T extends Serializable> T jsonToObject(String json, Class<T> cls) {
		return GSON.fromJson(json, cls);
	}
}
