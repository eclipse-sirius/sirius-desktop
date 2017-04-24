/*******************************************************************************
 * Copyright (c) 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.properties.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.eef.core.api.EditingContextAdapter;
import org.eclipse.eef.core.api.LockStatusChangeEvent;
import org.eclipse.eef.core.api.LockStatusChangeEvent.LockStatus;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;

/**
 * Wrapper for the {@link EditingContextAdapter} used to trigger the refresh of the dialogs and wizards when a changed
 * is performed (regardless of its impact).
 * 
 * @author sbegaudeau
 */
public class EditingContextAdapterWrapper implements EditingContextAdapter {

    /**
     * The delegate.
     */
    private final EditingContextAdapter delegate;

    /**
     * The consumer executed when a change is performed.
     */
    private final List<Consumer<IStatus>> performedModelChangeConsumers = new ArrayList<>();

    /**
     * The constructor.
     * 
     * @param delegate
     *            The delegate
     */
    public EditingContextAdapterWrapper(EditingContextAdapter delegate) {
        this.delegate = delegate;
    }

    @Override
    public IStatus performModelChange(Runnable operation) {
        IStatus status = this.delegate.performModelChange(operation);
        this.performedModelChangeConsumers.forEach(consumer -> consumer.accept(status));
        return status;
    }

    /**
     * Adds a new consumer which will be executed when a change is performed.
     * 
     * @param consumer
     *            The consumer
     */
    public void addPerformedModelChangeConsumer(Consumer<IStatus> consumer) {
        this.performedModelChangeConsumers.add(consumer);
    }

    /**
     * Removes a consumer which is executed when a change is performed.
     * 
     * @param consumer
     *            The consumer
     */
    public void removePerformedModelChangeConsumer(Consumer<IStatus> consumer) {
        this.performedModelChangeConsumers.remove(consumer);
    }

    @Override
    public void registerModelChangeListener(Consumer<List<Notification>> listener) {
        this.delegate.registerModelChangeListener(listener);
    }

    @Override
    public void unregisterModelChangeListener() {
        this.delegate.unregisterModelChangeListener();
    }

    @Override
    public EditingDomain getEditingDomain() {
        return this.delegate.getEditingDomain();
    }

    @Override
    public void addLockStatusChangedListener(Consumer<Collection<LockStatusChangeEvent>> listener) {
        this.delegate.addLockStatusChangedListener(listener);
    }

    @Override
    public void removeLockStatusChangedListener(Consumer<Collection<LockStatusChangeEvent>> listener) {
        this.delegate.removeLockStatusChangedListener(listener);
    }

    @Override
    public LockStatus getLockStatus(EObject obj) {
        return this.delegate.getLockStatus(obj);
    }

}
