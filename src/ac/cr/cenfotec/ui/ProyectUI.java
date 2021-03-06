package ac.cr.cenfotec.ui;

// UI imports 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import ac.cr.cenfotec.gestores.*;
import java.sql.SQLException;

import ac.cr.cenfotec.clases.Company;
import ac.cr.cenfotec.clases.Department;
import ac.cr.cenfotec.clases.Employee;
import ac.cr.cenfotec.clases.Procedure;
import ac.cr.cenfotec.clases.Task;

import java.util.ArrayList;

//Manager imports
import ac.cr.cenfotec.managers.Authenticator_Manager;

public class ProyectUI {

	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static PrintStream out = System.out;
	static Authenticator_Manager authManager = new Authenticator_Manager();
	static GestorProcedure procedManag = new GestorProcedure();
	
	static GestorDepartament gestorDep = new GestorDepartament();
	
	
	public static void main(String[] args) throws Exception
	{
		showMainMenu();
	}
	
	
	public static void showMainMenu () throws Exception
	{
		boolean end = false;
		
		do {
			try {
				
				end = showLoginMenu();
				if (end) {
					showSystemMenu();
				} else {
					System.out.println("Credenciales invalidas, por favor intente nuevamente");
				}				
				
			} catch (Exception error) {
				
				System.out.println(error);
				System.out.println(error.getMessage());
				
			}
		} while (end !=true);

	}
	
	
	public static boolean showLoginMenu () throws IOException, Exception
	{
		
		String userName, password;
		boolean isAuthenticated = false;
		
		out.println("Formulario inicio de sesion");
		out.println("Ingrese su nombre de usuario");
		userName = in.readLine();
		out.println("Ingrese su contrasena");
		password = in.readLine();
		
		isAuthenticated = verifyCredentials(userName, password);
		
		if (isAuthenticated) 
		{
				return true;				
			
		} else {
			
				return false;
				
			}
	}
	
	
	public static boolean verifyCredentials(String userName, String password) throws Exception 
	{
		return authManager.validateCredentials(userName, password);
	}
	
	
	public static void showSystemMenu () {
		if (authManager.getUserAutenticated().getUserType() == 0) {
			showAdminMenu();
		} else if (authManager.getUserAutenticated().getUserType() == 1) {
			showUserMenu();
		}
	}	
	
	
	public static void showAdminMenu() 
	{
		int option;
		boolean end = false;
		
		do {
			try {
				showAdminOptions();
				option = readOption();
				end = excuteAdminOption(option);
			}
			catch (Exception error) {
				System.out.println(error);
				System.out.println(error.getMessage());
			}
			
		}while(end != true);
	}	
	

	public static void showAdminOptions ()
	{
		out.println("----------------------------");
		out.println("Bienvenido! Seleccione la opci�n de su preferencia");
		out.println("----------------------------");
		
		out.println("Seleccione una opci\u00f3n");
		out.println("1.Registrar Organizaci\u00f3n");
		out.println("2.Registrar un usuario");
		out.println("3.Registrar un proceso");
		out.println("4.Registrar tarea");
		out.println("5.Modificar tarea");
		out.println("6.Ver historial de tareas");
		out.println("7.Listar empleados");
		out.println("8.Listar compa�ias");
		out.println("9.Listar Departamentos");
		out.println("10.Listar Tareas");
		out.println("11.Cerrar Sesi\u00f3n");
	}
	
	public static void showUserMenu() 
	{
		int option;
		boolean end = false;
		
		do {
			try {
				showUserOptions();
				option = readOption();
				end = excuteUserOption(option);
			}
			catch (Exception error) {
				System.out.println(error);
				System.out.println(error.getMessage());
			}
			
		}while(end != true);
	}	
	
		
	public static void showUserOptions() 
	{
		out.println("----------------------------");
		out.println("Bienvenido! Seleccione la opci�n de su preferencia");
		out.println("----------------------------");
		
		out.println("Seleccione una opci\u00f3n");
		out.println("1.Modificar tarea");
		out.println("2.Ver historial de tareas");
		out.println("3.Cerrar Sesi\u00f3n");		
	}
	

	public static int readOption() throws NumberFormatException, IOException 
	{
		int option;
		out.println("Ingrese la opci\u00f3n que desea realizar");
		option = Integer.parseInt(in.readLine());
		return option;
	}	
	
	
	public static boolean excuteUserOption (int option) throws InterruptedException, IOException, Exception 
	{
		boolean end = false;
		
		switch (option) {
			
			case 1:
				break;
				
			case 2: 
				break;
			
			case 3:
				closeSession(); 
				break;
			
			default: 
				break;
				
		}
		
		return end; 
	}
	

	public static boolean excuteAdminOption (int option) throws Exception
	{
		boolean end = false;
		
		switch (option) {
			
			case 1:
				registrarCompania();
				break;
				
			case 2: 
				registerUser();
				break;
			
			case 3:
				registerProcedure();
				break;
				
			case 4:
				registerTask();
				break;
				
			case 5: 
				break;
			
			case 6:
				break;
				
			case 7:
				listarEmpleado(); 
				break;
			case 8:
				listarCompania(); 
				break;
			case 9:
				listarDepartamento(); 
				break;
			case 10:
				listTasks(); 
				break;	
			case 11:
				closeSession(); 
				break;
			
			default: 
				break;
				
		}
		
		return end; 
	}	
	
	
	public static void registerProcedure() throws IOException 
	{
		String name, description;
		String companyId;
		boolean registered;
		try {

			out.println("Ingrese el nombre del nuevo proceso");
			name = in.readLine();
			out.println("Ingrese la descripcion del nuevo proceso");
			description = in.readLine();
			
			out.println("----");
			out.println(" ");
			out.println("Lista de companias");
			out.println("Seleccione una compania de la lista");
			listarCompania();
			out.println("");
			out.println("----");
			
			out.println("Ingrese el identificador de la compania a la que pertenece el proceso");
			companyId = in.readLine();
			registered = procedManag.registerProcedure(name, companyId, description);
			confirmProcess(registered);
			
		} catch (Exception error) {
			System.out.println("Error en el metodo registrar proceso UI");
			System.out.println(error.getMessage());
		}
		
	}
	
	public static void listProcedures()
	{
		
		
		ArrayList<Procedure> lista;
		
		String name;
		String companyId;
		String description;
		
		lista = procedManag.getRegisteredProcedures();
		
		for(int i = 0; i <lista.size(); i++) {
			name = lista.get(i).getName();
			companyId = lista.get(i).getCompanyId();
			description = lista.get(i).getDescription();
			
			
			out.println("Nombre del proceso: " + name + " Compania: " + companyId + " Description: " + description );
		}
		
	}
	
	
	public static void listTasks()
	{
		
		
		ArrayList<Task> lista;
		
		int id;
		String name;
		String idUsuario;
		String procedureName;
		String description;
		int state;
		String nameState;
		String asignado;
		
		lista = procedManag.getResgisteredTasks();
		
		for(int i = 0; i <lista.size(); i++) {
			id = lista.get(i).getId();
			name = lista.get(i).getName();
			idUsuario = lista.get(i).getIdUsuario();
			description = lista.get(i).getDescription();
			state = lista.get(i).getState();
			
			if (state == 0)
			{
				nameState = "Pendiente";
			} else {
				nameState = "Finalizada";
			}
			
			if (idUsuario.equals(" "))
			{
				asignado = "Sin asignar";
			} else {
				asignado = idUsuario;
			}
			
			out.println("Numero de tarea: " + id + " Nombre: " + name + " Usuario Asignado: " + asignado + " Description: " + description + " Estado: " + nameState );
		}
		
	}	
	
	public static void registerTask() 
	{
		String name, nameProcedure, description;
		String idUsuario = " ";
		int id, state;
		Boolean confirm;
		
		try {

			out.println("Ingrese el nombre de la nueva tarea");
			name = in.readLine();
			out.println("Ingrese la descripcion de la nueva tarea");
			description = in.readLine();
			
			out.println("----");
			out.println(" ");
			out.println("Lista de procesos");
			listProcedures();
			out.println("");
			out.println("----");
			
			out.println("Ingrese el nombre del proceso al que desea asignar la tarea");
			nameProcedure = in.readLine();

			double range = (0 - 100000);     
			id =  (int) ((Math.random() * range) + 100000);
			
			System.out.println(id);
			
			state = 0;
			
			confirm = procedManag.addTask(id, name, idUsuario, nameProcedure, description, state);
			confirmProcess(confirm);
			
		} catch (Exception error) {
			System.out.println("Error en el metodo registrar tarea UI");
			System.out.println(error.getMessage());
		}		
			
	}
	
	public static void confirmProcess(boolean confirmProcedure) {
		if (confirmProcedure) {
			out.println("El proceso se ha realizado correctamente");
		} else {
			out.println("Ha ocurrido un error al realizar el proceso");
		}
	} 

	public static void closeSession () throws Exception
	{
		authManager.clearUserAuthenticated();
		clearConsole();
		showMainMenu();
	}
	
	
	public final static void clearConsole() throws Exception
	{
	    try
	    {
	        final String os = System.getProperty("os.name");

	        if (os.contains("Windows"))
	        {
	            Runtime.getRuntime().exec("cls");
	        }
	        else
	        {
	            Runtime.getRuntime().exec("clear");
	        }
	    }
	    catch (final Exception e)
	    {
	        //  Handle any exceptions.
	    }
	}
	
	public static void registrarCompania() throws IOException, Exception{
		String legalNumber;
		String name;
		String description;
		Boolean confirm;
		
		out.println("Digite la cedula juridica");
		legalNumber = in.readLine();
		
		out.println("Digite el nombre de la compa�ia");
		name = in.readLine();
		
		out.println("Digite la desripci� de la compa�ia");
		description = in.readLine();
		
		GestorCompany gestor = new GestorCompany();
		 try {
			 confirm =  gestor.registerCompany(legalNumber, name, description);
			 confirmProcess(confirm);
	        } catch (java.lang.ClassNotFoundException e) {
	            out.println(e.getMessage());
	        } catch (SQLException e) {
	            out.println(e.getMessage());
	            out.println(e.getErrorCode());
	        }
		
	}
	
	public static void registerUser() throws IOException, Exception{
		 String name;
		 String lastName;
		 int id;
		 String password; 
		 String userName;
		 int userType;
		 String firm;
		 String company;
		 String departament;
		 Boolean confirm;
		 
		 out.println("Digite el nombre del usuario");
		 name = in.readLine();
		 out.println("Digite el apellido");
		 lastName = in.readLine();
		 out.println("Digite el numero de indentificaci�n");
		 id = Integer.parseInt(in.readLine());
		 out.println("Digite una contrase�a temporal para el usuario");
		 password = in.readLine();
		 out.println("Digite un nombre de usuario");
		 userName = in.readLine();
		 out.println("Digite el tipo de usuario");
		 userType = Integer.parseInt(in.readLine());
		 out.println("Firma del usuario");
		 firm= in.readLine();
		 out.println("Digite la compa�ia del usuario");
		 company = in.readLine();
		 out.println("Digite el departamento en el que trabaja el usuario");
		 departament = in.readLine();
		 
		 
		 GestorUser gestor = new GestorUser();
	        try {
	            confirm = gestor.registerUser(name, lastName, id, password, userName, userType, firm, company, departament);
	            confirmProcess(confirm);
	        } catch (java.lang.ClassNotFoundException e) {
	            out.println(e.getMessage());
	        } catch (SQLException e) {
	            out.println(e.getMessage());
	            out.println(e.getErrorCode());
	        }
	        
		 }
	
	public static void listarEmpleado() throws IOException, Exception{
		GestorUser gestor = new GestorUser();
		
		ArrayList<Employee> lista;
		
		lista = gestor.listarEmpleado();
		
		String name; 
		String lastName;
		int id; 
		String company;
		String departamentId;
		
		for(int i = 0; i <lista.size(); i++) {
			name = lista.get(i).getName();
			lastName = lista.get(i).getLastName();
			id = lista.get(i).getId();
			company = lista.get(i).getCompany();
			departamentId = lista.get(i).getDepartamentId();
			
			out.println("Nombre: " + name + " Apellido: " + lastName + " id: " + id + " Compa�ia: " + company + "  Departamento: " + departamentId);
		}
	
	}
	
	public static void listarCompania() throws IOException, Exception{
		GestorCompany gestor = new GestorCompany();
		
		ArrayList<Company> lista;
		
		lista = gestor.listarCompania();
		
		String legalNumber; 
		String name;
		String description;
		
		for(int i = 0; i <lista.size(); i++) {
			legalNumber = lista.get(i).getLegalNumber();
			name = lista.get(i).getName();
			description = lista.get(i).getDescription();
			
			
			out.println("Numero de Cedula: " + legalNumber + " Nombre: " + name + " Description: " + description );
		}
	
	}
	
	public static void registerDepartament() throws IOException, Exception{
		 String name;
		 int id;
		 String description;
		 int companyId; 
		 
		 
		 /*out.println("Digite el nombre del departamento");
		 name = in.readLine();
		 out.println("Digite el id del departamento");
		 lastName = Integer.parseInt(in.readLine());
		 out.println("Digite una breve descripci�n el departamento");
		 description = in.readLine;
		 out.println("Digite el id de la compa�ia a la que pertenece el departamento");
		 companyId = Integer.parseInt(in.readLine());
		
	
		 
		 GestorDepartament gestor = new GestorDepartament();
	        try {
	            gestor.registerDepartament(name, id, description, companyId );
	        } catch (java.lang.ClassNotFoundException e) {
	            out.println(e.getMessage());
	        } catch (SQLException e) {
	            out.println(e.getMessage());
	            out.println(e.getErrorCode());
	        }*/
	        
		 }
	
	public static void listarDepartamento() throws IOException, Exception{
		
		
		ArrayList<Department> lista;
		
		 lista = gestorDep.listarDepartamento();
		
		 String name;
		 int id;
		 String description;
		 int companyId; 
		
		for(int i = 0; i <lista.size(); i++) {
			name = lista.get(i).getName();
			id = lista.get(i).getId();
			description = lista.get(i).getDescription();
			companyId = lista.get(i).getCompanyId();
			
			
			out.println("Name: " + name + " Id: " + id + " Description: " + description + "ComapnyId" + companyId );
		}
	
	}
	
	
}
