import model.*;
import util.Ayudantes;
import controller.Biblioteca;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        boolean salir = false;

        Catalogo comedia = new Catalogo("Comedia");
        Catalogo terror = new Catalogo("Terror");
        Catalogo policiaca = new Catalogo("Policia"); // 2
        Catalogo ensayo = new Catalogo("Ensayo"); // 1

        Autor autor1 = new Autor("Alexandre Dumas");
        Autor autor2 = new Autor("Miguel de Cervantes");
        Autor autor3 = new Autor("Arthur Conan Doyle");

        Libro libro1 = new Libro("10000000000001", "La Dama de las Camelias", autor1, ensayo, Soporte.FISICO, 288);
        Libro libro2 = new Libro("10000000000002", "Don Quijote de la Mancha", autor2, comedia, Soporte.FISICO, 1500);
        Libro libro3 = new Libro("10000000000003", "Las Aventuras de Sherlock Holmes", autor3, policiaca, Soporte.DIGITAL, 342);

        Biblioteca.agregarLibro(libro1);
        Biblioteca.agregarLibro(libro2);
        Biblioteca.agregarLibro(libro3);

        do {
            // MENU PRINCIPAL
            MostrarMenuPrincipal();
            String aviso = "\nElige una opción: ";
            int choice = Ayudantes.AyudanteScannerInt(sc, aviso, 0, 5);

            switch (choice){
                case 1:
                    System.out.println("\n *+-.-+*+-. AGREGAR LIBRO .-+*+-.-+*\n");

                    // CREAR LIBRO
                    Libro libro =  new Libro().CrearLibro(sc);

                    // AGREGAR A LA BIBLIOTECA
                    Biblioteca.agregarLibro(libro);

                    break;
                case 2:     // 2. VER BIBLIOTECA ENTERA
                    // ELEGIR OPCION DE BIBLIOTECA
                    Biblioteca.OpcionesBiblioteca(sc);

                    break;
                case 3:
                    System.out.println("\n *+-.-+*+-. ELIMINAR LIBRO .-+*+-.-+*\n");

                    // ELIMINAR LIBRO DE BIBLIOTECA
                    Biblioteca.EliminarLibro(sc);

                    break;
                case 4:
                    // INTERFAZ DE OPCIONES DE AUTORES
                    Autor.OpcionesAutores(sc);
                    break;
                case 5:
                    System.out.println("\n *+-.-+*+-. VER CATÁLOGO .-+*+-.-+*\n");
                    break;
                case 0:
                    System.out.println("\nSaliendo del programa...");
                    salir = true;
                    break;
                default:
                    System.out.println("\n[ Opción inválida, inténtalo de nuevo... ]");
            }
        } while (!salir);
    }
    // ============= METODOS ==============
    // MENU PRINCIPAL
    public static void MostrarMenuPrincipal() {
        System.out.println("\n *+-.-+*+-. LA BIBLIOTECA DE ALEJANDRÍA .-+*+-.-+*\n");
        System.out.println("     1. Agregar libro");
        System.out.println("     2. Ver libros");
        System.out.println("     3. Eliminar libro");
        System.out.println("     4. Ver autores");
        System.out.println("     5. Ver catálogos");
        System.out.println("     0. Salir");
    }
}
