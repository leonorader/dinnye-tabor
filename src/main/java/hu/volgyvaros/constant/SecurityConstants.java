package hu.volgyvaros.constant;

public final class SecurityConstants {

    public static final String AUTH_LOGIN_URL = "/api/authenticate";

    // Signing key for HS512 algorithm
    public static final String JWT_SECRET = "n2r5u8w/A%R*G-KjPdMgVkYf3u6v9S$B&E(H+MbQeTtWmZq4t7w!p%C*F-J@NcRg";

    // JWT token defaults
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_TYPE = "JWT";
    public static final String TOKEN_ISSUER = "secure-volgyvaros-api";
    public static final String TOKEN_AUDIENCE = "secure-volgyvaros-app";

    public static final String TOKEN_USERNAME = "username";
    public static final String TOKEN_ROLE = "role";
    public static final String TOKEN_NAME = "name";

    private SecurityConstants() { }
}