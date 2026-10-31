package poo;
import uni1a.*;

public class PruebaAudioVisual {
    public static void main(String[] args) {
        System.out.println("Hello from Eclipse!");

        // Crear instancias de las subclases originales
        Pelicula pelicula = new Pelicula("Avatar", 125, "Accion", "20th Century Studios");
        SerieDeTV serie = new SerieDeTV("Game of Thrones", 60, "Fantasy");
        Documental documental = new Documental("Cosmos", 45, "Science", "Astronomy");

        // Agregación: actores en la película
        pelicula.agregarActor(new Actor("Sam Worthington", 48, "Australiano"));
        pelicula.agregarActor(new Actor("Zoe Saldaña", 46, "Estadounidense"));

        // Composición: temporadas de la serie
        serie.agregarTemporada(1, 10, 2011);
        serie.agregarTemporada(2, 10, 2012);
        serie.agregarTemporada(3, 10, 2013);

        // Asociación: investigadores del documental
        documental.asociarInvestigador(new Investigador("Carl Sagan", "Astronomía", "Universidad de Cornell"));
        documental.asociarInvestigador(new Investigador("Ann Druyan", "Divulgación científica", "Cosmos Studios"));

        // Nueva subclase Anime: composición con Temporada y agregación con Actor
        Anime anime = new Anime("Attack on Titan", 24, "Accion", "Wit Studio", "Japonés");
        anime.agregarTemporada(1, 25, 2013);
        anime.agregarTemporada(2, 12, 2017);
        anime.agregarActorDeVoz(new Actor("Kenji Mori", 34, "Japonés"));
        anime.agregarActorDeVoz(new Actor("Aiko Tanaka", 31, "Japonesa"));

        // Nueva subclase Trailer: asociación con Pelicula
        Trailer trailer = new Trailer("Avatar - Tráiler oficial", 2, "Accion",
                                      "Sitio oficial", true, pelicula);

        // Guardar todo en un arreglo (polimorfismo)
        ContenidoAudiovisual[] contenidos = new ContenidoAudiovisual[5];
        contenidos[0] = pelicula;
        contenidos[1] = serie;
        contenidos[2] = documental;
        contenidos[3] = anime;
        contenidos[4] = trailer;

        // Mostrar los detalles de cada contenido audiovisual
        for (ContenidoAudiovisual contenido : contenidos) {
            contenido.mostrarDetalles();
        }
    }
}