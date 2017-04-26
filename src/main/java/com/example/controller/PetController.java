package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Pet;
import com.example.repo.PetRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController()
@Api(value = "pets_new")
public class PetController {
 
	@Autowired
	private PetRepository pets;
	
	@ApiOperation(value = "Retorna os pets")
	@ApiImplicitParams( {
		@ApiImplicitParam(name = "page", dataType = "long", required = false, paramType = "query", 
	            value = "Results page you want to retrieve (0..N)"),
	    @ApiImplicitParam(name = "size", dataType = "long", paramType = "query",
	            value = "Number of records per page."),
	    @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
	            value = "Sorting criteria in the format: property(,asc|desc). " +
	                    "Default sort order is ascending. " +
	                    "Multiple sort criteria are supported.")
	})	
	@RequestMapping(value="api/pets", produces="application/json", method=RequestMethod.GET)
	public Page<Pet> list(Pageable pageable){
		//PageRequest p = new PageRequest(pageable.getPageNumber(), 5);
		Page<Pet> lista = pets.findAll(pageable);
		//PageWrapper<Igreja> page = new PageWrapper<Igreja>(lista, "/blog");
		return lista;
	}
	
	@ApiOperation(value = "Grava o Pet")
	@RequestMapping(value="api/pets", method=RequestMethod.POST)
	public ResponseEntity<?> save(@RequestBody Pet pet){
		pets.save(pet);
		return new ResponseEntity<Pet>(pet, HttpStatus.CREATED); 
	}
	
	@ApiOperation(value = "Atualiza um pet")
	@RequestMapping(value="api/pets/{id}", method=RequestMethod.PUT)
	public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Pet pet){
		System.out.println(pet.getNome());
		Pet p = pets.save(pet);
		return new ResponseEntity<Pet>(p, HttpStatus.OK); 
	}
	
	@ApiOperation(value = "Retorna um pet")
	@RequestMapping(value="api/pets/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> get(@PathVariable("id") Long id){
		Pet pet = pets.findOne(id);
		if (pet != null) {
			return new ResponseEntity<Pet>(pet, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Não encontrada", HttpStatus.NOT_FOUND);
		}
	}

	@ApiOperation(value = "Remove um pet")
	@RequestMapping(value="api/pets/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable("id") Long id){
		try {
			pets.delete(id);
			return new ResponseEntity<String>("Pet excluído com sucesso", HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
