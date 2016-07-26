/* Archivo: ExcepcionEstudianteInvalido.java
  * Autor: Diego Mosquera
  * Fecha: 18-06-10
  *
  */
  
package regAcadUtil;

public class ExcepcionEstudianteInvalido extends Exception
{	
    public ExcepcionEstudianteInvalido (String causa)
	{
	   super(causa);
	}
}