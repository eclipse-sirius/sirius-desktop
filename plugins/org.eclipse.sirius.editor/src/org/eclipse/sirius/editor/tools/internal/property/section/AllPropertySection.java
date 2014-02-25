/*******************************************************************************
 * Copyright (c) 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.tools.internal.property.section;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.provider.PropertySource;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.sirius.editor.properties.sections.common.AbstractViewpointPropertySection;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.featureExtensions.FeatureExtensionsUIManager;
import org.eclipse.sirius.viewpoint.description.audit.provider.AuditItemProviderAdapterFactory;
import org.eclipse.sirius.viewpoint.description.provider.DescriptionItemProviderAdapterFactory;
import org.eclipse.sirius.viewpoint.description.style.provider.StyleItemProviderAdapterFactory;
import org.eclipse.sirius.viewpoint.description.tool.provider.ToolItemProviderAdapterFactory;
import org.eclipse.sirius.viewpoint.description.validation.provider.ValidationItemProviderAdapterFactory;
import org.eclipse.sirius.viewpoint.provider.ViewpointItemProviderAdapterFactory;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.IPropertySourceProvider;
import org.eclipse.ui.views.properties.PropertySheetEntry;
import org.eclipse.ui.views.properties.PropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * A property section which displays the EMF properties view.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class AllPropertySection extends AbstractViewpointPropertySection implements IPropertySourceProvider {
    /**
     * the content property sheet page witch manages SheetEntry for this
     * section.
     */
    protected PropertySheetPage contentPage;

    /**
     * This is the one adapter factory used for providing views of the model.
     */
    protected ComposedAdapterFactory adapterFactory;

    /**
     * Default constructor.
     */
    public AllPropertySection() {
        // Create an adapter factory that yields item providers.
        //
        final List<AdapterFactory> factories = new ArrayList<AdapterFactory>();
        // Start of user code put your specific adapter factories
        factories.add(DialectUIManager.INSTANCE.createAdapterFactory());
        factories.add(FeatureExtensionsUIManager.INSTANCE.createAdapterFactory());
        // End of user code put your specific adapter factories
        factories.add(new ResourceItemProviderAdapterFactory());
        factories.add(new ViewpointItemProviderAdapterFactory());
        factories.add(new DescriptionItemProviderAdapterFactory());
        factories.add(new StyleItemProviderAdapterFactory());
        factories.add(new ToolItemProviderAdapterFactory());
        factories.add(new ValidationItemProviderAdapterFactory());
        factories.add(new AuditItemProviderAdapterFactory());
        factories.add(new ReflectiveItemProviderAdapterFactory());

        adapterFactory = new ComposedAdapterFactory(factories);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#createControls(org.eclipse.swt.widgets.Composite,
     *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
     */
    @Override
    public void createControls(final Composite parent, final TabbedPropertySheetPage tabbedPropertySheetPage) {
        super.createControls(parent, tabbedPropertySheetPage);

        final Composite composite = getWidgetFactory().createFlatFormComposite(parent);

        contentPage = new PropertySheetPage();
        final PropertySheetEntry root = new PropertySheetEntry();

        root.setPropertySourceProvider(this);
        contentPage.setRootEntry(root);

        contentPage.createControl(composite);
        final FormData data = new FormData();
        data.left = new FormAttachment(0, 0);
        data.right = new FormAttachment(100, 0);
        data.top = new FormAttachment(0, 0);
        data.bottom = new FormAttachment(100, 0);
        data.height = 100;
        data.width = 100;
        contentPage.getControl().setLayoutData(data);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.views.properties.IPropertySourceProvider#getPropertySource(java.lang.Object)
     */
    public IPropertySource getPropertySource(final Object object) {
        IPropertySource propSrc = null;

        if (object instanceof IPropertySource) {
            propSrc = (IPropertySource) object;
        } else if (object instanceof EObject) {
            if (adapterFactory != null) {
                final IItemPropertySource ips = (IItemPropertySource) adapterFactory.adapt(object, IItemPropertySource.class);
                if (ips != null) {
                    propSrc = new PropertySource(object, ips);
                }
            }
        } else {
            if (adapterFactory != null) {
                final IItemPropertySource ips = (IItemPropertySource) adapterFactory.adapt(object, IItemPropertySource.class);
                if (ips != null) {
                    propSrc = new PropertySource(object, ips);
                }
            }
            if (propSrc == null && object instanceof IAdaptable) {
                propSrc = (IPropertySource) ((IAdaptable) object).getAdapter(IPropertySource.class);
            }
        }
        return propSrc;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#refresh()
     */
    @Override
    public void refresh() {
        super.refresh();
        if (contentPage != null) {
            contentPage.refresh();
        }
    }

    /**
     * Override to use all vertical space.<BR>
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#shouldUseExtraSpace()
     */
    @Override
    public boolean shouldUseExtraSpace() {
        return true;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.editor.properties.sections.common.AbstractSiriusPropertySection#setInput(org.eclipse.ui.IWorkbenchPart,
     *      org.eclipse.jface.viewers.ISelection)
     */
    @Override
    public void setInput(final IWorkbenchPart part, final ISelection selection) {
        super.setInput(part, selection);
        if (contentPage != null) {
            contentPage.selectionChanged(part, selection);
        }
    }

    @Override
    protected void makeReadonly() {
        contentPage.getControl().setEnabled(false);

    }

    @Override
    protected void makeWrittable() {
        contentPage.getControl().setEnabled(true);
    }

    @Override
    protected EStructuralFeature getFeature() {
        return null;
    }
}
