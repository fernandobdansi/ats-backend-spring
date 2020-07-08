package edu.ifes.ci.si.les.ats.config;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import edu.ifes.ci.si.les.ats.services._DBService;

@Configuration
@Profile("test")
public class ConfigurationDBTest {

	@Autowired
	private _DBService dbService;

	@Bean
	public boolean instatiateDatabase() throws ParseException, IOException {
		dbService.instantiateTestDatabase();
		return true;
	}

}
