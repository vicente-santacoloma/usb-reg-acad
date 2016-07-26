 import regAcadUtil.*;
 import java.util.*;
 
//@ import org.jmlspecs.models.JMLEqualsEqualsPair;
//@ import org.jmlspecs.models.JMLEqualsToEqualsRelation;
//@ import org.jmlspecs.models.JMLEqualsToEqualsMap;
//@ import org.jmlspecs.models.JMLType;
//@ import org.jmlspecs.models.JMLInteger;
//@ import org.jmlspecs.models.JMLString;

public interface RegAcadInterface
{
    //@ public model instance JMLEqualsToEqualsRelation cursadas;
    //@ public model instance JMLEqualsToEqualsMap califs;
    
    /*@ public instance invariant 
      @     (\forall JMLEqualsEqualsPair p 
      @         ; this.cursadas.has(p) 
      @         ; p.key instanceof JMLString && p.value instanceof JMLString);
      @ public instance invariant 
      @     (\forall JMLEqualsEqualsPair p 
      @         ; this.califs.has(p) 
      @         ; p.key instanceof JMLEqualsEqualsPair && 
      @           p.value instanceof JMLInteger);
      @ public instance invariant 
      @     (\forall JMLString x 
      @         ; this.cursadas.domain().has(x)
      @         ; Especificaciones.esCarne(x.toString()));
      @ public instance invariant 
      @     (\forall JMLString x 
      @         ; this.cursadas.range().has(x)
      @         ; Especificaciones.esCodigo(x.toString()));      
      @ public instance invariant 
      @      this.califs.domain().equals(this.cursadas);
      @ public instance invariant      
      @     (\forall JMLInteger x 
      @         ; this.califs.range().has(x)
      @         ; 1 <= x.intValue() && x.intValue() <= 20);
      @*/    


    /*@ public normal_behavior
      @    requires 
      @        Especificaciones.esCarne(e);
      @    requires 
      @        Especificaciones.esCodigo(a);
      @    requires 
      @        1 <= c && c <= 20;
      @    requires 
      @        !this.cursadas.has(new JMLEqualsEqualsPair(
      @             new JMLString(e), 
      @             new JMLString(a))
      @        );
      @    ensures
      @        this.califs.equals(\old(this.califs.extend(      
      @             new JMLEqualsEqualsPair(new JMLString(e), 
      @             new JMLString(a)), new JMLInteger(c)))
      @        );
      @    assignable cursadas, califs;      
      @ also public exceptional_behavior
      @    requires 
      @        !Especificaciones.esCarne(e)
      @           ||
      @        !Especificaciones.esCodigo(a)
      @           ||
      @        !(1 <= c && c <= 20)
      @           ||
      @        this.cursadas.has(new JMLEqualsEqualsPair(
      @             new JMLString(e), 
      @             new JMLString(a))
      @        );
      @    signals_only ExcepcionEstudianteInvalido,
      @                 ExcepcionAsignaturaInvalida,
      @                 ExcepcionCalificacionInvalida,
      @                 ExcepcionCalificacionYaRegistrada;
      @    signals(ExcepcionEstudianteInvalido) !Especificaciones.esCarne(e);
      @    signals(ExcepcionAsignaturaInvalida) !Especificaciones.esCodigo(a);
      @    signals(ExcepcionCalificacionInvalida) !(1 <= c && c <= 20);
      @    signals(ExcepcionCalificacionYaRegistrada) 
      @        Especificaciones.esCarne(e) && Especificaciones.esCodigo(a)
      @           &&
      @        (1 <= c && c <= 20)
      @           &&
      @        this.cursadas.has(new JMLEqualsEqualsPair(
      @             new JMLString(e), 
      @             new JMLString(a))
      @        );
      @    assignable \nothing;           
      @*/
      public void agregar(final String e, final String a, final int c)
             throws ExcepcionEstudianteInvalido, 
                    ExcepcionAsignaturaInvalida,
                    ExcepcionCalificacionInvalida,
                    ExcepcionCalificacionYaRegistrada;        
               
    /*@ public normal_behavior
      @    requires 
      @        Especificaciones.esCarne(e);
      @    requires 
      @        Especificaciones.esCodigo(a);
      @    requires 
      @        this.cursadas.has(new JMLEqualsEqualsPair(
      @             new JMLString(e), 
      @             new JMLString(a))
      @        );
      @    ensures
      @        this.cursadas.equals(\old(this.cursadas.remove(
      @             new JMLEqualsEqualsPair(new JMLString(e), 
      @             new JMLString(a))))
      @        );
      @    assignable cursadas, califs;    
      @ also public exceptional_behavior
      @    requires  
      @        !Especificaciones.esCarne(e)
      @           ||
      @        !Especificaciones.esCodigo(a)
      @           ||
      @        !this.cursadas.has(new JMLEqualsEqualsPair(
      @              new JMLString(e), 
      @              new JMLString(a))
      @        );
      @    signals_only ExcepcionEstudianteInvalido,
      @                 ExcepcionAsignaturaInvalida,
      @                 ExcepcionCursoNoRegistrado;
      @    signals(ExcepcionEstudianteInvalido) !Especificaciones.esCarne(e);
      @    signals(ExcepcionAsignaturaInvalida) !Especificaciones.esCodigo(a);
      @    signals(ExcepcionCursoNoRegistrado)       
      @         Especificaciones.esCarne(e) && Especificaciones.esCodigo(a)
      @            &&
      @         !this.cursadas.has(new JMLEqualsEqualsPair(
      @                      new JMLString(e), 
      @                      new JMLString(a))
      @                );
      @    assignable \nothing;      
      @*/      
      public void eliminar(final String e, final String a)
             throws ExcepcionEstudianteInvalido, 
                    ExcepcionAsignaturaInvalida,
                    ExcepcionCursoNoRegistrado;                   
               
    /*@ public normal_behavior
      @    requires 
      @        Especificaciones.esCarne(e);
      @    requires 
      @        (\exists JMLEqualsEqualsPair p 
      @             ; this.cursadas.has(p) 
      @             ; p.key.equals(new JMLString(e)));
      @    ensures
      @        (* Esta escrita en pantalla la lista, ordenada por asignatura y 
      @           sin repeticiones, que contiene todos los pares del conjunto 
      @           de asignaturas cursadas por el estudiante de carnet e *);
      @    assignable System.out.output;
      @ also public exceptional_behavior
      @    requires 
      @        !Especificaciones.esCarne(e)
      @           ||
      @        !(\exists JMLEqualsEqualsPair p 
      @              ; this.cursadas.has(p) 
      @              ; p.key.equals(new JMLString(e)));
      @    signals_only ExcepcionEstudianteInvalido,
      @                 ExcepcionEstudianteNoRegistrado;
      @    signals(ExcepcionEstudianteInvalido) !Especificaciones.esCarne(e);
      @    signals(ExcepcionEstudianteNoRegistrado) 
      @        Especificaciones.esCarne(e)
      @           &&
      @        !(\exists JMLEqualsEqualsPair p 
      @              ; this.cursadas.has(p) 
      @              ; p.key.equals(new JMLString(e)));
      @    assignable \nothing;
      @*/                     
      public void ListarAsignaturasPorEstudiante(final String e)        
             throws ExcepcionEstudianteInvalido, 
                    ExcepcionEstudianteNoRegistrado;        

               
    /*@ public normal_behavior
      @    requires 
      @        Especificaciones.esCodigo(a);
      @    requires 
      @        (\exists JMLEqualsEqualsPair p 
      @              ; this.cursadas.has(p) 
      @              ; p.value.equals(new JMLString(a)));
      @    ensures
      @        (* Esta escrita en pantalla la lista, ordenada por carne y sin
      @           repeticiones, que contiene todos los pares del conjunto de
      @           estudiantes que han cursado la asignatura cuyo codigo es a *);
      @    assignable System.out.output;
      @ also public exceptional_behavior
      @    requires 
      @        !Especificaciones.esCodigo(a)
      @           ||
      @        !(\exists JMLEqualsEqualsPair p 
      @              ; this.cursadas.has(p) 
      @             ; p.value.equals(new JMLString(a)));
      @    signals_only ExcepcionAsignaturaInvalida,
      @                 ExcepcionAsignaturaNoRegistrada;
      @    signals(ExcepcionAsignaturaInvalida) !Especificaciones.esCodigo(a);
      @    signals(ExcepcionAsignaturaNoRegistrada) 
      @        Especificaciones.esCodigo(a)
      @           &&
      @        !(\exists JMLEqualsEqualsPair p 
      @              ; this.cursadas.has(p) 
      @             ; p.value.equals(new JMLString(a)));
      @    assignable \nothing;      
      @*/               
      public void ListarEstudiantesPorAsignatura(final String a)    
             throws ExcepcionAsignaturaInvalida,    
                    ExcepcionAsignaturaNoRegistrada;
    
    /*@ public normal_behavior
      @    requires true;
      @    ensures
      @      (* iteradorAsignaturaEstudiante contiene la secuencia ordenada por
      @         estudiante/asignatura y sin repeticiones, formada por todas las  
      @         tripletas del conjunto*);
      @    assignable \nothing;    
      @*/
      public Iterator iteradorEstudianteAsignatura();        

    /*@ public normal_behavior
      @    requires true;
      @    ensures
      @      (* iteradorAsignaturaEstudiante contiene la secuencia ordenada por
      @         asignatura/estudiante y sin repeticiones, formada por todas las 
      @         tripletas del conjunto*);
      @    assignable \nothing;    
      @*/    
      public Iterator iteradorAsignaturaEstudiante();               
}