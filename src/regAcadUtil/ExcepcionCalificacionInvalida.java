/* Archivo: ExcepcionCalificacionInvalida.java
  * Autor: Diego Mosquera
  * Fecha: 18-06-10
  *
  */
  
package regAcadUtil;

public class ExcepcionCalificacionInvalida extends Exception
{	
    public ExcepcionCalificacionInvalida (String causa)
	{
	   super(causa);
	}
}