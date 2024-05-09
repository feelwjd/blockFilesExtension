package com.example.blockfilesextension.util;

public class CodeEx {
    /******************************
     * 공통 사용 코드
     ******************************/

    /* Success status codes */
    public static final Integer OK = 200;
    public static final Integer CREATED = 201;
    public static final Integer ACCEPTED = 202;
    public static final Integer NON_AUTHORITATIVE_INFORMATION = 203;
    public static final Integer NO_CONTENT = 204;

    /* Redirection status codes */
    public static final Integer MULTIPLE_CHOICES = 300;
    public static final Integer MOVED_PERMANENTLY = 301;
    public static final Integer FOUND = 302;
    public static final Integer SEE_OTHER = 303;
    public static final Integer NOT_MODIFIED = 304;

    /* Client error status codes */
    public static final Integer BAD_REQUEST = 400;
    public static final Integer UNAUTHORIZED = 401;
    public static final Integer PAYMENT_REQUIRED = 402;
    public static final Integer FORBIDDEN = 403;
    public static final Integer NOT_FOUND = 404;
    public static final Integer METHOD_NOT_ALLOWED = 405;
    public static final Integer NOT_ACCEPTABLE = 406;
    public static final Integer PROXY_AUTHENTICATION_REQUIRED = 407;
    public static final Integer REQUEST_TIMEOUT = 408;

    public static final Integer ERROR = 500;

    /* DB error status codes */
    public static final Integer DB_UNKNOWN_ERROR = 9000;
    public static final Integer DB_CONNECTION_FAILURE = 9001;
    public static final Integer DB_SQL_SYNTAX_ERROR = 9002;
    public static final Integer DB_INVALID_DATA_ERROR = 9003;
    public static final Integer DB_DUPLICATE_KEY_ERROR = 9004;
    public static final Integer DB_CONSTRAINT_VIOLATION_ERROR = 9005;
    public static final Integer DB_INSERT_ERROR = 9006;
    public static final Integer DB_DELETE_ERROR = 9007;
    public static final Integer DB_UPDATE_ERROR = 9008;
    public static final Integer DB_NULL_ERROR = 9009;

    /* File upload error codes */
    public static final Integer FILE_UPLOAD_ERROR_START = 8000;
    public static final Integer FILE_NOT_FOUND = 8001;
    public static final Integer FILE_UPLOAD_SIZE_EXCEEDED = 8002;
    public static final Integer FILE_UPLOAD_FORMAT_NOT_SUPPORTED = 8003;
    public static final Integer FILE_UPLOAD_PARTIAL_ERROR = 8004;
}
