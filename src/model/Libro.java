package model;

import java.io.Serializable;
import java.util.Scanner;
import java.util.Random;

import util.Ayudantes;

public class Libro extends Elemento implements Serializable {
    private Soporte soporte;
    private static final long serialVersionUID = 1L; // Control de versiones

    // ============== CONSTRUCTORES ==============

    public Libro() {}

    public Libro(String isbn, String titulo, Autor autor, Catalogo categoria, Soporte soporte, int nroPaginas) {
        super(isbn, titulo, autor, categoria, nroPaginas);
        this.soporte = soporte;
    }

    // ============= GETTER Y SETTER =============
    public Soporte getSoporte() { return soporte; }
    public void setSoporte(Soporte soporte) { this.soporte = soporte; }


    // ================== MENU'S =================
    public static void MostrarMenuSoporte() {
        System.out.println("\n *+-.-+*+-. SOPORTE .-+*+-.-+*\n");
        System.out.println("     1. Físico");
        System.out.println("     2. Digital");
    }

    // ============= DATOS DEL LIBRO =================
    // 0. GENERAR ID
    public static String GenerarISBN(){
        Random random = new Random();
        long isbn = (long)(random.nextDouble() * 10_000_000_000_000L);

        return String.format("%013d", isbn);
    }
    // 1. AGREGAR TITULO
    public static String AgregarTitulo(Scanner sc) {
        String titulo;
        boolean opcionNoValida;

        do {
            opcionNoValida = false;
            String aviso = "Título: ";

            titulo = Ayudantes.AyudanteScannerString(sc, aviso);

            if (titulo == null || titulo.isEmpty() || !titulo.matches("^[a-zA-Z0-9\\s\\-\\.,!?;:()]+$")) {
                opcionNoValida = true;
            }
        } while (opcionNoValida);
        return titulo;
    }
    // 2. AGREGAR AUTOR
    public static Autor AgregarAutor(Scanner sc) {
        String nombreAutor;
        boolean opcionNoValida;
        Autor autor = null;

        do {
            opcionNoValida = false;
            String aviso = "Autor: ";

            nombreAutor = Ayudantes.AyudanteScannerString(sc, aviso);

            if (nombreAutor == null || nombreAutor.isEmpty() || !nombreAutor.matches("^[a-zA-Z0-9\\s\\-\\.,!?;:()]+$")) {
                opcionNoValida = true;
            } else {
                // AGREGAMOS EL AUTOR A LA BASE DE DATOS DE AUTORES
                autor = new Autor(nombreAutor);
            }

        } while (opcionNoValida);

        return autor;
    }
    // 3. ELEGIR GÉNERO
    public static Catalogo AgregarCategoria(Scanner sc) {
        Catalogo categoria = new Catalogo();
        boolean opcionNoValida;

        do {
            opcionNoValida = false;

            // MOSTRAR MENU DE CATEGORIAS
            Catalogo.MostrarCatalogo();

            // ELEGIR OPCION
            String aviso = "\nElige un género: ";

            int bookChoice = Ayudantes.AyudanteScannerInt(sc, aviso, 1, Catalogo.CatalogoDB().size());

            switch (bookChoice) {
                case 1 -> categoria = Catalogo.getCategoriaById(1);
                case 2 -> categoria = Catalogo.getCategoriaById(2);
                case 3 -> categoria = Catalogo.getCategoriaById(3);
                case 4 -> categoria = Catalogo.getCategoriaById(4);
                default -> opcionNoValida = true;
            }


        } while (opcionNoValida);
        return categoria;
    }
    // 4. AGREGAR NRO DE PAGINAS
    public static int AgregarNroPaginas(Scanner sc) {
        int nroPaginas;
        boolean opcionNoValida;

        do {
            opcionNoValida = false;

            // MOSTRAR TITULO
            String aviso = "Nº de páginas: ";
            nroPaginas = Ayudantes.AyudanteScannerInt(sc, aviso, 1, 2000);

            if (nroPaginas > 2000 || nroPaginas < 0){
                opcionNoValida = true;
            }
        } while (opcionNoValida);
        return nroPaginas;
    }
    // 5. AGREGAR SOPORTE
    public static Soporte AgregarSoporte(Scanner sc){
        Soporte soporte = null;
        boolean opcionNoValida;

        do {
            opcionNoValida = false;

            // MOSTRAR MENU DE GENEROS
            MostrarMenuSoporte();

            // ELEGIR OPCION
            String aviso = "\nElige el soporte: ";

            int soporteChoice = Ayudantes.AyudanteScannerInt(sc, aviso, 1, 2);

            switch (soporteChoice) {
                case 1 -> soporte = Soporte.FISICO;
                case 2 -> soporte = Soporte.DIGITAL;
                default -> opcionNoValida = true;
            }
        } while (opcionNoValida);
        return soporte;
    }

    // ================ CREAR LIBRO ===================
    public Libro CrearLibro(Scanner sc){
        this.setIsbn(Libro.GenerarISBN());
        this.setTitulo(Libro.AgregarTitulo(sc));
        this.setAutor(Libro.AgregarAutor(sc));
        this.setCategoria(Libro.AgregarCategoria(sc));
        this.setNroPaginas(Libro.AgregarNroPaginas(sc));
        this.setSoporte(Libro.AgregarSoporte(sc));
        return new Libro(this.getIsbn(), this.getTitulo(), this.getAutor(), this.getCategoria(), this.getSoporte(), getNroPaginas());
    }

    // ================ MOSTRAR LIBRO =================
    public static void MostrarLibro(Libro libro, int index){
        System.out.format("\n=========== Libro %d ===========\n\n", index + 1);
        System.out.println("     ISBN: " + libro.getIsbn());
        System.out.println("     Titulo: " + libro.getTitulo());
        System.out.println("     Autor: " + libro.getAutor().getNombre());
        System.out.println("     Categoria: " + libro.getCategoria().getCategoriaNombre());
        System.out.println("     Nº de páginas: " + libro.getNroPaginas());
        System.out.println("     Soporte: " + libro.getSoporte());
    }
}
