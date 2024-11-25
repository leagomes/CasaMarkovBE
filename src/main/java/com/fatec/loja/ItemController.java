package com.fatec.loja;

import java.util.List;
// import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PutMapping;

@CrossOrigin(origins = "*")
@RestController
public class ItemController {
    @Autowired
    ItemRepository bd;

    @PostMapping("/api/item")
    public ResponseEntity<String> gravar(@RequestBody List<Item> obj){
    try {
        // Salva todos os itens recebidos no banco de dados
        for (Item item : obj) {
            System.out.println(item.getProduto().getId());
            bd.save(item); 
        }
        return ResponseEntity.ok("Itens adicionados com sucesso!");
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao adicionar itens: " + e.getMessage());
    }
    }

    // @PutMapping("/api/item")
    // public String alterar(@RequestBody Item obj){
    //     //bd.save(obj);
    //     return "O item "+ obj.getId() + " foi alterado corretamente!";
    // }

    // @GetMapping("/api/item/{codigo}")
    // public Item carregar(@PathVariable int codigo){
    //     Optional<Item> obj = bd.findById(codigo);
    //     if(obj.isPresent()){
    //         return obj.get();
    //     } else {
    //         return null;
    //     }
    // }

    // @DeleteMapping("/api/item/{codigo}")
    // public String remover(@PathVariable int codigo){
    //     //bd.deleteById(codigo);
    //     return "Registro "+ codigo + " removido com suceso!";
    // }

    // @GetMapping("/api/itens")
    // public List<Item> listar(){
    //    // return bd.findAll();
    //    return null;
    // }
}
