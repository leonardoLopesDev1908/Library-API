package com.jpa.libraryapi.common;

import org.springframework.stereotype.Component;

public class ApiConstants {

    public static final String API_BASE = "/api";
    public static final String API_AUTHOR = API_BASE + "/author";
    public static final String API_BOOK = API_BASE + "/book";
    public static final String API_CURRICULUM = API_AUTHOR + "/curriculum";

}
