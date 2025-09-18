package pe.edu.upeu.venta.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pe.edu.upeu.venta.modelo.Usuario;
import pe.edu.upeu.venta.usuariorepository.UsuaRepository;

import jakarta.annotation.PostConstruct;
import java.util.Optional;

// @Service indica que esta clase contiene lógica de negocio (un servicio de la aplicación)
@Service
public class UsuaService {

    // Repositorio para acceder a la base de datos
    private final UsuaRepository usuaRepository;

    // BCrypt se usa para encriptar y verificar contraseñas de manera segura
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Constructor con inyección de dependencias: Spring pasa automáticamente el repositorio
    public UsuaService(UsuaRepository usuaRepository) {
        this.usuaRepository = usuaRepository;
    }

    // Método para registrar un nuevo usuario
    public Usuario register(String username, String rawPassword, String role) {
        // Se encripta la contraseña ingresada
        String hash = passwordEncoder.encode(rawPassword);
        // Se crea un objeto Usuario con id = null (se genera automáticamente en la BD)
        Usuario u = new Usuario(null, username, hash, role);
        // Se guarda en la base de datos y se devuelve
        return usuaRepository.save(u);
    }

    // Método para autenticar (validar usuario y contraseña)
    public boolean authenticate(String username, String rawPassword) {
        // Busca el usuario en la base de datos por su nombre
        Optional<Usuario> opt = usuaRepository.findByUsername(username);
        // Si no existe, retorna false
        if (opt.isEmpty()) return false;
        // Si existe, compara la contraseña ingresada con la encriptada en BD
        return passwordEncoder.matches(rawPassword, opt.get().getPasswordHash());
    }

    // @PostConstruct -> se ejecuta automáticamente después de crear el servicio
    @PostConstruct
    public void initDefaultUser() {
        // Si el usuario "admin" no existe en la BD...
        if (usuaRepository.findByUsername("admin").isEmpty()) {
            // ...se crea un usuario por defecto
            register("Juanito", "Juanitoxtlv", "ADMINISTRADOR");
            // Mensaje en consola para confirmar creación
            System.out.println("Default Juanito created -> username: Juanito password: Juanitoxtlv");
        }
    }
}
