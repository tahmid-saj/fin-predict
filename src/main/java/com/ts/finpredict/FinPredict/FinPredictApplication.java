package com.ts.finpredict.FinPredict;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
		scanBasePackages = {
				"com.ts.finpredict.assets",
				"com.ts.finpredict.util"
		}
)
public class FinPredictApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinPredictApplication.class, args);
	}

}
