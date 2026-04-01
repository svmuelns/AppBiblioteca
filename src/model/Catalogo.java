package model;

import controller.Biblioteca;
import util.Ayudantes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Catalogo {
    private int categoriaID, capacidadLibros;
    private String categoriaNombre, subcategoria, personaje;

    private static final List<Catalogo> CatalogoLista= new ArrayList<>();
    private static int contadorID = 1;

    // ============= CONSTRUCTORES ==============

    // VACIO
    public Catalogo() {}

    // GENERAL
    public Catalogo(String categoriaNombre, int capacidadLibros) {
        this.categoriaID = contadorID++;
        this.categoriaNombre = categoriaNombre;
        this.capacidadLibros = capacidadLibros;

        CatalogoLista.add(this);
    }

    // TERROR
    public Catalogo(String categoriaNombre, int capacidadLibros, String subcategoria){
        this.categoriaID = contadorID++;
        this.categoriaNombre = categoriaNombre;
        this.capacidadLibros = capacidadLibros;
        this.subcategoria = subcategoria;

        CatalogoLista.add(this);
    }

    // POLICIACA
    public Catalogo(String categoriaNombre, int capacidadLibros, String subcategoria, String personaje){
        this.categoriaID = contadorID++;
        this.categoriaNombre = categoriaNombre;
        this.capacidadLibros = capacidadLibros;
        this.subcategoria = subcategoria;
        this.personaje = personaje;

        CatalogoLista.add(this);
    }

    // =========== GETTERS Y SETTERS ============
    public int getCategoriaID() {
        return categoriaID;
    }
    public void setCategoriaID(int categoriaID) {
        this.categoriaID = categoriaID;
    }
    public String getCategoriaNombre() {
        return categoriaNombre;
    }
    public void setCategoriaNombre(String categoriaNombre) {
        this.categoriaNombre = categoriaNombre;
    }

    public int getCapacidadLibros() {
        return capacidadLibros;
    }

    public void setCapacidadLibros(int capacidadLibros) {
        this.capacidadLibros = capacidadLibros;
    }

    public String getPersonaje() {
        return personaje;
    }

    public void setPersonaje(String personaje) {
        this.personaje = personaje;
    }

    public String getSubcategoria() {
        return subcategoria;
    }

    public void setSubcategoria(String subcategoria) {
        this.subcategoria = subcategoria;
    }
    // ============================================

    public static Catalogo getCategoriaById(int id) {
        for (Catalogo categoria : CatalogoLista) {
            if (categoria.getCategoriaID() == id) {
                return categoria;
            }
        }
        return null;
    }
    // MOSTRAR TODAS LAS CATEGORIAS-DB
    public static List<Catalogo> CatalogoDB() {
        return new ArrayList<>(CatalogoLista);
    }

    // =============== MÉTODOS ===================

    // MOSTRAR INFO DE CATALOGO
    public static void MostrarCategoria(Catalogo categoria, int index){
        System.out.format("\n=========== Catálogo %d ===========\n\n", index + 1);
        System.out.println("     Categoría: " + categoria.getCategoriaNombre());
        System.out.println("     Capacidad de libros: " + categoria.getCapacidadLibros());
        if (categoria.getSubcategoria() != null && !categoria.getSubcategoria().matches("null")) {
            System.out.println("     Subcategoría: " + categoria.getSubcategoria());
        }
            if (categoria.getPersonaje() != null && !categoria.getPersonaje().matches("null")){
            System.out.println("     Personaje: " + categoria.getPersonaje());
        }
    }

    // MOSTRAR CATALOGO, TODAS LAS CATEGORÍAS
    public static void MostrarCatalogo() {
        System.out.println("\n *+-.-+*+-. CATALOGO .-+*+-.-+*\n");
        for (Catalogo categoria : Catalogo.CatalogoDB()) {
            System.out.println("     " + categoria.getCategoriaID() + ". " + categoria.categoriaNombre);
        }
    }

    // 1. MOSTRAR MENU CATALOGO (INTERFAZ)
    public static void MostrarMenuCatalogo() {
        System.out.println("\n *+-.-+*+-. MENÚ: CATÁLOGO .-+*+-.-+*\n");
        System.out.println("     1. Ver catálogos");
        System.out.println("     2. Crear catálogo");
        System.out.println("     3. Cambiar libro de catálogo");
        System.out.println("     0. Salir");
    }

    // 2. VER TODOS LOS CATALOGOS
    public static void MostrarInfoCatalogo(Scanner sc){
        boolean opcionNoValida;
        int index = 0;

        do {
            opcionNoValida = false;

            // MOSTRAR TODAS LAS CATEGORÍAS
            MostrarCatalogo();

            // ELEGIR OPCION
            String aviso = "\nElige una categoría para ver su información: ";

            int choiceCategoria = Ayudantes.AyudanteScannerInt(sc, aviso, 1, CatalogoDB().size());

            if (!CatalogoDB().isEmpty()) {
                if (choiceCategoria < 1 || choiceCategoria > CatalogoDB().size()){
                    opcionNoValida = true;
                } else if (getCategoriaById(choiceCategoria) != null){
                    MostrarCategoria(getCategoriaById(choiceCategoria), index);
                }
            } else {
                System.out.println("[ No hay catálogos por el momento... ]");
            }

        } while (opcionNoValida);
    }

    // 3. AGREGAR LIBRO AL CATALOGO
    public static void AgregarLibroAlCatalogo(Scanner sc){
        boolean opcionNoValida;
        int index;

        do {
            opcionNoValida = false;
            index = 0;

            Libro libro = Biblioteca.FiltrarPorISBN(sc, index);

            if (libro != null){
                Libro.MostrarLibro(libro, index);

                MostrarCatalogo();

                System.out.println("\n[ Actualmente el libro " + libro.getTitulo() + " está en el catálogo " + libro.getCategoria().getCategoriaID() + ". " + libro.getCategoria().getCategoriaNombre() + " ]");

                String aviso = "\nA qué catálogo deseas cambiar el libro?: ";
                int choiceCambio = Ayudantes.AyudanteScannerInt(sc, aviso, 0, CatalogoDB().size());

                if (choiceCambio < 0 || choiceCambio > CatalogoDB().size()){
                    opcionNoValida = true;
                } else {
                    libro.setCategoria(getCategoriaById(choiceCambio));
                    System.out.println("\n[ La categoría del libro " + libro.getTitulo() + " ha cambiado a " + libro.getCategoria().getCategoriaNombre() + " exitosamente! ]");
                }
            }
        } while (opcionNoValida);
    }
    // =============== SUB MÉTODOS ===============

    // AGREGAR NOMBRE CATALOGO
    public static String AgregarNombreCatalogo(Scanner sc){
        String nombre;
        boolean opcionNoValida;

        do {
            opcionNoValida = false;
            String aviso = "Nombre de la categoría: ";

            nombre = Ayudantes.AyudanteScannerString(sc, aviso);

            if (nombre == null || nombre.isEmpty() || !nombre.matches("^[a-zA-Z0-9\\s\\-\\.,!?;:()]+$")) {
                opcionNoValida = true;
            }
        } while (opcionNoValida);
        return nombre;
    }

    // AGREGAR CAPACIDAD CATALOGO
    public static int AgregarCapacidadCatalogo(Scanner sc) {
        int capacidad;
        boolean opcionNoValida;

        do {
            opcionNoValida = false;

            String aviso = "Capacidad del catálogo: ";
            capacidad = Ayudantes.AyudanteScannerInt(sc, aviso, 1, 100);

            if (capacidad < 0 || capacidad > 100){
                opcionNoValida = true;
            }
        } while (opcionNoValida);
        return capacidad;
    }

    // CREAR CATALOGO
    public void CrearCatalogo(Scanner sc){
        this.setCategoriaNombre(AgregarNombreCatalogo(sc));
        this.setCapacidadLibros(AgregarCapacidadCatalogo(sc));
        new Catalogo(this.getCategoriaNombre(), this.getCapacidadLibros());
    }

    // ===========================================

    // MENU ELEGIR OPCION CATALOGO
    public static void OpcionesCatalogo(Scanner sc) {
        boolean salir;

        do {
            salir = false;

            // MOSTRAR MENU DE OPCIONES CATALOGO
            MostrarMenuCatalogo();

            // ELEGIR OPCION
            String aviso = "\nElige una opción: ";

            int choiceCatalogo = Ayudantes.AyudanteScannerInt(sc, aviso, 0, 3);

            switch (choiceCatalogo) {
                case 1:
                    // 1. MOSTRAR TODAS LAS CATEGORÍAS
                    MostrarInfoCatalogo(sc);
                    break;
                case 2:
                    // 2. CONSTRUIR CATÁLOGO (CREAR CATEGORÍA)
                    new Catalogo().CrearCatalogo(sc);
                    break;
                case 3:
                    // 3. AGREGAR LIBROS AL CATÁLOGO (CAMBIAR CATEGORÍA)
                    Catalogo.AgregarLibroAlCatalogo(sc);
                    break;
                case 0:
                    salir = true;
            }
        } while (!salir);

    }
}

// - Construir catálogo: pedirá el número de libros que se pueden guardar y construirá el catálogo
// - Consultar número de libros del catálogo: mostrará los libros que tiene el catálogo
// - Agregar libros al catálogo