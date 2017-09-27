

//metodo de busqueda de los ambientes
var buscarCatalogoAmbientes = function (seleccionado){
	var activo = seleccionado === 'Activos' ? true :false;
	execHttpSeguridadIntegral(
			{type: "GET",dataType: "json",url: "rest/tipo-ambiente/buscar-ambientes?activo="+activo},
			function(data,jqXHR){
				 tipoAmbienteView.arrayTipoAmbiente=data;
			});	
}
//para agregar un nuevo ambiente
var agregarAmbiente = function(nuevoAmbiente,filtroBusqueda){
	execHttpSeguridadIntegral(
			{type: "POST",dataType: "json",contentType: 'application/json',data:JSON.stringify(nuevoAmbiente),url: "rest/tipo-ambiente/agregar-ambiente"},
			function(data,jqXHR){
				 bootbox.alert("Se guardo el ambiente exitosamente")
				 buscarCatalogoAmbientes(filtroBusqueda)
			});	
}

//variable con el binding de Vue
var tipoAmbienteView = new Vue({
	  el: '#tipo-ambiente',
	  data: {
		  	arrayTipoAmbiente:[],
		  	arrayBuscarAmbiente:['Activos','Inactivos'],
		  	tipoAmbienteSeleccionado:'Activos',
		  	nuevoAmbiente:{clave:'',nombre:'',descripcion:''}
	  },
	  methods: {
		    greet: function (event) {
		      // `this` inside methods point to the Vue instance
		      alert('Hello ' + this.name + '!')
		      // `event` is the native DOM event
		      alert(event.target.tagName)
		    },
		    buscarAmbientes:buscarCatalogoAmbientes
		    ,agregarAmbiente:agregarAmbiente
	  }
})

//inicio del modulo
//inicio del modulo
$(function() {
	buscarCatalogoAmbientes(tipoAmbienteView.tipoAmbienteSeleccionado)
})