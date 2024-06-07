package com.ts.finpredict.FinPredict;

//import com.ts.finpredict.FinPredict.model.entity.PredictorDailyEntity;
//import com.ts.finpredict.FinPredict.model.entity.PredictorWeeklyEntity;
//import com.ts.finpredict.FinPredict.model.service.PredictorDailyService;
//import com.ts.finpredict.FinPredict.model.service.PredictorWeeklyService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication(
		scanBasePackages = {
				"com.ts.finpredict.*"
		}
)
public class FinPredictApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinPredictApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner commandLineRunner(
//			PredictorDailyService predictorDailyService,
//			PredictorWeeklyService predictorWeeklyService
//	) {
//		return runner -> {
////			createPredictorDaily(predictorDailyService);
////			createPredictorWeekly(predictorWeeklyService);
//
////			readPredictorDailyByDate(predictorDailyService, "2024-05-01");
////			readPredictorWeeklyByDate(predictorWeeklyService, "2024-05-01");
//
////			readAllPredictorDaily(predictorDailyService);
////			readAllPredictorWeekly(predictorWeeklyService);
//
////			updatePredictorDaily(predictorDailyService, "2024-05-01", 24);
////			updatePredictorWeekly(predictorWeeklyService, "2024-05-01", 24);
//
////			deletePredictorDaily(predictorDailyService, "2024-05-01");
////			deletePredictorWeekly(predictorWeeklyService, "2024-05-01");
//
////			deleteAllPredictorDaily(predictorDailyService);
////			deleteAllPredictorWeekly(predictorWeeklyService);
//		};
//	}
//
//	public void createPredictorDaily(PredictorDailyService predictorDailyService) {
//		PredictorDailyEntity tmpPredictorDailyEntity = new PredictorDailyEntity("2024-06-15", 211);
//		predictorDailyService.save(tmpPredictorDailyEntity);
//	}
//
//	public void createPredictorWeekly(PredictorWeeklyService predictorWeeklyService) {
//		PredictorWeeklyEntity tmpPredictorWeeklyEntity = new PredictorWeeklyEntity("2024-06-13", 311);
//		predictorWeeklyService.save(tmpPredictorWeeklyEntity);
//	}
//
//	public void readPredictorDailyByDate(PredictorDailyService predictorDailyService, String currentDay) {
//		List<PredictorDailyEntity> predictorDailyEntities = predictorDailyService.findByDate(currentDay);
//		for (PredictorDailyEntity predictorDailyEntity : predictorDailyEntities) {
//			System.out.println(predictorDailyEntity);
//		}
//	}
//
//	public void readPredictorWeeklyByDate(PredictorWeeklyService predictorWeeklyService, String currentDay) {
//		List<PredictorWeeklyEntity> predictorWeeklyEntities = predictorWeeklyService.findByDate(currentDay);
//		for (PredictorWeeklyEntity predictorWeeklyEntity : predictorWeeklyEntities) {
//			System.out.println(predictorWeeklyEntity);
//		}
//	}
//
//	public void readAllPredictorDaily(PredictorDailyService predictorDailyService) {
//		List<PredictorDailyEntity> predictorDailyEntities = predictorDailyService.findAll();
//
//		for (PredictorDailyEntity predictorDailyEntity : predictorDailyEntities) {
//			System.out.println(predictorDailyEntity);
//		}
//	}
//
//	public void readAllPredictorWeekly(PredictorWeeklyService predictorWeeklyService) {
//		List<PredictorWeeklyEntity> predictorWeeklyEntities = predictorWeeklyService.findAll();
//
//		for (PredictorWeeklyEntity predictorWeeklyEntity : predictorWeeklyEntities) {
//			System.out.println(predictorWeeklyEntity);
//		}
//	}
//
//	private void updatePredictorDaily(PredictorDailyService predictorDailyService, String currentDay, int closingPrice) {
//		List<PredictorDailyEntity> predictorDailyEntities = predictorDailyService.findByDate(currentDay);
//		predictorDailyEntities.get(0).setClosingPrice(closingPrice);
//		predictorDailyService.update(predictorDailyEntities.get(0));
//
//		System.out.println(predictorDailyService.findByDate(currentDay));
//	}
//
//	private void updatePredictorWeekly(PredictorWeeklyService predictorWeeklyService, String currentDay, int closingPrice) {
//		List<PredictorWeeklyEntity> predictorWeeklyEntities = predictorWeeklyService.findByDate(currentDay);
//		predictorWeeklyEntities.get(0).setClosingPrice(closingPrice);
//		predictorWeeklyService.update(predictorWeeklyEntities.get(0));
//
//		System.out.println(predictorWeeklyService.findByDate(currentDay));
//	}
//
//	private void deletePredictorDaily(PredictorDailyService predictorDailyService, String currentDay) {
//		predictorDailyService.delete(currentDay);
//	}
//
//	private void deletePredictorWeekly(PredictorWeeklyService predictorWeeklyService, String currentDay) {
//		predictorWeeklyService.delete(currentDay);
//	}
//
//	private void deleteAllPredictorDaily(PredictorDailyService predictorDailyService) {
//		int numRowsDeleted = predictorDailyService.deleteAll();
//		System.out.println("Deleted rows: " + numRowsDeleted);
//	}
//
//	private void deleteAllPredictorWeekly(PredictorWeeklyService predictorWeeklyService) {
//		int numRowsDeleted = predictorWeeklyService.deleteAll();
//		System.out.println("Deleted rows: " + numRowsDeleted);
//	}
}
