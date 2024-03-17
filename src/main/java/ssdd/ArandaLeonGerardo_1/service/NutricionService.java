package ssdd.ArandaLeonGerardo_1.service;

import org.springframework.stereotype.Service;
import ssdd.ArandaLeonGerardo_1.entities.Nutrition;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class NutricionService {
    private final Map<Long, Nutrition> mapaNutricion = new HashMap<>();
    private final AtomicLong nextId = new AtomicLong();


    public Nutrition crearNutricion(Nutrition nutrition) {
        long id = nextId.incrementAndGet();
        nutrition.setId(id);
        mapaNutricion.put(id, nutrition);
        return nutrition;
    }

    public Nutrition obtenerNutricion(Long id) {
        return mapaNutricion.get(id);
    }

    public Collection<Nutrition> obtenerTodasLasNutricion() {
        return mapaNutricion.values();
    }

    public Nutrition actualizarNutricion(Long id, Nutrition nutrition) {
        if (!mapaNutricion.containsKey(id)) {
            return null;
        }
        nutrition.setId(id);
        mapaNutricion.put(id, nutrition);
        return nutrition;
    }

    public void eliminarNutricion(Long id) {
        mapaNutricion.remove(id);
    }
}
