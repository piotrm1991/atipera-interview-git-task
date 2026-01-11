package restrepo.response;

import java.util.List;

/**
 * Record representing repository information.
 */
public record RepositoryResponse(
    String name,

    String owner,

    List<BranchResponse> branches
) { }
