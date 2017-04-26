package com.example.util;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

public class RestClient {
	
	public RestClient(){
		
	}
	
	
	public String getPessoa(){
		RestTemplate template = new RestTemplate();
		String uri = "http://https://randomuser.me/api";
		String resultado = template.getForObject(uri, String.class);
		return resultado;
		
		/*
		if ((resultado != null) && (resultado.results.length > 0)){
			Geometry g = resultado.results[0].geometry;
			lat = g.getLocation().getLat();
			lng = g.getLocation().getLng();
			System.out.println(g);
			pessoa.setLatitude(lat);
			pessoa.setLongitude(lng);
		}
		*/
	}
	
}
