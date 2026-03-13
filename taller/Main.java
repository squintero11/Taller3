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

        biblioteca.add(new Libro(contadorId++, "La Metamorfosis", 201, "Franz Kafka"));
        biblioteca.add(new Libro(contadorId++, "El Código Da Vinci", 454, "Dan Brown"));
        biblioteca.add(new Libro(contadorId++, "Harry Potter y la Piedra Filosofal", 309, "J.K. Rowling"));
        biblioteca.add(new Libro(contadorId++, "Los Juegos del Hambre", 374, "Suzanne Collins"));
        biblioteca.add(new Libro(contadorId++, "El Alquimista", 208, "Paulo Coelho"));
        biblioteca.add(new Libro(contadorId++, "Drácula", 418, "Bram Stoker"));
        biblioteca.add(new Libro(contadorId++, "El Hobbit", 310, "J.R.R. Tolkien"));
        biblioteca.add(new Libro(contadorId++, "El Perfume", 255, "Patrick Süskind"));
        biblioteca.add(new Libro(contadorId++, "La Sombra del Viento", 565, "Carlos Ruiz Zafón"));
        biblioteca.add(new Libro(contadorId++, "El Psicoanalista", 468, "John Katzenbach"));

        biblioteca.add(new Revista(contadorId++, "Muy Interesante", "Mensual", 201));
        biblioteca.add(new Revista(contadorId++, "Semana", "Semanal", 202));
        biblioteca.add(new Revista(contadorId++, "Car and Driver", "Mensual", 203));
        biblioteca.add(new Revista(contadorId++, "PC World", "Mensual", 204));
        biblioteca.add(new Revista(contadorId++, "Runner’s World", "Mensual", 205));
        biblioteca.add(new Revista(contadorId++, "Men’s Health", "Mensual", 206));
        biblioteca.add(new Revista(contadorId++, "Women’s Health", "Mensual", 207));
        biblioteca.add(new Revista(contadorId++, "Historia National Geographic", "Mensual", 208));
        biblioteca.add(new Revista(contadorId++, "Emprendedores", "Mensual", 209));
        biblioteca.add(new Revista(contadorId++, "Fotogramas", "Mensual", 210));

        biblioteca.add(new Pelicula(contadorId++, "Titanic", 195, "James Cameron"));
        biblioteca.add(new Pelicula(contadorId++, "Avatar", 162, "James Cameron"));
        biblioteca.add(new Pelicula(contadorId++, "Inception", 148, "Christopher Nolan"));
        biblioteca.add(new Pelicula(contadorId++, "Gladiator", 155, "Ridley Scott"));
        biblioteca.add(new Pelicula(contadorId++, "Jurassic Park", 127, "Steven Spielberg"));
        biblioteca.add(new Pelicula(contadorId++, "El Rey León", 88, "Roger Allers, Rob Minkoff"));
        biblioteca.add(new Pelicula(contadorId++, "Shrek", 90, "Andrew Adamson, Vicky Jenson"));
        biblioteca.add(new Pelicula(contadorId++, "Frozen", 102, "Chris Buck, Jennifer Lee"));
        biblioteca.add(new Pelicula(contadorId++, "Spider-Man: No Way Home", 148, "Jon Watts"));
        biblioteca.add(new Pelicula(contadorId++, "Doctor Strange", 115, "Scott Derrickson"));

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
