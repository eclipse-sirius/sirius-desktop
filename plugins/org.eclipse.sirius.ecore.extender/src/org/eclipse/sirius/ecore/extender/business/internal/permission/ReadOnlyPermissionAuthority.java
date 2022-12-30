/*******************************************************************************
 * Copyright (c) 2009-2023 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.ecore.extender.business.api.permission.IAuthorityListener;
import org.eclipse.sirius.ecore.extender.business.api.permission.LockStatus;

/**
 * A permission authority which will not allow write access depending on a
 * boolean. Do not extend.
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

    @Override
    public boolean canCreateIn(final EObject obj) {
        return checkApproval(obj);
    }

    @Override
    public boolean canDeleteInstance(final EObject target) {
        return checkApproval(target);
    }

    @Override
    public boolean canEditFeature(final EObject obj, final String featureName) {
        return checkApproval(obj);
    }

    @Override
    public boolean canEditInstance(final EObject obj) {
        return checkApproval(obj);
    }

    @Override
    public void setReportIssues(final boolean report) {
    }

    @Override
    public void notifyInstanceChange(final EObject instance) {

    }

    @Override
    public void notifyInstanceDeletion(final EObject instance) {
    }

    @Override
    public void notifyNewInstanceCreation(final EObject instance) {
    }

    @SuppressWarnings("unchecked")
    @Override
    public void notifyLock(Collection<? extends EObject> elements) {
        for (IAuthorityListener listener : listeners) {
            listener.notifyIsLocked((Collection<EObject>) elements);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void notifyUnlock(Collection<? extends EObject> elements) {
        for (IAuthorityListener listener : listeners) {
            listener.notifyIsReleased((Collection<EObject>) elements);
        }
    }

    @Override
    public LockStatus getLockStatus(EObject element) {
        return canEditInstance(element) ? LockStatus.NOT_LOCKED : LockStatus.LOCKED_BY_OTHER;
    }

    @Override
    public boolean isFrozen(EObject eObject) {
        return false;
    }
    
}
