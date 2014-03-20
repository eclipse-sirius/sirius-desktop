/*******************************************************************************
 * Copyright (c) 2007, 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.internal.providers;

import java.lang.ref.WeakReference;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.services.editpart.AbstractEditPartProvider;
import org.eclipse.gmf.runtime.diagram.ui.services.editpart.CreateGraphicEditPartOperation;
import org.eclipse.gmf.runtime.diagram.ui.services.editpart.CreateRootEditPartOperation;
import org.eclipse.gmf.runtime.diagram.ui.services.editpart.IEditPartOperation;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DDiagramEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.SiriusEditPartFactory;
import org.eclipse.sirius.diagram.ui.part.SiriusVisualIDRegistry;
import org.eclipse.sirius.diagram.ui.tools.internal.graphical.edit.part.DDiagramRootEditPart;

/**
 * @was-generated
 */
public class SiriusEditPartProvider extends AbstractEditPartProvider {

    /**
     * @was-generated
     */
    private EditPartFactory factory;

    /**
     * @was-generated
     */
    private boolean allowCaching;

    /**
     * @was-generated
     */
    private WeakReference<IGraphicalEditPart> cachedPart;

    /**
     * @was-generated
     */
    private WeakReference<View> cachedView;

    /**
     * @was-generated
     */
    public SiriusEditPartProvider() {
        setFactory(new SiriusEditPartFactory());
        setAllowCaching(true);
    }

    /**
     * @was-generated
     */
    public final EditPartFactory getFactory() {
        return factory;
    }

    /**
     * @was-generated
     */
    protected void setFactory(EditPartFactory factory) {
        this.factory = factory;
    }

    /**
     * @was-generated
     */
    public final boolean isAllowCaching() {
        return allowCaching;
    }

    /**
     * @was-generated
     */
    protected synchronized void setAllowCaching(boolean allowCaching) {
        this.allowCaching = allowCaching;
        if (!allowCaching) {
            cachedPart = null;
            cachedView = null;
        }
    }

    /**
     * @was-generated
     */
    protected IGraphicalEditPart createEditPart(View view) {
        EditPart part = factory.createEditPart(null, view);
        if (part instanceof IGraphicalEditPart) {
            return (IGraphicalEditPart) part;
        }
        return null;
    }

    /**
     * @was-generated NOT : return the {@link DDiagramRootEditPart}.
     */
    public RootEditPart createRootEditPart(Diagram diagram) {
        return new DDiagramRootEditPart(diagram.getMeasurementUnit());
    }

    /**
     * @was-generated
     */
    protected IGraphicalEditPart getCachedPart(View view) {
        if (cachedView != null && cachedView.get() == view) {
            return cachedPart.get();
        }
        return null;
    }

    /**
     * @was-generated
     */
    public synchronized IGraphicalEditPart createGraphicEditPart(View view) {
        if (isAllowCaching()) {
            IGraphicalEditPart part = getCachedPart(view);
            cachedPart = null;
            cachedView = null;
            if (part != null) {
                return part;
            }
        }
        return createEditPart(view);
    }

    /**
     * @was-generated NOT customize the root edit part.
     */
    public synchronized boolean provides(IOperation operation) {
        if (operation instanceof CreateGraphicEditPartOperation) {
            View view = ((IEditPartOperation) operation).getView();
            if (!DDiagramEditPart.MODEL_ID.equals(SiriusVisualIDRegistry.getModelID(view))) {
                return false;
            }
            if (isAllowCaching() && getCachedPart(view) != null) {
                return true;
            }
            IGraphicalEditPart part = createEditPart(view);
            if (part != null) {
                if (isAllowCaching()) {
                    cachedPart = new WeakReference<IGraphicalEditPart>(part);
                    cachedView = new WeakReference<View>(view);
                }
                return true;
            }
        } else if (operation instanceof CreateRootEditPartOperation) {
            View view = ((IEditPartOperation) operation).getView();
            if (!DDiagramEditPart.MODEL_ID.equals(SiriusVisualIDRegistry.getModelID(view))) {
                return false;
            }
            return true;
        }
        return false;
    }
}
