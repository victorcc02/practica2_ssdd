package ssdd.ArandaLeonGerardo_1.entities;

public class Nutrition {
    private Long id;
    private String nombre;
    private String comida;
    private String tipo;

    public Nutrition() {
    }

    public Nutrition(String nombre, String comida, String tipo) {
        this.nombre = nombre;
        this.comida = comida;
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getComida() {
        return comida;
    }

    public void setComida(String comida) {
        this.comida = comida;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
