package br.com.tsan.easytask.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.tsan.easytask.models.Usuario;
import br.com.tsan.easytask.services.UsuarioService;


@WebServlet("/usuario")
public class UsuarioServlet extends HttpServlet 
{

    /**
     * serialização
     */
    private static final long serialVersionUID = 1L;
    private UsuarioService usuarioServico = new UsuarioService( );

    public UsuarioServlet() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        //super.doGet(req, resp);
        String filter = req.getParameter( "searchAction" );
        
        if( filter != null )
        {
            switch( filter )
            {
                case "searchById":
                    searchById(req, resp);
                    break;
                case "searchByName":
                    searchByName(req, resp);
                    break;
                case "reset":
                    resetForm(req,resp);
            }
        }
        else
        {
            resetForm(req,resp);
        }
    }
    
    /**
     * método de inicialização do forme de listagem
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void resetForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        List<Usuario> result = usuarioServico.getTodosUsuarios();
        forwardListUsuarios(req, resp, result);
    }
    
    /**
     * busca usuario por id
     * @param req
     * @param resp
     */
    private void searchById( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException
    {
        long idUsuario = Integer.valueOf(req.getParameter("userId"));
        Usuario usuario = null;
        
        try
        {
            usuario = usuarioServico.searchUsuarioById(idUsuario);
        }
        catch( Exception ex )
        {
            Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex );
        }
        
        req.setAttribute("userList",usuario);
        req.setAttribute("action" ,"edit" );
        String nextJSP = "/jsp/novo-usuario.jsp";
        
        RequestDispatcher rd = getServletContext().getRequestDispatcher(nextJSP);
        rd.forward(req, resp);
        
    }
    
    /**
     * busca usuario por nome
     * @param req
     * @param resp
     */
    private void searchByName( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException
    {
        String nomeUsuario = req.getParameter("userName");
        List<Usuario> usuarioList = new ArrayList<Usuario>();

        usuarioList = usuarioServico.searchUsuariosByName(nomeUsuario);

        forwardListUsuarios(req,resp,usuarioList);
    }
    
    private void forwardListUsuarios(HttpServletRequest req,HttpServletResponse resp, List<Usuario> usuarioList )
    throws ServletException, IOException
    {
        String nextJSP = "/pages/usuario-lista.jsp";
        //String nextJSP = "/pages/novo-usuario.jsp";
        
        RequestDispatcher rd = getServletContext().getRequestDispatcher(nextJSP);
        req.setAttribute("userList", usuarioList);
        rd.forward(req, resp);
    }

    //+===============================================================
    // Métodos ligados a Post
    //+===============================================================
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch( action )
        {
            case "add":
                addUserAction(req,resp);
                break;
            case "edit":
                break;
            case "remove":
                break;
        }
    }
    
    private void addUserAction(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException
    {
        String email = req.getParameter("email"    );
        String nome  = req.getParameter("nome"     );
        String descr = req.getParameter("descricao");
        
        Usuario usr = new Usuario( );
        
        usr.Add(nome, email, descr, 1);
        
        long idUsuario = usuarioServico.adicionaUsuario(usr);
        
        List<Usuario> lista = usuarioServico.getTodosUsuarios();
        
        String mensagem = "O novo usuário foi incluído com sucesso!!!";
        req.setAttribute("message", mensagem);
        
        forwardListUsuarios(req,resp,lista);
        
    }
    
}
