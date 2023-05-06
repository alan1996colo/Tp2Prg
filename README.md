# Tp2Prg  
- ATENCION  
* Revisar que el nombre del paquete coincida  
+ Si hay una gran modificacion en el diseño, en la manera de lo posible modificar el archivo mdj de StarUml.  
* Intente usar la clase test en lugar de un  "main" para probar cualquier cosa, hasta el mas ridiculo print se puede probar desde el test sin necesidad de hacer mucho   
- ~~consultadevecinosTest es redundante, en grafoTest queda todo puesto.~~ 
- No abusar creando mas de una clase Test para una sola clase grafo.  
- Si se necesita tener mas distribuido los test, se pueden separar por lineas de /*****///  
  - Ejemplo  
    >GrafoTest  
    >//aca comienzan los test de aristas:   
    > @test   
    > public void arista1Test(){}    
    > public void arista2Test(){}  
    > public void arista3Test(){}  
    > //aca comienzan los test de vecinos  
    > @test expected= nullPointerException  
    > public void vectorNUllTest(){}  
    > public void vectorNUllTest2(){}  
    > public void vectorNUllTest3(){}  
    > etc   
     
*UPDATE 23/4*  
***1)La aplicación debe permitir al usuario registrar una serie de localidades, incluyendo el nombre,
provincia, latitud y longitud de cada una. :*** $Esto se entiende de la siguiente manera, los vertices (localidades) 
  van a ser modificados en el transcurso de ejecucion de la aplicacion, por lo tanto necesitamos una implementacion   
  en la que la complejidad de agregar o eliminar vertices no sea tan grande.
~~En un principio decidi que seria mas eficiente la matriz de adyacencia, pero pase por alto la dinamica del tp.~~   
Por lo tanto, en este momento considero que es mejor una implementacion por lista de vecinos, ya que el orden de   
complejidad de agregar es O(1) amortizado y eliminar es O(n)


*clase 25/4:* 
 >El profe indicó que para manejar algoritmos sobre grafo es mejor crear una clase "Solver" (objeto )    
 >que use metodos sobre Grafo.    
 >Grafo _grafo  
 >public Solver(Grafo g){  
 >_grafo=g  
 >}  
 _La ventaja de solver , es que es un objeto en memoria y se le va poder seguir pidiendo cosas sobre el estado en   
que se encuentra._  

_Consideraciones: Dada la explicacion de la ultima clase, quiza sea pertinente quitarle el "public" a todos los metodos  
dento del paquete Negocio, y que solo la clase Negocio tenga metodos "public", de esta manera a la clase interfaz le es  
imposible acceder a metodos del paquete negocio que no sean de la clase negocio en si. Revisar si este planteo es correcto  
, y hacer lo mismo del lado del paquete "Interfaz"._

> ---Update 27/4-----    

Notas clase GestorArchivo :
Deje un metodo util para cargar una lista de nodos en GrafoLista de forma que si se quiere buscar una "ciudadNombre" se puede cargar un objeto   
GrafoLista auxiliar y desde ahi usar algun metodo que busque el nodo, y desde ahi agregarlo al GrafoLista principal si es necesario.  

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
- [ ] Revisar metodos publicos que no necesitan ser publicos, pasarlos a privados o modificador de acceso por defecto. --Alejandro  
- [ ] Agregar estructura heap a prim para mejorar su rendimiento y su orden de complejidad  --Alan
- [ ] Agregar opciones en la interfaz visual para guardar el archivo que se estuvo trabajando y cargarlo mas tarde. -->Hernan  
- [ ] Revisar todas las funciones y aplicar las buenas practicas de programacion de las lecturas del inicio de las clases.-->Todos
- [ ] Agregar todos los tests que se hayan omitido de la capa de negocio. -->Alejandro
- [ ] Dessarrollar una herramienta para permitir que el usuario modifique la soluci´on, cambiando una conexion por otra y
mostrando el costo de esta modificacion -->Hernan
- [ ] Terminar la interfaz grafica --> Hernan
