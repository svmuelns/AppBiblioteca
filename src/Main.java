import model.*;
import util.Ayudantes;
import controller.Biblioteca;
import model.Catalogo;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        boolean salir = false;

        Catalogo comedia = new Catalogo("Comedia", 100, "Parodia");
        Catalogo terror = new Catalogo("Terror", 100, "Gore");
        Catalogo policiaca = new Catalogo("Policia", 100, "Misterio", "Villano"); // 2
        Catalogo ensayo = new Catalogo("Ensayo", 100); // 1

        Autor autor1 = new Autor("Alexandre Dumas");
        Autor autor2 = new Autor("Miguel de Cervantes");
        Autor autor3 = new Autor("Arthur Conan Doyle");
        Autor autor4 = new Autor("Bram Stoker");

        Libro libro1 = new Libro("10000000000001", "La Dama de las Camelias", autor1, ensayo, Soporte.FISICO, 288);
        Libro libro2 = new Libro("10000000000002", "Don Quijote de la Mancha", autor2, comedia, Soporte.FISICO, 1500);
        Libro libro3 = new Libro("10000000000003", "Las Aventuras de Sherlock Holmes", autor3, policiaca, Soporte.DIGITAL, 342);
        Libro libro4 = new Libro("10000000000004", "Dracula", autor4, terror, Soporte.FISICO, 342);

        Biblioteca.AgregarLibro(libro1);
        Biblioteca.AgregarLibro(libro2);
        Biblioteca.AgregarLibro(libro3);
        Biblioteca.AgregarLibro(libro4);

        do {
            // MENU PRINCIPAL
            MostrarMenuPrincipal();
            String aviso = "\nElige una opción: ";
            int choice = Ayudantes.AyudanteScannerInt(sc, aviso, 0, 3);

            switch (choice){
                case 1:
                    // 1. VER LIBROS
                    Biblioteca.OpcionesBiblioteca(sc);

                    break;
                case 2:
                    // INTERFAZ DE OPCIONES DE AUTORES
                    Autor.OpcionesAutores(sc);
                    break;
                case 3:
                    // INTERFAZ DE OPCIONES DE CATÁLOGO
                    Catalogo.OpcionesCatalogo(sc);
                    break;
                case 0:
                    System.out.println("\nSaliendo del programa...");
                    salir = true;
                    break;
            }
        } while (!salir);
    }
    // ============= METODOS ==============
    // MENU PRINCIPAL
    public static void MostrarMenuPrincipal() {
        System.out.format("\n                            Director: %s", Biblioteca.getDirector());
        System.out.format("\n *+-.-+*+-. LA BIBLIOTECA DE %s .-+*+-.-+*\n\n", Biblioteca.getNombreBiblioteca());
        System.out.println("     1. Ver libros");
        System.out.println("     2. Ver autores");
        System.out.println("     3. Ver catálogos");
        System.out.println("     0. Salir");
    }
}
