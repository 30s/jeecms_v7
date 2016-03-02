package com.jeecms.common.web.session.id;

import java.util.UUID;

import org.apache.commons.lang.StringUtils;
//import org.safehaus.uuid.UUID;
//import org.safehaus.uuid.UUIDGenerator;
import com.fasterxml.uuid.impl.RandomBasedGenerator;

/**
 * 通过UUID生成SESSION ID
 */
public class JugUUIDGenerator implements SessionIdGenerator {
	public String get() {
//		UUID uuid = UUIDGenerator.getInstance().generateRandomBasedUUID();
		UUID uuid = new RandomBasedGenerator(null).generate();
		return StringUtils.remove(uuid.toString(), '-');
	}

	public static void main(String[] args) {
//		UUIDGenerator.getInstance().generateRandomBasedUUID();
		new RandomBasedGenerator(null).generate();
		long time = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
//			UUIDGenerator.getInstance().generateRandomBasedUUID();
			new RandomBasedGenerator(null).generate();
		}
		time = System.currentTimeMillis() - time;
		System.out.println(time);
	}
}
