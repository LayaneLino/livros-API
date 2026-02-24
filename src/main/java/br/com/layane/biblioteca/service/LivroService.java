package br.com.layane.biblioteca.service;

import br.com.layane.biblioteca.domain.Livro;
import br.com.layane.biblioteca.repository.ILivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {
    private final ILivroRepository repository;

    public LivroService(ILivroRepository repository) {
        this.repository = repository;
    }

    public Livro cadastrarLivro(Livro livro) {
        if (repository.buscarPorIsbn(livro.getIsbn()) != null) {
            throw new RuntimeException("Já existe um livro com este ISBN!");
        }
        return repository.salvar(livro);
    }

    public List<Livro> listarLivros() {
        return repository.listarTodos();
    }

    public Livro buscarLivroPorId(int id) {
        Livro livro = repository.buscarPorId(id);
        if (livro == null) {
            throw new RuntimeException("ID não encontrado!");
        }
        return livro;
    }

    public Livro buscarLivroPorIsbn(String isbn) {
        Livro livro = repository.buscarPorIsbn(isbn);
        if (livro == null) {
            throw new RuntimeException("ISBN não encontrado!");
        }
        return livro;
    }

    public void atualizarLivro(int id, Livro livroAtualizado) {
        Livro livroExistente = buscarLivroPorId(id);

        if (!livroExistente.getIsbn().equals(livroAtualizado.getIsbn())) {
            throw new RuntimeException("O ISBN não pode ser alterado!");
        }

        livroExistente.setTitulo(livroAtualizado.getTitulo());
        livroExistente.setAutor(livroAtualizado.getAutor());
        livroExistente.setAnoPublicacao(livroAtualizado.getAnoPublicacao());

        repository.atualizar(livroExistente);
    }

    public void removerLivro(int id) {
        buscarLivroPorId(id);
        repository.deletar(id);
    }

    public void marcarComoIndisponivel(int id) {
        Livro livro = buscarLivroPorId(id);
        livro.marcarComoIndisponivel();
        repository.atualizar(livro);
    }

    public void marcarComoDisponivel(int id) {
        Livro livro = buscarLivroPorId(id);
        livro.marcarComoDisponivel();
        repository.atualizar(livro);
    }

    public int contar() {
        return repository.contar();
    }


}
