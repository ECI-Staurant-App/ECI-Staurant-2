package edu.eci.arsw.ecistaurant.security;

import edu.eci.arsw.ecistaurant.model.Usuario;
import edu.eci.arsw.ecistaurant.persistence.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {


        Usuario usuario = usuarioRepository.findByEmail(email);

        if (logger == null){
            logger.error("Error en el login, no existe el usuario "+ email+ " en el sistema");
            throw new UsernameNotFoundException("Error en el login, no existe el usuario "+ email+ " en el sistema");
        }
        List<GrantedAuthority> authorities = usuario.getRoles()
                .stream()
                .map(rol -> new SimpleGrantedAuthority(rol.getNombre()))
                .peek(authority -> logger.info("Rol: " + authority.getAuthority()))
                .collect(Collectors.toList());

        return new User(usuario.getEmail(),usuario.getPasswd(),usuario.getEnabled(),true,true,true,authorities);
    }
}
