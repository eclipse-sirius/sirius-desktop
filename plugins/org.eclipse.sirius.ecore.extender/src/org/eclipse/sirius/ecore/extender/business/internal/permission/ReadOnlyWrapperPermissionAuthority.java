/*******************************************************************************
 * Copyright (c) 2009, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.ecore.extender.business.internal.permission;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.sirius.ecore.extender.business.api.permission.IAuthorityListener;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.LockStatus;

/**
 * A permission authority which will not allow write access depending on the activation of the read only mode.
 * 
 * @author mchauvin
 */
public class ReadOnlyWrapperPermissionAuthority extends ReadOnlyPermissionAuthority {

    private IPermissionAuthority wrappedAuthority;

    /***
     * Construct a new instance of eadOnlyPermissionAuthority by wrapping an existing one.
     * 
     * @param authority
     *            the existing authority to wrap. May not be null
     */
    public ReadOnlyWrapperPermissionAuthority(final IPermissionAuthority authority) {
        this.wrappedAuthority = authority;
    }

    public IPermissionAuthority getWrappedAuthority() {
        return wrappedAuthority;
    }

    @Override
    public List<EObject> getLockedObjects() {
        return Collections.unmodifiableList(wrappedAuthority.getLockedObjects());
    }

    @Override
    public boolean canCreateIn(final EObject obj) {
        if (super.canCreateIn(obj)) {
            return this.wrappedAuthority.canCreateIn(obj);
        }
        return false;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ecore.extender.business.internal.permission.DummyPermissionAuthority#canDeleteInstance(org.eclipse.emf.ecore.EObject)
     */
    @Override
    public boolean canDeleteInstance(final EObject target) {
        if (super.canDeleteInstance(target)) {
            return this.wrappedAuthority.canDeleteInstance(target);
        }
        return false;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ecore.extender.business.internal.permission.DummyPermissionAuthority#canEditFeature(org.eclipse.emf.ecore.EObject,
     *      java.lang.String)
     */
    @Override
    public boolean canEditFeature(final EObject obj, final String featureName) {
        if (super.canEditFeature(obj, featureName)) {
            return this.wrappedAuthority.canEditFeature(obj, featureName);
        }
        return false;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ecore.extender.business.internal.permission.DummyPermissionAuthority#canEditInstance(org.eclipse.emf.ecore.EObject)
     */
    @Override
    public boolean canEditInstance(final EObject obj) {
        if (super.canEditInstance(obj)) {
            return this.wrappedAuthority.canEditInstance(obj);
        }
        return false;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ecore.extender.business.internal.permission.ReadOnlyPermissionAuthority#checkApproval(org.eclipse.emf.ecore.EObject)
     */
    @Override
    protected boolean checkApproval(final EObject eObject) {
        final boolean approval = !isReadOnly();
        return approval;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority#setReportIssues(boolean)
     */
    @Override
    public void setReportIssues(final boolean report) {
        wrappedAuthority.setReportIssues(report);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority#notifyInstanceChange(org.eclipse.emf.ecore.EObject)
     */
    @Override
    public void notifyInstanceChange(final EObject instance) {
        wrappedAuthority.notifyInstanceChange(instance);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority#notifyInstanceDeletion(org.eclipse.emf.ecore.EObject)
     */
    @Override
    public void notifyInstanceDeletion(final EObject instance) {
        wrappedAuthority.notifyInstanceDeletion(instance);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority#notifyNewInstanceCreation(org.eclipse.emf.ecore.EObject)
     */
    @Override
    public void notifyNewInstanceCreation(final EObject instance) {
        wrappedAuthority.notifyNewInstanceCreation(instance);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ecore.extender.business.internal.permission.AbstractPermissionAuthority#addAuthorityListener(org.eclipse.sirius.ecore.extender.business.api.permission.IAuthorityListener)
     */
    @Override
    public void addAuthorityListener(final IAuthorityListener listener) {
        this.wrappedAuthority.addAuthorityListener(listener);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ecore.extender.business.internal.permission.AbstractPermissionAuthority#removeAuthorityListener(org.eclipse.sirius.ecore.extender.business.api.permission.IAuthorityListener)
     */
    @Override
    public void removeAuthorityListener(final IAuthorityListener listener) {
        this.wrappedAuthority.removeAuthorityListener(listener);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ecore.extender.business.internal.permission.AbstractPermissionAuthority#init(org.eclipse.emf.ecore.resource.ResourceSet)
     */
    @Override
    public void init(final ResourceSet set) {
        this.wrappedAuthority.init(set);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ecore.extender.business.internal.permission.AbstractPermissionAuthority#dispose(org.eclipse.emf.ecore.resource.ResourceSet)
     */
    @Override
    public void dispose(final ResourceSet set) {
        this.wrappedAuthority.dispose(set);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ecore.extender.business.internal.permission.AbstractPermissionAuthority#isChanged(org.eclipse.emf.ecore.EObject)
     */
    @Override
    public boolean isChanged(final EObject instance) {
        return this.wrappedAuthority.isChanged(instance);

    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ecore.extender.business.internal.permission.AbstractPermissionAuthority#isNewInstance(org.eclipse.emf.ecore.EObject)
     */
    @Override
    public boolean isNewInstance(final EObject instance) {
        return this.wrappedAuthority.isNewInstance(instance);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ecore.extender.business.internal.permission.AbstractPermissionAuthority#setListening(boolean)
     */
    @Override
    public void setListening(final boolean shouldListen) {
        this.wrappedAuthority.setListening(shouldListen);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyLock(Collection<? extends EObject> elements) {
        this.wrappedAuthority.notifyLock(elements);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyUnlock(Collection<? extends EObject> elements) {
        this.wrappedAuthority.notifyUnlock(elements);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LockStatus getLockStatus(EObject element) {
        return getWrappedAuthority().getLockStatus(element);
    }
}
