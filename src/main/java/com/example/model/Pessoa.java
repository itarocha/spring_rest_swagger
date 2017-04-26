package com.example.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity(name="pessoa")
public class Pessoa {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message="Nome é obrigatório")
	@Size(max = 32, message="Nome deve ter deve ter no máximo 32 caracteres")
	private String nome;

	@NotEmpty(message="Sobrenome é obrigatório")
	@Size(max = 32, message="Nome deve ter deve ter no máximo 32 caracteres")
	private String sobrenome;
	
	@Size(max = 64, message="Endereço deve ter deve ter no máximo 64 caracteres")
	public String endereco;
	
	@Size(max = 32, message="Cidade deve ter deve ter no máximo 32 caracteres")
	public String cidade;
	
	@Size(max = 32, message="Estado deve ter deve ter no máximo 32 caracteres")
	public String estado;
	
	@Size(max = 64, message="Email deve ter no máximo 64 caracteres")
	public String email;
	
	public Integer cep;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCidade() {
		return cidade;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getCep() {
		return cep;
	}

	public void setCep(Integer cep) {
		this.cep = cep;
	}

}
