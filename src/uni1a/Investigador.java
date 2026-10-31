package uni1a;

public class Investigador {
    private String nombre;
    private String especialidad;
    private String institucion;

    public Investigador(String nombre, String especialidad, String institucion) {
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.institucion = institucion;
    }

    public String getNombre() { return nombre; }
    public String getEspecialidad() { return especialidad; }
    public String getInstitucion() { return institucion; }

    @Override
    public String toString() {
        return nombre + " - " + especialidad + " (" + institucion + ")";
    }
}
