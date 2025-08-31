#  Proyecto Final - Microservicios con Spring Cloud (TodoCode Academy)

Este proyecto fue desarrollado como **trabajo integrador final** del curso **Microservicios con Spring Cloud** de TodoCode Academy.  
El objetivo fue aplicar los conceptos de **arquitectura de microservicios**, **comunicación entre servicios** y **patrones de resiliencia** para una **tienda online de electrodomésticos**.

---

## Objetivo
Se desarrollaron **tres microservicios independientes** que se comunican entre sí para simular la operatoria de una tienda online:

1. **Microservicio de Productos**  
   - Gestiona el catálogo de electrodomésticos disponibles.  
   - CRUD de productos con atributos: `código`, `nombre`, `marca`, `precio`.

2. **Microservicio de Carrito de Compras**  
   - Maneja los carritos de cada usuario.  
   - Permite agregar o quitar productos.  
   - Calcula el **total** en base a los productos cargados.

3. **Microservicio de Ventas**  
   - Registra ventas con `id` y `fecha`.  
   - Cada venta está asociada a un carrito.  
   - Obtiene el total y los productos consultando al microservicio de **Carrito**, que a su vez consume al de **Productos**.

---

##  Requerimientos implementados
✅ **Arquitectura de microservicios** diagramada y desarrollada.  
✅ CRUDs y operaciones necesarias en cada servicio.  
✅ **Servidor Eureka** para registrar y descubrir los servicios.  
✅ **Balanceo de carga (Spring Cloud Load Balancer)** con múltiples instancias.  
✅ **Circuit Breaker y Retry (Resilience4J)** para manejar fallos de comunicación.  
✅ **API Gateway** como punto de entrada único para clientes externos.  

*(La parte de Docker fue opcional y no se implementó en esta entrega.)*

---

##  Tecnologías utilizadas
- **Java + Spring Boot**  
- **Spring Cloud (Eureka, Load Balancer, API Gateway, Resilience4J)**  
- **Maven**  
- **MySQL (según microservicio)**  
- **Postman** para pruebas de integración  
- **Git y GitHub** para control de versiones  

---

##  Pruebas
- Se realizaron pruebas de comunicación entre servicios con **Postman**.
- 

## Autor
**Martín Díaz**  
Estudiante de Análisis de Sistemas | Java Backend Developer

