package com.example.ApiMedioDePago;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.ApiMedioDePago.Model.MedioDePago;

@SpringBootTest
public class TestMedioDePago {

      @Test
    void testCrearMedioDePago(){

        //Creacion de objeto
        MedioDePago MedioDepago = new MedioDePago();
        
        //Seteo de atributos
        MedioDepago.setId_medio_de_pago(1);
        MedioDepago.setActivo(  true);
        MedioDepago.setNombre_proveedor("Banco estado");
        MedioDepago.setTipo("Debito");
        MedioDepago.setDetalles("Tarjeta unica");

        //Confirmacion de seteo (gets)
        assertEquals(1, MedioDepago.getId_medio_de_pago());
        //Atributo Booleano , llamar con is y sin contenido dentro del parentesis
        assertTrue(MedioDepago.isActivo());
        assertEquals("Banco estado", MedioDepago.getNombre_proveedor());
        assertEquals("Debito", MedioDepago.getTipo());
        assertEquals("Tarjeta unica", MedioDepago.getDetalles());

        System.out.println("Test Realizado exitosamente");
    }

}
