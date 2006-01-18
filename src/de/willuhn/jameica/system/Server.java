/**********************************************************************
 * $Source: /cvsroot/jameica/jameica/src/de/willuhn/jameica/system/Server.java,v $
 * $Revision: 1.11 $
 * $Date: 2006/01/18 18:40:21 $
 * $Author: web0 $
 * $Locker:  $
 * $State: Exp $
 *
 * Copyright (c) by willuhn.webdesign
 * All rights reserved
 *
 **********************************************************************/
package de.willuhn.jameica.system;

import de.willuhn.logging.Logger;
import de.willuhn.util.ApplicationException;
import de.willuhn.util.ProgressMonitor;

/**
 * Diese Klasse bildet den Serverloop der Anwendung ab.
 * @author willuhn
 */
public class Server implements ApplicationController
{
  private ApplicationCallback callback = null;
  
  /**
   * @see de.willuhn.jameica.system.ApplicationController#init()
   */
  public void init() throws ApplicationException
  {
    Logger.info(Application.getI18n().tr("jameica up and running..."));

		String[] welcome = Application.getWelcomeMessages();
		if (welcome != null && welcome.length > 0)
		{
      try
      {
        Logger.flush();
      }
      catch (InterruptedException e)
      {
        // ignore
      }
      Logger.info("----------------------------------------------------------------------");
      Logger.info(Application.getI18n().tr("Startup-Messages:"));
			for (int i=0;i<welcome.length;++i)
			{
        Logger.info("  " + welcome[i]); 
			}
		}
    Logger.info("----------------------------------------------------------------------");
    Logger.info(Application.getI18n().tr("press \"<CTRL><C>\" to shut down the server."));
  }

  /**
   * @see de.willuhn.jameica.system.ApplicationController#shutDown()
   */
  public void shutDown()
  {
  }

  /**
   * @see de.willuhn.jameica.system.ApplicationController#getApplicationCallback()
   */
  public ApplicationCallback getApplicationCallback()
  {
    if (callback == null)
      callback = new ApplicationCallbackConsole();
    return callback;
  }

  /**
   * @see de.willuhn.jameica.system.ApplicationController#start(de.willuhn.jameica.system.BackgroundTask)
   */
  public void start(final BackgroundTask task)
  {
    Thread t = new Thread("[Jameica Backgroundtask] " + task.getClass().getName())
    {
      public void run()
      {
        ProgressMonitor monitor = new ConsoleMonitor();
        try
        {
          task.run(monitor);
        }
        catch (OperationCanceledException oce)
        {
          if (monitor != null) monitor.setStatus(ProgressMonitor.STATUS_CANCEL);
        }
        catch (Throwable t)
        {
          Logger.error("error while executing background task",t);
          if (monitor != null) monitor.setStatus(ProgressMonitor.STATUS_ERROR);
        }
        finally
        {
          if (monitor != null) monitor.setStatus(ProgressMonitor.STATUS_DONE);
        }
      }
    };
    t.start();
  }

  /**
   * @author willuhn
   */
  private class ConsoleMonitor implements ProgressMonitor
  {
    /**
     * @see de.willuhn.util.ProgressMonitor#setPercentComplete(int)
     */
    public void setPercentComplete(int percent)
    {
      Logger.debug("startup completed: " + percent + " %");
    }
    
    /**
     * @see de.willuhn.util.ProgressMonitor#addPercentComplete(int)
     */
    public void addPercentComplete(int percent)
    {
      if (percent < 1)
        return;
      setPercentComplete(getPercentComplete() + percent);
    }

    /**
     * @see de.willuhn.util.ProgressMonitor#getPercentComplete()
     */
    public int getPercentComplete() {return 0;}

    /**
     * @see de.willuhn.util.ProgressMonitor#setStatus(int)
     */
    public void setStatus(int status) {}
    
    /**
     * @see de.willuhn.util.ProgressMonitor#setStatusText(java.lang.String)
     */
    public void setStatusText(String text) {
      Logger.info(text);
    }
    
    /**
     * @see de.willuhn.util.ProgressMonitor#log(java.lang.String)
     */
    public void log(String msg) {
      Logger.info(msg);
    }
  };
  
}

/*********************************************************************
 * $Log: Server.java,v $
 * Revision 1.11  2006/01/18 18:40:21  web0
 * @N Redesign des Background-Task-Handlings
 *
 * Revision 1.10  2005/07/14 22:58:36  web0
 * *** empty log message ***
 *
 * Revision 1.9  2005/06/28 21:13:41  web0
 * *** empty log message ***
 *
 * Revision 1.8  2005/04/21 17:14:14  web0
 * @B fixed shutdown behaviour
 *
 * Revision 1.7  2005/01/30 20:47:43  willuhn
 * *** empty log message ***
 *
 * Revision 1.6  2005/01/12 00:59:38  willuhn
 * *** empty log message ***
 *
 * Revision 1.5  2005/01/03 23:04:54  willuhn
 * @N separater StartupError Handler
 *
 * Revision 1.4  2004/11/12 18:23:58  willuhn
 * *** empty log message ***
 *
 * Revision 1.3  2004/08/11 00:39:25  willuhn
 * *** empty log message ***
 *
 * Revision 1.2  2004/07/27 19:17:07  willuhn
 * *** empty log message ***
 *
 * Revision 1.1  2004/07/21 20:08:45  willuhn
 * @C massive Refactoring ;)
 *
 * Revision 1.2  2004/06/30 20:58:39  willuhn
 * *** empty log message ***
 *
 * Revision 1.1  2003/12/12 01:28:05  willuhn
 * *** empty log message ***
 *
 **********************************************************************/