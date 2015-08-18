/*******************************************************************************
 * Copyright (c) 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.wizards.pages;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * A generic wizard page to select root object and encodings.
 * 
 * @author mchauvin
 */
public class GenericInitialObjectPage extends WizardPage {
    /**
     * initial object widget.
     */
    protected Combo initialObjectField;

    /**
     * encodings combo.
     */
    protected Combo encodingField;

    /**
     * available encodings.
     */
    protected List<String> encodings;

    /**
     * Caches the names of the types that can be created as the root object.
     */
    protected List<String> initialObjectNames;

    /**
     * The root package.
     */
    protected EPackage ePackage;

    /**
     * .
     */
    protected ModifyListener validator = new ModifyListener() {
        public void modifyText(final ModifyEvent e) {
            setPageComplete(validatePage());
        }
    };

    /**
     * Construct a new wizard page to select root object and encoding.
     * 
     * @param pageId
     *            the page id
     */
    public GenericInitialObjectPage(final String pageId) {
        super(pageId);
    }

    /**
     * Set the package.
     * 
     * @param ePackage
     *            the package to set
     */
    // CHECKSTYLE:OFF
    public void setEPackage(final EPackage ePackage) {
        // CHECKSTYLE:ON
        this.ePackage = ePackage;
        /* reset initial Object names */
        this.initialObjectNames = null;
        if (initialObjectField != null) {
            initialObjectField.removeAll();
            fillInitialObjectField();
        }
    }

    /**
     * Returns the names of the types that can be created as the root object.
     * 
     * @return the names of the types that can be created.
     */
    protected Collection<String> getInitialObjectNames() {
        if (initialObjectNames == null) {
            initialObjectNames = new ArrayList<String>();
            for (EClassifier eClassifier : ePackage.getEClassifiers()) {
                if (eClassifier instanceof EClass) {
                    final EClass eClass = (EClass) eClassifier;
                    if (!eClass.isAbstract()) {
                        initialObjectNames.add(eClass.getName());
                    }
                }
            }
            Collections.sort(initialObjectNames, CommonPlugin.INSTANCE.getComparator());
        }
        return initialObjectNames;
    }

    /**
     * Get all the available encodings.
     * 
     * @return the available encodings
     */
    protected Collection<String> getEncodings() {
        if (encodings == null) {
            encodings = new ArrayList<String>();
            encodings.add("UTF-8"); //$NON-NLS-1$
            encodings.add("ASCII"); //$NON-NLS-1$
            encodings.add("UTF-16"); //$NON-NLS-1$
            encodings.add("UTF-16BE"); //$NON-NLS-1$
            encodings.add("UTF-16LE"); //$NON-NLS-1$
            encodings.add("ISO-8859-1"); //$NON-NLS-1$
        }
        return encodings;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    public void createControl(final Composite parent) {
        final Composite composite = new Composite(parent, SWT.NONE);

        final GridLayout layout = new GridLayout();
        layout.numColumns = 1;
        layout.verticalSpacing = 12;
        composite.setLayout(layout);

        final GridData compositeData = new GridData();
        compositeData.verticalAlignment = GridData.FILL;
        compositeData.grabExcessVerticalSpace = true;
        compositeData.horizontalAlignment = GridData.FILL;
        composite.setLayoutData(compositeData);

        final Label containerLabel = new Label(composite, SWT.LEFT);

        containerLabel.setText("&Model Object");

        final GridData containerData = new GridData();
        containerData.horizontalAlignment = GridData.FILL;
        containerLabel.setLayoutData(containerData);

        initialObjectField = new Combo(composite, SWT.BORDER);

        final GridData initialObjectData = new GridData();
        initialObjectData.horizontalAlignment = GridData.FILL;
        initialObjectData.grabExcessHorizontalSpace = true;
        initialObjectField.setLayoutData(initialObjectData);

        fillInitialObjectField();

        initialObjectField.addModifyListener(validator);
        // Workaround for https://bugs.eclipse.org/bugs/show_bug.cgi?id=294192
        initialObjectField.addSelectionListener(new SelectionListener() {
            public void widgetSelected(SelectionEvent e) {
                setPageComplete(validatePage());
            }

            public void widgetDefaultSelected(SelectionEvent e) {
            }
        });
        // End of workaround

        final Label encodingLabel = new Label(composite, SWT.LEFT);

        encodingLabel.setText("&XML Encoding");
        final GridData encodingLabelData = new GridData();
        encodingLabelData.horizontalAlignment = GridData.FILL;
        encodingLabel.setLayoutData(encodingLabelData);

        encodingField = new Combo(composite, SWT.BORDER);

        final GridData encodingFieldData = new GridData();
        encodingFieldData.horizontalAlignment = GridData.FILL;
        encodingFieldData.grabExcessHorizontalSpace = true;
        encodingField.setLayoutData(encodingFieldData);

        for (String encoding : getEncodings()) {
            encodingField.add(encoding);
        }

        encodingField.select(0);
        encodingField.addModifyListener(validator);

        setPageComplete(validatePage());
        setControl(composite);
    }

    private void fillInitialObjectField() {
        for (String objectName : getInitialObjectNames()) {
            initialObjectField.add(getLabel(objectName));
        }

        if (initialObjectField.getItemCount() == 1) {
            initialObjectField.select(0);
        }
    }

    /**
     * Check if this page validates.
     * 
     * @return <code>true</code> if this page validates, <code>false</code>
     *         otherwise
     */
    protected boolean validatePage() {
        return getInitialObjectName() != null && getEncodings().contains(encodingField.getText());
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.dialogs.DialogPage#setVisible(boolean)
     */
    @Override
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        if (visible) {
            if (initialObjectField.getItemCount() == 1) {
                initialObjectField.clearSelection();
                encodingField.setFocus();
            } else {
                encodingField.clearSelection();
                initialObjectField.setFocus();
            }
        }
    }

    /**
     * Get the root object name.
     * 
     * @return the root object name.
     */
    public String getInitialObjectName() {
        final String label = initialObjectField.getText();

        for (String name : getInitialObjectNames()) {
            if (getLabel(name).equals(label)) {
                return name;
            }
        }
        return null;
    }

    /**
     * Get the encoding.
     * 
     * @return the encoding
     */
    public String getEncoding() {
        return encodingField.getText();
    }

    /**
     * Returns the label for the specified type name.
     * 
     * @param typeName
     *            the type name
     * @return the label
     */
    protected String getLabel(final String typeName) {
        final String label = SiriusEditPlugin.getPlugin().getItemText(typeName);
        return label != null ? label : typeName;
    }
}
