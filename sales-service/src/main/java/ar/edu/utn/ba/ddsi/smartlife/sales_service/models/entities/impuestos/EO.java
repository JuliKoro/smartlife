package ar.edu.utn.ba.ddsi.smartlife.sales_service.models.entities.impuestos;

import ar.edu.utn.ba.ddsi.smartlife.sales_service.models.entities.productos.Producto;
import lombok.Getter;
import lombok.Setter;

// implementa la interfaz de Impuesto
public class EO implements Impuesto {

	// se pone static, porque son variables de clase, se pueden utilizar sin instanciar la clase
	// si cambio dicho valor, cambia globalmente para todas las instancias que hayan
	@Getter
	@Setter
	private static double factorPrecioBase = 0.5;

	@Getter
	@Setter
	private static double coeficienteGanancias = 4;

	@Getter
	@Setter
	private static double gananciasImpositivas = 4;

	@Override
	public double calcular(Producto producto) {
		return EO.factorPrecioBase * producto.getPrecioBase()
				+ EO.coeficienteGanancias * EO.gananciasImpositivas;
	}
}
