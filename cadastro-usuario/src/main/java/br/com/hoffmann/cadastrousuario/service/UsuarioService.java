package br.com.hoffmann.cadastrousuario.service;

import br.com.hoffmann.cadastrousuario.domain.request.UsuarioRequest;
import br.com.hoffmann.cadastrousuario.domain.response.UsuarioResponse;
import br.com.hoffmann.cadastrousuario.entity.Usuario;
import br.com.hoffmann.cadastrousuario.repository.UsuarioRepository;
import br.com.hoffmann.cadastrousuario.service.auxiliar.ServiceAuxiliar;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@Transactional(value = "transactionManager")
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ServiceAuxiliar auxiliar;

    public void createUsuario(UsuarioRequest request) {
        auxiliar.validaSePessoaExiste(request);
        auxiliar.criaUsuario(request);
    }

    public void deletaUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    public void updateUsuario(UsuarioRequest request, Long id) throws NotFoundException {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (!usuario.isPresent()) {
            throw new NotFoundException(format("ID [%s] nao encontrado.", id));
        }
        auxiliar.updateExceptions(request, id);
        auxiliar.atualizaUsuario(usuario.get());
    }

    public UsuarioResponse buscaUsuario(Long id) throws NotFoundException {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (!usuario.isPresent())
            throw new NotFoundException("Usuario não encontrado!!!");
        return new UsuarioResponse(usuario.get());
    }

    public List<UsuarioResponse> buscaUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream().map(UsuarioResponse::new).collect(Collectors.toList());
    }
}
