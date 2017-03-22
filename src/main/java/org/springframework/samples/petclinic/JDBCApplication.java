package org.springframework.samples.petclinic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.samples.petclinic.owner.Pet;
import org.springframework.samples.petclinic.owner.PetType;

public class JDBCApplication {

	public static void main(String[] args) {
		System.out.println("-------- Test de conexión con MySQL ------------");

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("No encuentro el driver en el Classpath");
			e.printStackTrace();
			return;
		}

		System.out.println("Driver instalado y funcionando");
		Connection connection = null;
		Statement statement = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/petclinic","root", "Everis2017");
			if (connection != null)
				System.out.println("Conexión establecida");
			
		    Owner yo=new Owner();
		    yo.setFirstName("Guillermo");
		    yo.setAddress("mas lejos que cerca");
		    yo.setCity("Sevilla");
		    yo.setLastName("Blanco");
		    yo.setTelephone("432432");
		    
		    Pet mascota=new Pet();   
		    mascota.setBirthDate(new Date());
		    mascota.setName("celeste");
		    
		    PetType type=new PetType();
		    type.setId(1);
		    
		    mascota.setType(type);
		    
		    String q="insert owners set first_name=?, last_name=?, address=?, city=?, telephone=?";
		    
		    
		    PreparedStatement ps=connection.prepareStatement(q);
		    ps.setString(1, yo.getFirstName());
		    ps.setString(2, yo.getLastName());
		    ps.setString(3, yo.getAddress());
		    ps.setString(4, yo.getCity());
		    ps.setString(5, yo.getTelephone());
		    ps.execute();
		    
		    q="select max(id)  from owners";
		    ps=connection.prepareStatement(q);
		    ResultSet res=ps.executeQuery();
		    res.next();
		    int id=res.getInt(1);
		    
		    q="insert pet set name=?, birth_date=?, type_id=?, owner_id=?";
		    ps=connection.prepareStatement(q);
		    ps.setString(1, mascota.getName());
		    ps.setDate(2, (java.sql.Date) mascota.getBirthDate());
		    ps.setInt(3, mascota.getType().getId());
		    ps.setInt(4, id);
		    
		    ps.execute();
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		} finally {
			try {
				if(statement != null)
					connection.close();
			} catch (SQLException se) {
		    	  
		    }
		    try {
		        if(connection != null)
		            connection.close();
		    } catch (SQLException se) {
		         	se.printStackTrace();
		    }
		}
	}

}
