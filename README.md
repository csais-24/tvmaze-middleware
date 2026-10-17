# TVMaze Middleware

Middleware desarrollado con Java y Spring Boot que consume la API de TVMaze y permite consultar series, guardar resultados en MongoDB y agregar comentarios con calificaciones.

## Tecnologías

- Java 21
- Spring Boot
- MongoDB Atlas
- TVMaze API
- Maven

## Configuración

Necesitas Java 21 y una base de datos MongoDB.

Configura la variable de entorno `MONGODB_URI` con tu cadena de conexión a MongoDB Atlas.

Después, ejecuta el proyecto desde la raíz:

```bash
./mvnw spring-boot:run
```

En Windows puedes usar:

```powershell
.\mvnw.cmd spring-boot:run
```

La API estará disponible en `http://localhost:8080`.

## Endpoints

| Método | Endpoint | Descripción |
|---|---|---|
| GET | `/api/shows/search?q=breaking` | Busca series e incluye sus comentarios. |
| GET | `/api/shows/{id}` | Consulta una serie por ID. |
| POST | `/api/shows/{id}/comments` | Agrega un comentario y una calificación. |

Para agregar un comentario, envía un JSON como este:

```json
{
  "comment": "Excelente serie",
  "rating": 5
}
```

La calificación debe estar entre 0 y 5.

## Caché

Al consultar una serie por ID, primero se busca en MongoDB. Si no existe en caché, se consulta TVMaze y se guarda la respuesta para futuras consultas.

Los comentarios se almacenan en MongoDB y se incluyen tanto en las búsquedas como en las consultas por ID.

## API externa

Documentación de TVMaze: https://www.tvmaze.com/api