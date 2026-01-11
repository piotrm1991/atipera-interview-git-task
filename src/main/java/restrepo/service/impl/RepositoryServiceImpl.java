package restrepo.service.impl;

import restrepo.mapper.RepositoryResponseMapper;
import restrepo.response.RepositoryResponse;
import restrepo.client.response.BranchApiResponse;
import restrepo.client.GitHubClient;
import restrepo.service.RepositoryService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Implementation of the RepositoryService interface for managing repository related requests.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RepositoryServiceImpl implements RepositoryService {

  private final GitHubClient gitHubApiService;
  private final RepositoryResponseMapper mapper;

  @Override
  public List<RepositoryResponse> getAllPublicRepositoriesWithoutForks(String username) {
    log.info("Getting all repos without forks for user: {}", username);

    List<RepositoryResponse> result = new ArrayList<RepositoryResponse>();

    gitHubApiService
            .getAllRepositoriesOfUser(username)
            .stream()
            .filter(repo -> !repo.fork())
            .forEach(repo -> {
              List<BranchApiResponse> branches = gitHubApiService.getAllBranches(repo.owner().login(), repo.name());
              result.add(mapper.mapRepositoryApiResponseAndBranchesToRepositoryResponse(repo, branches));
            });

    return result;
  }
}
