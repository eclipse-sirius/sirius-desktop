/*******************************************************************************
 * Copyright (c) 2007, 2022 THALES GLOBAL SERVICES.
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.sirius.ecore.extender.business.api.permission.IAuthorityListener;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.LockStatus;

/**
 * Permission authority always telling YES to every access.
 * 
 * @author cbrun
 * 
 */
public class DummyPermissionAuthority implements IPermissionAuthority {
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canCreateIn(final EObject obj) {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canEditFeature(final EObject obj, final String featureName) {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canEditInstance(final EObject obj) {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dispose(final ResourceSet set) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(final ResourceSet set) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isChanged(final EObject instance) {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isNewInstance(final EObject instance) {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyInstanceChange(final EObject instance) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyInstanceDeletion(final EObject instance) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyNewInstanceCreation(final EObject instance) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addAuthorityListener(final IAuthorityListener listener) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeAuthorityListener(final IAuthorityListener listener) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setReportIssues(final boolean report) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setListening(final boolean shouldListen) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canDeleteInstance(final EObject target) {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyLock(Collection<? extends EObject> elements) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyUnlock(Collection<? extends EObject> elements) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LockStatus getLockStatus(EObject element) {
        return canEditInstance(element) ? LockStatus.NOT_LOCKED : LockStatus.LOCKED_BY_OTHER;
    }

    @Override
    public List<EObject> getLockedObjects() {
        return Collections.unmodifiableList(new ArrayList<EObject>());
    }

}
