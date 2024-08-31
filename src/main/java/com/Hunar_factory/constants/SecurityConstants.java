package com.Hunar_factory.constants;

import lombok.Value;

public class SecurityConstants {

    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String TOKEN_CANNOT_BE_VERIFIED= "Token can not be verified";
    public static final String AKAR_ARKAN = "Akar Arkan - Hunar Factory";
    public static final String AKAR_ARKAN_ADMINISTRATION = "User Management HUNAR FACTORY";
    public static final String  AUTHORITIES = "authorities";
    public static final String FORBIDDEN_MESSAGE = "You need to log in to access this page";
    public static final String ACCESS_DENIED_MESSAGE = "You do not have permission to access this page";
    public static final String OPTIONS_HTTP_METHOD = "OPTIONS";
    public static final String ID_CLAIM = "id";
    public static final String ROLE = "ROLE";
    public static final String[] PUBLIC_URL = { "/images/**","/welcome","/api/v1/user/login","/api/v1/user/register" , "/api/v1/user/verify" , "/api/v1/user/logout" , "/api/v1/pallet/**", "/api/v1/package/**" , "/api/v1/workerProduct/**"};



}
