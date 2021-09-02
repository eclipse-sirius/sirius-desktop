/*******************************************************************************
 * Copyright (c) 2018, 2021 Obeo
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.tools.internal.management;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.tools.api.DiagramPlugin;
import org.eclipse.sirius.diagram.tools.api.management.ToolChangeListener;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.tool.FeatureChangeListener;
import org.eclipse.sirius.viewpoint.description.tool.ToolFilterDescription;

/**
 * A resource set listener for a tool description filter. When one or several notifications match the elements to listen
 * and features specified by the the tool, then we update the tools available for the diagram.
 * 
 * @author <a href=mailto:pierre.guilet@obeo.fr>Pierre Guilet</a>
 *
 */
public class ToolFilterDescriptionListenerForUpdate extends ResourceSetListenerImpl {
    /**
     * True if the tools should be updated.
     */
    protected boolean shoudUpdate;

    private DDiagram diagram;

    private EList<FeatureChangeListener> filterListeners;

    /**
     * Create a new instance.
     * 
     * @param interpreter
     *            the interpreter to use to evaluate the tool filter expressions.
     * 
     * @param filter
     *            the filter from which to create a resource set listener
     * @param diagram
     *            the diagram from which tool filters are handled.
     */
    public ToolFilterDescriptionListenerForUpdate(IInterpreter interpreter, final ToolFilterDescription filter, final DDiagram diagram) {
        super(ToolFilterDescriptionListenerForUpdate.getFilter(interpreter, diagram, filter));
        this.filterListeners = filter.getListeners();
        this.diagram = diagram;
    }

    @Override
    public Command transactionAboutToCommit(ResourceSetChangeEvent event) throws RollbackException {
        shoudUpdate = false;
        for (Notification next : event.getNotifications()) {
            handleNotification(event.getEditingDomain(), next);
        }
        if (shoudUpdate) {
            return executeUpdate(event.getEditingDomain());
        }
        return null;
    }

    /**
     * Return the update command to execute.
     * 
     * @param transactionalEditingDomain
     *            the editing domaon from which the command will be executed.
     * @return the update command to execute.
     */
    protected Command executeUpdate(TransactionalEditingDomain transactionalEditingDomain) {
        return new UpdateToolRecordingCommand(transactionalEditingDomain, diagram, false);
    }

    @Override
    public void resourceSetChanged(ResourceSetChangeEvent event) {
        if (shoudUpdate) {
            Set<ToolChangeListener> listenersAssociatedToDiagram = DiagramPlugin.getDefault().getToolManagement(diagram).getToolListeners();
            for (ToolChangeListener toolChangeListener : listenersAssociatedToDiagram) {
                toolChangeListener.notifyToolChange(ToolChangeListener.ChangeKind.OTHER_UPDATE);
            }
        }
    }

    private void handleNotification(TransactionalEditingDomain domain, Notification notification) {
        if (notification.getNotifier() instanceof EObject) {
            final EObject notifier = (EObject) notification.getNotifier();
            final EStructuralFeature feature = (EStructuralFeature) notification.getFeature();
            final ModelAccessor accessor = getModelAccessor(domain);

            for (final FeatureChangeListener listener : filterListeners) {
                if (accessor.eInstanceOf(notifier, listener.getDomainClass())
                        || (notifier instanceof DRepresentationDescriptor && accessor.eInstanceOf(((DRepresentationDescriptor) notifier).getRepresentation(), listener.getDomainClass()))) {
                    if (feature.getName().equals(listener.getFeatureName())) {
                        shoudUpdate = true;
                    }
                }
            }
        }
    }

    private ModelAccessor getModelAccessor(TransactionalEditingDomain domain) {
        return SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(domain.getResourceSet());
    }

    private static NotificationFilter getFilter(IInterpreter interpreter, final DRepresentation representation, ToolFilterDescription filter) {
        final Collection<EObject> elementsToListen = ToolFilterDescriptionListenerForUpdate.elementsToListen(interpreter, representation, filter.getElementsToListen());
        return new NotificationFilter.Custom() {

            @Override
            public boolean matches(Notification notification) {
                boolean repDescNameOrDocChangeNotif = notification.getNotifier() instanceof DRepresentationDescriptor
                        && (ViewpointPackage.eINSTANCE.getDRepresentationDescriptor_Name().equals(notification.getFeature())
                                || DescriptionPackage.eINSTANCE.getDocumentedElement_Documentation().equals(notification.getFeature()));
                return !notification.isTouch() && (elementsToListen.contains(notification.getNotifier()) || repDescNameOrDocChangeNotif);
            }
        };
    }

    private static Collection<EObject> elementsToListen(IInterpreter interpreter, final DRepresentation representation, final String elementsToListen) {
        Collection<EObject> semanticCandidates = Collections.emptySet();
        try {
            if (interpreter != null && !StringUtil.isEmpty(elementsToListen)) {
                semanticCandidates = interpreter.evaluateCollection(representation, elementsToListen);
            }
        } catch (final EvaluationException exception) {
            // TODO log something
        }
        return semanticCandidates;
    }

}
