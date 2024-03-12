package hu.preznyak.cmhweddings.bootstrap;

import hu.preznyak.cmhweddings.domain.Contact;
import hu.preznyak.cmhweddings.domain.Wedding;
import hu.preznyak.cmhweddings.repositories.ContactRepository;
import hu.preznyak.cmhweddings.repositories.WeddingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class DataLoader implements CommandLineRunner {

    private final WeddingRepository weddingRepository;
    private final ContactRepository contactRepository;

    @Override
    public void run(String... args) {
        loadWeddingObjects();
        loadContactObjects();
    }

    private void loadWeddingObjects() {
        if (weddingRepository.count() == 0) {
            weddingRepository.save(Wedding.builder()
                    .brideName("Pam Beesly")
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

    private void loadContactObjects() {
        if (contactRepository.count() == 0) {

            Optional<Wedding> schruteWedding = weddingRepository.findByBrideNameAndGroomName("Angela Martin", "Dwight Schrute");
            schruteWedding.ifPresent(wedding -> contactRepository.save(Contact.builder()
                    .name("Michael Scott")
                    .emailAddress("m.scott@dundermifflin.com")
                    .phoneNumber("+587566358622")
                    .wedding(wedding)
                    .build()));

            Optional<Wedding> halpertWedding = weddingRepository.findByBrideNameAndGroomName("Pam Beesly", "Jim Halpert");
            halpertWedding.ifPresent(wedding -> contactRepository.save(Contact.builder()
                    .name("Phyllis Vance")
                    .emailAddress("p.vance@dundermifflin.com")
                    .phoneNumber("+587566358622")
                    .wedding(wedding)
                    .build()));
        }

    }
}
