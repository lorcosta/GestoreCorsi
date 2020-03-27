package it.polito.tdp.corsi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.corsi.model.Corso;

public class CorsoDAO {
	
	public List<Corso> getCorsiByPeriodo(Integer pd){
		String query="select * from corso where pd=?";
		
		List<Corso> result= new ArrayList<Corso>();
		
		try {
			Connection conn= ConnectDB.getConnection();
			PreparedStatement st= conn.prepareStatement(query);
			st.setInt(1, pd);
			ResultSet rs= st.executeQuery();
			
			while(rs.next()) {
				Corso c= new Corso(rs.getString("codins"), rs.getInt("crediti"),
						rs.getString("nome"), rs.getInt("pd"));
				result.add(c);
						
			}
			
			conn.close();
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		
		return result;
	}
	
	public Map<Corso, Integer> getIscrittiByPeriodo(Integer pd){
		String query="select c.codins, c.nome, c.crediti, c.pd, COUNT(*) AS tot"
				+ " from corso as c, iscrizione i where c.codins=i.codins and c.pd=? "
				+ "group by c.codins, c.nome, c.crediti, c.pd";
		
		Map<Corso, Integer> result= new HashMap<Corso, Integer>();
		
		try {
			Connection conn= ConnectDB.getConnection();
			PreparedStatement st= conn.prepareStatement(query);
			st.setInt(1, pd);
			ResultSet rs= st.executeQuery();
			
			while(rs.next()) {
				Corso c= new Corso(rs.getString("codins"), rs.getInt("crediti"),
						rs.getString("nome"), rs.getInt("pd"));
				result.put(c, rs.getInt("tot"));
						
			}
			
			conn.close();
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		
		return result;
	}

}
