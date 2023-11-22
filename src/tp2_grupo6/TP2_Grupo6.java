
package tp2_grupo6;

import java.util.Scanner;

public class TP2_Grupo6 {
    private static String[] asignaturas; 
    private static String[] estudiantes;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        imprimirBienvenida();

        asignaturas = ingresarAsignaturas(scanner);
        estudiantes = ingresarEstudiantes(scanner);
        int[][] calificaciones = ingresarCalificaciones(estudiantes.length, asignaturas.length, scanner);

        while (true) {
            mostrarMenu();
            char opcion = scanner.next().charAt(0);

            switch (opcion) {
                case 'A':
                    calcularPromedios(estudiantes, asignaturas, calificaciones);
                    break;
                case 'B':
                    mostrarEstadisticas(asignaturas, calificaciones);
                    break;
                case 'C':
                    buscarEstudiante(estudiantes, calificaciones, scanner);
                    break;
                case 'D':
                    estudiantesDestacados(estudiantes, calificaciones);
                    break;
                case 'E':
                    System.out.println("Muchas gracias por usar el Sistema de Gestion de Calificaciones.");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opcion invalida. Por favor, ingrese una opcion valida.");
            }
        }
    }

    private static void imprimirBienvenida() {
        System.out.println("** Bienvenido al Sistema de Gestion de Calificaciones Estudian-TS **");
        System.out.print("Presiona Enter para continuar...");
        new Scanner(System.in).nextLine();
    }

    private static String[] ingresarAsignaturas(Scanner scanner) {
        System.out.print("Ingrese la cantidad de asignaturas (maximo 10): ");
        int numAsignaturas = scanner.nextInt();
        scanner.nextLine(); 

        String[] asignaturas = new String[numAsignaturas];
        for (int i = 0; i < numAsignaturas; i++) {
            System.out.print("Ingrese el nombre de la asignatura " + (i + 1) + ": ");
            asignaturas[i] = scanner.nextLine();
        }
        return asignaturas;
    }

    private static String[] ingresarEstudiantes(Scanner scanner) {
        System.out.print("Ingrese el numero de estudiantes (maximo 30): ");
        int numEstudiantes = scanner.nextInt();
        scanner.nextLine(); 

        String[] estudiantes = new String[numEstudiantes];
        for (int i = 0; i < numEstudiantes; i++) {
            System.out.print("Ingrese el nombre del estudiante " + (i + 1) + " (apellido, nombre): ");
            estudiantes[i] = scanner.nextLine();
        }
        return estudiantes;
    }

    private static int[][] ingresarCalificaciones(int numEstudiantes, int numAsignaturas, Scanner scanner) {
        int[][] calificaciones = new int[numEstudiantes][numAsignaturas];

        for (int i = 0; i < numEstudiantes; i++) {
            for (int j = 0; j < numAsignaturas; j++) {
                System.out.print("Ingrese la calificacion para " + estudiantes[i] + " en la asignatura "
                        + asignaturas[j] + ": ");
                calificaciones[i][j] = scanner.nextInt();

               
                while (calificaciones[i][j] < 1 || calificaciones[i][j] > 10) {
                    System.out.println("La calificacion debe estar en el rango [1, 10]. Intentelo nuevamente.");
                    System.out.print("Ingrese la calificacion para " + estudiantes[i] + " en la asignatura "
                            + asignaturas[j] + ": ");
                    calificaciones[i][j] = scanner.nextInt();
                }
            }
        }
        return calificaciones;
    }
    

    private static void mostrarMenu() {
        System.out.println("\n-- Menu Principal --");
        System.out.println("A. Calculo de Promedios");
        System.out.println("B. Estadisticas");
        System.out.println("C. Busqueda de Estudiantes");
        System.out.println("D. Estudiantes Destacados");
        System.out.println("E. Salir");
    }

    private static void calcularPromedios(String[] estudiantes, String[] asignaturas, int[][] calificaciones) {
        for (int i = 0; i < estudiantes.length; i++) {
            int sum = 0;
            for (int j = 0; j < calificaciones[i].length; j++) {
                sum += calificaciones[i][j];
            }
            double promedio = (double) sum / calificaciones[i].length;
            System.out.println(" * Promedio de " + estudiantes[i] + ": " + promedio + "*");
        }
    }

    private static void mostrarEstadisticas(String[] asignaturas, int[][] calificaciones) {
        for (int i = 0; i < asignaturas.length; i++) {
            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;

            for (int j = 0; j < calificaciones.length; j++) {
                if (calificaciones[j][i] > max) {
                    max = calificaciones[j][i];
                }
                if (calificaciones[j][i] < min) {
                    min = calificaciones[j][i];
                    
                }
            }

            System.out.println("* Estadisticas para la asignatura " + asignaturas[i] + " *");
            System.out.println("Calificacion mas alta: " + max);
            System.out.println("Calificacion mas baja: " + min + "\n");
        }
    }

    private static void buscarEstudiante(String[] estudiantes, int[][] calificaciones, Scanner scanner) {
        scanner.nextLine(); 
        System.out.print("Ingrese el nombre del estudiante a buscar: ");
        String nombreBuscar = scanner.nextLine();

        boolean encontrado = false;
        for (int i = 0; i < estudiantes.length; i++) {
            if (estudiantes[i].equalsIgnoreCase(nombreBuscar)) {
                System.out.println("* Calificaciones de " + estudiantes[i] + " *");
                for (int j = 0; j < calificaciones[i].length; j++) {
                    System.out.println("Asignatura " + asignaturas[j] + ": " + calificaciones[i][j]);
                }
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Estudiante " + nombreBuscar + " no encontrado.");
        }
    }

    private static void estudiantesDestacados(String[] estudiantes, int[][] calificaciones) {
        System.out.println("* Estudiantes destacados *");
        for (int i = 0; i < estudiantes.length; i++) {
            int sum = 0;
            for (int j = 0; j < calificaciones[i].length; j++) {
                sum += calificaciones[i][j];
            }
            double promedio = (double) sum / calificaciones[i].length;
            if (promedio > 9) {
                System.out.println(estudiantes[i] + ": Promedio = " + promedio);
            }
        }
    }
}