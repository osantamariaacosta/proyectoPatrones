package ac.cr.cenfotec.multis;

import ac.cr.cenfotec.clases.Department;

import java.util.ArrayList;

import com.cenfotec.AccesoDatos.*; 


public class MultiDepartament {
	
	public boolean registerDepartament(String name, int id, String description, int companyId)
	{
		
		String query;
		query = "Insert Into TDepartament (Name, Id, Description, CompanyId) VALUES ('" + name + "','" + id + "','"
				+ description + "','" + companyId + "')";
		
		try {
			
			AccesoBD accesoDatos;
			accesoDatos = Conector.getConector();
			accesoDatos.ejecutarSQL(query);
			return true;
		}catch (Exception error) {
			System.out.println(error);
			System.out.println(error.getMessage());
			return false;
		}
	}
	
	 public ArrayList<Department> listarUser() throws Exception 
	 {
	        ArrayList<Department> lista = new ArrayList<>();

	        String query;
	        query = "SELECT * FROM TDepartament";

	        try {
	        	/*
	            AccesoBD accesoDatos;
	            accesoDatos = Conector.getConector();
	            ResultSet rs = accesoDatos.ejecutarSQL(query);
	            while (rs.next()) {
	                Departament tmpDepartament = new Departament();

	                tmpDepartament.setName(rs.getString("name"));
	                tmpDepartament.setId(rs.getString("lastName"));
	                tmpDepartament.setDescription(rs.getInt("id"));
	                tmpDepartament.setCompanyId(rs.getString("company"));
	            }*/
	        	return lista;
	        } catch (Exception error) {
	            System.out.println(error);
	            System.out.println(error.getMessage());
				return lista;
	        }

	    }
	

}
