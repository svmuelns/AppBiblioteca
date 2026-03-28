package model;

public abstract class Elemento {
    private String isbn;
    private String titulo;
    private Autor autor;
    private Catalogo categoria;
    private int nroPaginas;

    // ============= CONSTRUCTORES ===============
    public Elemento() {}

    public Elemento(String isbn, String titulo, Autor autor, Catalogo categoria, int nroPaginas) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.nroPaginas = nroPaginas;
    }

    // ============= GETTER Y SETTER =============
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Catalogo getCategoria() {
        return categoria;
    }

    public void setCategoria(Catalogo categoria) {
        this.categoria = categoria;
    }

    public int getNroPaginas() {
        return nroPaginas;
    }

    public void setNroPaginas(int nroPaginas) {
        this.nroPaginas = nroPaginas;
    }
}
