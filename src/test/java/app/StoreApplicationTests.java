package app;

import app.services.DocumentValidator;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StoreApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void cpf() {
		Assert.assertTrue(DocumentValidator.isCPF("08523020020"));
		Assert.assertTrue(DocumentValidator.isCPF("062.061.850-79"));
	}
}
