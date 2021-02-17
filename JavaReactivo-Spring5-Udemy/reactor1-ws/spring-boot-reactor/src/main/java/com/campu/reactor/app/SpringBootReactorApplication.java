package com.campu.reactor.app;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import reactor.core.publisher.Flux;

@SpringBootApplication
public class SpringBootReactorApplication implements CommandLineRunner {
	
	private static final Logger logger = LoggerFactory.getLogger(SpringBootReactorApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringBootReactorApplication.class, args);
	}

	//Implementa la interfaz para que sea un proyecto de consola
	@Override
	public void run(String... args) throws Exception {
		//Creando el primer flujo
		//Flux es un publisher u observable
		//doOnNext -> Tarea que se ejecuta cada que se emite un elemento en el flujo
		
		Flux<String> nombres = Flux.just("Daniel","Marvin","Yaneth","Camila", "Bruce Lee", "Bruce Willis")
				.map(nombre -> {return nombre.toUpperCase();}) //Podria mapear a un objeto tipo usuario por ej con ese nombre
				.filter(nombre -> nombre.contains("BRUCE"))
				.doOnNext(elemento -> System.out.println(elemento));
		
		//Nos suscribimos al observable
		nombres.subscribe();
		
		
		//A partir de una lista crear un flux
		List<String> listUsuarios = new ArrayList<>();
		listUsuarios.add("Lista Daniel");
		listUsuarios.add("Lista Marvin");
		listUsuarios.add("Lista Camila");
		listUsuarios.add("Lista Yaneth");
		
		Flux<String> nombreFluxFromList = Flux.fromIterable(listUsuarios);
		
		//*****************************************
		//Otro ejemplo de Baeldung
		List<Integer> elements = new ArrayList<>();

		Flux.just(1, 2, 3, 4)
		  .subscribe(number -> elements.add(number));

		System.out.println(elements);
		
	}

}
