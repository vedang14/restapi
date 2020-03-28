package com.vedangkaiburrproject.restapi.controllers;

import com.vedangkaiburrproject.restapi.models.Servers;
import com.vedangkaiburrproject.restapi.repositories.ServersRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Server Object Not Found")
class NotFoundException extends Exception {

    NotFoundException() {
    }
}
@RestController
@RequestMapping(value = "/server")
public class ServersController {
    @Autowired
    private ServersRepository repository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Servers> getAllServers() {
            return repository.findAll();
    }

    @RequestMapping(value = "/byId", method = RequestMethod.GET)
    public ResponseEntity getServerById(@RequestParam("id") String id)throws NotFoundException {
        try {
            return new ResponseEntity(repository.findById(id).get(),HttpStatus.OK);
        }
       catch (RuntimeException e){
            throw new NotFoundException();
        }
    }

   @RequestMapping(value = "/byName", method = RequestMethod.GET)
    public ResponseEntity getByName(@RequestParam("name") String name)throws NotFoundException {
        try {
            return new ResponseEntity(repository.findByName(name),HttpStatus.OK);
        }
        catch(RuntimeException e){
            throw new NotFoundException();
        }
    }


    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public Servers updateServerById(@RequestParam("id") ObjectId id, @Valid @RequestBody Servers server) {
        server.setId(id.toString());
        repository.save(server);
        return server;
    }


    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Servers addServerCar(@Valid @RequestBody Servers server) {
        server.setId(ObjectId.get().toString());
        repository.save(server);
        return server;
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    public ResponseEntity deleteServerByID(@RequestParam("id") String id) {
        repository.delete(repository.findById(id).get());
        return new ResponseEntity("Student deleted successfully",HttpStatus.OK);
    }
}
