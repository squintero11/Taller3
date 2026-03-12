public class Libro extends MaterialBibliografico {
    private int paginas;
    private String autor;

    public Libro(int id, String titulo, int paginas, String autor) {
        super(id, titulo, 14, 5000); // 14 días, $5000 por día
        this.paginas = paginas;
        this.autor = autor;
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
        System.out.println("Tipo: LIBRO | Autor: " + autor + " | Páginas: " + paginas);
        System.out.println("Días máximos préstamo: " + getDiasMaximosPrestamo());
        System.out.println("Estado: " + (isDisponible() ? "Disponible" : "Prestado"));
        System.out.println("Prestamos realizados: " + getContadorPrestamos());
        System.out.println("Multa acumulada: $" + String.format("%.2f", getMultaAcumulada()));
        System.out.println("------------------------------------------------");
    }
}
