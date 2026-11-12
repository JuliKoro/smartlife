*SmartLife \- Decisiones de Diseño*

*Servicio de Ventas*

* Comenzamos el diseño por la creación de la entidad ***Comercio***  
  * Puede que los comercios estén siendo gestionados por otro servicio que no está especificado en el enunciado.  
  * Por lo tanto, voy a modelar lo mínimo necesario de los comercios. Lo mínimo necesario será un “id”, o sea un identificador único.   
  * El identificador del comercio podría servir para identificarlo en otro servicio.   
* Modelado de ***productos***  
  * Sabemos que todos los productos van a tener un precio base.  
  * Como mínimo necesitamos un nombre/descripción para cada producto.  
  * *Primera alternativa: clase abstracta y subclases*  
    * Clase abstracta Producto \- la clase padre  
    * Clases hijas: Electrónico y Hogar  
    * Supongamos que el IVA lo calculamos en la clase padre, proque sabemos por enunciado que todos los tipos de productos lo van a tener.  
    * Si supongo que quiero escribir todos los métodos que calculan impuestos en la clase padre, y todos los que puedan llegar a existir en un futuro, entonces tendríamos baja cohesión.  
    * También supongamos que, actualmente, el método “totalDeImpuestos” lo sobreescribimos en cada clase hija para que cada una tenga su propio cálculo.  
      * Electronicos aplicaría cálculo del EO  
      * DelHogar aplicaría cálculo del EI  
    * ¿Qué pasaría si surge un nuevo tipo de producto? Por ejemplo, productos de jardinería.  
      * Debería crear una nueva clase según este modelado.   
    * ¿Qué pasaría si a este nuevo tipo de producto hay que aplicarle tanto el EO como el EI?   
      * En este caso hipotético estaría repitiendo lógica que están en clases “vecinas”  
    * Estoy yendo por camino que no tiene muchas salidas o que más bien tiene muchas “desventajas”. ***Esto no significa que no sea posible de implementarlo, sino que no nos conviene por muchas cosas.***  
    * Más allá de todo lo anterior, ¿realmente es responsabilidad del producto saber calcular los impuestos?  
  * *Segunda alternativa: interface Producto y clases concretas que la implementan*  
    * Interface: Producto  
    * Clases concretas: Hogar y Electrónico  
    * Suponiendo que venimos a proponer una mejora respecto a la alternativa 1, pero sin ser muy distinta, entonces:  
      * El cálculo del IVA ahora lo deberíamos repetir en las clases concretas, porque las interfaces NO SON CLASES, no puedo tener comportamiento (\*).  
        * Esto lleva claramente a una repetición de lógica  
      * El cálculo de los impuestos como EO, EI y todos los que puedan llegar a existir, deberían estar en las clases concretas.  
        * Esto también llevaría a una posible repetición de lógica  
      * Inclusive, hasta deberíamos repetir la escritura de los atributos en las clases concretas que implementan la interface Producto.   
    * ¿Qué pasaría si surge un nuevo tipo de producto? Por ejemplo, productos de jardinería.  
      * Debería crear una nueva clase según este modelado, repitiendo los atributos comunes en todas las demás clases “hermanas”/”vecinas”.   
    * ¿Qué pasaría si a este nuevo tipo de producto hay que aplicarle tanto el EO como el EI?   
      * En este caso hipotético estaría repitiendo lógica que están en clases “vecinas”  
    * (....)  
    * Más allá de todo lo anterior, ¿realmente es responsabilidad del producto saber calcular los impuestos?  
    * Nos está resultando un poco “peor” que la alternativa 1\.  
  * *Tercera alternativa: Producto como clase concreta \+ TipoDeProducto como clase concreta*  
    * Producto, en esta alternativa, llevaría el precioBase y el nombre/descripción, además del id.  
    * TipoDeProducto llevaría nombre/descripción y los impuestos que aplican a él.   
      * “Hogar” y “Electrónico” pasarían a ser simplemente INSTANCIAS de la clase TipoDeProducto  
    * ¿Qué pasaría si surge un nuevo tipo de producto? Por ejemplo, productos de jardinería.  
      * En esta propuesta solamente debería crear una nueva instancia de tipo de producto y asociarle los impuestos que aplican a esta nueva categoría.  
      * Esta propuesta es mucho más flexible (extensible) que la alternativa 1 y 2\.  
        * ¿Por qué es más flexible (extensible)? Porque si quiero crear un nuevo tipo de producto NO DEBO escribir código, sino que simplemente instancias la misma clase. La instanciación la podría hacer a través de una interfaz gráfica en tiempo de ejecución.  
    * ¿Qué pasaría si a este nuevo tipo de producto hay que aplicarle tanto el EO como el EI?   
      * (...)  
  * *Cuarta alternativa: Producto como clase concreta y TipoDeProducto como enumerado*  
    * Esta propuesta tiene poca flexibilidad pues tengo que tocar código cada vez que quiera un nuevo tipo de producto.  
    * Además, no puedo relacionar directamente los Tipos de producto con los impuestos a aplicar.  
* Modelado de impuestos  
  * *Primera alternativa: Impuesto como clase concreta*  
    * Esto implicaría que tendría el comportamiento para calcular el IVA, el EO y el EI (y todos los que puedan llegar a surgir en un futuro) en la misma clase.  
      * Consecuencias: baja cohesión (porque la clase “hace demasiado”), poca mantenibilidad, (...)  
  * *Segunda alternativa: Impuesto como interface y las “demás” como clases concretas que la implementan*  
    * Impuesto sería una interface que tendría un método “calcular” o “aplicarSobre(producto)”  
    * IVA, EO y EI serían clases concretas que implementarían la interface Impuesto y cada una haría una redefinición del método en cuestión.   
      * ***HAY COMPORTAMIENTO DISTINTO EN CADA IMPUESTO, ES DECIR, CADA UNO SE CALCULA DE UNA FORMA DISTINTA.***  
    * ¿Qué sucedería si apareciera un nuevo impuesto?  
      * En simples palabras, tendría una clase por cada impuesto particular.  
    * Con esta alternativa hemos ganado:  
      * Que el producto ya nos encargue de calcular los impuestos  
      * Que cada clase sea altamente cohesiva  
      * Que cada clase sea altamente mantenible (\*)  
  * *Tercera alternativa: Clase abstracta Impuesto y clases hijas IVA, EO y EI*  
    * (...)  
* Modelado de ***interesados en ventas***  
  * *Alternativa 1: El comercio conoce a cada uno de los servicios que están interesados en la venta de los productos*  
    * Genera alto acoplamiento entre el comercio y cada servicio  
    * Complica/entorpece la extensibilidad  
      * ¿Qué pasaría si mañana hay un nuevo interesado?  
        * Comercio debería conocer otra clase más  
    * Si modelamos a los servicios como clases concretas (SFFA y S2), la clase comercio conocería exactamente la existencia de estas dos clases  
  * *Alternativa 2: El comercio no conozca directamente a cada uno de los servicios que están interesados*  
    * Genera que el comercio no esté acoplado a los servicios interesados  
    * Mejora la extensibilidad  
      * ¿Qué pasaría si mañana hay un nuevo interesado?  
        * Comercio NO NECESITA conocer otra clase más, porque solamente está acoplada a una interface.  
    * Proponemos que cada servicio sea una clase concreta pero que Comercio no las conozca directamente, sino a través de una interface. 

*Servicio de Seguridad*

* Hasta ahora todos los demás servicios estaban desplegados en un servidor en la nube.  
* Por lo anterior, consideramos que la arquitectura general del Sistema es una Arquitectura Web.  
* Si consideramos que los sensores instalados en el hogar envían los datos directamente a un servicio cloud:  
  * Podríamos sufrir latencia \=\> tardaríamos en recibir los datos en el servicio cloud y por lo tanto no podríamos darnos cuenta rápidamente de que “está habiendo un robo”, por ejemplo. (PERFORMANCE)  
  * Si perdemos conexión a internet, entonces nunca podríamos enviar los datos al servicio cloud \=\> Los datos nunca se procesarían (DISPONIBILIDAD)  
  * Si consideramos un único servidor cloud procesando todas las solicitudes (Único punto de falla (\*)), podríamos sufrir una saturación del mismo generando que las solicitudes se procesen “demasiado tarde” en varios casos.   
    * Si escalamos el servidor podemos hacerlo de dos formas distintas:  
      * Escalar verticalmente: implica que vamos a agregar más recursos a ese servidor. Este tipo de escalabilidad tiene un límite.  
      * Escalar horizontalmente: implica agregar más servidores para que las solicitudes puedan ser atendidas por muchos servers al mismo tiempo.  
    * Si bien este problema se puede solucionar con las estrategias mencionadas anteriormente, no eliminamos los dos problemas anteriores.  
* La integración entre los distintos sensores/dispositivos y el servicio cloud podría ser:  
  * Cliente \- servidor (solicitud \- respuesta) \- API REST (HTTP): los sensores/dispositivos tomarían el papel de cliente, y el servicio cloud actuaría de servidor. Esto implicaría que cada sensor/dispositivo tenga que conocer la URL exacta del servidor. Cada dispositivo debería hacer una request HTTP (probablemente POST) al servidor enviándole los datos para que éste los procese y se de cuenta si efectivamente existe un evento particular. Recordemos que esta conexión es sincrónica.  
  * Broker (publicación \-  subscripción/ mqtt): cada dispositivo/sensor publica sus datos en algún tópico en particular. El servicio cloud estaría suscrito a los tópicos necesarios para procesar los datos.  
* Para poder solucionar los problemas que conlleva conectar directamente los sensores/dispositivos desde el hogar hacia el servicio cloud, planteamos una arquitectura dividida:  
  * Un Sistema funcionando íntegramente en el hogar  
    * La finalidad es poder procesar los datos recibidos de cada uno de los sensores/dispositivos de forma muy rápida sin sufrir cortes de servicio.  
    * No dependemos de una conexión activa a internet pues la lógica para el procesamiento está embebida en un dispositivo local.  
    * Vamos a necesitar una conexión a internet para poder sincronizar posteriormente todos los eventos registrados hacia el servicio central. No hace falta que sea en tiempo real.  
    * La sincronización puede ser con un esquema push based o pull based.  Si es pull based el servicio cloud debería preguntar cada N minutos/segundos a cada uno de los hogares por la ocurrencia de nuevos eventos. En cambio, es push based cada uno de los hogares a mandar cada N minutos/segundos los nuevos eventos al servicio cloud. Claramente nos conviene un esquema push based.  
    * ¿Qué pasaría si necesitamos actualizar el código que se ejecuta en cada uno de los hogares?  
      * Una posibilidad sería realizar una actualización 1 a 1\.  
        * Nosotros mandamos a un técnico a cada hogar  
        * Invitamos a que cada hogar venga a un centro/oficina a actualizar su dispositivo  
      * Otra posibilidad es pensar en un centro de actualizaciones.  
  * Un Servicio cloud para agrupar/persistir la ocurrencia de los eventos y solamente funcionar para consultas/análisis de métricas posteriores.