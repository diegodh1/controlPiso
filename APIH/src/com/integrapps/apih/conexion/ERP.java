package com.integrapps.apih.conexion;

import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Iterator;

import javax.sql.DataSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import com.integrapps.apih.model.Lista;
import com.integrapps.apih.model.Listaitem;
import com.integrapps.apih.model.usuario;
import com.integrapps.apih.model.Documento;
import com.integrapps.apih.model.Ept;
import com.integrapps.apih.model.Eptdoc;

public class ERP {
	
	public static String driverClassName;
    public static String url;
    public static String dbUsername;
    public static String dbPassword;

    private DataSource dataSource;
    private JdbcTemplate template;
    
    
	public ERP()
	{
		
			driverClassName ="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	        url = "jdbc:sqlserver://10.10.10.11\\winbd:1433;databaseName=TUnoEE_Hercules_Real";
	        dbUsername = "sa";
	        dbPassword = "Hercules123*";
	        dataSource = getDataSource();
	        template = new JdbcTemplate(dataSource);
		  
	}

	public static DriverManagerDataSource getDataSource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(dbUsername);
        dataSource.setPassword(dbPassword);

        return dataSource;

    }
	
	  //ESTE METODO SE ENCARGA DE LLENADO DE LISTA 
    public ArrayList<Lista> carguelista(String sql) {
        ArrayList<Lista> lista = (ArrayList<Lista>) template.query(sql, new BeanPropertyRowMapper<Lista>(Lista.class));
        return lista;
    }
    
	  //ESTE METODO SE ENCARGA DE LLENADO DE LISTA DE ITEMS 
    public ArrayList<Listaitem> carguelistaitem(String sql) {
        ArrayList<Listaitem> lista = (ArrayList<Listaitem>) template.query(sql, new BeanPropertyRowMapper<Listaitem>(Listaitem.class));
        return lista;
    }
    
}
