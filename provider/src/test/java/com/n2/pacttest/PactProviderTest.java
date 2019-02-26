package com.n2.pacttest;

import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.loader.PactBroker;
import au.com.dius.pact.provider.junit.target.TestTarget;
import au.com.dius.pact.provider.spring.SpringRestPactRunner;
import au.com.dius.pact.provider.spring.target.MockMvcTarget;
import com.n2.controller.PersonController;
import com.n2.model.CreatePersonResponseDto;
import java.util.UUID;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;

@RunWith(SpringRestPactRunner.class)
@Provider("provider")
@PactBroker(host = "${pactbroker.host}",
    protocol = "${pactbroker.protocol}",
    port = "${pactbroker.port}"
)
@SpringBootTest(classes = PersonController.class)
@PropertySource("classpath:application.yml")
public class PactProviderTest {


  @Autowired
  private PersonController personController;

  @TestTarget
  public final MockMvcTarget target = new MockMvcTarget();

  @Before
  public void setUp() {

    CreatePersonResponseDto createPersonResponseDto = CreatePersonResponseDto
        .builder()
        .personId(UUID.randomUUID())
        .build();

    target.setControllers(personController);
  }

}

