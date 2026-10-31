package uni1a;

import java.util.ArrayList;
import java.util.List;

// Clase de gestión: guarda los contenidos, actores e investigadores del sistema
public class Catalogo {
    private final List<ContenidoAudiovisual> contenidos = new ArrayList<>();
    private final List<Actor> actores = new ArrayList<>();
    private final List<Investigador> investigadores = new ArrayList<>();

    public void agregarContenido(ContenidoAudiovisual contenido) {
        contenidos.add(contenido);
    }

    // Devuelve false si ya existe un actor con ese nombre
    public boolean registrarActor(Actor actor) {
        for (Actor a : actores) {
            if (a.getNombre().equalsIgnoreCase(actor.getNombre())) {
                return false;
            }
        }
        actores.add(actor);
        return true;
    }

    // Devuelve false si ya existe un investigador con ese nombre
    public boolean registrarInvestigador(Investigador investigador) {
        for (Investigador i : investigadores) {
            if (i.getNombre().equalsIgnoreCase(investigador.getNombre())) {
                return false;
            }
        }
        investigadores.add(investigador);
        return true;
    }

    public List<ContenidoAudiovisual> getContenidos() {
        return new ArrayList<>(contenidos);
    }

    public List<Actor> getActores() {
        return new ArrayList<>(actores);
    }

    public List<Investigador> getInvestigadores() {
        return new ArrayList<>(investigadores);
    }

    public List<Pelicula> getPeliculas() {
        List<Pelicula> resultado = new ArrayList<>();
        for (ContenidoAudiovisual c : contenidos) {
            if (c instanceof Pelicula) {
                resultado.add((Pelicula) c);
            }
        }
        return resultado;
    }

    public List<SerieDeTV> getSeries() {
        List<SerieDeTV> resultado = new ArrayList<>();
        for (ContenidoAudiovisual c : contenidos) {
            if (c instanceof SerieDeTV) {
                resultado.add((SerieDeTV) c);
            }
        }
        return resultado;
    }

    public List<Documental> getDocumentales() {
        List<Documental> resultado = new ArrayList<>();
        for (ContenidoAudiovisual c : contenidos) {
            if (c instanceof Documental) {
                resultado.add((Documental) c);
            }
        }
        return resultado;
    }

    public List<Anime> getAnimes() {
        List<Anime> resultado = new ArrayList<>();
        for (ContenidoAudiovisual c : contenidos) {
            if (c instanceof Anime) {
                resultado.add((Anime) c);
            }
        }
        return resultado;
    }

    public List<Trailer> getTrailers() {
        List<Trailer> resultado = new ArrayList<>();
        for (ContenidoAudiovisual c : contenidos) {
            if (c instanceof Trailer) {
                resultado.add((Trailer) c);
            }
        }
        return resultado;
    }

    // Busca contenidos cuyo título contenga el texto (sin distinguir mayúsculas)
    public List<ContenidoAudiovisual> buscarPorTitulo(String texto) {
        List<ContenidoAudiovisual> resultado = new ArrayList<>();
        String buscado = texto.toLowerCase();
        for (ContenidoAudiovisual c : contenidos) {
            if (c.getTitulo().toLowerCase().contains(buscado)) {
                resultado.add(c);
            }
        }
        return resultado;
    }

    // Elimina un contenido. Si es una película, los tráilers que la promocionaban
    // quedan sin película asociada (asociación), pero siguen existiendo.
    public boolean eliminarContenido(ContenidoAudiovisual contenido) {
        boolean eliminado = contenidos.remove(contenido);
        if (eliminado && contenido instanceof Pelicula) {
            for (Trailer t : getTrailers()) {
                if (t.getPelicula() == contenido) {
                    t.setPelicula(null);
                }
            }
        }
        return eliminado;
    }
}
