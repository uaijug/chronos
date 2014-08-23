package br.com.uaijug.chronos.model.types;

public enum MiniCurse {

	/** The YES. */
	Palestras("PALESTRAS"), 
	
	/** The YES. */
	UsandoNOSQLCassandracomJEE6("Usando NOSQL-Cassandra com JEE 6 "), 
	
	DesenvolvendoAplicoesAndroidcomLinguagensdeScript(
			"Desenvolvendo Aplicacoes Android com Linguagens de Script "),
	/** The NO. */
	CriandoumaAppCompletacomExtJS4PHPeMySQL(
			"Criando uma App Completa com ExtJS 4, PHP e MySQL");

	/** The status. */
	String curse;

	/**
	 * Instantiates a new status.
	 * 
	 * @param status
	 *            the status
	 */
	private MiniCurse(String curse) {
		this.curse = curse;
	}

	public String getCurse() {
		return curse;
	}

	public void setCurse(String curse) {
		this.curse = curse;
	}

}
