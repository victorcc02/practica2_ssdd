package ssdd.ArandaLeonGerardo_1.service;

import org.springframework.stereotype.Service;
import ssdd.ArandaLeonGerardo_1.entities.Nutricion;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class NutricionService {
    private final Map<Long, Nutricion> mapaNutricion = new HashMap<>();
    private final AtomicLong nextId = new AtomicLong();


    public Nutricion crearNutricion(Nutricion nutricion) {
        long id = nextId.incrementAndGet();
        nutricion.setId(id);
        mapaNutricion.put(id, nutricion);
        return nutricion;
    }

    public Nutricion obtenerNutricion(Long id) {
        return mapaNutricion.get(id);
    }

    public Collection<Nutricion> obtenerTodasLasNutricion() {
        return mapaNutricion.values();
    }

    public Nutricion actualizarNutricion(Long id, Nutricion nutricion) {
        if (!mapaNutricion.containsKey(id)) {
            return null;
        }
        nutricion.setId(id);
        mapaNutricion.put(id, nutricion);
        return nutricion;
    }

    public void eliminarNutricion(Long id) {
        mapaNutricion.remove(id);
    }
}
