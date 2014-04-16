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
package org.eclipse.sirius.business.api.session;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.NotificationFilter;

/**
 * The session event broker allows clients to do trigger specific operations
 * during a local transaction application. The corresponding changes are
 * appended to the transaction.
 * 
 * @author cbrun
 * 
 */
public interface SessionEventBroker {
    /**
     * Add a new trigger listening to local changes on the given element.
     * 
     * @param element
     *            listened element.
     * @param trigger
     *            trigger to launch with a feature changes.
     */
    void addLocalTrigger(EObject element, ModelChangeTrigger trigger);

    /**
     * Add a new trigger listening to a scope (and not just elements).
     * 
     * @param scope
     *            scope which should cause the trigger to get fired.
     * @param trigger
     *            trigger to fire.
     * @since 1.0.0M7
     */
    void addLocalTrigger(NotificationFilter scope, ModelChangeTrigger trigger);

    /**
     * Add a new trigger listening to local changes on the given element.
     * 
     * @param element
     *            listened element.
     * @param feature
     *            listened feature.
     * @param trigger
     *            trigger to launch with a feature changes.
     */
    void addLocalTrigger(EObject element, EStructuralFeature feature, ModelChangeTrigger trigger);

    /**
     * Remove the local trigger.
     * 
     * @param trigger
     *            trigger to remove.
     */
    void removeLocalTrigger(ModelChangeTrigger trigger);

    /**
     * Dispose this SessionEventBroker by removing all this
     * {@link ModelChangeTrigger}.
     */
    void dispose();

}
