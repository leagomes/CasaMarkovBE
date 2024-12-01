package com.fatec.loja;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class PedidoController {
  @Autowired
  PedidoRepository bd;

 @PostMapping("/api/pedido")
    public int gravar(@RequestBody Pedido obj){
        bd.save(obj);
        return obj.getId();
    }
}
