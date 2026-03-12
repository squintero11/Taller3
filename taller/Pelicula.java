public class Pelicula extends MaterialBibliografico {
    private int duracionMinutos;
    private String director;

    public Pelicula(int id, String titulo, int duracionMinutos, String director) {
        super(id, titulo, 3, 10000); // 3 días, $10000 por día
        this.duracionMinutos = duracionMinutos;
        this.director = director;
    }

    @Override
    public double calcularMulta(int diasRetraso) {
        if (diasRetraso > 0) {
            return diasRetraso * getMultaPorDia();
        }
        return 0;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("------------------------------------------------");
        System.out.println("ID: " + getId() + " | Título: " + getTitulo());
        System.out.println("Tipo: PELÍCULA | Director: " + director + " | Duración: " + duracionMinutos + " min");
        System.out.println("Días máximos préstamo: " + getDiasMaximosPrestamo());
        System.out.println("Estado: " + (isDisponible() ? "Disponible" : "Prestado"));
        System.out.println("Prestamos realizados: " + getContadorPrestamos());
        System.out.println("Multa acumulada: $" + String.format("%.2f", getMultaAcumulada()));
        System.out.println("------------------------------------------------");
    }
}