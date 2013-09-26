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
package org.eclipse.sirius.business.internal.helper.information;

import java.util.Iterator;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.viewpoint.DDiagramElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.DiagramDescription;
import org.eclipse.sirius.viewpoint.description.InformationSection;

/**
 * Adapter factory for the information view.
 * 
 * @author ymortier
 */
public class DecorateSemanticElementAdapterFactory implements IAdapterFactory {

    final Class<?>[] supportedTypes = new Class[] { DSemanticDecorator.class };

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.core.runtime.IAdapterFactory#getAdapter(java.lang.Object,
     *      java.lang.Class)
     */
    public Object getAdapter(final Object adaptableObject, @SuppressWarnings("rawtypes") final Class adapterType) {
        if (adaptableObject instanceof DSemanticDecorator && adapterType == InformationSection.class) {
            final DSemanticDecorator element = (DSemanticDecorator) adaptableObject;
            final EObject target = element.getTarget();
            if (element instanceof DDiagramElement && ((DDiagramElement) element).getParentDiagram() != null && ((DDiagramElement) element).getParentDiagram().getDescription() != null) {
                final DiagramDescription desc = ((DDiagramElement) element).getParentDiagram().getDescription();
                final Iterator it = desc.getInformationSections().iterator();
                while (it.hasNext()) {
                    final InformationSection sec = (InformationSection) it.next();
                    final ModelAccessor modelAccessor = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(target);
                    if (modelAccessor.eInstanceOf(target, sec.getTargetClass().getName())) {
                        return sec;
                    }
                }
            }
        }
        return null;
    }

    public Class<?>[] getAdapterList() {
        return supportedTypes;
    }

}
