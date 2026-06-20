package sistemaDeGestionAcademica;

public class CronogramaGeneral {
	private String[][] asignaciones;

	public CronogramaGeneral() {
		this.asignaciones = new String[6][3];
	}

	public boolean asignarComision(int dia, int turno, String nombreComision) {
		boolean yaAsignado = false;
		if (dia < 0 || dia > 5) {
			throw new Error("dia erroneo");
		}
		if (turno < 0 || turno > 2) {
			throw new Error("turno invalido");
		}
		if (dia == 5 && turno != 0) {
			throw new Error("sabado solo puede ser a la mañana");
		}
		if (nombreComision == null || nombreComision.equals("")) {
			throw new Error("nombre invalido");
		}
		if (asignaciones[dia][turno] != null) {
			yaAsignado = false;
		} else {
			this.asignaciones[dia][turno] = nombreComision;
			yaAsignado = true;
		}
		return yaAsignado;
	}

	public boolean liberarHorario(int dia, int turno) {

		boolean liberado = false;

		if (dia < 0 || dia > 5) {
			throw new Error("dia erroneo");
		}

		if (turno < 0 || turno > 2) {
			throw new Error("turno invalido");
		}

		if (dia == 5 && turno != 0) {
			throw new Error("sabado solo puede ser a la mañana");
		}

		if (this.asignaciones[dia][turno] == null) {
			liberado = false;
		} else {
			this.asignaciones[dia][turno] = null;
			liberado = true;
		}

		return liberado;
	}

	public String consultarHorario(int dia, int turno) {
		if (dia < 0 || dia > 5) {
			throw new Error("dia erroneo");
		}

		if (turno < 0 || turno > 2) {
			throw new Error("turno invalido");
		}

		return this.asignaciones[dia][turno];

	}

	@Override
	public String toString() {
		String[] dias = { "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado" };
		String[] turnos = { "Mañana", "Tarde", "Noche" };

		String resultado = "";

		for (int i = 0; i < this.asignaciones.length; i++) {
			resultado += dias[i] + "\n";

			for (int j = 0; j < this.asignaciones[i].length; j++) {
				resultado += "  " + turnos[j] + ": ";

				if (this.asignaciones[i][j] == null) {
					resultado += "Libre";
				} else {
					resultado += this.asignaciones[i][j];
				}

				resultado += "\n";
			}

			resultado += "\n";
		}

		return resultado;
	}

	public int[] buscarComision(String nombreComision) {
		boolean encontrado = false;
		int[] posicion = null;

		if (nombreComision == null || nombreComision.equals("")) {
			throw new Error("nombre invalido");
		}

		for (int i = 0; i < this.asignaciones.length && !encontrado; i++) {
			for (int j = 0; j < this.asignaciones[i].length && !encontrado; j++) {
				if (this.asignaciones[i][j] != null && this.asignaciones[i][j].equalsIgnoreCase(nombreComision)) {

					posicion = new int[2];
					posicion[0] = i;
					posicion[1] = j;
					encontrado = true;
				}
			}
		}

		return posicion;
	}
}
