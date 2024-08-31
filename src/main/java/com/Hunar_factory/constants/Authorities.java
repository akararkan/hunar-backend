package com.Hunar_factory.constants;

import java.util.List;

public class Authorities {
    public static final List<String> USER_AUTH = List.of("USER:READ");
    public static final List<String> WORKER_AUTH = List.of("WORKER:READ , WORKER:WRITE");
    public static final List<String> PALLET_WORKER_AUTH = List.of("PALLET_WORKER:READ, PALLET_WORKER:WRITE");
    public static final List<String> ADMIN_AUTH = List.of("ADMIN:READ , ADMIN:WRITE" , "ADMIN:UPDATE");
    public static final List<String> MANAGER_AUTH = List.of("MANAGER:READ , MANAGER:WRITE" , "MANAGER:UPDATE" , "MANAGER:DELETE");
}
