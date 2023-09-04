/**
 * 
 */
package br.com.tsan.easytask.controller;

import java.util.ArrayList;
import java.util.List;

import br.com.tsan.easytask.models.Usuario;

/**
 * Serviço de Tratamento de Usuário
 */
public class UsuarioController {

    private static final List<Usuario> usuarioList = new ArrayList<Usuario>();
    /**
     * Constructor
     */
    public UsuarioController() 
    {
    }
    
    static
    {
        usuarioList.add(new Usuario(1, "Alfred", "alfred.waynes@gmail.com", "", 1));
        usuarioList.add(new Usuario(2, "Gordon", "gordon.cheaf@gmail.com" , "", 2));
    }
    
    public static List<Usuario> getInstance( ) 
    {
        return usuarioList;
    }

}
