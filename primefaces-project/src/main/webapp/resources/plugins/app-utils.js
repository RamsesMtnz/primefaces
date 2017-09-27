///dialoguito de progreso
var dialogoProgreso = dialogoProgreso || (function () {
	var pleaseWaitDiv =	$('<div class="modal " id="procesando-accion-dialog">'+
									'<div class="modal-dialog">'+
										'<div class="modal-content">'+
										      '<div class="modal-header">'+
										        '<h4 class="modal-title">Procesando</h4>'+
										      '</div>'+
										      '<div class="modal-body">'+
										        '<div class="progress">'+
												  '<div class="progress-bar progress-bar-striped active" role="progressbar"  style="width: 100%">'+
												   '</div>'+
												'</div>'+
										      '</div>'+
										'</div>'+
									'</div>'+
							'</div>');
  
    return {
        mostrar: function() {
            pleaseWaitDiv.modal({backdrop: 'static', keyboard: false});
        },
        esconder: function () {
            pleaseWaitDiv.modal('hide');
        },

    };
})();

//para los mensjaes de horro y error ja 
var dialogoMensaje = dialogoMensaje || (function () {
	var pleaseWaitDiv =	$('<div class="modal " id="procesando-accion-dialog">'+
									'<div class="modal-dialog">'+
										'<div class="modal-content">'+
										      '<div class="modal-header">'+
										        '<h4 class="modal-title">Procesando</h4>'+
										      '</div>'+
										      '<div class="modal-body">'+
										        '<div class="progress">'+
												  '<div class="progress-bar progress-bar-striped active" role="progressbar"  style="width: 100%">'+
												   '</div>'+
												'</div>'+
										      '</div>'+
										'</div>'+
									'</div>'+
							'</div>');
  
    return {
        mostrar: function() {
            pleaseWaitDiv.modal({backdrop: 'static', keyboard: false});
        },
        esconder: function () {
            pleaseWaitDiv.modal('hide');
        },

    };
})();

/***** Peticiones http :P  *****/

//FALTA MEJORARLE Y CONTEMPLAR QUE HACER CON LOS CALLBACK Y LOS STATUS Y ERRORES DE BUSQUEDA
var execHttpSeguridadIntegral = function (dataRequest,callback){
	dataRequest.url = "/seguridad-integral-web/"+dataRequest.url;
	dialogoProgreso.mostrar();
	$.ajax(dataRequest)
		 .done(function( data, textStatus, jqXHR ) {
			if(callback != undefined){
				callback(data,jqXHR)
			}
				//errroe de negocio
			if(jqXHR.status === 204){
					bootbox.alert('No se encontraron resultados')
			}else if(jqXHR.status === 202){
			  bootbox.alert(data.mensaje)
			}
			
			dialogoProgreso.esconder();
		 })
		.fail(function( jqXHR, textStatus, errorThrown ) {
			console.log(jqXHR);
			bootbox.alert('Ocurrio un error no esperado notifiquelo al administrador')
			dialogoProgreso.esconder();
		});
}