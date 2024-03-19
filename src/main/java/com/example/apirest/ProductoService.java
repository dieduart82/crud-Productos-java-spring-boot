/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirest;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;


@Service
@AllArgsConstructor

public class ProductoService {

    private final ProductoRepository productoRepository;

    public Iterable<Producto> getAll() {

        return productoRepository.findAll();
    }

    public Optional<Producto> productoSaveById(Long id) {
        return productoRepository.findById(id);
    }

    public void productoSave(Producto producto) {
        productoRepository.save(producto);
    }

    public Optional<Producto> productoUpdate(Long id, Producto producto) {
        Optional<Producto> existeProducto = productoRepository.findById(id);
        if (existeProducto.isPresent()) {
            Producto productoExistente = existeProducto.get();

            productoExistente.setNombre(producto.getNombre());
            productoExistente.setPrecio(producto.getPrecio());
            productoExistente.setEmail(producto.getEmail());

            return Optional.of(productoRepository.save(productoExistente));
        } else {
            throw new NoSuchElementException("Producto no encontrado");
        }
    }

    public void deleteProducto(Long id) {
        Optional<Producto> producto = productoRepository.findById(id);
        if (producto.isPresent()) {
            productoRepository.deleteById(id);
        } else {
            throw new NoSuchElementException("Producto no encotrado");
        }
    }

}
