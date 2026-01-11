package restrepo.integration;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import restrepo.client.impl.GitHubClientImpl;
import restrepo.client.response.RepositoryApiResponse;
import restrepo.exception.EntityNotFoundException;
import restrepo.response.RepositoryResponse;
import restrepo.service.impl.RepositoryServiceImpl;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@WireMockTest(httpPort = 8181)
public class RepositoryServiceIntegrationTest {

    @Autowired
    private RepositoryServiceImpl repositoryService;

    @Autowired
    private GitHubClientImpl gitHubClient;

    @Test
    void getAllPublicRepositoriesWithoutForksIntegrationTest_Correct() {
        List<RepositoryResponse> response = repositoryService.getAllPublicRepositoriesWithoutForks("abcd");
        assertThat(response).isNotNull();
        assertThat(response.size()).isEqualTo(0);

        List<RepositoryApiResponse> responseClient = gitHubClient.getAllRepositoriesOfUser("abcd");
        assertThat(responseClient).isNotNull();
        assertThat(responseClient.size()).isEqualTo(1);

        response = repositoryService.getAllPublicRepositoriesWithoutForks("piotrm1991");
        assertThat(response).isNotNull();
        assertThat(response.size()).isEqualTo(14);

        responseClient = gitHubClient.getAllRepositoriesOfUser("piotrm1991");
        assertThat(responseClient).isNotNull();
        assertThat(responseClient.size()).isEqualTo(14);
    }

    @Test
    void getAllPublicRepositoriesWithoutForksIntegrationTest_NoUser() {
        assertThatThrownBy(() -> {
            repositoryService.getAllPublicRepositoriesWithoutForks("ikiipi");
        }).isInstanceOf(EntityNotFoundException.class).hasMessage("User: ikiipi not found.");
    }
}
