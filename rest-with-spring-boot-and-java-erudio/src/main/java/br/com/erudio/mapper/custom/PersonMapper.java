package br.com.erudio.mapper.custom;

import br.com.erudio.data.dto.V2.PersonDTOV2;
import br.com.erudio.model.Person;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersonMapper {

    public PersonDTOV2 convertEntityToDTO(Person person){
        PersonDTOV2 dto = new PersonDTOV2();

        dto.setFirstName(person.getFirstName());
        dto.setLastName(person.getLastName());
        dto.setBirthday(new Date());
        dto.setAddress(person.getAddress());
        dto.setGender(person.getGender());
        return dto;
    }

    public Person convertDTOToEntity(PersonDTOV2 person){
        Person entity = new Person();

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setBirthday(new Date());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        return entity;
    }

}
