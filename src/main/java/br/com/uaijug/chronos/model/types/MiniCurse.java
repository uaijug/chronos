/*
 * 
 */
package br.com.uaijug.chronos.model.types;

// TODO: Auto-generated Javadoc
/**
 * The Enum MiniCurse.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
public enum MiniCurse {

	/** The YES. */
	Palestras("PALESTRAS"), 
	
	/** The YES. */
	UsandoNOSQLCassandracomJEE6("Usando NOSQL-Cassandra com JEE 6 "), 
	
	/** The Desenvolvendo aplicoes androidcom linguagensde script. */
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
	 * @param curse the curse
	 */
	private MiniCurse(String curse) {
		this.curse = curse;
	}

	/**
	 * Gets the curse.
	 *
	 * @return the curse
	 */
	public String getCurse() {
		return curse;
	}

	/**
	 * Sets the curse.
	 *
	 * @param curse the new curse
	 */
	public void setCurse(String curse) {
		this.curse = curse;
	}

}
