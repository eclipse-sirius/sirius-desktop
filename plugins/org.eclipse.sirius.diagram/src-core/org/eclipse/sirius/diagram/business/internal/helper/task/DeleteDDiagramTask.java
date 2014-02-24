/*******************************************************************************
 * Copyright (c) 2007, 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.business.internal.helper.task;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.api.helper.task.AbstractCommandTask;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramLink;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.tools.api.command.semantic.RemoveDanglingReferences;
import org.eclipse.sirius.tools.internal.util.GMFUtil;
import org.eclipse.sirius.viewpoint.DNavigable;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.SiriusPlugin;

/**
 * A task to delete a representation.
 * 
 * @author mchauvin
 */
public class DeleteDDiagramTask extends AbstractCommandTask {

    private DRepresentation representation;

    private boolean deleteReferences;

    /**
     * Default constructor.
     * 
     * @param representation
     *            the representation to delete
     */
    public DeleteDDiagramTask(final DRepresentation representation) {
        this.representation = representation;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.helper.task.ICommandTask#execute()
     */
    public void execute() {

        /* only destroy attached elements */
        if ((representation != null) && (representation.eResource() != null)) {

            ModelAccessor accessor = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(representation);

            if (deleteReferences) {
                final Session session;
                if (representation instanceof DSemanticDecorator) {
                    session = SessionManager.INSTANCE.getSession(((DSemanticDecorator) representation).getTarget());
                } else {
                    session = SessionManager.INSTANCE.getSession(representation);
                }
                accessor.eDelete(representation, session != null ? session.getSemanticCrossReferencer() : null);
            } else {

                final EObject root = EcoreUtil.getRootContainer(representation);

                if (representation instanceof DDiagram) {
                    // remove the links to the diagram
                    removeSiriusLinks(root, (DDiagram) representation);
                }

                // tear down incoming references
                GMFUtil.tearDownIncomingReferences(representation);

                // also tear down outgoing references, because we don't want
                // reverse-reference lookups to find destroyed objects
                GMFUtil.tearDownOutgoingReferences(representation);

                // remove the object from its container
                SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(representation).eRemove(representation);

                // in case it was cross-resource-contained
                final Resource res = representation.eResource();
                if (res != null) {
                    res.getContents().remove(representation);
                }
                if (root != null) {
                    RemoveDanglingReferences.removeDanglingReferences(root);
                }
            }
        }
    }

    public void setDeleteIncomingReferences(final boolean value) {
        deleteReferences = value;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.helper.task.ICommandTask#getLabel()
     */
    public String getLabel() {
        return null;
    }

    /**
     * Remove the {@link DDiagramLink} instances if they point to the target
     * {@link DDiagram}.
     * 
     * @param root
     *            the root container of {@link DDiagramLink} instances
     * @param viewpoint
     *            the target
     */
    private void removeSiriusLinks(final EObject root, final DDiagram diagram) {

        final Map<DDiagramLink, DNavigable> removedLinks = new HashMap<DDiagramLink, DNavigable>(4);

        final Iterator<EObject> it = root.eAllContents();
        while (it.hasNext()) {
            final EObject child = it.next();
            if (child instanceof DDiagramLink) {
                if (((DDiagramLink) child).getTarget() == diagram) {
                    final EObject container = child.eContainer();
                    if (container instanceof DNavigable) {
                        removedLinks.put((DDiagramLink) child, (DNavigable) container);
                    }
                }
            }
        }

        final Iterator<Map.Entry<DDiagramLink, DNavigable>> itRemovedLinks = removedLinks.entrySet().iterator();
        while (it.hasNext()) {
            final Map.Entry<DDiagramLink, DNavigable> entry = itRemovedLinks.next();
            final DNavigable container = entry.getValue();
            final DDiagramLink child = entry.getKey();
            container.getOwnedNavigationLinks().remove(child);
        }
    }
}
