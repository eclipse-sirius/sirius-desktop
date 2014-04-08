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
package org.eclipse.sirius.diagram.business.internal.metamodel.spec;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.common.tools.api.util.EObjectCouple;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.operations.AbstractNodeMappingSpecOperations;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.DSemanticDiagramHelper;
import org.eclipse.sirius.diagram.business.internal.metamodel.operations.DDiagramElementSpecOperations;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.DragAndDropTargetDescription;
import org.eclipse.sirius.diagram.impl.DNodeImpl;
import org.eclipse.sirius.tools.api.profiler.SiriusTasksKey;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.Style;

/**
 * Implementation of DNodeImpl.java.
 * 
 * @author cbrun, mchauvin, ymortier
 */
public class DNodeSpec extends DNodeImpl {

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DNodeImpl#getMapping()
     */
    @Override
    public DiagramElementMapping getMapping() {
        return getActualMapping();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DNodeImpl#refresh()
     */
    @Override
    public void refresh() {
        if (this.getActualMapping() != null) {
            this.getActualMapping().updateNode(this);

            /*
             * Update bordering nodes
             */
            final Iterator<DNode> iter = this.getOwnedBorderedNodes().iterator();
            final Collection<EObjectCouple> managedBorderingNodes = new HashSet<EObjectCouple>();
            while (iter.hasNext()) {
                final DNode n = iter.next();

                // n.getOriginMapping().updateNode(n);

                n.refresh();

                managedBorderingNodes.add(new EObjectCouple(n.getTarget(), n.getActualMapping()));
            }
            /*
             * create the non managed bordering nodes
             */

            AbstractNodeMappingSpecOperations.createBorderingNodes(this.getActualMapping(), this.getTarget(), this, managedBorderingNodes, this.getParentDiagram());
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DNodeImpl#getStyle()
     */
    @Override
    public Style getStyle() {
        return getOwnedStyle();
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
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DNodeImpl#isFold(java.util.Map)
     */
    @Override
    @Deprecated
    public boolean isFold(final Map<?, ?> alreadyManagedElements) {
        throw new UnsupportedOperationException("Deprecated method.");
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.impl.DNodeImpl#validate()
     */
    @Override
    public boolean validate() {
        DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.VALIDATE_NODE_KEY);
        if (getTarget() != null && getFirstParentWithSemantic() != null && getActualMapping() != null) {
            final EObject mySemanticElement = getTarget();
            final EObject representedParent = getFirstParentWithSemantic();
            EObject representedParentSemantic = getFirstParentWithSemantic().getTarget();
            if (representedParent instanceof DSemanticDiagram) {
                representedParentSemantic = DSemanticDiagramHelper.getRootContent((DSemanticDiagram) representedParent);
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
     * Return the first parent that is a {@link DSemanticDecorator}.
     * 
     * @return the first parent that is a {@link DSemanticDecorator}.
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
     * @see org.eclipse.sirius.viewpoint.impl.DNodeImpl#getDragAndDropDescription()
     */
    @Override
    public DragAndDropTargetDescription getDragAndDropDescription() {
        return this.getActualMapping();
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuffer result = new StringBuffer("Node");
        if (getName() != null && getName().length() > 0) {
            result.append(" ");
            result.append(getName());
        }
        if (getTarget() != null) {
            result.append(" --> ");
            result.append(getTarget().toString());
        }
        return result.toString();
    }

}
