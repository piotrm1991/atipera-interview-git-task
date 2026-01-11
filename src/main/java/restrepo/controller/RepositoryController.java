package restrepo.controller;

import restrepo.response.RepositoryResponse;
import restrepo.service.RepositoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for handling repository-related HTTP requests.
 */
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/repos")
@RestController
public class RepositoryController {

  private final RepositoryService repositoryService;

  /**
   * Handles HTTP GET requests for getting repositories by username.
   *
   * @param username String login of searched user.
   * @return A {@link List} containing RepositoryResponse records.
   */
  @GetMapping("/{username}")
  @ResponseStatus(HttpStatus.OK)
  public List<RepositoryResponse> getAllPublicReposWithoutForks(@PathVariable String username) {
    log.info("GET-request: getting all repositories from user, without forks.");

    return repositoryService.getAllPublicRepositoriesWithoutForks(username);
  }
}
