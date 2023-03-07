package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

//Klasse die PersonDao Method implements
@Repository("fakeDao") //instantiate as a Bean (Served as a Repository)
public class FakePersonDataAccessService implements PersonDao{

    private static List<Person> DB = new ArrayList<>();
    @Override
    public int insertPerson(UUID id, Person person) { //Person der Datenbank hinzufügen
        DB.add(new Person(id, person.getName()));
        return 1;
    }

    @Override
    public List<Person> selectAllPeople(){
        return DB;
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        return DB.stream()
                .filter(person -> person.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deletePersonById(UUID id) {
        Optional<Person> personMaybe = selectPersonById(id);
        if(personMaybe.isEmpty()){
            return 0;
        }
        DB.remove(personMaybe.get());
        return 1;
    }

    @Override
    public int updatePersonById(UUID id, Person update) {
        return selectPersonById(id)
                .map(person ->{ //p ist person
                    int indexOfPersonToUpdate = DB.indexOf(person);
                    if (indexOfPersonToUpdate>= 0) {//if true, Person gefunden
                        DB.set(indexOfPersonToUpdate, new Person(id, update.getName())); //setze Inhalt der Person zu Inhalt von gerade erhaltener Person
                                return 1; //alles gut
                    }
                    return 0;//krise
                })
                .orElse(0); //keine ID da

    }
}
