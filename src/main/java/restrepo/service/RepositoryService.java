package restrepo.service;

import restrepo.response.RepositoryResponse;
import java.util.List;

/**
 * Service interface for handling repository information requests.
 */
public interface RepositoryService {

  /**
   * Retrieve all repositories of given user without forks.
   *
   * @param username String request login of user.
   * @return A {@link List} containing RepositoryResponse records.
   */
  List<RepositoryResponse> getAllPublicRepositoriesWithoutForks(String username);
}
