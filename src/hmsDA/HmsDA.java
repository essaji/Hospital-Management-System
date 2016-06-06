package hmsDA;

import hmsModels.Category;
import hmsModels.Doctor;
import hmsModels.Employee;
import hmsModels.Indoor;
import hmsModels.Medicine;
import hmsModels.Nurse;
import hmsModels.Patient;
import hmsModels.Prescription;
import hmsModels.Room;
import hmsModels.User;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.google.gson.JsonElement;

public class HmsDA {
	
	Connection con;
	
	public HmsDA(){
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hms2","root","");
			
			
			
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public Employee getEmployee(int eid){
		Employee emp = new Employee();
		try{
			Statement stmt2 = con.createStatement();
			ResultSet set2 = stmt2.executeQuery("SELECT * FROM employee WHERE eid="+eid);
			if(!set2.next()) return null;
			
			emp.setFirstname(set2.getString("firstname"));
			emp.setLastname(set2.getString("lastname"));
			emp.setGender(set2.getString("gender"));
			emp.setDob(set2.getString("dob"));
			emp.setPhone(set2.getString("phone"));
			emp.setSalary(set2.getInt("salary"));
			emp.setUid(set2.getInt("uid"));
			emp.setEid(set2.getInt("eid"));
			emp.setUser(getUser(emp.getUid()));
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return emp;
	}
	
	public User getUser(int uid){
		User u = new User();
		try{
			Statement stmt3 = con.createStatement();
			ResultSet set3 = stmt3.executeQuery("SELECT * FROM users WHERE uid="+uid);
			if(!set3.next()) return null;
			
			u.setUsername(set3.getString("username"));
			u.setPassword(set3.getString("password"));
			u.setUid(set3.getInt("uid"));
			u.setType(set3.getString("type"));
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return u;
	}
	public Category getCategory(int catid) throws SQLException{
		Category cat = new Category();

		Statement stmt4 = con.createStatement();
		ResultSet set4 = stmt4.executeQuery("SELECT * FROM category WHERE catid="+catid);
		if(!set4.next()) return null;
		
		cat.setCatid(catid);
			cat.setName(set4.getString("name"));
		return cat;
	}
	
	public Category getCategory(String name){
		Category cat = new Category();
		try{
			Statement stmt4 = con.createStatement();
			ResultSet set4 = stmt4.executeQuery("SELECT * FROM category WHERE name='"+name+"'");
			if(!set4.next()) return null;
			
			cat.setCatid(set4.getInt("catid"));
			cat.setName(set4.getString("name"));
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return cat;
	}
	
	public Doctor getDoctor(int did) throws SQLException{
		Doctor doc = new Doctor();

		Statement stmt3 = con.createStatement();
		ResultSet set3 = stmt3.executeQuery("SELECT * FROM doctor WHERE did="+did);
		if(!set3.next()) return null;
		doc.setDid(set3.getInt("did"));
		doc.setCatid(set3.getInt("catid"));
		doc.setEid(set3.getInt("eid"));
		
		doc.setCategory(getCategory(doc.getCatid()));
		doc.setEmployee(getEmployee(doc.getEid()));
		return doc;
	}
	
	public Patient getPatient(int pid) throws SQLException{
		Patient patient = null;
		ResultSet set = con.createStatement().executeQuery("SELECT * FROM patient WHERE pid="+pid);
		while(set.next()){
			patient = new Patient();
			patient.setPid(set.getInt("pid"));
			patient.setName(set.getString("name"));
			patient.setGender(set.getString("gender"));
			patient.setDob(set.getString("dob"));
			patient.setType(set.getString("type"));
			patient.setCatid(set.getInt("catid"));
			patient.setDid(set.getInt("did"));
			
			patient.setCategory(getCategory(patient.getCatid()));
			
			if(patient.getDid()==0){
				patient.setDoctor(null);
			}
			else
			{
				patient.setDoctor(getDoctor(patient.getDid()));
			}
		}
		return patient;
	}
	public Room getRoom(int rid) throws SQLException{
		Room room = new Room();
		ResultSet set = con.createStatement().executeQuery("SELECT * FROM room WHERE rid="+rid);
		
		if(!set.next()) return null;
		room.setRid(set.getInt("rid"));
		room.setTotalBeds(set.getInt("totalbeds"));
		room.setNid(set.getInt("nid"));
		room.setAvailableBeds(set.getInt("availablebeds"));
		room.setNurse(getNurse(room.getNid()));
		return room;
	}
	
	public Nurse getNurse(int nid) throws SQLException{
		Nurse nurse = new Nurse();
		Statement stmt = con.createStatement();
		ResultSet set = stmt.executeQuery("SELECT * FROM nurse WHERE nid="+nid);
		if(!set.next()) return null;
		nurse.setEid(set.getInt("eid"));
		nurse.setNid(nid);
		nurse.setExperience(set.getString("experience"));
		nurse.setEmployee(getEmployee(nurse.getEid()));
		return nurse;
	}
	
	public ArrayList<Doctor> getDoctorList(){
		
		ArrayList<Doctor> docList = new ArrayList<Doctor>();
		/*Doctor doc = new Doctor();
		Employee emp = new Employee();
		Category cat = new Category();*/
		Statement stmt;
		
		try{
			stmt = con.createStatement();
			ResultSet set = stmt.executeQuery("SELECT * FROM doctor");
			while(set.next()){
				Doctor doc = new Doctor();
				int did = set.getInt("did");
				int catid = set.getInt("catid");
				int eid = set.getInt("eid");
				
				doc.setDid(did);
				doc.setCatid(catid);
				doc.setEid(eid);
				doc.setEmployee(getEmployee(eid));
				doc.setCategory(getCategory(catid));
				docList.add(doc);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return docList;
	}
	
	public ArrayList<Patient> getPatientList(){
		
		ArrayList<Patient> patientList = new ArrayList<Patient>();
		
		try{
			Statement stmt = con.createStatement();
			ResultSet set = stmt.executeQuery("SELECT * FROM patient");
			
			patientList = getPatientListFromSet(set);
		}
		catch(SQLException ex){
			ex.printStackTrace();
		}
		return patientList;
	}
	
	public ArrayList<Patient> getPatientListFromSet(ResultSet set) throws SQLException{
		
		ArrayList<Patient> patientList = new ArrayList<Patient>();
		
		while(set.next()){
			Patient patient = new Patient();
			patient.setPid(set.getInt("pid"));
			patient.setName(set.getString("name"));
			patient.setDob(set.getString("dob"));
			patient.setGender(set.getString("gender"));
			patient.setType(set.getString("type"));
			
			int catid = set.getInt("catid");
			int did = set.getInt("did");
			
			patient.setCatid(catid);
			patient.setDid(did);
			
			patient.setCategory(getCategory(catid));
			
			if(did==0)
				patient.setDoctor(null);
			else
			{
				patient.setDoctor(getDoctor(did));
			}
			
			patientList.add(patient);
		}
		
		return patientList;
	}
	
	public ArrayList<Nurse> getNurseList(){
		ArrayList<Nurse> NurseList = new ArrayList<Nurse>();
		
		try{
			Statement stmt = con.createStatement();
			ResultSet set = stmt.executeQuery("SELECT * FROM nurse");
			while(set.next()){
				Nurse n = new Nurse();
				Employee emp = new Employee();
				
				n.setEid(set.getInt("eid"));
				n.setNid(set.getInt("nid"));
				n.setExperience(set.getString("experience"));
				n.setEmployee(getEmployee(n.getEid()));
				
				NurseList.add(n);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return NurseList;
	}
	
	public ArrayList<Room> getRoomList(){
		ArrayList<Room> roomList = new ArrayList<Room>();
		
		try{
			Statement stmt = con.createStatement();
			ResultSet set = stmt.executeQuery("SELECT * FROM room");
			
			while(set.next()){
				Room room = new Room();
				room.setRid(set.getInt("rid"));
				room.setNid(set.getInt("nid"));
				room.setTotalBeds(set.getInt("totalbeds"));
				room.setAvailableBeds(set.getInt("availablebeds"));
				room.setNurse(getNurse(room.getNid()));
				
				roomList.add(room);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return roomList;
	}
	
	public ArrayList<Indoor> getIndoorList(){
		ArrayList<Indoor> indoorList = new ArrayList<Indoor>();
		
		try{
			Statement stmt = con.createStatement();
			ResultSet set = stmt.executeQuery("SELECT * FROM indoor");
			while(set.next()){
				Indoor indoor = new Indoor();
				
				indoor.setIpid(set.getInt("ipid"));
				indoor.setDisease(set.getString("disease"));
				indoor.setPid(set.getInt("pid"));
				indoor.setRid(set.getInt("rid"));
				indoor.setStatus(set.getString("status"));
				indoor.setPatient(getPatient(indoor.getPid()));
				
				if(indoor.getRid()==0){
					indoor.setRoom(null);
				}
				else
				{
					indoor.setRoom(getRoom(indoor.getRid()));
				}
				
				indoorList.add(indoor);
				
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return indoorList;
	}
	
	public ArrayList<Medicine> getMedicineList() throws SQLException{
		
		ArrayList<Medicine> medList = new ArrayList<Medicine>();
		PreparedStatement stmt = con.prepareStatement("SELECT * FROM medicine");
		ResultSet set = stmt.executeQuery();
		
		while(set.next()){
			Medicine med = new Medicine();
			med.setMid(set.getInt("mid"));
			med.setName(set.getString("name"));
			med.setPrice(set.getInt("price"));
			
			medList.add(med);
		}
		
		return medList;
		
	}
	
	public ArrayList<Prescription> getPrescriptionList() throws SQLException{
		
		PreparedStatement stmt = con.prepareStatement("SELECT * FROM prescription");
		ResultSet set = stmt.executeQuery();
		
		
		return getPresListFromSet(set);
		
	}
	
	public Prescription getPrescription(int prid) throws SQLException{
		PreparedStatement stmt = con.prepareStatement("SELECT * FROM prescription WHERE prid=?");
		stmt.setInt(1, prid);
		ResultSet set = stmt.executeQuery();
		
		if(!set.next()) return null;
		
		Prescription pres = new Prescription();
		pres.setPrid(prid);
		pres.setDid(set.getInt("did"));
		pres.setMid(set.getInt("mid"));
		pres.setPid(set.getInt("pid"));
		pres.setDosage(set.getString("dosage"));
		
		pres.setDoctor(getDoctor(pres.getDid()));
		pres.setPatient(getPatient(pres.getPid()));
		pres.setMedicine(getMedicine(pres.getMid()));
		
		return pres;
		
		
	}
	
	public ArrayList<Prescription> getPresListFromSet(ResultSet set) throws SQLException{
		
		ArrayList<Prescription> presList = new ArrayList<Prescription>();
		
		while(set.next()){
			Prescription p = new Prescription();
			p.setPrid(set.getInt("prid"));
			p.setDid(set.getInt("did"));
			p.setDoctor(getDoctor(p.getDid()));
			p.setDosage(set.getString("dosage"));
			p.setMid(set.getInt("mid"));
			p.setMedicine(getMedicine(p.getMid()));
			p.setPid(set.getInt("pid"));
			p.setPatient(getPatient(p.getPid()));
			
			presList.add(p);
		}
		
		return presList;
		
	}
	
	public ArrayList<Patient> getDocPatientsList(int uid) throws SQLException{
		
		int eid = getEidByUid(uid);
		
		PreparedStatement stmt = con.prepareStatement("SELECT * FROM doctor WHERE eid=?");
		stmt.setInt(1, eid);
		ResultSet set = stmt.executeQuery();
		if(!set.next()) return null;
		
		int catid = set.getInt("catid");
		
		stmt = con.prepareStatement("SELECT * FROM patient WHERE catid=?");
		stmt.setInt(1, catid);
		set = stmt.executeQuery();
		
		return getPatientByCatid(catid);
		
	}
	
	public ArrayList<Prescription> getDocPresList(int uid) throws SQLException{
		
		int eid = getEidByUid(uid);
		
		PreparedStatement stmt = con.prepareStatement("SELECT * FROM doctor WHERE eid=?");
		stmt.setInt(1, eid);
		ResultSet set = stmt.executeQuery();
		if(!set.next()) return null;
		int did = set.getInt("did");
		
		stmt = con.prepareStatement("SELECT * FROM prescription WHERE did=?");
		stmt.setInt(1, did);
		set = stmt.executeQuery();
		
		return getPresListFromSet(set);
		
	}
	
	public ArrayList<Prescription> getPatientPres(int pid)throws SQLException{
		PreparedStatement stmt = con.prepareStatement("SELECT * FROM prescription WHERE pid=?");
		stmt.setInt(1, pid);
		ResultSet set = stmt.executeQuery();
		
		return getPresListFromSet(set);
	}
	
	public ArrayList<Patient> getPatientByCatid(int catid) throws SQLException{
		PreparedStatement stmt = con.prepareStatement("SELECT * FROM patient WHERE catid=?");
		stmt.setInt(1, catid);
		ResultSet set = stmt.executeQuery();
		ArrayList<Patient> patList= getPatientListFromSet(set);
		
		return patList;
	}
	
	public Medicine getMedicine(int mid) throws SQLException{
		PreparedStatement stmt = con.prepareStatement("SELECT * FROM medicine WHERE mid=?");
		stmt.setInt(1, mid);
		ResultSet set = stmt.executeQuery();
		
		Medicine med = new Medicine();
		if(!set.next()) return null;
		
		med.setMid(mid);
		med.setName(set.getString("name"));
		med.setPrice(set.getInt("price"));
		
		return med;
		
	}
	
	//well don't worry this one's easy you just have to brainstorm a little bit :P
	public ArrayList<Indoor> getNursePatients(int uid) throws SQLException{
		
		ArrayList<Indoor> indoorList = new ArrayList<Indoor>();
		
		PreparedStatement stmt = con.prepareStatement("SELECT * FROM indoor");
		ResultSet set = stmt.executeQuery();
		
		while(set.next()){
			int rid = set.getInt("rid");
			if(rid==0) continue;
			PreparedStatement stmt2 = con.prepareStatement("SELECT * FROM room WHERE rid=?");
			stmt2.setInt(1, rid);
			ResultSet set2 = stmt2.executeQuery();
			if(!set2.next()) continue;
			
			int nid2 = set2.getInt("nid");
			int eid = getEidByUid(uid);
			PreparedStatement stmt3 = con.prepareStatement("SELECT * FROM nurse WHERE eid=?");
			stmt3.setInt(1, eid);
			ResultSet set3 = stmt3.executeQuery();
			if(!set3.next()) return null;
			int nid = set3.getInt("nid");
			if(nid==nid2){
				Indoor in = new Indoor();
				in.setRid(rid);
				in.setDisease(set.getString("Disease"));
				in.setIpid(set.getInt("ipid"));
				in.setPid(set.getInt("pid"));
				in.setPatient(getPatient(in.getPid()));
				in.setRoom(getRoom(in.getRid()));
				in.setStatus(set.getString("status"));
				
				indoorList.add(in);
			}
		}
		
		return indoorList;
		
	}
	
	public Indoor getIndoor(int ipid) throws SQLException{
		
		PreparedStatement stmt = con.prepareStatement("SELECT * FROM indoor WHERE ipid=?");
		stmt.setInt(1, ipid);
		ResultSet set = stmt.executeQuery();
		
		if(!set.next()) return null;
		
		Indoor indoor = new Indoor();
		indoor.setIpid(ipid);
		indoor.setDisease(set.getString("disease"));
		indoor.setPid(set.getInt("pid"));
		indoor.setRid(set.getInt("rid"));
		indoor.setStatus(set.getString("status"));
		indoor.setRoom(getRoom(indoor.getRid()));
		indoor.setPatient(getPatient(indoor.getPid()));
		
		return indoor;
	}
	
	public int addDoctor(Doctor doctor)throws SQLException{
		
		doctor.setEid(addEmployee(doctor.getEmployee()));
		
		int did=0;
		
		PreparedStatement stmt = con.prepareStatement("INSERT INTO doctor(catid,eid) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS);
		stmt.setInt(1, doctor.getCatid());
		stmt.setInt(2, doctor.getEid());
		
		stmt.executeUpdate();
		ResultSet keys = stmt.getGeneratedKeys();
		if(keys!=null && keys.next()) did=keys.getInt(1);
		doctor.setDid(did);
		return did;
		
		
	}
	
	public int addNurse(Nurse nurse) throws SQLException{
		
		nurse.setEid(addEmployee(nurse.getEmployee()));
		
		
		int nid=0;
		
		PreparedStatement stmt = con.prepareStatement("INSERT INTO nurse(experience,eid) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS);
		stmt.setString(1, nurse.getExperience());
		stmt.setInt(2, nurse.getEid());
		
		stmt.executeUpdate();
		ResultSet keys = stmt.getGeneratedKeys();
		if(keys!=null && keys.next()) nid=keys.getInt(1);
		nurse.setNid(nid);
		return nid;
	}
	
	public int addEmployee(Employee employee) throws SQLException{
		
		employee.setUid(addUser(employee.getUser()));
		
		int eid=0;

		PreparedStatement stmt = con.prepareStatement("INSERT INTO employee(firstname,lastname,gender,dob,salary,phone,uid) VALUES(?,?,?,?,?,?,?) ", Statement.RETURN_GENERATED_KEYS);
		stmt.setString(1,employee.getFirstname());
		stmt.setString(2,employee.getLastname());
		stmt.setString(3,employee.getGender());
		stmt.setString(4, employee.getDob());
		stmt.setInt(5,employee.getSalary());
		stmt.setString(6,employee.getPhone());
		stmt.setInt(7,employee.getUid());

		stmt.executeUpdate();
		ResultSet keys = stmt.getGeneratedKeys();
		if(keys!=null && keys.next()) eid=keys.getInt(1);
		employee.setEid(eid);
		return eid;
	}
	
	public int addUser(User user) throws SQLException{
		int uid=0;
		PreparedStatement stmt = con.prepareStatement("INSERT INTO users(username,password,type) VALUES(?,?,?) ", Statement.RETURN_GENERATED_KEYS);
		stmt.setString(1, user.getUsername());
		stmt.setString(2, user.getPassword());
		stmt.setString(3, user.getType());
		
		stmt.executeUpdate();
		
		ResultSet keys = stmt.getGeneratedKeys();
		if(keys!=null && keys.next()) uid = keys.getInt(1);
		user.setUid(uid);
		return uid;
	}
	
	public void deleteRoom(int rid) throws SQLException{
		
		PreparedStatement stmt = con.prepareStatement("DELETE FROM room WHERE rid=?");
		stmt.setInt(1, rid);
		stmt.execute();
		
	}
	
	public void deletePatient(int pid) throws SQLException{
		
		PreparedStatement stmt = con.prepareStatement("DELETE FROM patient WHERE pid=?");
		stmt.setInt(1, pid);
		stmt.execute();
		
	}
	
	public void deleteEmployee(int uid) throws SQLException{
		
		
		PreparedStatement stmt = con.prepareStatement("DELETE FROM users WHERE uid=?");
		stmt.setInt(1, uid);
		stmt.execute();
	}
	
	
	public int addRoom(Room room) throws SQLException{
		
		int rid=0;
		PreparedStatement stmt = con.prepareStatement("INSERT INTO room(totalbeds,nid,availablebeds) VALUES(?,?,?) ", Statement.RETURN_GENERATED_KEYS);
		stmt.setInt(1, room.getTotalbeds());
		stmt.setInt(2, room.getNid());
		stmt.setInt(3,room.getTotalbeds());
		stmt.executeUpdate();
		
		ResultSet keys = stmt.getGeneratedKeys();
		if(keys!=null && keys.next()) rid = keys.getInt(1);

		return rid;
		
	}
	
	public Patient addPatient(Patient p) throws SQLException{
		
		PreparedStatement stmt = con.prepareStatement("INSERT INTO patient(name,gender,dob,catid) VALUES(?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
		stmt.setString(1,p.getName());
		stmt.setString(2,p.getGender());
		stmt.setString(3,p.getDob());
		stmt.setInt(4, p.getCatid());
		stmt.executeUpdate();
		
		ResultSet keys = stmt.getGeneratedKeys();
		int pid = 0;
		if(keys!=null && keys.next()) pid = keys.getInt(1);
		
		p.setPid(pid);
		return p;
	}
	
	public void updateUser(User user) throws SQLException{
		PreparedStatement stmt = con.prepareStatement("UPDATE users SET username=?, password=?, type=? WHERE uid=?");
		stmt.setString(1, user.getUsername());
		stmt.setString(2, user.getPassword());
		stmt.setString(3, user.getType());
		stmt.setInt(4, user.getUid());
		stmt.execute();
	}
	public void updateEmployee(Employee emp) throws SQLException{
		
		updateUser(emp.getUser());
		
		
		PreparedStatement stmt = con.prepareStatement("UPDATE employee set firstname=?, lastname=?, dob=?, salary=?,"
				+ "gender=?, phone=? WHERE eid=?");
		stmt.setString(1, emp.getFirstname());
		stmt.setString(2, emp.getLastname());
		stmt.setString(3, emp.getDob());
		stmt.setInt(4, emp.getSalary());
		stmt.setString(5, emp.getGender());
		stmt.setString(6, emp.getPhone());
		stmt.setInt(7, emp.getEid());
		stmt.execute();
	}
	public void updateDoctor(Doctor doc) throws SQLException{
		
		updateEmployee(doc.getEmployee());
		
		
		PreparedStatement stmt = con.prepareStatement("UPDATE doctor set catid=? WHERE did=?");
		stmt.setInt(1, doc.getCatid());
		stmt.setInt(2, doc.getDid());
		
		stmt.execute();
	}
	
	public void updateNurse(Nurse nurse) throws SQLException{
		
		updateEmployee(nurse.getEmployee());
		
		PreparedStatement stmt = con.prepareStatement("UPDATE nurse set experience=? WHERE nid=?");
		stmt.setString(1, nurse.getExperience());
		stmt.setInt(2, nurse.getNid());
		
		stmt.execute();
	}
	
	public void updateRoom(Room room) throws SQLException{
		
		PreparedStatement stmt = con.prepareStatement("UPDATE room set totalbeds=?, nid=? WHERE rid=?");
		
		stmt.setInt(1, room.getTotalbeds());
		stmt.setInt(2, room.getNid());
		stmt.setInt(3, room.getRid());
		
		stmt.execute();
	}
	
	public ArrayList<Room> getNurseRooms(int nid) throws SQLException{
		PreparedStatement stmt = con.prepareStatement("SELECT * FROM room WHERE nid=?");
		stmt.setInt(1, nid);
		ResultSet set = stmt.executeQuery();
		
		ArrayList<Room> roomList = new ArrayList<Room>();
		
		while(set.next()){
			Room room = new Room();
			room.setRid(set.getInt("rid"));
			room.setAvailableBeds(set.getInt("availablebeds"));
			room.setNid(set.getInt("nid"));
			room.setNurse(getNurse(room.getNid()));
			room.setTotalBeds(set.getInt("totalbeds"));
			
			roomList.add(room);
		}
		
		return roomList;
	}
	
	public int getEidByUid(int uid) throws SQLException{
		PreparedStatement stmt = con.prepareStatement("SELECT eid FROM employee WHERE uid=?");
		stmt.setInt(1, uid);
		ResultSet set = stmt.executeQuery();
		if(!set.next()) return 0;
		
		int eid = set.getInt("eid");
		
		return eid;
	}
	
	public ArrayList<Room> getNurseRoomsByUid(int uid) throws SQLException{
		
		int eid = getEidByUid(uid);
		
		if(eid==0) return null;
		
		PreparedStatement stmt;
		stmt = con.prepareStatement("SELECT * FROM nurse WHERE eid=?");
		stmt.setInt(1, eid);
		ResultSet set = stmt.executeQuery();
		set.next();
		int nid = set.getInt("nid");
		return getNurseRooms(nid);
	}
	
	public int getDidByUid(int uid) throws SQLException{
		int eid = getEidByUid(uid);
		
		PreparedStatement stmt = con.prepareStatement("SELECT * FROM doctor WHERE eid=?");
		stmt.setInt(1, eid);
		ResultSet set = stmt.executeQuery();
		
		set.next();
		
		return set.getInt("did");
	}
	
	public void addIndoor(Indoor indo) throws SQLException{
		PreparedStatement stmt = con.prepareStatement("INSERT INTO indoor SET disease=?,pid=?, status=?");
		stmt.setString(1, indo.getDisease());
		stmt.setInt(2, indo.getPid());
		stmt.setString(3,indo.getStatus());
		
		stmt.execute();
		
		stmt = con.prepareStatement("UPDATE patient SET type=? WHERE pid=?");
		stmt.setString(1, "indoor");
		stmt.setInt(2, indo.getPid());
		stmt.execute();
		
		
	}
	
	public void addPrescription(Prescription p) throws SQLException{
		
		PreparedStatement stmt = con.prepareStatement("INSERT INTO prescription SET dosage=?, pid=?, mid=?, did=?", Statement.RETURN_GENERATED_KEYS);
		stmt.setString(1, p.getDosage());
		stmt.setInt(2, p.getPid());
		stmt.setInt(3, p.getMid());
		stmt.setInt(4, p.getDid());
		
		stmt.executeUpdate();
		int prid=0;
		ResultSet keys = stmt.getGeneratedKeys();
		if(keys!=null && keys.next()) prid = keys.getInt(1);
		
		p.setDoctor(getDoctor(p.getDid()));
		p.setMedicine(getMedicine(p.getMid()));
		p.setPatient(getPatient(p.getPid()));
		p.setPrid(prid);
		
	}
	public Prescription updatePrescription(Prescription p)throws SQLException{
		PreparedStatement stmt = con.prepareStatement("UPDATE prescription SET dosage=?, mid=? WHERE prid=?");
		stmt.setString(1, p.getDosage());
		stmt.setInt(2, p.getMid());
		stmt.setInt(3, p.getPrid());
		
		stmt.execute();
		
		return getPrescription(p.getPrid());
	}
	
	public void removePres(int prid) throws SQLException{
		PreparedStatement stmt = con.prepareStatement("DELETE FROM prescription WHERE prid=?");
		stmt.setInt(1, prid);
		stmt.execute();
	}

	public void updateIndoorRid(int ipid, int rid) throws SQLException {
		
		PreparedStatement stmt = con.prepareStatement("UPDATE indoor SET rid=? WHERE ipid=?");
		stmt.setInt(1, rid);
		stmt.setInt(2, ipid);
		stmt.execute();
		
	}

	public void updatePatient(Patient p) throws SQLException {
		
		PreparedStatement stmt = con.prepareStatement("UPDATE patient SET name=?, gender=?, dob=?, type=?, catid=?, did=? WHERE pid=?");
		stmt.setString(1, p.getName());
		stmt.setString(2, p.getGender());
		stmt.setString(3, p.getDob());
		stmt.setString(4, p.getType());
		stmt.setInt(5, p.getCatid());
		stmt.setInt(6, p.getDid());
		stmt.setInt(7, p.getPid());
		stmt.execute();
		
	}

	public Medicine addMedicine(Medicine med) throws SQLException {
		PreparedStatement stmt = con.prepareStatement("INSERT INTO medicine SET name=?, price=?", Statement.RETURN_GENERATED_KEYS);
		stmt.setString(1, med.getName());
		stmt.setInt(2, med.getPrice());
		
		stmt.executeUpdate();
		
		int mid=0;
		ResultSet keys = stmt.getGeneratedKeys();
		if(keys!=null && keys.next()) mid = keys.getInt(1);
		
		med.setMid(mid);
		
		return med;
		
		
	}

	public Medicine updateMedicine(Medicine med) throws SQLException {
		PreparedStatement stmt = con.prepareStatement("UPDATE medicine SET name=?, price=? WHERE mid=?");
		stmt.setString(1, med.getName());
		stmt.setInt(2, med.getPrice());
		stmt.setInt(3,med.getMid());
		
		stmt.execute();
		return med;
	}

	public void removeMedicine(int mid) throws SQLException {
		
		PreparedStatement stmt = con.prepareStatement("DELETE FROM medicine WHERE mid=?");
		stmt.setInt(1, mid);
		stmt.execute();
		
	}

	public ArrayList<Category> getCategories() throws SQLException {
		
		PreparedStatement stmt = con.prepareStatement("SELECT * FROM category");
		ResultSet set = stmt.executeQuery();
		
		return getCategoryListFromSet(set);
		
	}
	
	public ArrayList<Category> getCategoryListFromSet(ResultSet set) throws SQLException{
		ArrayList<Category> catList = new ArrayList<Category>();
		
		while(set.next()){
			Category cat = new Category();
			cat.setCatid(set.getInt("catid"));
			cat.setName(set.getString("name"));
			catList.add(cat);
		}
		
		return catList;
	}

	public Category addCategory(Category cat) throws SQLException {
		
		PreparedStatement stmt = con.prepareStatement("INSERT INTO category SET name=?",Statement.RETURN_GENERATED_KEYS);
		stmt.setString(1, cat.getName());
		
		int catid=0;
		stmt.executeUpdate();
		ResultSet keys = stmt.getGeneratedKeys();
		if(keys!=null && keys.next()) catid=keys.getInt(1);
		
		cat.setCatid(catid);
		return cat;
	}

	public Category updateCategory(Category cat) throws SQLException {
		PreparedStatement stmt = con.prepareStatement("UPDATE category SET name=? WHERE catid=?");
		stmt.setString(1, cat.getName());
		stmt.setInt(2, cat.getCatid());
		stmt.execute();
		return cat;
	}

	public void deleteCategory(int catid) throws SQLException {
		PreparedStatement stmt = con.prepareStatement("DELETE FROM category WHERE catid=?");
		stmt.setInt(1, catid);
		stmt.execute();
	}

	public void decreaseBed(int rid) throws SQLException {
		PreparedStatement stmt = con.prepareStatement("SELECT * FROM room WHERE rid=?");
		stmt.setInt(1, rid);
		ResultSet set = stmt.executeQuery();
		
		int availablebeds=0;
		
		if(set.next()) availablebeds = set.getInt("availablebeds");
		
		availablebeds--;
		
		stmt = con.prepareStatement("UPDATE room SET availablebeds=? WHERE rid=?");
		stmt.setInt(1, availablebeds);
		stmt.setInt(2, rid);
		stmt.execute();
		
	}
}
