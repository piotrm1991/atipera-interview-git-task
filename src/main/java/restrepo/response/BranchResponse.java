package restrepo.response;

/**
 * Record representing response containing branch information.
 */
public record BranchResponse(

    String name,

    String sha
) { }
