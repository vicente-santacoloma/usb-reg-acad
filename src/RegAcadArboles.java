import regAcadUtil.*;
import java.util.*;
 
//@ import org.jmlspecs.models.JMLEqualsEqualsPair;
//@ import org.jmlspecs.models.JMLEqualsToEqualsRelation;
//@ import org.jmlspecs.models.JMLEqualsToEqualsMap;
//@ import org.jmlspecs.models.JMLType;
//@ import org.jmlspecs.models.JMLInteger;
//@ import org.jmlspecs.models.JMLString;

/**
 * Implementacion concreta del TDA Registro Academico
 * usando arboles binarios 
 *
 * @author Guillermo Palma (gpalma@ldc.usb.ve)
 * @version 0.2
 */
public class RegAcadArboles implements RegAcadInterface {

    // ////////////////////////////////////
    // MODELO DE REPRESENTACION
    // ////////////////////////////////////

    /**
     * Arbol binario donde la informacion esta ordenada por
     * Asignatura/Estudiante
     */
    private /*@ spec_public nullable @*/ Nodo raizAEC;
    
    /**
     * Arbol binario donde la informacion esta ordenada por
     * Estudiante/Asignatura
     */
    private /*@ spec_public nullable @*/ Nodo raizEAC;
    
    /**
     * Estructura para representar los nodos de los arboles 
     */
    private class Nodo {

	/**
	 * La data del nodo 
	 */
	public InfoAcad infoEstud;
	
	/**
	 * Referencia a un objeto Nodo que contiene objetos 
	 * InfoAcad que son menores al objeto infoEstud
	 */
	public Nodo     izq;

	/**
	 * Referencia a un objeto Nodo que contiene objetos 
	 * InfoAcad que son mayores al objeto infoEstud
	 */
	public Nodo     der;
	
	/**
	 * Referencia a un objeto Nodo que es el objeto  
	 * progenitor en el arbol binario
	 */
	public Nodo     prog;
	
	/**
	 * Construye un nuevo nodo 
	 *
	 * @param info     Contiene la informacion academica 
	 */
	public Nodo(InfoAcad info) {
	    infoEstud = info;
	    izq = null;
	    der = null;
	    prog = null;
	}
    }

    // ////////////////////////////////////
    // INVARIANTE DE REPRESENTACION
    // ////////////////////////////////////

    /*@ public invariant infoValida(raizAEC) && infoValida(raizEAC) &&
      @                  esArbolDeBusqAsigEst(raizAEC) && esArbolDeBusqEstAsig(raizEAC) &&
      @                  sonArbolesEquivalentes();
      @*/

    // ////////////////////////////////////
    // RELACION DE ACOPLAMIENTO
    // ////////////////////////////////////

    //  
    /*@ public represents califs <- map(raizEAC);
      @ public represents cursadas <- relacion(raizEAC);
      @*/
     
    /*@ public pure model JMLEqualsToEqualsRelation relacion(Nodo n){
      @     JMLEqualsToEqualsRelation rel = new JMLEqualsToEqualsRelation();
      @     relacion_aux(rel, n);
      @     return rel;
      @ }
      @*/

    /*@ assignable rel;
      @ public void relacion_aux(JMLEqualsToEqualsRelation rel, Nodo n){
      @     if (n != null) {
      @	          rel = rel.insert(new JMLEqualsEqualsPair(
      @                                 new JMLString(n.infoEstud.obtenerEstudiante()), 
      @                                 new JMLString(n.infoEstud.obtenerAsignacion())));
      @         relacion_aux(rel, n.der);
      @	        relacion_aux(rel, n.izq);
      @     }
      @ }
      @*/

    /*@ public pure model JMLEqualsToEqualsMap map(Nodo n){
      @     JMLEqualsToEqualsMap func = new JMLEqualsToEqualsMap();
      @     map_aux(func, n);
      @     return func;
      @ }
      @*/    

    /*@ assignable func;
      @ public void map_aux(JMLEqualsToEqualsMap func, Nodo n){   
      @    if (n != null) {    
      @          func = (JMLEqualsToEqualsMap) func.add(new JMLEqualsEqualsPair(
      @                                                   new JMLString(n.infoEstud.obtenerEstudiante()), 
      @                                                   new JMLString(n.infoEstud.obtenerAsignacion())),
      @                                                 new JMLInteger(n.infoEstud.obtenerCalificacion()));
      @        map_aux(func, n.der);
      @        map_aux(func, n.izq);
      @    }
      @ }
      @*/    
    
    /**
     * Constructor del registro academico implementado como arboles
     * binarios. Los dos arboles que contienen la informacion academica
     * hacen referencia a null
     */
    public RegAcadArboles() {
	InfoAcad a = new InfoAcad("0000000", "CI0000", 0);
	raizAEC = new Nodo(a);
	raizEAC = new Nodo(a);
    } 

    /////////////////////////////////////////////////////////////////////
    //
    // Metodos auxiliares que no forman parte del TDA Registro Academico
    //
    /////////////////////////////////////////////////////////////////////

    /**
     * Encuentra el menor valor InfoAcad que contiene un arbol binario
     *
     * @param nodo     Referencia a un nodo de un arbol de busqueda binario
     */
    private /*@ pure @*/ InfoAcad minInfoAcad(Nodo nodo) {
	Nodo actual = nodo;
	while (actual.izq != null)
	    actual = actual.izq;
	
	return actual.infoEstud;
    }

    /**
     * Encuentra el menor valor InfoAcad que contiene un arbol binario
     *
     * @param nodo     Referencia a un nodo de un arbol de busqueda binario
     */
    private /*@ pure @*/ InfoAcad maxInfoAcad(Nodo nodo) {
	Nodo actual = nodo;
	while (actual.der != null)
	    actual = actual.der;
	
	return actual.infoEstud;
    }
    
    /**
     * Determina si una estructura cumple con las condiciones para ser
     * un arbol de busqueda binario en donde la informacion academica 
     * se encuentre correctamente ordenada por Estudiante/Asignatura
     *
     * @param nodo     Referencia a un nodo de un arbol de busqueda binario
     * @return         Verdadero si es arbol de busqueda binario falso de lo contrario
     */
    public /*@ pure @*/ boolean esArbolDeBusqEstAsig(Nodo nodo) {
	EstudianteAsignaturaComparador eaCompador = new EstudianteAsignaturaComparador();
	int comparacion;
	InfoAcad info;

	if (nodo == null)
	    return true;
	
	if (nodo.izq != null) {
	    info = maxInfoAcad(nodo.izq);
	    comparacion = eaCompador.compare(info, nodo.infoEstud);
	    if (comparacion > 0) 
		return false;
	}

	if (nodo.der != null) {
	    info = minInfoAcad(nodo.der);
	    comparacion = eaCompador.compare(info, nodo.infoEstud);
	    if (comparacion <= 0) 
		return false;
	}
	return ( esArbolDeBusqEstAsig(nodo.izq) && esArbolDeBusqEstAsig(nodo.der) );
    }

    /**
     * Determina si una estructura cumple con las condiciones para ser
     * un arbol de busqueda binario en donde la informacion academica 
     * se encuentre correctamente ordenada por Asignatura/Estudiante
     *
     * @param nodo     Referencia a un nodo de un arbol de busqueda binario
     * @return         Verdadero si es arbol de busqueda binario falso de lo contrario
     */
    public /*@ pure @*/ boolean esArbolDeBusqAsigEst(Nodo nodo) {
	AsignaturaEstudianteComparador aeCompador = new AsignaturaEstudianteComparador();
	int comparacion;
	InfoAcad info;

	if (nodo == null)
	    return true;
	
	if (nodo.izq != null) {
	    info = maxInfoAcad(nodo.izq);
	    comparacion = aeCompador.compare(info, nodo.infoEstud);
	    if (comparacion > 0) 
		return false;
	}

	if (nodo.der != null) {
	    info = minInfoAcad(nodo.der);
	    comparacion = aeCompador.compare(info, nodo.infoEstud);
	    if (comparacion <= 0) 
		return false;
	}
	return ( esArbolDeBusqAsigEst(nodo.izq) && esArbolDeBusqAsigEst(nodo.der) );
    }

    /**
     * Determina si la informacion academica contenida en un arbol de busqueda binario
     * es valida o no
     *
     * @param nodo     Referencia a un nodo de un arbol de busqueda binario
     * @return         Verdadero si la informacion academica 
     *                 del arbol es valida, falso de lo contrario
     */
    public /*@ pure @*/ boolean infoValida(Nodo nodo) {
	String carne;
	String asignatura;
	int calificacion;

	if (nodo == null)
	    return true;

	carne = nodo.infoEstud.obtenerEstudiante();	
	if (!Especificaciones.esCarne(carne)) {
	    return false;
	}
	
	asignatura = nodo.infoEstud.obtenerAsignacion();
	if (!Especificaciones.esCodigo(asignatura)) {
	    return false;
        } 

	calificacion = nodo.infoEstud.obtenerCalificacion();
        if (calificacion < 0 || calificacion > 20) {
	    return false;
        }

	return ( infoValida(nodo.izq) && infoValida(nodo.der) );
    }
    
    /**
     * Determina si un objeto InfoAcad se encuentra referenciado 
     * en un arbol de búsqueda binario ordenado por Estudiante/Asignatura 
     *     
     * @param nodo     Referencia a un nodo de un arbol de busqueda binario
     * @param info     Referencia a un objeto InfoAcad
     * @return         Verdadero si el objeto InfoAcad se encuentra en el arbol,
     *                 falso de lo contrario                    
     */
    private /*@ pure @*/ boolean encontrarInfoAcadEstAsig(InfoAcad info, Nodo nodo) {
	if (nodo == null)
	    return false;

	EstudianteAsignaturaComparador eaCompador = new EstudianteAsignaturaComparador();
	int comparacion = eaCompador.compare(info, nodo.infoEstud);
	
	if(comparacion == 0) {
	    if (info == nodo.infoEstud)
		return true;
	    else
		return false;
	} else if (comparacion > 0) {
	    return  encontrarInfoAcadEstAsig(info, nodo.der);
	} else {
	    return encontrarInfoAcadEstAsig(info, nodo.izq);
	}
    }

    /**
     * Determina si un objeto InfoAcad se encuentra referenciado 
     * en un arbol de búsqueda binario ordenado por Asignatura/Estudiante
     *     
     * @param nodo     Referencia a un nodo de un arbol de busqueda binario
     * @param info     Referencia a un objeto InfoAcad
     * @return         Verdadero si el objeto InfoAcad se encuentra en el arbol,
     *                 falso de lo contrario                    
     */
    private /*@ pure @*/ boolean encontrarInfoAcadAsigEst(InfoAcad info, Nodo nodo) {
	if (nodo == null) 
	    return false;
	
	AsignaturaEstudianteComparador aeCompador = new AsignaturaEstudianteComparador();
	int comparacion = aeCompador.compare(info, nodo.infoEstud);
	
	if(comparacion == 0) {
	    if (info == nodo.infoEstud)
		return true;
	    else
		return false;
	} else if (comparacion > 0) {
	    return  encontrarInfoAcadAsigEst(info, nodo.der);
	} else {
	    return encontrarInfoAcadAsigEst(info, nodo.izq);
	}
    }
    
    /**
     * Determina si todos los objetos InfoAcad de un arbol se encuentran
     * en el arbol raizEAC. 
     *
     * @param nodo     Referencia a un nodo de un arbol de busqueda binario
     * @return         Verdadero si todos los objetos de un arbol estan referenciados
     *                 en el arbol raizEAC, falso de lo contrario  
     */
    public /*@ pure @*/ boolean estaDataEnArbolEAC(Nodo nodo) {    
	if (nodo == null)
	    return true;
	
	return ( encontrarInfoAcadEstAsig(nodo.infoEstud, raizEAC) && 
		 estaDataEnArbolEAC(nodo.izq) && estaDataEnArbolEAC(nodo.der) );
    }
    
    /**
     * Determina si todos los objetos InfoAcad de un arbol se encuentran
     * en el arbol raizAEC. 
     *
     * @param nodo     Referencia a un nodo de un arbol de busqueda binario
     * @return         Verdadero si todos los objetos de un arbol estan referenciados
     *                 en el arbol raizAEC, falso de lo contrario  
     */
    public /*@ pure @*/ boolean estaDataEnArbolAEC(Nodo nodo) {    
	if (nodo == null)
	    return true;
	
	return ( encontrarInfoAcadAsigEst(nodo.infoEstud, raizAEC) && 
		 estaDataEnArbolAEC(nodo.izq) && estaDataEnArbolAEC(nodo.der) );
    }
    
    /**
     * Determina si los arboles raizAEC y raizEAC hacer referencia a los mismos
     * objetos InfoAcad
     *
     * @return         Verdadero si los dos arboles tienen los mismos objetos InfoAcad,
     *                 falso de lo contrario
     */
    public /*@ pure @*/ boolean sonArbolesEquivalentes() {
	return ( estaDataEnArbolEAC(raizAEC) && estaDataEnArbolAEC(raizEAC) );
    }

    /////////////////////////////////////////////////////////////////////
    //
    // Metodos del TDA Registro Academico
    //
    /////////////////////////////////////////////////////////////////////

    /**
     * Agrega informacion academica a un registro 
     *
     * @param e        Carne del estudiante
     * @param a        Asignatura cursada
     * @param c        Calificacion del estudiante
     */
    /*@ also normal_behavior
      @ requires true;
      @ ensures true;
      @ assignable raizAEC, raizEAC; 
      @*/
    public void agregar(final String e, final String a, final int c) throws ExcepcionEstudianteInvalido, 
									    ExcepcionAsignaturaInvalida,
									    ExcepcionCalificacionInvalida,
									    ExcepcionCalificacionYaRegistrada {
	
        if (!Especificaciones.esCarne(e)){
            throw new ExcepcionEstudianteInvalido("");
        }
	
        if (!Especificaciones.esCodigo(a)){
            throw new ExcepcionAsignaturaInvalida("");
        }

        if ( c < 0 || c > 20){
            throw new ExcepcionCalificacionInvalida("");
        }
	
	InfoAcad nuevaInfo = new InfoAcad(e,a,c);

	// Se agrega un estudiante a el arbol ordenado por Estudiante/Asignatura
	Nodo posicion = raizEAC;
	Nodo nuevoNodo = new Nodo(nuevaInfo);
	EstudianteAsignaturaComparador eaCompador = new EstudianteAsignaturaComparador();
	int resultado;

	if ( posicion == null ) {
	    raizEAC = nuevoNodo; 
	} else {
	    while ( posicion != null ) {
		resultado = eaCompador.compare(posicion.infoEstud, nuevoNodo.infoEstud);
		if ( resultado > 0 ) {
		    if ( posicion.izq == null ) {
			posicion.izq = nuevoNodo;
			nuevoNodo.prog = posicion;
			break;
		    } else {
			posicion = posicion.izq;
		    }
		} else if ( resultado < 0 ) {
		    if ( posicion.der == null ) {
			posicion.der = nuevoNodo;
			nuevoNodo.prog = posicion;
			break;
		    } else {
			posicion = posicion.der;
		    }
		} else {
		    throw new ExcepcionCalificacionYaRegistrada("");
		}
	    }
	}
	
	// Se agrega un estudiante a el arbol ordenado por Asignatura/studiante
	posicion = raizAEC;
	nuevoNodo = new Nodo(nuevaInfo);
	AsignaturaEstudianteComparador aeCompador = new AsignaturaEstudianteComparador(); 

	if ( posicion == null ) {
	    raizAEC = nuevoNodo; 
	} else {
	    while ( posicion != null ) {
		resultado = aeCompador.compare(posicion.infoEstud, nuevoNodo.infoEstud);
		if ( resultado > 0 ) {
		    if ( posicion.izq == null ) {
			posicion.izq = nuevoNodo;
			nuevoNodo.prog = posicion;
			break;
		    } else {
			posicion = posicion.izq;
		    }
		} else if ( resultado < 0 ) {
		    if ( posicion.der == null ) {
			posicion.der = nuevoNodo;
			nuevoNodo.prog = posicion;
			break;
		    } else {
			posicion = posicion.der;
		    }
		} else {
		    throw new ExcepcionCalificacionYaRegistrada("");
		}
	    }
	}
    } // Fin de agregar

    public void eliminar(final String e, final String a)
        throws ExcepcionEstudianteInvalido, 
               ExcepcionAsignaturaInvalida,
               ExcepcionCursoNoRegistrado        
    {
	
	InfoAcad nuevaInfo = new InfoAcad(e,a,0);
	
	//Se elimina un estudiante a el arbol ordenado por Estudiante/Asignatura
	
	Nodo elim = raizEAC;
	Nodo nuevoNodo = new Nodo(nuevaInfo);
	Nodo x=null;
	Nodo y=null;
	EstudianteAsignaturaComparador eaCompador = new EstudianteAsignaturaComparador();
	int resultado=1;
	
	while ( resultado!=0 && elim!=null ) {
	resultado = eaCompador.compare(elim.infoEstud, nuevoNodo.infoEstud);
	if ( resultado > 0 ) {
		elim = elim.izq;
	} else if (resultado<0) {
		elim = elim.der;
		}	
	}
	
	if (elim==null) { 
		throw new ExcepcionCursoNoRegistrado(a); 
	}
	if (elim.izq==null || elim.der==null) {
		y=elim;
	} else { 
		y=sucesor(elim);
	}
	if (y.izq!=null) { 
		x=y.izq;
	} else {
		x=y.der;
	}
	if (x!=null) {
		x.prog=y.prog;
	}
	if (y.prog==null) {
		raizEAC=x;
	} else if (y==y.prog.izq) {
		y.prog.izq=x;
	} else {
		y.prog.izq=x;
	}
	if (y!=elim) {
		elim.infoEstud=y.infoEstud;
	}
	
	//Se elimina un estudiante a el arbol ordenado por Asignatura/Estudiante
	
	elim = raizAEC;
	EstudianteAsignaturaComparador aeCompador = new EstudianteAsignaturaComparador();
	resultado=1;
	
	while ( resultado!=0 && elim!=null ) {
	resultado = aeCompador.compare(elim.infoEstud, nuevoNodo.infoEstud);
	if ( resultado > 0 ) {
		elim = elim.izq;
	} else if (resultado<0) {
		elim = elim.der;
		}	
	}
	
	if (elim==null) { 
		throw new ExcepcionCursoNoRegistrado(a); 
	}
	if (elim.izq==null || elim.der==null) {
		y=elim;
	} else { 
		y=sucesor(elim);
	}
	if (y.izq!=null) { 
		x=y.izq;
	} else {
		x=y.der;
	}
	if (x!=null) {
		x.prog=y.prog;
	}
	if (y.prog==null) {
		raizEAC=x;
	} else if (y==y.prog.izq) {
		y.prog.izq=x;
	} else {
		y.prog.izq=x;
	}
	if (y!=elim) {
		elim.infoEstud=y.infoEstud;
	}
	
	} //Fin de eliminar
    
    private Nodo minimo (Nodo nodo) {
	
	Nodo actual = nodo;
	
	while (actual.izq != null){
		actual = actual.izq;
	}
	return actual;
	}
	
	private Nodo sucesor (Nodo nodo){
	
	Nodo actual = nodo;
	Nodo aux;
	
	if (actual.der!=null) {
		return minimo(actual.der);
	}
	aux=actual.prog;
	while (aux!=null && actual==aux.der) {
		actual=aux;
		aux=aux.prog;
	}
	return aux;
	}
	
	public void ListarAsignaturasPorEstudiante(final String e)           
        throws ExcepcionEstudianteInvalido, 
               ExcepcionEstudianteNoRegistrado
    {
		if (!Especificaciones.esCarne(e)){
			throw new ExcepcionEstudianteInvalido(e);
		}
		Iterator g= iteradorEstudianteAsignatura();
		System.out.println("ASIGNACION"+" "+"CALIFICACION");
		while (g.hasNext()) 
		{	
			InfoAcad Info=((InfoAcad)g.next());
			if (e.equals(Info.obtenerEstudiante()))
			{
				System.out.println("  "+Info.obtenerAsignacion()+"       "+Info.obtenerCalificacion()+" ");
			}
		} 
	}
    
    public void ListarEstudiantesPorAsignatura(final String a)    
        throws ExcepcionAsignaturaInvalida,    
               ExcepcionAsignaturaNoRegistrada
    {
		if (!Especificaciones.esCodigo(a)){
			throw new ExcepcionAsignaturaInvalida(a);
		}
		Iterator g= iteradorAsignaturaEstudiante();
		System.out.println("ESTUDIANTE"+" "+"CALIFICACION");
		while (g.hasNext()) 
		{	
			InfoAcad Info=((InfoAcad)g.next());
			if (a.equals(Info.obtenerAsignacion()))
			{
				System.out.println("  "+Info.obtenerEstudiante()+"       "+Info.obtenerCalificacion()+" ");
			}
		}
	}

	//LISTAR TODOS LOS ESTUDIANTES/ASIGNATURAS
	public void ListarTodaEstudiantePorAsignatura() {
		Iterator g= iteradorAsignaturaEstudiante();
		System.out.println("ESTUDIANTE"+"    "+"ASIGNATURA");
		while (g.hasNext()) 
		{	
			InfoAcad Info=((InfoAcad)g.next());
			{
				System.out.println("  "+Info.obtenerEstudiante()+"       "+Info.obtenerAsignacion()+" ");
			}
		}
	}
	
	//LISTAR TODAS LAS ASIGNATURAS/ESTUDIANTES
	public void ListarTodaAsignaturaPorEstudiantes() {
		Iterator g= iteradorEstudianteAsignatura();
		System.out.println("ASIGNACION"+"    "+"ESTUDIANTE");
		while (g.hasNext()) 
		{	
			InfoAcad Info=((InfoAcad)g.next());
			{
				System.out.println("  "+Info.obtenerAsignacion()+"       "+Info.obtenerEstudiante()+" ");
			}
		}
	}
    
    public Iterator iteradorEstudianteAsignatura()
    {
		return new IteradorInfoAcad(this.raizEAC);
    }
    
    public Iterator iteradorAsignaturaEstudiante()
    {
        return new IteradorInfoAcad(this.raizAEC);
    }
	
	public class IteradorInfoAcad implements Iterator{

		private /*@ spec_public @*/ Nodo nodo;
		
		public IteradorInfoAcad (Nodo nodo){
			this.nodo=minNodo(nodo);
		}
		
		public boolean hasNext(){
			return nodo!=null;
		}
		
		/*@ also assignable nodo ; @*/
		public Object next() throws NoSuchElementException{
			while (nodo!=null){
				Nodo auxiliar=nodo;
				nodo=NodoSiguiente(nodo);
				return new InfoAcad
					(auxiliar.infoEstud.obtenerEstudiante(),
						auxiliar.infoEstud.obtenerAsignacion(),auxiliar.infoEstud.obtenerCalificacion());
			}
			throw new NoSuchElementException();
		}
		
		private /*@ pure @*/ Nodo minNodo(Nodo nodox) {
			Nodo actual = nodox;
			while (actual.izq != null){
				actual = actual.izq;
			}
			return actual;
		}
		
		/*@ assignable nodox ; @*/
		private /*pure*/ Nodo NodoSiguiente (Nodo nodox){
			Nodo actual = nodox;
			Nodo x = nodox;
			if (actual.der!=null){
				return minNodo(actual.der);
			}
			else{
				actual=actual.prog;
				while (actual!=null && x==actual.der){
					x=actual;
					actual=actual.prog;
				}
			return actual;
			}
			}
	}

} // Fin de RegAcadArboles
