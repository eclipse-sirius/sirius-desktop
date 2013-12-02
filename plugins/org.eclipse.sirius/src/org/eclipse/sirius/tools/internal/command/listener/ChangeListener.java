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
package org.eclipse.sirius.tools.internal.command.listener;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.sirius.common.tools.api.util.SmartAdapter;
import org.eclipse.sirius.tools.api.command.listener.IChangeListener;
import org.eclipse.sirius.tools.api.command.listener.TriggerOperation;

import com.google.common.collect.Sets;

/**
 * A listener which record created, modified and deleted elements and which is
 * able to launch a trigger operation with these elements as parameters.
 * 
 * @author mchauvin
 * @since 0.9.0
 */
public final class ChangeListener extends SmartAdapter implements IChangeListener {

    private Collection<Object> deletedElements;

    private Collection<Object> modifiedElements;

    private Collection<Object> createdElements;

    private TriggerOperation triggerOperation;

    /** counter that tracs the recursive depth of the activate() method. */
    private int activateRecursiveCount;

    /**
     * Activate this listener.
     */
    public void activate() {
        activateRecursiveCount++;
        if (activateRecursiveCount == 1) {
            init();
        }
    }

    /**
     * Deactivate this listener.
     */
    public void deactivate() {
        activateRecursiveCount--;
    }

    /**
     * Launches the trigger operation if there is one.
     */
    public void launchTriggerOperation() {
        if (triggerOperation != null) {
            triggerOperation.run(createdElements, modifiedElements, deletedElements);
        }
        dispose();
    }

    /**
     * Set the trigger operation.
     * 
     * @param operation
     *            the trigger operation
     */
    public void setTriggerOperation(TriggerOperation operation) {
        this.triggerOperation = operation;
    }

    /**
     * Unset the trigger operation.
     */
    public void unsetTriggerOperation() {
        this.triggerOperation = null;
    }

    private void init() {
        deletedElements = Sets.newLinkedHashSet();
        createdElements = Sets.newLinkedHashSet();
        modifiedElements = Sets.newLinkedHashSet();
    }

    private void dispose() {
        deletedElements = null;
        createdElements = null;
        modifiedElements = null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.ecore.util.EContentAdapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
     */
    @Override
    public void notifyChanged(final Notification notification) {
        if (isActivated()) {
            switch (notification.getEventType()) {
            case Notification.SET:
            case Notification.ADD:
            case Notification.ADD_MANY:
                if (isACreation(notification)) {
                    final Object newValue = notification.getNewValue();
                    createdElements.add(newValue);
                }
                modifiedElements.add(notification.getNotifier());
                break;
            case Notification.MOVE:
                modifiedElements.add(notification.getNotifier());
                break;
            case Notification.UNSET:
            case Notification.REMOVE:
            case Notification.REMOVE_MANY:
                if (isADeletion(notification)) {
                    final Object oldValue = notification.getOldValue();
                    deletedElements.add(oldValue);
                }
                modifiedElements.add(notification.getNotifier());
                break;
            default:
                break;
            }
        }
        super.notifyChanged(notification);
    }

    private boolean isADeletion(final Notification notification) {
        boolean isADeletion = false;
        final Object f = notification.getFeature();

        if (f instanceof EReference && !((EReference) f).isContainment()) {
            return isADeletion;
        } else {
            isADeletion = true;
        }
        return isADeletion;
    }

    private boolean isACreation(final Notification notification) {
        boolean isACreation = false;
        final Object f = notification.getFeature();

        if (f instanceof EReference && !((EReference) f).isContainment()) {
            return isACreation;
        } else if (notification.getOldValue() == null) {
            final Object newValue = notification.getNewValue();
            if (deletedElements.contains(newValue)) {
                deletedElements.remove(newValue);
                isACreation = false;
            } else {
                isACreation = true;
            }
        }
        return isACreation;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tools.api.command.listener.IChangeListener#isActivated()
     */
    public boolean isActivated() {
        return activateRecursiveCount != 0;
    }
}
