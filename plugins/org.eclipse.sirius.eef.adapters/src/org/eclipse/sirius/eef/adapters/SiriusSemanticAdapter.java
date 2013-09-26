/*******************************************************************************
 * Copyright (c) 2009-2012 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.eef.adapters;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.eef.runtime.api.adapters.SemanticAdapter;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.sirius.eef.util.VPDecoratorHelper;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

/**
 * An adapter factory to convert an element from Sirius into an EEF
 * SemanticAdapter, if possible.
 * 
 * @author <a href="mailto:jerome.benois@obeo.fr">Jerome Benois</a>
 * @author <a href="mailto:goulwen.lefur@obeo.fr">Goulwen Le Fur</a>
 */
public class SiriusSemanticAdapter implements IAdapterFactory {

    /** The types list */
    private static final Class<?>[] TYPES = { SemanticAdapter.class, };

    /**
     * {@inheritDoc}
     */
    public Object getAdapter(final Object adaptableObject, @SuppressWarnings("rawtypes")
    final Class adapterType) {
        Object adapter = null;
        // if Object comes from GMF
        if ((adaptableObject != null) && (adapterType == SemanticAdapter.class)) {
            EObject semanticElement = null;
            if (adaptableObject instanceof GraphicalEditPart) {
                semanticElement = ((GraphicalEditPart) adaptableObject).resolveSemanticElement();
            } else if (adaptableObject instanceof ConnectionEditPart) {
                semanticElement = ((Edge) ((ConnectionEditPart) adaptableObject).getModel()).getElement();
            } else if (adaptableObject instanceof DSemanticDecorator) {
                semanticElement = ((DSemanticDecorator) adaptableObject).getTarget();
            }
            if (semanticElement != null) {
                VPDecoratorHelper helper = new VPDecoratorHelper(semanticElement);
                if (helper.canAdapt()) {
                    return helper.createSemanticAdapterFromDSemanticDecorator();
                }
            }
        }
        return adapter;
    }

    /**
     * {@inheritDoc}
     */
    public Class<?>[] getAdapterList() {
        return TYPES;
    }

}
