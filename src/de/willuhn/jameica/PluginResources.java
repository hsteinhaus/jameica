/**********************************************************************
 * $Source: /cvsroot/jameica/jameica/src/de/willuhn/jameica/Attic/PluginResources.java,v $
 * $Revision: 1.4 $
 * $Date: 2004/03/29 23:20:49 $
 * $Author: willuhn $
 * $Locker:  $
 * $State: Exp $
 *
 * Copyright (c) by willuhn.webdesign
 * All rights reserved
 *
 **********************************************************************/
package de.willuhn.jameica;

import de.willuhn.datasource.db.EmbeddedDatabase;
import de.willuhn.util.I18N;

/**
 * Container, der zusaetzliche Informationen fuer das Plugin bereitstellt.
 */
public class PluginResources {

	private AbstractPlugin plugin = null;
	private EmbeddedDatabase db = null;
	private I18N i18n = null;

  /**
   * ct.
   * Das Plugin-File oder Verzeichnis.
   */
  protected PluginResources(AbstractPlugin plugin)
  {
  	this.plugin = plugin;
		this.i18n = new I18N(getPath() + "/lang/messages",
												 Application.getConfig().getLocale());
  }

	/**
	 * Liefert das Language-Pack fuer das Plugin.
   * @return Language-Pack.
   */
  public I18N getI18N()
	{
		return i18n;
	}

	/**
	 * Liefert das Verzeichnis, in dem sich das Plugin gefindet.
	 * @return Verzeichnis, in dem sich das Plugin befindet.
	 */
	public String getPath()
	{
		if (!plugin.getFile().getName().endsWith(".jar"))
			return plugin.getFile().getPath();

		return plugin.getFile().getParent();
	}

	/**
	 * Liefert die embedded Datenbank des Plugins. Damit ist keine JDBC-Verbindung
	 * oder ein DB-Hub gemeint, sondern ein Objekt, mit dem man das Plugin
	 * eine Datenbank fuer sich erstellen und mit Tabellen fuellen kann.
	 * TODO: Username und Passwort?
	 * @return die Embedded Datenbank des Plugins.
	 */
	public EmbeddedDatabase getDatabase()
	{
		if (db != null)
			return db;
		db = new EmbeddedDatabase(getPath() + "/db",plugin.getName(),plugin.getName());
		db.setLogger(Application.getLog());
		db.setClassLoader(Application.getClassLoader());
		return db;
	}

	/**
	 * Liefert den Usernamen fuer die Embedded-Datenbank.
	 * Sollte vom Plugin ueberschrieben werden, wenn die
	 * Datenbank benutzt wird.
	 * @return Username fuer die Datenbank.
	 */
	protected String getUsername()
	{
		return "jameica";
	}

	/**
	 * Liefert das Passwort fuer die Embedded-Datenbank.
	 * Sollte vom Plugin ueberschrieben werden, wenn die
	 * Datenbank benutzt wird.
	 * @return Passwort fuer die Datenbank.
	 */
	protected String getPassword()
	{
		return "jameica"; 
	}

}


/**********************************************************************
 * $Log: PluginResources.java,v $
 * Revision 1.4  2004/03/29 23:20:49  willuhn
 * *** empty log message ***
 *
 * Revision 1.3  2004/03/18 01:24:47  willuhn
 * @C refactoring
 *
 * Revision 1.2  2004/03/03 22:27:11  willuhn
 * @N help texts
 * @C refactoring
 *
 * Revision 1.1  2004/02/25 23:11:57  willuhn
 * *** empty log message ***
 *
 **********************************************************************/