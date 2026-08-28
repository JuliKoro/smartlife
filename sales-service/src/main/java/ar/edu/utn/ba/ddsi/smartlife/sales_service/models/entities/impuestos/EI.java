package ar.edu.utn.ba.ddsi.smartlife.sales_service.models.entities.impuestos;

import ar.edu.utn.ba.ddsi.smartlife.sales_service.models.entities.productos.Producto;
import lombok.Getter;
import lombok.Setter;

// implementa la interfaz de Impuesto
public class EI implements Impuesto {

	// se pone static, porque son variables de clase, se pueden utilizar sin instanciar la clase
	// si cambio dicho valor, cambia globalmente para todas las instancias que hayan
	@Getter
	@Setter
	private static double divisorPrecioBase = 4;

	@Getter
	@Setter
	private static double factorGanancias = 0.3;

	@Getter
	@Setter
	private static double gananciasImpositivas = 3.5;

	@Override
	public double calcular(Producto producto) {
		return producto.getPrecioBase() / EI.divisorPrecioBase
				+ EI.factorGanancias * EI.gananciasImpositivas;
	}
}
