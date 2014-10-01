/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.api.tool;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.DemultiplexingListener;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.tool.FeatureChangeListener;
import org.eclipse.sirius.viewpoint.description.tool.ToolFilterDescription;

/**
 * A resource set listener for a tool description filter. When one or several
 * notifications match the elements to listen and features specified by the the
 * tool
 * 
 * @author mchauvin
 * @since 0.9.0
 */
public class ToolFilterDescriptionListener extends DemultiplexingListener {

    private ToolFilterDescription filter;

    private boolean shoudUpdate;

    private Runnable updateRunnable;

    /**
     * Create a new instance.
     * 
     * @param interpreter
     *            the interpreter to use to evaluate the tool filter
     *            expressions.
     * 
     * @param filter
     *            the filter from which to create a resource set listener
     * @param representation
     *            the representation to use as context for elements to listen
     *            computation
     */
    public ToolFilterDescriptionListener(IInterpreter interpreter, final ToolFilterDescription filter, final DRepresentation representation) {
        super(ToolFilterDescriptionListener.getFilter(interpreter, representation, filter));
        this.filter = filter;
    }

    /**
     * Set the runnable which will be called when the notifications matches the
     * elements to listen and associated features.
     * 
     * @param runnable
     *            the runnable to call
     */
    public void setUpdateRunnable(final Runnable runnable) {
        this.updateRunnable = runnable;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.transaction.DemultiplexingListener#resourceSetChanged(org.eclipse.emf.transaction.ResourceSetChangeEvent)
     */
    @Override
    public void resourceSetChanged(ResourceSetChangeEvent event) {
        shoudUpdate = false;
        super.resourceSetChanged(event);
        if (shoudUpdate) {
            updateRunnable.run();
        }

    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.transaction.DemultiplexingListener#handleNotification(org.eclipse.emf.transaction.TransactionalEditingDomain,
     *      org.eclipse.emf.common.notify.Notification)
     */
    @Override
    protected void handleNotification(TransactionalEditingDomain domain, Notification notification) {
        if (notification.getNotifier() instanceof EObject) {
            final EObject notifier = (EObject) notification.getNotifier();
            final EStructuralFeature feature = (EStructuralFeature) notification.getFeature();
            final ModelAccessor accessor = getModelAccessor(domain);

            for (final FeatureChangeListener listener : filter.getListeners()) {
                if (accessor.eInstanceOf(notifier, listener.getDomainClass())) {
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
        final Collection<EObject> elementsToListen = ToolFilterDescriptionListener.elementsToListen(interpreter, representation, filter.getElementsToListen());
        NotificationFilter notificationFilter = NotificationFilter.NOT_TOUCH;
        for (final EObject eObject : elementsToListen) {
            notificationFilter = notificationFilter.and(NotificationFilter.createNotifierFilter(eObject));
        }
        return notificationFilter;
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
