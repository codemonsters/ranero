# ranero
2 Player Frogger Homage

## Preparación del entorno de desarrollo

1. Importamos el proyecto seleccionando la carpeta principal donde se clonó el repositorio
2. Ojo: no actualizamos el plugin de Gradle a la versión 4.x (incompatibilidad actual con LibGDX)
3. Añadimos una nueva "run configuration" de tipo "Application":
    * Name: desktop
    * Main Class: es.codemonsters.ranero.desktop.DesktopLauncher
    * Working Directory: android/assets
    * Use classpath of module: desktop

### Software necesario

Instalamos lo siguiente:
* Un cliente Git:
    * [git](https://git-scm.com/downloads): Cliente Git oficial (CLI / intefaz de línea de comandos)
    * [SmartSVN](http://www.smartsvn.com/): Cliente con interfaz gráfica
    * [GitKraken](http://www.gitkraken.com/): Otro cliente con interfaz gráfica
* JDK
* Android SDK
* Android Studio

### Cómo trabajar con el repositorio

Inicialmente necesitaremos clonar el repositorio Git en nuestro equipo:

1. Configuramos Git:
    * Para identificar nuestras contribuciones más fácilmente:
        * ```git config --global user.email "NUESTRA@DIRECCION.MAIL"```
        * ```git config --global user.name "NOMBREDEUSUARIO"```
    * Para que no vuelva a pedir nuestra contraseña durante los siguientes 90 minutos (5400 segundos):
        * ```git config --global credential.helper cache```
        * ```git config --global credential.helper 'cache --timeout=5400'```
    * Utilizar colores en la terminal:
        * ```git config color.ui true```
    * Para mostrar la información de cada commit en una única línea:
        * ```git config format.pretty oneline```
2. Clonamos el repositorio dentro de una carpeta local:
    * Si es desde la terminal ejecutamos: ```git clone https://NOMBREDEUSUARIO@github.com/codemonsters/ranero.git```
    * O bien usamos un programa con interfaz gráfica (SmartSVN, GitKraken...)
3. Abrimos el proyecto con Android Studio

### Trabajando con branches (frecuentemente una branch implica una nueva característica):

* Para crear una nueva rama y comenzar a utilizarla: ```git checkout -b nombre_rama```
* Hacemos tantos commit y push dentro la rama como sea necesario
* Cuando consideremos el trabajo acabado mezclamos/publicamos los cambios en la rama principal (master): ```git checkout master; git merge nombre_rama```

## Documentación

TODO
