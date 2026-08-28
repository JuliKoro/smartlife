package ar.edu.utn.ba.ddsi.smartlife.sales_service.models.entities.observers;

import ar.edu.utn.ba.ddsi.smartlife.sales_service.models.entities.venta.Venta;

// Patron Observer
public interface ObservadorVenta {

	void serNotificadoDe(Venta venta);

}
