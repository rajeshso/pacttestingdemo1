package com.n2.controller;


import com.n2.model.CreatePersonResponseDto;
import com.n2.model.PersonDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consumer")
public class PersonController {


  @ApiOperation(value = "Create a new person", nickname = "createConversation")
  @PostMapping("/v1/people/addPerson")
  @ResponseStatus(HttpStatus.CREATED)
  public CreatePersonResponseDto createPerson(
      @RequestBody  PersonDTO personDTO) {
    System.out.println("personDTO is " + personDTO);

    return new CreatePersonResponseDto(personDTO.getId());
  }

}
