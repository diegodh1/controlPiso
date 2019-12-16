package com.integrapps.apih.conexion;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.integrapps.apih.model.Lista;
import com.integrapps.apih.model.Listaitem;
import com.integrapps.apih.model.dpidocitemold;
import com.integrapps.apih.model.dpidocitemoldcumplido;
import com.integrapps.apih.model.dtuencabezado;
import com.integrapps.apih.model.mmto_canastilla;
import com.integrapps.apih.model.pedido;
import com.integrapps.apih.model.stringadmin;
import com.integrapps.apih.model.usuario;
import com.integrapps.apih.model.Documento;
import com.integrapps.apih.model.Ept;
import com.integrapps.apih.model.Eptdoc;

public class operaciones {

	public operaciones() {
		// TODO Auto-generated constructor stub
	}

	public ArrayList<Lista> carguelista(conexion conn) {
		String sql = "SELECT idorganizacion as id, rtrim(ltrim(upper(strorganizacion))) as nombre FROM organizacion ORDER BY strorganizacion";
		return conn.carguelista(sql);
	}

	public ArrayList<Lista> carguebodega(conexion conn) {
		String sql = "SELECT ideptbod as id,ept_nombre as nombre FROM eptbodega where numactivo=1 and ept_idorganizacion =1";
		return conn.carguelista(sql);
	}

	public ArrayList<Lista> cargueubicabodega(conexion conn, String ideptbodega) {
		String sql = "SELECT id_bodubic as id,desc_bodubic as nombre FROM eptubic_bodega WHERE  numactivo=1 and id_eptbod="
				+ ideptbodega;
		return conn.carguelista(sql);
	}

	public usuario login(usuario u, conexion conn) {
		String sql = "SELECT idusuario,ltrim(rtrim(upper(strnombre))) as nombreusuario,organizacion.idorganizacion,ltrim(rtrim(upper(organizacion.strorganizacion))) as nombreorg, ltrim(rtrim(strusuario))  FROM usuario inner join organizacion on usuario.idorganizacion = organizacion.idorganizacion where  usuario.idorganizacion = "
				+ u.getIdorganizacion() + " and ltrim(rtrim(upper(strusuario))) like ltrim(rtrim(upper('"
				+ u.getStrusuario() + "%'))) and ltrim(rtrim(upper(strpassword))) = ltrim(rtrim(upper('"
				+ u.getStrpassword() + "'))) and usuario.numactivo = 1 and organizacion.numactivo = 1";
		return conn.cargueonlylista(sql);
	}

	public ArrayList<Eptdoc> findmoduloept(String codebar, conexion conn) {
		String sql = "SELECT ideptdoc,ind_cierre,canastilla.txtdescripcion as strtipo, idbodegadestino, bodegadestino, CONVERT(VARCHAR(10), eptdoc.fechacrea, 103) as fechacrea,cast(concat('EPT', RIGHT('00' + Ltrim(Rtrim(eptdoc.idorganizacion)),2),RIGHT('00000000' + Ltrim(Rtrim(eptdoc.num_doc_pt)),8))as varchar) as barcodeh FROM eptdoc inner join canastilla on eptdoc.idcanastilla = canastilla.idcanastilla where  ((eptdoc.strtipo = SUBSTRING('"
				+ codebar + "',1,3) and eptdoc.idorganizacion = CAST(SUBSTRING('" + codebar
				+ "',4,2) AS INT) AND eptdoc.num_doc_pt = CAST(SUBSTRING('" + codebar
				+ "',6,8) AS INT)) or canastilla.barcodeh = '" + codebar + "') and eptdoc.ind_cierre =1";
		return conn.find_ept(sql);
	}

	public ArrayList<Ept> finditemeptdoc(String barcode, conexion conn) {
		String sql = "SELECT  EP.idept ,op.numop ,OP.coditem ,OP.descripcionitem, (EP.cantenviada - EP.cantubicada) as cantenviada,EP.barcodeh FROM dbo.ept AS EP inner join  dbo.OP  AS OP on  EP.idop = OP.idop  where  EP.ideptdoc in(SELECT ideptdoc FROM eptdoc inner join canastilla on eptdoc.idcanastilla = canastilla.idcanastilla where  ((eptdoc.strtipo = SUBSTRING('"
				+ barcode + "',1,3) and eptdoc.idorganizacion = CAST(SUBSTRING('" + barcode
				+ "',4,2) AS INT) AND eptdoc.num_doc_pt = CAST(SUBSTRING('" + barcode
				+ "',6,8) AS INT)) or canastilla.barcodeh = '" + barcode
				+ "') and eptdoc.ind_cierre =1) and (EP.cantenviada != EP.cantubicada)";
		return conn.item_ept(sql);
	}

	public String ubicaitem_ept(Ept ept, conexion conn) {
		String sql = "update ept set cantubicada = (cantubicada + " + ept.getCantubicada()
				+ ") ,leido_traslado = 1,fechaedit=getdate(),usuedit='" + ept.getUsuedit() + "' WHERE idept ="
				+ ept.getIdept();
		int x = conn.actualizar(sql);
		return (x > 0) ? "OK" : "FALL";
	}

	public String crearDTU(ArrayList<Ept> ept, conexion conn) {
		return conn.proceDTU(ept);
	}

	public Documento generarDTU(String iddtu, conexion conn) {
		String sql = "SELECT  detdtu_item as coditem,[detdtu_estante] as strubicacion,[detdtu_cant] as cantubicada,[detdtu_ubica] as strlocalizacion FROM [dtudetalle]  where detdtu_iddoc ="
				+ iddtu;
		ArrayList<Ept> item_ept = conn.item_ept(sql);
		sql = "SELECT dtubarcode,dtuusu,CONVERT(VARCHAR(10), dtufechacrea, 103) as dtufechacrea,iddtu FROM dtudoc where iddtu="
				+ iddtu;

		return conn.carguedocumento(item_ept, sql);
	}

	public String DTUELIMINAR(ArrayList<Ept> ept, conexion conn) {
		return conn.DTUELIMINAR(ept);
	}

	// Seccion Picking

	public ArrayList<Lista> carguelistaerp(ERP conxerp) {
		String sql = "SELECT" + "  T028_MM_CLASES_DOCUMENTO.F028_ID as id,"
				+ "  T021_MM_TIPOS_DOCUMENTOS.F021_ID as nombre " + "FROM " + "  T034_MM_GRUPOS_CLASES_DOCTO "
				+ "  INNER JOIN T028_MM_CLASES_DOCUMENTO ON T028_MM_CLASES_DOCUMENTO.F028_ID_GRUPO_CLASE_DOCTO = "
				+ "    T034_MM_GRUPOS_CLASES_DOCTO.F034_ID "
				+ "  INNER JOIN T029_MM_CLASES_TIPOS_DOCUMENTO ON T029_MM_CLASES_TIPOS_DOCUMENTO.F029_ID_CLASE_DOCTO = "
				+ "    T028_MM_CLASES_DOCUMENTO.F028_ID "
				+ "  INNER JOIN T021_MM_TIPOS_DOCUMENTOS ON T029_MM_CLASES_TIPOS_DOCUMENTO.F029_ID_CIA = "
				+ "    T021_MM_TIPOS_DOCUMENTOS.F021_ID_CIA AND "
				+ "    T021_MM_TIPOS_DOCUMENTOS.F021_ID = T029_MM_CLASES_TIPOS_DOCUMENTO.F029_ID_TIPO_DOCTO " + "WHERE "
				+ "  T029_MM_CLASES_TIPOS_DOCUMENTO.F029_ID_CIA = 1 AND "
				+ "  T029_MM_CLASES_TIPOS_DOCUMENTO.F029_ID_CLASE_DOCTO = 502 AND "
				+ "  T028_MM_CLASES_DOCUMENTO.F028_ID_CONCEPTO = 501";
		return conxerp.carguelista(sql);
	}

	public ArrayList<Lista> getall_cliente(String seltipo, conexion conn) {
//			String sql = "SELECT DISTINCT " + 
//					"  (T200_MM_TERCEROS.F200_ID) AS id, " + 
//					"  T201_MM_CLIENTES.F201_DESCRIPCION_SUCURSAL AS nombre " +  
//					" FROM " + 
//					"  T430_CM_PV_DOCTO " + 
//					"  INNER JOIN T431_CM_PV_MOVTO ON T431_CM_PV_MOVTO.F431_ROWID_PV_DOCTO = T430_CM_PV_DOCTO.F430_ROWID " + 
//					"  INNER JOIN T201_MM_CLIENTES ON T430_CM_PV_DOCTO.F430_ROWID_TERCERO_FACT = T201_MM_CLIENTES.F201_ROWID_TERCERO AND " + 
//					"    T430_CM_PV_DOCTO.F430_ID_SUCURSAL_FACT = T201_MM_CLIENTES.F201_ID_SUCURSAL " + 
//					"  INNER JOIN T200_MM_TERCEROS ON T201_MM_CLIENTES.F201_ROWID_TERCERO = T200_MM_TERCEROS.F200_ROWID " + 
//					" WHERE " + 
//					"  T430_CM_PV_DOCTO.F430_ID_CIA = 1 AND " + 
//					"  T430_CM_PV_DOCTO.F430_ID_CO = '001' AND " + 
//					"  T430_CM_PV_DOCTO.F430_ID_TIPO_DOCTO = '" + seltipo + "' AND " + 
//					" T430_CM_PV_DOCTO.F430_IND_ESTADO = '4' OR T430_CM_PV_DOCTO.F430_IND_ESTADO = '2' " +
//					" ORDER BY T201_MM_CLIENTES.F201_DESCRIPCION_SUCURSAL";

//					"SELECT DISTINCT (T200_MM_TERCEROS.F200_ID) as id,T201_MM_CLIENTES.F201_DESCRIPCION_SUCURSAL  as nombre"
//					+ " FROM T430_CM_PV_DOCTO"
//					+ " INNER JOIN T431_CM_PV_MOVTO ON T431_CM_PV_MOVTO.F431_ROWID_PV_DOCTO = T430_CM_PV_DOCTO.F430_ROWID"
//					+ " INNER JOIN T201_MM_CLIENTES ON T430_CM_PV_DOCTO.F430_ROWID_TERCERO_FACT = T201_MM_CLIENTES.F201_ROWID_TERCERO AND T430_CM_PV_DOCTO.F430_ID_SUCURSAL_FACT = T201_MM_CLIENTES.F201_ID_SUCURSAL"
//					+ " INNER JOIN T200_MM_TERCEROS ON T201_MM_CLIENTES.F201_ROWID_TERCERO = T200_MM_TERCEROS.F200_ROWID "
//					+ " WHERE "
//					+ " T430_CM_PV_DOCTO.F430_ID_CIA = 1 AND T430_CM_PV_DOCTO.F430_ID_CO = '001' AND T430_CM_PV_DOCTO.F430_ID_TIPO_DOCTO = '"
//					+ seltipo + "' AND  T430_CM_PV_DOCTO.F430_IND_ESTADO = 2 AND T431_CM_PV_MOVTO.F431_IND_ESTADO = 2"
//					+ " ORDER BY T201_MM_CLIENTES.F201_DESCRIPCION_SUCURSAL";
		String sql = "SELECT DISTINCT f200_id AS id, " + " f201_descripcion_sucursal AS nombre "
				+ " FROM Pedidos_Clientes" + " WHERE f430_id_tipo_docto = '" + seltipo + "'" + " ORDER BY nombre";
		return conn.carguelista(sql);
	}

	public ArrayList<Lista> getall_pedido(pedido unPedido, conexion conn) {
//			String sql = "SELECT DISTINCT " + 
//					"  (T430_CM_PV_DOCTO.F430_CONSEC_DOCTO) AS id, " + 
//					"  T430_CM_PV_DOCTO.F430_CONSEC_DOCTO AS nombre " +  
//					"FROM " + 
//					"  T430_CM_PV_DOCTO " + 
//					"  INNER JOIN T431_CM_PV_MOVTO ON T431_CM_PV_MOVTO.F431_ROWID_PV_DOCTO = T430_CM_PV_DOCTO.F430_ROWID " + 
//					"  INNER JOIN T201_MM_CLIENTES ON T430_CM_PV_DOCTO.F430_ROWID_TERCERO_FACT = T201_MM_CLIENTES.F201_ROWID_TERCERO AND " + 
//					"    T430_CM_PV_DOCTO.F430_ID_SUCURSAL_FACT = T201_MM_CLIENTES.F201_ID_SUCURSAL " + 
//					"  INNER JOIN T200_MM_TERCEROS ON T201_MM_CLIENTES.F201_ROWID_TERCERO = T200_MM_TERCEROS.F200_ROWID " + 
//					" WHERE " + 
//					"  T430_CM_PV_DOCTO.F430_ID_CIA = 1 AND " + 
//					"  T430_CM_PV_DOCTO.F430_ID_CO = '001' AND " + 
//					"  T430_CM_PV_DOCTO.F430_ID_TIPO_DOCTO = '" + unPedido.getTipo() + "' AND "  
//					+ "  T200_MM_TERCEROS.F200_ID = '" + unPedido.getIdcliente() + "' AND "
//					+ " T430_CM_PV_DOCTO.F430_IND_ESTADO = '4' OR T430_CM_PV_DOCTO.F430_IND_ESTADO = '2' "
//					+ "  ORDER BY T430_CM_PV_DOCTO.F430_CONSEC_DOCTO";

//					String sql = "SELECT" + "  DISTINCT (T430_CM_PV_DOCTO.F430_CONSEC_DOCTO) as id,"
//					+ "  T430_CM_PV_DOCTO.F430_CONSEC_DOCTO as nombre" + " FROM " + "  T430_CM_PV_DOCTO "
//					+ " INNER JOIN T431_CM_PV_MOVTO ON T431_CM_PV_MOVTO.F431_ROWID_PV_DOCTO = T430_CM_PV_DOCTO.F430_ROWID "
//					+ "  INNER JOIN T201_MM_CLIENTES ON T430_CM_PV_DOCTO.F430_ROWID_TERCERO_FACT = T201_MM_CLIENTES.F201_ROWID_TERCERO AND "
//					+ "  T430_CM_PV_DOCTO.F430_ID_SUCURSAL_FACT = T201_MM_CLIENTES.F201_ID_SUCURSAL "
//					+ "  INNER JOIN T200_MM_TERCEROS ON T201_MM_CLIENTES.F201_ROWID_TERCERO = T200_MM_TERCEROS.F200_ROWID "
//					+ " WHERE " + "  T430_CM_PV_DOCTO.F430_ID_CIA = 1 AND " + "  T430_CM_PV_DOCTO.F430_ID_CO = '001' AND "
//					+ "  T430_CM_PV_DOCTO.F430_ID_TIPO_DOCTO = '" + unPedido.getTipo() + "' AND"
//					+ "  T200_MM_TERCEROS.F200_ID = '" + unPedido.getIdcliente() + "'"
//					+ " AND  (T430_CM_PV_DOCTO.F430_IND_ESTADO = 2" + " AND T431_CM_PV_MOVTO.F431_IND_ESTADO = 2 OR  T430_CM_PV_DOCTO.F430_IND_ESTADO = '4')"
//					+ "  ORDER BY T430_CM_PV_DOCTO.F430_CONSEC_DOCTO";
		String sql = "SELECT DISTINCT f430_consec_docto AS id, f430_consec_docto AS nombre " + "FROM Pedidos_Clientes "
				+ "WHERE (f430_id_tipo_docto = '" + unPedido.getTipo() + "') AND " + " (f200_id = '"
				+ unPedido.getIdcliente() + "')" + "ORDER BY id";
		return conn.carguelista(sql);
	}

	public ArrayList<Listaitem> get_infopedido(pedido unPedido, conexion conn) {
		String resp = "";
		String sql = "";

//			sql = "SELECT " + "  T120_MC_ITEMS.F120_ID, " + "  T120_MC_ITEMS.F120_DESCRIPCION, "
//					+ "  T431_CM_PV_MOVTO.F431_CANT1_PEDIDA, " + "  T431_CM_PV_MOVTO.F431_CANT1_REMISIONADA, "
//					+ "  T431_CM_PV_MOVTO.F431_CANT1_FACTURADA, " + " T431_CM_PV_MOVTO.F431_ID_UNIDAD_MEDIDA,"
//					+ " T431_CM_PV_MOVTO.F431_VLR_BRUTO " + "FROM " + "  T430_CM_PV_DOCTO "
//					+ "  INNER JOIN T431_CM_PV_MOVTO ON T431_CM_PV_MOVTO.F431_ROWID_PV_DOCTO = T430_CM_PV_DOCTO.F430_ROWID "
//					+ "  INNER JOIN T121_MC_ITEMS_EXTENSIONES ON T431_CM_PV_MOVTO.F431_ROWID_ITEM_EXT = T121_MC_ITEMS_EXTENSIONES.F121_ROWID "
//					+ "  INNER JOIN T120_MC_ITEMS ON T121_MC_ITEMS_EXTENSIONES.F121_ROWID_ITEM = T120_MC_ITEMS.F120_ROWID "
//					+ "WHERE " + "  T430_CM_PV_DOCTO.F430_ID_CIA = 1 AND " + "  T430_CM_PV_DOCTO.F430_ID_CO = '001' AND "
//					+ "  T430_CM_PV_DOCTO.F430_ID_TIPO_DOCTO = '" + unPedido.getTipo() + "' AND "
//					+ "  T430_CM_PV_DOCTO.F430_CONSEC_DOCTO = " + unPedido.getIdpedido()
//					+ " AND T431_CM_PV_MOVTO.F431_IND_ESTADO = 2" + " ORDER BY " + "  T120_MC_ITEMS.F120_ID";
//
//			sql = "SELECT  BI_T431.f_item as item, " + "t120_mc_items.f120_descripcion as descripcion, "
//					+ " t120_mc_items.f120_referencia as valor1, " + "BI_T431.f_cant_pendiente_inv as valor2,  "
//					+ "BI_T431.f_cant_remision_inv as valor3,  " + " BI_T431.f_um_inv as valor4, "
//					+ " BI_T431.f_peso_um as valor5 " + " FROM  t120_mc_items INNER JOIN "
//					+ " BI_T431 ON t120_mc_items.f120_id = BI_T431.f_item " + " WHERE  (BI_T431.f_id_cia = 1) AND "
//					+ "(BI_T431.f_co = '001') AND " + "(BI_T431.f_id_tipo_docto = '" + unPedido.getTipo() + "') AND "
//					+ "(BI_T431.f_cod_estado_docto = 2) AND "
//					+ "(BI_T431.f_cod_estado_movto = 2 OR BI_T431.f_cod_estado_movto = 3) AND "
//					+ "(BI_T431.f_parametro_biable = '1') AND t120_mc_items.f120_id_cia = BI_T431.f_id_cia AND "
//					+ "BI_T431.f_nrodocto = " + unPedido.getIdpedido() + " order by descripcion asc";
//			
//			sql = "SELECT        BI_T431.f_item AS item, t120_mc_items.f120_descripcion AS descripcion, t120_mc_items.f120_referencia AS valor1, BI_T431.f_cant_pendiente_inv AS valor2, " + 
//					"                         BI_T431.f_cant_remision_inv AS valor3, BI_T431.f_um_inv AS valor4, BI_T431.f_peso_um AS valor5 " + 
//					"FROM            t120_mc_items INNER JOIN " + 
//					"                         BI_T431 ON t120_mc_items.f120_id = BI_T431.f_item AND t120_mc_items.f120_id_cia = BI_T431.f_id_cia " + 
//					"WHERE        (BI_T431.f_id_cia = 1) AND (BI_T431.f_co = '001') AND (BI_T431.f_id_tipo_docto = '" + unPedido.getTipo() + "') AND (BI_T431.f_parametro_biable = '1') AND (BI_T431.f_nrodocto = " + unPedido.getIdpedido() + ") " + 
//					"                         AND (BI_T431.f_cod_estado_docto = 2) AND (BI_T431.f_cod_estado_movto = 2) OR " + 
//					"                         (BI_T431.f_id_cia = 1) AND (BI_T431.f_co = '001') AND (BI_T431.f_id_tipo_docto = '" + unPedido.getTipo() + "') AND (BI_T431.f_parametro_biable = '1') AND (BI_T431.f_nrodocto = " + unPedido.getIdpedido() + ") " + 
//					"                         AND (BI_T431.f_cod_estado_docto = 3) AND (BI_T431.f_cod_estado_movto = 3) " + 
//					"ORDER BY descripcion";

		sql = "SELECT  f_item AS item, f120_descripcion AS descripcion, f120_referencia AS valor1, f_cant_pendiente_inv AS valor2, "
				+ " f_cant_remision_inv AS valor3, f_um_inv AS valor4, f_peso_um AS valor5 " + " FROM ItemsEnPedido "
				+ " WHERE (f_id_tipo_docto = '" + unPedido.getTipo() + "') " + " AND  (f_nrodocto = "
				+ unPedido.getIdpedido() + ") " + "ORDER BY descripcion";

		// ItemsEnPedido

		return conn.carguelistaitem(sql);
	}

	public ArrayList<Listaitem> get_infopedidobycode(pedido unPedido, ERP conxerp, conexion conn) {
		ArrayList<Lista> resp;
		String sql = "";

		resp = get_infopedidotiponumero(unPedido, conn);
		Lista mylist = resp.get(0);
		sql = "SELECT  BI_T431.f_item as item, " + "t120_mc_items.f120_descripcion as descripcion, "
				+ " t120_mc_items.f120_referencia as valor1, " + "BI_T431.f_cant_pendiente_inv as valor2,  "
				+ "BI_T431.f_cant_remision_inv as valor3,  " + " BI_T431.f_um_inv as valor4, "
				+ " BI_T431.f_peso_um as valor5 " + " FROM  t120_mc_items INNER JOIN "
				+ " BI_T431 ON t120_mc_items.f120_id = BI_T431.f_item " + " WHERE  (BI_T431.f_id_cia = 1) AND "
				+ "(BI_T431.f_co = '001') AND " + "(BI_T431.f_id_tipo_docto = '" + mylist.getNombre() + "') AND "
				+ "(BI_T431.f_cod_estado_docto = 2) AND "
				+ "(BI_T431.f_cod_estado_movto = 2 OR BI_T431.f_cod_estado_movto = 3) AND "
				+ "(BI_T431.f_parametro_biable = '1') AND t120_mc_items.f120_id_cia = BI_T431.f_id_cia AND "
				+ "BI_T431.f_nrodocto = " + mylist.getId() + " order by descripcion asc";

		return conxerp.carguelistaitem(sql);
	}

	public ArrayList<Lista> get_infopedidotiponumero(pedido unPedido, conexion conn) {
		String sql = "";

		sql = "SELECT        TOP (200) erp_num_doc AS id,  erp_tipo_doc AS nombre " + "FROM         dpidoc "
				+ "WHERE        (dpibarcode = '" + unPedido.getNombrePedido()
				+ "') AND (erp_cia = 1) AND (erp_co = '001')";

		return conn.carguelista(sql);
	}

	public ArrayList<Lista> get_infoclientebypick(pedido unPedido, conexion conn) {
		String sql = "";

		sql = "SELECT   dpiidcliente as id, dpidesccliente as nombre " + " FROM    dpidoc " + " WHERE  (dpibarcode =  '"
				+ unPedido.getNombrePedido() + "')";

		return conn.carguelista(sql);
	}

	public ArrayList<Lista> Listall_bodega_tipo(int tipo, conexion conn) {
		ArrayList<Lista> resp = null;
		String sql = "";

		sql = "SELECT ideptbod as id, eptnumbod as nombre  FROM  eptbodega WHERE epttipbod = " + tipo;

		resp = conn.carguelista(sql);
		return resp;
	}

	public ArrayList<Listaitem> get_ubicaitempedido(pedido unPedido, ERP conxerp, conexion conn) {
		String resp = "";

		String adic = "";
		String tipo = unPedido.getTipo();
		switch (tipo.toUpperCase().trim()) {
		case "PVM":
			adic = " AND f_id_bodega = '00127'";
			break;
		case "PVN":
			adic = " AND f_id_bodega = '00127'";
			break;
		case "PVC":
			adic = " AND f_id_bodega = '00127'";
			break;
		case "PDE":
			adic = " AND f_id_bodega = '00050'";
			break;
		case "RQ":
			adic = " AND (f_id_bodega = '00101'  OR f_id_bodega = '00127')";
			break;
		default:
			// code block
		}

		String sql = "SELECT TOP (200) f120_id as item, " + "f120_referencia  as descripcion, "
				+ " f_cant_disponible_1  as valor1, " + " f_id_bodega  as valor2, " + " f_id_ubicacion_aux  as valor3, "
				+ " f120_id_unidad_inventario as valor4, " + " f_id_cia  as valor5 " + " FROM  Saldos_Bodega_Ubicacion "
				+ " WHERE (f120_id = '" + unPedido.getIdpedido() + "') " + adic
				+ " AND (f_cant_disponible_1 > 0) AND (f_id_cia = 1)";

		return conn.carguelistaitem(sql);
	}

	public ArrayList<Lista> getall_unidad(conexion conn) {
		ArrayList<Lista> resp = null;
		String sql = "SELECT" + "  emb_id as id," + "  emb_nombre as nombre" + " FROM " + "  Des_Und_Embalaje "
				+ " WHERE " + " cia = 1 AND emb_activo = 1" + " ORDER BY emb_id";

		resp = conn.carguelista(sql);
		return resp;
	}

	public String crea_doc(int idclie, int numped, String desclie, String tipoPedido, conexion conn) {
		String resp = "";
		int numcons = get_consec(conn);
		String strnumCod = "";
		if (numcons > 0) {
			numcons++;
			strnumCod = String.valueOf(numcons);
		} else {
			strnumCod = "1";
		}
		stringadmin stadmin = new stringadmin();
		strnumCod = "DPI04" + stadmin.AdicionaCeros(strnumCod, 7);
		String sql = "INSERT INTO  dpidoc "
				+ " (dpibarcode, dpiidcliente, dpiidpedido, dpidesccliente,erp_tipo_doc, erp_num_doc) "
				+ " VALUES (?, ?, ?, ?, ?, ?)";

		int int_resp = conn.inserta_docdpi(sql, strnumCod, idclie, numped, desclie, tipoPedido);

		if (int_resp > 0) {
			int_resp = get_iddoc(strnumCod, conn);
			resp = String.valueOf(int_resp);

			if (int_resp > 0) {
				update_consec(numcons, 1, conn);
//		                int_resp = creaevento(resp, new Date());
			}

		}

		return resp;
	}

//		public int crea_docpick(String tipo_doc, int num_doc, conexion conn) {
//			int resp = 0;
//			String strresp = "";
//			int numcons = get_consec(conn);
//			String strnumCod = "";
//			if (numcons > 0) {
//				strnumCod = String.valueOf(numcons);
//			} else {
//				strnumCod = "1";
//				numcons = 1;
//			}
//			stringadmin stadmin = new stringadmin();
//			strnumCod = "DPI04" + stadmin.AdicionaCeros(strnumCod, 7);
//
//			String sql = "INSERT INTO  picking "
//					+ " (barcodem, barcodeh, erp_tipo_doc, erp_num_doc, pick_tipo_doc, pick_num_doc) "
//					+ " VALUES (?, ?, ?, ?, ?, ?)";
//
//			resp = conn.inserta_docpick(sql, strnumCod, strnumCod, tipo_doc, num_doc, "DPI", numcons);
//
//			if (resp > 0) {
//				resp = get_iddocpick(strnumCod, conn);
//				strresp = String.valueOf(resp);
//			}
//			resp = Integer.valueOf(strresp);
//			return resp;
//		}

	public int get_consec(conexion conn) {
		int resp = 0;
		String sql = "SELECT numconsecutivo " + "FROM dbo.documento " + "WHERE strtipo = 'DPI' AND idorganizacion=1";

		resp = conn.get_intVal(sql);
		return resp;
	}

	public int get_iddoc(String strnumCod, conexion conn) {
		int resp = 0;
		String sql = "SELECT iddpi " + " FROM dpidoc " + " WHERE dpibarcode ='" + strnumCod + "'";

		resp = conn.get_intVal(sql);
		return resp;
	}

//		public int get_iddocpick(String strnumCod, conexion conn) {
//			int resp = 0;
//			String sql = "SELECT idpicking " + " FROM picking " + " WHERE barcodem ='" + strnumCod + "'";
//
//			resp = conn.get_intVal(sql);
//			return resp;
//		}

	public int update_consec(int numconsec, int idorg, conexion conn) {
		int resp = 0;
		String sql = "UPDATE  documento SET numconsecutivo = ? WHERE strtipo = 'DPI' AND idorganizacion = ? ";
		resp = conn.update_consec(sql, numconsec, idorg);
		return resp;
	}

	public int creaevento(String iddoc, Date fecdoc, conexion conn) {
		int respint = 0;

		String sql = "INSERT INTO  Eventos_Erp "
				+ " (Eventos_Erp.Evento_Tipo, Eventos_Erp.Evento_Param1, Eventos_Erp.Evento_Param4, "
				+ " Eventos_Erp.Evento_Param5, Eventos_Erp.Evento_Param2, Eventos_Erp.Evento_Param3, "
				+ " Eventos_Erp.Evento_Param6)" + " VALUES ('PICKING', '" + iddoc + "', " + 0 + " , " + 0 + " , '"
				+ fecdoc + "', '', 0)";

		respint = conn.actualiza_erpopSQL(sql);
		return respint;
	};

	public ArrayList<Lista> create_dpidetalle(int iddoc, String item, int cant, String estante, String ubica,
			String idund, String peso_it, String numEmbala, String descEmb, String canasta, conexion conn) {
		// String resp = "";
		ArrayList<Lista> resp = new ArrayList();

		String sql = "INSERT INTO  dpidetalle "
				+ " (detdpi_iddoc, detdpi_item, detdpi_cant, detdpi_estante, detdpi_ubica, detdpi_und, detdpi_peso, detdpi_numembala, detdpi_descemb, detdpi_bodega) "
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		int intresp = conn.inserta_dpidetalle(sql, iddoc, item, cant, estante, ubica, idund, peso_it, numEmbala,
				descEmb, canasta);
		if (intresp > 0) {
			resp = getdocpick(iddoc, conn);
		}
		return resp;
	};

//		public ArrayList<Lista> create_dpidetpick(int idpicking, String iditem, int cantidad, String ubica, String idund,
//				int numembala, String tipoembala, String strbod, String referencia, float peso, conexion conn) {
//			ArrayList<Lista> resp = new ArrayList();
//			String sql = "INSERT INTO  Picking_Detalle "
//					+ " (idpicking, iditem, cantidad, ubicacion, idund, pick_det_numembala, pick_det_tipoembala, pick_det_bodega, itemreferencia, peso_it)"
//					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//			int intresp = conn.inserta_docdetpick(sql, idpicking, iditem, cantidad, ubica, idund, numembala, tipoembala, strbod, referencia, peso);
//			if(intresp >0) {
//				resp=getdocpick(idpicking, conn);
//			}
//			
//			return resp;
//		};

	public ArrayList<Lista> getdocpick(int iddoc, conexion conn) {
		ArrayList<Lista> resplist;
		String sql = "";
		if (iddoc > 0) {
			sql = "SELECT iddpi as id, dpibarcode as nombre  " + " FROM dpidoc " + " WHERE iddpi  ='" + iddoc + "'";
		}

		resplist = conn.carguelista(sql);

		// String resp = resplist.get(0).getNombre();
		return resplist;
	}

	public ArrayList<Lista> getall_docact(String idest, conexion conn) {
		ArrayList<Lista> resp;
		String sql = "";
		if (idest.equals("1")) {
			sql = "SELECT iddpi as id, dpibarcode as nombre" + " FROM dpidoc " + " WHERE dpiestado ='" + idest
					+ "' ORDER BY dpibarcode DESC";
		} else {
			sql = "SELECT iddpi as id, dpibarcode as nombre" + " FROM dpidoc ORDER BY dpibarcode DESC";
		}

		resp = conn.carguelista(sql);
		return resp;
	}

	public ArrayList<dpidocitemold> get_dpidetalle(String iddoc, conexion conn) {
		ArrayList<dpidocitemold> resp;

		String sql = "SELECT detdpi_item as item, detdpi_cant as cant, "
				+ " detdpi_estante as estante, detdpi_bodega as canasta, "
				+ "detdpi_ubica as ubica, detdpi_und as unidad, detdpi_peso as peso, "
				+ "detdpi_numembala as val_id, detdpi_descemb as embala " + "FROM dpidetalle "
				+ "WHERE (detdpi_iddoc = '" + iddoc + "')  ORDER BY detdpi_numembala";

		resp = conn.get_dpidetalle(sql);
		return resp;
	}

	public ArrayList<dpidocitemoldcumplido> get_dpidetallecumplido(String barcodedoc, conexion conn) {
		ArrayList<dpidocitemoldcumplido> resp;

		String sql = "SELECT detdpi_item as item, detdpi_cant as cant, "
				+ " detdpi_estante as estante, detdpi_bodega as canasta, "
				+ "detdpi_ubica as ubica, detdpi_und as unidad, detdpi_peso as peso, "
				+ " detdpi_numembala as val_id, detdpi_descemb as embala,  detdpi_estado as cumplido "
				+ "FROM dpidetalle INNER JOIN " + " dpidoc ON dpidoc.iddpi = dpidetalle.detdpi_iddoc "
				+ "WHERE (dpidoc.dpibarcode = '" + barcodedoc + "')  ORDER BY detdpi_numembala";

		resp = conn.get_dpidetallecumplido(sql);
		return resp;

	}

//estante  = item referencia,  embala = tipodecaja, canasta = bodega, ubica= ubicacion

//public ArrayList<dpidocitemold> get_dpidetalle(String iddoc, conexion conn) {
//	 ArrayList<dpidocitemold> resp;
//                             
// String sql = "SELECT picking_detalle.iditem as item, picking_detalle.cantidad as cant, "
// 		+ " picking_detalle.itemreferencia as estante, picking_detalle.pick_det_bodega as canasta, "
//              + "picking_detalle.ubicacion as ubica, picking_detalle.idund as unidad, picking_detalle.peso_it as peso, "
//              + "picking_detalle.pick_det_numembala as val_id, picking_detalle.pick_det_tipoembala as embala "
//              + "FROM picking_detalle INNER JOIN " + 
//              " picking ON picking_detalle.idpicking = picking.idpicking INNER JOIN " + 
//              " dpidoc ON picking.barcodem = dpidoc.dpibarcode " + 
//              "WHERE (dpidoc.iddpi = '" + iddoc + "')  ORDER BY pick_det_numembala";  
// 
// resp = conn.get_dpidetalle(sql);
// return resp;
//}    

//	    public ArrayList<dpidocitemold> get_dpidetalle(String iddoc, conexion conn) {
//	    	 ArrayList<dpidocitemold> resp;
//	                                  
//	      String sql = "SELECT detdpi_item as item, detdpi_cant as cant, detdpi_estante as estante, detdpi_canasta as canasta, "
//	                   + "detdpi_ubica as ubica, detdpi_und as unidad, detdpi_peso as peso, detdpi_numembala as val_id, detdpi_descemb as embala" + 
//	                   " FROM dpidetalle " +
//	                   " WHERE detdpi_iddoc ='" + iddoc + "' ORDER BY detdpi_numembala";  
//	      resp = conn.get_dpidetalle(sql);
//	      return resp;
//	  }

	public dtuencabezado get_dpihead(String iddoc, conexion conn) {
		dtuencabezado resp;
		// SELECT iddpi as iddtu, dpibarcode as optbarcode, dpiestado as optest, dpiusu
		// as optusu, dpifechacrea as optfec, dpiidcliente, dpidesccliente, dpiidpedido
		String sql = "SELECT iddpi, dpibarcode, dpiestado, dpiusu, dpifechacrea, dpiidcliente, dpidesccliente, dpiidpedido"
				+ " FROM dpidoc " + " WHERE iddpi ='" + iddoc + "' ORDER BY iddpi";
		resp = conn.get_dtuhead(sql);
		return resp;
	}

	public dtuencabezado get_dpiheadbycode(String iddoc, conexion conn) {
		dtuencabezado resp;
		// SELECT iddpi as iddtu, dpibarcode as optbarcode, dpiestado as optest, dpiusu
		// as optusu, dpifechacrea as optfec, dpiidcliente, dpidesccliente, dpiidpedido
		String sql = "SELECT iddpi, dpibarcode, dpiestado, dpiusu, dpifechacrea, dpiidcliente, dpidesccliente, dpiidpedido"
				+ " FROM dpidoc " + " WHERE dpibarcode ='" + iddoc + "' ORDER BY iddpi";
		resp = conn.get_dtuhead(sql);
		return resp;
	}

//	     public ArrayList<dpidocitemoldcumplido> get_dpidetallecumplido(String barcodedoc, conexion conn) {
//	    	 ArrayList<dpidocitemoldcumplido> resp;
//	                                 
//	     String sql = "SELECT picking_detalle.iditem as item, picking_detalle.cantidad as cant, "
//	     		+ " picking_detalle.itemreferencia as estante, picking_detalle.pick_det_bodega as canasta, "
//	                  + "picking_detalle.ubicacion as ubica, picking_detalle.idund as unidad, picking_detalle.peso_it as peso, "
//	                  + "picking_detalle.pick_det_numembala as val_id, picking_detalle.pick_det_tipoembala as embala,  picking_detalle.cumplido "
//	                  + "FROM picking_detalle INNER JOIN " + 
//	                  " picking ON picking_detalle.idpicking = picking.idpicking INNER JOIN " + 
//	                  " dpidoc ON picking.barcodem = dpidoc.dpibarcode " + 
//	                  "WHERE (dpidoc.dpibarcode = '" + barcodedoc + "')  ORDER BY pick_det_numembala";  
//	     
//	     resp = conn.get_dpidetallecumplido(sql);
//	     return resp; 
//	     
//	     }

	public int close_docdpi(int iddoc, String codedoc, conexion conn) {
		int resp = 0;
		int est = 2;
		String sql = "UPDATE  dpidoc SET dpiestado = ? WHERE iddpi = ? ";
		resp = conn.update_consec(sql, est, iddoc);

		resp = creaevento(String.valueOf(iddoc), new Date(), conn);
		// close_docdpi_picking(codedoc, conn);
		return resp;
	}

//	     public int close_docdpi_picking(String codedoc, conexion conn) {
//	         int resp = 0;
//	         int est = 1;
//	         String sql = "UPDATE  dpidoc SET dpiestado = ? WHERE dpibarcode = ? AND (erp_co = '001') AND (erp_cia = 1)";
//	         resp = conn.update_consecpick(sql, est, codedoc);
//	               
//	         int idpicking = get_iddocpick(codedoc, conn);
//	         
//	         if (idpicking > 0) {
//	        	 idpicking = creaevento(String.valueOf(idpicking), new Date(), conn);
//				}
//	         
//	         return resp; 
//	     }

	public String cumple_dpidetpick(int idpicking, String iditem, int cantidad, String ubica, String idund,
			int numembala, String tipoembala, String strbod, String referencia, float peso, conexion conn) {
		String resp = "";
		// int idpicking = get_iddocpick(barcodepicking, conn);
		int est = 1;
		String sql = "UPDATE  dpidetalle SET detdpi_estado = ? "
				+ " WHERE detdpi_iddoc = ? AND detdpi_item = ? AND detdpi_cant = ? AND detdpi_ubica = ? AND detdpi_und = ? "
				+ " AND detdpi_numembala = ? AND detdpi_descemb = ? AND detdpi_bodega = ? AND detdpi_estante = ? AND detdpi_peso = ? ";

		int intresp = conn.update_cumpledetallepick(sql, est, idpicking, iditem, cantidad, ubica, idund, numembala,
				tipoembala, strbod, referencia, peso);

		return resp = Integer.toString(intresp);
	};

//			public String cumple_dpidetpick(String barcodepicking, String iditem, int cantidad, String ubica, String idund,
//					int numembala, String tipoembala, String strbod, String referencia, float peso, conexion conn) {
//				String resp = "";
//				int idpicking = get_iddocpick(barcodepicking, conn);
//				int est = 1;
//				String sql = "UPDATE  Picking_Detalle SET cumplido = ? "
//						+ " WHERE idpicking = ? AND iditem = ? AND cantidad = ? AND ubicacion = ? AND idund = ? "
//						+ " AND pick_det_numembala = ? AND pick_det_tipoembala = ? AND pick_det_bodega = ? AND itemreferencia = ? AND peso_it = ? ";
//			
//				int intresp = conn.update_cumpledetallepick(sql, est, idpicking, iditem, cantidad, ubica, idund, numembala, tipoembala, strbod, referencia, peso);
//				
//				return resp = Integer.toString(intresp);
//			};

	public String delete_dpidetalle(int idgrup, conexion conn) {
		String resp = "";
		int int_resp = 0;
		String sql = "";

		sql = "DELETE FROM  dpidetalle WHERE detdpi_iddoc = ?";

		int_resp = conn.delete_detalle(sql, idgrup);
		resp = String.valueOf(int_resp);

		return resp;
	}
	

//		      public String delete_pickdet(String barcodedoc, conexion conn) {
//		        String resp = "";
//		        int int_resp = 0;
//		        String sql="";
//		        
//		        int idpicking = get_iddocpick(barcodedoc, conn);
//		        	        
//		        sql = "DELETE FROM  picking_detalle WHERE idpicking = ?";
//		        
//		        int_resp = conn.delete_detalle(sql, idpicking);
//		        resp =String.valueOf(idpicking);
//		        return resp;
//		    }

	// *********** MOVIMIENTO CANASTILLA ******************

	/**
	 * Obtiene la información del movimiento de una canastilla. <br>
	 * 
	 * @param el id de la canastilla que queremos obtener la información, La conexión a la base de datos deseada.
	 * @return la información del movimiento de la canastilla Object[]{usuarioNombre,nombreCanastilla,descripcion,organizacionNombre,bodegaOrigen,bodegaDestino} <br>
	 */
	public mmto_canastilla getInfoCanastilla(String idCanastilla, conexion conn) {
		String sql = "SELECT U.strnombre as usuarioCrea, CONCAT(C1.txtdescripcion,' CANASTILLA ',C1.strcanastilla) as canastillaId,O.strorganizacion as organizacionId,(SELECT B.strbodega FROM bodega AS B WHERE B.idbodega=C.C_bodega_origen_id) AS bodegaOrigenId,(SELECT B.strbodega FROM bodega AS B WHERE B.idbodega=C.C_bodega_destino_id) AS bodegaDestinoId \r\n" + 
				"FROM mmto_canastilla AS C LEFT JOIN canastilla AS C1 ON C.idcanastilla=C1.idcanastilla\r\n" + 
				"LEFT JOIN organizacion AS O ON C.C_ciaorg_id=O.idorganizacion\r\n" + 
				"LEFT JOIN usuario AS U ON C.A_usuariocrea=U.strusuario\r\n" + 
				"WHERE C.idcanastilla="+idCanastilla+" AND C.E_cumplido=0";
		return conn.getInfoCanastilla(sql);
	}
	
	/**
	 * Permite saber cuál es la bodega destino de una canastilla. <br>
	 * 
	 * @param el codigo de la canastilla y la conexión a la base de datos.
	 * @return el codigo de la bodega destino de una canastilla
	 *         <br>
	 */
	public String getCanastillaDestino(String idCanastilla, conexion conn) {
		String sql =" SELECT idbodega_destino FROM canastilla WHERE idcanastilla="+idCanastilla;
		return conn.getCanastillaDestino(sql);
	}
	/**
	 * Permite entregar una canastilla a una bodega destino. <br>
	 * 
	 * @param el codigo de la canastilla, la bodega destino y la conexión a la base de datos.
	 * @return mensaje de confirmación para saber si se entregó o no la canastilla
	 *         <br>
	 */
	public String entregarCanastilla(String idCanastilla, String idBodega, conexion conn) {
		String temp = getCanastillaDestino(idCanastilla, conn);
		if(temp.equals(idBodega)) {
			String sql = "UPDATE mmto_canastilla SET E_cumplido=1, C_bodega_origen_id="+idBodega+" WHERE idcanastilla="+idCanastilla+" AND E_cumplido=0";
			conn.entregarCanastilla(sql);
			sql = "UPDATE canastilla SET idbodega="+idBodega+" WHERE idcanastilla="+idCanastilla+"";
			return conn.entregarCanastilla(sql);
		}
		else {
			return "Esta canastilla tiene una bodega destino diferente a la que se envio";
		}
		
	}
	public ArrayList<mmto_canastilla> getPendientes(String usuario, conexion conn) {
		String sql = "SELECT U.strnombre as usuarioCrea, CONCAT(C1.txtdescripcion,' CANASTILLA ',C1.strcanastilla) as canastillaId,O.strorganizacion as organizacionId,(SELECT B.strbodega FROM bodega AS B WHERE B.idbodega=C.C_bodega_origen_id) AS bodegaOrigenId,(SELECT B.strbodega FROM bodega AS B WHERE B.idbodega=C.C_bodega_destino_id) AS bodegaDestinoId \r\n" + 
				"FROM mmto_canastilla AS C LEFT JOIN canastilla AS C1 ON C.idcanastilla=C1.idcanastilla\r\n" + 
				"LEFT JOIN organizacion AS O ON C.C_ciaorg_id=O.idorganizacion\r\n" + 
				"LEFT JOIN usuario AS U ON C.A_usuariocrea=U.strusuario\r\n" + 
				"WHERE C.A_usuariocrea='"+usuario+"' AND C.E_cumplido=0";
		return conn.getPendientes(sql);
		
	}
	
	

}
