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

import com.example.model.Pessoa;
import com.example.repo.PessoaRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController()
public class PessoasController {

	@Autowired
	private PessoaRepository pessoas;
	
	@ApiOperation(value = "Retorna as pessoas")
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
	@RequestMapping(value="/api/pessoas", method = RequestMethod.GET, produces="application/json")
	public Page<Pessoa> list(Pageable pageable){
		//PageRequest p = new PageRequest(pageable.getPageNumber(), 5);
		Page<Pessoa> lista = pessoas.findAll(pageable);
		//PageWrapper<Igreja> page = new PageWrapper<Igreja>(lista, "/blog");
		return lista;
	}
	
	@RequestMapping(value="/api/pessoas", method=RequestMethod.POST)
	public ResponseEntity<?> save(@RequestBody Pessoa pessoa){
		pessoas.save(pessoa);
		return new ResponseEntity<Pessoa>(pessoa, HttpStatus.CREATED); 
	}
	
	@RequestMapping(value="/api/pessoas/{id}", method=RequestMethod.PUT)
	public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Pessoa pessoa){
		Pessoa p = pessoas.save(pessoa);
		return new ResponseEntity<Pessoa>(p, HttpStatus.OK); 
	}
	
	@RequestMapping(value="/api/pessoas/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> get(@PathVariable("id") Long id){
		Pessoa pessoa = pessoas.findOne(id);
		if (pessoa != null) {
			return new ResponseEntity<Pessoa>(pessoa, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Não encontrada", HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value="/api/pessoas/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable("id") Long id){
		try {
			pessoas.delete(id);
			return new ResponseEntity<String>("Pessoa excluída com sucesso", HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
