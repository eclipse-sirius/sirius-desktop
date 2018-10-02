/*******************************************************************************
 * Copyright (c) 2009, 2014 Obeo and others. All rights
 * reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this
t https://www.eclipse.org/legal/epl-2.0/
t
t SPDX-License-Identifier: EPL-2.0
 *
 * Contributors: Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.editor;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.sirius.business.api.helper.SiriusUtil;

/**
 * A {@link org.eclipse.emf.transaction.ResourceSetListener} to reacts of VSM
 * update.
 *
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public final class DescriptionFileChangedNotifier extends ResourceSetListenerImpl {

    private AbstractDTableViewerManager abstractDTableViewerManager;

    /**
     * Default constructor.
     *
     * @param abstractDTableViewerManager
     *            the {@link AbstractDTableViewerManager}.
     */
    public DescriptionFileChangedNotifier(AbstractDTableViewerManager abstractDTableViewerManager) {
        super(NotificationFilter.createFeatureFilter(Resource.class, Resource.RESOURCE__IS_LOADED).or(NotificationFilter.createFeatureFilter(Resource.class, Resource.RESOURCE__IS_MODIFIED)));
        this.abstractDTableViewerManager = abstractDTableViewerManager;
        abstractDTableViewerManager.getEditingDomain().addResourceSetListener(this);
    }

    @Override
    public boolean isPostcommitOnly() {
        return true;
    }

    @Override
    public void resourceSetChanged(ResourceSetChangeEvent event) {
        if (hasNotificationAboutVSMReload(event)) {
            abstractDTableViewerManager.setDescriptionFileChanged(true);
        }
    }

    private boolean hasNotificationAboutVSMReload(ResourceSetChangeEvent event) {
        boolean hasNotificationAboutVSMReload = false;
        for (Notification notification : event.getNotifications()) {
            if (notification.getNotifier() instanceof Resource) {
                Resource resource = (Resource) notification.getNotifier();
                // Indicates that at least one description file has changed or
                // was
                // reloaded
                if (SiriusUtil.DESCRIPTION_MODEL_EXTENSION.equals(resource.getURI().fileExtension())
                        && (notification.getFeatureID(Resource.class) == Resource.RESOURCE__IS_MODIFIED || notification.getFeatureID(Resource.class) == Resource.RESOURCE__IS_LOADED)) {
                    hasNotificationAboutVSMReload = true;
                    break;
                }
            }
        }
        return hasNotificationAboutVSMReload;
    }

    /**
     * Dispose this {@link DescriptionFileChangedNotifier}.
     */
    public void dispose() {
        if (getTarget() != null) {
            getTarget().removeResourceSetListener(this);
        }
    }
}
