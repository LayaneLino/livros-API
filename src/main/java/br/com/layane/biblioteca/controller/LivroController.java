package br.com.layane.biblioteca.controller;

import br.com.layane.biblioteca.domain.Livro;
import br.com.layane.biblioteca.service.LivroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {
    private final LivroService service;

    public LivroController(LivroService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Livro> cadastrar(@RequestBody Livro livro) {
        Livro novoLivro = service.cadastrarLivro(livro);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoLivro);
    }

    @GetMapping
    public ResponseEntity<List<Livro>> listar() {
        return ResponseEntity.ok(service.listarLivros());
    }

    @GetMapping("/busca/{id}")
    public ResponseEntity<Livro> buscarPorId(@PathVariable int id) {
        return ResponseEntity.ok(service.buscarLivroPorId(id));
    }

    @GetMapping("/busca/{isbn}")
    public ResponseEntity<Livro> buscarPorIsbn(@PathVariable String isbn) {
        return ResponseEntity.ok(service.buscarLivroPorIsbn(isbn));
    }

    @PutMapping("/att/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable int id, @RequestBody Livro livro) {
        service.atualizarLivro(id, livro);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/status/{id}/indisponivel")
    public ResponseEntity<Void> marcarIndisponivel(@PathVariable int id) {
        service.marcarComoIndisponivel(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/status/{id}/disponivel")
    public ResponseEntity<Void> marcarDisponivel(@PathVariable int id) {
        service.marcarComoDisponivel(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Void> remover(@PathVariable int id) {
        service.removerLivro(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/contar")
    public ResponseEntity<Integer> contar() {
        return ResponseEntity.ok(service.contar());
    }

}
