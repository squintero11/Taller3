import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<MaterialBibliografico> biblioteca = new ArrayList<>();
    private static ArrayList<Prestamos> prestamosRealizados = new ArrayList<>();
    private static int contadorId = 1;
    private static Scanner scanner = new Scanner(System.in);
    // Formato de fecha esperado: dd/MM/yyyy
    private static final DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        cargarMaterialesEjemplo();

        boolean salir = false;

        while (!salir) {
            mostrarMenu();
            int opcion = leerEntero("Seleccione una opción (1-5): ", 1, 5);

            switch (opcion) {
                case 1:
                    agregarMaterial();
                    break;
                case 2:
                    listarMateriales();
                    break;
                case 3:
                    simularPrestamo();
                    break;
                case 4:
                    mostrarAcumuladoMultas();
                    break;
                case 5:
                    salir = true;
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
            System.out.println();
        }
        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("=== BIBLIOTECA - MENU PRINCIPAL ===");
        System.out.println("1. Agregar material");
        System.out.println("2. Listar materiales");
        System.out.println("3. Simular préstamo (con fechas)");
        System.out.println("4. Mostrar acumulado de multas");
        System.out.println("5. Salir");
    }

    private static int leerEntero(String mensaje, int min, int max) {
        int valor = 0;
        boolean valido = false;

        while (!valido) {
            System.out.print(mensaje);
            if (scanner.hasNextInt()) {
                valor = scanner.nextInt();
                scanner.nextLine();
                if (valor >= min && valor <= max) {
                    valido = true;
                } else {
                    System.out.println("Error: El número debe estar entre " + min + " y " + max);
                }
            } else {
                System.out.println("Error: Debe ingresar un número válido.");
                scanner.nextLine();
            }
        }
        return valor;
    }

    private static void cargarMaterialesEjemplo() {
        System.out.println("=== CARGANDO MATERIALES DE EJEMPLO ===");
        System.out.println("Cargando 10 Libros, 10 Revistas y 10 Películas uno por uno...\n");

        // 10 Libros
        biblioteca.add(new Libro(contadorId++, "El Principito", 96, "Antoine de Saint-Exupéry"));
        biblioteca.add(new Libro(contadorId++, "Cien Años de Soledad", 417, "Gabriel García Márquez"));
        biblioteca.add(new Libro(contadorId++, "1984", 328, "George Orwell"));
        biblioteca.add(new Libro(contadorId++, "Don Quijote", 863, "Miguel de Cervantes"));
        biblioteca.add(new Libro(contadorId++, "La Odisea", 541, "Homero"));
        biblioteca.add(new Libro(contadorId++, "Moby Dick", 635, "Herman Melville"));
        biblioteca.add(new Libro(contadorId++, "Orgullo y Prejuicio", 432, "Jane Austen"));
        biblioteca.add(new Libro(contadorId++, "El Señor de los Anillos", 1178, "J.R.R. Tolkien"));
        biblioteca.add(new Libro(contadorId++, "Crónica de una Muerte Anunciada", 160, "Gabriel García Márquez"));
        biblioteca.add(new Libro(contadorId++, "Fahrenheit 451", 249, "Ray Bradbury"));

        // 10 Revistas
        biblioteca.add(new Revista(contadorId++, "National Geographic", "Mensual", 101));
        biblioteca.add(new Revista(contadorId++, "Time", "Semanal", 102));
        biblioteca.add(new Revista(contadorId++, "Vogue", "Mensual", 103));
        biblioteca.add(new Revista(contadorId++, "Forbes", "Semanal", 104));
        biblioteca.add(new Revista(contadorId++, "National Geographic Kids", "Mensual", 105));
        biblioteca.add(new Revista(contadorId++, "People", "Semanal", 106));
        biblioteca.add(new Revista(contadorId++, "Rolling Stone", "Semanal", 107));
        biblioteca.add(new Revista(contadorId++, "Scientific American", "Mensual", 108));
        biblioteca.add(new Revista(contadorId++, "The New Yorker", "Semanal", 109));
        biblioteca.add(new Revista(contadorId++, "Wired", "Mensual", 110));

        // 10 Películas
        biblioteca.add(new Pelicula(contadorId++, "El Padrino", 175, "Francis Ford Coppola"));
        biblioteca.add(new Pelicula(contadorId++, "Pulp Fiction", 154, "Quentin Tarantino"));
        biblioteca.add(new Pelicula(contadorId++, "El Señor de los Anillos: La Comunidad del Anillo", 178, "Peter Jackson"));
        biblioteca.add(new Pelicula(contadorId++, "El Resplandor", 146, "Stanley Kubrick"));
        biblioteca.add(new Pelicula(contadorId++, "Matrix", 136, "Lana Wachowski, Lilly Wachowski"));
        biblioteca.add(new Pelicula(contadorId++, "Interstellar", 169, "Christopher Nolan"));
        biblioteca.add(new Pelicula(contadorId++, "El Laberinto del Fauno", 118, "Guillermo del Toro"));
        biblioteca.add(new Pelicula(contadorId++, "Parásitos", 132, "Bong Joon-ho"));
        biblioteca.add(new Pelicula(contadorId++, "Coco", 105, "Lee Unkrich, Adrian Molina"));
        biblioteca.add(new Pelicula(contadorId++, "Toy Story", 81, "John Lasseter"));

        System.out.println("\n✓ " + biblioteca.size() + " materiales cargados exitosamente.");
        System.out.println("✓ Contador de ID actual: " + contadorId);
        System.out.println("========================================\n");
    }

    private static void agregarMaterial() {
        System.out.println("\n--- Agregar Nuevo Material ---");
        System.out.print("Ingrese el título: ");
        String titulo = scanner.nextLine();

        if (titulo.trim().isEmpty()) {
            System.out.println("Error: El título no puede estar vacío.");
            return;
        }

        System.out.print("Seleccione tipo (1: Libro, 2: Revista, 3: Película): ");
        int tipo = leerEntero("", 1, 3);

        MaterialBibliografico nuevoMaterial = null;

        if (tipo == 1) {
            System.out.print("Ingrese número de páginas: ");
            int paginas = leerEntero("", 1, 99999);
            System.out.print("Ingrese autor: ");
            String autor = scanner.nextLine();
            nuevoMaterial = new Libro(contadorId, titulo, paginas, autor);
        } else if (tipo == 2) {
            System.out.print("Ingrese periodicidad (ej. Mensual): ");
            String periodicidad = scanner.nextLine();
            System.out.print("Ingrese número de edición: ");
            int edicion = leerEntero("", 1, 99999);
            nuevoMaterial = new Revista(contadorId, titulo, periodicidad, edicion);
        } else if (tipo == 3) {
            System.out.print("Ingrese duración en minutos: ");
            int duracion = leerEntero("", 1, 99999);
            System.out.print("Ingrese director: ");
            String director = scanner.nextLine();
            nuevoMaterial = new Pelicula(contadorId, titulo, duracion, director);
        }

        if (nuevoMaterial != null) {
            biblioteca.add(nuevoMaterial);
            contadorId++;
            System.out.println("Material agregado con éxito (ID: " + nuevoMaterial.getId() + ")");
            System.out.println("Contador de ID actual: " + contadorId);
        }
    }

    private static void listarMateriales() {
        System.out.println("\n--- Listado de Materiales ---");
        if (biblioteca.isEmpty()) {
            System.out.println("No hay materiales en la biblioteca.");
            return;
        }
        System.out.println("Total de materiales: " + biblioteca.size());
        System.out.println("------------------------------------------------");
        for (MaterialBibliografico m : biblioteca) {
            m.mostrarInformacion();
        }
    }

    private static void simularPrestamo() {
        System.out.println("\n--- Simular Préstamo y Devolución ---");
        if (biblioteca.isEmpty()) {
            System.out.println("No hay materiales para prestar.");
            return;
        }

        System.out.println("Seleccione el ID del material a prestar:");
        for (MaterialBibliografico m : biblioteca) {
            System.out.println("- ID: " + m.getId() + " | " + m.getTitulo() + " | " + (m.isDisponible() ? "Disponible" : "Prestado"));
        }

        int idSeleccionado = leerEntero("Ingrese ID: ", 1, 99999);

        MaterialBibliografico materialSeleccionado = null;
        for (MaterialBibliografico m : biblioteca) {
            if (m.getId() == idSeleccionado) {
                materialSeleccionado = m;
                break;
            }
        }

        if (materialSeleccionado != null) {
            if (!materialSeleccionado.isDisponible()) {
                System.out.println("Error: Este material ya está prestado.");
                return;
            }

            System.out.print("Ingrese fecha de préstamo (dd/MM/yyyy): ");
            String fechaPrestamoStr = scanner.nextLine();
            LocalDate fechaPrestamo = LocalDate.parse(fechaPrestamoStr, formatoFecha);

            System.out.print("Ingrese fecha de devolución (dd/MM/yyyy): ");
            String fechaDevolucionStr = scanner.nextLine();
            LocalDate fechaDevolucion = LocalDate.parse(fechaDevolucionStr, formatoFecha);

            // Calcular días de retraso
            int diasRetraso = calcularDiasRetraso(materialSeleccionado, fechaPrestamo, fechaDevolucion);

            // Calcular multa
            double multa = materialSeleccionado.calcularMulta(diasRetraso);

            // Registrar préstamo
            Prestamos prestamo = new Prestamos(materialSeleccionado, diasRetraso, multa, fechaPrestamo, fechaDevolucion);
            prestamosRealizados.add(prestamo);

            // Mostrar resultados
            System.out.println("------------------------------------------------");
            System.out.println("Material: " + materialSeleccionado.getTitulo());
            System.out.println("Fecha de préstamo: " + fechaPrestamo.format(formatoFecha));
            System.out.println("Fecha de devolución: " + fechaDevolucion.format(formatoFecha));
            System.out.println("Días de retraso: " + diasRetraso);
            System.out.printf("Multa Total a Pagar: $%.2f pesos%n", multa);
            System.out.println("------------------------------------------------");

            materialSeleccionado.setDisponible(false);
            System.out.println("Material marcado como PRESTADO.");
        } else {
            System.out.println("Material no encontrado.");
        }
    }

    private static int calcularDiasRetraso(MaterialBibliografico material, LocalDate fechaPrestamo, LocalDate fechaDevolucion) {
        int diasPrestamo = obtenerDiasPrestamo(material);
        long diasUsados = ChronoUnit.DAYS.between(fechaPrestamo, fechaDevolucion);

        if (diasUsados <= diasPrestamo) {
            return 0;
        } else {
            return (int) (diasUsados - diasPrestamo);
        }
    }

    private static int obtenerDiasPrestamo(MaterialBibliografico material) {
        if (material instanceof Libro) {
            return 14;
        } else if (material instanceof Revista) {
            return 7;
        } else if (material instanceof Pelicula) {
            return 3;
        }
        return 0;
    }

    private static void mostrarAcumuladoMultas() {
        System.out.println("\n=== ACUMULADO DE MULTAS ===");
        if (prestamosRealizados.isEmpty()) {
            System.out.println("No hay préstamos registrados.");
            return;
        }

        double totalAcumulado = 0;

        System.out.println("------------------------------------------------");
        System.out.printf("%-5s %-20s %-15s %-10s %-10s%n", "ID", "Material", "Multa", "Días", "Estado");
        System.out.println("------------------------------------------------");

        for (Prestamos p : prestamosRealizados) {
            System.out.printf("%-5d %-20s $%-14.2f %-10d %-10s%n",
                p.getMaterial().getId(),
                p.getMaterial().getTitulo(),
                p.getMultaTotal(),
                p.getDiasRetraso(),
                p.getMaterial().isDisponible() ? "Prestado" : "Devuelto");
            totalAcumulado += p.getMultaTotal();
        }

        System.out.println("------------------------------------------------");
        System.out.printf("TOTAL ACUMULADO: $%.2f pesos%n", totalAcumulado);
        System.out.println("------------------------------------------------");
        System.out.println("Total de préstamos registrados: " + prestamosRealizados.size());
    }
}