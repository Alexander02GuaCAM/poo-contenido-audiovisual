/**
 * Class SerieDeTV
 */
package uni1a;

import java.util.ArrayList;
import java.util.List;

// Subclase SerieDeTV que extiende de ContenidoAudiovisual
public class SerieDeTV extends ContenidoAudiovisual {
    private List<Temporada> temporadas = new ArrayList<>(); // Composición con Temporada

    public SerieDeTV(String titulo, int duracionEnMinutos, String genero) {
        super(titulo, duracionEnMinutos, genero);
    }

    // Composición: la serie CREA sus propias temporadas
    public void agregarTemporada(int numero, int numeroEpisodios, int anioEstreno) {
        temporadas.add(new Temporada(numero, numeroEpisodios, anioEstreno));
    }

    public List<Temporada> getTemporadas() {
        return temporadas;
    }

    public int getNumeroTemporadas() {
        return temporadas.size();
    }

    public void mostrarTemporadas() {
        System.out.println("Temporadas:");
        if (temporadas.isEmpty()) {
            System.out.println(" - Sin temporadas registradas");
        } else {
            for (Temporada t : temporadas) {
                System.out.println(" - " + t);
            }
        }
    }

    @Override
    public void mostrarDetalles() {
        System.out.println("Detalles de la serie:");
        System.out.println("ID: " + getId());
        System.out.println("Título: " + getTitulo());
        System.out.println("Duración en minutos: " + getDuracionEnMinutos());
        System.out.println("Género: " + getGenero());
        System.out.println("Número de temporadas: " + getNumeroTemporadas());
        mostrarTemporadas();
        System.out.println();
    }
}