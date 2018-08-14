package ac.cr.cenfotec.gestores;

import ac.cr.cenfotec.multis.MultiDepartament;
import java.util.ArrayList;

import ac.cr.cenfotec.clases.Company;
import ac.cr.cenfotec.clases.Departament;

public class GestorDepartament {

	MultiDepartament multi = new MultiDepartament();

	public boolean  registerDepartament(String name, int id, String description, int companyId) 
			throws Exception
	{	
		boolean isRegistered;
		try {
		
			isRegistered = multi.registerDepartament(name, id, description, companyId);
			return isRegistered;
			
		} catch (Exception error) {
			System.out.println(error);
			System.out.println(error.getMessage());
			isRegistered = false;
			return false;
		}
	}
	
	public ArrayList<Departament> listarDepartamento() throws java.lang.ClassNotFoundException, java.sql.SQLException, Exception 
	{
	    ArrayList<Departament> lista;
	    lista = multi.listarDepartamento();
	    return lista;
	}
}
