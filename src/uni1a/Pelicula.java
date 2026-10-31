/**
 * Class Pelicula
 */
package uni1a;

import java.util.ArrayList;
import java.util.List;

// Subclase Pelicula que extiende de ContenidoAudiovisual
public class Pelicula extends ContenidoAudiovisual {
    private String estudio;
    private List<Actor> actores = new ArrayList<>(); // Agregación con Actor

    public Pelicula(String titulo, int duracionEnMinutos, String genero, String estudio) {
        super(titulo, duracionEnMinutos, genero);
        this.estudio = estudio;
    }

    public String getEstudio() {
        return estudio;
    }

    public void setEstudio(String estudio) {
        this.estudio = estudio;
    }

    // Agregación: el Actor se crea afuera y se agrega a la película
    public void agregarActor(Actor actor) {
        actores.add(actor);
    }

    public List<Actor> getActores() {
        return actores;
    }

    public void mostrarReparto() {
        System.out.println("Reparto:");
        if (actores.isEmpty()) {
            System.out.println(" - Sin actores registrados");
        } else {
            for (Actor a : actores) {
                System.out.println(" - " + a);
            }
        }
    }

    @Override
    public void mostrarDetalles() {
        System.out.println("Detalles de la película:");
        System.out.println("ID: " + getId());
        System.out.println("Título: " + getTitulo());
        System.out.println("Duración en minutos: " + getDuracionEnMinutos());
        System.out.println("Género: " + getGenero());
        System.out.println("Estudio: " + estudio);
        mostrarReparto();
        System.out.println();
    }
}