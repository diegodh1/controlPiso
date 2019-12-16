package com.integrapps.apih.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.integrapps.apih.conexion.ERP;
import com.integrapps.apih.conexion.conexion;
import com.integrapps.apih.conexion.operaciones;
import com.integrapps.apih.model.Documento;
import com.integrapps.apih.model.Ept;
import com.integrapps.apih.model.Eptdoc;
import com.integrapps.apih.model.Lista;
import com.integrapps.apih.model.Listaitem;
import com.integrapps.apih.model.dpidocitem;
import com.integrapps.apih.model.dpidocitemold;
import com.integrapps.apih.model.dpidocitemoldcumplido;
import com.integrapps.apih.model.dtuencabezado;
import com.integrapps.apih.model.mmto_canastilla;
import com.integrapps.apih.model.pedido;
import com.integrapps.apih.model.usuario;

@RestController
public class controlar {

	private conexion conn;
	private ERP conerp;

	@RequestMapping(value = "/**/organizaciones", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ArrayList<Lista> organizaciones() {
		operaciones l = new operaciones();
		conn = new conexion();
		conerp = new ERP();
		return l.carguelista(conn);

	}

	// Login
	@RequestMapping(value = "/**/login", method = RequestMethod.POST)
	public @ResponseBody usuario login(@RequestBody usuario us) {
		operaciones usudao = new operaciones();

		return usudao.login(us, conn);
	}

	@RequestMapping(value = "/**/bodegas", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ArrayList<Lista> bodegas() {
		operaciones l = new operaciones();
		return l.carguebodega(conn);

	}

	@RequestMapping(value = "/**/ubibodegas", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ArrayList<Lista> ubicabodega(@RequestBody String idbodegaept) {
		operaciones l = new operaciones();
		return l.cargueubicabodega(conn, idbodegaept);

	}

	@RequestMapping(value = "/**/findmoduloept", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ArrayList<Eptdoc> findmoduloept(@RequestBody String codebar) {
		operaciones l = new operaciones();
		return l.findmoduloept(codebar, conn);

	}

	// traer item de documento ept
	@RequestMapping(value = "/**/item_ept", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ArrayList<Ept> item_ept(@RequestBody Lista le) {
		operaciones l = new operaciones();
		return l.finditemeptdoc(le.getNombre(), conn);
	}

	// ubicacion del item en ept
	@RequestMapping(value = "/**/ubicaitem_ept", method = RequestMethod.POST, produces = "text/plain")
	public @ResponseBody String ubicaitem_ept(@RequestBody Ept ept) {
		operaciones l = new operaciones();
		return l.ubicaitem_ept(ept, conn);
	}

	// Crear el DTU
	@RequestMapping(value = "/**/crearDTU", method = RequestMethod.POST, produces = "text/plain")
	public @ResponseBody String crearDTU(@RequestBody ArrayList<Ept> ept) {
		operaciones l = new operaciones();
		return l.crearDTU(ept, conn);
	}

	// Generar el DTU
	@RequestMapping(value = "/**/generarDTU", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody Documento generarDTU(@RequestBody String iddtu) {
		operaciones l = new operaciones();
		return l.generarDTU(iddtu, conn);
	}

	// ELIMINAR ITEM RELACIONADO AL DTU
	@RequestMapping(value = "/**/DTUELIMINAR", method = RequestMethod.POST, produces = "text/plain")
	public @ResponseBody String DTUELIMINAR(@RequestBody ArrayList<Ept> ept) {
		operaciones l = new operaciones();
		return l.DTUELIMINAR(ept, conn);
	}

	///// Seccion Picking

	// Cargar Tipos de Pedido conexion con ERP
	@RequestMapping(value = "/**/getall_tipopedido", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ArrayList<Lista> getall_tipopedido() {
		operaciones gd = new operaciones();
		ArrayList<Lista> l = gd.carguelistaerp(conerp);
		return l;
	}

	// Cargar Clientes conexion con ERP
	@RequestMapping(value = "/**/getall_cliente", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ArrayList<Lista> getall_cliente(@RequestBody String seltipo) {
		operaciones gd = new operaciones();
		ArrayList<Lista> l = gd.getall_cliente(seltipo, conn);
		return l;
	}

	// Trae todos los pedidos
	@RequestMapping(value = "/**/getall_pedido", method = RequestMethod.POST)
	public @ResponseBody ArrayList<Lista> getall_pedido(@RequestBody pedido ped) {
		operaciones pedidodao = new operaciones();
		ArrayList<Lista> l = pedidodao.getall_pedido(ped, conn);
		return l;
	}

	// Trae la información de un pedido
	@RequestMapping(value = "/**/get_infopedido", method = RequestMethod.POST)
	public @ResponseBody ArrayList<Listaitem> get_infopedido(@RequestBody pedido ped) {
		operaciones pedidodao = new operaciones();
		ArrayList<Listaitem> l = pedidodao.get_infopedido(ped, conn);
		return l;
	}

	// Trae la información de un pedido
	@RequestMapping(value = "/**/get_infopedidotiponumero", method = RequestMethod.POST)
	public @ResponseBody ArrayList<Lista> get_infopedidotiponumero(@RequestBody pedido ped) {
		operaciones pedidodao = new operaciones();
		ArrayList<Lista> l = pedidodao.get_infopedidotiponumero(ped, conn);
		return l;
	}

	// Trae la información de un cliente
	@RequestMapping(value = "/**/get_infoclientebypick", method = RequestMethod.POST)
	public @ResponseBody ArrayList<Lista> get_infoclientebypick(@RequestBody pedido ped) {
		operaciones pedidodao = new operaciones();
		ArrayList<Lista> l = pedidodao.get_infoclientebypick(ped, conn);
		return l;
	}

	// Trae la información de la ubicaciòn de los item de un pedido
	@RequestMapping(value = "/**/get_ubicaitempedido", method = RequestMethod.POST)
	public @ResponseBody ArrayList<Listaitem> get_ubicaitempedido(@RequestBody pedido ped) {
		operaciones pedidodao = new operaciones();
		ArrayList<Listaitem> l = pedidodao.get_ubicaitempedido(ped, conerp, conn); // = new ArrayList();
		if (l.isEmpty()) {
			int i;
			i = 1;

//				    	  l.add(new Listaitem("21840","65-P071-3","26.0000","00101","I-08-03","UND","1"));
//				    	  l.add(new Listaitem("21840","65-P071-3","10.0000","00101","I-08-04","UND","1"));
//				    	  l.add(new Listaitem("21840","65-P071-3","10.0000","00101","I-08-05","UND","5"));
			// ArrayList<Listaitem>l= [{"item" : "21840", "descripcion" :
			// "65-P071-3","valor1":"26.0000", "valor2" : "00101", "valor3" : "I-08-03",
			// "valor4" : "UND" , "valor5" : "1"}];
		} else {
			// l.add(new
			// Listaitem("21840","65-P071-3","10.0000","00101","I-08-04","UND","1"));
		}

		return l;
	}

	// Trae la unidad de embalaje de los items
	@RequestMapping(value = "/**/getall_unidad", method = RequestMethod.POST)
	public @ResponseBody ArrayList<Lista> getall_unidad(@RequestBody pedido ped) {
		operaciones pedidodao = new operaciones();
		ArrayList<Lista> l = pedidodao.getall_unidad(conn);
		return l;
	}

	// Crea el documento
	@RequestMapping(value = "create_dpidoc", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody String create_dpidoc(@RequestBody pedido ped) {
		int idclie = ped.getIdcliente();
		int numped = ped.getIdpedido();
		String strdesclie = ped.getNombreCliente();
		String strtipo = ped.getTipo();
		String result = "";
		operaciones pedidodao = new operaciones();
		result = pedidodao.crea_doc(idclie, numped, strdesclie, strtipo, conn);
		return result;
	}

	// Crea el documento
//			    @RequestMapping(value = "crea_docpick", method = RequestMethod.POST, produces = "application/json")
//			     public @ResponseBody String crea_docpick(@RequestBody pedido ped) {
//			    	    String str_tipo = ped.getTipo();
//			            int numped = ped.getIdpedido();
//			            operaciones  pedidodao= new operaciones();
//			        String result = String.valueOf(pedidodao.crea_docpick(str_tipo, numped, conn));
//			       return result;   
//			     } 

	// Crea el detalle del documento
	@RequestMapping(value = "create_dpidetalle", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ArrayList<Lista> create_dpidetalle(@RequestBody dpidocitem dpiItem) {
		ArrayList<Lista> result = new ArrayList();
		int iddoc = dpiItem.getDocid();
		String item = dpiItem.getItem();
		int cant = dpiItem.getCant();
		String estante = dpiItem.getEstante();
		String unidad = dpiItem.getUnidad();
		String ubica = dpiItem.getUbica();
		String peso = dpiItem.getPeso();
		String id = dpiItem.getId();
		String embala = dpiItem.getEmbala();
		String emidtab = dpiItem.getEmidtab();
		String canasta = dpiItem.getCanasta();

		operaciones pedidodao = new operaciones();
		result = pedidodao.create_dpidetalle(iddoc, item, cant, estante, ubica, unidad, peso, id, embala, canasta,
				conn);

		return result;
	}

	// Crea el detalle del documento
//			   @RequestMapping(value = "create_dpidetpick", method = RequestMethod.POST, produces = "application/json")
//			    public @ResponseBody ArrayList<Lista> create_dpidetpick(@RequestBody dpidocitem dpiItem) {
//				   ArrayList<Lista> result = new ArrayList();
//			        int iddoc = dpiItem.getDocid();
//			        String item = dpiItem.getItem();
//			        int cant =  dpiItem.getCant();
//			        String estante = dpiItem.getEstante();
//			        String unidad = dpiItem.getUnidad();
//			        String ubica = dpiItem.getUbica();
//			        String strpeso = dpiItem.getPeso();
//			        float peso = Float.parseFloat(strpeso);
//			        int intId = Integer.parseInt(dpiItem.getId());
//			        String embala = dpiItem.getEmbala();
//			        String emidtab = dpiItem.getEmidtab();
//			        String canasta = dpiItem.getCanasta();
//			        String bodega = dpiItem.getBodega();
//			        
//			        operaciones  pedidodao= new operaciones();
////			        int idpicking, String iditem, int cantidad,  String ubica, String idund, int numembala, String tipoembala
//			        result = pedidodao.create_dpidetpick(iddoc, item,  cant, ubica, unidad, intId, embala, bodega, estante, peso, conn);
//			        
//			      return result;   
//			    }

	// Trae todos los documentos sin cerrar
	@RequestMapping(value = "getall_docdpi", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ArrayList<Lista> getall_docdpi(@RequestBody pedido ped) {
		ArrayList<Lista> result;
//			       if(conect==0){
//			           result = "[{ \"id\":1,\"desc\":\"DPI040000002\"}, { \"id\":2,\"desc\":\"DPI040000003\"}]";
//			           
//			           return result;
//			       }

		String str_idbod = ped.getTipo();

		operaciones pedidodao = new operaciones();
		result = pedidodao.getall_docact(str_idbod, conn);
		return result;
	}

	// Trae el detalle del documento
	@RequestMapping(value = "get_dpidetalle", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ArrayList<dpidocitemold> get_dpidetalle(@RequestBody pedido ped) {
		ArrayList<dpidocitemold> result;
//			       if(conect==0){
//			           result = "[{ \"id\":1,\"desc\":\"DPI040000002\"}, { \"id\":2,\"desc\":\"DPI040000003\"}]";
//			           
//			           return result;
//			       }
		String str_idbod = ped.getTipo();

		operaciones pedidodao = new operaciones();
		result = pedidodao.get_dpidetalle(str_idbod, conn);
		return result;
	}

	// Trae el detalle del documento
	@RequestMapping(value = "get_dpihead", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody dtuencabezado get_dpihead(@RequestBody pedido ped) {
		dtuencabezado result;
//			       if(conect==0){
//			           result = (String) "[{\"iddtu\":2,\"optest\":1,\"optbarcode\":\"DPI040000003\", \"optusu\":\"Usuario\",\"optfec\":\"2018-07-28 19:52:51.9\"}]";
//			           
//			           return result;
//			       }
		String str_idbod = ped.getTipo();

		operaciones pedidodao = new operaciones();
		result = pedidodao.get_dpihead(str_idbod, conn);
		return result;
	}

	// Trae el detalle del documento
	@RequestMapping(value = "get_dpiheadbycode", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody dtuencabezado get_dpiheadbycode(@RequestBody pedido ped) {
		dtuencabezado result;
//		       if(conect==0){
//		           result = (String) "[{\"iddtu\":2,\"optest\":1,\"optbarcode\":\"DPI040000003\", \"optusu\":\"Usuario\",\"optfec\":\"2018-07-28 19:52:51.9\"}]";
//		           
//		           return result;
//		       }
		String str_idbod = ped.getTipo();

		operaciones pedidodao = new operaciones();
		result = pedidodao.get_dpiheadbycode(str_idbod, conn);
		return result;
	}

	// Trae el detalle del documento
	@RequestMapping(value = "get_dpidetallecumplido", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ArrayList<dpidocitemoldcumplido> get_dpidetallecumplido(@RequestBody pedido ped) {
		ArrayList<dpidocitemoldcumplido> result;
//			       if(conect==0){
//			           result = "[{ \"id\":1,\"desc\":\"DPI040000002\"}, { \"id\":2,\"desc\":\"DPI040000003\"}]";
//			           
//			           return result;
//			       }
		String barcodedoc = ped.getTipo();

		operaciones pedidodao = new operaciones();
		result = pedidodao.get_dpidetallecumplido(barcodedoc, conn);
		return result;
	}

	// Cierra el documento
	@RequestMapping(value = "close_docdpi", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody String close_docdpi(@RequestBody pedido ped) {
		String result;
		int resp;
//			        if(conect==0){
//			            result= "1";
//			            
//			            return result;
//			        }

		int iddoc = ped.getIdpedido();
		String codedoc = ped.getNombrePedido();
		operaciones pedidodao = new operaciones();
		resp = pedidodao.close_docdpi(iddoc, codedoc, conn);
		result = String.valueOf(resp);
		return result;
	}

	// Crea el detalle del documento
	@RequestMapping(value = "cumple_dpidetpick", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody String cumple_dpidetpick(@RequestBody dpidocitem dpiItem) {
		String result = "";
		int iddoc = dpiItem.getDocid();
		String item = dpiItem.getItem();
		int cant = dpiItem.getCant();
		String estante = dpiItem.getEstante();
		String unidad = dpiItem.getUnidad();
		String ubica = dpiItem.getUbica();
		String strpeso = dpiItem.getPeso();
		float peso = Float.parseFloat(strpeso);
		int intId = Integer.parseInt(dpiItem.getId());
		String embala = dpiItem.getEmbala();
		String emidtab = dpiItem.getEmidtab();
		String canasta = dpiItem.getCanasta();
		String bodega = dpiItem.getBodega();

		operaciones pedidodao = new operaciones();
//				        int idpicking, String iditem, int cantidad,  String ubica, String idund, int numembala, String tipoembala
		result = pedidodao.cumple_dpidetpick(iddoc, item, cant, ubica, unidad, intId, embala, bodega, estante, peso,
				conn);

		return result;
	}

	// Borra el detalle del documnto
	@RequestMapping(value = "delete_dpidetalle", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody String delete_dpidetalle(@RequestBody pedido ped) {
		String result;
//				       if(conect==0){
//				           result= "1";
//				           
//				           return result;
//				       }
		int iddoc = ped.getIdpedido();
		String barcodedoc = ped.getNombrePedido();
		operaciones pedidodao = new operaciones();
		result = pedidodao.delete_dpidetalle(iddoc, conn);
		return result;
	}

	// Borra el detalle del documnto
//				   @RequestMapping(value = "delete_pickdet", method = RequestMethod.POST, produces = "application/json")
//				    public @ResponseBody String delete_pickdet(@RequestBody pedido ped) {
//				        String result;
////				       if(conect==0){
////				           result= "1";
////				           
////				           return result;
////				       }
//				       int iddoc = ped.getIdpedido();
//				       String barcodedoc = ped.getNombrePedido();
//				       operaciones  pedidodao= new operaciones();
//				       result = pedidodao.delete_pickdet(barcodedoc, conn);
//				       return result;   
//				    }

	// *********************** MOVIMIENTO CANASTILLA **************************

	@RequestMapping(value = "/**/getInfoCanastilla", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ArrayList<mmto_canastilla> getInfoCanastilla(@RequestBody String idCanastilla) {
		operaciones mmtoCanastilla = new operaciones();
		ArrayList<mmto_canastilla> arr = new ArrayList<mmto_canastilla>();
		arr.add(mmtoCanastilla.getInfoCanastilla(idCanastilla, conn));
		return arr;
	}

	@RequestMapping(value = "/**/entregarCanastilla", method = RequestMethod.POST, produces = "text/plain")
	public @ResponseBody String entregarCanastilla(@RequestBody String data) {
		operaciones entrega = new operaciones();
		String[] canastilla = data.split(",");
		if (canastilla.length == 2) {
			return entrega.entregarCanastilla(canastilla[0], canastilla[1], conn);
		} else {
			return "Los parametos pasados en la peticion no coinciden";
		}
	}
	
	@RequestMapping(value = "/**/getPendientes", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody ArrayList<mmto_canastilla> getPendientes(@RequestBody String usuario) {
		operaciones mmtoCanastilla = new operaciones();
		return mmtoCanastilla.getPendientes(usuario, conn);
	}

}
