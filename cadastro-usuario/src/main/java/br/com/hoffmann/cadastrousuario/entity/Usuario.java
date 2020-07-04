package br.com.hoffmann.cadastrousuario.entity;

import br.com.hoffmann.cadastrousuario.domain.enums.TipoDeUsuario;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Table(name = "USUARIO")
public class Usuario {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_USUARIO")
    @SequenceGenerator(sequenceName = "SQ_USUARIO", allocationSize = 1, name = "SQ_USUARIO")
    @Column(name = "ID")
    private Long id;

    @Column(length = 50, name = "NOME")
    private String nome;

    @Column(length = 14, name = "CPF")
    private String cpf;

    @Column(length = 1, name = "TIPO_DE_USUARIO")
    private TipoDeUsuario tipoDeUsuario;

    @Column(length = 1, name = "STATUS")
    private Character status;

    @Column(length = 30, name = "ESTADO")
    private String estado;

    @Column(length = 2, name = "SIGLA")
    private String sigla;

    @Column(length = 50, name = "CIDADE")
    private String cidade;

    @Column(length = 100, name = "RUA")
    private String rua;

    @Column(name = "NUMERO")
    private Short numero;

    @Column(length = 30, name = "BAIRRO")
    private String bairro;

    @Column(length = 10, name = "CEP")
    private String cep;

    @Column(length = 20, name = "COMPLEMENTO")
    private String complemento;

    @Column(length = 10, name = "TELEFONE")
    private String telefone;

    @Column(length = 11, name = "CELULAR")
    private String celular;

    @Column(length = 100, name = "EMAIL")
    private String email;
}
