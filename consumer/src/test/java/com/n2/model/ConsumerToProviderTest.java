package com.n2.model;

import static io.pactfoundation.consumer.dsl.LambdaDsl.newJsonBody;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;
import com.google.common.collect.ImmutableMap;
import java.util.Map;
import java.util.UUID;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class ConsumerToProviderTest {

  private static final String ID_KEY = "id";
  private static final String NAME_KEY = "name";
  private static final String ADDRESS_KEY = "address";
  private static final String ID_VALUE = UUID.randomUUID().toString();
  private static final String NAME_VALUE = "Rajesh";
  private static final String ADDRESS_VALUE = "London";
  private static final Map<String, String> HEADERS =
      ImmutableMap.of(CONTENT_TYPE, APPLICATION_JSON_VALUE);
  private static final String CONSUMER = "consumer";
  private static final String PROVIDER = "provider";
  private static final String PROVIDER_HOST = "localhost";
  private static final int PROVIDER_PORT = 29014;
  private static final String CONSUMER_URI = "/consumer/v1/people/addPerson";

  private final RestTemplate restTemplate = new RestTemplate();

  @Rule
  public PactProviderRuleMk2 mockProvider =
      new PactProviderRuleMk2(PROVIDER, PROVIDER_HOST, PROVIDER_PORT, this);

  @Pact(consumer = CONSUMER, provider = PROVIDER)
  public RequestResponsePact createPersonPact(PactDslWithProvider builder) {

    return builder
        .uponReceiving("POST request with the person details")
        .path(CONSUMER_URI)
        .method(HttpMethod.POST.toString())
        .headers(HEADERS)
        .body(
            newJsonBody(
                    o -> {
                      o.uuid(ID_KEY);
                      o.stringType(NAME_KEY);
                      o.stringType(ADDRESS_KEY);
                    })
                .build())
        .willRespondWith()
        .status(HttpStatus.CREATED.value())
        .toPact();
  }

  @Test
  @PactVerification(PROVIDER)
  public void createPerson() {

    final HttpEntity<PersonDTO> requestEntity =
        new HttpEntity<>(createPersonDTO());

    ResponseEntity<String> responseEntity =
        restTemplate.postForEntity(mockProvider.getUrl() + CONSUMER_URI, requestEntity, String.class);

    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
  }

  private PersonDTO createPersonDTO() {
    return new PersonDTO(UUID.fromString(ID_VALUE), NAME_VALUE, ADDRESS_VALUE);
  }
}
