package br.com.hoffmann.cadastrousuario.domain.response;

import br.com.hoffmann.cadastrousuario.domain.enums.TipoDeUsuario;
import br.com.hoffmann.cadastrousuario.entity.Usuario;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
public class UsuarioResponse implements Serializable {

    @ApiModelProperty(value = "Id")
    private Long id;

    @ApiModelProperty(value = "Nome")
    private String nome;

    @ApiModelProperty(value = "CPF")
    private String cpf;

    @ApiModelProperty(value = "Tipo de usuario")
    private TipoDeUsuario tipoDeUsuario;

    @ApiModelProperty(value = "Status")
    private Character status;

    @ApiModelProperty(value = "Estado")
    private String estado;

    @ApiModelProperty(value = "Sigla")
    private String sigla;

    @ApiModelProperty(value = "Cidade")
    private String cidade;

    @ApiModelProperty(value = "Rua")
    private String rua;

    @ApiModelProperty(value = "Numero")
    private Short numero;

    @ApiModelProperty(value = "Bairro")
    private String bairro;

    @ApiModelProperty(value = "Cep")
    private String cep;

    @ApiModelProperty(value = "Complemento")
    private String complemento;

    @ApiModelProperty(value = "Telefone")
    private String telefone;

    @ApiModelProperty(value = "Celular")
    private String celular;

    @ApiModelProperty(value = "Email")
    private String email;

    public UsuarioResponse() {
    }

    public UsuarioResponse(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.cpf = usuario.getCpf();
        this.tipoDeUsuario = usuario.getTipoDeUsuario();
        this.status = usuario.getStatus();
        this.estado = usuario.getEstado();
        this.sigla = usuario.getSigla();
        this.cidade = usuario.getCidade();
        this.rua = usuario.getRua();
        this.numero = usuario.getNumero();
        this.bairro = usuario.getBairro();
        this.cep = usuario.getCep();
        this.complemento = usuario.getComplemento();
        this.telefone = usuario.getTelefone();
        this.celular = usuario.getCelular();
        this.email = usuario.getEmail();
    }
}
