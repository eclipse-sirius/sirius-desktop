/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ArmEvent;
import org.eclipse.swt.events.ArmListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.PlatformUI;

/**
 * A listener which listens when a menu item is armed to display a tooltip.
 * 
 * @author mchauvin
 */
public class MenuContributionItemArmListener implements ArmListener {

    private Control control;

    private Shell currentTooltip;

    /**
     * Create a new instance.
     * 
     * @param control
     *            the control
     */
    public MenuContributionItemArmListener(Control control) {
        this.control = control;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.swt.events.ArmListener#widgetArmed(org.eclipse.swt.events.ArmEvent)
     */
    public void widgetArmed(final ArmEvent e) {

        Widget widget = e.widget;
        if (widget instanceof MenuItem) {
            Object data = ((MenuItem) widget).getData(AbstractMenuContributionItem.TOOLTIP);
            if (data instanceof String) {
                createTooltip((String) data);
            }
        }
    }

    private void createTooltip(String tooltipText) {

        if (currentTooltip != null && !currentTooltip.isDisposed()) {
            currentTooltip.dispose();
        }
        Label label;
        final Shell tip = new Shell(control.getShell(), SWT.ON_TOP | SWT.TOOL);
        tip.setLayout(new FillLayout());
        label = new Label(tip, SWT.NONE);

        Display display = PlatformUI.getWorkbench().getDisplay();

        label.setForeground(display.getSystemColor(SWT.COLOR_INFO_FOREGROUND));
        label.setBackground(display.getSystemColor(SWT.COLOR_INFO_BACKGROUND));
        label.setText(tooltipText);

        Point size = tip.computeSize(SWT.DEFAULT, SWT.DEFAULT);
        Point pt = PlatformUI.getWorkbench().getDisplay().getCursorLocation();
        int offset = 15;
        tip.setBounds(pt.x + offset, pt.y - offset, size.x, size.y);
        tip.setVisible(true);
        control.getDisplay().timerExec(3000, new Runnable() {
            public void run() {
                tip.dispose();
            }
        });
        currentTooltip = tip;
    }
}
