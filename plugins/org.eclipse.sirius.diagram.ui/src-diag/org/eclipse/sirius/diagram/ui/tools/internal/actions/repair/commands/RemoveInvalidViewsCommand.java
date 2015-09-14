/*******************************************************************************
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.actions.repair.commands;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.common.command.IdentityCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmf.runtime.diagram.ui.internal.editparts.DefaultNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.services.editpart.EditPartService;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.ui.tools.api.profiler.SiriusTasks;

/**
 * Specific command to remove invalid views.
 * 
 * @author mporhel
 */
@SuppressWarnings("restriction")
public class RemoveInvalidViewsCommand extends IdentityCommand {

    private Resource airdResource;

    /**
     * Default constructor.
     * 
     * @param airdResource
     *            Aird {@link Resource}
     */
    public RemoveInvalidViewsCommand(Resource airdResource) {
        super(Messages.RemoveInvalidViewsCommand_label);
        this.airdResource = airdResource;
    }

    /**
     * Overridden.
     * 
     * {@inheritDoc}
     */
    @Override
    public void execute() {
        final Iterator<EObject> iterContents = airdResource.getAllContents();
        final List<View> viewsToRemove = new LinkedList<View>();
        while (iterContents.hasNext()) {
            final EObject next = iterContents.next();
            if (next instanceof View) {
                final View currentView = (View) next;
                if (!isValid(currentView)) {
                    viewsToRemove.add(currentView);
                }
            }
        }

        for (final View view : viewsToRemove) {
            SiriusUtil.delete(view);
        }
        airdResource = null;
    }

    /**
     * Check if a view is valid.
     * 
     * @param view
     *            the view to check
     * @return <code>true</code>if the view is valid, <code>false</code>
     *         otherwise
     */
    public boolean isValid(final View view) {
        return isValid(view, false);
    }

    /**
     * Check if a view is valid.
     * 
     * @param view
     *            the view to check
     * @param checkElement
     *            true if we must check that the element of this view is not
     *            null and is in a resource
     * @return <code>true</code>if the view is valid, <code>false</code>
     *         otherwise
     */
    public boolean isValid(final View view, boolean checkElement) {
        DslCommonPlugin.PROFILER.startWork(SiriusTasks.IS_VIEW_VALID);
        // Otherwise some providers could be removed from GMF Services
        boolean result = true;
        try {
            if (checkElement) {
                result = view.getElement() != null && view.getElement().eResource() != null;
            }
            result = result && !(EditPartService.getInstance().createGraphicEditPart(view) instanceof DefaultNodeEditPart);
            // CHECKSTYLE:OFF
        } catch (final Exception e) {
            // CHECKSTYLE:ON
            // silent
        }
        DslCommonPlugin.PROFILER.stopWork(SiriusTasks.IS_VIEW_VALID);
        return result;
    }

    /**
     * Overridden to avoid the CommandStack to keep a reference to this command.
     * 
     * {@inheritDoc}
     */
    @Override
    public boolean canUndo() {
        return false;
    }
}
