package com.market.project.persistance.crud;

import com.market.project.persistance.entity.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoCrudRepository extends CrudRepository <Producto, Integer >{
    List<Producto> findByIdCategoriaOrderByNombreAsc(int idCategoria);
    @Query(value = "SELECT * FROM productos WHERE id_categoria = ?", nativeQuery = true)
    List<Producto> getbyIdCategoria(int idCategoria);
    Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int cantidadStock, boolean estado);
}
