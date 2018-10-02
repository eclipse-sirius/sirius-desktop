/**
 * Copyright (c) 2013, 2014 THALES GLOBAL SERVICES
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *      Obeo - Initial API and implementation
 */
package org.eclipse.sirius.tests.swtbot.support.api.condition;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.sirius.ecore.extender.business.api.permission.IAuthorityListener;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;

/**
 * SWTBot condition to check that the current permission authority notify its
 * listeners thaht some elements were locked/unlocked.
 * 
 * @author mporhel
 */
public class NotifiedFromLockOperationCondition extends DefaultCondition implements IAuthorityListener {

    private boolean notified;

    private final IPermissionAuthority permissionAuthority;

    /**
     * Constructor.
     * 
     * @param set
     *            the current resource set.
     */
    public NotifiedFromLockOperationCondition(ResourceSet set) {
        super();
        permissionAuthority = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(set);
        permissionAuthority.addAuthorityListener(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean test() throws Exception {
        return notified;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getFailureMessage() {
        return "No lock/unlock notification launched by the permission authority to its listeners";
    }

    private void notified() {
        notified = true;
        permissionAuthority.removeAuthorityListener(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyIsLocked(EObject instance) {
        notified();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyIsReleased(EObject instance) {
        notified();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyIsLocked(Collection<EObject> instances) {
        notified();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyIsReleased(Collection<EObject> instances) {
        notified();
    }
};
