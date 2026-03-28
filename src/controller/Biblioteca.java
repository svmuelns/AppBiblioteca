package controller;

import model.Libro;
import model.Autor;
import model.Catalogo;
import util.Ayudantes;

import java.time.LocalDateTime;
import java.util.*;

public class Biblioteca{
    private int registroID;
    private LocalDateTime fechaAgregada;
    private LocalDateTime fechaPrestado;

    private static final Map<String, Libro> BibliotecaDB = new HashMap<>();

    // ========== SETTERS Y GETTERS =============
    public static Map<String, Libro> getBibliotecaDB() {
        return BibliotecaDB;
    }
    // AGREGAR LIBROS
    public static void agregarLibro(Libro libro) {
        BibliotecaDB.put(libro.getIsbn(), libro);
    }
    public static Libro getLibro(String key) {
        return BibliotecaDB.get(key);
    }
    public static boolean contieneLibro(String key) {
        return BibliotecaDB.containsKey(key);
    }
    public static int getSize() {
        return BibliotecaDB.size();
    }

    // ================ MENU'S ==================
    public static void MostrarMenuBiblioteca(){
        System.out.println("\n *+-.-+*+-. MENÚ: LIBROS .-+*+-.-+*\n");
        System.out.println("     1. Ver todos los libros");
        System.out.println("     2. Filtrar por autor");
        System.out.println("     3. Filtrar por categoría");
        System.out.println("     4. Filtrar por ISBN");
        System.out.println("     0. Salir");
    }

    // MENU ELEGIR OPCION BIBLIOTECA
    public static void OpcionesBiblioteca(Scanner sc) {
        boolean salir;

        do {
            salir = false;
            int index;

            // MOSTRAR MENU DE OPCIONES BIBLIOTECA
            MostrarMenuBiblioteca();

            // ELEGIR OPCION
            String aviso = "\nElige una opción: ";

            int choiceBiblio = Ayudantes.AyudanteScannerInt(sc, aviso, 0, 4);

            switch (choiceBiblio) {
                case 1:
                    // 1. MOSTRAR TODOS LOS LIBROS
                    MostrarLibrosBiblioteca();
                    break;
                case 2:
                    // 2. FILTRAR POR AUTOR
                    index = 0;
                    FiltrarPorAutor(sc, index);
                    break;
                case 3:
                    // 3. FILTRAR POR CATEGORIA
                    index = 0;
                    FiltrarPorCategoria(sc, index);
                    break;
                case 4:
                    // 4. FILTRAR POR ISBN
                    index = 0;
                    FiltrarPorISBN(sc, index);
                    break;
                case 0:
                    salir = true;
            }
        } while (!salir);
    }

    // 1. VER TODOS LOS LIBROS
    public static void MostrarLibrosBiblioteca() {
        int index = 0;
        for (Libro librito : BibliotecaDB.values()) {
            Libro.MostrarLibro(librito, index);
            index++;
        }
        System.out.println();
    }

    // 2. FILTRAR POR AUTOR
    public static void FiltrarPorAutor(Scanner sc, int index) {
        boolean opcionNoValida;
        boolean libroEncontrado = false;
        int choiceAutor;

        if (!Autor.AutoresDB().isEmpty()) {
            do {
                // MOSTRAR MENU DE AUTORES
                Autor.MostrarAutores();

                String aviso = "Elige un autor: ";
                opcionNoValida = false;

                choiceAutor = Ayudantes.AyudanteScannerInt(sc, aviso, 1, Autor.AutoresDB().size());

                if (choiceAutor < 1 || choiceAutor > Autor.AutoresDB().size() ){
                    opcionNoValida = true;
                } else {

                    Autor autorSeleccionado = Autor.AutoresDB().get(choiceAutor - 1);

                    System.out.format("\n.-+*+-. FILTRANDO POR AUTOR: %s .-+*+-.\n", autorSeleccionado.getNombre());

                    for (Libro librito : BibliotecaDB.values()) {

                        if (librito.getAutor().getAutorID() == autorSeleccionado.getAutorID()) {
                            Libro.MostrarLibro(librito, index);
                            index++;
                            libroEncontrado = true;
                        }
                    }

                    if (!libroEncontrado) {
                        System.out.println("\n[ No se encontraron libros para este autor... ]");
                    }

                }

            } while (opcionNoValida);
        } else {
            System.out.println("[ Por el momento no hay autores... ]");
        }
    }

    // 3. FILTRAR POR CATEGORIA
    public static void FiltrarPorCategoria(Scanner sc, int index) {
        boolean opcionNoValida;
        int choiceCategoria;

        if (!Catalogo.CatalogoDB().isEmpty()){
            do {
                // MOSTRAR CATEGORIAS DEL CATALOGO
                Catalogo.MostrarCatalogo();

                String aviso = "\nElige una categoría: ";
                opcionNoValida = false;

                choiceCategoria = Ayudantes.AyudanteScannerInt(sc, aviso, 1, Catalogo.CatalogoDB().size());

                if (choiceCategoria < 1 || choiceCategoria > Catalogo.CatalogoDB().size()){
                    opcionNoValida = true;
                }

                // RESTAMOS 1 A LA CATEGORIA SELECCIONADA
                Catalogo categoriaSeleccionada = Catalogo.CatalogoDB().get(choiceCategoria - 1);

                System.out.format("\n.-+*+-. FILTRANDO POR CATEGORÍA: %s .-+*+-.\n", categoriaSeleccionada.getCategoriaName());

                for (Libro librito : BibliotecaDB.values()){
                    if (librito.getCategoria().getCategoriaID() == categoriaSeleccionada.getCategoriaID()) {
                        Libro.MostrarLibro(librito, index);
                        index++;
                    }
                }

            } while (opcionNoValida);
        }
    }

    // 4. FILTRAR POR ISBN
    public static Libro FiltrarPorISBN(Scanner sc, int index){
        boolean opcionNoValida;
        String inputISBN;

        if (!BibliotecaDB.isEmpty()){
            do {

                System.out.println("\n     0. Salir");

                String aviso = "\nEscribe el ISBN: ";
                opcionNoValida = false;

                inputISBN = Ayudantes.AyudanteScannerString(sc, aviso);

                if (inputISBN == null || inputISBN.isEmpty() || !inputISBN.matches("^[a-zA-Z0-9\\s\\-\\.,!?;:()]+$")) {
                    opcionNoValida = true;
                } else if (inputISBN.equals("0")) {
                    break;
                } else {
                    for (Libro librito : BibliotecaDB.values()){
                        if (librito.getIsbn().equals(inputISBN)){
                            Libro.MostrarLibro(librito, index);
                            return librito;
                        }
                        index++;
                    }
                }
            } while (opcionNoValida);
        } else {
            System.out.println("[ La biblioteca está vacía... ]");
        }
        return null;
    }

    // 5. ELIMINAR LIBRO
    public static void EliminarLibro(Scanner sc){
        int index = 0;
        int deleteInput;
        boolean opcionNoValida;

        Libro librito = FiltrarPorISBN(sc, index);

        do {
            opcionNoValida = false;

            if (librito != null){
                System.out.println("     1. Eliminar libro: " + librito.getTitulo());
                System.out.println("     0. Salir");

                String aviso = "\nEstás seguro que deseas eliminar el libro?: ";
                deleteInput = Ayudantes.AyudanteScannerInt(sc, aviso, 0, 1);

                switch (deleteInput) {
                    case 1:
                        BibliotecaDB.remove(librito.getIsbn());
                        System.out.println("\n[ El libro ha sido eliminado con éxito... ]");

                    case 0:
                        System.out.println("Saliendo...");
                        break;

                    default:
                        opcionNoValida = true;
                }
            }
        } while (opcionNoValida);
    }
}

