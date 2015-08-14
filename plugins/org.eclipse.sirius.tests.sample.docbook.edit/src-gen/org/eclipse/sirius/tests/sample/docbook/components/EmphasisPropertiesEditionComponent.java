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
import org.eclipse.emf.eef.runtime.impl.utils.EEFConverterUtil;
import org.eclipse.sirius.eef.components.SiriusAwarePropertiesEditingComponent;
import org.eclipse.sirius.tests.sample.docbook.DocbookPackage;
import org.eclipse.sirius.tests.sample.docbook.Emphasis;
import org.eclipse.sirius.tests.sample.docbook.parts.DocbookViewsRepository;
import org.eclipse.sirius.tests.sample.docbook.parts.EmphasisPropertiesEditionPart;

// End of user code

/**
 *
 *
 */
public class EmphasisPropertiesEditionComponent extends SiriusAwarePropertiesEditingComponent {

    public static String BASE_PART = "Base"; //$NON-NLS-1$

    /**
     * Default constructor
     *
     */
    public EmphasisPropertiesEditionComponent(PropertiesEditingContext editingContext, EObject emphasis, String editing_mode) {
        super(editingContext, emphasis, editing_mode);
        parts = new String[] { EmphasisPropertiesEditionComponent.BASE_PART };
        repositoryKey = DocbookViewsRepository.class;
        partKey = DocbookViewsRepository.Emphasis.class;
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

            final Emphasis emphasis = (Emphasis) elt;
            final EmphasisPropertiesEditionPart basePart = (EmphasisPropertiesEditionPart) editingPart;
            // init values
            if (isAccessible(DocbookViewsRepository.Emphasis.Properties.remap)) {
                basePart.setRemap(EcoreUtil.convertToString(EcorePackage.Literals.ESTRING, emphasis.getRemap()));
            }

            // init filters

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
        if (editorKey == DocbookViewsRepository.Emphasis.Properties.remap) {
            return DocbookPackage.eINSTANCE.getEmphasis_Remap();
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
        Emphasis emphasis = (Emphasis) semanticObject;
        if (DocbookViewsRepository.Emphasis.Properties.remap == event.getAffectedEditor()) {
            emphasis.setRemap((java.lang.String) EEFConverterUtil.createFromString(EcorePackage.Literals.ESTRING, (String) event.getNewValue()));
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
            EmphasisPropertiesEditionPart basePart = (EmphasisPropertiesEditionPart) editingPart;
            if (DocbookPackage.eINSTANCE.getEmphasis_Remap().equals(msg.getFeature()) && msg.getNotifier().equals(semanticObject) && basePart != null
                    && isAccessible(DocbookViewsRepository.Emphasis.Properties.remap)) {
                if (msg.getNewValue() != null) {
                    basePart.setRemap(EcoreUtil.convertToString(EcorePackage.Literals.ESTRING, msg.getNewValue()));
                } else {
                    basePart.setRemap("");
                }
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
        NotificationFilter filter = new EStructuralFeatureNotificationFilter(DocbookPackage.eINSTANCE.getEmphasis_Remap());
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
                if (DocbookViewsRepository.Emphasis.Properties.remap == event.getAffectedEditor()) {
                    Object newValue = event.getNewValue();
                    if (newValue instanceof String) {
                        newValue = EEFConverterUtil.createFromString(DocbookPackage.eINSTANCE.getEmphasis_Remap().getEAttributeType(), (String) newValue);
                    }
                    ret = Diagnostician.INSTANCE.validate(DocbookPackage.eINSTANCE.getEmphasis_Remap().getEAttributeType(), newValue);
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
