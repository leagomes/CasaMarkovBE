package com.fatec.loja;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
            Optional<Pedido> pdOptional = bdPedido.findById(pedidoId);
            Pedido pd;
            if (pdOptional.isPresent()) {
                pd = pdOptional.get();
            } else {
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
