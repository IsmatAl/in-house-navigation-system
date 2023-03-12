package com.example.inhousenavigationsystem.populator;

import com.example.inhousenavigationsystem.domain.model.MobileStationModel;
import com.example.inhousenavigationsystem.domain.model.MobileStationStatus;
import com.example.inhousenavigationsystem.persistence.entity.BaseStationEntity;
import com.example.inhousenavigationsystem.persistence.entity.MobileStationEntity;
import com.example.inhousenavigationsystem.persistence.entity.BaseStationRepo;
import com.example.inhousenavigationsystem.persistence.entity.MobileStationRepo;
import com.example.inhousenavigationsystem.populator.helper.CustomBSKey;
import com.example.inhousenavigationsystem.populator.helper.CustomMSKey;
import com.opencsv.CSVReaderHeaderAware;
import com.opencsv.exceptions.CsvException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static com.example.inhousenavigationsystem.populator.helper.Helper.distinctByKeyClass;


@Slf4j
@Component
@RequiredArgsConstructor
public class DataPopulator {
	private final MobileStationRepo mobileStationRepo;
	private final BaseStationRepo baseStationRepo;

	@EventListener(ApplicationReadyEvent.class)
	public void populateAllData() throws IOException, CsvException {
		insertStations();
		log.info("Base and mobile stations are inserted for populate database");
	}

	private List<MobileStationEntity> loadMobileStationFromCSV(String fileName) throws IOException, CsvException {
		final ClassPathResource lastNamesFile = new ClassPathResource(fileName);
		try (final CSVReaderHeaderAware csvFileReader = new CSVReaderHeaderAware(
				new BufferedReader(new InputStreamReader(lastNamesFile.getInputStream())))) {
			final List<String[]> allRows = csvFileReader.readAll();
			return (List<MobileStationEntity>) allRows.stream().map(v -> MobileStationEntity.builder().name(v[0]).model(MobileStationModel.valueOf(v[1])).status(MobileStationStatus.valueOf(v[2])).build())
					.filter(distinctByKeyClass(CustomMSKey::new)).toList();
		}
	}

	private List<BaseStationEntity> loadBaseStationFromCSV(String fileName) throws IOException, CsvException {
		final ClassPathResource lastNamesFile = new ClassPathResource(fileName);
		try (final CSVReaderHeaderAware csvFileReader = new CSVReaderHeaderAware(
				new BufferedReader(new InputStreamReader(lastNamesFile.getInputStream())))) {
			final List<String[]> allRows = csvFileReader.readAll();
			return (List<BaseStationEntity>) allRows.stream().map(v -> BaseStationEntity.builder().name(v[0]).x(v[1]).y(v[2])
                            .detectionRadiusInMeters(Float.parseFloat(v[3])).build())
					.filter(distinctByKeyClass(CustomBSKey::new)).toList();
		}
	}

	private void insertStations() throws IOException, CsvException {
		mobileStationRepo.saveAll(loadMobileStationFromCSV("data/mobile_stations.csv"));
		baseStationRepo.saveAll(loadBaseStationFromCSV("data/base_stations.csv"));
	}
}
