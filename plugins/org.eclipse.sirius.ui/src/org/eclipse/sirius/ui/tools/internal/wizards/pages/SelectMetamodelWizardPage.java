/*
 * Copyright (c) 2005-2007 Obeo
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 */

package org.eclipse.sirius.ui.tools.internal.wizards.pages;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.sirius.common.ui.tools.api.resource.WorkspaceResourceDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

/**
 * The role of this wizard page is to select an ecore metamodel, in the
 * workspace or in the registry (EPackage).
 * 
 * @author www.obeo.fr
 */
public class SelectMetamodelWizardPage extends WizardPage {

    /**
     * The metamodel URI.
     */
    private Text metamodelURI;

    /**
     * The extensions.
     */
    private String[] extensions;

    /**
     * The metamodel type.
     */
    private Combo metamodelType;

    /**
     * The metamodel types.
     */
    private String[] types;

    /**
     * Indicates if we have to select the main type of the metamodel.
     */
    private boolean chooseMetamodelType;

    /**
     * Constructor.
     * 
     * @param pageName
     *            is the name of the page
     * @param extensions
     *            is a table of extensions
     */
    public SelectMetamodelWizardPage(final String pageName, final String[] extensions) {
        this(pageName, extensions, false);
    }

    /**
     * Constructor.
     * 
     * @param pageName
     *            is the name of the page
     * @param extensions
     *            is a table of extensions
     * @param chooseMetamodelType
     *            indicates if we have to select the main type of the metamodel
     */
    public SelectMetamodelWizardPage(final String pageName, final String[] extensions, final boolean chooseMetamodelType) {
        super(pageName);
        setTitle(pageName);
        setDescription("This page is used to specify the URI of the metamodel"); //$NON-NLS-1$
        this.extensions = extensions;
        this.chooseMetamodelType = chooseMetamodelType;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    public void createControl(final Composite parent) {
        final Composite rootContainer = new Composite(parent, SWT.NULL);
        final GridLayout rootContainerLayout = new GridLayout();
        rootContainerLayout.numColumns = 1;
        rootContainerLayout.marginTop = 14;
        rootContainerLayout.verticalSpacing = 3;
        rootContainerLayout.marginLeft = 5;
        rootContainerLayout.marginRight = 5;
        rootContainer.setLayout(rootContainerLayout);

        final Composite registryContainer = new Composite(rootContainer, SWT.NULL);
        final GridLayout registryContainerLayout = new GridLayout();
        registryContainerLayout.numColumns = 3;
        registryContainerLayout.verticalSpacing = 3;
        registryContainer.setLayout(registryContainerLayout);

        final Label registryLabel = new Label(registryContainer, SWT.NULL);
        registryLabel.setText("&Registry values" + ':'); //$NON-NLS-1$

        final Set<String> registryValues = new TreeSet<String>(EPackage.Registry.INSTANCE.keySet());
        final String[] valueLabels = registryValues.toArray(new String[registryValues.size()]);
        final Combo comboBox = new Combo(registryContainer, SWT.READ_ONLY);
        comboBox.setItems(valueLabels);
        final int visibleItemCount = 15;
        if (valueLabels.length < visibleItemCount) {
            comboBox.setVisibleItemCount(valueLabels.length);
        } else {
            comboBox.setVisibleItemCount(visibleItemCount);
        }
        comboBox.addSelectionListener(new SelectionListener() {
            public void widgetDefaultSelected(final SelectionEvent e) {
            }

            public void widgetSelected(final SelectionEvent e) {
                metamodelURI.setText(valueLabels[((Combo) e.widget).getSelectionIndex()]);
            }
        });

        final Button button = new Button(registryContainer, SWT.PUSH);
        button.setText("Browse..."); //$NON-NLS-1$
        button.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(final SelectionEvent e) {
                handleBrowse();
            }
        });

        final Composite uriContainer = new Composite(rootContainer, SWT.NULL);
        GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
        gridData.horizontalSpan = 3;
        uriContainer.setLayoutData(gridData);
        final GridLayout uriContainerLayout = new GridLayout();
        uriContainerLayout.numColumns = 1;
        uriContainer.setLayout(uriContainerLayout);

        final Label uriLabel = new Label(uriContainer, SWT.NULL);
        uriLabel.setText("&Metamodel URI" + ':'); //$NON-NLS-1$

        metamodelURI = new Text(uriContainer, SWT.BORDER | SWT.SINGLE);
        gridData = new GridData(GridData.FILL_HORIZONTAL);
        gridData.horizontalSpan = 3;
        metamodelURI.setLayoutData(gridData);
        metamodelURI.addModifyListener(new ModifyListener() {
            public void modifyText(final ModifyEvent e) {
                updateTypes();
                dialogChanged();
                metamodelURI.getText();
            }
        });

        if (chooseMetamodelType) {
            final Composite typeContainer = new Composite(rootContainer, SWT.NULL);
            final GridData typeContainerGridData = new GridData(GridData.FILL_HORIZONTAL);
            typeContainerGridData.horizontalSpan = 4;
            typeContainer.setLayoutData(typeContainerGridData);
            final GridLayout typeContainerLayout = new GridLayout();
            typeContainerLayout.numColumns = 1;
            typeContainer.setLayout(typeContainerLayout);

            final Label typeLabel = new Label(typeContainer, SWT.NULL);
            typeLabel.setText("&Type values" + ':');

            metamodelType = new Combo(typeContainer, SWT.READ_ONLY);
            gridData = new GridData(GridData.FILL_HORIZONTAL);
            gridData.horizontalSpan = 4;
            metamodelType.setLayoutData(gridData);
            metamodelType.addSelectionListener(new SelectionListener() {
                public void widgetDefaultSelected(final SelectionEvent e) {
                }

                public void widgetSelected(final SelectionEvent e) {
                    dialogChanged();
                }
            });
        }

        updateTypes();
        dialogChanged();
        setControl(rootContainer);
    }

    private void handleBrowse() {
        final WorkspaceResourceDialog dialog = new WorkspaceResourceDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), SWT.SINGLE, "Select the metamodel in the workspace",
                Arrays.asList(extensions));
        dialog.open();
        if (dialog.getSelectedResources() != null && !dialog.getSelectedResources().isEmpty()) {
            metamodelURI.setText(dialog.getSelectedResources().get(0).getFullPath().toString());
        }
    }

    /**
     * Updates the metamodel types.
     */
    private void updateTypes() {
        if (metamodelType != null) {
            final TreeSet<String> typeValues = new TreeSet<String>();
            final String mmURI = getMetamodelURI();
            final EPackage ePackage = SelectMetamodelWizardPage.getEPackage(mmURI);

            final EClassifier[] classifiers = SelectMetamodelWizardPage.computeAllClassifiers(ePackage).toArray(new EClassifier[] {});
            for (final EClassifier classifier : classifiers) {
                typeValues.add(SelectMetamodelWizardPage.getEClassifierShortPath(classifier));
            }
            types = typeValues.toArray(new String[typeValues.size()]);
            metamodelType.setItems(types);
            final int visibleItemCount = 15;
            if (types.length < visibleItemCount) {
                metamodelType.setVisibleItemCount(types.length);
            } else {
                metamodelType.setVisibleItemCount(visibleItemCount);
            }
            final String nameForClass = "class"; //$NON-NLS-1$
            int index = bestType(-1, nameForClass, 0);
            index = bestType(index, nameForClass, 1);
            index = bestType(index, nameForClass, 2);
            final String nameForModel = "model"; //$NON-NLS-1$
            index = bestType(index, nameForModel, 0);
            index = bestType(index, nameForModel, 1);
            index = bestType(index, nameForModel, 2);

            final String nameForRoot = "root"; //$NON-NLS-1$
            index = bestType(index, nameForRoot, 0);
            index = bestType(index, nameForRoot, 1);
            index = bestType(index, nameForRoot, 2);
            final String nameForPackage = "package"; //$NON-NLS-1$
            index = bestType(index, nameForPackage, 0);
            index = bestType(index, nameForPackage, 1);
            index = bestType(index, nameForPackage, 2);
            if (index > -1) {
                metamodelType.select(index);
            }
        }
    }

    /**
     * Returns the short path of the classifier.
     * <p>
     * Sample : "resources.JavaFile" is the short path for the classifier
     * java.resources.JavaFile
     * 
     * @param eClassifier
     *            is the classifier
     * @return short path of the classifier
     */
    private static String getEClassifierShortPath(final EClassifier eClassifier) {
        String name = eClassifier.getName();
        if (eClassifier.getEPackage() != null) {
            name = eClassifier.getEPackage().getName() + '.' + name;
        }
        return name;
    }

    private static EPackage getEPackage(final String uri) {
        return EPackage.Registry.INSTANCE.getEPackage(uri);
    }

    /**
     * Search all the classifiers recursively in a package.
     * 
     * @param ePackage
     *            is the container
     * @return list of classifiers
     */
    private static List<EClassifier> computeAllClassifiers(final EPackage ePackage) {
        return SelectMetamodelWizardPage.computeAllClassifiersList(ePackage, false);
    }

    /**
     * Search all the classifiers recursively in a package.
     * 
     * @param ePackage
     *            is the container
     * @param classOnly
     *            indicates that only the classes are kept
     * @return list of classifiers
     */
    private static List<EClassifier> computeAllClassifiersList(final EPackage ePackage, final boolean classOnly) {
        final List<EClassifier> classifiers = new BasicEList<EClassifier>();
        if (ePackage != null) {
            SelectMetamodelWizardPage.computeAllClassifiersList(ePackage, classifiers, classOnly);
        }
        return classifiers;
    }

    private static void computeAllClassifiersList(final EPackage ePackage, final List<EClassifier> all, final boolean classOnly) {
        final Iterator<EClassifier> classifiers = ePackage.getEClassifiers().iterator();
        while (classifiers.hasNext()) {
            final EClassifier classifier = classifiers.next();
            if (!classOnly) {
                all.add(classifier);
            } else {
                if (classifier instanceof EClass && !((EClass) classifier).isAbstract() && !((EClass) classifier).isInterface()) {
                    all.add(classifier);
                }
            }
        }
        final Iterator<EPackage> packages = ePackage.getESubpackages().iterator();
        while (packages.hasNext()) {
            SelectMetamodelWizardPage.computeAllClassifiersList(packages.next(), all, classOnly);
        }
    }

    // CHECKSTYLE:OFF
    private int bestType(final int index, final String name, final int level) {
        if (index == -1) {
            final String nameLower = name.toLowerCase();
            for (int i = 0; i < types.length; i++) {
                String type = types[i];
                final int iDot = type.lastIndexOf("."); //$NON-NLS-1$
                if (iDot > -1) {
                    type = type.substring(iDot + 1);
                }
                type = type.toLowerCase();
                if (level == 0 && type.equals(nameLower)) {
                    return i;
                } else if (level == 1 && type.startsWith(nameLower)) {
                    return i;
                } else if (level == 2 && type.endsWith(nameLower)) {
                    return i;
                }
            }
        }
        return index;
    }

    // CHECKSTYLE:ON

    /**
     * Validates the changes on the page.
     */
    private void dialogChanged() {
        final String mmURI = getMetamodelURI();
        if (mmURI.length() == 0) {
            updateStatus("Metamodel URI must be specified");
        } else if (SelectMetamodelWizardPage.getEPackage(mmURI) == null) {
            updateStatus("Metamodel URI doesn't exist");
        } else if (chooseMetamodelType && getMetamodelType().length() == 0) {
            updateStatus("Metamodel type is not selected");
        } else {
            updateStatus(null);
        }
    }

    /**
     * Updates the status of the page.
     * 
     * @param message
     *            is the error message.
     */
    private void updateStatus(final String message) {
        setMessage(message);
        setPageComplete(message == null);
    }

    /**
     * Returns the metamodel URI.
     * 
     * @return the metamodel URI
     */
    public String getMetamodelURI() {
        return metamodelURI.getText();
    }

    /**
     * Returns the package associated with the current metamodel uri.
     * 
     * @return the package
     */
    public EPackage getEPackage() {
        return SelectMetamodelWizardPage.getEPackage(metamodelURI.getText());
    }

    /**
     * Returns the metamodel type.
     * 
     * @return the metamodel type
     */
    public String getMetamodelType() {
        if (metamodelType != null && types != null && metamodelType.getSelectionIndex() > -1 && metamodelType.getSelectionIndex() < types.length) {
            return types[metamodelType.getSelectionIndex()];
        } else {
            return ""; //$NON-NLS-1$
        }
    }

}
