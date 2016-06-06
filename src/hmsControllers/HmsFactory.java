package hmsControllers;

import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import hmsDA.HmsDA;
import hmsModels.Category;
import hmsModels.Doctor;
import hmsModels.Indoor;
import hmsModels.Medicine;
import hmsModels.Nurse;
import hmsModels.Patient;
import hmsModels.Prescription;
import hmsModels.Room;

public class HmsFactory {

	HmsDA hms = new HmsDA();
	private static Gson g = new Gson();
	
	public static String toJson(Object obj){
		return g.toJson(obj);
	}
	
	public ArrayList<Doctor> getDoctors(){
		
		return hms.getDoctorList();
	}
	public ArrayList<Patient> getPatients(){
		return hms.getPatientList();
	}
	public ArrayList<Nurse> getNurses(){
		return hms.getNurseList();
	}
	public ArrayList<Room> getRooms(){
		return hms.getRoomList();
	}
	public ArrayList<Indoor> getIndoors(){
		return hms.getIndoorList();
	}
	public String getDoctorsJson(){
		ArrayList<Doctor> docList = hms.getDoctorList();
		return g.toJson(docList);
	}
	
	public String getNursesJson(){
		ArrayList<Nurse> nurseList = hms.getNurseList();
		
		return g.toJson(nurseList);
	}
	
	public String getDoctorJson(int did) throws SQLException{
		
		Doctor doc;
		doc = hms.getDoctor(did);
		
		return g.toJson(doc);
		
	}
	
	public String getNurseJson(int nid) throws SQLException{
		Nurse nurse;
		
		nurse = hms.getNurse(nid);
		
		return g.toJson(nurse);
			
	}
	
	public String getPatientsJson(){
		ArrayList<Patient> patientList = hms.getPatientList();
		
		return g.toJson(patientList);
	}
	
	public String getPatientJson(int pid) throws SQLException{
		
		Patient patient;

		patient = hms.getPatient(pid);
		
		return g.toJson(patient);
			
	}
	
	public String getRoomsJson(){
		ArrayList<Room> roomList = hms.getRoomList();
		
		return g.toJson(roomList);
	}
	
	public String getRoomJson(int rid) throws SQLException{
		
		Room room;
		room = hms.getRoom(rid);
		
		return g.toJson(room);
	}
	
	public String getIndoorsJson(){
		ArrayList<Indoor> indoorList = hms.getIndoorList();
		
		return g.toJson(indoorList);
	}
	
	public String getIndoorJson(int ipid) throws SQLException{
		
		Indoor indoor;
		indoor = hms.getIndoor(ipid);
		
		return g.toJson(indoor);
	}
	
	public String getNurseRoomsJson(int nid) throws SQLException{
		ArrayList<Room> roomList = hms.getNurseRooms(nid);
		
		return g.toJson(roomList);
	}
	
	public String getNurseRoomsJsonByUid(int uid) throws SQLException{
		ArrayList<Room> roomList = hms.getNurseRoomsByUid(uid);
		
		return g.toJson(roomList);
	}
	
	public String getNursePatientsJson(int uid) throws SQLException{
		ArrayList<Indoor> indoorList = hms.getNursePatients(uid);
		
		return g.toJson(indoorList);
	}
	
	public String getMedicinesJson() throws SQLException{
		ArrayList<Medicine> medList = hms.getMedicineList();
		
		return g.toJson(medList);
	}
	
	public String getPrescriptionsJson() throws SQLException{
		ArrayList<Prescription> presList = hms.getPrescriptionList();
		
		return g.toJson(presList);
	}
	
	public String getPrescriptionsJson(int prid) throws SQLException{
		return hms.getPrescription(prid).toJson();
	}
	
	public String getDocPatientsJson(int uid) throws SQLException{
		ArrayList<Patient> patList = hms.getDocPatientsList(uid);
		
		return g.toJson(patList);
	}
	
	public String getDocPresJson(int uid) throws SQLException{
		ArrayList<Prescription> presList = hms.getDocPresList(uid);
		
		return g.toJson(presList);
	}
	
	public String getPatientPresJson(int pid) throws SQLException{
		ArrayList<Prescription> presList = hms.getPatientPres(pid);
		
		return g.toJson(presList);
	}
	
	public void addPres(Prescription p, int uid) throws SQLException{
		int did = hms.getDidByUid(uid);
		
		p.setDid(did);
		
		hms.addPrescription(p);
	}
	
	public String updatePres(Prescription pres, int uid) throws SQLException{
		int did = hms.getDidByUid(uid);
		
		pres.setDid(did);
		
		return hms.updatePrescription(pres).toJson();
	}
	
	public void removePres(int prid) throws SQLException{
		hms.removePres(prid);
	}
	
	public void addIndoor(Indoor indo) throws SQLException{
		
		hms.addIndoor(indo);
	}
	
	public void updatePatient(int pid, int uid, String type) throws SQLException{
		Patient p = hms.getPatient(pid);
		p.setDid(hms.getDidByUid(uid));
		p.setType(type);
		hms.updatePatient(p);
	}

	public String updateIndoorRid(int ipid, int rid) throws SQLException {
		
		Room room = hms.getRoom(rid);
		if(room.getAvailableBeds()>0){
			
			hms.decreaseBed(rid);
			
			hms.updateIndoorRid(ipid,rid);
			room.setAvailableBeds(room.getAvailableBeds()-1);
			hms.updateRoom(room);
			
			return hms.getIndoor(ipid).toJson();
		}
		else
			return "Beds not available in the selected room.";
	}

	public String addMedicne(Medicine med) throws SQLException {
		
		return hms.addMedicine(med).toJson();
	}

	public String updateMedicine(Medicine med) throws SQLException {
		
		return hms.updateMedicine(med).toJson();
	}

	public String getMedicine(int mid) throws SQLException {
		
		return hms.getMedicine(mid).toJson();
		
	}

	public void removeMedicine(int mid) throws SQLException {
		
		hms.removeMedicine(mid);
	}

	public String getCategoriesJson() throws SQLException {
		
		return g.toJson(hms.getCategories());
	}

	public String addCategory(Category cat) throws SQLException {
		
		return hms.addCategory(cat).toJson();
	}

	public String getCategory(int catid) throws SQLException {
		return hms.getCategory(catid).toJson();
	}

	public String updateCategory(Category cat) throws SQLException {
		return hms.updateCategory(cat).toJson();
	}

	public void deleteCategory(int catid) throws SQLException {
		hms.deleteCategory(catid);
	}

	public String addPatient(Patient patient) throws SQLException {
		return hms.addPatient(patient).toJson();
	}
}
