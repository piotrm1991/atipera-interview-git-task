package restrepo.client.response;

/**
 * Record representing GitHub Api response containing branch information.
 */
public record BranchApiResponse(

    String name,

    CommitApiResponse commit
) { }
