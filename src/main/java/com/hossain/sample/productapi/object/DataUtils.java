package com.hossain.sample.productapi.object;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import com.google.gson.Gson;


public class DataUtils {
	private static final Logger log = LoggerFactory.getLogger(DataUtils.class);
	private ReentrantLock lock = new ReentrantLock();

	public <T> T getJsonObject(String location, String filename, Type typeof) {
		StringBuilder value = getFileContent(location, filename + ".json");
		if(value.length()>0) {
			Gson gson = new Gson();
			return gson.fromJson(value.toString(), typeof);
		}
		return null;
	}

	private StringBuilder getFileContent(String fileLocation, String fileName) {
		lock.lock();
		StringBuilder value = new StringBuilder();
		try (BufferedReader br = new BufferedReader(
				new InputStreamReader(new ClassPathResource(fileLocation + fileName).getInputStream()))) {
			String line = "";
			while ((line = br.readLine()) != null) {
				value.append(line);
			}
		}catch (NullPointerException | IOException e) {
			log.error("Failed reading file at "+ fileLocation +fileName + ". "+e);
		}finally {
			lock.unlock();
		}
		return value;
		
	}

}
