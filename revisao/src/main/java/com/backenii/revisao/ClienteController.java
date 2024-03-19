package com.backenii.revisao;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public List<cliente> listarTodos(){
        return clienteRepository.findAll();
    }

    @GetMapping("/{nome}")
    public ResponseEntity<cliente> obterPorNome(@PathVariable String nome){
        return clienteRepository.findByNome(nome)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public cliente adicionarCliente(@RequestBody cliente NovoCliente){
        return clienteRepository.save(NovoCliente);
    }

    @DeleteMapping("/{nome}")
    public ResponseEntity<Void> excluirCLiente(@PathVariable String nome){
        if(clienteRepository.findByNome(nome).isPresent()) {
            clienteRepository.deleteByNome(nome);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
