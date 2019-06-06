package com.example;

import com.example.router.RouteDefinition;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class MainTest {
    @Test
    public void testSimpleRoute() {
        RouteDefinition routeDefinition = new RouteDefinition("GET /todos");

        assertThat(routeDefinition.matches("GET", "/todos")).isEqualTo(true);
        assertThat(routeDefinition.matches("POST", "/todos")).isEqualTo(false);
        assertThat(routeDefinition.matches("GET", "/todos/1")).isEqualTo(false);
    }

    @Test
    public void testRoutesWithParameters() {
        RouteDefinition routeDefinition = new RouteDefinition("POST /todos/:id");

        assertThat(routeDefinition.matches("POST", "/todos/1")).isEqualTo(true);
        assertThat(routeDefinition.matches("POST", "/todos/bar")).isEqualTo(true);
        assertThat(routeDefinition.matches("POST", "/todos/1/foo")).isEqualTo(true);
        assertThat(routeDefinition.matches("POST", "/todos")).isEqualTo(false);
        assertThat(routeDefinition.matches("GET", "/todos/1")).isEqualTo(false);
    }
}
