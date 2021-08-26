/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.business.internal.metamodel.helper;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;

/**
 * A key of {@link BestStyleDescriptionRegistry}.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class BestStyleDescriptionKey {

    private DiagramElementMapping diagramElementMapping;

    private EObject modelElement;

    private EObject viewVariable;

    private EObject containerVariable;

    private DDiagram dDiagram;

    /**
     * Default constructor.
     * 
     * @param diagramElementMapping
     *            the {@link DiagramElementMapping} of the
     *            <code>viewVariable</code>
     * @param modelElement
     *            the target of the <code>viewVariable</code>
     * @param viewVariable
     *            the view representing the semantic target
     * @param containerVariable
     *            the semantic container
     * @param dDiagram
     *            the {@link DDiagram} owing the <code>viewVariable</code>
     */
    public BestStyleDescriptionKey(DiagramElementMapping diagramElementMapping, EObject modelElement, EObject viewVariable, EObject containerVariable, DDiagram dDiagram) {
        this.diagramElementMapping = diagramElementMapping;
        this.modelElement = modelElement;
        this.viewVariable = viewVariable;
        this.containerVariable = containerVariable;
        this.dDiagram = dDiagram;
    }

    public DiagramElementMapping getDiagramElementMapping() {
        return diagramElementMapping;
    }

    public EObject getModelElement() {
        return modelElement;
    }

    public EObject getViewVariable() {
        return viewVariable;
    }

    public EObject getContainerVariable() {
        return containerVariable;
    }

    public DDiagram getDDiagram() {
        return dDiagram;
    }

    @Override
    public int hashCode() {
        int hashCode = 1;
        hashCode = 31 * hashCode + (diagramElementMapping == null ? 0 : diagramElementMapping.hashCode());
        hashCode = 31 * hashCode + (modelElement == null ? 0 : modelElement.hashCode());
        hashCode = 31 * hashCode + (viewVariable == null ? 0 : viewVariable.hashCode());
        hashCode = 31 * hashCode + (containerVariable == null ? 0 : containerVariable.hashCode());
        hashCode = 31 * hashCode + (dDiagram == null ? 0 : dDiagram.hashCode());
        return hashCode;
    }

    @Override
    public boolean equals(Object obj) {
        boolean equals = false;
        if (obj instanceof BestStyleDescriptionKey) {
            BestStyleDescriptionKey bestStyleDescriptionKey = (BestStyleDescriptionKey) obj;
            equals = equalsMapping(bestStyleDescriptionKey.getDiagramElementMapping()) && equalsModelElement(bestStyleDescriptionKey.getModelElement())
                    && equalsViewVariable(bestStyleDescriptionKey.getViewVariable()) && equalsDDiagram(bestStyleDescriptionKey.getDDiagram());
        }
        return equals;
    }

    private boolean equalsMapping(DiagramElementMapping otherDiagramElementMapping) {
        boolean equalsMapping = otherDiagramElementMapping == null && this.diagramElementMapping == null;
        if (!equalsMapping && diagramElementMapping != null) {
            equalsMapping = this.diagramElementMapping.equals(otherDiagramElementMapping);
        }
        return equalsMapping;
    }

    private boolean equalsModelElement(EObject otherModelElement) {
        boolean equalsModelElement = otherModelElement == null && this.modelElement == null;
        if (!equalsModelElement && modelElement != null) {
            equalsModelElement = this.modelElement.equals(otherModelElement);
        }
        return equalsModelElement;
    }

    private boolean equalsViewVariable(EObject otherViewVariable) {
        boolean equalsViewVariable = otherViewVariable == null && this.viewVariable == null;
        if (!equalsViewVariable && viewVariable != null) {
            equalsViewVariable = this.viewVariable.equals(otherViewVariable);
        }
        return equalsViewVariable;
    }

    private boolean equalsDDiagram(DDiagram otherDDiagram) {
        boolean equalsDDiagram = otherDDiagram == null && this.dDiagram == null;
        if (!equalsDDiagram && dDiagram != null) {
            equalsDDiagram = this.dDiagram.equals(otherDDiagram);
        }
        return equalsDDiagram;
    }
}
