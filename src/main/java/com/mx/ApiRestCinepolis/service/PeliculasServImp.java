package com.mx.ApiRestCinepolis.service;

import java.util.ArrayList;
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
	//Realizarlos con metodos que ya existen del dao y Desarrollando la logica
		//Peticion post para buscar por nombre
		//Peticion get pasando la informacion en la url para buscar por genero
		//Peticion post para buscar por: nombre, precio, genero
		//Peticion para eliminar por nombre
	@Transactional
	public Peliculas buscarXnombre(String nombre) {
		List<Peliculas> listaPeliculas = peliculasDao.findAll();
		for(Peliculas p: listaPeliculas) {
			if(p.getNombre().equals(nombre)) {
				return p;
			}	
				
		}return null;

	}
	
	@Transactional
	public Peliculas buscarXgenero(String genero) {
		List<Peliculas> listaPeliculas = peliculasDao.findAll();
		for(Peliculas p: listaPeliculas) {
			if(p.getGenero().equals(genero)) {
				return p;
			}
		}return null;
	}
	
	@Transactional(readOnly = true)
	public List<Peliculas> buscar(Peliculas pelicula) {
		List<Peliculas> listaPeliculasEnc = new ArrayList<>();
		for(Peliculas p: peliculasDao.findAll()) {
			if(p.getNombre().equals(pelicula.getNombre())) {
				listaPeliculasEnc.add(p);
			}else if(p.getPrecio().equals(pelicula.getPrecio())) {
				listaPeliculasEnc.add(p);
			}else if(p.getGenero().equals(pelicula.getGenero())) {
				listaPeliculasEnc.add(p);
			}
		}return listaPeliculasEnc;
	}
	
	@Transactional
	public boolean eliminarXnombre(String nombre) {
		Peliculas peliculaEnc = buscarXnombre(nombre);
		boolean bandera = false;
		if(peliculaEnc != null) {
			bandera = true;
			eliminar(peliculaEnc.getIdPelicula());
		}return bandera;
				
	}

}
