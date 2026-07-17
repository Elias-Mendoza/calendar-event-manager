# Gestor de Calendario y Eventos (TP Algoritmos y programación III)

Proyecto universitario desarrollado para la materia Algoritmos y Programación III (FIUBA). El objetivo del trabajo fue aplicar los fundamentos de la Programación Orientada a Objetos (POO), patrones de diseño y buenas prácticas de desarrollo en Java.

## 🚀 Características Principales
* **Gestión de Eventos y Tareas:** Creación de actividades de día completo o con fecha/hora específica.
* **Sistema de Repeticiones:** Lógica de recurrencia personalizable (frecuencia diaria, semanal, mensual, anual) con límites por cantidad o hasta una fecha tope.
* **Sistema de Alarmas:** Implementación de alarmas dinámicas que se actualizan automáticamente al modificar la fecha o el horario de los eventos.
* **Persistencia de Datos (I/O):** Serialización y deserialización del calendario completo utilizando flujos de entrada/salida (I/O Streams) para guardar y cargar el estado de la aplicación.
* **Manejo de Excepciones:** Implementación de excepciones personalizadas (ej. `CalendarioNoGuardadoException`) para controlar errores de persistencia.

## 🛠️ Tecnologías y Prácticas Aplicadas
* **Lenguaje:** Java
* **Arquitectura:** Diseño fuertemente orientado a objetos (Herencia, Polimorfismo, Clases Abstractas).
* **Testing:** Desarrollo guiado por pruebas unitarias (TDD) para validar la lógica de fechas, alarmas y serialización.
* **Control de Versiones:** Uso de Git con flujo de trabajo basado en ramas y Pull Requests (Etapa 1, Etapa 2).
* **Gestor de Dependencias:** Maven.
