<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html ng-app="app">
<head>
<link rel="shortcut icon" type="image/x-icon"
	href="<c:url value="/resource/image/favicon.ico" />">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="<c:url value="/resource/css/jquery.mobile-1.4.5.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/resource/css/index.css" />" rel="stylesheet">
<link href="<c:url value="/resource/css/alertify.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/resource/css/default.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/resource/css/semantic.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/resource/css/bootstrap.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/resource/css/own.com.css" />"
	rel="stylesheet">
<script type="text/javascript"
	src="<c:url value="/resource/js/jquery-1.11.1.min.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resource/js/jquery.mobile-1.4.5.min.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resource/js/angular.min.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resource/js/angular-cookie.min.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resource/js/alertify.min.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resource/js/JsBarcode.all.min.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resource/js/app.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resource/js/index.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resource/js/jspdf.min.js" />"></script>
</head>
<style>
.center-button{
  margin: 0 auto;
}
</style>
<body ng-controller="inicio">
	<div data-role="page" class="jqm-demos ui-responsive-panel" id="inicio"
		data-title="Panel Principal" data-url="panel-responsive-page1">
		<div data-role="header">
			<h1>Menú Principal</h1>
			<a href="#nav-panel" data-icon="bars">Menu</a> <a
				href="#iniciasesion" data-icon="gear">Login</a>
		</div>
		<!-- /header -->
		<div role="main" class="ui-content">
			<div class="ui-grid-solo">
				<b>Organización: <span class="black-text">{{nombreorg}}</span></b>
			</div>
			<div class="ui-grid-solo">
				<b>Bienvenido, <span class="black-text">{{nombreusuario}}</span></b>
			</div>
		</div>
		<div data-role="panel" data-display="push" data-theme="b"
			id="nav-panel" ng-show="nombreorg !== ''">

			<ul data-role="listview">
				  
				<li data-icon="delete"><a href="#" data-rel="close">Cerrar
						menú</a></li>   
				<li><a href="#traslado" ng-click="traslado();">Traslado de
						Ubicación</a></li>
				<li><a href="#movimientoCanastilla" ng-click="getCanastillasPendientes();" ng-show="true">Movimientos</a></li>
				<li>
					<div data-role="collapsible" data-inset="false">
						<h2>Picking</h2>
						<ul data-role="listview" style="background-color: whitesmoke">
							<li><a href="#pickingcrea" ng-click="pickingcrea();">Crear</a></li>
							<li><a href="#pickingprint" ng-click="pickingedita();"
								ng-show="true">Editar</a></li>
							<li><a href="#pickingprint" ng-click="pickingprint();">Ver</a></li>
							<li><a href="#cumplimientopicking"
								ng-click="pickingclosed();" ng-show="true">Cumplir</a></li>

						</ul>
					</div> <!-- /collapsible -->
				</li>
			</ul>
		</div>
		<!-- /page -->

		<div data-role="footer">
			<h4>copyright &#169; 2019 Integrapps</h4>
		</div>
		<!-- /footer -->
	</div>

	<div data-role="page" id="iniciasesion">
		<div data-role="header">
			<h1>Inicio de Sesión</h1>
		</div>
		<!-- /header -->
		<div role="main" class="ui-content fondogris">
			<div class="ui-grid-solo right">
				<a href="#inicio"
					class="ui-btn ui-shadow ui-corner-all ui-btn-inline ui-mini ui-icon-back ui-btn-icon-left">Regresar</a>
			</div>
			<form ng-submit="validainicio(forminicio.$valid);" name="forminicio">
				            
				<h2>Inicio Sesión</h2>
				<label for="organizacion">Organización:</label> <select
					name="organizacion" id="organizacion" required
					ng-model="organizacion">
					<option value="" selected diabled="disabled">Seleccionar</option>
					<option ng-value="org.id" ng-repeat="org in organizaciones">{{org.nombre}}</option>
				</select> <label>* Usuario:</label> <input type="text" ng-model="login"
					required>            <label>* Contraseña:</label> <input
					type="password" ng-model="clave" required>             
				<div class="ui-grid-a">
					                <input type="submit"
						class="ui-btn ui-shadow ui-corner-all ui-btn-a ui-mini"
						value="Iniciar">             
				</div>
				 
			</form>
		</div>
	</div>
	<!-- /page -->

	<div data-role="page" id="DTU">
		<div role="main" class="ui-content">
			<div class="ui-grid-solo right">
				<a href="#traslado" ng-click="traslado();"
					class="ui-btn ui-shadow ui-corner-all ui-btn-inline ui-mini ui-icon-back ui-btn-icon-left">Regresar</a>
			</div>
			<div class="ui-responsive">
				<div class="ui-grid-solo" align="center">Documento de Traslado
					de Ubicación</div>
				<div class="ui-grid-solo" align="center">
					<img id="barcodedtu" />
				</div>
				<div class="ui-grid-a">
					<div class="ui-block-a">
						<div class="ui-bar ui-bar-a"
							style="height: 200px; max-height: 200px; background-color: white !important;">
							<div>No:{{numdtu}}</div>
							<div>Realiz&oacute;:{{nombrerealizo}}</div>
							<div>Fecha de Creaci&oacute;n:{{fechadtu}}</div>
						</div>
					</div>
					<div class="ui-block-b">
						<div class="ui-bar ui-bar-a"
							style="height: 200px; max-height: 200px; background-color: white !important;">
							<img src="<c:url value="/resource/image/logohercules.png" />"
								style="width: 80%; max-height: 198px;">
						</div>
					</div>
				</div>
				<div class="ui-grid-solo" align="center">
					<h4>&Iacute;tems</h4>
				</div>
				<table data-role="table" class="ui-responsive">
					<thead>
						<tr>
							<th data-priority="2">Item</th>
							<th data-priority="4">Cantidad Entregada</th>
							<th data-priority="3">Estanteria</th>
							<th data-priority="1">Localización</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="i in itemdtu">
							<td>{{i.coditem}}</td>
							<td>{{i.cantubicada}}</td>
							<td>{{i.strubicacion}}</td>
							<td>{{i.strlocalizacion}}</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<!-- /page -->

	<!-- ******************* MOVIMIENTO CANASTILLA *************************************** -->
	<div data-role="page" id="movimientoCanastilla">
		<div data-role="header" style="background-color: yellow !important;">
			<div class="ui-grid-solo ui-shadow">
					<h1 style="color: black !important; text-align: center;">Movimiento de Canastilla</h1>
			</div>
		</div>
		<!-- /header -->

		<div role="main" class="ui-content" style="background-color: white !important;">
		<a href="#inicio"
						class="ui-btn ui-shadow ui-corner-all ui-btn-inline ui-mini ui-icon-back ui-btn-icon-left"
						style="background-color: white !important;">Regresar</a>
			<br>
			<label for="canastillaId"><span style="color:black">CANASTILLA ID:</span></label>
			<input type="text" name="text-1" id="canastillaId" ng-model="idCanastila" ng-change="getInfoCanastilla();">
			<table data-role="table" data-mode="reflow" class="ui-responsive">
			<caption><span style="color:black"><b>INFORMACIÓN CANASTILLA</b></span></caption>
				<thead>
					<tr>
						<th style="color:black;" bgcolor="#E77415" data-priority="1">Organización</th>
						<th style="color:black;" bgcolor="#E77415" data-priority="2">Canastilla</th>
						<th style="color:black;" bgcolor="#E77415" data-priority="3">Bodega Origen</th>
						<th style="color:black;" bgcolor="#E77415" data-priority="4">Bodega Destino</th>
						<th style="color:black;" bgcolor="#E77415" data-priority="5">Usuario Encargado</th>
					</tr>
				</thead>
				<tbody>
						<tr ng-repeat="x in canastillaObject">
							<th>{{x.organizacionId}}</th>
							<td>{{x.canastillaId}}</td>
							<td>{{x.bodegaOrigenId}}</td>
							<td>{{x.bodegaDestinoId}}</td>
							<td>{{x.usuarioCrea}}</td>
						</tr>
				</tbody>
			</table>
			
			<br>
			<label for="bodegaId"><span style="color:black">BODEGA DESTINO ID:</span></label>
			<input type="text" name="text-1" id="bodegaId" ng-model="idBodegaDestino">
			<div class="center-button">
			<a href="" ng-click="entregarCanastilla();" class="ui-btn ui-shadow ui-corner-all ui-icon-arrow-r ui-btn-icon-right"><span style="color:black">Entregar Canastilla</a>
			</div>
			<br>
			<table data-role="table" data-mode="reflow" class="ui-responsive">
			<caption><span style="color:black"><b>CANASTILLAS PENDIENTES</b></span></caption>
				<thead>
					<tr>
						<th style="color:black;" bgcolor="#E77415" data-priority="1">Organización</th>
						<th style="color:black;" bgcolor="#E77415" data-priority="2">Canastilla</th>
						<th style="color:black;" bgcolor="#E77415" data-priority="3">Bodega Origen</th>
						<th style="color:black;" bgcolor="#E77415" data-priority="4">Bodega Destino</th>
						<th style="color:black;" bgcolor="#E77415" data-priority="5">Usuario Encargado</th>
					</tr>
				</thead>
				<tbody>
						<tr ng-repeat="x in canastillaPendiente">
							<th>{{x.organizacionId}}</th>
							<td>{{x.canastillaId}}</td>
							<td>{{x.bodegaOrigenId}}</td>
							<td>{{x.bodegaDestinoId}}</td>
							<td>{{x.usuarioCrea}}</td>
						</tr>
				</tbody>
			</table>
		</div>
	</div>
	<!-- /page -->

	<div data-role="page" id="traslado">

		<div data-role="header">
			<h1>Traslado de Ubicación</h1>
		</div>
		<!-- /header -->

		<div role="main" class="ui-content fondogris">
			<div class="ui-grid-solo right">
				<a href="#inicio"
					class="ui-btn ui-shadow ui-corner-all ui-btn-inline ui-mini ui-icon-back ui-btn-icon-left">Regresar</a>
			</div>
			<form>
				<label for="bodegaept">Bodega:</label> <select name="bodegaept"
					id="bodegaept" required ng-model="bodegaept">
					<option value="" disabled="disabled" selected>Seleccionar</option>
					<option ng-value="org.id" ng-repeat="org in bodegasept">{{org.nombre}}</option>
				</select> <label for="ubibodega">Ubicación en Bodega:</label> <select
					name="ubibodega" id="ubibodega" required ng-model="ubibodega">
					<option value="" disabled="disabled" selected>Seleccionar</option>
					<option ng-value="org.id" ng-repeat="org in ubibodegas">{{org.nombre}}</option>
				</select>
			</form>
			<div class="ui-grid-a ui-responsive">
				<div class="pt-1 informacion" ng-show="vermodulo">
					<b>MODULO: <span class="black-text">{{nombremodulo}}</span></b>
				</div>
				<div class="pt-1 informacion" ng-show="vermodulo">
					<b>LOCALIZACIÓN: <span class="black-text">{{nombrelocalizacion}}</span></b>
				</div>
				<div class=" pt-1">
					<label for="lecturaept"><b>Modulo ó Documento EPT:</b></label> <input
						name="lecturaept" ng-model="lecturaept" id="lecturaept"
						ng-keyup="$event.keyCode == 13 && buscareptdoc();">
				</div>
				<label for="itemubicado"><b>Lectura de Item:</b></label> <input
					type="text" name="itemubicado" ng-model="itemubicado"
					id="itemubicado" ng-keyup="$event.keyCode == 13 && itemubicar();">
				<div class="ui-grid-solo ui-reponsive " ng-show="selectitem">
					<div class="ui-body ui-body-d">
						<table data-role="table" class="ui-responsive">
							<thead>
								<tr>
									<th data-priority="2">Cod.Item</th>
									<th data-priority="4">Desc.Item</th>
									<th data-priority="3">Cant.Enviada</th>
									<th data-priority="1">Cant.Ubicada</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<th>{{coditem}}</th>
									<td>{{descitem}}</td>
									<td>{{cantenv}}</td>
									<td>
										<form>
											<input type="number" ng-model="cantubicada" pattern="[0-9]*"
												class="inputnumero">
										</form>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				<label for="local"><b>Localización:</b></label> <input type="text"
					name="local" ng-model="localizacion" id="local"
					ng-keyup="$event.keyCode == 13 && buscaritemept();">
				<form class="ui-filterable" ng-show="tbitems.length > 0">
					<input id="filtroitem" data-type="search"
						placeholder="Buscar por Item">
				</form>
				<table data-role="table" id="listitem" data-filter="true"
					data-input="#filtroitem" class="ui-responsive table-stroke"
					data-mode="reflow" ng-show="tbitems.length > 0">
					<thead>
						<tr>
							<th data-priority="1">Num.</th>
							<th data-priority="persist">Cod.Item</th>
							<th data-priority="2">Desc.Item</th>
							<th data-priority="3">Cant.Enviada</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="items in tbitems">
							<th>{{$index+1}}</th>
							<td>{{items.coditem}}</td>
							<td>{{items.descripcionitem}}</td>
							<td>{{items.cantenviada}}</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<!-- /content -->
	</div>
	<!-- /page -->

	<div data-role="page" id="configuracion">

		<div data-role="header">
			<h1>Configuración</h1>
		</div>
		<!-- /header -->

		<div role="main" class="ui-content fondogris">
			<div class="ui-grid-solo">
				<a ng-href="#iniciasesion"
					class="ui-btn  ui-btn-inline ui-icon-home  ui-btn-icon-right">Inicio
					de Sesión</a>
			</div>
			<br />
			<div class="ui-grid-solo center ">
				<form ng-submit="guardarconfig(formconfig.$valid);"
					name="formconfig">
					<label>* IP Servidor:</label> <input type="text"
						ng-model="ipserver" required> <label>* Puerto:</label> <input
						type="text" ng-model="puerto" required> <input
						type="submit" data-inline="true" value="Guardar">
				</form>
			</div>
		</div>
		<!-- /content -->
	</div>
	<!-- /page -->

	<div data-role="page" id="pickingcrea">
		<div data-role="header">
			<h1>Crear Documento de Picking</h1>
		</div>
		<!-- /header -->

		<div role="main" class="ui-content"
			style="background-color: white !important;">
			<div class="ui-grid-solo right">
				<a href="#inicio"
					class="ui-btn ui-shadow ui-corner-all ui-btn-inline ui-mini ui-icon-back ui-btn-icon-left"
					style="background-color: white !important;">Regresar</a>
			</div>

			<label for="seltipo">Tipo Pedido:</label> <select name="seltipo"
				id="seltipo" required ng-model="seltipo"
				ng-change="cambiaseltipo();">
				<option value="" disabled="disabled" selected>Seleccionar</option>
				<option ng-value="org.nombre" ng-repeat="org in ar_tipopedido">{{org.nombre}}</option>
			</select> <label for="selcliente">Cliente:</label> <select name="selcliente"
				id="selcliente" required ng-model="selcliente"
				ng-change="cambioselcliente();">
				<option value="" disabled="disabled" selected>Seleccionar</option>
				<option ng-value="org.id" ng-repeat="org in ar_cliente">{{org.nombre}}</option>
			</select> <label for="selpedido">Pedido:</label> <select name="selpedido"
				id="selpedido" required ng-model="selpedido"
				ng-change="cambioselpedido();">
				<option value="" disabled="disabled" selected>Seleccionar</option>
				<option ng-value="org.id" ng-repeat="org in ar_pedido">{{org.nombre}}</option>
			</select>
			<div class="ui-grid-a">
				              <input type="submit" id="btninfopedido"
					name="btninfopedido"
					class="ui-btn ui-shadow ui-corner-all ui-btn-a ui-mini"
					value="Buscar" ng-click="get_infopedido();" ng-disabled="">
				         
			</div>

			<div class="ui-grid-solo ui-reponsive">
				<div class="ui-body ui-body-d">


					<table data-role="table" data-mode="reflow"
						class="ui-responsive table-stroke" id="tbitempedido" border="1">
						<thead>
							<tr>
								<th data-priority="2">Ítem</th>
								<th data-priority="4">Descripción</th>
								<th data-priority="5">Referencia</th>
								<th data-priority="1">Cantidad Pendiente</th>
								<th data-priority="3">Cantidad Remisionada</th>
								<th data-priority="6">Unidad</th>
								<th data-priority="7">Peso Unitario</th>
								<th data-priority="8">Buscar</th>
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="value in ar_infopedido">
								<th>{{value.item}}</th>
								<td>{{value.descripcion}}</td>
								<td>{{value.valor1}}</td>
								<td>{{value.valor2}}</td>
								<td>{{value.valor3}}</td>
								<td>{{value.valor4}}</td>
								<td>{{value.valor5}}</td>
								<td>
									<form>
										<input type="submit"
											class="ui-btn ui-shadow ui-corner-all ui-btn-a ui-mini"
											value="Buscar"
											ng-click="buscaitem(value.item, value.descripcion, value.valor2, value.valor4, value.valor5);">
									</form>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>

			<div data-role="header">
				<h1>Ubicación del Item</h1>
			</div>
			<!-- /header -->

			<div class="ui-grid-solo ui-reponsive">
				<div class="ui-body ui-body-d">
					<table data-role="table" data-mode="reflow"
						class="ui-responsive table-stroke" id="tbubicaitempedido"
						border="1">
						<thead>
							<tr>
								<th data-priority="2">Ítem</th>
								<th data-priority="4">Referencia</th>
								<th data-priority="1">Cantidad Disponible</th>
								<th data-priority="3">Bodega</th>
								<th data-priority="5">Ubicación</th>
								<th data-priority="6">Seleccionar</th>
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="value in ar_itembodega">
								<th>{{value.item}}</th>
								<td>{{value.descripcion}}</td>
								<td>{{value.valor1}}</td>
								<td>{{value.valor2}}</td>
								<td>{{value.valor3}}</td>
								<td>
									<form>
										<input type="submit"
											class="ui-btn ui-shadow ui-corner-all ui-btn-a ui-mini"
											value="Iniciar"
											ng-click="iniciaorganiza(value.item, value.descripcion, value.valor1, value.valor3, value.valor2);">
									</form>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>


			<div data-role="collapsible" data-collapsed="false">
				<div data-role="header">
					<h1>Seleccionar Unidad de Embalaje</h1>
				</div>
				<!-- /header -->

				<fieldset class="ui-grid-a">
					<div class="ui-block-a">
						<label for="itembala">Número:</label> <input type="number"
							id="itembala" ng-model="itembala" name="itembala" disabled="true">
					</div>
					<div class="ui-block-b">
						<label for="selun_embala">Unidad de Embalaje:</label> <select
							name="selun_embala" id="selun_embala" required
							ng-model="selun_embala" ng-change="sel_embala();">
							<option value="" disabled="disabled" selected>Seleccionar</option>
							<option ng-value="org.id" ng-repeat="org in ar_unembala">{{org.nombre}}</option>
						</select>
					</div>
					<div class="ui-block-c">
						<input type="submit" value="Nuevo" name="btncreaembala"
							id="btncreaembala" ng-click="creaembala();">  
					</div>
					<div class="ui-block-d">
						<label for="actuni_emb">Seleccionada:</label> <input type="text"
							id="actuni_emb" ng-model="actuni_emb" name="actuni_emb"
							class="grey-text" disabled="true" style="font-weight: bold;">
					</div>

				</fieldset>

			</div>

			<div data-role="collapsible">
				<h4>Items para asignar</h4>
				<div>
					<form name="frmsetop" id="frmsetop" method="get">
						<fieldset class="ui-grid-a">
							<div class="ui-block-a">
								<label for="numord">Item:</label> <input type="text" id="numord"
									ng-model="numord" name="numord" class="grey-text"
									disabled="true" style="font-weight: bold;">
							</div>
							<div class="ui-block-b">
								<label for="itdesc">Descripcion:</label> <input type="text"
									id="itdesc" ng-model="itdesc" name="itdesc" class="grey-text"
									disabled="true" style="font-weight: bold;">
							</div>
							<div class="ui-block-c">
								<label for="numcanasta">Ubicación</label> <input type="text"
									id="numcanasta" ng-model="numcanasta" name="numcanasta"
									class="grey-text" disabled="true" style="font-weight: bold;">
							</div>
							<div class="ui-block-a">
								<label for="ordCant">Cantidad:</label> <input type="number"
									id="ordCant" ng-model="ordCant" name="ordCant"
									class="grey-text" style="font-weight: bold;"
									ng-change="cambiacantorganiza();"
									ng-click="cambiacantorganiza();"
									ng-disabled="habilitanumcant();">
							</div>
							<div class="ui-block-b">
								<label for="numunidad">Unidad:</label> <input type="text"
									id="numunidad" ng-model="numunidad" name="numunidad"
									class="grey-text" disabled="true" style="font-weight: bold;">
							</div>
							<div class="ui-block-c">
								<label for="itpeso">Peso:</label> <input type="text" id="itpeso"
									ng-model="itpeso" name="itpeso" class="grey-text"
									disabled="true" style="font-weight: bold;">
							</div>


						</fieldset>
						<div class="ui-grid-solo">
							<div class="ui-block-a">
								<button id="btnasignaestante" name="btnasignaestante"
									class="waves-effect  btn blue-grey darken-3"
									ng-click="asignaestante();"
									ng-disabled="habilitaasignaestante();">Adicionar</button>
							</div>
						</div>
					</form>
				</div>


				<div>
					<div class="row">
						<div class="col s12 mt-2">
							<table id="tblasignada" datatable="ng"
								dt-options="showCase.dtOptions"
								class="row-border hover responsive-table" cellspacing="0"
								style="width: 100%; font-size: 80%;" sortable="false">
								<thead>
									<tr>
										<th class="center">Id</th>
										<th class="center">Embalaje</th>
										<th class="center" ng-show="false">EmbalajeId</th>
										<th class="center">Item</th>
										<th class="center">Descripción</th>
										<th class="center">Cantidad</th>
										<th class="center">Bodega</th>
										<th class="center">Ubicacion</th>
										<th class="center">Unidad</th>
										<th class="center">Peso Unitario</th>
										<th class="center">Peso Total</th>
										<th class="center">Eliminar</th>
									</tr>
								</thead>

								<tbody>
									<tr ng-repeat="value in ar_itemorganiza">
										<td class="center" ng-if="$odd"
											style="background-color: #d7f5fa">{{value.id}}</td>
										<td class="center" ng-if="$even">{{value.id}}</td>
										<td class="center" ng-if="$odd"
											style="background-color: #d7f5fa">{{value.embala}}</td>
										<td class="center" ng-if="$even">{{value.embala}}</td>
										<td class="center" ng-if="$odd"
											style="background-color: #d7f5fa" ng-show="false">{{value.emidtab}}</td>
										<td class="center" ng-if="$even" ng-show="false">{{value.emidtab}}</td>
										<td class="center" ng-if="$odd"
											style="background-color: #d7f5fa">{{value.item}}</td>
										<td class="center" ng-if="$even">{{value.item}}</td>
										<td class="center" ng-if="$odd"
											style="background-color: #d7f5fa">{{value.estante}}</td>
										<td class="center" ng-if="$even">{{value.estante}}</td>
										<td class="center" ng-if="$odd"
											style="background-color: #d7f5fa">{{value.cant}}</td>
										<td class="center" ng-if="$even">{{value.cant}}</td>
										<td class="center" ng-if="$odd"
											style="background-color: #d7f5fa">{{value.canasta}}</td>
										<td class="center" ng-if="$even">{{value.canasta}}</td>
										<td class="center" ng-if="$odd"
											style="background-color: #d7f5fa">{{value.ubica}}</td>
										<td class="center" ng-if="$even">{{value.ubica}}</td>
										<td class="center" ng-if="$odd"
											style="background-color: #d7f5fa">{{value.unidad}}</td>
										<td class="center" ng-if="$even">{{value.unidad}}</td>
										<td class="center" ng-if="$odd"
											style="background-color: #d7f5fa">{{value.peso}}</td>
										<td class="center" ng-if="$even">{{value.peso}}</td>
										<td class="center" ng-if="$odd"
											style="background-color: #d7f5fa">{{value.pesotot}}</td>
										<td class="center" ng-if="$even">{{value.pesotot}}</td>
										<td class="center" ng-if="$odd"
											style="background-color: #d7f5fa">
											<button
												class="btn-floating waves-effect waves-light green darken-3 tooltipped"
												data-position="top" data-delay="1"
												ng-click="removeitrow($index);" data-tooltip="Borrar Piezas">
												<i class="material-icons">Borrar</i>
											</button>
										</td>
										<td class="center" ng-if="$even">
											<button
												class="btn-floating waves-effect waves-light green darken-3 tooltipped"
												data-position="top" data-delay="1"
												ng-click="removeitrow($index);" data-tooltip="Borrar Piezas">Borrar
											</button>
										</td>
									</tr>
								</tbody>
							</table>
						</div>

						<div class="input-field col m3 s12">
							<input type="number" id="pesodoc" ng-model="pesodoc"
								name="pesodoc" class="grey-text" style="font-weight: bold;"
								ng-disabled="true"> <label for="pesodoc">Peso
								del picking:</label>
						</div>
						<div class="input-field col m4 s12 right-align mt-2">
							<button id="btnguardaorg"
								class="waves-effect  btn blue-grey darken-3"
								ng-click="guardadocorganiza();"
								ng-disabled="habilitaguardadocorganiza();">{{btnguardaorg}}</button>
						</div>
					</div>
				</div>
			</div>

		</div>
		<!-- /content -->
	</div>
	<!-- /page -->

	<div data-role="page" id="pickingprint">
		<div data-role="header">
			<h1>{{txtButtonSel}} Picking</h1>
		</div>
		<!-- /header -->

		<div role="main" class="ui-content"
			style="background-color: white !important;">
			<div class="ui-grid-solo right">
				<a href="#inicio"
					class="ui-btn ui-shadow ui-corner-all ui-btn-inline ui-mini ui-icon-back ui-btn-icon-left"
					style="background-color: white !important;">Regresar</a>
			</div>

			<div data-role="collapsible" data-collapsed="false">
				<h4>Seleccionar Documento</h4>
				<div>
					<div>
						<label for="seldocorg">Codigo del documento: </label> <select
							id="seldocorg" class="browser-default" ng-model="seldocorg"
							required="required">
							<option class="blue-grey-text" value="" disabled selected
								style="font-family: 'Open Sans Condensed', sans-serif; font-size: 1rem;">Seleccionar...</option>
							<option ng-value="v.id" ng-repeat="v in ar_docorg"
								style="font-family: 'Open Sans Condensed', sans-serif; font-size: 1rem;">{{v.desc}}</option>

						</select>

					</div>
					<div class="ui-grid-solo">
						<div class="ui-block-a">
							<button id="btninfodocumento" name="btninfodocumento"
								class="waves-effect  btn blue-grey darken-3"
								ng-click="infodocumento();">{{txtButtonSel}}</button>
						</div>
					</div>

				</div>
			</div>
			<!-- /collapsible -->

		</div>
		<!-- /ui-content -->
	</div>
	<!-- /page -->

	<div data-role="page" id="pickingdoc">
		<div data-role="header" id="headpickingdoc">

			<div class="ui-grid-solo right">

				<h1>Imprimir Picking</h1>

				<a href="#inicio"
					class="ui-btn ui-shadow ui-corner-all ui-btn-inline ui-mini ui-icon-back ui-btn-icon-left"
					style="background-color: white !important;">Regresar</a>
				<!-- 				<a ng-click="imprimirPdf();" -->
				<!-- 				    class="ui-btn ui-shadow ui-corner-all ui-btn-inline ui-mini ui-btn-icon-left" -->
				<!-- 					style="background-color: white !important;">Generar Pdf</a> -->
			</div>
		</div>
		<!-- /header -->

		<div role="main" class="ui-content" id="pdfcontent"
			style="background-color: white !important;">
			<div id="pdfcontent" style="background-color: white !important;">
				<div align="center">Documento de Picking</div>
				<div align="center">
					<img id="barcodedpi" />
				</div>
				<div class="ui-grid-a">
					<div class="ui-block-a">
						<div class="ui-bar ui-bar-a"
							style="height: 100px; max-height: 100px; background-color: white !important;">
							<div>No:{{optbarcode}}</div>
							<div>Realiz&oacute;:{{usua}}</div>
							<div>Fecha de Creaci&oacute;n:{{fecagrup}}</div>
						</div>
					</div>
					<div class="ui-block-b">
						<div class="ui-bar ui-bar-a"
							style="height: 100px; max-height: 100px; background-color: white !important;">
							<img src="<c:url value="/resource/image/logohercules.png" />"
								style="width: 80%; max-height: 100px;">
						</div>
					</div>
				</div>
				<div align="center">
					<h4>&Iacute;tems</h4>
				</div>
				<div style="position: relative; top: 0.2in; width: 100%;">

					<div class="row">
						<table id="tbdetallepdf" data-role="table" data-mode="reflow"
							class="ui-responsive table-stroke" border="1">
							<thead>
								<tr>
									<th>Id</th>
									<th>Embalaje</th>
									<th>Item</th>
									<th>Descripción</th>
									<th>Cantidad</th>
									<th>Bodega</th>
									<th>Ubicacion</th>
									<th>Unidad</th>
									<th>Peso Unitario</th>
									<th>Peso Total</th>
								</tr>
							</thead>

							<tbody>
								<tr ng-repeat="value in ar_itemorganiza">
									<td>{{value.id}}</td>
									<td>{{value.embala}}</td>
									<td>{{value.item}}</td>
									<td>{{value.estante}}</td>
									<td>{{value.cant}}</td>
									<td>{{value.canasta}}</td>
									<td>{{value.ubica}}</td>
									<td>{{value.unidad}}</td>
									<td>{{value.peso}}</td>
									<td>{{value.pesotot}}</td>
								</tr>
							</tbody>
						</table>

						<div>Peso del picking: {{pesodoc}} Kg.</div>

					</div>
				</div>
			</div>


		</div>
		<!-- /ui-content -->
	</div>
	<!-- /page -->

	<div data-role="page" id="cumplimientopicking">

		<div data-role="header">
			<h1>Cumplir Picking</h1>
		</div>
		<!-- /header -->

		<div role="main" class="ui-content fondogris">
			<div class="ui-grid-solo right">
				<a href="#inicio"
					class="ui-btn ui-shadow ui-corner-all ui-btn-inline ui-mini ui-icon-back ui-btn-icon-left">Regresar</a>
			</div>

			<div class="ui-grid-a ui-responsive">
				<div class=" pt-1">
					<label for="lecturadocpic"><b>Documento Picking:</b></label> <input
						name="lecturadocpic" ng-model="lecturadocpic" id="lecturadocpic"
						ng-keyup="$event.keyCode == 13 && buscardocpic();">
				</div>
				<form>
					<label for="bodegadocpic">Bodega:</label> <select
						name="bodegadocpic" id="bodegadocpic" required
						ng-model="bodegadocpic">
						<option value="" disabled="disabled" selected>Seleccionar</option>
						<option ng-value="org.id" ng-repeat="org in ar_bodegasdocpic">{{org.nombre}}</option>
					</select>
					<div class=" pt-1">
						<label for="lecturaubicapic"><b>Ubicación:</b></label> <input
							name="lecturaubicapic" ng-model="lecturaubicapic"
							id="lecturaubicapic"
							ng-keyup="$event.keyCode == 13 && buscaritemdocpic();">
					</div>
					<div class=" pt-1">
						<label for="lecturaitempi"><b>Item:</b></label> <input
							name="lecturaitempi" ng-model="lecturaitempi" id="lecturaitempi"
							ng-keyup="$event.keyCode == 13 && buscaritemdocpic();">
					</div>
				</form>

				<div class="ui-grid-solo ui-reponsive ">
					<div class="ui-body ui-body-d">
						<table data-role="table" id="tablecumplido"
							class="ui-responsive table-stroke" data-mode="reflow" border="1">
							<thead>
								<tr>
									<th>Id</th>
									<th>Embalaje</th>
									<th>Item</th>
									<th>Descripción</th>
									<th>Cantidad</th>
									<th>Bodega</th>
									<th>Ubicacion</th>
									<th>Unidad</th>
									<th>Cumplido</th>
								</tr>
							</thead>

							<tbody>
								<tr ng-repeat="value in ar_itemorganizacum">
									<td>{{value.id}}</td>
									<td>{{value.embala}}</td>
									<td>{{value.item}}</td>
									<td>{{value.estante}}</td>
									<td>{{value.cant}}</td>
									<td>{{value.canasta}}</td>
									<td>{{value.ubica}}</td>
									<td>{{value.unidad}}</td>
									<td><a ng-if="value.cumplido === '0'" href=""
										class="reddot" data-role="button" data-icon="delete"
										data-theme="b">NO</a> <a ng-if="value.cumplido === '1'"
										href="" class="greendot" data-role="button" data-icon="check"
										data-theme="b">SI</a></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>


			</div>
		</div>
		<!-- /content -->
	</div>
	<!-- /page -->


	<div data-role="page" id="pickingedita">
		<div data-role="header">
			<h1>Editar Picking</h1>
		</div>
		<!-- /header -->

		<div role="main" class="ui-content"
			style="background-color: white !important;">
			<div class="ui-grid-solo right">
				<a href="#inicio"
					class="ui-btn ui-shadow ui-corner-all ui-btn-inline ui-mini ui-icon-back ui-btn-icon-left"
					style="background-color: white !important;">Regresar</a>
			</div>

			<div class="ui-grid-solo ui-reponsive">
				<div class="ui-body ui-body-d">


					<table data-role="table" data-mode="reflow"
						class="ui-responsive table-stroke" id="tbitempedido" border="1">
						<thead>
							<tr>
								<th data-priority="2">Ítem</th>
								<th data-priority="4">Descripción</th>
								<th data-priority="5">Referencia</th>
								<th data-priority="1">Cantidad Pendiente</th>
								<th data-priority="3">Cantidad Remisionada</th>
								<th data-priority="6">Unidad</th>
								<th data-priority="7">Peso Unitario</th>
								<th data-priority="8">Buscar</th>
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="value in ar_infopedido">
								<th>{{value.item}}</th>
								<td>{{value.descripcion}}</td>
								<td>{{value.valor1}}</td>
								<td>{{value.valor2}}</td>
								<td>{{value.valor3}}</td>
								<td>{{value.valor4}}</td>
								<td>{{value.valor5}}</td>
								<td>
									<form>
										<input type="submit"
											class="ui-btn ui-shadow ui-corner-all ui-btn-a ui-mini"
											value="Buscar"
											ng-click="buscaitem(value.item, value.descripcion, value.valor2, value.valor4, value.valor5);">
									</form>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>

			<div data-role="header">
				<h1>Ubicación del Item</h1>
			</div>
			<!-- /header -->

			<div class="ui-grid-solo ui-reponsive">
				<div class="ui-body ui-body-d">
					<table data-role="table" data-mode="reflow"
						class="ui-responsive table-stroke" id="tbubicaitempedido"
						border="1">
						<thead>
							<tr>
								<th data-priority="2">Ítem</th>
								<th data-priority="4">Referencia</th>
								<th data-priority="1">Cantidad Disponible</th>
								<th data-priority="3">Bodega</th>
								<th data-priority="5">Ubicación</th>
								<th data-priority="6">Seleccionar</th>
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="value in ar_itembodega">
								<th>{{value.item}}</th>
								<td>{{value.descripcion}}</td>
								<td>{{value.valor1}}</td>
								<td>{{value.valor2}}</td>
								<td>{{value.valor3}}</td>
								<td>
									<form>
										<input type="submit"
											class="ui-btn ui-shadow ui-corner-all ui-btn-a ui-mini"
											value="Iniciar"
											ng-click="iniciaorganiza(value.item, value.descripcion, value.valor1, value.valor3, value.valor2);">
									</form>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>


			<div data-role="collapsible" data-collapsed="false">
				<div data-role="header">
					<h1>Seleccionar Unidad de Embalaje</h1>
				</div>
				<!-- /header -->

				<fieldset class="ui-grid-a">
					<div class="ui-block-a">
						<label for="itembala">Número:</label> <input type="number"
							id="itembala" ng-model="itembala" name="itembala" disabled="true">
					</div>
					<div class="ui-block-b">
						<label for="selun_embala">Unidad de Embalaje:</label> <select
							name="selun_embala" id="selun_embala" required
							ng-model="selun_embala" ng-change="sel_embala();">
							<option value="" disabled="disabled" selected>Seleccionar</option>
							<option ng-value="org.id" ng-repeat="org in ar_unembala">{{org.nombre}}</option>
						</select>
					</div>
					<div class="ui-block-c">
						<input type="submit" value="Nuevo" name="btncreaembala"
							id="btncreaembala" ng-click="creaembala();">  
					</div>
					<div class="ui-block-d">
						<label for="actuni_emb">Seleccionada:</label> <input type="text"
							id="actuni_emb" ng-model="actuni_emb" name="actuni_emb"
							class="grey-text" disabled="true" style="font-weight: bold;">
					</div>

				</fieldset>

			</div>

			<div data-role="collapsible">
				<h4>Items para editar</h4>
				<div>
					<form name="frmsetop" id="frmsetop" method="get">
						<fieldset class="ui-grid-a">
							<div class="ui-block-a">
								<label for="numord">Item:</label> <input type="text" id="numord"
									ng-model="numord" name="numord" class="grey-text"
									disabled="true" style="font-weight: bold;">
							</div>
							<div class="ui-block-b">
								<label for="itdesc">Descripcion:</label> <input type="text"
									id="itdesc" ng-model="itdesc" name="itdesc" class="grey-text"
									disabled="true" style="font-weight: bold;">
							</div>
							<div class="ui-block-c">
								<label for="numcanasta">Ubicación</label> <input type="text"
									id="numcanasta" ng-model="numcanasta" name="numcanasta"
									class="grey-text" disabled="true" style="font-weight: bold;">
							</div>
							<div class="ui-block-a">
								<label for="ordCant">Cantidad:</label> <input type="number"
									id="ordCant" ng-model="ordCant" name="ordCant"
									class="grey-text" style="font-weight: bold;"
									ng-change="cambiacantorganiza();"
									ng-click="cambiacantorganiza();"
									ng-disabled="habilitanumcant();">
							</div>
							<div class="ui-block-b">
								<label for="numunidad">Unidad:</label> <input type="text"
									id="numunidad" ng-model="numunidad" name="numunidad"
									class="grey-text" disabled="true" style="font-weight: bold;">
							</div>
							<div class="ui-block-c">
								<label for="itpeso">Peso:</label> <input type="text" id="itpeso"
									ng-model="itpeso" name="itpeso" class="grey-text"
									disabled="true" style="font-weight: bold;">
							</div>


						</fieldset>
						<div class="ui-grid-solo">
							<div class="ui-block-a">
								<button id="btnasignaestante" name="btnasignaestante"
									class="waves-effect  btn blue-grey darken-3"
									ng-click="asignaestante();"
									ng-disabled="habilitaasignaestante();">Adicionar</button>
							</div>
						</div>
					</form>
				</div>


				<div>
					<div class="row">
						<div class="col s12 mt-2">
							<table id="tblasignada" datatable="ng"
								dt-options="showCase.dtOptions"
								class="row-border hover responsive-table" cellspacing="0"
								style="width: 100%; font-size: 80%;" sortable="false">
								<thead>
									<tr>
										<th class="center">Id</th>
										<th class="center">Embalaje</th>
										<th class="center" ng-show="false">EmbalajeId</th>
										<th class="center">Item</th>
										<th class="center">Descripción</th>
										<th class="center">Cantidad</th>
										<th class="center">Bodega</th>
										<th class="center">Ubicacion</th>
										<th class="center">Unidad</th>
										<th class="center">Peso Unitario</th>
										<th class="center">Peso Total</th>
										<th class="center">Eliminar</th>
									</tr>
								</thead>

								<tbody>
									<tr ng-repeat="value in ar_itemorganiza">
										<td class="center" ng-if="$odd"
											style="background-color: #d7f5fa">{{value.id}}</td>
										<td class="center" ng-if="$even">{{value.id}}</td>
										<td class="center" ng-if="$odd"
											style="background-color: #d7f5fa">{{value.embala}}</td>
										<td class="center" ng-if="$even">{{value.embala}}</td>
										<td class="center" ng-if="$odd"
											style="background-color: #d7f5fa" ng-show="false">{{value.emidtab}}</td>
										<td class="center" ng-if="$even" ng-show="false">{{value.emidtab}}</td>
										<td class="center" ng-if="$odd"
											style="background-color: #d7f5fa">{{value.item}}</td>
										<td class="center" ng-if="$even">{{value.item}}</td>
										<td class="center" ng-if="$odd"
											style="background-color: #d7f5fa">{{value.estante}}</td>
										<td class="center" ng-if="$even">{{value.estante}}</td>
										<td class="center" ng-if="$odd"
											style="background-color: #d7f5fa">{{value.cant}}</td>
										<td class="center" ng-if="$even">{{value.cant}}</td>
										<td class="center" ng-if="$odd"
											style="background-color: #d7f5fa">{{value.canasta}}</td>
										<td class="center" ng-if="$even">{{value.canasta}}</td>
										<td class="center" ng-if="$odd"
											style="background-color: #d7f5fa">{{value.ubica}}</td>
										<td class="center" ng-if="$even">{{value.ubica}}</td>
										<td class="center" ng-if="$odd"
											style="background-color: #d7f5fa">{{value.unidad}}</td>
										<td class="center" ng-if="$even">{{value.unidad}}</td>
										<td class="center" ng-if="$odd"
											style="background-color: #d7f5fa">{{value.peso}}</td>
										<td class="center" ng-if="$even">{{value.peso}}</td>
										<td class="center" ng-if="$odd"
											style="background-color: #d7f5fa">{{value.pesotot}}</td>
										<td class="center" ng-if="$even">{{value.pesotot}}</td>
										<td class="center" ng-if="$odd"
											style="background-color: #d7f5fa">
											<button
												class="btn-floating waves-effect waves-light green darken-3 tooltipped"
												data-position="top" data-delay="1"
												ng-click="removeitrow($index);" data-tooltip="Borrar Piezas">
												<i class="material-icons">Borrar</i>
											</button>
										</td>
										<td class="center" ng-if="$even">
											<button
												class="btn-floating waves-effect waves-light green darken-3 tooltipped"
												data-position="top" data-delay="1"
												ng-click="removeitrow($index);" data-tooltip="Borrar Piezas">Borrar
											</button>
										</td>
									</tr>
								</tbody>
							</table>
						</div>

						<div class="input-field col m3 s12">
							<input type="number" id="pesodoc" ng-model="pesodoc"
								name="pesodoc" class="grey-text" style="font-weight: bold;"
								ng-disabled="true"> <label for="pesodoc">Peso
								del picking:</label>
						</div>
						<div class="input-field col m4 s12 right-align mt-2">
							<button id="btnguardaedita"
								class="waves-effect  btn blue-grey darken-3"
								ng-click="guardadocedita();">Actualizar</button>
						</div>
					</div>
				</div>
			</div>

		</div>
		<!-- /content -->
	</div>
	<!-- /page -->

</body>
</html>