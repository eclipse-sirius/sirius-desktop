/*******************************************************************************
 * Copyright (c) 2007, 2020 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.api.properties.filter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.IFilter;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

/**
 * The base filter for property. The job of this filter is to initialize the <code>notationView</code>, the
 * <code>viewPointElement</code> and the <code>semanticElements</code> attributes. The method
 * {@link AbstractPropertyFilter#select(Object)} returns always <code>true</code>.
 * 
 * @author ymortier
 */
public abstract class AbstractPropertyFilter implements IFilter {

    /** The edit part. */
    protected EditPart editPart;

    /** The GMF model element. */
    protected View notationView;

    /** The viewpoint model element. */
    protected EObject viewPointElement;

    /** The semantic model elements. */
    protected List<EObject> semanticElements;

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
     */
    @Override
    public boolean select(final Object toTest) {
        this.notationView = null;
        this.viewPointElement = null;
        this.semanticElements = Collections.emptyList();
        if (toTest instanceof EditPart) {
            this.editPart = (EditPart) toTest;
            if (this.editPart.isActive() && editPart.getModel() instanceof View) {
                this.notationView = (View) editPart.getModel();
            }
        } else if (toTest instanceof View) {
            this.notationView = (View) toTest;
        } else if (toTest instanceof DDiagramElement) {
            this.viewPointElement = (DDiagramElement) toTest;
        }
        if (viewPointElement == null && notationView != null) {
            if (notationView.getElement() != null) {
                this.viewPointElement = notationView.getElement();
            }
        }
        if (viewPointElement instanceof DSemanticDecorator) {
            final DSemanticDecorator decorateSemanticElement = (DSemanticDecorator) viewPointElement;
            // avoid doubles.
            final Set<EObject> tmpSemanticElements = new HashSet<EObject>();
            if (viewPointElement instanceof DDiagramElement) {
                tmpSemanticElements.addAll(((DDiagramElement) viewPointElement).getSemanticElements());
            }
            tmpSemanticElements.add(decorateSemanticElement.getTarget());
            this.semanticElements = new ArrayList<EObject>(tmpSemanticElements);
        }
        return true;
    }

}
