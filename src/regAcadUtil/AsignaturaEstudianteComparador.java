import java.util.Comparator;

/**
 * Implementa un comparador para objetos de tipo InfoAcad
 * usando el orden Asignatura/Estudiante
 *
 * @author Guillermo Palma (gpalma@ldc.usb.ve)
 * @version 1.0
 */
class AsignaturaEstudianteComparador implements Comparator {
    
    /**
     * Compara dos objetos InfoAcad
     * usando el orden Asignatura/Estudiante. 
     * Si o1 < o2 entonces retorna un valor menor que cero
     * Si o1 > o2 entonces retorna un valor mayor que cero
     * Si o1 = o2 entonces retorna un valor igual que cero
     *
     * @param o1     Un objeto InfoAcad
     * @param o2     Un objeto InfoAcad
     * @return       Un entero que indica cual es la relacion entre o1 y o2
     */
    public int compare(Object o1, Object o2) {
	InfoAcad info1, info2;
	int resultado;
	
	info1 = (InfoAcad)o1;
	info2 = (InfoAcad)o2;
	resultado = info1.obtenerAsignacion().compareTo(info2.obtenerAsignacion());
	if ( resultado != 0 )
	    return resultado;
	else
	    return info1.obtenerEstudiante().compareTo(info2.obtenerEstudiante()); 
    }
}