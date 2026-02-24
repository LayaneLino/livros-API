package br.com.layane.biblioteca.repository;

import br.com.layane.biblioteca.domain.Livro;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class LivroRepositoryMemoria implements ILivroRepository {

    private final Map<Integer, Livro> livros = new HashMap<>();

    @Override
    public Livro salvar(Livro livro) {
        if (livro == null) {
            throw new IllegalArgumentException("Livro não pode ser null!");
        }
        livros.put(livro.getId(), livro);
        return livro;
    }

    @Override
    public List<Livro> listarTodos() {
        return new ArrayList<>(livros.values());
    }

    @Override
    public Livro buscarPorId(int id) {
        return livros.get(id);
    }

    @Override
    public Livro buscarPorIsbn(String isbn) {
        return livros.values().stream()
                .filter(l -> l.getIsbn().equals(isbn))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean atualizar(Livro livro) {
        if (livro != null && livros.containsKey(livro.getId())) {
            livros.put(livro.getId(), livro);
            return true;
        }
        return false;
    }

    @Override
    public boolean deletar(int id) {
        return livros.remove(id) != null;
    }

    @Override
    public int contar() {
        return livros.size();
    }
}
