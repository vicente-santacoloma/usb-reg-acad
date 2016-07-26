/* Archivo: ExcepcionCursoNoRegistrado.java
  * Autor: Diego Mosquera
  * Fecha: 18-06-10
  *
  */
  
package regAcadUtil;

public class ExcepcionCursoNoRegistrado extends Exception
{	
    public ExcepcionCursoNoRegistrado (String causa)
	{
	   super(causa);
	}
}