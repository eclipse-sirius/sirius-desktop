/******************************************************************************
 * Copyright (c) 2005, 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 *    Goulwen Le Fur (Obeo) <goulwen.lefur@obeo.fr> - Trac bug #1754 : Remove or make unavailable Copy / Duplicate from menu
 ****************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.clipboard;

import java.util.Hashtable;

import org.eclipse.gmf.runtime.common.ui.services.action.global.AbstractGlobalActionHandlerProvider;
import org.eclipse.gmf.runtime.common.ui.services.action.global.IGlobalActionHandler;
import org.eclipse.gmf.runtime.common.ui.services.action.global.IGlobalActionHandlerContext;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPart;

/**
 * Concrete class that implements the <code>IGlobalActionHandlerProvider</code>
 * providing <code>IGlobalActionHandler</code> for viewpoint diagram ui based
 * diagrams. This provider supports for clipboard.
 * 
 * @author glefur
 */
public class SiriusClipboardGlobalActionHandlerProvider extends AbstractGlobalActionHandlerProvider {

    /**
     * List for handlers.
     */
    private Hashtable<IWorkbenchPart, SiriusClipboardGlobalActionHandler> handlerList = new Hashtable<IWorkbenchPart, SiriusClipboardGlobalActionHandler>();

    /**
     * Creates a new instance.
     */
    public SiriusClipboardGlobalActionHandlerProvider() {
        super();
    }

    /**
     * {@inheritDoc} Returns a global action handler that supports global image
     * operations (cut, copy, and paste).
     */
    @Override
    public IGlobalActionHandler getGlobalActionHandler(final IGlobalActionHandlerContext context) {
        final IWorkbenchPart part = context.getActivePart();
        /* Create the handler */
        if (!getHandlerList().containsKey(part)) {
            getHandlerList().put(part, new SiriusClipboardGlobalActionHandler());
            /*
             * Register as a part listener so that the cache can be cleared when
             * the part is disposed
             */
            part.getSite().getPage().addPartListener(initListener(context));
        }
        return getHandlerList().get(part);
    }

    /**
     * Initialize a listener removing the GlobalAction on closing the given part
     * 
     * @param context
     *            the context to clean
     * @return the expected listener
     */
    private IPartListener initListener(final IGlobalActionHandlerContext context) {
        return new IPartListener() {

            private IWorkbenchPart localPart = context.getActivePart();

            /**
             * @see org.eclipse.ui.IPartListener#partActivated(IWorkbenchPart)
             */
            public void partActivated(IWorkbenchPart part) {
            }

            /**
             * @see org.eclipse.ui.IPartListener#partBroughtToTop(IWorkbenchPart)
             */
            public void partBroughtToTop(IWorkbenchPart part) {
            }

            /**
             * @see org.eclipse.ui.IPartListener#partClosed(IWorkbenchPart)
             */
            public void partClosed(IWorkbenchPart part) {
                /* Remove the cache associated with the part */
                if (part != null && part == localPart && getHandlerList().containsKey(part)) {
                    getHandlerList().remove(part);
                    localPart.getSite().getPage().removePartListener(this);
                    localPart = null;
                }
            }

            /**
             * @see org.eclipse.ui.IPartListener#partDeactivated(IWorkbenchPart)
             */
            public void partDeactivated(IWorkbenchPart part) {
            }

            /**
             * @see org.eclipse.ui.IPartListener#partOpened(IWorkbenchPart)
             */
            public void partOpened(IWorkbenchPart part) {
            }
        };
    }

    /**
     * Returns the handlerList.
     * 
     * @return Hashtable
     */
    private Hashtable<IWorkbenchPart, SiriusClipboardGlobalActionHandler> getHandlerList() {
        return handlerList;
    }
}
