/*******************************************************************************
 * Copyright (c) 2009, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.tools.api.util;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.resource.Resource;

import com.google.common.collect.Iterables;

/**
 * A lazy cross referencer which does nothing until one of its method is called. <BR>
 * <BR>
 * This cross referencer also reacts to {@link EObject} removal from their
 * containing reference : it removes itself automatically from their adapters
 * and recursively from those of their contents. <BR>
 * <BR>
 * This cross referencer also provide a way to disable the resolution of proxy.
 * This can be useful to avoid reloading of a resource during the unloading of
 * it (caused by resolution of some proxy with crossReferencer).<BR>
 * <BR>
 * 
 * @see {@link org.eclipse.emf.transaction.impl.ResourceSetManager#observe(org.eclipse.emf.ecore.resource.Resource, Notification)}
 *      and message
 *      {@link org.eclipse.emf.transaction.internal.EMFTransactionStatusCodes.RELOAD_DURING_UNLOAD}
 *      this one.
 * 
 * @author mchauvin
 */
public class LazyCrossReferencer extends ECrossReferenceAdapterWithUnproxyCapability {
    private boolean initialized;

    private ECrossReferenceAdapterWithUnproxyCapability adapter = new InternalCrossReferencer();

    /**
     * Subclasses should override, and call super.initialize().
     */
    protected void initialize() {
        initialized = true;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.ecore.util.ECrossReferenceAdapter#dump()
     */
    @Override
    public void dump() {
        if (!initialized) {
            initialize();
        }
        adapter.dump();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.ecore.util.ECrossReferenceAdapter#getInverseReferences(org.eclipse.emf.ecore.EObject,
     *      boolean)
     */
    @Override
    public Collection<Setting> getInverseReferences(final EObject object, final boolean resolve) {
        if (!initialized) {
            initialize();
        }
        return adapter.getInverseReferences(object, resolve);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.ecore.util.ECrossReferenceAdapter#getInverseReferences(org.eclipse.emf.ecore.EObject)
     */
    @Override
    public Collection<Setting> getInverseReferences(final EObject object) {
        if (!initialized) {
            initialize();
        }
        return adapter.getInverseReferences(object);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.ecore.util.ECrossReferenceAdapter#getNonNavigableInverseReferences(org.eclipse.emf.ecore.EObject,
     *      boolean)
     */
    @Override
    public Collection<Setting> getNonNavigableInverseReferences(final EObject object, final boolean resolve) {
        if (!initialized) {
            initialize();
        }
        return adapter.getNonNavigableInverseReferences(object, resolve);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.ecore.util.ECrossReferenceAdapter#getNonNavigableInverseReferences(org.eclipse.emf.ecore.EObject)
     */
    @Override
    public Collection<Setting> getNonNavigableInverseReferences(final EObject object) {
        if (!initialized) {
            initialize();
        }
        return adapter.getNonNavigableInverseReferences(object);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.ecore.util.ECrossReferenceAdapter#getTarget()
     */
    @Override
    public Notifier getTarget() {
        if (!initialized) {
            initialize();
        }
        return adapter.getTarget();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.ecore.util.ECrossReferenceAdapter#isAdapterForType(java.lang.Object)
     */
    @Override
    public boolean isAdapterForType(final Object type) {
        if (!initialized) {
            initialize();
        }
        return adapter.isAdapterForType(type);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.ecore.util.ECrossReferenceAdapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
     */
    @Override
    public void notifyChanged(final Notification notification) {
        if (!initialized) {
            initialize();
        }
        adapter.notifyChanged(notification);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.ecore.util.ECrossReferenceAdapter#setTarget(org.eclipse.emf.common.notify.Notifier)
     */
    @Override
    public void setTarget(final Notifier target) {
        if (!initialized) {
            initialize();
        }
        adapter.setTarget(target);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.ecore.util.ECrossReferenceAdapter#unsetTarget(org.eclipse.emf.common.notify.Notifier)
     */
    @Override
    public void unsetTarget(final Notifier target) {
        if (!initialized) {
            initialize();
        }
        adapter.unsetTarget(target);
    }



    /**
     * Look at all EObjects of this resource and resolve proxy cross reference
     * that reference these EObjects.
     * 
     * @param resource
     *            Each cross reference pointing to a proxy of this
     *            <code>resource</code> will be resolved.
     */
    @Override
    public void resolveProxyCrossReferences(Resource resource) {
        if (initialized) {
            // The resolution of proxy is called only is the cross-referencer
            // has already been initialized.
            adapter.resolveProxyCrossReferences(resource);
        }
    }

    /**
     * @see LazyCrossReferencer. This class is the delegated adapter.
     */
    private class InternalCrossReferencer extends ECrossReferenceAdapterWithUnproxyCapability {

        /**
         * {@inheritDoc}
         */
        @Override
        protected void handleContainment(Notification notification) {
            deregisterDeletedElements(notification);

            super.handleContainment(notification);
        }

        /**
         * This method removes the current cross referencer adapter from
         * adapters of removed elements. The removeAdapter method propagate the
         * removal to all contents of its parameter.
         * 
         * @param notification
         *            a containment notification
         */
        private void deregisterDeletedElements(Notification notification) {
            switch (notification.getEventType()) {

            case Notification.UNSET:
            case Notification.SET:
            case Notification.REMOVE:
                Object oldValue = notification.getOldValue();
                if (oldValue instanceof Notifier) {
                    removeAdapter((Notifier) oldValue);
                }
                break;

            case Notification.REMOVE_MANY:
                for (Notifier oldVal : Iterables.filter((Collection<?>) notification.getOldValue(), Notifier.class)) {
                    removeAdapter(oldVal);
                }
                break;

            default:
                break;
            }
        }
    };
}
