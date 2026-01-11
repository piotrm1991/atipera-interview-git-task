package restrepo.client.response;

/**
 * Record representing GitHub Api response containing repository owner information.
 */
public record OwnerApiResponse(
    String login
) { }
