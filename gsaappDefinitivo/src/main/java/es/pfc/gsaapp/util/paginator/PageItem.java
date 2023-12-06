package es.pfc.gsaapp.util.paginator;

public class PageItem {

	private int numero;
	
	private boolean actual;

	public PageItem(Integer numero, Boolean actual) {
		super();
		this.numero = numero;
		this.actual = actual;
	}

	public int getNumero() {
		return numero;
	}

	public boolean getActual() {
		return actual;
	}
}