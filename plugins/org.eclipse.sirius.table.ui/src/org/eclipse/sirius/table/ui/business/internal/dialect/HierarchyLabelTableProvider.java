/*******************************************************************************
 * Copyright (c) 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.ui.business.internal.dialect;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.table.metamodel.table.description.TableDescription;
import org.eclipse.sirius.table.metamodel.table.description.TableMapping;
import org.eclipse.sirius.table.metamodel.table.description.TableNavigationDescription;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;
import org.eclipse.sirius.viewpoint.description.RepresentationExtensionDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolEntry;
import org.eclipse.swt.graphics.Image;

/**
 * A label provider for mappings to display their hierarchy.
 * 
 * @author jdupont
 */
public class HierarchyLabelTableProvider extends LabelProvider {

    private static final String DELIMITER = " > "; //$NON-NLS-1$

    private ILabelProvider wrappedProvider;

    /**
     * Create a new instance with wrapped label provider as base type.
     * 
     * @param wrappedLabelProvider
     *            the wrapped label provider
     */
    public HierarchyLabelTableProvider(final ILabelProvider wrappedLabelProvider) {
        this.wrappedProvider = wrappedLabelProvider;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.LabelProvider#getImage(java.lang.Object)
     */
    @Override
    public Image getImage(final Object element) {
        return wrappedProvider != null ? wrappedProvider.getImage(element) : super.getImage(element);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.LabelProvider#getText(java.lang.Object)
     */
    @Override
    public String getText(final Object element) {
        if (element instanceof TableMapping || element instanceof TableDescription || element instanceof TableNavigationDescription || element instanceof ToolEntry) {
            final IdentifiedElement mapping = (IdentifiedElement) element;
            String text = mapping.getName();
            EObject container = mapping.eContainer();
            while (container != null && !(container instanceof Group)) {
                text = getLabel(container) + DELIMITER + text;
                container = container.eContainer();
            }
            return text;
        }
        return wrappedProvider != null ? wrappedProvider.getText(element) : super.getText(element);
    }

    private String getLabel(final EObject eObject) {

        String label = null;

        if (eObject instanceof IdentifiedElement) {
            label = new IdentifiedElementQuery((IdentifiedElement) eObject).getLabel();
        } else if (eObject instanceof RepresentationExtensionDescription) {
            label = ((RepresentationExtensionDescription) eObject).getName();
        }
        return label != null ? label : "Element whithout name";
    }

}
