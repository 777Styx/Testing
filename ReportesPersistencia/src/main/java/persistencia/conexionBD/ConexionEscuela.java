package persistencia.conexionBD;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

/**
 * Clase que gestiona la conexión a la base de datos MongoDB y proporciona métodos para abrir y cerrar la conexión, así como para obtener la base de datos de Escuela.
 * La clase utiliza la biblioteca oficial de MongoDB para Java.
 * 
 * @author Asiel Apodaca Monge
 */
public class ConexionEscuela {
    // Cadena de conexión a la instancia de MongoDB
    private static final String cadenaConexion = "mongodb://localhost:27017";
    // Nombre de la base de datos
    private static final String nombreBd = "OficinasDireccion";
    
    // Cliente de MongoDB
    private static MongoClient mongoClient;
    // Base de datos MongoDB
    private static MongoDatabase baseDeDatos;
    
    /**
     * Abre una conexión a la base de datos MongoDB.
     */
    public static void abrirConexion() {
        
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        MongoClientSettings configuracion = MongoClientSettings.builder().applyConnectionString(new ConnectionString(cadenaConexion)).codecRegistry(pojoCodecRegistry).build();
        
        mongoClient = MongoClients.create(configuracion);
        baseDeDatos = mongoClient.getDatabase(nombreBd);
    }
    
    /**
     * Obtiene la base de datos de Escuela. Si la conexión no está abierta, la abre antes de obtener la base de datos.
     * 
     * @return La base de datos de Escuela.
     */
    public static MongoDatabase obtenerBaseDeDatos() {
        if(baseDeDatos == null) {
            abrirConexion();
        }
        return baseDeDatos;
    }
    
    /**
     * Cierra la conexión al cliente de MongoDB.
     */
    public static void cerrarConexion() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }
}
