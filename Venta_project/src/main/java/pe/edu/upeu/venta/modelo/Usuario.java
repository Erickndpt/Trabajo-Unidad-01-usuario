package pe.edu.upeu.venta.modelo;
import jakarta.persistence.*;
// Importamos anotaciones de Lombok para generar automáticamente código repetitivo
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// @Data = crea getters/setters, toString, equals, hashCode automáticamente
@Data
// @NoArgsConstructor = genera un constructor vacío (sin parámetros)
@NoArgsConstructor
// @AllArgsConstructor = genera un constructor con todos los atributos
@AllArgsConstructor
// @Entity = indica que esta clase será una entidad de base de datos (tabla)
@Entity
// @Table = especifica el nombre de la tabla en la base de datos
@Table(name = "users")
public class Usuario {

    // @Id = define que este campo es la clave primaria
    // @GeneratedValue = indica que el ID se genera automáticamente
    // strategy = GenerationType.IDENTITY → la BD se encarga de autoincrementar el ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identificador único del usuario

    // @Column = define características de la columna en la BD
    // unique = true → no permite que se repitan usuarios
    // nullable = false → este campo no puede quedar vacío
    @Column(unique = true, nullable = false)
    private String username; // Nombre de usuario

    // nullable = false → obligatorio
    @Column(nullable = false)
    private String passwordHash; // Contraseña del usuario (encriptada con hash)

    // nullable = false → obligatorio
    @Column(nullable = false)
    private String role; // Rol del usuario (ej: ADMIN, VENDEDOR, etc.)
}
