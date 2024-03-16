package ssdd.ArandaLeonGerardo_1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ssdd.ArandaLeonGerardo_1.entities.Nutricion;
import ssdd.ArandaLeonGerardo_1.service.NutricionService;

import java.util.Collection;

@Controller
@RequestMapping("/api/nutricion")
public class NutricionREStControlador {

    @Autowired
    private NutricionService nutricionService;
    @PostMapping
    public ResponseEntity<Nutricion> crearNutricion(@RequestBody Nutricion nutricion) {
        return ResponseEntity.status(201).body(nutricionService.crearNutricion(nutricion));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Nutricion> obtenerNutricion(@PathVariable Long id) {
        Nutricion nutricion = nutricionService.obtenerNutricion(id);
        if (nutricion == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(nutricion);
    }

    @GetMapping
    public ResponseEntity<Collection<Nutricion>> obtenerTodasLasNutricion() {
        return ResponseEntity.ok(nutricionService.obtenerTodasLasNutricion());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Nutricion> actualizarNutricion(@PathVariable Long id, @RequestBody Nutricion nutricion) {
        Nutricion actualizada = nutricionService.actualizarNutricion(id, nutricion);
        if (actualizada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarNutricion(@PathVariable Long id) {
        nutricionService.eliminarNutricion(id);
        return ResponseEntity.ok().build();
    }
}
