/*******************************************************************************
 * Copyright (c) 2013, 2015 THALES GLOBAL SERVICES.
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
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor.PropertyValueWrapper;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ui.tools.api.properties.SiriusExtensiblePropertySource;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.IPropertySourceProvider;
import org.eclipse.ui.views.properties.PropertySheetEntry;
import org.eclipse.ui.views.properties.tabbed.AdvancedPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * Properties section to display semantic element properties for
 * ModelExplorerView selection.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class SemanticPropertySection extends AdvancedPropertySection implements IPropertySourceProvider {

    @Override
    public void createControls(Composite parent, TabbedPropertySheetPage atabbedPropertySheetPage) {
        super.createControls(parent, atabbedPropertySheetPage);
        PropertySheetEntry root = new PropertySheetEntry();
        root.setPropertySourceProvider(this);
        page.setRootEntry(root);
    }

    @Override
    public IPropertySource getPropertySource(final Object object) {
        IPropertySource propSrc = null;
        Object selectedObject = object;
        if (selectedObject instanceof IAdaptable) {
            selectedObject = ((IAdaptable) selectedObject).getAdapter(EObject.class);
        }
        AdapterFactory af = SiriusEditPlugin.getPlugin().getItemProvidersAdapterFactory();
        if (af != null && (isSemanticEObject(selectedObject) || object instanceof PropertyValueWrapper)) {
            IItemPropertySource ips = (IItemPropertySource) af.adapt(selectedObject, IItemPropertySource.class);
            if (ips != null) {
                propSrc = new SiriusExtensiblePropertySource(selectedObject, ips);
            }
        }
        return propSrc;
    }

    private boolean isSemanticEObject(Object selectedObject) {
        boolean isSemanticEObject = false;
        if (selectedObject instanceof EObject) {
            EObject selectedEObject = (EObject) selectedObject;
            Session session = new EObjectQuery(selectedEObject).getSession();
            isSemanticEObject = session != null && session.getSemanticResources().contains(selectedEObject.eResource());
        }
        return isSemanticEObject;
    }
}
