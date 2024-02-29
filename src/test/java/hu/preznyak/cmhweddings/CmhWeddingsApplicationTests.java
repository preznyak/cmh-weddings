package hu.preznyak.cmhweddings;

import hu.preznyak.cmhweddings.web.model.Wedding;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.time.LocalDate;
import java.util.UUID;

@SpringBootTest
class CmhWeddingsApplicationTests {

	@Autowired
	MongoTemplate mongoTemplate;

	@Test
	void contextLoads() {
	}

	@Test
	void basicTestForSuccess() {
		//given
		UUID testUuid = UUID.randomUUID();
		Wedding toSave = Wedding.builder()
				.id(testUuid)
				.brideName("Test Bride")
				.groomName("Test Groom")
				.location("Testadelphia")
				.date(LocalDate.now())
				.price(300.0)
				.build();

		//when
		mongoTemplate.insert(toSave, "wedding");
		Wedding saved = mongoTemplate.findById(testUuid, Wedding.class);

		//then
		Assertions.assertEquals("Test Groom",saved.getGroomName());

	}
}
