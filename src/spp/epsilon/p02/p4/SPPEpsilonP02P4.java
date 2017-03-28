//Proyecto Parcial 2
//Problema 4
//Equipo Epsilon
//Nombres, matrículas y carreras de integrantes de equipo:
//Roberto Alain Rivera Bravo | A01411516 | IMT11
//Fabricio Arturo Balboa Cavazos | A01411541 | IMT11
//Andrés de Jesús Martínez Castillo | A01411447 | IMT11
//Jessica Delgado González | A01411536 | IMT11
//Alfredo Alejandro Lárraga Sosa | A01410278 | LMC

/*
4.Escribir un programa que dada una matriz de 4x4 realice las siguientes funciones:
a.Calcule el promedio de todos los valores.
b.Encuentre el valor mínimo y valor máximo de entre todos los valores.
c.Sumar los valores de la diagonal descendente (de Izquierda arriba a Derecha abajo)
d.Sumar los valores que están por encima de la diagonal descendente.
e.Sumar los valores que están por debajo de la diagonal descendente.
f.Utilice los siguientes valores {{1,2,3,4},{8,7,6,5},{9,10,11,12},{16,15,14,13}}
Deberá mostrar los resultados de cada punto.
*/

package spp.epsilon.p02.p4;
import java.util.Scanner;


public class SPPEpsilonP02P4 {

    public static void main(String[] args) {
        boolean flag;
        
        do{        
            menu(); //manda a método menú
            flag = salida(); //recibe el valor booleano
        } while (flag==false);
    }
    
    //método menú: muestra las opciones en pantalla
    public static void menu(){
        Scanner teclado = new Scanner (System.in);
        String message;
        
        System.out.println("-----------------------------");        
        System.out.println("Procedimientos con matriz 4x4");
        System.out.println("-----------------------------"); 
        
        System.out.println("¿Desea empezar el programa?");
        System.out.println("SI. Presione 1");
        System.out.println("NO. Presione cualquier otra tecla");
        message = teclado.next();
        
        if(message.equals("1")){
            crearMatrices();
        }
    }
    
    /*método crear matrices: relaciona a los métodos para crear matrices, asignarles datos
    y realizar las operaciones y mostrar las partes deseadas de las matrices*/   
    public static void crearMatrices(){
        int m;
        //Se asigna la dimensión de la matriz
        System.out.println("\nIntroduzca la dimensión de la matriz cuadrada");
        m = verificarIntPositivo();
        
        //Se asignan valores a la matriz matrix
        System.out.println("\nValores de matriz A");
        int[][]A = asignarValores(m);
        
        System.out.println("\nPara la matriz: ");
        mostrarArray(A);
        
        //a.Calcule el promedio de todos los valores
        System.out.println("\n1. El promedio de todos los valores es: "+promedioMatriz(A,m));
        
        //b.Encuentre el valor mínimo y valor máximo de entre todos los valores.
        System.out.println("\n2. El valor mínimo es: ");
        valEquals(valInf(A,m),A,m);
        
        
        System.out.println("\n2.5 El valor máximo es: ");
        valEquals(valSup(A,m),A,m);
        
        //c.Sumar los valores de la diagonal descendente (de Izquierda arriba a Derecha abajo)
        System.out.println("\n3. La suma de los valores en la diagonal descendente es: "+sumaDiagonal(A,m));
        //d.Sumar los valores que están por encima de la diagonal descendente.
        System.out.println("\n4. La suma de los valores encima de la diagonal descendente es: "+sumaDiagonalArriba(A,m));
        //e.Sumar los valores que están por debajo de la diagonal descendente.
        System.out.println("\n5. La suma de los valores debajo de la diagonal descendente es: "+sumaDiagonalAbajo(A,m));
        
    }
    
    //Método solicitar datos: permite verificar que los datos sean correctos y los captura
    public static int solicitarDatos(){
        Scanner teclado = new Scanner (System.in);
        int x=0;
        boolean flag;

        do {
            try { //Intenta realizar las instrucciones
                x = teclado.nextInt();
                flag = true;
            } catch (Exception ex) { //Evita que el programa falle en caso de error y muestra el error
                System.out.println("\nIntroduzca un número entero válido");
                System.out.println(ex);
                flag = false;
                teclado.next(); //Limpia el buffer del teclado
            }
     
        } while (flag == false);
        
        return x; //regresa el valor verificado
    }
    
    /*Método verificar int positivo: verifica que el entero sea positivo para evitar introducir
    valores negativos en el tamaño de la matriz*/
    public static int verificarIntPositivo(){
        boolean flag;
        int x;
        
        //Ciclo do while: ejecuta al menos una vez las instrucciones en do, y evalúa si es cierto en while
        do {
            x = solicitarDatos();
            //Evalúa si la x es menor a uno, y si lo es, asigna flag=false para repetir el ciclo
            if (x < 1) {
                System.out.println("\nIntroduzca un entero positivo(no hay longitudes negativas  o nulas de matrices)");
                flag=false;
            } else{
              flag=true;  
            }
            
        } while (flag==false);
    
        return x; //regresa el valor comprobado
    }        
    
    //Método asignar valores: asigna los valores de una matriz con un ciclo for
    public static int[][] asignarValores(int m){
        //Crea el array con el tamaño introducido por el usuario anteriormente
        int[][] matrix = new int[m][m];
        
        //Ciclo for: Nos desplaza en las filas del array
        for (int i = 0; i < m; i++) {
            //Ciclo for: Nos desplzaza en las columnas del array
            for (int j = 0; j < m; j++) {
                System.out.println("Introduzca el valor de la posición "+i+","+j);
                //Asigna el valor del array en la posición i, j
                matrix[i][j] = solicitarDatos();
            }
        }
 
        return matrix; //regresa la matriz a la que se le asignaron valores
    }
    
    //método promedio matriz: realiza el promedio de los valores de la matriz
    public static double promedioMatriz(int[][]A,int m){
        int cont=0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                cont += A[i][j];
            }
        }
        
        //se parsea para obtener un resultado más exacto con tipo double en vez de int
        double cont1 = ((double)cont)/(m*m); 
        
        return cont1; //regresa el valor double del promedio
    }
    
    //método valor inferior: obtiene el valor inferior de la matriz
    public static int valInf(int[][]A,int m){
        int cont = A[0][0];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                if(A[i][j] < cont){
                   cont = A[i][j]; 
                }
            }
        }
        
        return cont; //regresa el valor inferior de la matriz
    }
    
    //método valor superior: obtiene el valor superior de la matriz
    public static int valSup(int[][]A, int m){
        int cont= A[0][0];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                if (A[i][j] > cont) {
                    cont = A[i][j];
                }
            }
        }
        
        return cont; //regresa el valor superior de la matriz
    }
    
    //método valor equals: imprime el valor superior o inferior y su posición (o posiciones si se repite el valor) 
    public static void valEquals(int cont, int [][]A, int m){
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                if(A[i][j] == cont){
                    System.out.println(A[i][j]+" en la posición "+i+","+j);
                }
            }
        }
        
    }
    
    //método suma diagonal: suma los valores en la diagonal descendente
    public static int sumaDiagonal(int[][] A,int m){
        int cont=0;
        
        for (int i = 0; i < m; i++) {
            cont += A[i][i];
        }
        
        return cont; //regresa la suma de los valores en la diagonal descendente
    }
    
    //método suma diagonal abajo: suma los valores abajo de la diagonal descendente
    public static int sumaDiagonalAbajo(int[][] A,int m){
        int cont=0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < i; j++) {
                cont += A[i][j];
            }
        }
        
        return cont; //regresa la suma los valores abajo de la diagonal descendente
    }
    
    //método suma diagonal arriba: suma los valores arriba de la diagonal descendente
    public static int sumaDiagonalArriba(int[][] A,int m){
        int cont=0;
        
        for (int i = 0; i < m; i++) {
            for (int j = i+1; j < m; j++) {
               cont += A[i][j]; 
            }    
        }
        
        return cont; //regresa la suma los valores arriba de la diagonal descendente
    }   
    
    //Método mostrar Array: muestra en pantalla la matriz creada
    public static void mostrarArray(int [][]matrix){ //Se recibe el objeto matrix
        //Recorre las "i" filass del array
        for(int i=0; i<matrix.length; i++){
            //Recorre las "j" columnas del array
            for (int j=0; j<matrix[i].length; j++){
                System.out.print("["+matrix[i][j]+"]");     
            }                
            /*Introduce un salto de línea cada que se rebase la cantidad de 
            columnas del array para que se vea el arreglo en orden en pantalla*/
            System.out.println("\t");
        } 
        
    }
    
    //Método salir: da opción de terminar o reiniciar los cálculos   
    public static boolean salida(){
        Scanner teclado = new Scanner (System.in);
        String option;
        boolean flag;
        
        System.out.println("\n¿Desea salir?");
        System.out.println("SI: Presione 1");
        System.out.println("NO: Presione cualquier otra tecla");
        option = teclado.next();
        
        //verifica si el String es igual a "1"
        if(!option.equals("1")){
             //Se manda el valor booleano para volver a ejecutar el programa
            flag=false;
            System.out.println("\n\n");
        } else{
            //Se manda el valor booleano para terminar el programa
           flag=true; 
        }
        
        return flag;  //Se regresa el valor booleano
    }
    
    
    
}
