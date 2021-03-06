/**********************************************************************
 * $Source: /cvsroot/jameica/jameica/src/de/willuhn/jameica/gui/style/StyleFactoryDefaultImpl.java,v $
 * $Revision: 1.16 $
 * $Date: 2011/05/03 17:04:44 $
 * $Author: willuhn $
 * $Locker:  $
 * $State: Exp $
 *
 * Copyright (c) by willuhn.webdesign
 * All rights reserved
 *
 **********************************************************************/
package de.willuhn.jameica.gui.style;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;

import de.willuhn.jameica.gui.util.Font;

/**
 * Default-Implementierung der Style-Factory.
 */
public class StyleFactoryDefaultImpl implements StyleFactory
{

  /**
   * @see de.willuhn.jameica.gui.style.StyleFactory#createButton(org.eclipse.swt.widgets.Composite)
   */
  public Button createButton(Composite parent)
  {
    Button button = new Button(parent,SWT.PUSH);
    button.setFont(Font.DEFAULT.getSWTFont());
    return button;
  }
  
  /**
   * @see de.willuhn.jameica.gui.style.StyleFactory#createCheckbox(org.eclipse.swt.widgets.Composite)
   */
  public Button createCheckbox(Composite parent)
  {
    Button check = new Button(parent,SWT.CHECK);
    check.setFont(Font.DEFAULT.getSWTFont());
    return check;
  }

  /**
   * @see de.willuhn.jameica.gui.style.StyleFactory#createText(org.eclipse.swt.widgets.Composite)
   */
  public Text createText(Composite parent)
  {
    Text text = new Text(parent,SWT.BORDER | SWT.SINGLE);
    text.setFont(Font.DEFAULT.getSWTFont());
//    text.setForeground(Color.FOREGROUND.getSWTColor());
//    text.setBackground(Color.BACKGROUND.getSWTColor());
    return text;
  }

  /**
   * @see de.willuhn.jameica.gui.style.StyleFactory#createTextArea(org.eclipse.swt.widgets.Composite)
   */
  public Text createTextArea(Composite parent)
  {
    Text text = new Text(parent,SWT.BORDER | SWT.MULTI | SWT.WRAP | SWT.V_SCROLL);
    text.setFont(Font.DEFAULT.getSWTFont());
//    text.setForeground(Color.FOREGROUND.getSWTColor());
//    text.setBackground(Color.BACKGROUND.getSWTColor());
    return text;
  }

  /**
   * @see de.willuhn.jameica.gui.style.StyleFactory#createCombo(org.eclipse.swt.widgets.Composite, int)
   */
  public Combo createCombo(Composite parent, int style)
  {
    Combo combo = new Combo(parent,SWT.BORDER | style);
    combo.setFont(Font.DEFAULT.getSWTFont());
    return combo;
  }

  /**
   * @see de.willuhn.jameica.gui.style.StyleFactory#createTable(org.eclipse.swt.widgets.Composite, int)
   */
  public Table createTable(Composite parent, int style)
  {
    Table t = new Table(parent, SWT.BORDER | style);
    t.setFont(Font.DEFAULT.getSWTFont());
    return t;
  }

  /**
   * @see de.willuhn.jameica.gui.style.StyleFactory#getName()
   */
  public String getName() {
    return "Default-Look";
  }

  /**
   * @see de.willuhn.jameica.gui.style.StyleFactory#createLabel(org.eclipse.swt.widgets.Composite, int)
   */
  public Label createLabel(Composite parent, int style)
  {
    Label label = new Label(parent,style);
    label.setFont(Font.DEFAULT.getSWTFont());
    return label;
  }

}
