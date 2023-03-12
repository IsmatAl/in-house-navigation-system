package com.example.inhousenavigationsystem.populator;

import com.example.inhousenavigationsystem.persistence.entity.MobileStationEntity;
import com.example.inhousenavigationsystem.persistence.entity.MobileStationLocationEntity;
import com.example.inhousenavigationsystem.persistence.entity.MobileStationRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class FakeReceiverService {
    private static final int N = 30;
    private static final int MAX_X = 2500;
    private static final int MAX_Y = 2050;
    private final Random random = new Random();

    private final MobileStationRepo mobileStationRepo;

    @Scheduled(fixedRate = 6000)
    public void sendCoordinates() {
        final List<Pair<Float, Float>> coordinates = generateCoordinates();
        final List<MobileStationEntity> mobileStationEntities = mobileStationRepo.findAll();
        for (final MobileStationEntity mobileStationEntity : mobileStationEntities) {
            final Pair<Float, Float> newCoordinates = coordinates.get(random.nextInt(N));

            final MobileStationLocationEntity mobileStationLocation = MobileStationLocationEntity.builder().x(newCoordinates.getFirst().toString())
                    .y(newCoordinates.getSecond().toString()).build();

            mobileStationEntity.getLocations().add(mobileStationLocation);
            mobileStationRepo.save(mobileStationEntity);

        }
    }

    private List<Pair<Float, Float>> generateCoordinates() {
        final List<Pair<Float, Float>> coordinates = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            float x = (float) (random.nextDouble() * MAX_X);
            float y = (float) (random.nextDouble() * MAX_Y);
            coordinates.add(Pair.of(x, y));
        }
        return coordinates;
    }
}
