# 📦 Alfolí – Sistema de Gestión de Inventario Solidario

**Alfolí** es una aplicación backend desarrollada con **Java + Spring Boot**, orientada a organizaciones como iglesias o fundaciones que desean llevar un control ordenado de productos donados y entregados. Se pueden registrar entradas de productos, salidas hacia beneficiarios y solicitudes de necesidades.

> 🎯 Proyecto en desarrollo — Segunda aplicación backend de mi portafolio profesional.

---

## 🚀 Tecnologías Utilizadas

- 🧠 Java 17
- 🌐 Spring Boot 3
- 💾 PostgreSQL
- 🧩 Spring Data JPA + Hibernate
- 📋 Jakarta Bean Validation
- 🔁 API RESTful bien estructurada
- 🧪 Postman para pruebas
- 📦 Arquitectura en capas (`controller`, `service`, `repository`, `entity`, `dto`, `enum`)

---

## 📚 Módulos Implementados

### 🛒 Productos
- CRUD completo
- Registro con nombre, categoría, unidad de medida y stock.
- Relación con entradas, salidas y necesidades.
- Actualización automática del stock.

### 📥 Entradas
- Registro de productos recibidos.
- Aumento automático del stock.
- Guardado de detalles y responsable de entrega.

### 📤 Salidas
- Registro de productos entregados a familias u organizaciones.
- Reducción automática del stock.
- Incluye observación y responsable.

### 📋 Necesidades
- Registro de productos solicitados.
- Prioridad (`ALTA`, `MEDIA`, `BAJA`) y estado (`PENDIENTE`, `CUMPLIDA`).
- Observaciones y control de cumplimiento.
- Filtros por estado o prioridad.

---

## 🛠️ Funcionalidades en Desarrollo

- [ ] Editar y eliminar necesidades
- [ ] Historial por producto (entradas/salidas)
- [ ] Dashboard resumen (productos en stock, movimientos)
- [ ] Control de vencimientos o fechas sugeridas
- [ ] Frontend con Angular (en planificación)

---

## 🧭 Estructura de Paquetes

```
com.alfoli.alfoli
├── controller        # Controladores REST
├── dto              # Clases para entrada/salida de datos
├── entity           # Entidades JPA
├── repository       # Interfaces JPA Repositories
├── service          # Lógica de negocio
├── enum             # Enumeraciones (Prioridad, Estado, Categoría)
└── AlfoliApplication.java
```

---

## 📬 Ejemplo de entrada JSON

```json
{
  "fecha": "2025-06-15",
  "entregadoPor": "Hermano Juan",
  "detalles": [
    {
      "productoId": 1,
      "cantidad": 10
    },
    {
      "productoId": 2,
      "cantidad": 5
    }
  ]
}
```

---

## 📊 Ejemplo de necesidad

```json
{
  "productoSolicitado": "Leche",
  "cantidad": 3,
  "prioridad": "ALTA",
  "observaciones": "Familia en situación crítica"
}
```

---

## 🎯 Objetivo del Proyecto

Crear un sistema sólido, con buenas prácticas de backend, que ayude a organizaciones solidarias a gestionar eficientemente los recursos que reciben y entregan.

Incluye validaciones, estructura limpia, manejo de relaciones entre entidades y expansión futura al frontend con Angular.

---

## 👨‍💻 Autor

**Oscar González**  
_Ingeniero en Informática en formación_  
📫 [LinkedIn](https://www.linkedin.com)  
📁 Este proyecto forma parte de mi portafolio profesional

---

## 🧠 Licencia

© 2025 Oscar González. Todos los derechos reservados.