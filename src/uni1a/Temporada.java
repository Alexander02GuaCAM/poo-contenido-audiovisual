package uni1a;

public class Temporada {
    private int numero;
    private int numeroEpisodios;
    private int anioEstreno;

    public Temporada(int numero, int numeroEpisodios, int anioEstreno) {
        this.numero = numero;
        this.numeroEpisodios = numeroEpisodios;
        this.anioEstreno = anioEstreno;
    }

    public int getNumero() { return numero; }
    public int getNumeroEpisodios() { return numeroEpisodios; }
    public int getAnioEstreno() { return anioEstreno; }

    @Override
    public String toString() {
        return "Temporada " + numero + " - " + numeroEpisodios
               + " episodios (" + anioEstreno + ")";
    }
}