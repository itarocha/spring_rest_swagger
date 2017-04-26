package com.example.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity(name="tb_pet")
public class Pet {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Size(max = 64, message="Name deve ter deve ter no máximo 64 caracteres")
	private String nome;

	@Size(max = 32, message="Tag deve ter deve ter no máximo 32 caracteres")
	private String tag;

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

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	@Override
    public String toString() {
        return String.format("Pet[id=%d, name='%s', tag='%s']",id, nome, tag);
    }	

}
