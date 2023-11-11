# Sistema de Microservicios - Alquiler de Monopatines Electrónicos

![Imagen Monopatin](./resources/img/monopatin-electrico-716690.jpg)

## Descripción del Sistema

La empresa desea implementar un servicio de alquiler de monopatines electrónicos en una ciudad capital. El servicio se basa en una flota de monopatines estacionados en paradas predefinidas. Los usuarios deben crear una cuenta asociada a Mercado Pago y cargar créditos antes de utilizar el servicio. Un usuario puede tener varios usuarios asociados a su cuenta, y un usuario puede estar asociado a múltiples cuentas.

### Bases de datos necesarias

- AccountService (MongoDB)
- MonitoringService (MySQL)
- ScooterService (MySQL)
- DataService (MySQL)
- MaintenanceService (MySQL)

### Funcionalidades Principales

1. **Registro de Usuarios**:
   - Los usuarios deben crear una cuenta proporcionando información como nombre, número de celular, email, nombre y apellido.
   - Las cuentas tendrán un número identificatorio y una fecha de alta.

2. **Alquiler de Monopatines**:
   - Los usuarios pueden activar un monopatín para su uso escaneando un código QR.
   - Se registra la fecha y hora de inicio del viaje.
   - El uso del monopatín se mide por tiempo, y el crédito se descuenta en función del tiempo de uso.
   - El monopatín se puede apagar si el usuario hace una pausa de hasta 15 minutos, continuando el gasto de créditos.
   - El usuario debe estacionar el monopatín en una parada definida para finalizar el viaje.

3. **Ubicación y GPS**:
   - Cada monopatín cuenta con un GPS único.
   - Los usuarios pueden localizar el monopatín más cercano a través de un mapa interactivo en la aplicación.

4. **Gestión de Mantenimiento**:
   - Los encargados de mantenimiento registran acciones de mantenimiento, considerando el tiempo de uso y los kilómetros recorridos.
   - Se generan reportes sobre el uso de monopatines en términos de kilómetros y tiempo, incluyendo pausas y sin pausas.
   - Los monopatines pueden estar en mantenimiento o habilitados para su uso en paradas definidas.

5. **Administración**:
   - El Administrador de Monopatines gestiona monopatines y paradas, establece precios y puede anular cuentas de usuarios.
   - Puede realizar ajustes de precios y habilitar los nuevos precios a partir de una fecha específica.
   - Puede consultar monopatines con un cierto número de viajes en un año y el total facturado en un rango de meses de cierto año.

6. **Registro e Ingreso de Usuario Administrador y/o Mantenimiento**
   - Es necesario hacer registro de un usuario con rol de Administrador y/o Mantenimiento para poder hacer uso de sus 
   respectivos Endpoints
   - Es necesario hacer login de un usuario para recibir el token que habilite a hacer uso de los Endpoints 

## Arquitectura del Sistema

El sistema se basa en una arquitectura de microservicios y utiliza una base de datos SQL, aunque en algunos casos se puede optar por utilizar MongoDB. Cada microservicio tiene su propia base de datos.

⚠️ __AVISO: Los servicios REST se encuentran en su mayor parte protegidos para que solamente sean utilizados por otros servicios__ ⚠️


## Micro Servicios

🤚 Para ver la documentacion es necesario ejecutar el microservicio ya que utiliza el puerto necesario para cada microservicio.
- Link para documentacion swagger → .../documentation/swagger-ui
- Link para documentacion json → .../documentation/json
___ 
- **Servicio de Monitoreo** (MonitoringService)
   - Autenticacion 
      - Registro de usuario de monitoreo (admin - encargado de mantenimiento)
      - Login
   - Administracion
   - Gestion de Mantenimiento
   - [Ver Documentacion Swagger](http://localhost:8084/documentation/swagger-ui)
   - [Ver Documentacion JSON](http://localhost:8084/documentation/json)

- **Servicio de Cuentas** (AccountService)
   - Usuarios que utilizaran los monopatines
   - Mercado pago
   - Cuentas que podran utilizar los usuarios (dicha cuenta vincula varios usuarios con una cuenta de mercado pago)
   - [Ver Documentacion Swagger](http://localhost:8081/documentation/swagger-ui)
   - [Ver Documentacion JSON](http://localhost:8081/documentation/json)

- **Servicio de Monopatines** (ScooterService)
   - Estaciones de monopatines
   - Monopatines disponibles 
   - Gestion de viajes
   - [Ver Documentacion Swagger](http://localhost:8082/documentation/swagger-ui)
   - [Ver Documentacion JSON](http://localhost:8082/documentation/json)

- **Servicio de Datos** (DataService)
   - Gestion de los datos de viajes
      - Precio del viaje
      - Tiempo de pausa 
      - Cobro extra de pausa extendida
   - [Ver Documentacion Swagger](http://localhost:8086/documentation/swagger-ui)
   - [Ver Documentacion JSON](http://localhost:8086/documentation/json)

- **Servicio de Mantenimiento** (MaintenanceService)
   - Registro de mantenimiento de los monopatines 
   - Generacion de reportes de estado de los monopatines
   - [Ver Documentacion Swagger](http://localhost:8083/documentation/swagger-ui)
   - [Ver Documentacion JSON](http://localhost:8083/documentation/json)

### Primera Entrega

La primera entrega del sistema implica:

1. Modelado de datos, incluyendo subdominios y relaciones.
2. Diseño de un backend para operaciones básicas de ABM (Alta, Baja, Modificación) de entidades y soporte para funcionalidades principales.
3. Implementación de servicios/reportes.

### Segunda Entrega

4. Segurizar los endpoints REST con JWT.
5. Incorporar tests de unidad e integración (Junit o Mockito). Documentar los endpoints REST con Swagger (OpenAPI).
6. Utilizar una base NoSQL (MongoDB), o bien implementar una comunicación vía protocolo gRPC entre 2 microservicios.
7. (Opcional) Desplegar la aplicación mediante contenedores (Docker) en una nube.

## Endpoints

#### a) Generar un Reporte de Uso de Monopatines por Kilómetros

- **Método HTTP**: GET
- **Endpoint**: `localhost:8084/maintenanceManager/getReport?filter={filter}`
- **Endpoint(Optional)**: `/getReport?filter={filter}&pauseTime={boolean}`
- **Descripción**: Permite al encargado de mantenimiento generar un reporte de uso de monopatines en función de los kilómetros recorridos para determinar si un monopatín requiere mantenimiento. El reporte puede configurarse para incluir o excluir los tiempos de pausa. AVISO: Si no se le aplica un filtro se retornara null.
- **Filtros Posibles**: 
    - km
    - usageTimeWithPause
    - usageTimeWithoutPause

#### b) Anular Cuentas

- **Método HTTP**: PUT
- **Endpoint**: `localhost:8084/admin/changeAccountStatus?id={AccountId}&status={boolean}`
- **Descripción**: Permite al administrador inhabilitar temporalmente una cuenta de usuario. Status: true = habilitada, false = deshabilitada.

#### c) Consultar Monopatines con Más de X Viajes en un Año

- **Método HTTP**: GET
- **Endpoint**: `localhost:8084/admin/scooter/reportBy?travels={numberOfTravels}`
- **Descripción**: Permite al administrador consultar los monopatines que han tenido más de `X` viajes en un año específico.

#### d) Consultar el Total Facturado en un Rango de Meses de un Año

- **Método HTTP**: GET
- **Endpoint**: `localhost:8084/admin/totalFactured`
- **Endpoint(Optional)**: `localhost:8084/admin/totalFactured?month1={month1}&month2={month2}&year={year}`
- **Descripción**: Permite al administrador consultar el total facturado en un rango de meses de un año específico, existe la posibilidad de obtener el total facturado sin una ventana de tiempo, y si se desea ver el total facturado entre ciertos meses se puede usar el endpoint opcional.

#### e) Consultar la Cantidad de Monopatines en Operación vs. Mantenimiento

- **Método HTTP**: GET
- **Endpoint**: `localhost:8084/admin/scooter/status`
- **Descripción**: Permite al administrador consultar la cantidad de monopatines actualmente en operación y la cantidad de monopatines en mantenimiento.

#### f) Realizar un Ajuste de Precios

- **Método HTTP**: POST
- **Endpoint**: `localhost:8084/admin/travel/updatePrice?price={newPrice}`
- **Endpoint(Optional)**: `localhost:8084/admin/travel/updatePrice?price={newPrice}&date={dateParam}&extraPricePerMinute={extraForExtendedPause}&pauseLimit={pauseLimitTime}`
- **Descripción**: Permite al administrador realizar un ajuste de precios para el servicio de alquiler de monopatines, y a partir de cierta fecha, habilitar los nuevos precios. Existe la posibilidad de agregar el nuevo precio en el momento, o de establecer una fecha de cambio con el formato 'dd/MM/yyyy', asi como tambien cambiar el precio que se cobra extra por exceso de tiempo en pausa, o tambien el mismo tiempo de pausa (todos los parametros son opcionales).

#### g) Listar Monopatines Cercanos a la Zona del Usuario

- **Método HTTP**: GET
- **Endpoint**: `localhost:8081/users/localizeScooter?id={userId}`
- **Descripción**: Permite al usuario obtener un listado de los monopatines cercanos a su ubicación actual, para esto se pasa como parametro el ID del usuario, donde dentro del UserService implementara un algoritmo para saber la ubicacion actual. Esto facilita la búsqueda de un monopatín disponible en la zona del usuario.

### Endpoints y Datos para POST

1. **Endpoint: http://localhost:8081/users/add**
   
   **JSON de Solicitud:**
   ```json
   {
      "id": "String", //Opcional en caso de no darle una ID, MongoDB le otorga una ID aleatoria
      "firstName": "String",
      "lastName": "String",
      "phone": "String",
      "email": "example@example.com",
      "accounts": []
   }
   ```

2. **Endpoint: http://localhost:8081/mercadoPago/add**
   
   **JSON de Solicitud:**
   ```json
   {
      "id":"String", //Opcional en caso de no darle una ID, MongoDB le otorga una ID aleatoria
      "balance": 120000.00
   }
   ```

3. **Endpoint: http://localhost:8081/account/add**
   
   **JSON de Solicitud:**
   ```json
   {
      "id": "String", //Opcional en caso de no darle una ID, MongoDB le otorga una ID aleatoria
      "mercadoPago":{"id":"ID de mercadoPago en MongoDB"},
      "users": [
          {
              "id": "ID de user en MongoDB"
          }
      ],
      "createdAt": "2023-10-16T18:37:31.901+00:00"
   }
   ```

4. **Endpoint: http://localhost:8082/station/add**
   
   **JSON de Solicitud:**
   ```json
   {
       "id": 1,
       "location": "Plaza centro"
   }
   ```

5. **Endpoint: http://localhost:8082/scooter/add**
   
   **JSON de Solicitud:**
   ```json
   {
       "underMaintenance": false,
       "inUse": false,
       "location": "Plaza centro",
       "stationId": 1
   }
   ```

6. **Endpoint: http://localhost:8084/auth/register**
   
   Es posible el registro con otorgamiento de roles

   **JSON de Solicitud:**
   ```json
      {
         "username": "admin",
         "password": 12345,
         "admin": true,
         "maintenance": false
      }
   ```   
7. **Endpoint: http://localhost:8084/auth/login**
   
   **JSON de Solicitud:**
   ```json
      {
         "username": "admin",
         "password": 12345
      }
   ```   

8. **Endpoint: http://localhost:8082/travels/start?idScooter={IDScooter}&idAccount={IDAccount}**
   
   **Solicitud:**
   No utiliza JSON para esta solicitud, utiliza simplemente IDs pasados por parámetros.

9. **Endpoint: http://localhost:8084/maintenanceManager/underMaintenance?ScooterId={IDScooter}**
   
   **Solicitud:**
   No utiliza JSON para esta solicitud, utiliza simplemente IDs pasados por parámetros.

