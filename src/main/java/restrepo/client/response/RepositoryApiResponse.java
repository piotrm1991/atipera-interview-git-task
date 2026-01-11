package restrepo.client.response;

/**
 * Record representing GitHub Api response containing repository information.
 */
public record RepositoryApiResponse(
    String name,

    boolean fork,

    OwnerApiResponse owner
) { }
