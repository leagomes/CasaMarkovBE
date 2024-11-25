package com.fatec.loja;

// import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;

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

    // @PutMapping("/api/pedido")
    // public String alterar(@RequestBody Pedido obj){
    //     //bd.save(obj);
    //     return "O item "+ obj.getId() + " foi alterado corretamente!";
    // }

    // @GetMapping("/api/pedido/{codigo}")
    // public Item carregar(@PathVariable int codigo){
    //    // Optional<Cliente> obj = bd.findById(codigo);
    //  //   if(obj.isPresent()){
    //   //      return obj.get();
    //   //  } else {
    //         return null;
    //  //   }
    // }

    // @DeleteMapping("/api/pedido/{codigo}")
    // public String remover(@PathVariable int codigo){
    //     //bd.deleteById(codigo);
    //     return "Registro "+ codigo + " removido com suceso!";
    // }

    // @GetMapping("/api/pedidos")
    // public List<Pedido> listar(){
    //    // return bd.findAll();
    //    return null;
    // }
}
