 import regAcadUtil.*;
 import java.util.*;
 
//@ import org.jmlspecs.models.JMLEqualsEqualsPair;
//@ import org.jmlspecs.models.JMLEqualsToEqualsRelation;
//@ import org.jmlspecs.models.JMLEqualsToEqualsMap;
//@ import org.jmlspecs.models.JMLType;
//@ import org.jmlspecs.models.JMLInteger;
//@ import org.jmlspecs.models.JMLString;


public class RegAcadArray
{
    //MODELO DE REPRESENTACION
    
    private /*@ spec_public @*/ int NIA, MAX;
    private /*@ spec_public @*/ InfoAcad[] esAsign;
    private /*@ spec_public @*/ InfoAcad[] asignEs;
    
    //INVARIANTE DE REPRESENTACION
    
    /*@ public invariant 0 <= NIA && NIA <= MAX;
      @ public invariant (\forall int i; 0 <= i && i < NIA;
      @                      (\exists int j; 0<= j && j < NIA;
      @                          esAsign[i] == asignEs[j]
      @                      )
      @                  );
      @ public invariant (\forall int i; 0 <= i && i < NIA;
      @                      (\exists int j; 0<= j && j < NIA;
      @                          asignEs[i] == esAsign[j]
      @                      )
      @                  );
      @ public invariant (\forall int i; 0 <= i && i < NIA - 1;
      @                    InfoAcad.esMenorAsignEs(asignEs[i], asignEs[i+1]));
      @ public invariant (\forall int i; 0 <= i && i < NIA - 1;
      @                    InfoAcad.esMenorEsAsign(esAsign[i], esAsign[i+1]));
      @*/
      
    public RegAcadArray(){
        NIA = 0;
        MAX = 1;
        asignEs = new InfoAcad[MAX];
        esAsign = new InfoAcad[MAX];
    }      

    public /*@ pure @*/ int posicion(final String e, final String a,
                                     final int c, boolean ordenEsAsign)
        throws ExcepcionCalificacionYaRegistrada
    {
        int izq, der;
        izq = 0;
        der = NIA;

        InfoAcad nuevo = new InfoAcad(e,a,c);

        while(izq != der){
            int med = (izq + der)/2;
            if ((ordenEsAsign
                    && esAsign[med].obtenerEstudiante().equals(e)
                    && esAsign[med].obtenerAsignacion().equals(a)
                )
                ||
                (!ordenEsAsign
                    && asignEs[med].obtenerEstudiante().equals(e)
                    && asignEs[med].obtenerAsignacion().equals(a)
                ))
            {
                throw new ExcepcionCalificacionYaRegistrada("");
            } else if ( (ordenEsAsign 
                        && InfoAcad.esMenorEsAsign(esAsign[med], nuevo)
                        )
                        ||
                        (!ordenEsAsign
                        && InfoAcad.esMenorAsignEs(asignEs[med], nuevo)
                        )
                      )
            {
                izq = med + 1;
            }else {
                der = med;
            }
        }

        return izq;
    }

    /* @ also normal_behavior
      @        ensures ((\old(NIA) != \old(MAX)) || (MAX == 2*\old(MAX)))
      @                && NIA == \old(NIA) + 1;
      @        assignable asignEs, esAsign, MAX, NIA;
      @*/
    public void agregar(final String e, final String a, final int c)
        throws ExcepcionEstudianteInvalido, 
               ExcepcionAsignaturaInvalida,
               ExcepcionCalificacionInvalida,
               ExcepcionCalificacionYaRegistrada
    {
        if (!Especificaciones.esCarne(e)){
            throw new ExcepcionEstudianteInvalido("");
        }

        if (!Especificaciones.esCodigo(a)){
            throw new ExcepcionAsignaturaInvalida("");
        }

        if ( c < 0 || c > 20){
            throw new ExcepcionCalificacionInvalida("");
        }
        
        int posEsAsign = posicion(e, a, c, true);
        int posAsignEs = posicion(e, a, c, false);
        
        if (NIA == MAX){
            InfoAcad[] nuevoEsAsign = new InfoAcad[2*MAX];
            InfoAcad[] nuevoAsignEs = new InfoAcad[2*MAX];

          /*@ loop_invariant
            @        (\forall int k; 0<= k && k < i;
            @                    (nuevoEsAsign[k] == esAsign[k])
            @                &&  (nuevoAsignEs[k] == asignEs[k])
            @        );
            @    decreases MAX - i;
            @*/
            for (int i=0; i<MAX ;i++){
                nuevoEsAsign[i] = this.esAsign[i];
                nuevoAsignEs[i] = this.asignEs[i];
            }

            this.esAsign = nuevoEsAsign;
            this.asignEs = nuevoAsignEs;

            this.MAX *= 2;
        }

        InfoAcad nuevo = new InfoAcad(e,a,c);

        for (int i=NIA; i>posEsAsign; i--){
            esAsign[i] = esAsign[i-1];
        }
        esAsign[posEsAsign] = nuevo;

        for (int i=NIA; i>posAsignEs; i--){
            asignEs[i] = asignEs[i-1];
        }
        asignEs[posAsignEs] = nuevo;

        NIA++;
    }
    
	/* @ also assignable NIA; @*/
    public void eliminar(final String e, final String a)
        throws ExcepcionEstudianteInvalido, 
               ExcepcionAsignaturaInvalida,
               ExcepcionCursoNoRegistrado        
    {
		if (!Especificaciones.esCarne(e)){
			throw new ExcepcionEstudianteInvalido("");
		}
		if (!Especificaciones.esCodigo(a)){
			throw new ExcepcionAsignaturaInvalida("");
		}

		for (int i=0; i<NIA; i++)
		{
			if (e.equals(esAsign[i].obtenerEstudiante()) && a.equals(esAsign[i].obtenerAsignacion()))
			{
				for (int j=i; j<NIA-1; j++)
				{
					esAsign[j]=esAsign[j+1];
				}
				i=NIA;
			}
		}
		for (int i=0; i<NIA; i++)
		{
			if (e.equals(asignEs[i].obtenerEstudiante()) && a.equals(asignEs[i].obtenerAsignacion()))
			{
				for (int j=i; j<NIA-1; j++)
				{
					asignEs[j]=asignEs[j+1];
				}
				i=NIA; 
			}
		}
		NIA=NIA-1;
	}               

    public void ListarAsignaturasPorEstudiante(final String e)           
        throws ExcepcionEstudianteInvalido, 
               ExcepcionEstudianteNoRegistrado
    {
		if (!Especificaciones.esCarne(e)){
			throw new ExcepcionEstudianteInvalido("");
		}
		Iterator g = iteradorEstudianteAsignatura();
		System.out.println("ASIGNACION"+" "+"CALIFICACION");
		while (g.hasNext()) 
		{	
			InfoAcad Info =((InfoAcad)g.next());
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
			throw new ExcepcionAsignaturaInvalida("");
		}
		Iterator g = iteradorAsignaturaEstudiante();
		System.out.println("ESTUDIANTE"+" "+"CALIFICACION");
		while (g.hasNext()) 
		{	
			InfoAcad Info =((InfoAcad)g.next());
			if (a.equals(Info.obtenerAsignacion()))
			{
				System.out.println("  "+Info.obtenerEstudiante()+"       "+Info.obtenerCalificacion()+" ");
			}
		}
	}      

	public void ListarTodaAsignaturaPorEstudiantes() 
	{
		Iterator g = iteradorEstudianteAsignatura();
		System.out.println("ASIGNATURA"+"    "+"ESTUDIANTE");
		while (g.hasNext()) 
		{	
			InfoAcad Info =((InfoAcad)g.next());
			System.out.println("  "+Info.obtenerAsignacion()+"       "+Info.obtenerEstudiante()+" ");
		}
	}	
	
	public void ListarTodaEstudiantePorAsignatura() 
	{
		Iterator g = iteradorAsignaturaEstudiante();
		System.out.println("ESTUDIANTE"+"    "+"ASIGNATURA");
		while (g.hasNext()) 
		{	
			InfoAcad Info =((InfoAcad)g.next());
			System.out.println("  "+Info.obtenerEstudiante()+"       "+Info.obtenerAsignacion()+" ");
		}
	}
    
    public Iterator iteradorEstudianteAsignatura()
    {
        return new IteradorInfoAcad (this.NIA, this.esAsign);
    }
	
	public Iterator iteradorAsignaturaEstudiante()
    {
        return new IteradorInfoAcad (this.NIA, this.asignEs);
    }
	
	// Inner class
	private class IteradorInfoAcad implements Iterator
	{
		private /*@ spec_public @*/ int tam;
		private /*@ spec_public @*/ int count;
		private /*@ spec_public @*/ InfoAcad [] aux;
	
		IteradorInfoAcad(int N, InfoAcad [] info) 
		{	
			count=0;
			tam=N;
			aux=info;
		}	
		
		public boolean hasNext () 
		{
			return count<tam;
		}
		
		/*@ also assignable count; @*/
		public Object next () throws NoSuchElementException
		{
			for(int i=count; i<tam; i++)
			{
				count++;
				return new InfoAcad(aux[i].obtenerEstudiante(), aux[i].obtenerAsignacion(), aux[i].obtenerCalificacion());
			}
			throw new NoSuchElementException();
	    }
	}
	//END IteradorInfoAcad

	
}