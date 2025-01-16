package com.teknofest.turizm.mock;

import com.teknofest.turizm.model.Place;
import com.teknofest.turizm.model.ZoomLevel;
import com.teknofest.turizm.repository.PlaceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Profile("test")
@Component
public class MockDataLoader implements CommandLineRunner {

    private final PlaceRepository placeRepository;

    public MockDataLoader(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (placeRepository.count() == 0) {
            placeRepository.saveAll(Arrays.asList(
                    // VERY_LOW zoom level
                    new Place(1L, "Ayasofya", "İstanbul", "Sultanahmet", "Sultanahmet Mahallesi", 41.0082, 28.9784, true, "Historical", "Ayasofya, tarihi bir camidir.", 5f, "4.7f", ZoomLevel.VERY_LOW),
                    new Place(2L, "Topkapı Sarayı", "İstanbul", "Sultanahmet", "Sultanahmet Mahallesi", 41.0136, 28.9857, true, "Historical", "Topkapı Sarayı, Osmanlı İmparatorluğu'na ait eski bir saraydır.", 5f, "4.8f", ZoomLevel.VERY_LOW),

                    // LOW zoom level
                    new Place(3L, "Kapadokya", "Nevşehir", "Göllü Dağ", "Göllü Dağ Mahallesi", 38.7134, 34.8818, true, "Natural", "Kapadokya, eşsiz kaya oluşumlarıyla ünlüdür.", 5f, "4.6f", ZoomLevel.LOW),
                    new Place(4L, "Pamukkale", "Denizli", "Pamukkale", "Pamukkale Mahallesi", 37.9260, 29.1166, true, "Natural", "Pamukkale, beyaz travertenleriyle ünlüdür.", 5f, "4.9f", ZoomLevel.LOW),

                    // MEDIUM zoom level
                    new Place(5L, "Efes Antik Kenti", "İzmir", "Selçuk", "Selçuk Mahallesi", 37.9500, 27.3667, true, "Historical", "Efes, Antik Roma döneminden kalma büyük bir şehirdir.", 5f, "4.5f", ZoomLevel.MEDIUM),
                    new Place(6L, "Nemrut Dağı", "Adıyaman", "Kahta", "Kahta Mahallesi", 37.9678, 38.7319, true, "Natural", "Nemrut Dağı, devasa heykelleriyle ünlüdür.", 5f, "4.7f", ZoomLevel.MEDIUM),

                    // HIGH zoom level
                    new Place(7L, "Bodrum Kalesi", "Muğla", "Bodrum", "Bodrum Mahallesi", 37.0392, 27.4290, true, "Historical", "Bodrum Kalesi, 15. yüzyıldan kalma tarihi bir yapıdır.", 5f, "4.8f", ZoomLevel.HIGH),
                    new Place(8L, "Fethiye Ölüdeniz", "Muğla", "Fethiye", "Fethiye Mahallesi", 36.5329, 29.1200, true, "Natural", "Ölüdeniz, mavi lagünüyle ünlüdür.", 5f, "4.6f", ZoomLevel.HIGH)
            ));
            System.out.println("Mock veriler başarıyla veri tabanına kaydedildi. (MockDataLoader.class)");
        } else {
            System.out.println("Veri tabanında zaten veriler mevcut. (MockDataLoader.class)");
        }
    }
}
