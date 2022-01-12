package com.jetpackcompose.core.model

enum class NetworkStatus(val code: Int, val message: String) {
    Unknown(0, "Unknown error."),
    NetworkError(1, "Check your internet connection."),
    ResponseBodyIsNull(2, "The response body is null."),

    Continue(100, ""),
    SwitchingProtocols(101, ""),
    Processing(102, ""),
    EarlyHints(103, ""),

    OK(200, ""),
    Created(201, ""),
    Accepted(202, ""),
    NonAuthoritative(203, ""),
    NoContent(204, ""),
    ResetContent(205, ""),
    PartialContent(206, ""),
    MultiStatus(207, ""),
    AlreadyReported(208, ""),
    IMUsed(209, ""),

    MultipleChoices(300, ""),
    MovePermanently(301, ""),
    Found(302, ""),
    SeeOther(303, ""),
    NotModified(304, ""),
    UseProxy(305, ""),
    SwitchProxy(306, ""),
    TemporaryRedirect(307, ""),
    PermanentRedirect(308, ""),

    BadRequest(400, "Bad Request."),
    Unauthorized(401, "You must login to your account."),
    PaymentRequired(402, ""),
    Forbidden(403, "Forbidden."),
    NotFound(404, "Not Found."),
    MethodNotAllowed(405, ""),
    NotAcceptable(406, ""),
    ProxyAuthenticationRequired(407, ""),
    RequestTimeout(408, ""),
    Conflict(409, ""),
    Gone(410, ""),
    LengthRequired(411, ""),
    PreconditionFailed(412, ""),
    PayloadTooLarge(413, ""),
    URITooLong(414, ""),
    UnsupportedMediaType(415, ""),
    RangeNotSatisfiable(416, ""),
    ExpectationFailed(417, ""),
    IMATeapot(418, ""),
    MisdirectedRequest(421, ""),
    UnProcessableEntity(422, ""),
    Locked(423, ""),
    FailedDependency(424, ""),
    TooEarly(425, ""),
    UpgradeRequired(426, ""),
    PreconditionRequired(428, ""),
    TooManyRequests(429, "Too Many Requests."),
    RequestHeaderFieldsTooLarge(431, ""),
    UnavailableForLegalReasons(451, ""),

    InternalServerError(500, "Internal server error try later."),
    NotImplemented(501,""),
    BadGateway(502,""),
    ServiceUnavailable(503,"Service Unavailable."),
    GatewayTimeout(504,""),
    HTTPVersionNotSupported(505,""),
    NotExtended(510,""),
    NetworkAuthenticationRequired(511,"You must login again.");

    companion object {
        fun fromStatusCode(value: Int): NetworkStatus {
            return values().firstOrNull { it.code == value } ?: Unknown
        }
    }
}