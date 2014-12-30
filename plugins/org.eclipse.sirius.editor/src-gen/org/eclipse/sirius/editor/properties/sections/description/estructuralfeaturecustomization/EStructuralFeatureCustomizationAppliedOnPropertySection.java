/*******************************************************************************
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.properties.sections.description.estructuralfeaturecustomization;

// Start of user code imports

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.editor.editorPlugin.SiriusEditor;
import org.eclipse.sirius.editor.properties.sections.common.AbstractEditorDialogPropertySection;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.EAttributeCustomization;
import org.eclipse.sirius.viewpoint.description.EReferenceCustomization;
import org.eclipse.sirius.viewpoint.description.EStructuralFeatureCustomization;
import org.eclipse.sirius.viewpoint.description.style.BasicLabelStyleDescription;
import org.eclipse.sirius.viewpoint.description.style.LabelBorderStyleDescription;
import org.eclipse.sirius.viewpoint.description.style.StyleDescription;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

// End of user code imports

/**
 * A section for the appliedOn property of a EStructuralFeatureCustomization
 * object.
 */
public class EStructuralFeatureCustomizationAppliedOnPropertySection extends AbstractEditorDialogPropertySection {
    /**
     * @see org.eclipse.sirius.editor.properties.sections.AbstractEditorDialogPropertySection#getDefaultLabelText()
     */
    protected String getDefaultLabelText() {
        return "AppliedOn"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.sirius.editor.properties.sections.AbstractEditorDialogPropertySection#getLabelText()
     */
    protected String getLabelText() {
        String labelText;
        labelText = super.getLabelText() + ":"; //$NON-NLS-1$
        // Start of user code get label text
        labelText = super.getLabelText() + "*:"; //$NON-NLS-1$
        // End of user code get label text
        return labelText;
    }

    /**
     * @see org.eclipse.sirius.editor.properties.sections.AbstractEditorDialogPropertySection#getFeature()
     */
    protected EReference getFeature() {
        return DescriptionPackage.eINSTANCE.getEStructuralFeatureCustomization_AppliedOn();
    }

    /**
     * @see org.eclipse.sirius.editor.properties.sections.AbstractEditorDialogPropertySection#getFeatureAsText()
     */
    protected String getFeatureAsText() {
        String string = new String();

        if (eObject.eGet(getFeature()) != null) {
            List<?> values = (List<?>) eObject.eGet(getFeature());
            for (Iterator<?> iterator = values.iterator(); iterator.hasNext();) {
                EObject eObj = (EObject) iterator.next();
                string += getAdapterFactoryLabelProvider(eObj).getText(eObj);
                if (iterator.hasNext())
                    string += ", ";
            }
        }

        return string;
    }

    /**
     * @see org.eclipse.sirius.editor.properties.sections.AbstractEditorDialogPropertySection#isEqual(java.util.List)
     */
    protected boolean isEqual(List<?> newList) {
        return newList.equals(eObject.eGet(getFeature()));
    }

    /**
     * {@inheritDoc}
     */
    public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
        super.createControls(parent, tabbedPropertySheetPage);
        text.setToolTipText("The style to customize.");

        CLabel help = getWidgetFactory().createCLabel(composite, "");
        FormData data = new FormData();
        data.top = new FormAttachment(text, 0, SWT.TOP);
        data.left = new FormAttachment(nameLabel);
        help.setLayoutData(data);
        help.setImage(getHelpIcon());
        help.setToolTipText("The style to customize.");

        // Start of user code create controls
        nameLabel.setFont(SiriusEditor.getFontRegistry().get("required"));
        // End of user code create controls
    }

    // Start of user code user operations
    /**
     * Overridden to limit the choice to {@link StyleDescription}s,
     * {@link BasicLabelStyleDescription}s, {@link LabelBorderStyleDescription}s
     * and {@link GaugeSectionDescription} owned by the current
     * {@link DiagramDescription}.
     * 
     * {@inheritDoc}
     */
    @Override
    protected List<?> getChoiceOfValues(List<?> currentValues) {
        List<EObject> customizableElements = new ArrayList<EObject>();
        for (Object choiceOfValue : super.getChoiceOfValues(currentValues)) {
            if (choiceOfValue instanceof EObject) {
                EObject choice = (EObject) choiceOfValue;

                // Let the dialect tell us if they allow the customizations.
                if (DialectManager.INSTANCE.allowsEStructuralFeatureCustomization(choice) && isConformToCustomization(choice)) {
                    customizableElements.add(choice);
                }
            }
        }
        return customizableElements;
    }

    private boolean isConformToCustomization(EObject choice) {
        boolean isConform = false;
        // If the feature name (attribute name or reference name) is null or
        // empty, let the user choose the value, the completion will then help
        // him to find the attribute name or the reference name from the values.
        // See
        // org.eclipse.sirius.editor.tools.internal.assist.EAttributeCustomizationAttributeNameContentProposalProvider.bindCompletionProcessor(EAttributeCustomizationAttributeNamePropertySection,
        // Text) and
        // org.eclipse.sirius.editor.tools.internal.assist.EReferenceCustomizationReferenceNameContentProposalProvider.bindCompletionProcessor(EReferenceCustomizationReferenceNamePropertySection,
        // Text)
        if (eObject instanceof EAttributeCustomization) {
            EAttributeCustomization eAttributeCustomization = (EAttributeCustomization) eObject;
            if (StringUtil.isEmpty(eAttributeCustomization.getAttributeName())) {
                isConform = true;
            } else {
                EStructuralFeature feature = getEStructuralFeature(choice.eClass(), eAttributeCustomization.getAttributeName());
                isConform = feature instanceof EAttribute;
            }
        } else if (eObject instanceof EReferenceCustomization) {
            EReferenceCustomization eReferenceCustomization = (EReferenceCustomization) eObject;
            if (StringUtil.isEmpty(eReferenceCustomization.getReferenceName())) {
                isConform = true;
            } else {
                EStructuralFeature feature = getEStructuralFeature(choice.eClass(), eReferenceCustomization.getReferenceName());
                isConform = feature instanceof EReference && checkValue((EReference) feature, eReferenceCustomization.getValue());
            }
        }
        return isConform;
    }

    private boolean checkValue(EReference ref, EObject value) {
        EClass eType = ref.getEReferenceType();
        return value != null && eType != null && eType.isSuperTypeOf(value.eClass());
    }

    private EStructuralFeature getEStructuralFeature(EClass eClass, String featureName) {
        if (!StringUtil.isEmpty(featureName) && eClass != null) {
            return eClass.getEStructuralFeature(featureName);
        }
        return null;
    }

    @Override
    public void refresh() {
        super.refresh();
        updateReadOnlyStatus();
    }

    @Override
    protected boolean shouldBeReadOnly() {
        boolean shouldBeReadOnly = super.shouldBeReadOnly();
        if (!shouldBeReadOnly) {
            return ((EStructuralFeatureCustomization) eObject).isApplyOnAll();
        }
        return shouldBeReadOnly;
    }
    // End of user code user operations
}
