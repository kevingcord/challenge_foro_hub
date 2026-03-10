package com.forohub.controller;

import com.forohub.domain.Topico;
import com.forohub.dto.DatosRegistroTopico;
import com.forohub.repository.TopicoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    private final TopicoRepository repository;

    public TopicoController(TopicoRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @Transactional
    public void crearTopico(@RequestBody @Valid DatosRegistroTopico datos) {

        if(repository.findByTituloAndMensaje(datos.titulo(), datos.mensaje()).isPresent()){
            throw new RuntimeException("Tópico duplicado");
        }

        Topico topico = new Topico(
                null,
                datos.titulo(),
                datos.mensaje(),
                LocalDateTime.now(),
                "ABIERTO",
                datos.autor(),
                datos.curso()
        );

        repository.save(topico);
    }

    @GetMapping
    public List<Topico> listarTopicos(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Topico detalleTopico(@PathVariable Long id){
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tópico no encontrado"));
    }

    @PutMapping("/{id}")
    @Transactional
    public Topico actualizarTopico(@PathVariable Long id,
                                   @RequestBody @Valid DatosRegistroTopico datos){

        Topico topico = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe"));

        topico.setTitulo(datos.titulo());
        topico.setMensaje(datos.mensaje());
        topico.setAutor(datos.autor());
        topico.setCurso(datos.curso());

        return topico;
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void eliminarTopico(@PathVariable Long id){
        repository.deleteById(id);
    }
}