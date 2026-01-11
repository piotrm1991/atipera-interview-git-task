package restrepo.integration;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.servlet.client.RestTestClient;
import restrepo.controller.RepositoryController;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import restrepo.response.ExceptionResponse;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureRestTestClient
@WireMockTest(httpPort = 8181)
public class RepositoryControllerIntegrationTest {

    @Autowired
    private RepositoryController repositoryController;

    @LocalServerPort
    private int port;

    @Autowired
    private RestTestClient restTestClient;

    @Test
    void getAllPublicReposWithoutForksTest_Correct() {
        restTestClient.get().uri("http://localhost:%d/repos/%s".formatted(port, "abcd"))
                .exchange()
                .expectStatus().isOk()
                .expectBody(List.class)
                .consumeWith(r -> {
                    assertThat(r).isNotNull();
                    assertThat(r.getResponseBody().size()).isEqualTo(0);
                });
        restTestClient.get().uri("http://localhost:%d/repos/%s".formatted(port, "piotrm1991"))
                .exchange()
                .expectStatus().isOk()
                .expectBody(List.class)
                .consumeWith(r -> {
                    assertThat(r).isNotNull();
                    assertThat(r.getResponseBody().size()).isEqualTo(14);
                });
        restTestClient.get().uri("http://localhost:%d/repos/%s".formatted(port, "ikiipi"))
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(ExceptionResponse.class)
                .consumeWith(r -> {
                    assertThat(r.getResponseBody()).isEqualTo(new ExceptionResponse(404, "User: ikiipi not found."));
                });
    }
}
