package com.market.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <h1>API Market</h1>
 * Api market es una api que expone productos de una base de datos PostgreSQL
 * Permite, agregar y eliminarlos, con la regla de autenticacion.
 * @author anthonypernia
 * @version 1.0
 * @since 2021
 */


@SpringBootApplication
public class MarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarketApplication.class, args);

	}

}
