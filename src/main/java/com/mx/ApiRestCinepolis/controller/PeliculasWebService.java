package com.mx.ApiRestCinepolis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.ApiRestCinepolis.model.Peliculas;
import com.mx.ApiRestCinepolis.service.PeliculasServImp;

@RestController
@RequestMapping(path = "PeliculasWebService")
@CrossOrigin
public class PeliculasWebService {
	
	
	@Autowired
	PeliculasServImp peliculasServImp;
	//http://localhost:9000/PeliculasWebService/listar
	@GetMapping(path = "listar")
		public List<Peliculas> listar(){
			return peliculasServImp.mostrar();		
	}
	//http://localhost:9000/PeliculasWebService/guardar
	@PostMapping(path = "guardar")
	public ResponseEntity<?> guardar(@RequestBody Peliculas pelicula){
		String response = peliculasServImp.guardar(pelicula);
		
		if(response.equals("nombreExistente")) {
			return new ResponseEntity<>("Ese nombre ya existe", HttpStatus.OK);
		}else
			return new ResponseEntity<>(pelicula, HttpStatus.CREATED);
			
	}
	//http://localhost:9000/PeliculasWebService/buscarXid
	@PostMapping(path = "buscarXid")
	public Peliculas buscarXid(@RequestBody Peliculas pelicula) {
		return peliculasServImp.buscarXid(pelicula.getIdPelicula());
	}
	//http://localhost:9000/PeliculasWebService/editar
	@PutMapping(path = "editar")
	public ResponseEntity<?> editar(@RequestBody Peliculas pelicula){
		boolean response = peliculasServImp.editar(pelicula);
		if(response == false)
		    return new ResponseEntity<>("Ese registro ya existe", HttpStatus.OK);
		else
			return new ResponseEntity<>(pelicula, HttpStatus.CREATED);
	}
	//http://localhost:9000/PeliculasWebService/eliminar
	@PostMapping(path = "eliminar")
	public ResponseEntity<String> eliminar(@RequestBody Peliculas pelicula){
		boolean response = peliculasServImp.eliminar(pelicula.getIdPelicula());
		
		if(response == false)
			return new ResponseEntity<>("Ese regitro no existe", HttpStatus.OK);
		else
			return new ResponseEntity<>("Se elimino", HttpStatus.OK);
	}
	//http://localhost:9000/PeliculasWebService/buscarXnombre
    @PostMapping(path = "buscarXnombre")
    public Peliculas buscarXnombre(@RequestBody Peliculas pelicula) {
    	if(pelicula.getNombre().equals(null)) {
    		System.out.println("Ese registro no existe");
    	}
    	return peliculasServImp.buscarXnombre(pelicula.getNombre());
    }
    //http://localhost:9000/PeliculasWebService/burcarXgenero
    @GetMapping(path = "burcarXgenero")
    public Peliculas buscarXgenero(@RequestBody Peliculas pelicula) {
    	return peliculasServImp.buscarXgenero(pelicula.getGenero());
    }
    //http://localhost:9000/PeliculasWebService/buscar
    @PostMapping(path = "buscar")
    public List<Peliculas> buscar(@RequestBody Peliculas pelicula) {
    	return peliculasServImp.buscar(pelicula);
    }
  //http://localhost:9000/PeliculasWebService/eliminarXnombre
    @DeleteMapping (path = "eliminarXnombre")
    public ResponseEntity<String> eliminarXnombre(@RequestBody Peliculas pelicula){
    	boolean response = peliculasServImp.eliminarXnombre(pelicula.getNombre());
    	if (response) {
    		return new ResponseEntity<>("Pelicula eliminada", HttpStatus.OK);
    	}else {
    		return new ResponseEntity<>("No existe registro", HttpStatus.BAD_REQUEST);
    	}
    }

}
