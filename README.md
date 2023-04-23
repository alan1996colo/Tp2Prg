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
