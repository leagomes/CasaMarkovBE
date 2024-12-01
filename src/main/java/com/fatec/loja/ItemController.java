package com.fatec.loja;

import java.util.List;
// import java.util.Optional;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    PedidoRepository bdPedido;

    @PostMapping("/api/item/{pedidoId}")
    public String gravar(@RequestBody List<Item> obj, @PathVariable int pedidoId){
        try {
            // Salva todos os itens recebidos no banco de dados
            Optional<Pedido> pdOptional = bdPedido.findById(pedidoId);
            Pedido pd;
            if (pdOptional.isPresent()) {
                pd = pdOptional.get();
            } else {
                // Trate o caso em que o pedido não foi encontrado
                throw new RuntimeException("Pedido com ID " + pedidoId + " não encontrado");
            }
            for (Item item : obj) {
                item.setPedido(pd);
            }
            bd.saveAll(obj);
            return "Itens adicionados com sucesso!";
        } catch (Exception e) {
            System.out.println("Erro ao adicionar os itens: "+e);
            return "Erro ao adicionar itens. ";
        }
    }
}
