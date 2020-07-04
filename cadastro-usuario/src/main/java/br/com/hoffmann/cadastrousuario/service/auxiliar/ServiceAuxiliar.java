package br.com.hoffmann.cadastrousuario.service.auxiliar;


import br.com.hoffmann.cadastrousuario.domain.request.UsuarioRequest;
import br.com.hoffmann.cadastrousuario.domain.response.UsuarioResponse;
import br.com.hoffmann.cadastrousuario.entity.Usuario;
import br.com.hoffmann.cadastrousuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.NonUniqueResultException;
import java.util.Optional;

import static java.lang.String.format;

@Component
public class ServiceAuxiliar {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void criaUsuario(UsuarioRequest request) {
        Usuario usuario = new Usuario();
        usuario.setNome(request.getNome());
        usuario.setCpf(request.getCpf());
        usuario.setTipoDeUsuario(request.getTipoDeUsuario());
        usuario.setStatus(request.getStatus());
        usuario.setEstado(request.getEstado());
        usuario.setSigla(request.getSigla());
        usuario.setCidade(request.getCidade());
        usuario.setRua(request.getRua());
        usuario.setNumero(request.getNumero());
        usuario.setBairro(request.getBairro());
        usuario.setCep(request.getCep());
        usuario.setComplemento(request.getComplemento());
        usuario.setTelefone(request.getTelefone());
        usuario.setCelular(request.getCelular());
        usuario.setEmail(request.getEmail());

        usuarioRepository.save(usuario);
    }

    public UsuarioResponse atualizaUsuario(Usuario usuario) {
        UsuarioResponse response = new UsuarioResponse();

        response.setId(usuario.getId());
        response.setNome(usuario.getNome());
        response.setCpf(usuario.getCpf());
        response.setTipoDeUsuario(usuario.getTipoDeUsuario());
        response.setStatus(usuario.getStatus());
        response.setEstado(usuario.getEstado());
        response.setSigla(usuario.getSigla());
        response.setCidade(usuario.getCidade());
        response.setRua(usuario.getRua());
        response.setNumero(usuario.getNumero());
        response.setBairro(usuario.getBairro());
        response.setCep(usuario.getCep());
        response.setComplemento(usuario.getComplemento());
        response.setTelefone(usuario.getTelefone());
        response.setCelular(usuario.getCelular());
        response.setEmail(usuario.getEmail());

        return response;
    }

    public void updateExceptions(UsuarioRequest request, Long id) {

        Optional<Usuario> verificaCpf = verificaCpf(request);
        Optional<Usuario> verificaEmail = verificaEmail(request);
        Optional<Usuario> verificaTelefone = verificaTelefone(request);
        Optional<Usuario> verificaCelular = verificaCelular(request);

        if (verificaCpf.isPresent() && !verificaCpf.get().getId().equals(id)) {
            throw new NonUniqueResultException(format("Já temos o CPF [%s] cadastrado em nossa base.", request.getCpf()));
        } else if (verificaTelefone.isPresent() && !verificaTelefone.get().getId().equals(id)) {
            throw new NonUniqueResultException(format("Já temos o TELEFONE [%s] cadastrado em nossa base.", request.getTelefone()));
        } else if (verificaCelular.isPresent() && !verificaCelular.get().getId().equals(id)) {
            throw new NonUniqueResultException(format("Já temos o CELULAR [%s] cadastrado em nossa base.", request.getCelular()));
        } else if (verificaEmail.isPresent() && !verificaEmail.get().getId().equals(id)) {
            throw new NonUniqueResultException(format("Já temos o EMAIL [%s] cadastrado em nossa base.", request.getEmail()));
        }
    }

    public void validaSePessoaExiste(UsuarioRequest request) {

        Optional<Usuario> verificaCpf = verificaCpf(request);
        Optional<Usuario> verificaEmail = verificaEmail(request);
        Optional<Usuario> verificaTelefone = verificaTelefone(request);
        Optional<Usuario> verificaCelular = verificaCelular(request);

        if (verificaCpf.isPresent()) {
            throw new NonUniqueResultException(format("CPF [%s] já cadastrado.", request.getCpf()));
        } else if (verificaEmail.isPresent()) {
            throw new NonUniqueResultException(format("EMAIL [%s] já cadastrado.", request.getEmail()));
        } else if (verificaTelefone.isPresent()) {
            throw new NonUniqueResultException(format("TELEFONE [%s] já cadastrado.", request.getTelefone()));
        } else if (verificaCelular.isPresent()) {
            throw new NonUniqueResultException(format("CELULAR [%s] já cadastrado.", request.getCelular()));
        }
    }

    private Optional<Usuario> verificaCpf(UsuarioRequest request) {
        return usuarioRepository.findPessoaByCpf(request.getCpf());
    }

    private Optional<Usuario> verificaEmail(UsuarioRequest request) {
        return usuarioRepository.findPessoaByEmail(request.getEmail());
    }

    private Optional<Usuario> verificaTelefone(UsuarioRequest request) {
        return usuarioRepository.findPessoaByTelefone(request.getTelefone());
    }

    private Optional<Usuario> verificaCelular(UsuarioRequest request) {
        return usuarioRepository.findPessoaByCelular(request.getCelular());
    }
}
