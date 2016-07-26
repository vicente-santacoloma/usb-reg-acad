/*
 * Archivo: Especificaciones.java
 *
 * Descripci'on: 
 *
 * Fecha: junio 2010
 *
 * Autor: Diego Mosquera 
 *
 * Revisi'on: Jes'us Ravelo y Kelwin Fernandez
 *
 * Version: 1.0
 */
 
 
package regAcadUtil;

public class Especificaciones
{
    /*@ requires true;
      @ ensures \result <==> x.length() == 7 
      @         &&
      @        (\forall int i ; 0 <= i && i < x.length() 
      @                        ; '0' <= x.charAt(i) 
      @                          && 
      @                          x.charAt(i) <= '9' 
      @          );          
      @*/
    public /*@ pure @*/ static boolean esCarne(String x){
        boolean r = (x != "") && (x.length() == 7);
        for(int i = 0; i < x.length() && r; i++)
            r = (('0' <= x.charAt(i)) && (x.charAt(i) <= '9'));
        return r;    
    }
    
    /*@ requires true;
      @ ensures \result <==> x.length() == 6 
      @         &&
      @        (\forall int i ; 0 <= i && i < 2 
      @                        ; 'A' <= x.charAt(i)
      @                          && 
      @                          x.charAt(i) <= 'Z'
      @          )
      @         &&
      @        (\forall int i ; 2 <= i && i < 6 
      @                        ; '0' <= x.charAt(i)
      @                          && 
      @                          x.charAt(i) <= '9'
      @          );                 
      @*/
    public /*@ pure @*/ static boolean esCodigo(String x){
        boolean r = (x != "");
        r = r && (x.length() == 6);
        r = r && ('A' <= x.charAt(0)) && (x.charAt(0) <= 'Z');
        r = r && ('A' <= x.charAt(1)) && (x.charAt(1) <= 'Z');
        for(int i = 2; i < x.length() && r; i++)
            r = (('0' <= x.charAt(i)) && (x.charAt(i) <= '9'));
        return r;
    }
}
