# Juego de Colores - Android Studio (Kotlin)

## Descripción

**Juego de Colores** es una aplicación desarrollada en **Kotlin con Android Studio**, cuyo objetivo es **presionar el botón que coincida con el color mostrado en pantalla** antes de que se acabe el tiempo.  

Durante la partida, el usuario gana puntos por cada acierto, y al finalizar puede ver su **puntaje final**, **puntaje más alto histórico** (almacenado con `SharedPreferences`), y un **historial de partidas de la sesión** mediante un `RecyclerView`.

---

## Características principales

### WelcomeFragment
- Muestra el título del juego.
- Incluye un **AlertDialog** con las reglas del juego.
- Botón para **iniciar la partida**.

### GameFragment
- Muestra un **color aleatorio** que el jugador debe adivinar.
- Botones con distintos colores para responder.
- Temporizador de **30 segundos** (`CountDownTimer`).
- Muestra **puntaje actual** y **tiempo restante**.
- Al finalizar el tiempo, se navega a la pantalla de resultados mediante un **AlertDialog**.
- **Efectos de sonido**:
  -  `correct.mp3`: sonido de acierto.
  -  `wrong.mp3`: sonido de error.
  -  `time_over.mp3`: sonido al terminar el tiempo.

### ResultFragment
- Muestra el **puntaje final**.
- Muestra el **puntaje más alto histórico** usando `SharedPreferences`.
- Muestra un **historial de puntajes** (sin persistencia en BD).
- Botón para **volver a jugar**.
- Botón para **salir**.

---

## Funcionalidades implementadas

- Navegación entre fragments usando **Navigation Component**.  
- Manejo de ciclo de vida y lógica con `onViewCreated` y `onDestroyView`.  
- Temporizador con **CountDownTimer**.  
- Gestión de sonidos con **MediaPlayer**.  
- Persistencia simple con **SharedPreferences**.  
- Interfaz diseñada con **ConstraintLayout**.  
- Uso de recursos (`strings.xml`, `colors.xml`, `raw/`).  

---

## Tecnologías y librerías

- **Lenguaje:** Kotlin  
- **IDE:** Android Studio  
- **UI:** ConstraintLayout  
- **Componentes:**  
  - Navigation Component  
  - RecyclerView  
  - AlertDialog  
  - CountDownTimer  
  - MediaPlayer  
  - SharedPreferences  

---

## Ejecución

### Usando GitHub Desktop y Android Studio
1. Clonar el repositorio desde GitHub utilizando GitHub Desktop.  
   - Seleccionar **File > Clone Repository** y ubicar el proyecto en la carpeta de trabajo local.
2. Abrir Android Studio y seleccionar **Open Project**.
3. Esperar a que Gradle sincronice las dependencias automáticamente.
4. Conectar un dispositivo físico o abrir un emulador de Android.
5. Ejecutar la aplicación con el botón **Run** en Android Studio.
6. Probar las funcionalidades.


---

## Autor
- Nombre: Juan José Huamaní Vásquez
- Fecha: 19/10/2025  


