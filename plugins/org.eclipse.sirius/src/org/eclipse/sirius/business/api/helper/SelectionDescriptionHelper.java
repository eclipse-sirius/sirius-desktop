/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.api.helper;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.util.TreeItemWrapper;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.SelectionDescription;

import com.google.common.collect.ImmutableSet;

/**
 * Utility class for managing selection description elements.
 * 
 * @author mporhel
 * 
 */
public final class SelectionDescriptionHelper {

    /**
     * Avoid instantiation.
     */
    private SelectionDescriptionHelper() {

    }

    /**
     * Compute the TreeItemWrapper corresponding to this
     * {@link SelectionDescription}.
     * 
     * @param selectionDescription
     *            the selection description
     * @param container
     *            the semantic element
     * @param interpreter
     *            the interpreter used to compute expressions of the selection
     *            description
     * @param input
     *            the TreeItemWrapper to complete
     */
    public static void computeInput(final SelectionDescription selectionDescription, final EObject container, final IInterpreter interpreter, final TreeItemWrapper input) {

        final Collection<EObject> referencingENode = ImmutableSet.copyOf(RuntimeLoggerManager.INSTANCE.decorate(interpreter).evaluateCollection(container, selectionDescription,
                DescriptionPackage.eINSTANCE.getSelectionDescription_CandidatesExpression()));
        if (selectionDescription.isTree()) {
            final Collection<EObject> referencingRoots = RuntimeLoggerManager.INSTANCE.decorate(interpreter).evaluateCollection(container, selectionDescription,
                    DescriptionPackage.eINSTANCE.getSelectionDescription_RootExpression());
            final Iterator<EObject> iterRoots = referencingRoots.iterator();
            while (iterRoots.hasNext()) {
                final EObject refRoot = iterRoots.next();
                if (referencingENode.contains(refRoot)) {
                    final TreeItemWrapper treeItem = new TreeItemWrapper(refRoot, input);
                    input.getChildren().add(treeItem);
                    SelectionDescriptionHelper.computeChildren(selectionDescription, referencingENode, interpreter, treeItem, refRoot);
                }
            }
        } else {
            final Iterator<EObject> iterRoots = referencingENode.iterator();
            while (iterRoots.hasNext()) {
                final EObject refRoot = iterRoots.next();
                if (referencingENode.contains(refRoot)) {
                    final TreeItemWrapper treeItem = new TreeItemWrapper(refRoot, input);
                    input.getChildren().add(treeItem);
                }
            }
        }
    }

    private static void computeChildren(final SelectionDescription selectionDescription, final Collection<EObject> referencingENode, final IInterpreter interpreter, final TreeItemWrapper parent,
            final EObject refParent) {
        final Collection<EObject> referencingChilds = RuntimeLoggerManager.INSTANCE.decorate(interpreter).evaluateCollection(refParent, selectionDescription,
                DescriptionPackage.eINSTANCE.getSelectionDescription_ChildrenExpression());
        final Iterator<EObject> iterchilds = referencingChilds.iterator();
        while (iterchilds.hasNext()) {
            final EObject refElement = iterchilds.next();
            if (referencingENode.contains(refElement) && !parent.knownThisAsAncestor(refElement)) {
                final TreeItemWrapper treeItem = new TreeItemWrapper(refElement, parent);
                parent.getChildren().add(treeItem);
                SelectionDescriptionHelper.computeChildren(selectionDescription, referencingENode, interpreter, treeItem, refElement);
            }
        }
    }
}
