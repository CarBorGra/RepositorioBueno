package proyectoCatalogoRecetas;

import java.io.*;

public class ObjetosADat {

	public static void main(String[] args) throws ClassNotFoundException {
		Ingrediente zumoNaranja = new Ingrediente(false, 0.0, "naranja", "dulce", "Zumo de naranja");
		Ingrediente agua = new Ingrediente(false, 0.0, "agua", "insipido", "agua");
		try{
			FileOutputStream fos = new FileOutputStream("ingredientes.dat");//Me preparo para escribir
			ObjectOutputStream oos = new ObjectOutputStream(fos);//Me preparo para escribir un objeto serializado
			oos.writeObject(zumoNaranja);
			oos.writeObject(agua);
			oos.close();//Escribo ambos objetos y cierro la transmision
			FileInputStream fis = new FileInputStream("ingredientes.dat");//preparo para leer
			ObjectInputStream ois = new ObjectInputStream(fis);//Preparo para leer objetos serializados
			Ingrediente result1 = (Ingrediente) ois.readObject();
			Ingrediente result2 = (Ingrediente) ois.readObject();
			ois.close(); //Leo ambos objetos y cierro
			System.out.println(result1.nombre);
			System.out.println(result2.nombre);
		}catch (IOException e){
			System.out.println("Problema a la hora de leer/escribir");
		}
	}

}
