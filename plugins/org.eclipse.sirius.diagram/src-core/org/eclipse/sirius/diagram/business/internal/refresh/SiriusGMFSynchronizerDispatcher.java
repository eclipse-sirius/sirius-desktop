/*******************************************************************************
 * Copyright (c) 2011, 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.business.internal.refresh;

import java.util.Collection;
import java.util.Set;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.api.refresh.CanonicalSynchronizer;
import org.eclipse.sirius.diagram.business.api.refresh.CanonicalSynchronizerFactory;
import org.eclipse.sirius.diagram.business.api.refresh.DiagramCreationUtil;
import org.eclipse.sirius.diagram.business.internal.metamodel.operations.DDiagramElementSpecOperations;

import com.google.common.collect.Sets;

/**
 * Dispatch a collection of {@link Notification}s to a EMF Command.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class SiriusGMFSynchronizerDispatcher {

    /**
     * Get a EMF Command which update the GMF notation model from the {@link Notification} collection.
     * 
     * @param domain
     *            the {@link TransactionalEditingDomain} from which the Command will be executed
     * 
     * @param notifications
     *            the {@link Notification} collection about the session model changes.
     * 
     * @return the EMF COmmand to update the GMF notation model
     */
    public Command getGMFNotationModelSynchronizationCmd(TransactionalEditingDomain domain, Collection<Notification> notifications) {
        Command gmfNotationModelSynchronizationCmd = null;
        final Set<Diagram> gmfDiagramToSynchronizes = Sets.newLinkedHashSet();

        for (Notification notification : notifications) {
            Diagram gmfDiagram = getGMFDiagram(notification);

            if (gmfDiagram != null && !gmfDiagramToSynchronizes.contains(gmfDiagram)) {
                gmfDiagramToSynchronizes.add(gmfDiagram);
                CanonicalSynchronizer canonicalSynchronizer = CanonicalSynchronizerFactory.INSTANCE.createCanonicalSynchronizer(gmfDiagram);
                if (gmfNotationModelSynchronizationCmd == null) {
                    gmfNotationModelSynchronizationCmd = new SynchronizeGMFModelCommand(domain, canonicalSynchronizer);
                } else {
                    Command synchronizeCommand = new SynchronizeGMFModelCommand(domain, canonicalSynchronizer);
                    gmfNotationModelSynchronizationCmd = gmfNotationModelSynchronizationCmd.chain(synchronizeCommand);
                }
            }
        }
        return gmfNotationModelSynchronizationCmd;
    }

    private Diagram getGMFDiagram(Notification notification) {
        Diagram gmfDiagram = null;
        DDiagram dDiagram = null;
        Object notifier = notification.getNotifier();
        if (notifier instanceof DDiagram) {
            dDiagram = (DDiagram) notifier;
        } else if (notifier instanceof DDiagramElement && ((EObject) notifier).eContainer() != null) {
            dDiagram = ((DDiagramElement) notifier).getParentDiagram();
        } else if (notification.getOldValue() instanceof DDiagramElement) {
            DDiagramElement oldValue = (DDiagramElement) notification.getOldValue();
            dDiagram = DDiagramElementSpecOperations.getParentDiagram(oldValue);
        }
        if (dDiagram != null) {
            DiagramCreationUtil diagramCreationUtil = new DiagramCreationUtil(dDiagram);
            if (diagramCreationUtil.findAssociatedGMFDiagram()) {
                // the GMF diagram model is contained in the DDiagram model
                gmfDiagram = diagramCreationUtil.getAssociatedGMFDiagram();
            } else {
                // else the GMF diagram should be aside of the DDiagram model in
                // the same resource
                Resource diagramResource = dDiagram.eResource();
                for (final EObject object : diagramResource.getContents()) {
                    if (object instanceof Diagram) {
                        Diagram diagram = (Diagram) object;
                        if (dDiagram.equals(diagram.getElement())) {
                            gmfDiagram = diagram;
                            break;
                        }
                    }
                }
            }
        }
        return gmfDiagram;
    }
}
