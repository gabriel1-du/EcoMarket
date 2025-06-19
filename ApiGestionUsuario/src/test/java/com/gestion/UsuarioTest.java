package com.gestion;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.gestion.models.Usuario;


@SpringBootTest
public class UsuarioTest {
    

    @Test
    void testCreacionDeUsuario(){

        //Creacion del objeto tipo usuario
        Usuario usu = new Usuario();


        //Seteo de atributos al objeto
        usu.setApellido("Oliveira");
        usu.setContrasena("Pichilemu");
        usu.setDireccion("Machupichu");
        usu.setEmail("hola@gmail.com");
        usu.setEstado("Activo");
        usu.setIdUsuario(1);
        usu.setNombreUsuario("Maria");
        usu.setRol("Admin");
        usu.setTelefono("111111");

           // Verificaciones con assertEquals
        assertEquals("Oliveira", usu.getApellido());
        assertEquals("Pichilemu", usu.getContrasena());
        assertEquals("Machupichu", usu.getDireccion());
        assertEquals("hola@gmail.com", usu.getEmail());
        assertEquals("Activo", usu.getEstado());
        assertEquals(1, usu.getIdUsuario());
        assertEquals("Maria", usu.getNombreUsuario());
        assertEquals("Admin", usu.getRol());
        assertEquals("111111", usu.getTelefono());

    }
    
}
