/**
 * Copyright (c) 2015 Obeo
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.tests.sample.docbook.components;

// Start of user code for imports
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.eef.runtime.api.notify.EStructuralFeatureNotificationFilter;
import org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.api.notify.NotificationFilter;
import org.eclipse.emf.eef.runtime.context.PropertiesEditingContext;
import org.eclipse.emf.eef.runtime.context.impl.EObjectPropertiesEditionContext;
import org.eclipse.emf.eef.runtime.context.impl.EReferencePropertiesEditionContext;
import org.eclipse.emf.eef.runtime.impl.notify.PropertiesEditionEvent;
import org.eclipse.emf.eef.runtime.impl.utils.EEFConverterUtil;
import org.eclipse.emf.eef.runtime.policies.PropertiesEditingPolicy;
import org.eclipse.emf.eef.runtime.policies.impl.CreateEditingPolicy;
import org.eclipse.emf.eef.runtime.providers.PropertiesEditingProvider;
import org.eclipse.emf.eef.runtime.ui.widgets.referencestable.ReferencesTableSettings;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.sirius.eef.components.SiriusAwarePropertiesEditingComponent;
import org.eclipse.sirius.tests.sample.docbook.DocbookPackage;
import org.eclipse.sirius.tests.sample.docbook.ItemizedList;
import org.eclipse.sirius.tests.sample.docbook.ListItem;
import org.eclipse.sirius.tests.sample.docbook.parts.DocbookViewsRepository;
import org.eclipse.sirius.tests.sample.docbook.parts.ItemizedListPropertiesEditionPart;

// End of user code

/**
 *
 *
 */
public class ItemizedListPropertiesEditionComponent extends SiriusAwarePropertiesEditingComponent {

    public static String BASE_PART = "Base"; //$NON-NLS-1$

    /**
     * Settings for listitem ReferencesTable
     */
    protected ReferencesTableSettings listitemSettings;

    /**
     * Default constructor
     *
     */
    public ItemizedListPropertiesEditionComponent(PropertiesEditingContext editingContext, EObject itemizedList, String editing_mode) {
        super(editingContext, itemizedList, editing_mode);
        parts = new String[] { ItemizedListPropertiesEditionComponent.BASE_PART };
        repositoryKey = DocbookViewsRepository.class;
        partKey = DocbookViewsRepository.ItemizedList.class;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent#initPart(java.lang.Object,
     *      int, org.eclipse.emf.ecore.EObject,
     *      org.eclipse.emf.ecore.resource.ResourceSet)
     *
     */
    @Override
    public void initPart(Object key, int kind, EObject elt, ResourceSet allResource) {
        setInitializing(true);
        if (editingPart != null && key == partKey) {
            editingPart.setContext(elt, allResource);

            final ItemizedList itemizedList = (ItemizedList) elt;
            final ItemizedListPropertiesEditionPart basePart = (ItemizedListPropertiesEditionPart) editingPart;
            // init values
            if (isAccessible(DocbookViewsRepository.ItemizedList.Properties.mark)) {
                basePart.setMark(EcoreUtil.convertToString(EcorePackage.Literals.ESTRING, itemizedList.getMark()));
            }

            if (isAccessible(DocbookViewsRepository.ItemizedList.Properties.listitem)) {
                listitemSettings = new ReferencesTableSettings(itemizedList, DocbookPackage.eINSTANCE.getItemizedList_Listitem());
                basePart.initListitem(listitemSettings);
            }
            // init filters

            if (isAccessible(DocbookViewsRepository.ItemizedList.Properties.listitem)) {
                basePart.addFilterToListitem(new ViewerFilter() {
                    /**
                     * {@inheritDoc}
                     *
                     * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer,
                     *      java.lang.Object, java.lang.Object)
                     */
                    @Override
                    public boolean select(Viewer viewer, Object parentElement, Object element) {
                        return (element instanceof String && element.equals("")) || (element instanceof ListItem); //$NON-NLS-1$
                    }

                });
                // Start of user code for additional businessfilters for
                // listitem
                // End of user code
            }
            // init values for referenced views

            // init filters for referenced views

        }
        setInitializing(false);
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#associatedFeature(java.lang.Object)
     */
    @Override
    public EStructuralFeature associatedFeature(Object editorKey) {
        if (editorKey == DocbookViewsRepository.ItemizedList.Properties.mark) {
            return DocbookPackage.eINSTANCE.getItemizedList_Mark();
        }
        if (editorKey == DocbookViewsRepository.ItemizedList.Properties.listitem) {
            return DocbookPackage.eINSTANCE.getItemizedList_Listitem();
        }
        return super.associatedFeature(editorKey);
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updateSemanticModel(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
     *
     */
    @Override
    public void updateSemanticModel(final IPropertiesEditionEvent event) {
        ItemizedList itemizedList = (ItemizedList) semanticObject;
        if (DocbookViewsRepository.ItemizedList.Properties.mark == event.getAffectedEditor()) {
            itemizedList.setMark((java.lang.String) EEFConverterUtil.createFromString(EcorePackage.Literals.ESTRING, (String) event.getNewValue()));
        }
        if (DocbookViewsRepository.ItemizedList.Properties.listitem == event.getAffectedEditor()) {
            if (event.getKind() == PropertiesEditionEvent.ADD) {
                EReferencePropertiesEditionContext context = new EReferencePropertiesEditionContext(editingContext, this, listitemSettings, editingContext.getAdapterFactory());
                PropertiesEditingProvider provider = (PropertiesEditingProvider) editingContext.getAdapterFactory().adapt(semanticObject, PropertiesEditingProvider.class);
                if (provider != null) {
                    PropertiesEditingPolicy policy = provider.getPolicy(context);
                    if (policy instanceof CreateEditingPolicy) {
                        policy.execute();
                    }
                }
            } else if (event.getKind() == PropertiesEditionEvent.EDIT) {
                EObjectPropertiesEditionContext context = new EObjectPropertiesEditionContext(editingContext, this, (EObject) event.getNewValue(), editingContext.getAdapterFactory());
                PropertiesEditingProvider provider = (PropertiesEditingProvider) editingContext.getAdapterFactory().adapt((EObject) event.getNewValue(), PropertiesEditingProvider.class);
                if (provider != null) {
                    PropertiesEditingPolicy editionPolicy = provider.getPolicy(context);
                    if (editionPolicy != null) {
                        editionPolicy.execute();
                    }
                }
            } else if (event.getKind() == PropertiesEditionEvent.REMOVE) {
                listitemSettings.removeFromReference((EObject) event.getNewValue());
            } else if (event.getKind() == PropertiesEditionEvent.MOVE) {
                listitemSettings.move(event.getNewIndex(), (ListItem) event.getNewValue());
            }
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#updatePart(org.eclipse.emf.common.notify.Notification)
     */
    @Override
    public void updatePart(Notification msg) {
        super.updatePart(msg);
        if (editingPart.isVisible()) {
            ItemizedListPropertiesEditionPart basePart = (ItemizedListPropertiesEditionPart) editingPart;
            if (DocbookPackage.eINSTANCE.getItemizedList_Mark().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && basePart != null
                    && isAccessible(DocbookViewsRepository.ItemizedList.Properties.mark)) {
                if (msg.getNewValue() != null) {
                    basePart.setMark(EcoreUtil.convertToString(EcorePackage.Literals.ESTRING, msg.getNewValue()));
                } else {
                    basePart.setMark("");
                }
            }
            if (DocbookPackage.eINSTANCE.getItemizedList_Listitem().equals(msg.getFeature()) && isAccessible(DocbookViewsRepository.ItemizedList.Properties.listitem)) {
                basePart.updateListitem();
            }

        }
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.emf.eef.runtime.impl.components.StandardPropertiesEditionComponent#getNotificationFilters()
     */
    @Override
    protected NotificationFilter[] getNotificationFilters() {
        NotificationFilter filter = new EStructuralFeatureNotificationFilter(DocbookPackage.eINSTANCE.getItemizedList_Mark(), DocbookPackage.eINSTANCE.getItemizedList_Listitem());
        return new NotificationFilter[] { filter, };
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.emf.eef.runtime.api.component.IPropertiesEditionComponent#validateValue(org.eclipse.emf.eef.runtime.api.notify.IPropertiesEditionEvent)
     *
     */
    @Override
    public Diagnostic validateValue(IPropertiesEditionEvent event) {
        Diagnostic ret = Diagnostic.OK_INSTANCE;
        if (event.getNewValue() != null) {
            try {
                if (DocbookViewsRepository.ItemizedList.Properties.mark == event.getAffectedEditor()) {
                    Object newValue = event.getNewValue();
                    if (newValue instanceof String) {
                        newValue = EEFConverterUtil.createFromString(DocbookPackage.eINSTANCE.getItemizedList_Mark().getEAttributeType(), (String) newValue);
                    }
                    ret = Diagnostician.INSTANCE.validate(DocbookPackage.eINSTANCE.getItemizedList_Mark().getEAttributeType(), newValue);
                }
            } catch (IllegalArgumentException iae) {
                ret = BasicDiagnostic.toDiagnostic(iae);
            } catch (WrappedException we) {
                ret = BasicDiagnostic.toDiagnostic(we);
            }
        }
        return ret;
    }

}
