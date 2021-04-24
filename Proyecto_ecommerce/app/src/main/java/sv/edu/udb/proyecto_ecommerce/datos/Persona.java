package sv.edu.udb.proyecto_ecommerce.datos;

public class Persona {
    private String categoria;
    private String descripccion;
    private String precio;
    private String ubicacion;
    private String usuario;
    String key;

    public Persona() {
    }




    public Persona(String categoria, String descripccion, String precio, String ubicacion) {
        this.categoria = categoria;
        this.descripccion = descripccion;
        this.precio = precio;
        this.ubicacion = ubicacion;
        this.usuario=usuario;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescripccion() {
        return descripccion;
    }

    public void setDescripccion(String descripccion) {
        this.descripccion = descripccion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
