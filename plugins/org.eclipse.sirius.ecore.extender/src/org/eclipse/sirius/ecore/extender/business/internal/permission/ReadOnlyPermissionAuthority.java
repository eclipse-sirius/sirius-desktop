/*******************************************************************************
 * Copyright (c) 2009, 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ecore.extender.business.internal.permission;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.sirius.ecore.extender.business.api.permission.LockStatus;

/**
 * A permission authority which will not allow write access depending on a boolean.
 * Do not extend.
 * 
 * @author mchauvin
 */
public class ReadOnlyPermissionAuthority extends AbstractPermissionAuthority {

    private boolean activation;

    /**
     * Construct a new instance of ReadOnlyPermissionAuthority without wrapping
     * an existing authority.
     */
    public ReadOnlyPermissionAuthority() {
    }

    /**
     * Activate the read only behavior, may not be canceled.
     */
    public void activate() {
        activation = true;
    }

    protected boolean isReadOnly() {
        return activation;
    }

    private void notify(final boolean approval, final EObject obj) {
        if (approval) {
            if (isLocked(obj)) {
                releaseFromLockedAndNotify(obj);
            }
        } else {
            if (!isLocked(obj)) {
                storeAsLockedAndNotify(obj);
            }
        }
    }

    /**
     * Check approval for editing this object.
     * 
     * @param eObject
     *            the object
     * @return <code>true</code>if approval, <code>false</code> otherwise
     */
    protected boolean checkApproval(final EObject eObject) {
        final boolean approval = !isReadOnly();
        notify(approval, eObject);
        return approval;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ecore.extender.business.internal.permission.DummyPermissionAuthority#canCreateIn(org.eclipse.emf.ecore.EObject)
     */
    public boolean canCreateIn(final EObject obj) {
        return checkApproval(obj);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ecore.extender.business.internal.permission.DummyPermissionAuthority#canDeleteInstance(org.eclipse.emf.ecore.EObject)
     */
    public boolean canDeleteInstance(final EObject target) {
        return checkApproval(target);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ecore.extender.business.internal.permission.DummyPermissionAuthority#canEditFeature(org.eclipse.emf.ecore.EObject,
     *      java.lang.String)
     */
    public boolean canEditFeature(final EObject obj, final String featureName) {
        return checkApproval(obj);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ecore.extender.business.internal.permission.DummyPermissionAuthority#canEditInstance(org.eclipse.emf.ecore.EObject)
     */
    public boolean canEditInstance(final EObject obj) {
        return checkApproval(obj);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority#setReportIssues(boolean)
     */
    public void setReportIssues(final boolean report) {
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority#notifyInstanceChange(org.eclipse.emf.ecore.EObject)
     */
    public void notifyInstanceChange(final EObject instance) {
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority#notifyInstanceDeletion(org.eclipse.emf.ecore.EObject)
     */
    public void notifyInstanceDeletion(final EObject instance) {
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority#notifyNewInstanceCreation(org.eclipse.emf.ecore.EObject)
     */
    public void notifyNewInstanceCreation(final EObject instance) {
    }

    /**
     * {@inheritDoc}
     * @see org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority#notifyLock(java.util.Collection)
     */
	public void notifyLock(Collection<? extends EObject> elements) {
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority#notifyUnlock(java.util.Collection)
	 */
	public void notifyUnlock(Collection<? extends EObject> elements) {
	}

    /**
     * {@inheritDoc}
     */
    public LockStatus getLockStatus(EObject element) {
        return canEditInstance(element) ? LockStatus.NOT_LOCKED : LockStatus.LOCKED_BY_OTHER;
    }

}
