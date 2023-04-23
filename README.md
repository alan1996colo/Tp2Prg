# Tp2Prg  
-ATENCION-  
Revisar que el nombre del paquete coincida  
Si hay una gran modificacion en el diseño, en la manera de lo posible modificar el archivo mdj de StarUml.  
Intente usar la clase test en lugar de un  "main" para probar cualquier cosa, hasta el mas ridiculo print se puede probar desde el test sin necesidad de hacer mucho   
"consultadevecinosTest es redundante, en grafoTest queda todo puesto.  
No abusar creando mas de una clase Test para una sola clase grafo.  
testGrafo tranquilamente puede abordar (consultaDevecinos, revisarAristas,Mains etc, )	Si se necesita tener mas distribuido los test, se pueden separar por lineas de /*****///  
Ejemplo  
GrafoTest  
//aca comienzan los test de aristas:   
 @test   
 public void arista1Test(){}    
 public void arista2Test(){}  
 public void arista3Test(){}  
 //aca comienzan los test de vecinos  
 @test expected= nullPointerException  
 public void vectorNUllTest(){}  
 public void vectorNUllTest2(){}  
 public void vectorNUllTest3(){}  
 etc
~~~~~~~~~~~~~~~~~NOTAS TP2~~~~~~~~~~~~~~ UPDATE 23/4
1)La aplicación debe permitir al usuario registrar una serie de localidades, incluyendo el nombre,
provincia, latitud y longitud de cada una. : $Esto se entiende de la siguiente manera, los vertices (localidades) van a ser modificados en el transcurso de ejecucion de la aplicacion, por lo tanto necesitamos una implementacion en la que la complejidad de agregar o eliminar vertices no sea tan grande.
En un principio decidi que seria mas eficiente la matriz de adyacencia, pero pase por alto la dinamica del tp. Por lo tanto, en este momento considero que es mejor una implementacion por lista de vecinos, ya que el orden de complejidad de agregar es O(1) amortizado y eliminar es O(n)
~~~~~~~~~~~~~~~~~~NOTAS~~~~~~~~~~~~~
Proximamente eliminar toda implementacion relacionada a matriz de adyacencia y quedarse con lista de vecinos.
