package com.ts.finpredict.FinPredict;

import com.ts.finpredict.FinPredict.model.dao.PredictorDailyDAO;
import com.ts.finpredict.FinPredict.model.dao.PredictorWeeklyDAO;
import com.ts.finpredict.FinPredict.model.entity.PredictorDailyEntity;
import com.ts.finpredict.FinPredict.model.entity.PredictorWeeklyEntity;
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

	@Bean
	public CommandLineRunner commandLineRunner(
			PredictorDailyDAO predictorDailyDAO,
			PredictorWeeklyDAO predictorWeeklyDAO
	) {
		return runner -> {
//			createPredictorDaily(predictorDailyDAO);
//			createPredictorWeekly(predictorWeeklyDAO);

//			readPredictorDailyByDate(predictorDailyDAO, "2024-05-01");
//			readPredictorWeeklyByDate(predictorWeeklyDAO, "2024-05-01");

//			readAllPredictorDaily(predictorDailyDAO);
//			readAllPredictorWeekly(predictorWeeklyDAO);

//			updatePredictorDaily(predictorDailyDAO, "2024-05-01", 24);
//			updatePredictorWeekly(predictorWeeklyDAO, "2024-05-01", 24);

//			deletePredictorDaily(predictorDailyDAO, "2024-05-01");
//			deletePredictorWeekly(predictorWeeklyDAO, "2024-05-01");

//			deleteAllPredictorDaily(predictorDailyDAO);
//			deleteAllPredictorWeekly(predictorWeeklyDAO);
		};
	}

	public void createPredictorDaily(PredictorDailyDAO predictorDailyDAO) {
		PredictorDailyEntity tmpPredictorDailyEntity = new PredictorDailyEntity("2024-06-15", 211);
		predictorDailyDAO.save(tmpPredictorDailyEntity);
	}

	public void createPredictorWeekly(PredictorWeeklyDAO predictorWeeklyDAO) {
		PredictorWeeklyEntity tmpPredictorWeeklyEntity = new PredictorWeeklyEntity("2024-06-13", 311);
		predictorWeeklyDAO.save(tmpPredictorWeeklyEntity);
	}

	public void readPredictorDailyByDate(PredictorDailyDAO predictorDailyDAO, String currentDay) {
		List<PredictorDailyEntity> predictorDailyEntities = predictorDailyDAO.findByDate(currentDay);
		for (PredictorDailyEntity predictorDailyEntity : predictorDailyEntities) {
			System.out.println(predictorDailyEntity);
		}
	}

	public void readPredictorWeeklyByDate(PredictorWeeklyDAO predictorWeeklyDAO, String currentDay) {
		List<PredictorWeeklyEntity> predictorWeeklyEntities = predictorWeeklyDAO.findByDate(currentDay);
		for (PredictorWeeklyEntity predictorWeeklyEntity : predictorWeeklyEntities) {
			System.out.println(predictorWeeklyEntity);
		}
	}

	public void readAllPredictorDaily(PredictorDailyDAO predictorDailyDAO) {
		List<PredictorDailyEntity> predictorDailyEntities = predictorDailyDAO.findAll();

		for (PredictorDailyEntity predictorDailyEntity : predictorDailyEntities) {
			System.out.println(predictorDailyEntity);
		}
	}

	public void readAllPredictorWeekly(PredictorWeeklyDAO predictorWeeklyDAO) {
		List<PredictorWeeklyEntity> predictorWeeklyEntities = predictorWeeklyDAO.findAll();

		for (PredictorWeeklyEntity predictorWeeklyEntity : predictorWeeklyEntities) {
			System.out.println(predictorWeeklyEntity);
		}
	}

	private void updatePredictorDaily(PredictorDailyDAO predictorDailyDAO, String currentDay, int closingPrice) {
		List<PredictorDailyEntity> predictorDailyEntities = predictorDailyDAO.findByDate(currentDay);
		predictorDailyEntities.get(0).setClosingPrice(closingPrice);
		predictorDailyDAO.update(predictorDailyEntities.get(0));

		System.out.println(predictorDailyDAO.findByDate(currentDay));
	}

	private void updatePredictorWeekly(PredictorWeeklyDAO predictorWeeklyDAO, String currentDay, int closingPrice) {
		List<PredictorWeeklyEntity> predictorWeeklyEntities = predictorWeeklyDAO.findByDate(currentDay);
		predictorWeeklyEntities.get(0).setClosingPrice(closingPrice);
		predictorWeeklyDAO.update(predictorWeeklyEntities.get(0));

		System.out.println(predictorWeeklyDAO.findByDate(currentDay));
	}

	private void deletePredictorDaily(PredictorDailyDAO predictorDailyDAO, String currentDay) {
		predictorDailyDAO.delete(currentDay);
	}

	private void deletePredictorWeekly(PredictorWeeklyDAO predictorWeeklyDAO, String currentDay) {
		predictorWeeklyDAO.delete(currentDay);
	}

	private void deleteAllPredictorDaily(PredictorDailyDAO predictorDailyDAO) {
		int numRowsDeleted = predictorDailyDAO.deleteAll();
		System.out.println("Deleted rows: " + numRowsDeleted);
	}

	private void deleteAllPredictorWeekly(PredictorWeeklyDAO predictorWeeklyDAO) {
		int numRowsDeleted = predictorWeeklyDAO.deleteAll();
		System.out.println("Deleted rows: " + numRowsDeleted);
	}
}
