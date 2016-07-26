/* Archivo: ExcepcionAsignaturaNoRegistrada.java
  * Autor: Diego Mosquera
  * Fecha: 18-06-10
  *
  */
  
package regAcadUtil;  

public class ExcepcionAsignaturaNoRegistrada extends Exception
{	
    public ExcepcionAsignaturaNoRegistrada (String causa)
	{
	   super(causa);
	}
}