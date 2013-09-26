/*******************************************************************************
 * Copyright (c) 2009, 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.tools.internal.presentation;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.sirius.ui.business.api.template.RepresentationTemplateEditManager;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.RepresentationTemplate;

/**
 * Class responsible for triggering the representation templates updates when
 * something change in the model.
 * 
 * @author cbrun
 * 
 */
public class RepresentationTemplateUpdateTrigger extends EContentAdapter {
    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyChanged(Notification notification) {
        super.notifyChanged(notification);
        RepresentationTemplate template = lookForParentTemplateInChanges(notification);
        if (template != null) {
            RepresentationTemplateEditManager.INSTANCE.update(template);
        }

    }

    private RepresentationTemplate lookForParentTemplateInChanges(Notification msg) {
        RepresentationTemplate found = null;
        if (!msg.isTouch() && msg.getNotifier() instanceof EObject) {

            EObject current = (EObject) msg.getNotifier();
            if (elementIsATemplateAndChangeIsNotAboutComputedFeatures(msg, current)) {
                found = (RepresentationTemplate) current;
            }
            while (current != null && found == null) {
                if (current.eContainer() instanceof RepresentationTemplate) {
                    if (!objectIsContainedInComputedFeature(current)) {
                        found = (RepresentationTemplate) current.eContainer();
                    }
                }
                current = current.eContainer();
            }
        }
        return found;
    }

    private boolean objectIsContainedInComputedFeature(EObject current) {
        return current.eContainingFeature() == DescriptionPackage.eINSTANCE.getRepresentationTemplate_OwnedRepresentations();
    }

    private boolean elementIsATemplateAndChangeIsNotAboutComputedFeatures(Notification msg, EObject current) {
        return current instanceof RepresentationTemplate && msg.getFeature() != DescriptionPackage.eINSTANCE.getRepresentationTemplate_OwnedRepresentations();
    }
}
