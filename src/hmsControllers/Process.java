package hmsControllers;

import hmsDA.HmsDA;
import hmsModels.Category;
import hmsModels.Doctor;
import hmsModels.Employee;
import hmsModels.Nurse;
import hmsModels.Patient;
import hmsModels.Room;
import hmsModels.User;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class EditEmployee
 */
@WebServlet("/Process")
public class Process extends HttpServlet {
	
	HmsDA hms = new HmsDA();
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response){
		try {
			processRequest(request,response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response){
		try {
			processRequest(request,response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException{
		try{
			
			if(request.getParameter("action")!=null){
				
				if(request.getParameter("action").equals("addDoc"))
					addDoc(request,response);
				else if(request.getParameter("action").equals("addNurse"))
					addNurse(request,response);
				else if(request.getParameter("action").equals("addRoom"))
					addRoom(request,response);
				else if(request.getParameter("action").equals("addPatient"))
					addPatient(request,response);
				else if(request.getParameter("id")!=null){
					if(request.getParameter("action").equals("deleteEmp"))
						deleteEmployee(request,response);
					else if(request.getParameter("action").equals("deleteRoom"))
						deleteRoom(request,response);
					else if(request.getParameter("action").equals("deletePatient"))
						deletePatient(request,response);
					else if(request.getParameter("action").equals("editDoc"))
						updateDoc(request,response);
					else if(request.getParameter("action").equals("getDoc"))
						getDoc(request,response);
					else if(request.getParameter("action").equals("getNurse"))
						getNurse(request,response);
					else if(request.getParameter("action").equals("editNurse"))
						updateNurse(request,response);
					else if(request.getParameter("action").equals("getRoom"))
						getRoom(request,response);
					else if(request.getParameter("action").equals("editRoom"))
						updateRoom(request,response);
					else
						request.getRequestDispatcher("index.jsp").forward(request, response);
				}
				else
					request.getRequestDispatcher("index.jsp").forward(request, response);
			}
			else
				request.getRequestDispatcher("index.jsp").forward(request, response);
			
		}
		catch(SQLException ex){
			System.out.print(ex.getMessage());
			response.setStatus(ex.getErrorCode());
			response.setContentType("text/plain");
			response.getWriter().print(ex.getMessage());
			return;
		}
		catch(NumberFormatException e){
			response.setContentType("text/plain");
			response.getWriter().print(e.getMessage());
		}
		catch(IOException e){
			
			e.printStackTrace();
		}
		catch(ServletException ex){
			ex.printStackTrace();
		}
	}
	
	
	
	public void updateNurse(HttpServletRequest request, HttpServletResponse response) throws IOException, NumberFormatException, SQLException{
		
		Nurse nurse = hms.getNurse(Integer.parseInt(request.getParameter("id")));
		Employee employee = nurse.getEmployee();
		User user = employee.getUser();
		
		
		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		user.setType("doctor");
		//hms.updateUser(user);
		
		
		employee.setFirstname(request.getParameter("firstname"));
		employee.setLastname(request.getParameter("lastname"));
		employee.setSalary(Integer.parseInt(request.getParameter("salary")));
		employee.setPhone(request.getParameter("phone"));
		employee.setGender(request.getParameter("gender"));
		employee.setDob(request.getParameter("dob"));
		employee.setUid(user.getUid());
		//hms.updateEmployee(employee);
		
		
		nurse.setExperience(request.getParameter("experience"));
		nurse.setEid(employee.getEid());
		hms.updateNurse(nurse);
		
		response.setContentType("application/json");
		response.getWriter().print(nurse.toJson());
		
	}
	
	
	public void updateDoc(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException{

		int catid = Integer.parseInt(request.getParameter("catid"));
		
		Doctor doctor = hms.getDoctor(Integer.parseInt(request.getParameter("id")));
		Employee employee = doctor.getEmployee();
		User user = employee.getUser();
		
		
		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		user.setType("doctor");
		//hms.updateUser(user);
		
		
		employee.setFirstname(request.getParameter("firstname"));
		employee.setLastname(request.getParameter("lastname"));
		employee.setSalary(Integer.parseInt(request.getParameter("salary")));
		employee.setPhone(request.getParameter("phone"));
		employee.setGender(request.getParameter("gender"));
		employee.setDob(request.getParameter("dob"));
		employee.setUid(user.getUid());
		//hms.updateEmployee(employee);
		
		
		doctor.setCatid(catid);
		doctor.setEid(employee.getEid());
		hms.updateDoctor(doctor);
		
		response.setContentType("application/json");
		response.getWriter().print(hms.getDoctor(Integer.parseInt(request.getParameter("id"))).toJson());
		
		
	}
	
	public void getDoc(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException{
		int did = Integer.parseInt(request.getParameter("id"));
		
		Doctor doc = hms.getDoctor(did);
		
		response.setContentType("application/json");
		response.getWriter().print(doc.toJson());
		
		
	}
	
	public void getRoom(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException{
		int rid = Integer.parseInt(request.getParameter("id"));
		
		Room room = hms.getRoom(rid);
		
		response.setContentType("application/json");
		response.getWriter().print(room.toJson());
		
	}
	
	public void getNurse(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException{
		
		int nid = Integer.parseInt(request.getParameter("id"));
		
		Nurse nurse = hms.getNurse(nid);
		
		response.setContentType("application/json");
		response.getWriter().print(nurse.toJson());
		
	}
	
	
	public void addPatient(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException{
		String fullName = request.getParameter("fullname");
		String gender = request.getParameter("gender");
		String dob = request.getParameter("dob");
		int catid = Integer.parseInt(request.getParameter("catid"));
		
		Patient p = new Patient();
		p.setName(fullName);
		p.setGender(gender);
		p.setDob(dob);
		p.setCatid(catid);
		
		hms.addPatient(p);
		
	}
	
	public void deletePatient(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException{
		
		int pid = Integer.parseInt(request.getParameter("id"));
		hms.deletePatient(pid);

	}
	
	public void deleteRoom(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException{
		int rid = Integer.parseInt(request.getParameter("id"));
		hms.deleteRoom(rid);
		
	}
	
	public void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException{
		
		int uid = Integer.parseInt(request.getParameter("id"));
		hms.deleteEmployee(uid);
	}
	
public void updateRoom(HttpServletRequest request, HttpServletResponse response) throws IOException, NumberFormatException, SQLException{
		
		Room room;
		room = hms.getRoom(Integer.parseInt(request.getParameter("id")));
		room.setNid(Integer.parseInt(request.getParameter("nurseId")));
		room.setNurse(hms.getNurse(room.getNid()));
		room.setTotalBeds(Integer.parseInt(request.getParameter("totalbeds")));
		
		hms.updateRoom(room);
		
		response.setContentType("application/json");
		response.getWriter().print(room.toJson());
		
		System.out.print("room updated."+room.getRid()+room.getNurse().getEmployee().getFirstname());
		
	}
	
	public void addRoom(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException{

		int nid = Integer.parseInt(request.getParameter("nurseId"));
		int totalbeds = Integer.parseInt(request.getParameter("totalbeds"));
		
		Room room = new Room();
		room.setNid(nid);
		room.setTotalBeds(totalbeds);
		Nurse nurse = hms.getNurse(room.getNid());
		room.setNurse(nurse);
		room.setRid(hms.addRoom(room));
		
		
		response.setContentType("application/json");
		response.getWriter().print(room.toJson());
	}
	
	public void addNurse(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException{
		
		User user = new User();
		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		user.setType("nurse");
		//int uid = hms.addUser(nurseUser);
		
		Employee employee = new Employee();
		employee.setFirstname(request.getParameter("firstname"));
		employee.setLastname(request.getParameter("lastname"));
		employee.setSalary(Integer.parseInt(request.getParameter("salary")));
		employee.setPhone(request.getParameter("phone"));
		employee.setGender("female");
		employee.setDob(request.getParameter("dob"));
		employee.setUser(user);
		//employee.setUid(uid);
		//int eid = hms.addEmployee(employee);
		
		
		Nurse nurse = new Nurse();
		//nurse.setEid(eid);
		nurse.setExperience(request.getParameter("experience"));
		nurse.setEmployee(employee);
		int nid = hms.addNurse(nurse);
			
			
			

			response.setContentType("application/json");
			response.getWriter().print(nurse.toJson());
		
	}
	
	private void addDoc(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException{
		
		int catid = Integer.parseInt(request.getParameter("catid"));
		
		User docUser = new User();
		docUser.setUsername(request.getParameter("username"));
		docUser.setPassword(request.getParameter("password"));
		docUser.setType("doctor");
		//hms.addUser(docUser);
		
		Employee employee = new Employee();
		employee.setFirstname(request.getParameter("firstname"));
		employee.setLastname(request.getParameter("lastname"));
		employee.setSalary(Integer.parseInt(request.getParameter("salary")));
		employee.setPhone(request.getParameter("phone"));
		employee.setGender(request.getParameter("gender"));
		employee.setDob(request.getParameter("dob"));
		//employee.setUid(docUser.getUid());
		employee.setUser(docUser);
		//hms.addEmployee(employee);
		
		Doctor doctor = new Doctor();
		doctor.setCatid(catid);
		//doctor.setEid(employee.getEid());
		doctor.setEmployee(employee);
		doctor.setCategory(hms.getCategory(doctor.getCatid()));
		hms.addDoctor(doctor);
		
		response.setContentType("application/json");
		response.getWriter().print(doctor.toJson());
		
	}
}
