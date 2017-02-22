/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.delete;

import java.util.Hashtable;

import org.eclipse.gmf.runtime.common.ui.services.action.global.AbstractGlobalActionHandlerProvider;
import org.eclipse.gmf.runtime.common.ui.services.action.global.IGlobalActionHandler;
import org.eclipse.gmf.runtime.common.ui.services.action.global.IGlobalActionHandlerContext;
import org.eclipse.gmf.runtime.common.ui.services.action.global.IGlobalActionHandlerProvider;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPart;

/**
 * A {@link IGlobalActionHandlerProvider} to provide a
 * {@link IGlobalActionHandler} which manage the delete action.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class SiriusDeleteGlobalActionHandlerProvider extends AbstractGlobalActionHandlerProvider implements IGlobalActionHandlerProvider {

    /**
     * List for handlers.
     */
    private Hashtable<IWorkbenchPart, SiriusDeleteGlobalActionHandler> handlerList = new Hashtable<IWorkbenchPart, SiriusDeleteGlobalActionHandler>();

    /**
     * Overridden to provide the {@link SiriusDeleteGlobalActionHandler}.
     * 
     * {@inheritDoc}
     */
    @Override
    public IGlobalActionHandler getGlobalActionHandler(IGlobalActionHandlerContext context) {
        final IWorkbenchPart part = context.getActivePart();
        /* Create the handler */
        if (!getHandlerList().containsKey(part)) {
            getHandlerList().put(part, new SiriusDeleteGlobalActionHandler());
            /*
             * Register as a part listener so that the cache can be cleared when
             * the part is disposed
             */
            part.getSite().getPage().addPartListener(new PartListener(part));
        }
        return getHandlerList().get(part);
    }

    /**
     * Returns the handlerList.
     * 
     * @return Hashtable
     */
    private Hashtable<IWorkbenchPart, SiriusDeleteGlobalActionHandler> getHandlerList() {
        return handlerList;
    }

    /**
     * A part listener to remove the closed parts from cache.
     */
    private class PartListener implements IPartListener {

        private IWorkbenchPart localPart;

        PartListener(IWorkbenchPart part) {
            this.localPart = part;
        }

        /**
         * Overridden to dispose the {@link IPartListener}.
         * 
         * {@inheritDoc}
         */
        public void partClosed(IWorkbenchPart part) {
            if (part != null && localPart == part && getHandlerList().containsKey(part)) {
                getHandlerList().remove(part);
                part.getSite().getPage().removePartListener(this);
                localPart = null;
            }
        }

        /**
         * {@inheritDoc}
         */
        public void partActivated(IWorkbenchPart part) {
            // Do nothing.
        }

        /**
         * {@inheritDoc}
         */
        public void partBroughtToTop(IWorkbenchPart part) {
            // Do nothing.
        }

        /**
         * {@inheritDoc}
         */
        public void partDeactivated(IWorkbenchPart part) {
            // Do nothing.
        }

        /**
         * {@inheritDoc}
         */
        public void partOpened(IWorkbenchPart part) {
            // Do nothing.
        }
    }
}
