package utilidades;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Utilidades {
	public static String introducirCadena() {
		String cadena = "";
		InputStreamReader entrada = new InputStreamReader(System.in);
		BufferedReader teclado = new BufferedReader(entrada);
		try {
			cadena = teclado.readLine();
		} catch (IOException er) {
			System.out.println("error al introducir datos");
			System.exit(0);
		}
		return cadena;
	}
	public static String introducirCadena(String msg) {
		System.out.println(msg);
		String cadena = "";
		InputStreamReader entrada = new InputStreamReader(System.in);
		BufferedReader teclado = new BufferedReader(entrada);
		try {
			cadena = teclado.readLine();
		} catch (IOException er) {
			System.out.println("error al introducir datos");
			System.exit(0);
		}
		return cadena;
	}

	public static char introducirChar(String msg) {
		System.out.println(msg);
		char caracter = 0;
		InputStreamReader entrada = new InputStreamReader(System.in);
		BufferedReader teclado = new BufferedReader(entrada);
		try {
			caracter = teclado.readLine().charAt(0);
		} catch (IOException er) {
			System.out.println("error al introducir datos");
			System.exit(0);
		}
		return caracter;
	}

	public static float leerFloat() {
		float fNumero = 0;
		boolean ok;
		do {
			try {
				ok = true;
				fNumero = Float.parseFloat(introducirCadena());
			} catch (NumberFormatException e) {
				System.out.println("Error al introducir el número");
				ok = false;
			}
		} while (ok == false);
		return fNumero;
	}
	public static float leerFloat(String msg) {
		System.out.println(msg);
		float fNumero = 0;
		boolean ok;
		do {
			try {
				ok = true;
				fNumero = Float.parseFloat(introducirCadena());
			} catch (NumberFormatException e) {
				System.out.println("Error al introducir el número");
				ok = false;
			}
		} while (ok == false);
		return fNumero;
	}

	public static float leerFloat(float x, float y) {
		float fNumero = 0;
		boolean ok;
		do {
			try {
				ok = true;
				fNumero = Float.parseFloat(introducirCadena());
			} catch (NumberFormatException e) {
				System.out.println("Hay que introducir numeros. Vuelve a introducir: ");
				ok = false;
				fNumero = x;
			}
			if (fNumero < x || fNumero > y) {
				System.out.println("Dato fuera de rango. Introduce entre " + x
						+ " y " + y);
				ok = false;
			}
		} while (ok == false);
		return fNumero;
	}
	public static float leerFloat(float x, float y, String msg) {
		System.out.println(msg);
		float fNumero = 0;
		boolean ok;
		do {
			try {
				ok = true;
				fNumero = Float.parseFloat(introducirCadena());
			} catch (NumberFormatException e) {
				System.out.println("Hay que introducir numeros. Vuelve aintroducir: ");
				ok = false;
				fNumero = x;
			}
			if (fNumero < x || fNumero > y) {
				System.out.println("Dato fuera de rando. Introduce entre " + x
						+ " y " + y);
				ok = false;
			}
		} while (ok == false);
		return fNumero;
	}

	public static int leerInt() {
		int iNumero = 0;
		boolean ok;
		do {
			try {
				ok = true;
				iNumero = Integer.parseInt(introducirCadena());
			} catch (NumberFormatException e) {
				System.out.println("hay que introducir numeros");
				ok = false;
			}
		} while (ok == false);
		return iNumero;
	}
	public static int leerInt(String msg) {
		System.out.println(msg);
		int iNumero = 0;
		boolean ok;
		do {
			try {
				ok = true;
				iNumero = Integer.parseInt(introducirCadena());
			} catch (NumberFormatException e) {
				System.out.println("hay que introducir numeros");
				ok = false;
			}
		} while (ok == false);
		return iNumero;
	}

	public static int leerInt(int x, int y) {
		int num = 0;
		boolean ok;
		do {
			try {
				ok = true;
				num = Integer.parseInt(introducirCadena());

			} catch (NumberFormatException e) {
				System.out.println("Hay que introducir numeros");
				ok = false;
				num = x;

			}
			if (num < x || num > y) {
				System.out.println("Dato fuera de rango, introduce entre" + x
						+ " y " + y);
				ok = false;
			}
		} while (ok == false);
		return num;
	}
	public static int leerInt(int x, int y,String msg) {
		System.out.println(msg);
		int num = 0;
		boolean ok;
		do {
			try {
				ok = true;
				num = Integer.parseInt(introducirCadena());

			} catch (NumberFormatException e) {
				System.out.println("Hay que introducir numeros");
				ok = false;
				num = x;

			}
			if (num < x || num > y) {
				System.out.println("Dato fuera de rango, introduce entre" + x
						+ " y " + y);
				ok = false;
			}
		} while (ok == false);
		return num;
	}
	public int anosEntre(Date fechaNaci, Date fecha){
		SimpleDateFormat fanio = new SimpleDateFormat("yyyy");
		SimpleDateFormat fmes = new SimpleDateFormat("MM");
		SimpleDateFormat fdia = new SimpleDateFormat("dd");
		int dNaci, mNaci, aNaci,dia,mes,anio;
		int edad;
		
			dNaci = Integer.parseInt(fdia.format(fechaNaci));
			dia = Integer.parseInt(fdia.format(fecha));
			mNaci = Integer.parseInt(fmes.format(fechaNaci));
			mes = Integer.parseInt(fmes.format(fecha));
			aNaci = Integer.parseInt(fanio.format(fechaNaci));
			anio = Integer.parseInt(fanio.format(fecha));
			edad = anio - aNaci;
			if((mNaci == mes && dia < dNaci) || mes < mNaci ){
				edad--;
			}
			if(fecha.compareTo(fechaNaci) > 0) edad = edad*(-1);
		return edad;
	}
	public int getEdad(Date fechaNaci){
		Date fecha = new Date();
		SimpleDateFormat fanio = new SimpleDateFormat("yyyy");
		SimpleDateFormat fmes = new SimpleDateFormat("MM");
		SimpleDateFormat fdia = new SimpleDateFormat("dd");
		int dNaci, mNaci, aNaci,dia,mes,anio;
		int edad;
		
			dNaci = Integer.parseInt(fdia.format(fechaNaci));
			dia = Integer.parseInt(fdia.format(fecha));
			mNaci = Integer.parseInt(fmes.format(fechaNaci));
			mes = Integer.parseInt(fmes.format(fecha));
			aNaci = Integer.parseInt(fanio.format(fechaNaci));
			anio = Integer.parseInt(fanio.format(fecha));
			edad = anio - aNaci;
			if((mNaci == mes && dia < dNaci) || mes < mNaci ){
				edad--;
			}
			if(fecha.compareTo(fechaNaci) > 0) edad = edad*(-1);
		return edad;
	}
	
	public Date introducirFecha(){
		SimpleDateFormat formatear = new SimpleDateFormat();
		Date fecha = new Date();
		boolean correcto = true;
		do{
			correcto = true;
			System.out.println("Introduce fecha: (dd/mm/aaaa)");
			try{
				fecha = formatear.parse(introducirCadena());
			}catch(ParseException e){
				System.out.println("Introduce el formato adecuado");
				correcto = false;
			}
		}while(!correcto);
		return fecha;
	}
	public Date introducirFecha(String fecha){
		Date fechar = new Date();
		boolean correcto = true;
		SimpleDateFormat formatear = new SimpleDateFormat("dd/MM/yyyy");
		
		do{
			correcto = true;
			try {
				fechar = formatear.parse(fecha);
			} catch (ParseException e) {
				System.out.println("Error, asegurate de introducir el formato correcto.");
				System.out.println("introduce fecha de nuevo:");
				fecha = introducirCadena();
				correcto = false;
			}
			
		}while(!correcto);
		
		return fechar;
	}
	
	
}