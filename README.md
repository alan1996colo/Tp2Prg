 > ----------------UPdate 4/5 ----------------------- 

Por consejo del profesor Gabriel Carrillo, decidi mover todo lo relacionado a gestion de Archivos a una carpeta a parte.  

**¿Que hay de nuevo?** 
- Se agregó un constructor a GestorArchivos, para poder crearlo con un path diferente al que tiene por defecto. 
- Se agrego su seter `cambiarPath` y geter `getPath`    

**¿Que hay diferente?**  
- Ahora los metodos de GestorArchivos piden solo nombre y extension del archivo y no el path completo como antes.  

**¿Que se quitó?**  
- Se quitó la forma de llamar estaticamente a funciones de GestorArchivos  
_Recordemos que estamos trabajando con programacion orientada a objetos y usar metodos estaticos no es lo mas conveniente._

## CheckList antes de entregar  
- [ ] Modificar la estructura de GrafoLista para que las operaciones de recorrido se hagan en otra clase como en bfs. -->Alejandro 
- [ ] Revisar metodos publicos que no necesitan ser publicos, pasarlos a privados o modificador de acceso por defecto. -->Alejandro  
- [x] Agregar estructura heap a prim para mejorar su rendimiento y su orden de complejidad  -->Alan
- [x] Agregar opciones en la interfaz visual para guardar el archivo que se estuvo trabajando y cargarlo mas tarde. ~~Hernan~~ -->*Alan*    

- [ ] Revisar todas las funciones y aplicar las buenas practicas de programacion de las lecturas del inicio de las clases.-->Todos
- [ ] Agregar todos los tests que se hayan omitido de la capa de negocio. -->Alejandro
- [ ] Dessarrollar una herramienta para permitir que el usuario modifique la soluci´on, cambiando una conexion por otra y
mostrando el costo de esta modificacion -->Hernan
- [ ] Terminar la interfaz grafica --> Hernan


------------------------------------------------
update 8/5
- Se quito el metodo generarJsonDesdeLista, ya que el metodo generarJSON hace exactamente lo mismo, pasandole cualquier tipo de objeto. Es decir es mas generico.
----------------------------------------

Al parecer tuvimos el error en el planteo:
• Costo en pesos por kil´ometro de una conexi´on entre dos localidades.
• Porcentaje de aumento del costo si la conexi´on tiene m´as de 300 km.
• Costo fijo que se agrega si la conexi´on involucra localidades de dos provincias distintas.
Es decir, el usuario debe proporcionar el costo cada vez que agrega una conexion nueva, por lo que entiendo.

** El costo de cada conexión debería calcularse solamente al momento de agregar la arista al grafo completo.**

Como el costo de cada conexion deberia calcularse solamente al momento de agregar la arista al grafo, entonces directamente el peso de cada arista, deja de ser distancia y pasa a ser el resultado del calculo del precio de esa conexion usando la distancia.
Luego para conocer el precio de la conexion total solo recorremos las aristas del agm, y las sumamos.

Estuve viendo el error que paso de correccion el profesor.

distancia mardel plata--> tandil 158,35km
tandil bahia blanca --->316km
mar del plata bahia blanca--->420km

Solo quedaria emular exactamente el mismo grafo en los test de negocio, para descartar que el agm este funcionando mal.


Por el momento , sabemos que el metodo que calcula la distancia funciona aproximadamente bien, por lo tanto no es de alli el error.

Por lo tanto, quizas el bug se encuentre en la capa de presentacion.


-------------

Al parcer el agmPrim no funciona correctamente como pensabamos, estoy revisando si es por algun error del equals o hashCode en alguna clase.

------------------------
Luego de un largo debug sin sentido del agm, que funcionaba correctamente como inicialmente pensé. Revise la funcion que crea el grafo completo, y ahi se encontraba el bug. Ese metodo solo creaba aristas desde un lado del nodo, y no en las dos direcciones. Actualizado, ahora crea en ambas direcciones pero tarda mas.

Resuelto el problema principal del agm,falta terminar la siguiente lista de tareas para la reentrega:
- [x] Los tres parámetros de entrada que intervienen en el cálculo del costo deberían ser ingresados por el usuario, y no estar fijos en el código.
- [x] Sería conveniente que se mostrara en pantalla el costo de cada conexión que forma el AGM, para poder ver cómo se delosa el costo total.
- [x] El botón "Unir todos" no debería existir, porque es algo interno de la lógica de negocio. Al usuario final no le agrega ninguna información ver el grafo completo en pantalla.
- [x] No está claro qué representa el valor que se muestra en pantalla como "porcentaje de aumento".
- [x] Para dibujar una conexión en el mapa, es mejor repetir uno de los dos extremos de la conexión, en lugar de utilizar una coordenada intermedia. Si los puntos están muy lejanos entre sí, en el mapa se ven dos líneas en lugar de una.
- [x] Los tests que hicieron sobre AGM no son automáticos, no tienen ningún assert. Sólo tienen prints por consola.
- [ ] "Negocio" no es un buen nombre para una clase, es muy genérico y no da una idea de cuál es su responsabilidad.
- [ ] El ingreso de los campos de latitud y longitud en la pantalla están invertidos con respecto al orden habitual.


