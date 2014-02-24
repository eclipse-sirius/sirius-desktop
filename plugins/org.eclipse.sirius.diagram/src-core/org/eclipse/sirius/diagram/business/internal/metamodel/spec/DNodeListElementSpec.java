/*******************************************************************************
 * Copyright (c) 2007, 2008, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.metamodel.spec;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.internal.metamodel.operations.DDiagramElementSpecOperations;
import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.impl.DNodeListElementImpl;
import org.eclipse.sirius.tools.api.profiler.SiriusTasksKey;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.Style;

/**
 * Implementation of DNodeListElementImpl.java.
 * 
 * @author cbrun, mchauvin, ymortier.
 */
public class DNodeListElementSpec extends DNodeListElementImpl {
    @Override
    public DiagramElementMapping getMapping() {
        return getActualMapping();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DDiagramElementImpl#getParentDiagram()
     */
    @Override
    public DDiagram getParentDiagram() {
        return DDiagramElementSpecOperations.getParentDiagram(this);
    }

    /**
     * Get the first parent with semantic.
     * 
     * @return the first parent with semantic
     */
    public DSemanticDecorator getFirstParentWithSemantic() {
        DSemanticDecorator result = null;
        EObject cur = this.eContainer();
        while (cur != null && result == null) {
            if (cur instanceof DSemanticDecorator) {
                result = (DSemanticDecorator) cur;
            }
            cur = cur.eContainer();
        }
        return result;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DNodeListElementImpl#validate()
     */
    @Override
    public boolean validate() {
        DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.VALIDATE_NODE_KEY);
        if (getTarget() != null && getFirstParentWithSemantic() != null && getActualMapping() != null) {
            final EObject mySemanticElement = getTarget();
            final EObject representedParent = getFirstParentWithSemantic();
            EObject representedParentSemantic = getFirstParentWithSemantic().getTarget();
            if (representedParent instanceof DSemanticDiagram) {
                representedParentSemantic = ((DSemanticDiagram) representedParent).getRootContent();
            }
            if (!getActualMapping().getNodesCandidates(representedParentSemantic, ((DSemanticDecorator) representedParent).getTarget(), this.eContainer()).contains(mySemanticElement)) {
                DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.VALIDATE_NODE_KEY);
                return false;
            }
        }
        DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.VALIDATE_NODE_KEY);
        return true;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DNodeListElementImpl#refresh()
     */
    @Override
    public void refresh() {
        if (this.getActualMapping() != null) {
            this.getActualMapping().updateListElement(this);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DNodeListElementImpl#getStyle()
     */
    @Override
    public Style getStyle() {
        return getOwnedStyle();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DNodeListElementImpl#isFold(java.util.Map)
     */
    @Override
    public boolean isFold(final Map alreadyManagedElements) {
        final Object hash = Integer.valueOf(this.hashCode());
        final Boolean valueNode = (Boolean) alreadyManagedElements.get(hash);
        if (valueNode != null) {
            return valueNode.booleanValue();
        }
        alreadyManagedElements.put(hash, Boolean.FALSE);
        return false;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "List element " + getName();
    }
}
