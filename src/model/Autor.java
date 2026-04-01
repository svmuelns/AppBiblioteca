package model;

import util.Ayudantes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Autor {
    private final int autorID;
    private final String nombre;

    private static final List<Autor> AutoresLista = new ArrayList<>();
    private static int contadorID = 1;

    // ============== CONSTRUCTORES =============
    public Autor(String nombre) {
        this.autorID = contadorID++;
        this.nombre = nombre;
        AutoresLista.add(this);
    }

    // ============ GETTER Y SETTER ============
    public int getAutorID() {
        return autorID;
    }

    public String getNombre() {
        return nombre;
    }

    // ================= METODOS =================

    // MOSTRAR TODOS LOS AUTORES INDEXADOS
    // como es private, hacemos metodo para return la lista
    public static List<Autor> AutoresDB() {
        return new ArrayList<>(AutoresLista);
    }

    // FILTRAR POR ID
    public static Autor getAutorById(int id) {
        for (Autor autor : AutoresLista) {
            if (autor.getAutorID() == id) {
                return autor;
            }
        }
        return null; // sino aparece, regresa null
    }

    // ============= MENU AUTORES =============
    // MOSTRAR MENU AUTORES
    public static void MostrarMenuAutores(){
        System.out.println("\n *+-.-+*+-. MENÚ: AUTORES .-+*+-.-+*\n");
        System.out.println("     1. Ver todos los autores");
        System.out.println("     2. Agregar autor");
        System.out.println("     3. Eliminar autor");
        System.out.println("     0. Salir");
    }

    // MOSTRAR AUTORES
    public static void MostrarAutores() {
        System.out.println("\n *+-.-+*+-. AUTORES .-+*+-.-+*\n");
        if (!AutoresDB().isEmpty()) {
            for (Autor autorcito : AutoresDB()) {
                System.out.println("     " + autorcito.getAutorID() + ". " + autorcito.getNombre());
            }
            System.out.println();
        } else {
            System.out.println("[ No hay autores... ]");
        }
    }

    // MENU ELEGIR OPCION AUTORES
    public static void OpcionesAutores(Scanner sc) {
        boolean salir;

        do {
            salir = false;

            // MOSTRAR MENU DE OPCIONES BIBLIOTECA
            MostrarMenuAutores();

            // ELEGIR OPCION
            String aviso = "\nElige una opción: ";

            int choiceAutores = Ayudantes.AyudanteScannerInt(sc, aviso, 0, 3);

            switch (choiceAutores) {
                case 1:
                    // 1. MOSTRAR TODOS LOS AUTORES
                    MostrarAutores();
                    break;
                case 2:
                    // 2. AGREGAR AUTOR
                    Autor.AgregarAutor(sc);
                    break;
                case 3:
                    // 3. ELIMINAR AUTOR
                    Autor.EliminarAutor(sc);
                    break;
                case 0:
                    salir = true;
            }
        } while (!salir);
    }

    // 2. AGREGAR AUTOR
    public static void AgregarAutor(Scanner sc) {
        String nombreAutor;
        boolean opcionNoValida;

        do {
            System.out.println("\n *+-.-+*+-. AGREGAR AUTOR .-+*+-.-+*\n");

            opcionNoValida = false;
            String aviso = "Autor: ";

            nombreAutor = Ayudantes.AyudanteScannerString(sc, aviso);

            if (nombreAutor == null || nombreAutor.isEmpty() || !nombreAutor.matches("^[a-zA-Z0-9\\s\\-\\.,!?;:()]+$")) {
                opcionNoValida = true;
            } else {
                // AGREGAMOS EL AUTOR A LA BASE DE DATOS DE AUTORES
                new Autor(nombreAutor);
                System.out.println("[ ¡El autor " + nombreAutor + " se ha agregado correctamente! ]");
            }

        } while (opcionNoValida);
    }

    // FILTRAR POR ID AUTOR
    public static Autor ElegirAutor(Scanner sc) {
        boolean opcionNoValida;
        int inputAutorID;
        Autor autor;

        if (!AutoresDB().isEmpty()) {
            do {

                MostrarAutores();

                System.out.println("\n     0. Salir");

                String aviso = "\nEscribe el ID del autor: ";
                opcionNoValida = false;

                inputAutorID = Ayudantes.AyudanteScannerInt(sc, aviso, 1, AutoresDB().size());

                if (inputAutorID < 0 || inputAutorID > AutoresDB().size()) {
                    opcionNoValida = true;
                } else if (inputAutorID == 0) {
                    break;
                } else {
                    autor = getAutorById(inputAutorID);
                    return autor;

                }
            } while (opcionNoValida);
        } else {
            System.out.println("[ No hay autores... ]");
        }
        return null;
    }

    // ELIMINAR AUTOR
    public static void EliminarAutor(Scanner sc) {
        int deleteInput;
        boolean opcionNoValida;

        Autor autorcito = ElegirAutor(sc);

        do {
            opcionNoValida = false;

            if (autorcito != null) {
                System.out.println("\n     1. Eliminar autor: " + autorcito.getNombre());
                System.out.println("     0. Salir");

                String aviso = "\n¿Estás seguro que deseas eliminar el autor?: ";
                deleteInput = Ayudantes.AyudanteScannerInt(sc, aviso, 0, 1);

                if (deleteInput < 0 || deleteInput > 1) {
                    opcionNoValida = true;

                } else if (deleteInput == 1) {
                    AutoresDB().remove(autorcito.getAutorID() - 1);
                    System.out.println("\n[ El autor ha sido eliminado con éxito... ]");

                } else if (deleteInput == 0) {
                    System.out.println("Saliendo...");
                    break;
                }
            }
        } while (opcionNoValida);

    }
}