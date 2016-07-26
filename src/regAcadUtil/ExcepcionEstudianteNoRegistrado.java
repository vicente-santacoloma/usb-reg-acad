/* Archivo: ExcepcionEstudianteNoRegistrado.java
  * Autor: Diego Mosquera
  * Fecha: 18-06-10
  *
  */
  
package regAcadUtil;

public class ExcepcionEstudianteNoRegistrado extends Exception
{	
    public ExcepcionEstudianteNoRegistrado (String causa)
	{
	   super(causa);
	}
}