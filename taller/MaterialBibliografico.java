public abstract class MaterialBibliografico {
    private int id;
    private String titulo;
    private boolean disponible;
    private int diasMaximosPrestamo;
    private double multaPorDia;
    private int contadorPrestamos;
    private double multaAcumulada;

    public MaterialBibliografico(int id, String titulo, int diasMaximosPrestamo, double multaPorDia) {
        this.id = id;
        this.titulo = titulo;
        this.diasMaximosPrestamo = diasMaximosPrestamo;
        this.multaPorDia = multaPorDia;
        this.disponible = true;
        this.contadorPrestamos = 0;
        this.multaAcumulada = 0;
    }

    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }
    public int getDiasMaximosPrestamo() { return diasMaximosPrestamo; }
    public double getMultaPorDia() { return multaPorDia; }
    public int getContadorPrestamos() { return contadorPrestamos; }
    public double getMultaAcumulada() { return multaAcumulada; }

    public void setMultaAcumulada(double multaAcumulada) {
        this.multaAcumulada = multaAcumulada;
    }

    public void incrementarContadorPrestamos() {
        this.contadorPrestamos++;
    }

    public abstract void mostrarInformacion();
    public abstract double calcularMulta(int diasRetraso);
}
