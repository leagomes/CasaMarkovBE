package com.fatec.loja;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin(origins = "*")
@RestController
public class ClienteController {
    @Autowired
    ClienteRepository bd;

    @PostMapping("/api/cliente")
    public String gravar(@RequestBody Cliente obj){
        Optional<Cliente> emailTest = bd.findByEmail(obj.email);
        if(emailTest.isPresent()){
            return "Email já cadastrado!";
        } else {
            bd.save(obj);
            return "O cliente "+ obj.getNome() + " foi cadastrado com sucesso!";
        }
    }

    @PutMapping("/api/cliente")
    public String alterar(@RequestBody Cliente obj){
        Optional<Cliente> emailTest = bd.findByEmail(obj.email);
        if(emailTest.isPresent()){
            return "Email já cadastrado!";
        } else {
            bd.save(obj);
            return "O cliente "+ obj.getNome() + " foi atualizado com sucesso!";
        }
    }

    @GetMapping("/api/cliente/id/{id}")
    public Cliente carregarId(@PathVariable int id){
        Optional<Cliente> obj = bd.findById(id);
        if(obj.isPresent()){
            return obj.get();
        } else {
            return null;
        }
    }

    @GetMapping("/api/cliente/email/{email}")
    public Cliente carregarEmail(@PathVariable String email){
        Optional<Cliente> obj = bd.findByEmail(email);
        if(obj.isPresent()){
            return obj.get();
        } else {
            return null;
        }
    }

    @DeleteMapping("/api/cliente/id/{id}")
    public String removerId(@PathVariable int id){
        Optional<Cliente> obj = bd.findById(id);
        String nome = obj.get().nome;
        if(obj.isPresent()){
            bd.deleteById(id);
            return "Cadastro do cliente "+nome+" removido com sucesso!";
        } else {
            return "Cliente com id "+id+" não encontrado!";
        }
    }

    @DeleteMapping("/api/cliente/email/{email}")
    public String removerEmail(@PathVariable String email){
        Optional<Cliente> obj = bd.findByEmail(email);
        if(obj.isPresent()){
            String nome = obj.get().nome;
            int codigo = obj.get().id;
            bd.deleteById(codigo);
            return "Cadastro do cliente "+nome+" removido com sucesso!";
        } else {
            return "Cliente com email "+email+" não encontrado!";
        }
    }

    @GetMapping("/api/clientes")
    public List<Cliente> listar(){
        return bd.findAll();
    }

    @PostMapping ("/api/cliente/login")
    public Cliente fazerLogin(@RequestBody Cliente obj){
        Optional<Cliente> retorno = bd.login(obj.getEmail(), obj.getSenha());
        if(retorno.isPresent()){
            return retorno.get();
        } else {
            throw new ResponseStatusException(
                HttpStatus.UNAUTHORIZED, "Email ou senha inválidos"
            );
        }
    }

}
