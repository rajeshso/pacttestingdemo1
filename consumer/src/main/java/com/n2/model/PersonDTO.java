package com.n2.model;

import java.util.UUID;
import lombok.Data;

@Data
public class PersonDTO {

  private final UUID id;
  private final String name;
  private final String address;

}
