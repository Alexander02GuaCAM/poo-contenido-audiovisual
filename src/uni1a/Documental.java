/**
 * Class Documental
 */
package uni1a;

import java.util.ArrayList;
import java.util.List;

// Subclase Documental que extiende de ContenidoAudiovisual
public class Documental extends ContenidoAudiovisual {
    private String tema;
    private List<Investigador> investigadores = new ArrayList<>(); // Asociación con Investigador

    public Documental(String titulo, int duracionEnMinutos, String genero, String tema) {
        super(titulo, duracionEnMinutos, genero);
        this.tema = tema;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    // Asociación: el Investigador se crea afuera y se vincula al documental
    public void asociarInvestigador(Investigador investigador) {
        investigadores.add(investigador);
    }

    public List<Investigador> getInvestigadores() {
        return investigadores;
    }

    public void mostrarInvestigadores() {
        System.out.println("Investigadores:");
        if (investigadores.isEmpty()) {
            System.out.println(" - Sin investigadores registrados");
        } else {
            for (Investigador i : investigadores) {
                System.out.println(" - " + i);
            }
        }
    }

    @Override
    public void mostrarDetalles() {
        System.out.println("Detalles del documental:");
        System.out.println("ID: " + getId());
        System.out.println("Título: " + getTitulo());
        System.out.println("Duración en minutos: " + getDuracionEnMinutos());
        System.out.println("Género: " + getGenero());
        System.out.println("Tema: " + tema);
        mostrarInvestigadores();
        System.out.println();
    }
}