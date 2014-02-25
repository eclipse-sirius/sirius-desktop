/*******************************************************************************
 * Copyright (c) 2007, 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.internal.sheet;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.DecoratingLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.common.ui.business.api.views.properties.tabbed.LabelProviderProviderService;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.swt.graphics.Image;

/**
 * @was-generated
 */
public class SiriusSheetLabelProvider extends DecoratingLabelProvider {

    private LabelProviderProviderService labelProviderProviderService;

    /**
     * @was-generated
     */
    public SiriusSheetLabelProvider() {
        super(new AdapterFactoryLabelProvider(DiagramUIPlugin.getPlugin().getItemProvidersAdapterFactory()), null);
        labelProviderProviderService = new LabelProviderProviderService();
    }

    /**
     * @was-generated NOT
     */
    public String getText(Object element) {
        String text = null;
        ILabelProvider firstProvidedLabelProvider = labelProviderProviderService.getFirstProvidedLabelProvider(element);
        if (firstProvidedLabelProvider != null) {
            text = firstProvidedLabelProvider.getText(element);
        } else {
            Object selected = unwrap(element);
            // begin change YMO.
            if (selected instanceof DSemanticDecorator && ((DSemanticDecorator) selected).getTarget() != null) {
                EObject eObject = ((DSemanticDecorator) selected).getTarget();
                AdapterFactory adapterFactory = DiagramUIPlugin.getPlugin().getItemProvidersAdapterFactory();
                IItemLabelProvider itemLabelProvider = (IItemLabelProvider) adapterFactory.adapt(eObject, IItemLabelProvider.class);
                text = itemLabelProvider.getText(eObject);
            } else {
                text = super.getText(selected);
            }
        }
        return text;
    }

    /**
     * @not-generated
     */
    public Image getImage(Object element) {
        Image image = null;
        ILabelProvider firstProvidedLabelProvider = labelProviderProviderService.getFirstProvidedLabelProvider(element);
        if (firstProvidedLabelProvider != null) {
            image = firstProvidedLabelProvider.getImage(element);
        } else {
            Object selected = unwrap(element);
            if (selected instanceof DSemanticDecorator && ((DSemanticDecorator) selected).getTarget() != null) {
                EObject eObject = ((DSemanticDecorator) selected).getTarget();
                image = DiagramUIPlugin.getPlugin().getImage(DiagramUIPlugin.getPlugin().getItemImageDescriptor(eObject));
            } else {
                image = super.getImage(selected);
            }
        }
        return image;
    }

    /**
     * @was-generated NOT
     */
    private Object unwrap(Object element) {
        if (element instanceof IStructuredSelection) {
            return unwrap(((IStructuredSelection) element).getFirstElement());
        }
        if (element instanceof EditPart) {
            return unwrapEditPart((EditPart) element);
        }
        if (element instanceof IAdaptable) {
            View view = (View) ((IAdaptable) element).getAdapter(View.class);
            if (view != null) {
                return unwrapView(view);
            }
        }
        return element;
    }

    /**
     * @was-generated
     */
    private Object unwrapEditPart(EditPart p) {
        if (p.getModel() instanceof View) {
            return unwrapView((View) p.getModel());
        }
        return p.getModel();
    }

    /**
     * @was-generated
     */
    private Object unwrapView(View view) {
        return view.getElement() == null ? view : view.getElement();
    }

}
