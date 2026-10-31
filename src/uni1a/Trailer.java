package uni1a;

// Subclase Trailer que extiende de ContenidoAudiovisual
public class Trailer extends ContenidoAudiovisual {
    private String plataforma;
    private boolean oficial;
    private Pelicula pelicula;  // Asociación con Pelicula

    public Trailer(String titulo, int duracionEnMinutos, String genero,
                   String plataforma, boolean oficial, Pelicula pelicula) {
        super(titulo, duracionEnMinutos, genero);
        this.plataforma = plataforma;
        this.oficial = oficial;
        this.pelicula = pelicula;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public boolean isOficial() {
        return oficial;
    }

    public void setOficial(boolean oficial) {
        this.oficial = oficial;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    @Override
    public void mostrarDetalles() {
        System.out.println("Detalles del tráiler:");
        System.out.println("ID: " + getId());
        System.out.println("Título: " + getTitulo());
        System.out.println("Duración en minutos: " + getDuracionEnMinutos());
        System.out.println("Género: " + getGenero());
        System.out.println("Plataforma: " + plataforma);
        System.out.println("Tráiler oficial: " + (oficial ? "Sí" : "No"));
        if (pelicula != null) {
            System.out.println("Película promocionada: " + pelicula.getTitulo()
                    + " (" + pelicula.getEstudio() + ")");
        } else {
            System.out.println("Película promocionada: ninguna");
        }
        System.out.println();
    }
}