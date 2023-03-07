package com.example.demo.service;

import com.example.demo.dao.PersonDao;
import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

//Gesamte Logik die die App benötigt
@Service //served as a service
public class PersonService {

    private final PersonDao personDao; //Reference to PersonDao
    @Autowired //injecting into the constructor (into the interface)
    public PersonService(@Qualifier("fakeDao") PersonDao personDao) { //Qualifier hilft bei Unterscheidung der unterschiedlichen Instanzen vom Interface
        this.personDao = personDao;
    }


    public int addPerson(Person person){ //Person mit random ID generieren
        return personDao.insertPerson(person);
    }

    public List<Person> getAllPeople(){
        return personDao.selectAllPeople();
    }
    public Optional<Person> getPersonById(UUID id){
        return personDao.selectPersonById(id);
    }
    public int deletePerson(UUID id){
        return personDao.deletePersonById(id);
    }
    public int updatePerson(UUID id, Person newPerson){
        return personDao.updatePersonById(id, newPerson);
    }
}
