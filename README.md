# Sistema de Contenido Audiovisual

Proyecto de Programación Orientada a Objetos, Unidad 2 (Relación entre clases).
Universidad Politécnica Salesiana.

## Objetivo y propósito

Ampliar un proyecto Java de contenido audiovisual aplicando herencia, asociación,
agregación y composición. El sistema permite registrar películas, series, documentales,
animes y tráilers, y gestionar los actores, temporadas e investigadores que se
relacionan con ellos, desde un menú por consola.

## Clases y funcionalidades añadidas

### Clases de la Etapa 2

| Clase | Relación | Con | Descripción |
|---|---|---|---|
| `Actor` | Agregación | `Pelicula`, `Anime` | Un actor existe por sí solo y puede estar en varios contenidos. |
| `Temporada` | Composición | `SerieDeTV`, `Anime` | La temporada no existe sin su serie o anime; estos la crean internamente. |
| `Investigador` | Asociación | `Documental` | El investigador colabora con un documental, pero ambos son independientes. |

### Subclases nuevas de la Etapa 4

| Clase | Hereda de | Relaciones |
|---|---|---|
| `Anime` | `ContenidoAudiovisual` | Composición con `Temporada`, agregación con `Actor` (actores de voz) |
| `Trailer` | `ContenidoAudiovisual` | Asociación con `Pelicula` |

### Clase de gestión

`Catalogo` guarda los contenidos, actores e investigadores, y ofrece búsqueda,
filtrado por tipo y eliminación.

### Funcionalidades del menú (`SistemaAudioVisual`)

- Crear películas, series, documentales, animes y tráilers.
- Registrar actores e investigadores.
- Agregar actores a películas y animes, temporadas a series y animes, e investigadores a documentales.
- Listar, buscar por título, ver detalles y eliminar contenido.
- Ver estadísticas y cargar datos de ejemplo.

## Estructura del proyecto

```
poo_unidad1/
├── src/
│   ├── poo/
│   │   ├── PruebaAudioVisual.java
│   │   └── SistemaAudioVisual.java
│   └── uni1a/
│       ├── ContenidoAudiovisual.java
│       ├── Pelicula.java
│       ├── SerieDeTV.java
│       ├── Documental.java
│       ├── Anime.java
│       ├── Trailer.java
│       ├── Actor.java
│       ├── Temporada.java
│       ├── Investigador.java
│       └── Catalogo.java
├── .gitignore
└── README.md
```

## Cómo clonar y ejecutar

Requisitos: JDK 11 o superior y Eclipse.

1. En Eclipse: **File → Import → Git → Projects from Git → Clone URI**.
2. Pega `https://github.com/Alexander02GuaCAM/poo-contenido-audiovisual.git` y completa el asistente, eligiendo la rama `master`.
3. Clic derecho en `src/poo/SistemaAudioVisual.java` → **Run As → Java Application**.
4. Haz clic dentro de la consola de Eclipse para escribir las opciones del menú.

## Ejemplo de uso

Al ejecutar, usa la opción `16` para cargar datos de ejemplo y la `11` para listar los contenidos.
Con la opción `12` puedes ver los detalles de cada uno, incluidos el reparto, las temporadas
o los investigadores.

## Mejoras adicionales

- Clase `Catalogo` que separa la lógica de gestión de la interfaz de consola.
- Validación de entradas: números inválidos, valores fuera de rango, campos vacíos y duplicados.
- Búsqueda por título sin distinguir mayúsculas.
- Al eliminar una película, sus tráilers se conservan sin película asociada (asociación).
- Datos de ejemplo y estadísticas generales.

## Autor

Nombre: Alexander Guaman
Carrera: Ingenieria en Software
Asignatura: Programación Orientada a Objetos