package com.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
