/******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 *    Maxime Porhel (Obeo) <maxime.porhel@obeo.fr> - Trac bug #1502 : Print / PrintPreview freezes Eclipse : use of SiriusDiagramWithPrintGlobalActionHandler.
 *    Maxime Porhel (Obeo) <maxime.porhel@obeo.fr> - Trac bug #1502 : Renaming and checkstyle.
 ****************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.print;

import java.util.Hashtable;

import org.eclipse.gmf.runtime.common.ui.services.action.global.AbstractGlobalActionHandlerProvider;
import org.eclipse.gmf.runtime.common.ui.services.action.global.IGlobalActionHandler;
import org.eclipse.gmf.runtime.common.ui.services.action.global.IGlobalActionHandlerContext;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPart;

/**
 * Concrete class that implements the <code>IGlobalActionHandlerProvider</code>
 * providing <code>IGlobalActionHandler</code> for viewpoint diagram ui based
 * diagrams. This provider supports printing of images.
 * 
 * @author cmahoney
 */
public class SiriusDiagramWithPrintGlobalActionHandlerProvider extends AbstractGlobalActionHandlerProvider {

    private Hashtable handlerList = new Hashtable();

    /**
     * Constructor for DiagramWithPrintGlobalActionHandlerProvider.
     */
    public SiriusDiagramWithPrintGlobalActionHandlerProvider() {
        super();
    }

    /**
     * Return a global action handler for the given context.
     * 
     * @param context
     *            the current global action handler context
     * @return a global action hanlder
     */
    @Override
    public IGlobalActionHandler getGlobalActionHandler(final IGlobalActionHandlerContext context) {
        /* Create the handler */
        if (!getHandlerList().containsKey(context.getActivePart())) {
            getHandlerList().put(context.getActivePart(), new SiriusDiagramWithPrintGlobalActionHandler());

            /*
             * Register as a part listener so that the cache can be cleared when
             * the part is disposed
             */
            context.getActivePart().getSite().getPage().addPartListener(new IPartListener() {

                private IWorkbenchPart localPart = context.getActivePart();

                public void partActivated(final IWorkbenchPart part) {
                    // Do nothing
                }

                public void partBroughtToTop(final IWorkbenchPart part) {
                    // Do nothing
                }

                public void partClosed(final IWorkbenchPart part) {
                    /* Remove the cache associated with the part */
                    if (part != null && part == localPart && getHandlerList().containsKey(part)) {
                        getHandlerList().remove(part);
                        localPart.getSite().getPage().removePartListener(this);
                        localPart = null;
                    }
                }

                public void partDeactivated(final IWorkbenchPart part) {
                    // Do nothing
                }

                public void partOpened(final IWorkbenchPart part) {
                    // Do nothing
                }
            });
        }

        return (SiriusDiagramWithPrintGlobalActionHandler) getHandlerList().get(context.getActivePart());
    }

    /**
     * Returns the handlerList.
     * 
     * @return Hashtable
     */
    private Hashtable getHandlerList() {
        return handlerList;
    }
}
