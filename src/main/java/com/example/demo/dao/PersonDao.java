package com.example.demo.dao;

import com.example.demo.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;



public interface PersonDao { //Interface für Databaseimplementation - contract for anyone wishing to implement Interface

    //zwei Methoden: 1 - Person mit bestimmter ID einfügen; 2 - Person mit random ID einfügen
    int insertPerson(UUID id, Person person); //UUID ist die ID einer Person

    default int insertPerson(Person person) {
        UUID id = UUID.randomUUID();
        return insertPerson(id, person);
    }

    List<Person> selectAllPeople();


    Optional<Person> selectPersonById(UUID id);

    int deletePersonById(UUID id);

    int updatePersonById(UUID id, Person person);
}
