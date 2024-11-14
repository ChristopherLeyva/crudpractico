CREADOR POR CHRISTOPHER LUCAS LEYVA CHUMPITAZ #22
---

### 1. **Configurar MySQL y Java**
   - **Base de datos y tabla**: Crea una base de datos `laboratorioCRUD` y una tabla `personaCrud`:

     ```sql
     CREATE DATABASE laboratorioCRUD;
     USE laboratorioCRUD;

     CREATE TABLE personaCrud (
         id INT AUTO_INCREMENT PRIMARY KEY,
         nombre VARCHAR(100),
         apellido VARCHAR(100),
         dni VARCHAR(100)
     );
     ```

   - **Java y MySQL JDBC**: Descarga el conector JDBC de MySQL y agrégalo al proyecto Java en NetBeans.

### 2. **Código para Conexión a la Base de Datos**

   ```java
   import java.sql.Connection;
   import java.sql.DriverManager;
   import java.sql.SQLException;

   public class ConexionDB {
       public static Connection getConnection() throws SQLException {
           return DriverManager.getConnection(
               "jdbc:mysql://localhost:3306/laboratorioCRUD", "root", "tu_contraseña");
       }
   }
   ```

### 3. **Operaciones CRUD en `PersonaDAO`**

   #### Crear (Agregar)
   ```java
   public void insertarPersona(String nombre, String apellido, String dni) throws SQLException {
       String query = "INSERT INTO personaCrud (nombre, apellido, dni) VALUES (?, ?, ?)";
       try (Connection conn = ConexionDB.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
           stmt.setString(1, nombre);
           stmt.setString(2, apellido);
           stmt.setString(3, dni);
           stmt.executeUpdate();
       }
   }
   ```

   #### Leer (Listar)
   ```java
   public void listarPersonas() throws SQLException {
       String query = "SELECT * FROM personaCrud";
       try (Connection conn = ConexionDB.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query)) {
           while (rs.next()) {
               System.out.println(rs.getInt("id") + ": " + rs.getString("nombre") +
                       " " + rs.getString("apellido") + " - DNI: " + rs.getString("dni"));
           }
       }
   }
   ```

   #### Actualizar
   ```java
   public void actualizarPersona(int id, String nombre, String apellido, String dni) throws SQLException {
       String query = "UPDATE personaCrud SET nombre = ?, apellido = ?, dni = ? WHERE id = ?";
       try (Connection conn = ConexionDB.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
           stmt.setString(1, nombre);
           stmt.setString(2, apellido);
           stmt.setString(3, dni);
           stmt.setInt(4, id);
           stmt.executeUpdate();
       }
   }
   ```

   #### Eliminar
   ```java
   public void eliminarPersona(int id) throws SQLException {
       String query = "DELETE FROM personaCrud WHERE id = ?";
       try (Connection conn = ConexionDB.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
           stmt.setInt(1, id);
           stmt.executeUpdate();
       }
   }
   ```

### 4. **Ejecución de Ejemplo en `Main`**
   ```java
   public static void main(String[] args) throws SQLException {
       PersonaDAO dao = new PersonaDAO();
       dao.insertarPersona("Juan", "Pérez", "12345678");
       dao.listarPersonas();
       dao.actualizarPersona(1, "Juan Carlos", "Pérez Gómez", "12345678");
       dao.eliminarPersona(1);
   }
   ```

### 5. **Diagramación en Excalidraw**
   - Realiza un diagrama simple mostrando las operaciones CRUD y la conexión a la base de datos.

### 6. **Entrega del Proyecto**
   - Graba un video mostrando la ejecución y resultados de cada operación (CRUD), incluyendo capturas del código y diagrama.

---
