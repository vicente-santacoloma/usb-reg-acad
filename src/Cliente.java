import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import regAcadUtil.*;
import java.util.*;

public class Cliente 
{
	public static void Menu()
	{	
		int [] k=new int [1];
		int opcion=0;
		System.out.println();
		int N=Console.readInt("Registros a Crear: ");
		RegAcadArray [] r= new RegAcadArray[N];
		while(opcion!=11) 
		{
			System.out.println();
			imprimirMenu();
			System.out.println();
			opcion=Console.readInt("Introduzca Opcion= ");
			System.out.println();
			try
			{
				switch(opcion) 
				{
					case 1: crearRegistro(N,r,k);
							break;
					case 2: registrosDisponibles(k);
							agregarInfo(r);
							break;
					case 3: registrosDisponibles(k);
							eliminarInfo(r);
							break;
					case 4: registrosDisponibles(k);
							listarAC(r);
							break;
					case 5: registrosDisponibles(k);
							listarEC(r); 
							break;
					case 6: registrosDisponibles(k);
							leer(r);
							break;
					case 7: registrosDisponibles(k);
							listarEA(r);
							break;
					case 8: registrosDisponibles(k);
							listarAE(r);
							break;
					case 9: System.out.println("Sorpresa 1");
							break;
					case 10: System.out.println("Sorpresa 2");
							break;
					case 11: System.out.println("Se ha salido del programa");
							break;
				}
			}
			catch (ExcepcionEstudianteInvalido causa) {System.out.println("El carne es invalido! Intente nuevamente");}
			catch (ExcepcionAsignaturaInvalida causa) {System.out.println("La asignatura es invalida! Intente nuevamente");}
			catch (ExcepcionCalificacionInvalida causa) {System.out.println("La calificacion es invalida! Intente nuevamente");}
			catch (ExcepcionCursoNoRegistrado causa) {System.out.println("El curso no esta registrado! Intente nuevamente");}
			catch (ExcepcionEstudianteNoRegistrado causa) {System.out.println("El estudiante no esta registrado! Intente nuevamente");}
			catch (ExcepcionAsignaturaNoRegistrada causa) {System.out.println("La asignatura no esta registrada! Intente nuevamente");}
			catch (ExcepcionCalificacionYaRegistrada causa) {System.out.println("La calificacion ya esta registrada! Intente nuevamente");}
		}
	}
	
	private static void imprimirMenu()
	{
		System.out.println("1.  Crear un registro vacio");
		System.out.println("2.  Agregar informacion academica");
		System.out.println("3.  Eliminar informacion academica");
		System.out.println("4.  Listar asignaturas/califaciones de un estudiante");
		System.out.println("5.  Listar estudiantes/calificaciones de una asignatura");
		System.out.println("6.  Cargar un registro academico completo desde un archivo");
		System.out.println("7.  Listar toda la informacion contenida en un registro academico ordenada por estudiante/asignatura");
		System.out.println("8.  Listar toda la informacion contenida en un registro academico ordenada por asignatura/estudiante");
		System.out.println("9.  Procedimiento sorpresa1");
		System.out.println("10. Procedimiento sorpresa2");
		System.out.println("11. Salir del programa");
	}
	
	private static void crearRegistro(int N, RegAcadArray [] r, int [] k)
	{	
	    System.out.println("Implementacion del RegAcad: ");
		System.out.println();
		System.out.println("1.Arreglos");
		System.out.println("2.Arboles");
		System.out.println();
		int implementacion=Console.readInt("Implementacion: ");
		System.out.println("-----------------------------------------");
		if (implementacion==1)
		{
		RegAcadArray reg = new RegAcadArray ();
			if (k[0]!=N)
			{
			r[k[0]]=reg;
			k[0]++;
			System.out.println("Se ha creado un nuevo registro con exito!");

			}
			else if (k[0]==N)
			{
			System.out.println("Limite excedido de registros");
			}
		else if (implementacion==2)
		{
		System.out.println("No se ha desarrollado");
		}
		}
	}
	
	private static void agregarInfo(RegAcadArray [] r) 
		throws ExcepcionEstudianteInvalido, 
			   ExcepcionAsignaturaInvalida, 
			   ExcepcionCalificacionInvalida, 
			   ExcepcionCalificacionYaRegistrada
	{
		int N=Console.readInt("En cual registro desea agregar la informacion?");
		System.out.println();
		String e=Console.readString("Introduzca Carnet: ");
		String a=Console.readString("Introduzca Asignatura: ");
		int c=Console.readInt("Introduzca Calificacion: ");
		r[N].agregar(e,a,c);
		System.out.println("-----------------------------------");
		System.out.println("Informacion agregada con exito!");
	}
	
	private static void eliminarInfo (RegAcadArray [] r) 
		throws ExcepcionEstudianteInvalido, 
			   ExcepcionAsignaturaInvalida, 
			   ExcepcionCursoNoRegistrado
	{
		int N=Console.readInt("En cual registro desea eliminar la informacion?");
		System.out.println();
		String e=Console.readString("Introduzca Carnet: ");
		String a=Console.readString("Introduzca Asignatura: ");
		System.out.println("-----------------------------------");
		r[N].eliminar(e,a);
		System.out.println("Informacion eliminado con exito!");
	}
	
	private static void listarAC (RegAcadArray [] r) 
		throws ExcepcionEstudianteInvalido, 
			   ExcepcionEstudianteNoRegistrado
	{
		int N=Console.readInt("En cual registro desea listar asignaturas por estudiantes?");
		System.out.println();
		String e=Console.readString("Introduzca Carnet: ");
		System.out.println("-----------------------------------");
		r[N].ListarAsignaturasPorEstudiante(e);
	}
	
	private static void listarEC (RegAcadArray [] r) 
		throws ExcepcionAsignaturaInvalida, 
			   ExcepcionAsignaturaNoRegistrada
	{
		int N=Console.readInt("En cual registro desea listar estudiantes por asignatura?");
		System.out.println();
		String a=Console.readString("Introduzca Asignatura: ");
		System.out.println("-----------------------------------");
		r[N].ListarEstudiantesPorAsignatura(a);
	}
	
	private static void listarEA(RegAcadArray [] r) 
	{
		int N=Console.readInt("Registro que desea listar:");
		System.out.println();
		r[N].ListarTodaEstudiantePorAsignatura();
	}
	private static void listarAE(RegAcadArray [] r)
	{
		int N=Console.readInt("Registro que desea listar:");
		System.out.println();
		r[N].ListarTodaAsignaturaPorEstudiantes();
	}
	
	private static void registrosDisponibles (int [] k)
	{
	System.out.println("Registros Disponibles:");
	for(int i=0; i<k[0];i++)
	System.out.println("Registro["+i+"]");
	System.out.println();
	}

    public static void main(String args[])
	{
		Menu();	
	}

	private static void read(String name, RegAcadArray [] r) 
		throws ExcepcionEstudianteInvalido, 
			   ExcepcionAsignaturaInvalida, 
			   ExcepcionCalificacionInvalida, 
			   ExcepcionCalificacionYaRegistrada
	{
		int N=Console.readInt("Registro en donde desea agregar: ");
		BufferedReader apunt=null;
		String actual=" ";
		int i=0;
		try {
			apunt= new BufferedReader(new FileReader(name));
			}
		catch (IOException e) {
			System.out.println("El archivo no existe");
			System.exit(1);
			}
		while(actual!=null)
		{
			try {
				actual=apunt.readLine();
				}
			catch (IOException e) {
				System.out.println("Error");
				actual=null;
				}
			if (actual!=null)
			{
			try{
				String e=actual.substring(0,7);
				String a=actual.substring(8,14);
				int c=Integer.parseInt(actual.substring(15,17).trim());
				r[N].agregar(e,a,c);
				i++;
				}
				catch (ExcepcionEstudianteInvalido causa) {System.out.println("El carne es invalido! Intente nuevamente");}
				catch (ExcepcionAsignaturaInvalida causa) {System.out.println("La asignatura es invalida! Intente nuevamente");}
				catch (ExcepcionCalificacionInvalida causa) {System.out.println("La calificacion es invalida! Intente nuevamente");}
				catch (ExcepcionCalificacionYaRegistrada causa) {System.out.println("La calificacion ya esta registrada! Intente nuevamente");}
			}
		}
		System.out.println("-----------------------------------");
		// System.out.println("Informacion agregado exitosamente!");
	}
	
	private static void leer(RegAcadArray [] r) 
		throws ExcepcionEstudianteInvalido, 
			   ExcepcionAsignaturaInvalida, 
			   ExcepcionCalificacionInvalida, 
			   ExcepcionCalificacionYaRegistrada
	{
		String name=Console.readString("Introduzca el nombre del archivo: ");
		System.out.println();
		read(name,r);
	}
	
}
