package com.mx.ApiRestCinepolis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.ApiRestCinepolis.dao.PeliculasDao;
import com.mx.ApiRestCinepolis.model.Peliculas;

@Service
public class PeliculasServImp {
	
	@Autowired
	PeliculasDao peliculasDao;
	//MOSTRAR
	@Transactional(readOnly = true)
	public List<Peliculas> mostrar(){
		List<Peliculas> registrosBd = peliculasDao.findAll();
		return registrosBd;
	}
	//GUARDAR
	@Transactional
	public String guardar(Peliculas pelicula) {
		String respuesta = "guardado";
		boolean bandera = false;
		for(Peliculas p:peliculasDao.findAll()) {
			if(p.getNombre().equals(p.getNombre())) {
				respuesta = "nombreExistente";
				bandera =true;
				break;
				
			}
			
		}
		if(bandera)
			peliculasDao.save(pelicula);
		    return respuesta;
	}
	//BUSCAR
	@Transactional(readOnly = true)
	public Peliculas buscarXid(Integer idPelicula) {
		Peliculas peliculaEncontrada = peliculasDao.findById(idPelicula).orElse(null);
		return peliculaEncontrada;
	}
	//EDITAR
	@Transactional
	public boolean editar(Peliculas pelicula) {
		Peliculas peliculaEncont = peliculasDao.findById(pelicula.getIdPelicula()).orElse(null);
		boolean bandera = false;
		
		if(peliculaEncont != null) {
			peliculasDao.save(pelicula);
			bandera = true;
		}
		return bandera;
	}
	//ELIMINAR
	@Transactional
	public boolean eliminar(Integer idPeli) {
		Peliculas peliculaEliminar = peliculasDao.findById(idPeli).orElse(null);
		boolean bandera = false;
		
		if(peliculaEliminar != null) {
			bandera = true;
			peliculasDao.deleteById(idPeli);
		}
		return bandera;
	}

}
