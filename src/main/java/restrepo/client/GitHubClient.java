package restrepo.client;

import restrepo.client.response.BranchApiResponse;
import restrepo.client.response.RepositoryApiResponse;
import java.util.List;

/**
 * Service interface for handling GitHubApi requests.
 */
public interface GitHubClient {

  /**
   * Retrieve all public repositories of given user.
   *
   * @param username String.
   * @return A {@link List} containing RepositoryApiResponse records.
   */
  List<RepositoryApiResponse> getAllRepositoriesOfUser(String username);

  /**
   * Retrieve all branches of given repository.
   *
   * @param owner String username of repository owner.
   * @param repoName String repository name.
   * @return A {@link List} containing BranchApiResponse records.
   */
  List<BranchApiResponse> getAllBranches(String owner, String repoName);
}
