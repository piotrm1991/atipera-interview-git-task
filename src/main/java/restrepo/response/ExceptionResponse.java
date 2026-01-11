package restrepo.response;

/**
 * Record representing exception response.
 */
public record ExceptionResponse(

    int status,

    String message
) { }
