package com.integrapps.apih.conexion;

import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Iterator;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import com.integrapps.apih.model.Lista;
import com.integrapps.apih.model.Listaitem;
import com.integrapps.apih.model.dpidocitemold;
import com.integrapps.apih.model.dpidocitemoldcumplido;
import com.integrapps.apih.model.dtuencabezado;
import com.integrapps.apih.model.mmto_canastilla;
import com.integrapps.apih.model.usuario;
import com.integrapps.apih.model.Documento;
import com.integrapps.apih.model.Ept;
import com.integrapps.apih.model.Eptdoc;

public class conexion {

	public static String driverClassName;
	public static String url;
	public static String dbUsername;
	public static String dbPassword;

	private DataSource dataSource;
	private JdbcTemplate template;

	public conexion() {
//		int mycon = 2;
//		if(mycon == 1) {
		driverClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		url = "jdbc:sqlserver://localhost:1433;databaseName=pro_logic_hercules";
		dbUsername = "sa";
		dbPassword = "Cristiano1994";
		dataSource = getDataSource();
		template = new JdbcTemplate(dataSource);
//		}else {
//			driverClassName ="com.microsoft.sqlserver.jdbc.SQLServerDriver";
//	        url = "jdbc:sqlserver://USUARIO-PC\\SQLSERVER:1433;databaseName=pro_logic_hercules";
//	        dbUsername = "desa";
//	        dbPassword = "desa";
//	        dataSource = getDataSource();
//	        template = new JdbcTemplate(dataSource);
//		}

	}

	public static DriverManagerDataSource getDataSource() {

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(dbUsername);
		dataSource.setPassword(dbPassword);

		return dataSource;

	}

	// ESTE METODO SE ENCARGA DE LLENADO DE LISTA
	public ArrayList<Lista> carguelista(String sql) {
		ArrayList<Lista> lista = (ArrayList<Lista>) template.query(sql, new BeanPropertyRowMapper<Lista>(Lista.class));
		return lista;
	}

	// ESTE METODO SE ENCARGA DE LLENADO de un objeto USUARIO
	public usuario cargueonlylista(String sql) {
		usuario result = template.query(sql, (ResultSet rs) -> {
			usuario resp = null;
			while (rs.next()) {
				resp = new usuario(rs.getInt(3), 0, 0, rs.getInt(1), rs.getString(5), "", rs.getString(2), "", "", "",
						"", "", "", "", "", "", rs.getString(4));
			}
			return resp;

		});
		if (result != null) {
			return result;
		}
		result = new usuario();
		return result;

	}

	// ESTE METODO SE ENCARGA DE LLENADO de un objeto eptdoc
	public ArrayList<Eptdoc> find_ept(String sql) {
		ArrayList<Eptdoc> lista = (ArrayList<Eptdoc>) template.query(sql,
				new BeanPropertyRowMapper<Eptdoc>(Eptdoc.class));
		return lista;

	}

//  Cargar Item de tipo Ept
	public ArrayList<Ept> item_ept(String sql) {
		ArrayList<Ept> lista = (ArrayList<Ept>) template.query(sql, new BeanPropertyRowMapper<Ept>(Ept.class));
		return lista;
	}

	public int actualizar(String sql) {
		return template.update(sql);

	}

	public int buscarnum(String sql) {
		return template.queryForInt(sql);
	}

	public String DTUELIMINAR(ArrayList<Ept> ept) {
		String resp = "FALL";
		Iterator<Ept> it = ept.iterator();
		while (it.hasNext()) {
			Ept item = it.next();
			actualizar("update ept set cantubicada = (cantubicada - " + item.getCantubicada()
					+ ") ,leido_traslado = 0,fechaedit=getdate(),usuedit='" + item.getUsuedit() + "' WHERE idept ="
					+ item.getIdept());
			resp = "OK";
		}
		return resp;
	}

	// ejecutar el procedimiento almacenado de insertar DTU, pasando los parametros
	// recibidos.
	public String proceDTU(ArrayList<Ept> ept) {

		// Variable utlizada para almacenar la respuesta del procedimiento almancenado
		int result = 0;
		// se debe generar primero el documento
		int numdoc = buscarnum("select numconsecutivo from documento where strtipo = 'DTU' and idorganizacion = "
				+ ept.get(0).getIdorganizacion());
		numdoc = numdoc + 1;
		// actualizar("Insert into
		// dbo.dtudoc(idorganizacion,dtuusu,dtufechacrea,dtuestado,dtubodorigen,dtubarcode)
		// values
		// ("+ept.get(0).getIdorganizacion()+",'"+ept.get(0).getUsuedit()+"',GETDATE(),1,(select
		// eptnumbod from eptbodega where ideptbod="+ept.get(0).getStrbodega()+"),'DTU'
		// + RIGHT('00' + Ltrim(Rtrim("+ept.get(0).getIdorganizacion()+")),2)
		// +RIGHT('00000000' + Ltrim(Rtrim("+numdoc+")),8));");
		actualizar(
				"Insert into dbo.dtudoc(idorganizacion,dtuusu,dtufechacrea,dtuestado,dtubodorigen,dtubarcode,ideptdoc) values ("
						+ ept.get(0).getIdorganizacion() + ",'" + ept.get(0).getUsuedit()
						+ "',GETDATE(),1,(select eptnumbod from eptbodega where ideptbod=" + ept.get(0).getStrbodega()
						+ "),'DTU' + RIGHT('00' + Ltrim(Rtrim(" + ept.get(0).getIdorganizacion()
						+ ")),2) +RIGHT('00000000' + Ltrim(Rtrim(" + numdoc + ")),8));,'" + ept.get(0).getIdeptdoc());
		int iddtu = buscarnum("select max(iddtu) from dbo.dtudoc");
		actualizar("update documento set numconsecutivo=" + numdoc + ",fechaedit=getdate(),usuedit='"
				+ ept.get(0).getUsuedit() + "' where  strtipo = 'DTU' and idorganizacion = "
				+ ept.get(0).getIdorganizacion());
		actualizar(" INSERT INTO  Eventos_Erp(Evento_Tipo,Evento_Param1,Evento_Param2) values('DTU',CAST(" + iddtu
				+ " AS VARCHAR),CONVERT(VARCHAR(10), getdate(), 103))");
		// Se construye el objeto encargado de ejecutar el procedimeinto almacenado
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(template)
//            Se define el procedimiento con el nombre de  los parametros de entrada y salida con los que se construyo el Storeprocedure. Tener en cuenta que los nosbres de los parametros
//            deben ser los mismos que estan definidos en el procedimeinto almacenado
				.withProcedureName("InsertarDTU").declareParameters(new SqlParameter("@nom_usu", Types.VARCHAR),
						new SqlParameter("@id_organi", Types.BIGINT), new SqlParameter("@idbodega", Types.BIGINT),
						new SqlParameter("@item", Types.BIGINT), new SqlParameter("@iddtu", Types.BIGINT),
						new SqlParameter("@strubicacion", Types.VARCHAR),
						new SqlParameter("@strcanasta", Types.VARCHAR), new SqlParameter("@cantidad", Types.DECIMAL),
						new SqlOutParameter("@id_rta", Types.INTEGER));
//          Se asigan a las variables del procedimiento almacenado los parametros que se han definido y los cuales fueron enviados desde la capa Dao
		MapSqlParameterSource paramMap = null;
		Iterator<Ept> it = ept.iterator();
		while (it.hasNext()) {
			Ept item = it.next();
			paramMap = new MapSqlParameterSource().addValue("@nom_usu", item.getUsuedit())
					.addValue("@id_organi", item.getIdorganizacion()).addValue("@idbodega", item.getStrbodega())
					.addValue("@item", item.getCoditem()).addValue("@iddtu", iddtu)
					.addValue("@strubicacion", item.getStrlocalizacion()).addValue("@strcanasta", item.getStrcanasta())
					.addValue("@cantidad", item.getCantubicada());
			// Por ultimo como el procedimeinto devulve un dato de respuesta del 0 al 4, se
			// asigna este valor a la variable result y se retorna este resultado
			result = jdbcCall.executeFunction(Integer.class, paramMap);
		}
		return (result == 0) ? Integer.toString(iddtu) : "FALL";
	}

	// ESTE METODO SE ENCARGA DE LLENADO de un objeto Documento
	public Documento carguedocumento(ArrayList<Ept> ept, String sql) {
		Documento result = template.query(sql, (ResultSet rs) -> {
			Documento resp = null;
			while (rs.next()) {
				resp = new Documento(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), ept);
			}
			return resp;

		});
		if (result != null) {
			return result;
		}
		result = new Documento();
		return result;

	}

	/// Seccion Picking

	// ESTE METODO SE ENCARGA DE LLENADO DE LISTA DE ITEMS
	public ArrayList<Listaitem> carguelistaitem(String sql) {
		ArrayList<Listaitem> lista = (ArrayList<Listaitem>) template.query(sql,
				new BeanPropertyRowMapper<Listaitem>(Listaitem.class));
		return lista;
	}

	public int get_intVal(String sql) {
		int isopt = template.query(sql, (ResultSet rs) -> {
			int cons = -1;
			while (rs.next()) {
				int valcons = -1;
				valcons = rs.getInt(1);

				return valcons;
			}
			return cons;
		});

		return isopt;
	}

	public int inserta_docdpi(String sql, String strnumCod, int idclie, int idpedido, String descpedido,
			String tipoPedido) {
		Object[] params = { strnumCod, idclie, idpedido, descpedido, tipoPedido, idpedido };
		int[] types = { Types.VARCHAR, Types.INTEGER, Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.INTEGER };
		int rows = template.update(sql, params, types);

		return rows;
	}

	public int inserta_dpidetalle(String sql, int idpicking, String iditem, int cantidad, String estante, String ubica,
			String idund, String peso_it, String numEmbala, String descEmb, String canasta) {
		Object[] params = { idpicking, iditem, cantidad, estante, ubica, idund, peso_it, numEmbala, descEmb, canasta };
		int[] types = { Types.INTEGER, Types.VARCHAR, Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
				Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };
		int rows = template.update(sql, params, types);

		return rows;
	}

	public int inserta_docpick(String sql, String barcodem, String barcodeh, String tipo_doc, int num_doc,
			String pick_tipo_doc, int pick_num_doc) {
		Object[] params = { barcodem, barcodeh, tipo_doc, num_doc, pick_tipo_doc, pick_num_doc };
		int[] types = { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER, Types.VARCHAR, Types.INTEGER };
		int rows = template.update(sql, params, types);

		return rows;
	}

	public int inserta_docdetpick(String sql, int idpicking, String iditem, int cantidad, String ubica, String idund,
			int numembala, String tipoembala, String bodega, String referencia, float peso) {
		Object[] params = { idpicking, iditem, cantidad, ubica, idund, numembala, tipoembala, bodega, referencia,
				peso };
		int[] types = { Types.INTEGER, Types.VARCHAR, Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.INTEGER,
				Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.DECIMAL };
		int rows = template.update(sql, params, types);

		return rows;
	}

	public int update_consec(String sql, int estado, int id) {
		Object[] params = { estado, id };
		int[] types = { Types.BIGINT, Types.BIGINT };
		int rows = template.update(sql, params, types);

		return rows;
	}

	public int update_consecpick(String sql, int estado, String codedoc) {
		Object[] params = { estado, codedoc };
		int[] types = { Types.BIGINT, Types.VARCHAR };
		int rows = template.update(sql, params, types);

		return rows;
	}

	public int update_cumpledetallepick(String sql, int estado, int idpicking, String iditem, int cantidad,
			String ubica, String idund, int numembala, String tipoembala, String bodega, String referencia,
			float peso) {
		Object[] params = { estado, idpicking, iditem, cantidad, ubica, idund, numembala, tipoembala, bodega,
				referencia, peso };
		int[] types = { Types.BIGINT, Types.INTEGER, Types.VARCHAR, Types.INTEGER, Types.VARCHAR, Types.VARCHAR,
				Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.DECIMAL };
		int rows = template.update(sql, params, types);

		return rows;
	}

	public int actualiza_erpopSQL(String sql) {
		int[] types = { Types.VARCHAR, Types.VARCHAR, Types.BIGINT, Types.BIGINT, Types.VARCHAR, Types.VARCHAR,
				Types.BIGINT };
		int rows = template.update(sql);

		return rows;
	}

	// Item en documento de picking

	public ArrayList<dpidocitemold> get_dpidetalle(String sql) {
		ArrayList<dpidocitemold> lista = (ArrayList<dpidocitemold>) template.query(sql,
				new BeanPropertyRowMapper<dpidocitemold>(dpidocitemold.class));
		return lista;
	}

	public ArrayList<dpidocitemoldcumplido> get_dpidetallecumplido(String sql) {
		ArrayList<dpidocitemoldcumplido> lista = (ArrayList<dpidocitemoldcumplido>) template.query(sql,
				new BeanPropertyRowMapper<dpidocitemoldcumplido>(dpidocitemoldcumplido.class));
		return lista;
	}

	// encabezado en docmento de organización

	public dtuencabezado get_dtuhead(String sql) {
		dtuencabezado result = template.query(sql, (ResultSet rs) -> {
			dtuencabezado resp = null;
			while (rs.next()) {

				resp = new dtuencabezado(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5));
			}
			return resp;

		});
		if (result != null) {
			return result;
		}
		result = new dtuencabezado();
		return result;
	}

	// Borrar detalle picking
	public int delete_detalle(String sql, int id) {
		int rows = 0;
		try {
			Object[] params = { id };
			int[] types = { Types.BIGINT };
			rows = template.update(sql, params, types);
		} catch (Exception ex) {

		}

		return rows;
	}

	// ****** MODULO DE MOVIMIENTO CANASTILLA ***

	/**
	 * Obtiene la información sobre a cuál bodega se debe de llevar una canastilla.
	 * <br>
	 * 
	 * @param una instruccción SQL que obtiene todos los mmtos de canastilla
	 *            pendientes por cumplir.
	 * @return obtiene la información sobre el movimiento de una canastilla <br>
	 */
	public mmto_canastilla getInfoCanastilla(String sql) {
		mmto_canastilla result = template.query(sql, (ResultSet rs) -> {
			mmto_canastilla resp = null;
			while (rs.next()) {
				resp = new mmto_canastilla(null, rs.getString(3), rs.getString(2), rs.getString(4), rs.getString(5),
						null, null, null, rs.getString(1), null);
			}
			return resp;

		});
		return result;
	}

	/**
	 * Permite asignar una canastilla a una bodega destino. <br>
	 * 
	 * @param una instruccción SQL que obtiene todos los mmtos de canastilla
	 *            pendientes por cumplir.
	 * @return Un mensaje con el resultado de la operación 'Registro Realizado' o ''
	 *         <br>
	 */
	public String entregarCanastilla(String sql) {
		try {
			template.update(sql);
			return "Registro Realizado";
		} catch (Exception e) {
			return "";
		}
	}
	/**
	 * Permite saber cuál es la bodega destino de una canastilla. <br>
	 * 
	 * @param una instruccción SQL que obtiene todos los mmtos de canastilla
	 *            pendientes por cumplir.
	 * @return el codigo de la bodega destino de una canastilla
	 *         <br>
	 */
	public String getCanastillaDestino(String sql) {
		String result = template.query(sql, (ResultSet rs) -> {
			String resp = "No tiene asignada una bodega destino o ya ha sido entregada la canastilla a su bodega destino";
			while (rs.next()) {
				resp = rs.getString(1);
			}
			return resp;

		});
		return result;
	}
	/**
	 * Permite saber cuales son las canastillas que tiene pendientes un usuario por entregar. <br>
	 * 
	 * @param una instruccción SQL que obtiene todos los mmtos de canastilla
	 *            pendientes por cumplir.
	 * @return una lista de canastillas pendientes por entregar
	 *         <br>
	 */
	public ArrayList<mmto_canastilla> getPendientes(String sql) {
		ArrayList<mmto_canastilla> lista = (ArrayList<mmto_canastilla>) template.query(sql,
				new BeanPropertyRowMapper<mmto_canastilla>(mmto_canastilla.class));
		return lista;
	}

}
