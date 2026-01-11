package restrepo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import restrepo.controller.RepositoryController;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AtiperaInterviewGitTaskApplicationTests {

	@Autowired
	private RepositoryController repositoryController;

	@Test
	void contextLoads() {
		assertThat(repositoryController).isNotNull();
	}
}
