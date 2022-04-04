/*******************************************************************************
 * Copyright (c) 2008, 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.ui.tools.internal.views.common;

import java.text.MessageFormat;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.common.ui.tools.api.view.common.item.ItemDecorator;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.ui.tools.api.views.common.item.ItemWrapper;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.PlatformUI;

import com.google.common.collect.Iterables;

/**
 * Label provider used for the session .
 *
 * @author cedric
 *
 */
public class SessionLabelProvider extends AdapterFactoryLabelProvider {
    /**
     * Create a new session label provider.
     *
     * @param adapterFactory
     *            the adapter factory to delegate to.
     */
    public SessionLabelProvider(final AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    @Override
    public String getText(final Object object) {
        String text = null;
        if (object instanceof ItemDecorator) {
            text = ((ItemDecorator) object).getText();
        } else {
            Object item = object;
            if (object instanceof ItemWrapper) {
                item = ((ItemWrapper) object).getWrappedObject();
            }

            if (item instanceof Resource && safeResource((Resource) item)) {
                Resource res = (Resource) item;
                if (res.getURI() != null && res.getURI().lastSegment() != null) {
                    return MessageFormat.format("{0} - [{1}]", URI.decode(res.getURI().lastSegment()), URI.decode(res.getURI().toString())); //$NON-NLS-1$
                }
            } else {
                // TODO remove this try/catch once the offline mode
                // will be supported
                try {
                    text = super.getText(item);
                } catch (IllegalStateException e) {
                    SiriusEditPlugin.getPlugin().getLog().log(new Status(IStatus.WARNING, SiriusPlugin.ID, Messages.SessionLabelProvider_errorReadingModel));
                }
            }
        }
        return text;
    }

    private boolean safeResource(Resource res) {
        boolean safe = false;
        // Do not provide text for non MM resources without resource set
        if (res.getResourceSet() != null) {
            safe = true;
        } else {
            // Check EPackage Registry for MM
            for (EObject obj : Iterables.filter(EPackage.Registry.INSTANCE.values(), EObject.class)) {
                if (obj.eResource().equals(res)) {
                    safe = true;
                    break;
                }
            }
        }
        return safe;
    }

    @Override
    public Image getImage(final Object object) {
        Image image = null;
        if (object instanceof ItemDecorator) {
            image = ((ItemDecorator) object).getImage();
        } else if (object instanceof ItemWrapper) {
            image = super.getImage(((ItemWrapper) object).getWrappedObject());
        } else if (object instanceof Resource && safeResource((Resource) object)) {
            Resource resource = (Resource) object;
            String fileName = resource.getURI().lastSegment();
            ImageDescriptor imageDescriptor = PlatformUI.getWorkbench().getEditorRegistry().getImageDescriptor(fileName);
            if (imageDescriptor != null) {
                image = SiriusEditPlugin.getPlugin().getImage(imageDescriptor);
            }
        } else {
            image = super.getImage(object);
        }
        return image;
    }
}
