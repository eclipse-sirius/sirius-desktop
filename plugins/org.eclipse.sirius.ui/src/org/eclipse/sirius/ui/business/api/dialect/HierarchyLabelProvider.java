/*******************************************************************************
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.business.api.dialect;

import java.util.LinkedList;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;
import org.eclipse.sirius.viewpoint.description.RepresentationExtensionDescription;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.swt.graphics.Image;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

/**
 * A {@link LabelProvider} to display Sirius model description element in a
 * hierarchical way.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 * @author pcdavid
 */
public class HierarchyLabelProvider extends LabelProvider {

    private static final String DELIMITER = " > "; //$NON-NLS-1$

    private ILabelProvider wrappedProvider;

    /**
     * Create a new instance with wrapped label provider as base type.
     * 
     * @param wrappedProvider
     *            the wrapped {@link ILabelProvider}
     */
    public HierarchyLabelProvider(ILabelProvider wrappedProvider) {
        this.wrappedProvider = wrappedProvider;
    }

    @Override
    public Image getImage(final Object element) {
        return wrappedProvider != null ? wrappedProvider.getImage(element) : super.getImage(element);
    }

    @Override
    public String getText(Object element) {
        if (element instanceof EObject && handles((EObject) element)) {
            LinkedList<String> segments = Lists.newLinkedList();
            for (EObject current = (EObject) element; current != null; current = current.eContainer()) {
                segments.addFirst(getLabel(current));
            }
            return Joiner.on(getDelimiter()).join(segments);
        }
        return wrappedProvider != null ? wrappedProvider.getText(element) : super.getText(element);
    }

    /**
     * Tests whether this HierarchyLabelProvider can handle the specified
     * element. If not, the plain label from the wrapped provider will be used.
     * 
     * @param element
     *            the element to test.
     * @return <code>true</code> if this provider can handl the element.
     */
    protected boolean handles(EObject element) {
        return true;
    }

    /**
     * The delimiter to use between the segments of the hierarchy.
     * 
     * @return the delimiter to use.
     */
    protected String getDelimiter() {
        return DELIMITER;
    }

    /**
     * The label to use for null elements.
     * 
     * @return the label to use for null elements.
     */
    protected String getDefaultLabel() {
        return Messages.HierarchyLabelProvider_elementWihtoutNameLabel;
    }

    private String getLabel(EObject eObject) {
        String label = getDefaultLabel();
        if (eObject instanceof IdentifiedElement) {
            label = new IdentifiedElementQuery((IdentifiedElement) eObject).getLabel();
        } else if (eObject instanceof RepresentationExtensionDescription) {
            label = ((RepresentationExtensionDescription) eObject).getName();
        } else {
            label = wrappedProvider.getText(eObject);
        }
        return label;
    }

    @Override
    public void dispose() {
        if (wrappedProvider != null) {
            wrappedProvider.dispose();
            wrappedProvider = null;
        }
        super.dispose();
    }
}
