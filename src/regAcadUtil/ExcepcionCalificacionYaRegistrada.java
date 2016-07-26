/* Archivo: ExcepcionCalificacionYaRegistrada.java
  * Autor: Diego Mosquera
  * Fecha: 18-06-10
  *
  */
  
package regAcadUtil;

public class ExcepcionCalificacionYaRegistrada extends Exception
{	
    public ExcepcionCalificacionYaRegistrada (String causa)
	{
	   super(causa);
	}
}