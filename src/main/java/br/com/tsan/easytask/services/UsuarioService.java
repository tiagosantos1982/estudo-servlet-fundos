package br.com.tsan.easytask.services;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.tsan.easytask.controller.UsuarioController;
import br.com.tsan.easytask.models.Usuario;

public class UsuarioService// implements Comparator<Usuario>{
{
    List<Usuario> listaUsuarios = UsuarioController.getInstance();
    public UsuarioService() {
        // TODO Auto-generated constructor stub
    }
    
    public List<Usuario> getTodosUsuarios( )
    {
        return listaUsuarios;
    }
    
    public List<Usuario> searchUsuariosByName( String nome )
    {
        
        Comparator<Usuario> groupByComparator = 
                Comparator.comparing(Usuario::getNome)
                          .thenComparing(Usuario::getNome);
        
        List<Usuario> result = listaUsuarios
                .stream ( )
                .filter ( e -> e.getNome().equalsIgnoreCase( nome ) )
                .sorted ( groupByComparator                         )
                .collect( Collectors.toList( )                      );
        
        return result;
    }

    //busca usuario por id
    public Usuario searchUsuarioById( long id ) throws Exception
    {
        Usuario usuario = new Usuario();
        
        Optional<Usuario> match =
                listaUsuarios
                .stream()
                .filter( e -> e.getId() == id )
                .findFirst( );
        if( match.isPresent( ))
        {
            return match.get( );
        }
        else
        {
            throw new Exception("The user is not found by id: " + id );
        }
    }
    
    //adiciona usuário a lista
    public Long adicionaUsuario( Usuario usr )
    {
        listaUsuarios.add( usr );
        return usr.getId();
    }

//    @Override
//    public int compare(Usuario o1, Usuario o2) {
//        // TODO Auto-generated method stub
//        return 0;
//    }
}
