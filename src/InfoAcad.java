public class InfoAcad{
    private String estud, asign;
    private int calif;
    
    public InfoAcad(String estud, String asign, int calif){
        this.estud = estud;
        this.asign = asign;
        this.calif = calif;
    }
    
    public /*@ pure @*/ String obtenerEstudiante(){
        return this.estud;
    }
    
    public /*@ pure @*/ String obtenerAsignacion(){
        return this.asign;
    }
    
    public /*@ pure @*/ int obtenerCalificacion(){
        return this.calif;
    }

    /*@ requires true;
      @ ensures \result
      @          == (a.obtenerAsignacion().compareTo(b.obtenerAsignacion()) < 0)
      @                 ||
      @             (a.obtenerAsignacion().equals(b.obtenerAsignacion())
      @                 &&
      @             a.obtenerEstudiante().compareTo(b.obtenerEstudiante()) < 0);
      @*/
    public static /*@ pure @*/ boolean esMenorAsignEs (InfoAcad a, InfoAcad b)
    {
        return 
            (a.obtenerAsignacion().compareTo(b.obtenerAsignacion()) < 0)
                ||
                (a.obtenerAsignacion().equals(b.obtenerAsignacion())
                    &&
                a.obtenerEstudiante().compareTo(b.obtenerEstudiante()) < 0);
    }
    
    
    /*@ requires true;
      @ ensures \result
      @         == (a.obtenerEstudiante().compareTo(b.obtenerEstudiante()) < 0)
      @             ||
      @             (a.obtenerEstudiante().equals(b.obtenerEstudiante())
      @                 &&
      @             a.obtenerAsignacion().compareTo(b.obtenerAsignacion()) < 0);
      @*/
    public static /*@ pure @*/ boolean esMenorEsAsign (InfoAcad a, InfoAcad b)
    {
        return
            (a.obtenerEstudiante().compareTo(b.obtenerEstudiante()) < 0)
                ||
                (a.obtenerEstudiante().equals(b.obtenerEstudiante())
                    &&
                a.obtenerAsignacion().compareTo(b.obtenerAsignacion()) < 0);
    }
}