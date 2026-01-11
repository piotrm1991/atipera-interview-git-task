package restrepo.client.impl;

import static org.springframework.http.MediaType.APPLICATION_JSON;

import restrepo.exception.EntityNotFoundException;
import restrepo.client.response.BranchApiResponse;
import restrepo.client.response.RepositoryApiResponse;
import restrepo.client.GitHubClient;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

/**
 * Implementation of GitHubApiService interface for handling GitHubApi requests.
 */
@Slf4j
@Service
public class GitHubClientImpl implements GitHubClient {

  private final RestClient restClient;

  private static final String GIT_HUB_API_REPOSITORY_URL = "users/{username}/repos";
  private static final String GIT_HUB_API_BRANCHES_URL = "repos/{owner}/{repoName}/branches";
  private final String baseUrl;

  public GitHubClientImpl(@Value("${github.api.baseUrl}") String baseUrl) {
    restClient = RestClient.create();
    this.baseUrl = baseUrl;
  }

  @Override
  public List<RepositoryApiResponse> getAllRepositoriesOfUser(String username) {
    log.info("Getting all repos from GitHub api for user: {}", username);

    return restClient.get()
        .uri(baseUrl + GIT_HUB_API_REPOSITORY_URL, username)
        .accept(APPLICATION_JSON)
        .retrieve()
        .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
          throw new EntityNotFoundException(String.format("User: %s not found.", username));
        })
        .body(new ParameterizedTypeReference<>() { });
  }

  @Override
  public List<BranchApiResponse> getAllBranches(String owner, String repoName) {
    log.info("Getting all branches from GitHub api for user: {} and repository: {}",
        owner,
        repoName
    );

    return restClient.get()
        .uri(baseUrl + GIT_HUB_API_BRANCHES_URL, owner, repoName)
        .accept(APPLICATION_JSON)
        .retrieve()
        .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
          throw new EntityNotFoundException(
              String.format("User: %s or repository: %s not found.", owner, repoName));
        })
        .body(new ParameterizedTypeReference<>() { });
  }
}
