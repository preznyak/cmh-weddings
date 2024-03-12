package hu.preznyak.cmhweddings.bootstrap;

import hu.preznyak.cmhweddings.domain.Wedding;
import hu.preznyak.cmhweddings.repositories.WeddingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@RequiredArgsConstructor
@Component
public class WeddingLoader implements CommandLineRunner {

    private final WeddingRepository weddingRepository;

    @Override
    public void run(String... args) throws Exception {
        loadWeddingObjects();
    }

    private void loadWeddingObjects() {
        if (weddingRepository.count() == 0) {
            weddingRepository.save(Wedding.builder()
                    .brideName("Pam Beesley")
                    .groomName("Jim Halpert")
                    .date(LocalDate.of(2009, 4, 25))
                    .location("Niagara Falls")
                    .price(new BigDecimal(500))
                    .currency("USD")
                    .build());

            weddingRepository.save(Wedding.builder()
                    .brideName("Angela Martin")
                    .groomName("Dwight Schrute")
                    .date(LocalDate.of(2013, 6, 15))
                    .location("Shrute Farms")
                    .price(new BigDecimal(740))
                    .currency("USD")
                    .build());
        }
    }
}
