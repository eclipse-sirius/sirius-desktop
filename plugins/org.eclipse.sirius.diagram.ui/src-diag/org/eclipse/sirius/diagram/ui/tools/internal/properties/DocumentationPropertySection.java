/*******************************************************************************
 * Copyright (c) 2007, 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.properties;

import java.util.List;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.ITextListener;
import org.eclipse.jface.text.TextEvent;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DDiagramEditPart;
import org.eclipse.sirius.diagram.ui.part.SiriusDiagramEditor;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * Section that allows to set a documentation about a diagram.
 * 
 * @author smonnier
 * 
 */
public class DocumentationPropertySection extends AbstractPropertySection {

    /** The label width that will be used for section names. */
    public static final int LABEL_WIDTH = 292;

    /** Full path of the help icon. */
    public static final String ICONS_PREFERENCES_HELP = "icons/help.gif"; //$NON-NLS-1$

    /** The default size of the SourceViewer. */
    protected static final int SOURCE_VIEWER_DEFAULT_SIZE = 150;

    /** SourceViewer control of the section. */
    protected SourceViewer sourceViewer;

    /** Label control of the section. */
    protected CLabel nameLabel;

    /** Main composite. **/
    protected Composite composite;

    /**
     * Current selected object or first object in the selection when multiple
     * objects are selected.
     */
    protected EObject eObject;

    /** The list of currently selected objects. */
    protected List<EObject> eObjectList;

    /**
     * Internal text listener for updating all content dependent actions. The
     * updating is done asynchronously.
     */
    class TextListener implements ITextListener {

        public void textChanged(TextEvent event) {
            handleTextModified();
        }

    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySection#createControls(org.eclipse.swt.widgets.Composite,
     *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
     */
    @Override
    public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
        super.createControls(parent, tabbedPropertySheetPage);

        composite = getWidgetFactory().createFlatFormComposite(parent);
        composite.setLayout(new GridLayout(3, false));

        nameLabel = getWidgetFactory().createCLabel(composite, getLabelText(), SWT.TOP);
        nameLabel.setLayoutData(new GridData(SWT.NULL, SWT.TOP, false, true));

        CLabel help = getWidgetFactory().createCLabel(composite, ""); //$NON-NLS-1$
        help.setImage(getHelpIcon());
        help.setLayoutData(new GridData(SWT.NULL, SWT.TOP, false, true));
        help.setToolTipText(getToolTipText());

        int styles = SWT.V_SCROLL | SWT.H_SCROLL | SWT.MULTI | SWT.BORDER;
        sourceViewer = new SourceViewer(composite, null, styles);
        GridData layoutData = new GridData(GridData.FILL_BOTH);
        layoutData.widthHint = SOURCE_VIEWER_DEFAULT_SIZE;
        layoutData.heightHint = SOURCE_VIEWER_DEFAULT_SIZE;
        sourceViewer.getControl().setLayoutData(layoutData);

        TextListener fTextListener = new TextListener();
        sourceViewer.addTextListener(fTextListener);
        // sourceViewer.addTextInputListener(fTextListener);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.editor.properties.sections.common.AbstractSiriusPropertySection#setInput(org.eclipse.ui.IWorkbenchPart,
     *      org.eclipse.jface.viewers.ISelection)
     */
    @Override
    public void setInput(IWorkbenchPart part, ISelection selection) {
        super.setInput(part, selection);
        nameLabel.setText(getLabelText());
        if (selection instanceof StructuredSelection && ((StructuredSelection) selection).getFirstElement() instanceof DDiagramEditPart) {
            DDiagramEditPart ddep = (DDiagramEditPart) ((StructuredSelection) selection).getFirstElement();
            eObject = ((Diagram) ddep.getModel()).getElement();
            eObjectList = ((IStructuredSelection) selection).toList();
        }
    }

    /**
     * Handle the text modified event.
     */
    protected void handleTextModified() {
        String newText = sourceViewer.getDocument().get();
        boolean equals = isEqual(newText);

        if (!equals) {
            EditingDomain editingDomain = ((SiriusDiagramEditor) getPart()).getEditingDomain();
            Object value = getFeatureValue(newText);
            if (eObjectList.size() == 1) {
                // apply the property change to single selected object
                editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, eObject, getFeature(), value));
            } else {
                CompoundCommand compoundCommand = new CompoundCommand();
                /* apply the property change to all selected elements */
                for (EObject nextObject : eObjectList) {
                    compoundCommand.append(SetCommand.create(editingDomain, nextObject, getFeature(), value));
                }
                editingDomain.getCommandStack().execute(compoundCommand);
            }
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.views.properties.tabbed.view.ITabbedPropertySection#refresh()
     */
    @Override
    public void refresh() {
        if (getFeatureAsText() != null) {
            sourceViewer.setDocument(new Document(getFeatureAsText()));
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
     * @see org.eclipse.sirius.editor.properties.sections.AbstractMultilinePropertySection#isEqual(String)
     */
    protected boolean isEqual(String newText) {
        return getFeatureAsText().equals(newText);
    }

    /**
     * Get the feature for the text field of this section.
     * 
     * @return The feature for the text.
     * @see org.eclipse.sirius.editor.properties.sections.AbstractMultilinePropertySection#getFeature()
     */
    public EAttribute getFeature() {
        return DescriptionPackage.eINSTANCE.getDocumentedElement_Documentation();
    }

    /**
     * Get the base description.
     * 
     * @return The description for the feature.
     * @see org.eclipse.sirius.editor.properties.sections.AbstractMultilinePropertySection#getPropertyDescription()
     */
    protected String getPropertyDescription() {
        return "Use this field to save notes about this representation.";
    }

    protected String getToolTipText() {
        return getPropertyDescription();
    }

    /**
     * Get the value of the default feature as text for the text field of the
     * section.
     * 
     * @return The value of the default feature as text.
     */
    protected String getDefaultFeatureAsText() {
        String value = ""; //$NON-NLS-1$
        if (eObject != null && eObject.eGet(getFeature()) != null) {
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
        // final EStructuralFeature eFeature = getFeature();
        // final IItemPropertyDescriptor propertyDescriptor =
        // getPropertyDescriptor(eFeature);
        // if (propertyDescriptor != null)
        // return
        // propertyDescriptor.getLabelProvider(eObject).getText(eObject.eGet(eFeature));
        return getDefaultFeatureAsText();
    }

    /**
     * Get the new value of the feature for the text field of the section.
     * 
     * @param newText
     *            The new value of the feature as a string.
     * @return The new value of the feature.
     * @see org.eclipse.sirius.editor.properties.sections.AbstractMultilinePropertySection#getFeatureValue()
     */
    protected Object getFeatureValue(String newText) {
        return newText;
    }

    /**
     * Get the default label for the text field of the section.
     * 
     * @return The default label for the text field.
     */
    protected String getDefaultLabelText() {
        return "Documentation:"; //$NON-NLS-1$
    }

    /**
     * Get the label for the text field of the section.
     * 
     * @return The label for the text field.
     */
    protected String getLabelText() {
        return getDefaultLabelText();
    }

    /**
     * {@inheritDoc}
     */
    protected void makeReadonly() {
        sourceViewer.setEditable(false);
    }

    /**
     * {@inheritDoc}
     */
    protected void makeWrittable() {
        sourceViewer.setEditable(true);
    }

    /**
     * Creates and return the help icon to show in our label.
     * 
     * @return The help icon to show in our label.
     */
    protected Image getHelpIcon() {
        ImageDescriptor findImageDescriptor = DiagramUIPlugin.Implementation.findImageDescriptor(ICONS_PREFERENCES_HELP);
        return DiagramUIPlugin.getPlugin().getImage(findImageDescriptor);
    }
}
