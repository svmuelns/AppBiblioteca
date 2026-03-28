package model;

import java.util.ArrayList;
import java.util.List;

public class Catalogo {
    private int categoriaID;
    private String categoriaNombre;

    private static final List<Catalogo> CatalogoLista= new ArrayList<>();
    private static int contadorID = 1;

    // ============= CONSTRUCTORES ==============

    public Catalogo() {}

    public Catalogo(String categoriaNombre) {
        this.categoriaID = contadorID++;
        this.categoriaNombre = categoriaNombre;

        CatalogoLista.add(this);
    }
    // =========== GETTERS Y SETTERS ============
    public int getCategoriaID() {
        return categoriaID;
    }
    public void setCategoriaID(int categoriaID) {
        this.categoriaID = categoriaID;
    }
    public String getCategoriaName() {
        return categoriaNombre;
    }
    public void setCategoriaName(String categoriaNombre) {
        this.categoriaNombre = categoriaNombre;
    }
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

    // MOSTRAR MENU CATALOGO - CATEGORIAS
    public static void MostrarCatalogo() {
        System.out.println("\n *+-.-+*+-. CATALOGO .-+*+-.-+*\n");
        for (Catalogo categoria : Catalogo.CatalogoDB()) {
            System.out.println("     " + categoria.getCategoriaID() + ". " + categoria.categoriaNombre);
        }
    }

}

