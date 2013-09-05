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

import org.eclipse.sirius.DNode;
import org.eclipse.sirius.SiriusPlugin;
import org.eclipse.sirius.description.DiagramDescription;
import org.eclipse.sirius.description.InformationSection;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;

/**
 * Adapter factory for the Information View.
 * 
 * @author cbrun
 * 
 */
public class ViewNodeAdapterFactory implements IAdapterFactory {

    final Class<?>[] supportedTypes = new Class[] { DNode.class };

    /**
     * {@inheritDoc}
     */
    public Object getAdapter(final Object adaptableObject, @SuppressWarnings("rawtypes") final Class adapterType) {
        if (adaptableObject instanceof DNode) {
            final DNode node = (DNode) adaptableObject;
            final EObject target = node.getTarget();
            if (node.eContainer() != null && node.getParentDiagram() != null && node.getParentDiagram().getDescription() != null) {
                final DiagramDescription desc = node.getParentDiagram().getDescription();
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
