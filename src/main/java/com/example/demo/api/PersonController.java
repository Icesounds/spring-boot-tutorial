package com.example.demo.api;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

//API / Controller Layer = Requests to backend
@RestController
@RequestMapping("api/v1/person")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping //Post = Add to Server
    public void addPerson(@RequestBody Person person){ //Requestbody wird in in die Person geschrieben
        personService.addPerson(person);
    }

    @GetMapping //von Datenbank abrufen
    public List<Person> getAllPeople(){
        return personService.getAllPeople();
    }

    @GetMapping(path="{id}")
    public Person getPersonById(@PathVariable("id") UUID id){
        return personService.getPersonById(id)
                .orElse(null); //falls kein Benutzer gefunden, dann return null
    }

    @DeleteMapping(path ="{id}")
    public void deletePerson(@PathVariable("id") UUID id){
        personService.deletePerson(id);
    }

    @PutMapping(path ="{id}")
    public void updatePerson(@PathVariable("id")UUID id, @RequestBody Person personToUpdate){
        personService.updatePerson(id, personToUpdate);
    }
}
