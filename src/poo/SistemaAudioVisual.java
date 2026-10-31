package poo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import uni1a.*;

public class SistemaAudioVisual {

    private static final Scanner sc = new Scanner(System.in);
    private static final Catalogo catalogo = new Catalogo();
    private static boolean ejemploCargado = false;

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");
            System.out.println();
            ejecutarOpcion(opcion);
        } while (opcion != 0);
        System.out.println("Programa finalizado.");
    }

    // ---------------------------------------------------------------- MENÚ

    private static void mostrarMenu() {
        System.out.println("\n========== SISTEMA DE CONTENIDO AUDIOVISUAL ==========");
        System.out.println(" 1. Crear película");
        System.out.println(" 2. Crear serie de TV");
        System.out.println(" 3. Crear documental");
        System.out.println(" 4. Crear anime");
        System.out.println(" 5. Crear tráiler (de una película existente)");
        System.out.println(" 6. Registrar actor");
        System.out.println(" 7. Registrar investigador");
        System.out.println(" 8. Agregar actor a película / actor de voz a anime");
        System.out.println(" 9. Agregar temporada a serie / anime");
        System.out.println("10. Asociar investigador a documental");
        System.out.println("11. Listar contenidos");
        System.out.println("12. Ver detalles de un contenido");
        System.out.println("13. Buscar por título");
        System.out.println("14. Eliminar contenido");
        System.out.println("15. Ver estadísticas");
        System.out.println("16. Cargar datos de ejemplo");
        System.out.println(" 0. Salir");
        System.out.println("======================================================");
    }

    private static void ejecutarOpcion(int opcion) {
        switch (opcion) {
            case 1:  crearPelicula(); break;
            case 2:  crearSerie(); break;
            case 3:  crearDocumental(); break;
            case 4:  crearAnime(); break;
            case 5:  crearTrailer(); break;
            case 6:  registrarActor(); break;
            case 7:  registrarInvestigador(); break;
            case 8:  agregarActorAContenido(); break;
            case 9:  agregarTemporadaAContenido(); break;
            case 10: asociarInvestigadorADocumental(); break;
            case 11: listarContenidos(); break;
            case 12: verDetalles(); break;
            case 13: buscarContenido(); break;
            case 14: eliminarContenido(); break;
            case 15: mostrarEstadisticas(); break;
            case 16: cargarDatosDeEjemplo(); break;
            case 0:  break;
            default: System.out.println("Opción no válida. Intente de nuevo.");
        }
    }

    // ------------------------------------------------------ CREAR CONTENIDO

    private static void crearPelicula() {
        System.out.println("--- Nueva película ---");
        String titulo = leerTexto("Título: ");
        int duracion = leerEnteroPositivo("Duración en minutos: ");
        String genero = leerTexto("Género: ");
        String estudio = leerTexto("Estudio: ");

        Pelicula p = new Pelicula(titulo, duracion, genero, estudio);
        catalogo.agregarContenido(p);
        System.out.println("Película creada (ID " + p.getId() + ").");
    }

    private static void crearSerie() {
        System.out.println("--- Nueva serie de TV ---");
        String titulo = leerTexto("Título: ");
        int duracion = leerEnteroPositivo("Duración por episodio (min): ");
        String genero = leerTexto("Género: ");

        SerieDeTV s = new SerieDeTV(titulo, duracion, genero);
        catalogo.agregarContenido(s);
        System.out.println("Serie creada (ID " + s.getId() + ").");
        registrarTemporadasIniciales(s);
    }

    private static void crearDocumental() {
        System.out.println("--- Nuevo documental ---");
        String titulo = leerTexto("Título: ");
        int duracion = leerEnteroPositivo("Duración en minutos: ");
        String genero = leerTexto("Género: ");
        String tema = leerTexto("Tema: ");

        Documental d = new Documental(titulo, duracion, genero, tema);
        catalogo.agregarContenido(d);
        System.out.println("Documental creado (ID " + d.getId() + ").");
    }

    private static void crearAnime() {
        System.out.println("--- Nuevo anime ---");
        String titulo = leerTexto("Título: ");
        int duracion = leerEnteroPositivo("Duración por episodio (min): ");
        String genero = leerTexto("Género: ");
        String estudio = leerTexto("Estudio de animación: ");
        String idioma = leerTexto("Idioma original: ");

        Anime a = new Anime(titulo, duracion, genero, estudio, idioma);
        catalogo.agregarContenido(a);
        System.out.println("Anime creado (ID " + a.getId() + ").");
        registrarTemporadasIniciales(a);
    }

    private static void crearTrailer() {
        System.out.println("--- Nuevo tráiler ---");
        List<Pelicula> peliculas = catalogo.getPeliculas();
        if (peliculas.isEmpty()) {
            System.out.println("Primero debe existir al menos una película (opción 1).");
            return;
        }
        Pelicula pelicula = seleccionar(peliculas, "Seleccione la película a promocionar:");
        if (pelicula == null) {
            return;
        }
        String titulo = leerTexto("Título del tráiler: ");
        int duracion = leerEnteroPositivo("Duración en minutos: ");
        String genero = leerTexto("Género: ");
        String plataforma = leerTexto("Plataforma de publicación: ");
        boolean oficial = leerSiNo("¿Es un tráiler oficial?");

        Trailer t = new Trailer(titulo, duracion, genero, plataforma, oficial, pelicula);
        catalogo.agregarContenido(t);
        System.out.println("Tráiler creado (ID " + t.getId() + ").");
    }

    // -------------------------------------------- ACTORES E INVESTIGADORES

    private static void registrarActor() {
        System.out.println("--- Nuevo actor ---");
        String nombre = leerTexto("Nombre: ");
        int edad = leerEnteroEnRango("Edad: ", 1, 120);
        String nacionalidad = leerTexto("Nacionalidad: ");

        if (catalogo.registrarActor(new Actor(nombre, edad, nacionalidad))) {
            System.out.println("Actor registrado.");
        } else {
            System.out.println("Ya existe un actor con ese nombre.");
        }
    }

    private static void registrarInvestigador() {
        System.out.println("--- Nuevo investigador ---");
        String nombre = leerTexto("Nombre: ");
        String especialidad = leerTexto("Especialidad: ");
        String institucion = leerTexto("Institución: ");

        if (catalogo.registrarInvestigador(new Investigador(nombre, especialidad, institucion))) {
            System.out.println("Investigador registrado.");
        } else {
            System.out.println("Ya existe un investigador con ese nombre.");
        }
    }

    // ----------------------------------------------------------- RELACIONES

    // Agregación: el actor existe por sí solo y se agrega al contenido
    private static void agregarActorAContenido() {
        List<ContenidoAudiovisual> candidatos = new ArrayList<>();
        candidatos.addAll(catalogo.getPeliculas());
        candidatos.addAll(catalogo.getAnimes());
        if (candidatos.isEmpty()) {
            System.out.println("No hay películas ni animes. Cree uno primero.");
            return;
        }
        if (catalogo.getActores().isEmpty()) {
            System.out.println("No hay actores registrados. Use la opción 6 primero.");
            return;
        }
        ContenidoAudiovisual contenido = seleccionar(candidatos, "Seleccione la película o anime:");
        if (contenido == null) {
            return;
        }
        Actor actor = seleccionar(catalogo.getActores(), "Seleccione el actor:");
        if (actor == null) {
            return;
        }

        if (contenido instanceof Pelicula) {
            Pelicula p = (Pelicula) contenido;
            if (p.getActores().contains(actor)) {
                System.out.println("Ese actor ya está en el reparto.");
            } else {
                p.agregarActor(actor);
                System.out.println("Actor agregado al reparto de " + p.getTitulo() + ".");
            }
        } else {
            Anime a = (Anime) contenido;
            if (a.getActoresDeVoz().contains(actor)) {
                System.out.println("Ese actor ya es actor de voz de este anime.");
            } else {
                a.agregarActorDeVoz(actor);
                System.out.println("Actor de voz agregado a " + a.getTitulo() + ".");
            }
        }
    }

    // Composición: la serie o el anime CREAN sus propias temporadas
    private static void agregarTemporadaAContenido() {
        List<ContenidoAudiovisual> candidatos = new ArrayList<>();
        candidatos.addAll(catalogo.getSeries());
        candidatos.addAll(catalogo.getAnimes());
        if (candidatos.isEmpty()) {
            System.out.println("No hay series ni animes. Cree uno primero.");
            return;
        }
        ContenidoAudiovisual contenido = seleccionar(candidatos, "Seleccione la serie o anime:");
        if (contenido != null) {
            agregarTemporadaA(contenido);
        }
    }

    private static void agregarTemporadaA(ContenidoAudiovisual contenido) {
        List<Temporada> existentes;
        if (contenido instanceof SerieDeTV) {
            existentes = ((SerieDeTV) contenido).getTemporadas();
        } else {
            existentes = ((Anime) contenido).getTemporadas();
        }

        int numero = leerEnteroPositivo("Número de temporada: ");
        for (Temporada t : existentes) {
            if (t.getNumero() == numero) {
                System.out.println("La temporada " + numero + " ya existe.");
                return;
            }
        }
        int episodios = leerEnteroPositivo("Número de episodios: ");
        int anio = leerEnteroEnRango("Año de estreno: ", 1900, 2100);

        if (contenido instanceof SerieDeTV) {
            ((SerieDeTV) contenido).agregarTemporada(numero, episodios, anio);
        } else {
            ((Anime) contenido).agregarTemporada(numero, episodios, anio);
        }
        System.out.println("Temporada " + numero + " agregada a " + contenido.getTitulo() + ".");
    }

    private static void registrarTemporadasIniciales(ContenidoAudiovisual contenido) {
        while (leerSiNo("¿Desea agregar una temporada ahora?")) {
            agregarTemporadaA(contenido);
        }
    }

    // Asociación: el investigador existe por sí solo y se vincula al documental
    private static void asociarInvestigadorADocumental() {
        List<Documental> documentales = catalogo.getDocumentales();
        if (documentales.isEmpty()) {
            System.out.println("No hay documentales. Cree uno primero (opción 3).");
            return;
        }
        if (catalogo.getInvestigadores().isEmpty()) {
            System.out.println("No hay investigadores registrados. Use la opción 7 primero.");
            return;
        }
        Documental documental = seleccionar(documentales, "Seleccione el documental:");
        if (documental == null) {
            return;
        }
        Investigador investigador = seleccionar(catalogo.getInvestigadores(), "Seleccione el investigador:");
        if (investigador == null) {
            return;
        }

        if (documental.getInvestigadores().contains(investigador)) {
            System.out.println("Ese investigador ya está asociado a este documental.");
        } else {
            documental.asociarInvestigador(investigador);
            System.out.println("Investigador asociado a " + documental.getTitulo() + ".");
        }
    }

    // ------------------------------------------------------------ CONSULTAS

    private static void listarContenidos() {
        List<ContenidoAudiovisual> lista = catalogo.getContenidos();
        if (lista.isEmpty()) {
            System.out.println("El catálogo está vacío.");
            return;
        }
        System.out.println("--- Catálogo (" + lista.size() + " contenidos) ---");
        for (int i = 0; i < lista.size(); i++) {
            System.out.println((i + 1) + ". " + describir(lista.get(i)));
        }
    }

    private static void verDetalles() {
        ContenidoAudiovisual contenido = seleccionar(catalogo.getContenidos(), "Seleccione un contenido:");
        if (contenido != null) {
            System.out.println();
            contenido.mostrarDetalles();
        }
    }

    private static void buscarContenido() {
        String texto = leerTexto("Texto a buscar en el título: ");
        List<ContenidoAudiovisual> resultados = catalogo.buscarPorTitulo(texto);
        if (resultados.isEmpty()) {
            System.out.println("No se encontraron resultados.");
            return;
        }
        System.out.println(resultados.size() + " resultado(s):\n");
        for (ContenidoAudiovisual c : resultados) {
            c.mostrarDetalles();
        }
    }

    private static void eliminarContenido() {
        ContenidoAudiovisual contenido = seleccionar(catalogo.getContenidos(), "Seleccione el contenido a eliminar:");
        if (contenido == null) {
            return;
        }
        if (leerSiNo("¿Seguro que desea eliminar \"" + contenido.getTitulo() + "\"?")) {
            catalogo.eliminarContenido(contenido);
            System.out.println("Contenido eliminado.");
        } else {
            System.out.println("Operación cancelada.");
        }
    }

    private static void mostrarEstadisticas() {
        int totalTemporadas = 0;
        for (SerieDeTV s : catalogo.getSeries()) {
            totalTemporadas += s.getNumeroTemporadas();
        }
        int totalEpisodiosAnime = 0;
        for (Anime a : catalogo.getAnimes()) {
            totalEpisodiosAnime += a.getTotalEpisodios();
        }

        System.out.println("--- Estadísticas ---");
        System.out.println("Total de contenidos: " + catalogo.getContenidos().size());
        System.out.println(" - Películas: " + catalogo.getPeliculas().size());
        System.out.println(" - Series de TV: " + catalogo.getSeries().size()
                + " (" + totalTemporadas + " temporadas en total)");
        System.out.println(" - Documentales: " + catalogo.getDocumentales().size());
        System.out.println(" - Animes: " + catalogo.getAnimes().size()
                + " (" + totalEpisodiosAnime + " episodios en total)");
        System.out.println(" - Tráilers: " + catalogo.getTrailers().size());
        System.out.println("Actores registrados: " + catalogo.getActores().size());
        System.out.println("Investigadores registrados: " + catalogo.getInvestigadores().size());
    }

    // -------------------------------------------------------- DATOS DE PRUEBA

    private static void cargarDatosDeEjemplo() {
        if (ejemploCargado) {
            System.out.println("Los datos de ejemplo ya fueron cargados.");
            return;
        }
        Actor a1 = new Actor("Sam Worthington", 48, "Australiano");
        Actor a2 = new Actor("Zoe Saldaña", 46, "Estadounidense");
        Actor a3 = new Actor("Kenji Mori", 34, "Japonés");
        catalogo.registrarActor(a1);
        catalogo.registrarActor(a2);
        catalogo.registrarActor(a3);

        Investigador i1 = new Investigador("Carl Sagan", "Astronomía", "Universidad de Cornell");
        catalogo.registrarInvestigador(i1);

        Pelicula avatar = new Pelicula("Avatar", 125, "Accion", "20th Century Studios");
        avatar.agregarActor(a1);
        avatar.agregarActor(a2);

        SerieDeTV got = new SerieDeTV("Game of Thrones", 60, "Fantasy");
        got.agregarTemporada(1, 10, 2011);
        got.agregarTemporada(2, 10, 2012);

        Documental cosmos = new Documental("Cosmos", 45, "Science", "Astronomy");
        cosmos.asociarInvestigador(i1);

        Anime aot = new Anime("Attack on Titan", 24, "Accion", "Wit Studio", "Japonés");
        aot.agregarTemporada(1, 25, 2013);
        aot.agregarTemporada(2, 12, 2017);
        aot.agregarActorDeVoz(a3);

        Trailer trailer = new Trailer("Avatar - Tráiler oficial", 2, "Accion", "Sitio oficial", true, avatar);

        catalogo.agregarContenido(avatar);
        catalogo.agregarContenido(got);
        catalogo.agregarContenido(cosmos);
        catalogo.agregarContenido(aot);
        catalogo.agregarContenido(trailer);

        ejemploCargado = true;
        System.out.println("Datos de ejemplo cargados: 5 contenidos, 3 actores y 1 investigador.");
    }

    // ------------------------------------------------------------ UTILIDADES

    private static String tipo(ContenidoAudiovisual c) {
        if (c instanceof Pelicula) return "Película";
        if (c instanceof SerieDeTV) return "Serie";
        if (c instanceof Documental) return "Documental";
        if (c instanceof Anime) return "Anime";
        if (c instanceof Trailer) return "Tráiler";
        return "Contenido";
    }

    private static String describir(Object o) {
        if (o instanceof ContenidoAudiovisual) {
            ContenidoAudiovisual c = (ContenidoAudiovisual) o;
            return "[" + tipo(c) + "] " + c.getTitulo() + " - "
                    + c.getDuracionEnMinutos() + " min - " + c.getGenero();
        }
        return o.toString();
    }

    // Muestra una lista numerada y devuelve el elemento elegido (o null si se cancela)
    private static <T> T seleccionar(List<T> lista, String titulo) {
        if (lista.isEmpty()) {
            System.out.println("No hay elementos disponibles.");
            return null;
        }
        System.out.println(titulo);
        for (int i = 0; i < lista.size(); i++) {
            System.out.println((i + 1) + ". " + describir(lista.get(i)));
        }
        int numero = leerEntero("Número (0 para cancelar): ");
        if (numero == 0) {
            System.out.println("Operación cancelada.");
            return null;
        }
        if (numero < 1 || numero > lista.size()) {
            System.out.println("Número fuera de rango.");
            return null;
        }
        return lista.get(numero - 1);
    }

    private static String leerTexto(String mensaje) {
        String texto;
        do {
            System.out.print(mensaje);
            texto = sc.nextLine().trim();
            if (texto.isEmpty()) {
                System.out.println("El valor no puede estar vacío.");
            }
        } while (texto.isEmpty());
        return texto;
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String linea = sc.nextLine().trim();
            try {
                return Integer.parseInt(linea);
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número entero válido.");
            }
        }
    }

    private static int leerEnteroPositivo(String mensaje) {
        return leerEnteroEnRango(mensaje, 1, Integer.MAX_VALUE);
    }

    private static int leerEnteroEnRango(String mensaje, int min, int max) {
        int numero;
        do {
            numero = leerEntero(mensaje);
            if (numero < min || numero > max) {
                if (max == Integer.MAX_VALUE) {
                    System.out.println("Debe ser un número mayor o igual a " + min + ".");
                } else {
                    System.out.println("Debe estar entre " + min + " y " + max + ".");
                }
            }
        } while (numero < min || numero > max);
        return numero;
    }

    private static boolean leerSiNo(String mensaje) {
        while (true) {
            System.out.print(mensaje + " (s/n): ");
            String r = sc.nextLine().trim().toLowerCase();
            if (r.equals("s") || r.equals("si") || r.equals("sí")) {
                return true;
            }
            if (r.equals("n") || r.equals("no")) {
                return false;
            }
            System.out.println("Responda con s o n.");
        }
    }
}

