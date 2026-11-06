package com.weber.security;

import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;

public class SecurityConfig {

    public static void applyCors(HttpExchange exchange) throws IOException {
        String origin = exchange.getRequestHeaders().getFirst("Origin");

        if (origin != null && (origin.equals("http://localhost:5500") || origin.equals("https://tweber92.github.io"))) {
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", origin);
            exchange.getResponseHeaders().add("Access-Control-Allow-Credentials", "true");
            exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type, Authorization");
        }

        // Handle the preflight request immediately
        if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
            exchange.sendResponseHeaders(204, -1);
            exchange.close(); // Safely close the exchange here
        }
    }
}