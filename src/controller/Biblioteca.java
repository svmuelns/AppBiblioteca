package controller;

import model.Autor;
import model.Catalogo;
import model.Libro;
import util.Ayudantes;

import java.util.*;
import java.io.*;

public class Biblioteca{
    private int registroID;
    private static final String nombreBiblioteca = "ALEJANDRÍA";
    private static final String director = "Samuel Nuñez";

    private static final Map<String, Libro> BibliotecaDB = new HashMap<>();

    // ========== SETTERS Y GETTERS =============
    // AGREGAR LIBROS
    public static void AgregarLibro(Libro libro) {
        BibliotecaDB.put(libro.getIsbn(), libro);
    }
    public static String getNombreBiblioteca() {
        return nombreBiblioteca;
    }
    public static String getDirector(){
        return director;
    }

    // ================ MENU'S ==================
    public static void MostrarMenuBiblioteca(){
        System.out.println("\n *+-.-+*+-. MENÚ: LIBROS .-+*+-.-+*\n");
        System.out.println("     1. Ver todos los libros");
        System.out.println("     2. Agregar libros");
        System.out.println("     3. Eliminar libros");
        System.out.println("     4. Filtrar por autor");
        System.out.println("     5. Filtrar por categoría");
        System.out.println("     6. Filtrar por ISBN");
        System.out.println("     7. Exportar biblioteca");
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

            int choiceBiblio = Ayudantes.AyudanteScannerInt(sc, aviso, 0, 7);

            switch (choiceBiblio) {
                case 1:
                    // 1. MOSTRAR TODOS LOS LIBROS
                    MostrarLibrosBiblioteca();
                    break;
                case 2:
                    // 2. AGREGAR LIBROS
                    System.out.println("\n *+-.-+*+-. AGREGAR LIBRO .-+*+-.-+*\n");

                    // CREAR LIBRO
                    Libro libro =  new Libro().CrearLibro(sc);

                    // AGREGAR A LA BIBLIOTECA
                    AgregarLibro(libro);
                    break;
                case 3:
                    // 3. ELIMINAR LIBROS
                    System.out.println("\n *+-.-+*+-. ELIMINAR LIBRO .-+*+-.-+*\n");

                    // ELIMINAR LIBRO DE BIBLIOTECA
                    EliminarLibro(sc);

                    break;
                case 4:
                    // 4. FILTRAR POR AUTOR
                    index = 0;
                    FiltrarPorAutor(sc, index);
                    break;
                case 5:
                    // 5. FILTRAR POR CATEGORIA
                    FiltrarPorCategoria(sc);
                    break;
                case 6:
                    // 6. FILTRAR POR ISBN
                    index = 0;
                    FiltrarPorISBN(sc, index);
                    break;
                case 7:
                    // 7. EXPORTAR BIBLIOTECA
                    ExportarBiblioteca(BibliotecaDB);
                case 0:
                    salir = true;
            }
        } while (!salir);
    }

    // 1. VER TODOS LOS LIBROS
    public static void MostrarLibrosBiblioteca() {
        int index = 0;
        if (!BibliotecaDB.isEmpty()) {
            for (Libro librito : BibliotecaDB.values()) {
                Libro.MostrarLibro(librito, index);
                index++;
            }
            System.out.println();
        } else {
            System.out.println("[ La biblioteca está vacía... ]");
        }
    }

    // 2. FILTRAR POR AUTOR
    public static void FiltrarPorAutor(Scanner sc, int index) {
        boolean opcionNoValida;
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
                        }
                    }
                    if (index == 0) {      // Si no hay libros en la categoría, el index no aumenta
                        System.out.println("\n[ El autor no tiene libros por el momento... ]");
                    }

                }

            } while (opcionNoValida);
        } else {
            System.out.println("[ Por el momento no hay autores... ]");
        }
    }

    // 3. FILTRAR POR CATEGORIA
    public static void FiltrarPorCategoria(Scanner sc) {
        int index = 0;
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
                } else {

                    Catalogo categoriaSeleccionada = Catalogo.CatalogoDB().get(choiceCategoria - 1); // Restamos 1 a la categoría seleccionada

                    System.out.format("\n.-+*+-. FILTRANDO POR CATEGORÍA: %s .-+*+-.\n", categoriaSeleccionada.getCategoriaNombre());

                    for (Libro librito : BibliotecaDB.values()) {
                        if (librito.getCategoria().getCategoriaID() == categoriaSeleccionada.getCategoriaID()) {
                            Libro.MostrarLibro(librito, index);
                            index++;
                        }
                    }
                    if (index == 0) {      // Si no hay libros en la categoría, el index no aumenta
                        System.out.println("\n[ No hay libros en la categoría seleccionada por el momento... ]");
                    }
                }

            } while (opcionNoValida);
        } else{
            System.out.println("[ Por el momento no hay categorías... ]");
        }
    }

    // 4. FILTRAR POR ISBN
    public static Libro FiltrarPorISBN(Scanner sc, int index){
        boolean opcionNoValida;
        String inputISBN;

        if (!BibliotecaDB.isEmpty()){
            do {
                System.out.println("\n.-+*+-. FILTRANDO POR ISBN .-+*+-.\n");

                System.out.println("     0. Salir");

                String aviso = "\nEscribe el ISBN: ";
                opcionNoValida = false;

                inputISBN = Ayudantes.AyudanteScannerString(sc, aviso);

                if (inputISBN == null || inputISBN.isEmpty() || !inputISBN.matches("^[a-zA-Z0-9\\s\\-.,!?;:()]+$")) {
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
                System.out.println("\n     1. Eliminar libro: " + librito.getTitulo());
                System.out.println("     0. Salir");

                String aviso = "\nEstás seguro que deseas eliminar el libro?: ";
                deleteInput = Ayudantes.AyudanteScannerInt(sc, aviso, 0, 1);

                switch (deleteInput) {
                    case 1:
                        BibliotecaDB.remove(librito.getIsbn());
                        System.out.println("\n[ El libro ha sido eliminado con éxito... ]");
                        break;
                    case 0:
                        break;

                    default:
                        opcionNoValida = true;
                }
            }
        } while (opcionNoValida);
    }

    // 6. EXPORTAR LIBROS
    public static void ExportarBiblioteca(Map<String, Libro> BibliotecaDB) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("libros.obj"))) {
            oos.writeObject(BibliotecaDB);
            System.out.println("\n[ Libros exportados correctamente a libros.obj ]");
        } catch (IOException e) {
            System.err.println("\n[ Error al exportar los libros: " + e.getMessage() + "]");
            e.printStackTrace();
        }
    }
}

