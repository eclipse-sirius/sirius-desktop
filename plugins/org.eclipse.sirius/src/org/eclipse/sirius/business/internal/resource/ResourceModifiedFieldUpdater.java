/*******************************************************************************
 * Copyright (c) 2014, Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.resource;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Internal;
import org.eclipse.emf.transaction.Transaction;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.InternalTransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.emf.transaction.util.ValidateEditSupport;
import org.eclipse.sirius.common.tools.api.query.NotificationQuery;
import org.eclipse.sirius.ext.emf.tx.DelegatingValidateEditSupport;

/**
 * Class responsible to update {@link Resource#modified} field.
 * 
 * @author cedric
 */
public class ResourceModifiedFieldUpdater extends DelegatingValidateEditSupport {

    private InternalTransactionalEditingDomain domain;

    private Set<Resource> changedResources = new LinkedHashSet<Resource>();

    /**
     * Default constructor.
     * 
     * @param domain
     *            the {@link InternalTransactionalEditingDomain} on which
     *            changes occurs
     * @param delegate
     *            a possible delegate
     */
    public ResourceModifiedFieldUpdater(InternalTransactionalEditingDomain domain, ValidateEditSupport delegate) {
        super(delegate);
        this.domain = domain;
        TransactionalEditingDomain.DefaultOptions options = TransactionUtil.getAdapter(domain, TransactionalEditingDomain.DefaultOptions.class);
        if (options != null) {
            Map<Object, Object> aMap = new HashMap<Object, Object>();
            aMap.put(Transaction.OPTION_VALIDATE_EDIT, this);
            options.setDefaultTransactionOptions(aMap);
        }
    }

    @Override
    public void handleResourceChange(Resource resource, Notification notification) {
        super.handleResourceChange(resource, notification);
        if (!resource.isModified() && !isInLoad(resource) && !changedResources.contains(resource) && isResourceModelChange(notification)) {
            changedResources.add(resource);
        }
    }

    private boolean isResourceModelChange(Notification notification) {
        return (notification.getNotifier() instanceof EObject || notification.getFeatureID(null) == Resource.RESOURCE__CONTENTS) && !new NotificationQuery(notification).isTransientNotification();
    }

    private boolean isInLoad(Resource resource) {
        boolean isInLoad = resource instanceof Internal && ((Internal) resource).isLoading();
        return isInLoad;
    }

    @Override
    public void finalizeForCommit() {
        super.finalizeForCommit();
        if (!domain.getActiveTransaction().isReadOnly()) {
            for (Resource modifiedResource : changedResources) {
                modifiedResource.setModified(true);
            }
        }
        changedResources.clear();
    }

    @Override
    public void finalizeForRollback() {
        super.finalizeForRollback();
        changedResources.clear();
    }

}
