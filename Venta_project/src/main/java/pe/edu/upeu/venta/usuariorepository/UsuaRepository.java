package pe.edu.upeu.venta.usuariorepository;

// Importamos lo necesario
import org.springframework.data.jpa.repository.JpaRepository; // Proporciona métodos CRUD listos (guardar, buscar, eliminar, etc.)
import pe.edu.upeu.venta.modelo.Usuario; // Importamos la entidad Usuario (tabla en la BD)

import java.util.Optional; // Manejo seguro de valores que pueden ser nulos

// Repositorio que maneja la entidad Usuario
// Extiende de JpaRepository<Entidad, TipoID> -> en este caso Usuario y su ID (Long)
public interface UsuaRepository extends JpaRepository<Usuario, Long> {

    // Método personalizado: busca un usuario por su nombre de usuario
    // Optional<Usuario> -> puede devolver un Usuario encontrado o vacío (si no existe)
    Optional<Usuario> findByUsername(String username);
}
