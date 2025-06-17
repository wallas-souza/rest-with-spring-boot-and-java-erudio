package br.com.erudio.service;

import br.com.erudio.data.dto.V1.PersonDTO;
import br.com.erudio.data.dto.V2.PersonDTOV2;
import br.com.erudio.exception.ResourceNotFoundException;
import static br.com.erudio.mapper.ObjectMapper.parseListObjects;
import static br.com.erudio.mapper.ObjectMapper.parseObject;

import br.com.erudio.mapper.custom.PersonMapper;
import br.com.erudio.model.Person;
import br.com.erudio.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonService {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonService.class.getName());

    @Autowired
    PersonRepository repository;

    @Autowired
    PersonMapper mapper;

    public List<PersonDTO> findAll(){
        return parseListObjects(repository.findAll(), PersonDTO.class);
    }

    public PersonDTO findById(long id){
        logger.info("Finding one Person!");
        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found."));

        return parseObject(entity, PersonDTO.class);
    }

    public PersonDTO create(PersonDTO person){
        var entity = parseObject(person, PersonDTO.class);

        return parseObject(entity, PersonDTO.class);
    }

    public PersonDTOV2 createV2(PersonDTOV2 person){
        var entity = mapper.convertDTOToEntity(person);

        return mapper.convertEntityToDTO(repository.save(entity));
    }

    public void delete(long id){
        Person entity = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No records found."));

        repository.delete(entity);
    }

    public PersonDTO update(PersonDTO person){
        Person entity = repository.findById(person.getId()).orElseThrow(()-> new ResourceNotFoundException("No records found."));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return parseObject(entity, PersonDTO.class);
    }

}
