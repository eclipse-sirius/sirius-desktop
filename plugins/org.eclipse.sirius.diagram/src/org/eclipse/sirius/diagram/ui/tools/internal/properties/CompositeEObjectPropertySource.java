/*******************************************************************************
 * Copyright (c) 2007, 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.properties;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.diagram.part.SiriusDiagramEditor;
import org.eclipse.sirius.diagram.part.SiriusDiagramEditorPlugin;
import org.eclipse.sirius.diagram.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.diagram.tools.api.requests.RequestConstants;
import org.eclipse.sirius.ui.tools.api.properties.AbstractCompositeEObjectPropertySource;
import org.eclipse.ui.IEditorPart;

/**
 * Specialization for the manage of Diagram elements.
 * 
 * @author ymortier
 */
public class CompositeEObjectPropertySource extends AbstractCompositeEObjectPropertySource {

    /**
     * Creates a new <code>CompositeEObjectPropertySource</code>.
     */
    public CompositeEObjectPropertySource() {
        super();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.lang.Object,
     *      java.lang.Object)
     */
    @Override
    public void setPropertyValue(final Object id, final Object value) {
        super.setPropertyValue(id, value);
        final Identifier identifier = (Identifier) id;
        /*
         * FIXME CBR : the following code handle the case where the reference is
         * a "Many" one in refreshing
         */
        if (identifier.getId() instanceof String) {
            if (identifier.getEObject().eClass().getEStructuralFeature(((String) identifier.getId()).toLowerCase()) != null) {
                final EStructuralFeature feat = identifier.getEObject().eClass().getEStructuralFeature(((String) identifier.getId()).toLowerCase());
                if (feat instanceof EReference && ((EReference) feat).isMany()) {
                    final IEditorPart part = EclipseUIUtil.getActiveEditor();
                    if (part instanceof SiriusDiagramEditor) {
                        ((SiriusDiagramEditor) part).getDiagramEditPart().performRequest(new GroupRequest(RequestConstants.REQ_REFRESH_VIEWPOINT));
                    }
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.tools.api.properties.AbstractCompositeEObjectPropertySource#getItemProvidersAdapterFactory()
     */
    @Override
    protected AdapterFactory getItemProvidersAdapterFactory() {
        AdapterFactory adapterFactory = null;
        final IEditorPart part = EclipseUIUtil.getActiveEditor();
        if (part instanceof DDiagramEditor) {
            adapterFactory = ((DDiagramEditor) part).getAdapterFactory();
        } else {
            adapterFactory = SiriusDiagramEditorPlugin.getInstance().getItemProvidersAdapterFactory();
        }
        return adapterFactory;
    }
}
