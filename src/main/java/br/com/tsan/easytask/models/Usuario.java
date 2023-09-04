package br.com.tsan.easytask.models;

import java.util.concurrent.atomic.AtomicLong;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
public class Usuario {

    private static final AtomicLong counter = new AtomicLong(100);
    @Getter @Setter
    private long   id;
    @Getter @Setter
    private String nome;
    @Getter @Setter
    private String email;
    @Getter @Setter
    private String descricao;
    @Getter @Setter
    private long   idLevel;
    
    public Usuario() {

    }
    
    public Usuario(long id, String nome, String email, String descricao, long idLevel) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
        this.idLevel = idLevel;
    }
    
    public void Add( String nome, String email, String descricao, long idLevel ) 
    {
        this.id = counter.incrementAndGet();
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
        this.idLevel = idLevel;
    }

}
