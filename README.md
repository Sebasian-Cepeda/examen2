# Examen 2

# Descripción 
En este proyecto tendremos un servicio para buscar los factores de un numero y los primos menores o iguales de un numero dado (solo si es primo, si no es primo la respuesta es que no es primo) usando roundrobin (solo se aprecia en aws (ver video) ).
## Video probando instancia EC2 probando el roundRobin
En este video vemos como corremos en 3 instacias diferentes de EC2 un proxy el cual con ayuda de roundrobin manejara las peticiones hacia 2 servicios diferentes.
En el video primero corremos el proxy luego los 2 servicios y hacemos pruebas de los factores y primos obteniendo como respues un json con el metodo que se usa, el número y su resultado, en el proxy podemos ver la URL a la que se le hacen las peticiones y su resultado correspondiente (observe que cada petición se realiza a un EC2 distinto al anterior pero como se tienen 2 EC2 la URL se alterna unicamente entre estos)

https://github.com/Sebasian-Cepeda/examen2/assets/89321404/26b58106-e6c0-418a-bea7-141b7805c443

## DESARROLLADO CON
* [Java version 17](https://www.oracle.com/co/java/technologies/downloads/) - Lenguaje de programación usado.
* [Maven](https://maven.apache.org/download.cgi) - Gestor de dependencias del proyecto
* [Git](https://git-scm.com/downloads) - Gestion de versiones del proyecto

## Pasos para ejecutar en local
1. Debemos clonar este repositorio
```bash
git clone https://github.com/Sebasian-Cepeda/examen2.git
```
2. Hacemos un "cd" al repositorio clonado
3. Compilamos el proyecto con el siguiente comando
```bash
mvn clean install
```
4. debemos descomentariar la linea 10 de la clase main
   
   ![image](https://github.com/Sebasian-Cepeda/examen2/assets/89321404/e714baee-46d0-4fb3-bb0d-584587ecc467)

5.con ayuda del IDE que se esta usando podemos correr la clase main o tambien puede usar el siguiente comando
```bash
"target/classes;target/dependency/*" com.examen.Main
```
6. Podemos abrir en el brawser http://localhost:4567 y optenemos los formularios los cuales retornan un json como respuesta 
   ![image](https://github.com/Sebasian-Cepeda/examen2/assets/89321404/31af8998-4c9c-4246-a03d-0f70069d6f6f)


# Diseño
 El código proporcionado consiste en una clase Invoke la cual permite construir la URL para que con ayuda de roundRobin manejemos las peticiones.

 ![image](https://github.com/Sebasian-Cepeda/examen2/assets/89321404/3d4b9505-43d1-4362-b31f-81c78c2781a8)

 una clase main y una clase LogServerFacade las cuales permiten correr los servidores (para local solo la clase main pues LogServerFacade es el proxy que usamos para probar el roundrobin en aws) y una clase service la cual cuenta con los metodos factores y primos.
 


## AUTOR
Juan Sebastian Cepeda saray



