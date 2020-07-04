package br.com.hoffmann.cadastrousuario.controller;

import br.com.hoffmann.cadastrousuario.domain.request.UsuarioRequest;
import br.com.hoffmann.cadastrousuario.domain.response.UsuarioResponse;
import br.com.hoffmann.cadastrousuario.service.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Api(value = "Cadastro de usuarios - endPoints API", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@Validated
@RequestMapping(path = "/v1/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @ApiOperation(value = "EndPoint para cadastro de usuario")
    @RequestMapping(value = "/cadastraUsuario", method = RequestMethod.POST)
    @Validated(UsuarioRequest.createRequest.class)
    public ResponseEntity<UsuarioResponse> cadastraUsuario(
            @RequestBody @Valid UsuarioRequest request) {
        usuarioService.createUsuario(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "EndPoint para exclusão de usuario")
    @RequestMapping(value = "/deletaUsuario/{id}", method = RequestMethod.DELETE)
    public void deletaUsuario(
            @PathVariable(value = "id") @NotNull Long id) throws NotFoundException {
        usuarioService.deletaUsuario(id);
    }

    @ApiOperation(value = "EndPoint para alteração de informações dos usuario")
    @RequestMapping(value = "/updateUsuario/{id}", method = RequestMethod.PUT)
    @Validated(UsuarioRequest.updateRequest.class)
    public void updateUsuario(
            @PathVariable(value = "id") @NotNull Long id, String telefone, String celular,
            @RequestBody @Valid UsuarioRequest request) throws NotFoundException {
        usuarioService.updateUsuario(request, id);
    }

    @ApiOperation(value = "EndPoint para fazer a busca de usuarios")
    @RequestMapping(value = "/buscaUsuarios", method = RequestMethod.GET)
    public ResponseEntity<List<UsuarioResponse>> buscaUsuarios() throws NotFoundException {
        List<UsuarioResponse> response = usuarioService.buscaUsuarios();
        return ResponseEntity.ok().body(response);
    }

    @ApiOperation(value = "EndPoint para fazer a busca de um usuario por ID")
    @RequestMapping(value = "/buscaUsuario/{id}", method = RequestMethod.GET)
    public ResponseEntity<UsuarioResponse> buscaUsuario(@PathVariable(value = "id") Long id) throws NotFoundException {
        return ResponseEntity.ok().body(usuarioService.buscaUsuario(id));
    }
}
