package uni1a;

import java.util.ArrayList;
import java.util.List;

// Subclase Anime que extiende de ContenidoAudiovisual
public class Anime extends ContenidoAudiovisual {
    private String estudioAnimacion;
    private String idiomaOriginal;
    private List<Temporada> temporadas = new ArrayList<>();  // Composición con Temporada
    private List<Actor> actoresDeVoz = new ArrayList<>();    // Agregación con Actor

    public Anime(String titulo, int duracionEnMinutos, String genero,
                 String estudioAnimacion, String idiomaOriginal) {
        super(titulo, duracionEnMinutos, genero);
        this.estudioAnimacion = estudioAnimacion;
        this.idiomaOriginal = idiomaOriginal;
    }

    public String getEstudioAnimacion() {
        return estudioAnimacion;
    }

    public void setEstudioAnimacion(String estudioAnimacion) {
        this.estudioAnimacion = estudioAnimacion;
    }

    public String getIdiomaOriginal() {
        return idiomaOriginal;
    }

    public void setIdiomaOriginal(String idiomaOriginal) {
        this.idiomaOriginal = idiomaOriginal;
    }

    // Composición: el anime CREA sus propias temporadas
    public void agregarTemporada(int numero, int numeroEpisodios, int anioEstreno) {
        temporadas.add(new Temporada(numero, numeroEpisodios, anioEstreno));
    }

    // Agregación: el actor de voz se crea afuera y se agrega al anime
    public void agregarActorDeVoz(Actor actor) {
        actoresDeVoz.add(actor);
    }

    public List<Temporada> getTemporadas() {
        return temporadas;
    }

    public List<Actor> getActoresDeVoz() {
        return actoresDeVoz;
    }

    public int getNumeroTemporadas() {
        return temporadas.size();
    }

    // Suma los episodios de todas las temporadas
    public int getTotalEpisodios() {
        int total = 0;
        for (Temporada t : temporadas) {
            total += t.getNumeroEpisodios();
        }
        return total;
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

    public void mostrarActoresDeVoz() {
        System.out.println("Actores de voz:");
        if (actoresDeVoz.isEmpty()) {
            System.out.println(" - Sin actores de voz registrados");
        } else {
            for (Actor a : actoresDeVoz) {
                System.out.println(" - " + a);
            }
        }
    }

    @Override
    public void mostrarDetalles() {
        System.out.println("Detalles del anime:");
        System.out.println("ID: " + getId());
        System.out.println("Título: " + getTitulo());
        System.out.println("Duración por episodio (min): " + getDuracionEnMinutos());
        System.out.println("Género: " + getGenero());
        System.out.println("Estudio de animación: " + estudioAnimacion);
        System.out.println("Idioma original: " + idiomaOriginal);
        System.out.println("Número de temporadas: " + getNumeroTemporadas());
        System.out.println("Total de episodios: " + getTotalEpisodios());
        mostrarTemporadas();
        mostrarActoresDeVoz();
        System.out.println();
    }
}