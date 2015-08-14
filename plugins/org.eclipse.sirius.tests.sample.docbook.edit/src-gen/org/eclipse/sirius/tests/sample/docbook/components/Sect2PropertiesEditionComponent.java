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
import org.eclipse.sirius.tests.sample.docbook.Para;
import org.eclipse.sirius.tests.sample.docbook.Sect2;
import org.eclipse.sirius.tests.sample.docbook.Sect3;
import org.eclipse.sirius.tests.sample.docbook.parts.DocbookViewsRepository;
import org.eclipse.sirius.tests.sample.docbook.parts.Sect2PropertiesEditionPart;

// End of user code

/**
 *
 *
 */
public class Sect2PropertiesEditionComponent extends SiriusAwarePropertiesEditingComponent {

    public static String BASE_PART = "Base"; //$NON-NLS-1$

    /**
     * Settings for para ReferencesTable
     */
    protected ReferencesTableSettings paraSettings;

    /**
     * Settings for sect3 ReferencesTable
     */
    protected ReferencesTableSettings sect3Settings;

    /**
     * Default constructor
     *
     */
    public Sect2PropertiesEditionComponent(PropertiesEditingContext editingContext, EObject sect2, String editing_mode) {
        super(editingContext, sect2, editing_mode);
        parts = new String[] { Sect2PropertiesEditionComponent.BASE_PART };
        repositoryKey = DocbookViewsRepository.class;
        partKey = DocbookViewsRepository.Sect2.class;
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

            final Sect2 sect2 = (Sect2) elt;
            final Sect2PropertiesEditionPart basePart = (Sect2PropertiesEditionPart) editingPart;
            // init values
            if (isAccessible(DocbookViewsRepository.Sect2.Properties.para)) {
                paraSettings = new ReferencesTableSettings(sect2, DocbookPackage.eINSTANCE.getAbstractSect_Para());
                basePart.initPara(paraSettings);
            }
            if (isAccessible(DocbookViewsRepository.Sect2.Properties.id)) {
                basePart.setId(EcoreUtil.convertToString(EcorePackage.Literals.ESTRING, sect2.getId()));
            }

            if (isAccessible(DocbookViewsRepository.Sect2.Properties.sect3)) {
                sect3Settings = new ReferencesTableSettings(sect2, DocbookPackage.eINSTANCE.getSect2_Sect3());
                basePart.initSect3(sect3Settings);
            }
            // init filters
            if (isAccessible(DocbookViewsRepository.Sect2.Properties.para)) {
                basePart.addFilterToPara(new ViewerFilter() {
                    /**
                     * {@inheritDoc}
                     *
                     * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer,
                     *      java.lang.Object, java.lang.Object)
                     */
                    @Override
                    public boolean select(Viewer viewer, Object parentElement, Object element) {
                        return (element instanceof String && element.equals("")) || (element instanceof Para); //$NON-NLS-1$
                    }

                });
                // Start of user code for additional businessfilters for para
                // End of user code
            }

            if (isAccessible(DocbookViewsRepository.Sect2.Properties.sect3)) {
                basePart.addFilterToSect3(new ViewerFilter() {
                    /**
                     * {@inheritDoc}
                     *
                     * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer,
                     *      java.lang.Object, java.lang.Object)
                     */
                    @Override
                    public boolean select(Viewer viewer, Object parentElement, Object element) {
                        return (element instanceof String && element.equals("")) || (element instanceof Sect3); //$NON-NLS-1$
                    }

                });
                // Start of user code for additional businessfilters for sect3
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
        if (editorKey == DocbookViewsRepository.Sect2.Properties.para) {
            return DocbookPackage.eINSTANCE.getAbstractSect_Para();
        }
        if (editorKey == DocbookViewsRepository.Sect2.Properties.id) {
            return DocbookPackage.eINSTANCE.getSect2_Id();
        }
        if (editorKey == DocbookViewsRepository.Sect2.Properties.sect3) {
            return DocbookPackage.eINSTANCE.getSect2_Sect3();
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
        Sect2 sect2 = (Sect2) semanticObject;
        if (DocbookViewsRepository.Sect2.Properties.para == event.getAffectedEditor()) {
            if (event.getKind() == PropertiesEditionEvent.ADD) {
                EReferencePropertiesEditionContext context = new EReferencePropertiesEditionContext(editingContext, this, paraSettings, editingContext.getAdapterFactory());
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
                paraSettings.removeFromReference((EObject) event.getNewValue());
            } else if (event.getKind() == PropertiesEditionEvent.MOVE) {
                paraSettings.move(event.getNewIndex(), (Para) event.getNewValue());
            }
        }
        if (DocbookViewsRepository.Sect2.Properties.id == event.getAffectedEditor()) {
            sect2.setId((java.lang.String) EEFConverterUtil.createFromString(EcorePackage.Literals.ESTRING, (String) event.getNewValue()));
        }
        if (DocbookViewsRepository.Sect2.Properties.sect3 == event.getAffectedEditor()) {
            if (event.getKind() == PropertiesEditionEvent.ADD) {
                EReferencePropertiesEditionContext context = new EReferencePropertiesEditionContext(editingContext, this, sect3Settings, editingContext.getAdapterFactory());
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
                sect3Settings.removeFromReference((EObject) event.getNewValue());
            } else if (event.getKind() == PropertiesEditionEvent.MOVE) {
                sect3Settings.move(event.getNewIndex(), (Sect3) event.getNewValue());
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
            Sect2PropertiesEditionPart basePart = (Sect2PropertiesEditionPart) editingPart;
            if (DocbookPackage.eINSTANCE.getAbstractSect_Para().equals(msg.getFeature()) && isAccessible(DocbookViewsRepository.Sect2.Properties.para)) {
                basePart.updatePara();
            }
            if (DocbookPackage.eINSTANCE.getSect2_Id().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && basePart != null
                    && isAccessible(DocbookViewsRepository.Sect2.Properties.id)) {
                if (msg.getNewValue() != null) {
                    basePart.setId(EcoreUtil.convertToString(EcorePackage.Literals.ESTRING, msg.getNewValue()));
                } else {
                    basePart.setId("");
                }
            }
            if (DocbookPackage.eINSTANCE.getSect2_Sect3().equals(msg.getFeature()) && isAccessible(DocbookViewsRepository.Sect2.Properties.sect3)) {
                basePart.updateSect3();
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
        NotificationFilter filter = new EStructuralFeatureNotificationFilter(DocbookPackage.eINSTANCE.getAbstractSect_Para(), DocbookPackage.eINSTANCE.getSect2_Id(),
                DocbookPackage.eINSTANCE.getSect2_Sect3());
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
                if (DocbookViewsRepository.Sect2.Properties.id == event.getAffectedEditor()) {
                    Object newValue = event.getNewValue();
                    if (newValue instanceof String) {
                        newValue = EEFConverterUtil.createFromString(DocbookPackage.eINSTANCE.getSect2_Id().getEAttributeType(), (String) newValue);
                    }
                    ret = Diagnostician.INSTANCE.validate(DocbookPackage.eINSTANCE.getSect2_Id().getEAttributeType(), newValue);
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
