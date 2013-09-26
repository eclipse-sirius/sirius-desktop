/*******************************************************************************
 * Copyright (c) 2010, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.business.internal.elements;

import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;

import com.google.common.base.Preconditions;

import org.eclipse.sirius.common.tools.api.util.Option;
import org.eclipse.sirius.common.tools.api.util.Options;
import org.eclipse.sirius.business.api.query.DiagramElementMappingQuery;
import org.eclipse.sirius.viewpoint.DDiagramElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.DiagramElementMapping;

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
     * Constructor.
     * 
     * @param view
     *            the underlying GMF View.
     */
    AbstractSequenceElement(View view) {
        this.view = Preconditions.checkNotNull(view);
    }

    /**
     * Generic test function used by all the more specific predicates: checks
     * that a diagram element is valid, is part of a sequence diagram, and has
     * the specified type of mapping (or a sub-type).
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
        Preconditions.checkNotNull(mappingType);
        if (element == null || element.getDiagramElementMapping() == null) {
            return false;
        } else {
            DiagramElementMapping mappingToCheck = new DiagramElementMappingQuery(element.getDiagramElementMapping()).getRootMapping();
            return mappingType.isInstance(mappingToCheck) && SequenceDiagram.viewpointElementPredicate().apply(element.getParentDiagram());
        }
    }

    /**
     * {@inheritDoc}
     */
    public View getNotationView() {
        return view;
    }

    /**
     * {@inheritDoc}
     */
    public SequenceDiagram getDiagram() {
        Diagram gmfDiagram = view.getDiagram();
        Option<SequenceDiagram> diagram = ISequenceElementAccessor.getSequenceDiagram(gmfDiagram);
        assert diagram.some() : "The element is not part of a sequence diagram.";
        return diagram.get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return view.hashCode();
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    public Option<EObject> getSemanticTargetElement() {
        if (view.getElement() instanceof DSemanticDecorator) {
            return Options.newSome(((DSemanticDecorator) view.getElement()).getTarget());
        } else {
            return Options.newNone();
        }
    }

    /**
     * Tries to find a lifeline among the ancestors of this element (including
     * the element itself).
     * 
     * @return option on the parent lifeline of this sequenceElement
     */
    protected Option<Lifeline> getParentLifeline() {
        View current = view;
        do {
            Option<Lifeline> lifeline = ISequenceElementAccessor.getLifeline(current);
            if (lifeline.some()) {
                return lifeline;
            } else {
                current = (View) current.eContainer();
            }
        } while (current != null && !(current instanceof Diagram));
        return Options.newNone();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "#<" + getClass().getSimpleName() + ": " + view.getElement() + ">";
    }
}
