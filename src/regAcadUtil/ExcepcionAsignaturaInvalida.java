/* Archivo: ExcepcionAsignaturaInvalida.java
  * Autor: Diego Mosquera
  * Fecha: 18-06-10
  *
  */
  
package regAcadUtil;

public class ExcepcionAsignaturaInvalida extends Exception
{	
    public ExcepcionAsignaturaInvalida (String causa)
	{
	   super(causa);
	}
}