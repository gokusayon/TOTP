package com.bits.ns.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bits.ns.entities.User;

@Service
public class CommonServices {

	Logger logger = LoggerFactory.getLogger(getClass());
	public static boolean isFirstTime = true;

	@Autowired
	TOTPService totpService;

	@Autowired
	AuthServcies authServcies;
	
	public String logTotp(String steps, String HmacSHA1 , long testTime) {

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		df.setTimeZone(TimeZone.getTimeZone("UTC"));
		String fmtTime = String.format("%1$-10s", testTime);
		String utcTime = df.format(new Date(testTime * 1000));
		
		
		if (isFirstTime) {
			logger.info("+--------------+-----------------------+" + "------------------+--------+--------+");
			logger.info("|  Time(sec)   |   Time (UTC format)   " + "| Value of T(Hex)  |  TOTP  | Mode   |");
			logger.info("+--------------+-----------------------+" + "------------------+--------+--------+");
			isFirstTime = false;
		}

//		logger.info("+--------------+-----------------------+" + "------------------+--------+--------+");
		
		logger.info("|  " + fmtTime + "  |  " + utcTime + "  | " + steps + " |" + HmacSHA1 + "| SHA1   |");
		
		logger.info("+--------------+-----------------------+------------------+--------+--------+");
		
		return HmacSHA1;
	}

	public String getTotp(String username, String domain) {
		// Fetch UserDetails form DB
		User user = authServcies.getUserByUsernameAndApplication(username, domain);
		
		if(user == null) {
			throw new RuntimeException("User Not Found");
		}
		String seed = user.getSeed();
		long T0 = user.getT0();
		long X = user.getX();
		long testTime = user.getTestTime();

		
		// Proccess info
		String steps = "0";
		
		long T = (testTime - T0) / X;

		steps = Long.toHexString(T).toUpperCase();
		while (steps.length() < 16)
			steps = "0" + steps;

		
		// Generate Totp
		return logTotp(steps, totpService.generateTOTP(seed, steps, "8", "HmacSHA1"), testTime); 
	}
	
	public boolean compare(String username, String domain, String client_totp) {
		String server_totp = getTotp(username, domain);
		if(server_totp.equals(client_totp)) {
			return true;
		}else return false;
	}

	public String getTotp(long id) {
		// Fetch UserDetails form DB
		User user = authServcies.getUserById(id);
		String seed = user.getSeed();
		long T0 = user.getT0();
		long X = user.getX();
		long testTime = user.getTestTime();

		
		// Proccess info
		String steps = "0";
		
		long T = (testTime - T0) / X;

		steps = Long.toHexString(T).toUpperCase();
		while (steps.length() < 16)
			steps = "0" + steps;

		
		// Generate Totp
		return logTotp(steps, totpService.generateTOTP(seed, steps, "8", "HmacSHA1"), testTime); 
	}
}
