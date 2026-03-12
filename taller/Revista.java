public class Revista extends MaterialBibliografico {
    private String periodicidad;
    private int numeroEdicion;

    public Revista(int id, String titulo, String periodicidad, int numeroEdicion) {
        super(id, titulo, 7, 3000); // 7 días, $3000 por día
        this.periodicidad = periodicidad;
        this.numeroEdicion = numeroEdicion;
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
        System.out.println("Tipo: REVISTA | Periodicidad: " + periodicidad + " | Edición: " + numeroEdicion);
        System.out.println("Días máximos préstamo: " + getDiasMaximosPrestamo());
        System.out.println("Estado: " + (isDisponible() ? "Disponible" : "Prestado"));
        System.out.println("Prestamos realizados: " + getContadorPrestamos());
        System.out.println("Multa acumulada: $" + String.format("%.2f", getMultaAcumulada()));
        System.out.println("------------------------------------------------");
    }
}