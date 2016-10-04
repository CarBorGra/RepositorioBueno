package proyectoCatalogoRecetas;
import java.util.ArrayList;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import java.io.*;

public class IngredienteArray {
	
	//Ingrediente(boolean gas, double alcohol, String base, String sabor)
	
	public static void main(String[] args){
		
		Visualizar("ingredientesDat.xml");
		//De declaracion de objetos a ArrayList a XML
		Ingrediente ginebra = new Ingrediente(false, 40.5, "frutos", "amargo", "ginebra");
		Ingrediente ron = new Ingrediente(false, 35.5, "caña de azucar", "amargo", "ron");
		Ingrediente whisky = new Ingrediente(false, 45.5, "granos", "amargo", "whisky");
		Ingrediente fanta = new Ingrediente(true, 0, "fruta y gas", "dulce", "fanta");
		Ingrediente tonica = new Ingrediente(true, 0, "agua y gas", "amargo", "tonica");
		ArrayList<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
		ingredientes.add(ginebra);
		ingredientes.add(ron);
		ingredientes.add(whisky);
		ingredientes.add(fanta);
		ingredientes.add(tonica);
		writeXML(ingredientes, "ingredientesArray.xml");
		
		//De .dat a XML
		ArrayList<Ingrediente> ingredientesDat = new ArrayList<Ingrediente>();
		//Declaracion de objetos por estar sus valores definidos en el try
		ObjectInputStream ois = null;
		Ingrediente ingrediente1 = null;
		Ingrediente ingrediente2 = null;
		try{			
			FileInputStream fis = new FileInputStream("ingredientes.dat");//Abro una transmision para leer ingredientes.dat
			ois = new ObjectInputStream(fis);//Preparo para leer objetos
		}catch(IOException e){
			e.printStackTrace();
		}
		try{
			ingrediente1 = (Ingrediente) ois.readObject();//Leo los objetos
			ingrediente2 = (Ingrediente) ois.readObject();	
			ois.close();//Cierro la transmision
		}catch(ClassNotFoundException | IOException e){
			System.out.println("Error de lectura o de clase erronea");
			return;
		}
		//Los añado al array y uso el metodo para escribir XML con ellos
		ingredientesDat.add(ingrediente1);
		ingredientesDat.add(ingrediente2);
		writeXML(ingredientesDat, "ingredientesDat.xml");
		
	}
	
	public static void writeXML(ArrayList<Ingrediente> conjuntoIngredientes, String nombrearchivo){
		DocumentBuilderFactory parseador = DocumentBuilderFactory.newInstance();//Declaramos un parseador
		try{
			DocumentBuilder constructor = parseador.newDocumentBuilder();//Y un constructor
			DOMImplementation implementation = constructor.getDOMImplementation();
			Document documento = implementation.createDocument(null, "Ingredientes", null); //Y creamos un documento vacio de nombre "Ingredientes"
			documento.setXmlVersion("1.0"); //Y le asignamos version de xml
			//Llegados aqui tenemos un interprete, un constructor y un documento XML con un único nodo, <Ingredientes></Ingredientes>
			//Y utilizo el metodo después creado para llenar ese nodo con la informacion del arrayList
			for (int i=0;i<conjuntoIngredientes.size();i++){
				CrearElemento(conjuntoIngredientes.get(i), documento);
			}
			Source fuente = new DOMSource(documento);//Preparo para escribir los nodos
			Result resultado = new StreamResult(new java.io.File(nombrearchivo));//Preparo la transmision
			Transformer transformador = TransformerFactory.newInstance().newTransformer();//Creo un objeto para transformar a XML
			transformador.transform(fuente, resultado);//Escribo el contenido con formato en el documento
			Result console = new StreamResult(System.out);
			transformador.transform(fuente, console);//Voy mostrando por consola los elementos introducidos
		}catch(Exception e){
			System.out.println("Also ha salido terriblemente mal");
		}
	}
	
	//A partir de un objeto de tipo ingrediente y el documento, creo los nodos y los escribo en el DOM
	static void CrearElemento(Ingrediente ingrediente, Document documento){
		Element bebida = documento.createElement("bebida");
		documento.getDocumentElement().appendChild(bebida);
		Element nodonombre = documento.createElement("nombre");
		bebida.appendChild(nodonombre);
		Element base = documento.createElement("base");
		bebida.appendChild(base);
		Element sabor = documento.createElement("sabor");
		bebida.appendChild(sabor);
		Element gas = documento.createElement("gas");
		bebida.appendChild(gas);
		Element alcohol = documento.createElement("alcohol");
		bebida.appendChild(alcohol);
		Text textonombre = documento.createTextNode(ingrediente.nombre);
		Text textobase = documento.createTextNode(ingrediente.base);
		Text textosabor = documento.createTextNode(ingrediente.sabor);
		Text textogas = documento.createTextNode(String.valueOf(ingrediente.gas));
		Text textoalcohol = documento.createTextNode(String.valueOf(ingrediente.alcohol));
		nodonombre.appendChild(textonombre);
		base.appendChild(textobase);
		sabor.appendChild(textosabor);
		gas.appendChild(textogas);
		alcohol.appendChild(textoalcohol);
	}
	static void Visualizar(String nombrefichero){
		 DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
         //Declaradas a null por estar definidas en bloques try
		 Document doc = null;
         Transformer transformer = null;
         try{
			 DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
	         doc = docBuilder.parse (new File(nombrefichero));
         }catch(ParserConfigurationException | IOException | SAXException e){
        	 System.out.println("Error al abrir el fichero");
        	 return;
         }
         
		try {
			transformer = TransformerFactory.newInstance().newTransformer();
		} catch (TransformerConfigurationException | TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		}
         transformer.setOutputProperty(OutputKeys.INDENT, "yes");
         //initialize StreamResult with File object to save to file
         StreamResult result = new StreamResult(new StringWriter());
         DOMSource source = new DOMSource(doc);
         try {
			transformer.transform(source, result);
		} catch (TransformerException e) {
			e.printStackTrace();
		}
         String xmlString = result.getWriter().toString();
         System.out.println(xmlString);
	}
}
