/*******************************************************************************
 * Copyright (c) 2007, 2011 THALES GLOBAL SERVICES.
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
    public boolean canCreateIn(final EObject obj) {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public boolean canEditFeature(final EObject obj, final String featureName) {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public boolean canEditInstance(final EObject obj) {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public void dispose(final ResourceSet set) {
    }

    /**
     * {@inheritDoc}
     */
    public void init(final ResourceSet set) {
    }

    /**
     * {@inheritDoc}
     */
    public boolean isChanged(final EObject instance) {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isNewInstance(final EObject instance) {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public void notifyInstanceChange(final EObject instance) {
    }

    /**
     * {@inheritDoc}
     */
    public void notifyInstanceDeletion(final EObject instance) {
    }

    /**
     * {@inheritDoc}
     */
    public void notifyNewInstanceCreation(final EObject instance) {
    }

    /**
     * {@inheritDoc}
     */
    public void addAuthorityListener(final IAuthorityListener listener) {

    }

    /**
     * {@inheritDoc}
     */
    public void removeAuthorityListener(final IAuthorityListener listener) {

    }

    /**
     * {@inheritDoc}
     */
    public void setReportIssues(final boolean report) {
    }

    /**
     * {@inheritDoc}
     */
    public void setListening(final boolean shouldListen) {
    }

    /**
     * {@inheritDoc}
     */
    public boolean canDeleteInstance(final EObject target) {
        return true;
    }

    /**
     * {@inheritDoc}
     */
	public void notifyLock(Collection<? extends EObject> elements) {
		
	}

	/**
	 * {@inheritDoc}
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
