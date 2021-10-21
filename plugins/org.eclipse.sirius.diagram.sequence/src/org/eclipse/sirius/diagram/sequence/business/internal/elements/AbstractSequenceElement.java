/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.sequence.business.internal.elements;

import java.util.Objects;

import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.api.query.DiagramElementMappingQuery;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.sequence.tool.internal.Messages;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

/**
 * Partial abstract implementation of {@link ISequenceElement}.
 * 
 * @author mporhel
 */
public abstract class AbstractSequenceElement extends AdapterImpl implements ISequenceElement {
    /**
     * The underlying GMF View.
     */
    protected final View view;

    /**
     * The parent SequenceDiagram.
     */
    protected Option<SequenceDiagram> cachedDiagram;

    /**
     * Constructor.
     * 
     * @param view
     *            the underlying GMF View.
     */
    AbstractSequenceElement(View view) {
        this.view = Objects.requireNonNull(view);
    }

    /**
     * Generic test function used by all the more specific predicates: checks that a diagram element is valid, is part
     * of a sequence diagram, and has the specified type of mapping (or a sub-type).
     * 
     * This method handle edge mapping imports.
     * 
     * @param element
     *            {@link DDiagramElement}
     * @param mappingType
     *            type of the mapping
     * 
     * @return true is element is a sequence diagram element
     */
    protected static final boolean isSequenceDiagramElement(DDiagramElement element, EClass mappingType) {
        Objects.requireNonNull(mappingType);
        boolean result = false;
        if (element != null) {
            DiagramElementMapping mapping = element.getDiagramElementMapping();
            if (mapping != null) {
                DiagramElementMapping mappingToCheck = new DiagramElementMappingQuery(mapping).getRootMapping();
                result = mappingType.isInstance(mappingToCheck) && SequenceDiagram.viewpointElementPredicate().apply(element.getParentDiagram());
            }
        }
        return result;
    }

    @Override
    public View getNotationView() {
        return view;
    }

    @Override
    public SequenceDiagram getDiagram() {
        if (cachedDiagram == null) {
            Diagram gmfDiagram = view.getDiagram();
            Option<SequenceDiagram> diagram = ISequenceElementAccessor.getSequenceDiagram(gmfDiagram);
            assert diagram.some() : Messages.AbstractSequenceElement_invalidDiagram;
            cachedDiagram = diagram;
        }
        return cachedDiagram.get();
    }

    @Override
    public int hashCode() {
        return view.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (this == obj) {
            result = true;
        } else if (obj != null && getClass().equals(obj.getClass())) {
            AbstractSequenceElement other = (AbstractSequenceElement) obj;
            if (view != null && view.equals(other.view)) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public Option<EObject> getSemanticTargetElement() {
        EObject element = view.getElement();
        if (element instanceof DSemanticDecorator) {
            return Options.newSome(((DSemanticDecorator) element).getTarget());
        } else {
            return Options.newNone();
        }
    }

    @Override
    public String toString() {
        return "#<" + getClass().getSimpleName() + ": " + view.getElement() + ">"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }
}
