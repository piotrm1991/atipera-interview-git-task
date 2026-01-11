package restrepo.client.response;

/**
 * Record representing GitHub Api response containing commit information.
 */
public record CommitApiResponse(
    String sha
) { }
