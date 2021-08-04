/*******************************************************************************
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.editor.properties.sections.description.vsmelementcustomizationreuse;

// Start of user code imports

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.sirius.business.internal.query.model.EAttributeCustomizationQuery;
import org.eclipse.sirius.business.internal.query.model.EReferenceCustomizationQuery;
import org.eclipse.sirius.editor.editorPlugin.SiriusEditor;
import org.eclipse.sirius.editor.properties.sections.common.AbstractEditorDialogPropertySection;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.EAttributeCustomization;
import org.eclipse.sirius.viewpoint.description.EReferenceCustomization;
import org.eclipse.sirius.viewpoint.description.EStructuralFeatureCustomization;
import org.eclipse.sirius.viewpoint.description.VSMElementCustomizationReuse;
import org.eclipse.sirius.viewpoint.description.style.StyleDescription;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

// End of user code imports

/**
 * A section for the reuse property of a VSMElementCustomizationReuse object.
 */
public class VSMElementCustomizationReuseReusePropertySection extends AbstractEditorDialogPropertySection {
    /**
     * @see org.eclipse.sirius.editor.properties.sections.AbstractEditorDialogPropertySection#getDefaultLabelText()
     */
    @Override
    protected String getDefaultLabelText() {
        return "Reuse"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.sirius.editor.properties.sections.AbstractEditorDialogPropertySection#getLabelText()
     */
    @Override
    protected String getLabelText() {
        String labelText;
        labelText = super.getLabelText() + "*:"; //$NON-NLS-1$
        // Start of user code get label text

        // End of user code get label text
        return labelText;
    }

    /**
     * @see org.eclipse.sirius.editor.properties.sections.AbstractEditorDialogPropertySection#getFeature()
     */
    @Override
    protected EReference getFeature() {
        return DescriptionPackage.eINSTANCE.getVSMElementCustomizationReuse_Reuse();
    }

    /**
     * @see org.eclipse.sirius.editor.properties.sections.AbstractEditorDialogPropertySection#getFeatureAsText()
     */
    @Override
    protected String getFeatureAsText() {
        String string = new String();

        if (eObject.eGet(getFeature()) != null) {
            List<?> values = (List<?>) eObject.eGet(getFeature());
            for (Iterator<?> iterator = values.iterator(); iterator.hasNext();) {
                EObject eObj = (EObject) iterator.next();
                string += getAdapterFactoryLabelProvider(eObj).getText(eObj);
                if (iterator.hasNext()) {
                    string += ", ";
                }
            }
        }

        return string;
    }

    /**
     * @see org.eclipse.sirius.editor.properties.sections.AbstractEditorDialogPropertySection#isEqual(java.util.List)
     */
    @Override
    protected boolean isEqual(List<?> newList) {
        return newList.equals(eObject.eGet(getFeature()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
        super.createControls(parent, tabbedPropertySheetPage);
        text.setToolTipText("The style customizations to reuse.");

        CLabel help = getWidgetFactory().createCLabel(composite, "");
        FormData data = new FormData();
        data.top = new FormAttachment(text, 0, SWT.TOP);
        data.left = new FormAttachment(nameLabel);
        help.setLayoutData(data);
        help.setImage(getHelpIcon());
        help.setToolTipText("The style customizations to reuse.");

        nameLabel.setFont(SiriusEditor.getFontRegistry().get("required"));
        // Start of user code create controls

        // End of user code create controls
    }

    // Start of user code user operations
    /**
     * Overridden to limit the choice to {@link EStructuralFeatureCustomization} s.
     *
     * {@inheritDoc}
     */
    @Override
    protected List<?> getChoiceOfValues(List<?> currentValues) {
        List<EObject> availableStyleDescriptions = new ArrayList<EObject>();
        for (Object choiceOfValue : super.getChoiceOfValues(currentValues)) {
            if (choiceOfValue instanceof EObject) {
                EObject choice = (EObject) choiceOfValue;
                if (choice instanceof EStructuralFeatureCustomization) {
                    EStructuralFeatureCustomization eStructuralFeatureCustomization = (EStructuralFeatureCustomization) choice;
                    if (isEStructuralFeatureCustomizationApplicableOn(eStructuralFeatureCustomization)) {
                        availableStyleDescriptions.add(choice);
                    }
                }
            }
        }
        return availableStyleDescriptions;
    }

    private boolean isEStructuralFeatureCustomizationApplicableOn(EStructuralFeatureCustomization eStructuralFeatureCustomization) {
        boolean isEStructuralFeatureCustomizationApplicableOn = false;
        if (eObject instanceof VSMElementCustomizationReuse) {
            VSMElementCustomizationReuse vsmElementCustomizationReuse = (VSMElementCustomizationReuse) eObject;
            isEStructuralFeatureCustomizationApplicableOn = true;
            isEStructuralFeatureCustomizationApplicableOn = true;
            for (EObject eObject : vsmElementCustomizationReuse.getAppliedOn()) {
                if (eObject instanceof StyleDescription || eObject.eContainer() instanceof StyleDescription) {
                    if (eStructuralFeatureCustomization instanceof EAttributeCustomization) {
                        EAttributeCustomization eAttributeCustomization = (EAttributeCustomization) eStructuralFeatureCustomization;
                        EAttributeCustomizationQuery eAttributeCustomizationQuery = new EAttributeCustomizationQuery(eAttributeCustomization);
                        if (!eAttributeCustomizationQuery.isStyleDescriptionEltConformToEAttributeCustomization(eObject)) {
                            isEStructuralFeatureCustomizationApplicableOn = false;
                            break;
                        }
                    } else if (eStructuralFeatureCustomization instanceof EReferenceCustomization) {
                        EReferenceCustomization eReferenceCustomization = (EReferenceCustomization) eStructuralFeatureCustomization;
                        EReferenceCustomizationQuery eReferenceCustomizationQuery = new EReferenceCustomizationQuery(eReferenceCustomization);
                        if (!eReferenceCustomizationQuery.isStyleDescriptionEltConformToEReferenceCustomization(eObject)) {
                            isEStructuralFeatureCustomizationApplicableOn = false;
                            break;
                        }
                    }
                }
            }
        }
        return isEStructuralFeatureCustomizationApplicableOn;
    }
    // End of user code user operations
}
