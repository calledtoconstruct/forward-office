package com.example.golink;

import java.net.URI;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.web.client.ResourceAccessException;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class GolinkApplicationTests {

	@Test
	void contextLoads() {
	}

	@LocalServerPort
	private int port;

    @Autowired
	private TestRestTemplate restTemplate;

    @MockBean
    private LookupService lookupService;

	@ParameterizedTest
    @ValueSource(strings = {"calendar", "journal", "mail"})
	public void lookupReturnsRedirect(String shortName) throws Exception {
        Mockito
            .when(lookupService.getLongName(Mockito.eq(shortName)))
            .thenReturn(Optional.of("http://server.that.does.not.exist.com"));

        final var url = String.format(
            "http://localhost:%d/%s",
            port,
            shortName
            );

        final var uri = new URI(url);

        final var requestEntity = new RequestEntity<String>(HttpMethod.GET, uri);

        try {
            restTemplate.exchange(requestEntity, String.class);
            Assertions.assertTrue(false);
        } catch (final ResourceAccessException exception) {
            final var cause = exception.getCause();
            Assertions.assertEquals("server.that.does.not.exist.com", cause.getMessage());
        }
	}
}
