/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirest;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/productos")

public class productoController {

    private final ProductoService productoService;

    @GetMapping("listado")
    public Iterable getProductos() {
        return productoService.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Producto> productoSaveById(@PathVariable Long id) {
        try {
            Optional<Producto> producto = productoService.productoSaveById(id);
            return producto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("crear")
    public ResponseEntity<String> guardarProducto(@RequestBody Producto producto) {
        try {
            if (producto == null) {
                return ResponseEntity.badRequest().body("datos incorrectos");
            } else {
                return ResponseEntity.ok("producto guardado exisotasamente");
            }

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error interno");
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Optional<Producto>> porductoSaveById(@PathVariable Long id, @RequestBody Producto producto) {
        try {
            Optional<Producto> productoActualizado = productoService.productoUpdate(id, producto);
            return ResponseEntity.ok(productoActualizado);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteProducto(@PathVariable Long id){
        try{
            productoService.deleteProducto(id);
            return ResponseEntity.ok("Producto eliminado correctamente");

        }catch (NoSuchElementException e ){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("producto no encontrado");

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno");
        }
    }


}
