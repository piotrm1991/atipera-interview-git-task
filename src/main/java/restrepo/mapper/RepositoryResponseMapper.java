package restrepo.mapper;

import restrepo.response.BranchResponse;
import restrepo.response.RepositoryResponse;
import restrepo.client.response.BranchApiResponse;
import restrepo.client.response.RepositoryApiResponse;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Mapper class for mapping records between GitHubApi and application.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class RepositoryResponseMapper {

  /**
   * Maps GitHubApiRepositoryResponse and list of GitHubApiBranchResponse
   * to RepositoryResponse.
   *
   * @param repo RepositoryApiResponse record.
   * @param branches A {@link List} containing ApiBranchApiResponse records.
   * @return RepositoryResponse record.
   */
  public RepositoryResponse mapRepositoryApiResponseAndBranchesToRepositoryResponse(
      RepositoryApiResponse repo,
      List<BranchApiResponse> branches) {
    log.info("Mapping GitHub api response to repository response.");

    List<BranchResponse> branchesResponse = new ArrayList<>();

    branches.forEach(b -> {
      branchesResponse.add(new BranchResponse(b.name(), b.commit().sha()));
    });

    return new RepositoryResponse(repo.name(), repo.owner().login(), branchesResponse);
  }
}
