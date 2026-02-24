package br.com.layane.biblioteca.repository;

import br.com.layane.biblioteca.domain.Livro;

import java.util.List;

public interface ILivroRepository {
    Livro salvar(Livro livro);
    List<Livro> listarTodos();
    Livro buscarPorId(int id);
    Livro buscarPorIsbn(String isbn);
    boolean atualizar(Livro livro);
    boolean deletar(int id);
    int contar();
}
