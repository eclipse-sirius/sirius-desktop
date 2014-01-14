/*******************************************************************************
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.properties.sections.common;

import java.util.Iterator;
import java.util.Map.Entry;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.editor.properties.ViewpointPropertySheetPage;
import org.eclipse.sirius.editor.utils.TextWithContentAssistChangeHelper;
import org.eclipse.sirius.ext.swt.TextChangeListener;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * An abstract implementation of a section displaying a text field.
 */
public abstract class AbstractTextPropertySection extends AbstractViewpointPropertySection implements ModelViewBinding {

    private final String RETURN_TYPE = "http://www.eclipse.org/sirius/interpreted/expression/returnType";

    private final String VARIABLES = "http://www.eclipse.org/sirius/interpreted/expression/variables";

    /** Text control of the section. */
    protected Text text;

    /** Label control of the section. */
    protected CLabel nameLabel;

    /** Main composite */
    protected Composite composite;

    /** The main listener */
    protected TextChangeListener listener;

    private boolean handleModifications;

    /**
     * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySection#createControls(org.eclipse.swt.widgets.Composite,
     *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
     */
    public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
        if (tabbedPropertySheetPage instanceof ViewpointPropertySheetPage)
            super.createControls(parent, (ViewpointPropertySheetPage) tabbedPropertySheetPage);
        else
            super.createControls(parent, tabbedPropertySheetPage);

        composite = getWidgetFactory().createFlatFormComposite(parent);
        FormData data;

        text = getWidgetFactory().createText(composite, ""); //$NON-NLS-1$
        data = new FormData();
        data.left = new FormAttachment(0, LABEL_WIDTH);
        data.right = new FormAttachment(100, 0);
        data.top = new FormAttachment(0, ITabbedPropertyConstants.VSPACE);
        text.setLayoutData(data);

        nameLabel = getWidgetFactory().createCLabel(composite, getLabelText());
        data = new FormData();
        data.left = new FormAttachment(0, 0);
        data.right = new FormAttachment(text, -ITabbedPropertyConstants.HSPACE - 20);
        data.top = new FormAttachment(text, 0, SWT.CENTER);
        nameLabel.setLayoutData(data);

        listener = new TextWithContentAssistChangeHelper() {
            public void textChanged(Text control) {
                handleTextModified();
            }
        };
        listener.startListeningTo(text);
        listener.startListeningForEnter(text);
        enableModelUpdating();
    }

    /**
     * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#aboutToBeShown()
     * @Override
     */
    public void aboutToBeShown() {
        super.aboutToBeShown();
        PlatformUI.getWorkbench().getHelpSystem().setHelp(composite, "org.eclipse.sirius." + eObject.eClass().getName());
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.editor.properties.sections.common.AbstractViewpointPropertySection#setInput(org.eclipse.ui.IWorkbenchPart,
     *      org.eclipse.jface.viewers.ISelection)
     */
    @Override
    public void setInput(IWorkbenchPart part, ISelection selection) {
        super.setInput(part, selection);
        nameLabel.setText(getLabelText());
    }

    public TextChangeListener getListener() {
        return this.listener;
    }

    /**
     * Handle the text modified event.
     */
    protected void handleTextModified() {
        if (isActive()) {
            String newText = text.getText();
            boolean equals = isEqual(newText);

            if (!equals) {
                EditingDomain editingDomain = ((IEditingDomainProvider) getPart()).getEditingDomain();
                Object value = getFeatureValue(newText);
                if (value instanceof String && StringUtil.isEmpty((String) value)) {
                    if (getFeature() != null && !StringUtil.isEmpty(getFeature().getDefaultValueLiteral()))
                        value = "";
                    else
                        value = null;
                }
                if (eObjectList.size() == 1) {
                    // apply the property change to single selected object
                    editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, eObject, getFeature(), value));
                } else {
                    CompoundCommand compoundCommand = new CompoundCommand();
                    /* apply the property change to all selected elements */
                    for (Iterator<EObject> i = eObjectList.iterator(); i.hasNext();) {
                        EObject nextObject = i.next();
                        compoundCommand.append(SetCommand.create(editingDomain, nextObject, getFeature(), value));
                    }
                    editingDomain.getCommandStack().execute(compoundCommand);
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void disableModelUpdating() {
        handleModifications = false;
    }

    /**
     * {@inheritDoc}
     */
    public void enableModelUpdating() {
        handleModifications = true;
    }

    /**
     * {@inheritDoc}
     */
    protected boolean isActive() {
        return handleModifications;
    }

    /**
     * @see org.eclipse.ui.views.properties.tabbed.view.ITabbedPropertySection#refresh()
     */
    public void refresh() {
        final String value = getFeatureAsText();
        if (!StringUtil.equals(value, text.getText())) {
            text.setText(value);
        }

        final String tooltip = getToolTipText();
        if (tooltip != null) {
            text.setToolTipText(getToolTipText());
        }
    }

    /**
     * Determine if the provided string value is an equal representation of the
     * current setting of the text property.
     * 
     * @param newText
     *            the new string value.
     * @return <code>True</code> if the new string value is equal to the current
     *         property setting, <code>False</code> otherwise.
     */
    protected abstract boolean isEqual(String newText);

    /**
     * Get the feature for the text field of this section.
     * 
     * @return The feature for the text.
     */
    public abstract EAttribute getFeature();

    /**
     * Get the base description.
     * 
     * @return The description for the feature.
     */
    protected abstract String getPropertyDescription();

    protected String getToolTipText() {
        EAttribute exp = getFeature();
        if (!DescriptionPackage.eINSTANCE.getInterpretedExpression().equals(exp.getEAttributeType())) {
            return getPropertyDescription();
        }

        StringBuilder sb = new StringBuilder();
        sb.append(getPropertyDescription());
        addNewLine(sb);

        appendReturnTypeDetails(exp, sb);

        appendVariablesDetails(exp, sb);

        return sb.toString();
    }

    protected void appendReturnTypeDetails(EAttribute exp, StringBuilder sb) {
        EAnnotation returnTypes = exp.getEAnnotation(RETURN_TYPE);
        if (returnTypes != null && !returnTypes.getDetails().isEmpty()) {
            addNewLine(sb);
            sb.append("Expected return type: ");
            sb.append(returnTypes.getDetails().iterator().next().getValue());
            sb.append("\n");
        }
    }

    protected void appendVariablesDetails(EAttribute exp, StringBuilder sb) {
        final EAnnotation variables = exp.getEAnnotation(VARIABLES);
        if (variables != null && !variables.getDetails().isEmpty()) {
            addNewLine(sb);
            sb.append("Available variables:");
            for (final Entry<String, String> variable : variables.getDetails().entrySet()) {
                sb.append("\n . ");
                sb.append(variable.getKey());
                sb.append(": ");
                sb.append(variable.getValue());
            }
        }
    }

    private void addNewLine(StringBuilder sb) {
        if (!StringUtil.isEmpty(sb.toString())) {
            sb.append("\n");
        }
    }

    protected String getDefaultFeatureAsText() {
        String value = new String();
        if (eObject.eGet(getFeature()) != null) {
            value = eObject.eGet(getFeature()).toString();
        }
        return value;
    }

    /**
     * Get the value of the feature as text for the text field of the section.
     * 
     * @return The value of the feature as text.
     */
    protected String getFeatureAsText() {
        final EStructuralFeature eFeature = getFeature();
        final IItemPropertyDescriptor propertyDescriptor = getPropertyDescriptor(eFeature);
        if (propertyDescriptor != null)
            return propertyDescriptor.getLabelProvider(eObject).getText(eObject.eGet(eFeature));
        return getDefaultFeatureAsText();
    }

    /**
     * Get the new value of the feature for the text field of the section.
     * 
     * @param newText
     *            The new value of the feature as a string.
     * @return The new value of the feature.
     */
    protected abstract Object getFeatureValue(String newText);

    protected abstract String getDefaultLabelText();

    /**
     * Get the label for the text field of the section.
     * 
     * @return The label for the text field.
     */
    protected String getLabelText() {
        if (eObject != null) {
            final EStructuralFeature eFeature = getFeature();
            final IItemPropertyDescriptor propertyDescriptor = getPropertyDescriptor(eFeature);
            if (propertyDescriptor != null)
                return propertyDescriptor.getDisplayName(eObject);
        }
        return getDefaultLabelText();
    }

    /**
     * {@inheritDoc}
     */
    protected void makeReadonly() {
        text.setEnabled(false);
    }

    /**
     * {@inheritDoc}
     */
    protected void makeWrittable() {
        text.setEnabled(true);
    }
}
