/*******************************************************************************
 * Copyright (c) 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.views.modelexplorer.property;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.DecoratingLabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.graphics.Image;

/**
 * LabelProvider for properties contribution.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class SiriusPropertiesLabelProvider extends DecoratingLabelProvider {

    /**
     * Default constructor.
     */
    public SiriusPropertiesLabelProvider() {
        super(new AdapterFactoryLabelProvider(SiriusEditPlugin.getPlugin().getItemProvidersAdapterFactory()), null);
    }

    /**
     * {@inheritDoc}
     */
    public String getText(Object element) {
        String text = ""; //$NON-NLS-1$
        EObject selected = adapt(element);
        if (selected != null && selected.eResource() != null) {
            if (selected instanceof DSemanticDecorator && ((DSemanticDecorator) selected).getTarget() != null) {
                EObject eObject = ((DSemanticDecorator) selected).getTarget();
                AdapterFactory adapterFactory = SiriusEditPlugin.getPlugin().getItemProvidersAdapterFactory();
                IItemLabelProvider itemLabelProvider = (IItemLabelProvider) adapterFactory.adapt(eObject, IItemLabelProvider.class);
                text = itemLabelProvider.getText(eObject);
            } else {
                text = super.getText(selected);
            }
        }
        return text;
    }

    /**
     * {@inheritDoc}
     */
    public Image getImage(Object element) {
        Image image = null;
        EObject selected = adapt(element);
        if (selected != null && selected.eResource() != null) {
            if (selected instanceof DSemanticDecorator && ((DSemanticDecorator) selected).getTarget() != null) {
                EObject eObject = ((DSemanticDecorator) selected).getTarget();
                image = SiriusEditPlugin.getPlugin().getImage(SiriusEditPlugin.getPlugin().getItemImageDescriptor(eObject));
            } else {
                image = super.getImage(selected);
            }
        }
        return image;
    }

    private EObject adapt(Object object) {
        EObject eObject = null;
        if (object instanceof IAdaptable) {
            eObject = (EObject) ((IAdaptable) object).getAdapter(EObject.class);
        } else if (object instanceof EObject) {
            eObject = (EObject) object;
        } else if (object instanceof IStructuredSelection) {
            IStructuredSelection structuredSelection = (IStructuredSelection) object;
            Object firstElement = structuredSelection.getFirstElement();
            if (firstElement instanceof EObject) {
                eObject = (EObject) firstElement;
            }
        }
        return eObject;
    }

}
