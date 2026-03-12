import java.time.LocalDate;

public class Prestamos {
    private MaterialBibliografico material;
    private int diasRetraso;
    private double multaTotal;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;

    public Prestamos(MaterialBibliografico material, int diasRetraso, double multaTotal, 
                     LocalDate fechaPrestamo, LocalDate fechaDevolucion) {
        this.material = material;
        this.diasRetraso = diasRetraso;
        this.multaTotal = multaTotal;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
    }

    public MaterialBibliografico getMaterial() {
        return material;
    }

    public int getDiasRetraso() {
        return diasRetraso;
    }

    public double getMultaTotal() {
        return multaTotal;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }
}


