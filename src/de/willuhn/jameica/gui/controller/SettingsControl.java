/**********************************************************************
 * $Source: /cvsroot/jameica/jameica/src/de/willuhn/jameica/gui/controller/Attic/SettingsControl.java,v $
 * $Revision: 1.16 $
 * $Date: 2004/04/19 22:05:27 $
 * $Author: willuhn $
 * $Locker:  $
 * $State: Exp $
 *
 * Copyright (c) by willuhn.webdesign
 * All rights reserved
 *
 **********************************************************************/

package de.willuhn.jameica.gui.controller;

import de.willuhn.jameica.Application;
import de.willuhn.jameica.Config;
import de.willuhn.jameica.gui.GUI;
import de.willuhn.jameica.gui.dialogs.YesNoDialog;
import de.willuhn.jameica.gui.input.AbstractInput;
import de.willuhn.jameica.gui.input.FileInput;
import de.willuhn.jameica.gui.input.SelectInput;
import de.willuhn.jameica.gui.parts.TablePart;
import de.willuhn.jameica.gui.views.AbstractView;
import de.willuhn.jameica.gui.views.Settings;
import de.willuhn.jameica.gui.views.Start;
import de.willuhn.util.I18N;
import de.willuhn.util.Logger;

/**
 * 
 */
public class SettingsControl extends AbstractControl
{

	private Config config = Application.getConfig();
	
	private AbstractInput logFile  = new FileInput(config.getLogFile());
	private AbstractInput logLevel = new SelectInput(Logger.LEVEL_TEXT,config.getLogLevel());

	private I18N i18n;

  /**
   * ct.
   * @param view
   */
  public SettingsControl(AbstractView view)
  {
    super(view);
    i18n = Application.getI18n();
  }


	/**
	 * Liefert das Eingabe-Feld fuer das Logfile.
   * @return Eingabe-Feld fuer das Logfile.
   */
  public AbstractInput getLogFile()
	{
		return logFile;
	}

	/**
	 * Liefert das Eingabefeld fuer das Loglevel.
   * @return Eingabe-Feld fuer das Loglevel.
   */
  public AbstractInput getLoglevel()
	{
		return logLevel;
	}

	/**
	 * Liefert eine Tabelle mit den lokalen Services.
   * @return Tabelle mit den lokalen Services.
   */
  public TablePart getLocalServices()
	{
		TablePart t = new TablePart(config.getLocalServiceData(),this);
		t.addColumn(i18n.tr("Name"),null);
		return t;
	}

	/**
	 * Liefert eine Tabelle mit den remote Services.
	 * @return Tabelle mit den remote Services.
	 */
	public TablePart getRemoteServices()
	{
		TablePart t = new TablePart(config.getRemoteServiceData(),this);
		t.addColumn(i18n.tr("Name"),null);
		return t;
	}

  /**
   * @see de.willuhn.jameica.gui.controller.AbstractControl#handleDelete()
   */
  public void handleDelete()
  {
  }

  /**
   * @see de.willuhn.jameica.gui.controller.AbstractControl#handleCancel()
   */
  public void handleCancel()
  {
  	GUI.startView(Start.class.getName(),null);
  }

  /**
   * @see de.willuhn.jameica.gui.controller.AbstractControl#handleStore()
   */
  public void handleStore()
  {
  	Config config = Application.getConfig();

  	config.setLoglevel((String)logLevel.getValue());
  	Application.getLog().setLevel(config.getLogLevel()); // live umschaltung
  	config.setLogFile((String)logFile.getValue());

  	try
    {
      config.store();
			GUI.getStatusBar().setSuccessText(i18n.tr("Konfiguaration gespeichert."));
    }
    catch (Exception e)
    {
    	Application.getLog().error("error while writing config",e);
			GUI.getStatusBar().setErrorText(i18n.tr("Fehler beim Speichern der Konfiguration."));
    }
  	
  }

  /**
   * @see de.willuhn.jameica.gui.controller.AbstractControl#handleCreate()
   */
  public void handleCreate()
  {
  }
  
  /**
   * @see de.willuhn.jameica.gui.controller.AbstractControl#handleOpen(java.lang.Object)
   */
  public void handleOpen(Object o)
  {
  }
  
  /**
   * Setzt die Einstellungen zurueck.
   */
  public void handleRestore()
  {
  	try {
  		YesNoDialog prompt = new YesNoDialog(YesNoDialog.POSITION_CENTER);
  		prompt.setTitle(i18n.tr("Sicher?"));
  		prompt.setText(i18n.tr("Alle Einstellungen werden auf die zuvor gespeicherten Werte zur�ckgesetzt"));
  		if (!((Boolean) prompt.open()).booleanValue())
  			return;
			Application.getConfig().restore();
			GUI.startView(Settings.class.getName(),null);
			GUI.getStatusBar().setSuccessText(i18n.tr("letzte gespeicherte Konfiguaration wieder hergestellt."));
  	}
  	catch (Exception e)
  	{
  		Application.getLog().error("error while restoreing config",e);
			GUI.getStatusBar().setErrorText(i18n.tr("Fehler beim Wiederherstellen der Konfiguration"));
  	}
  	
  }

}


/**********************************************************************
 * $Log: SettingsControl.java,v $
 * Revision 1.16  2004/04/19 22:05:27  willuhn
 * *** empty log message ***
 *
 * Revision 1.15  2004/04/12 19:16:00  willuhn
 * @C refactoring
 * @N forms
 *
 * Revision 1.14  2004/04/01 00:23:24  willuhn
 * @N FontInput
 * @N ColorInput
 * @C improved ClassLoader
 * @N Tabs in Settings
 *
 * Revision 1.13  2004/03/30 22:08:26  willuhn
 * *** empty log message ***
 *
 * Revision 1.12  2004/03/24 00:46:03  willuhn
 * @C refactoring
 *
 * Revision 1.11  2004/03/11 08:56:56  willuhn
 * @C some refactoring
 *
 * Revision 1.10  2004/03/06 18:24:24  willuhn
 * @D javadoc
 *
 * Revision 1.9  2004/03/03 22:27:11  willuhn
 * @N help texts
 * @C refactoring
 *
 * Revision 1.8  2004/02/24 22:46:53  willuhn
 * @N GUI refactoring
 *
 * Revision 1.7  2004/02/11 00:10:42  willuhn
 * *** empty log message ***
 *
 * Revision 1.6  2004/01/28 20:51:25  willuhn
 * @C gui.views.parts moved to gui.parts
 * @C gui.views.util moved to gui.util
 *
 * Revision 1.5  2004/01/23 00:29:04  willuhn
 * *** empty log message ***
 *
 * Revision 1.4  2004/01/08 20:50:33  willuhn
 * @N database stuff separated from jameica
 *
 * Revision 1.3  2004/01/06 20:11:22  willuhn
 * *** empty log message ***
 *
 * Revision 1.2  2004/01/06 01:27:30  willuhn
 * @N table order
 *
 * Revision 1.1  2004/01/04 19:51:01  willuhn
 * *** empty log message ***
 *
 **********************************************************************/