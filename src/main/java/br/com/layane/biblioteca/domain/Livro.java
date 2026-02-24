package br.com.layane.biblioteca.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
import java.util.regex.Pattern;

public class Livro {
    private static int contadorId = 1;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private final int id;
    private String titulo;
    private String autor;
    private String isbn;
    private Integer anoPublicacao;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private boolean disponivel;

    public Livro(String titulo, String autor, String isbn, Integer anoPublicacao) {
        this.id = contadorId++;
        setTitulo(titulo);
        setAutor(autor);
        setIsbn(isbn);
        setAnoPublicacao(anoPublicacao);
        this.disponivel = true;
    }

    public static int getContadorId() {
        return contadorId;
    }

    public static void setContadorId(int contadorId) {
        Livro.contadorId = contadorId;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        if (titulo == null || titulo.trim().length() < 3) {
            throw new IllegalArgumentException("O título deve ter pelo menos 3 caracteres!");
        }
        this.titulo = titulo.trim();
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        if (autor == null || autor.trim().length() < 3) {
            throw new IllegalArgumentException("O autor deve ter pelo menos 3 caracteres!");
        }
        this.autor = autor.trim();
    }

    public String getIsbn() {
        return isbn;
    }

    private static final Pattern PADRAO_ISBN =
            Pattern.compile("^\\d{3}-\\d{1}-\\d{4}-\\d{4}-\\d{1}$");

    public void setIsbn(String isbn) {
        if (isbn == null || !PADRAO_ISBN.matcher(isbn).matches()) {
            throw new IllegalArgumentException("Formato de ISBN inválido! XXX-X-XXXX-XXXX-X");
        }
        this.isbn = isbn;
    }

    public Integer getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(Integer anoPublicacao) {
        if(anoPublicacao == null || anoPublicacao < 1500 || anoPublicacao > 2026){
            throw new IllegalArgumentException("O ano de publicação deve ser entre 1500 e 2026!");

        }
        this.anoPublicacao = anoPublicacao;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void marcarComoDisponivel() {
        this.disponivel = true;
    }

    public void marcarComoIndisponivel() {
        this.disponivel = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Livro livro = (Livro) o;
        return id == livro.id && anoPublicacao == livro.anoPublicacao && disponivel == livro.disponivel && Objects.equals(titulo, livro.titulo) && Objects.equals(autor, livro.autor) && Objects.equals(isbn, livro.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, autor, isbn, anoPublicacao, disponivel);
    }

    @Override
    public String toString() {
        return "Livro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", isbn='" + isbn + '\'' +
                ", anoPublicacao=" + anoPublicacao +
                ", disponivel=" + disponivel +
                '}';
    }
}
