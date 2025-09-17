/**
 * Copyright (c) 2017, 2025 Obeo
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *  Obeo - initial API and implementation
 */
package org.eclipse.sirius.ui.tools.internal.wizards.newmodel;

import java.util.Map.Entry;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.provider.EcoreEditPlugin;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.common.tools.api.ecore.EPackageMetaData;
import org.eclipse.sirius.ui.tools.api.wizards.CreateEMFModelWizard;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.FilteredList;

/**
 * A wizard page allowing to select a metamodel ({@link EPackage}) from an {@link EPackage.Registry}.
 * 
 * @see CreateEMFModelWizard
 * 
 * @author <a href="mailto:axel.richard@obeo.fr">Axel Richard</a>
 */
public class SelectEMFMetamodelWizardPage extends WizardPage {

    /** The package separator used to display qualified name of an EPackage. */
    private static final String PACKAGE_SEPARATOR = "::"; //$NON-NLS-1$

    /** The icon used for EPackages. */
    private final Image ePacakgeIcon = ExtendedImageRegistry.getInstance().getImage(EcoreEditPlugin.INSTANCE.getImage("full/obj16/EPackage")); //$NON-NLS-1$

    /** The text field allowing to filter the list of EPackages. */
    private Text metamodelFilterText;

    /** The list of EPackages. */
    private FilteredList metamodelFilteredList;

    /** The filter used by the text field and the filtered list. */
    private String metamodelFilter;

    /** A browser to display documentation of the selected metamodel. */
    private Browser documentationBrowser;

    /** The EPackage.Registry containing the metamodels to display. */
    private EPackage.Registry packageRegistry;

    /** The data model used by the wizard and its pages. */
    private CreateEMFModelWizardDataModel dataModel;

    /**
     * Default constructor for this page.
     * 
     * @param packageRegistry
     *            an {@link EPackage.Registry}.
     * @param dataModel
     *            the data model used by this page.
     */
    public SelectEMFMetamodelWizardPage(EPackage.Registry packageRegistry, CreateEMFModelWizardDataModel dataModel) {
        super("SelectEMFMetamodelWizardPage"); //$NON-NLS-1$
        this.packageRegistry = packageRegistry;
        this.dataModel = dataModel;
        setTitle(Messages.SelectEMFMetamodelWizardPage_title);
        setDescription(Messages.SelectEMFMetamodelWizardPage_description);
    }

    /**
     * Sets the filter pattern.
     *
     * @param filter
     *            the filter pattern.
     */
    public void setMetamodelFilter(String filter) {
        if (this.metamodelFilterText == null) {
            this.metamodelFilter = filter;
        } else {
            this.metamodelFilterText.setText(filter);
        }
    }

    /**
     * Create contents of the wizard.
     * 
     * @param parent
     *            the parent composite.
     */
    public void createControl(Composite parent) {
        Composite container = new Composite(parent, SWT.NULL);

        setControl(container);
        container.setLayout(new GridLayout(1, false));

        CLabel lblSelectMetamodel = new CLabel(container, SWT.NONE);
        lblSelectMetamodel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
        lblSelectMetamodel.setText(Messages.SelectEMFMetamodelWizardPage_metamodelLabel);

        createMetamodelFilterText(container);
        createMetamodelFilteredList(container);
        setMetamodelFilter("*"); //$NON-NLS-1$

        createMetamodelDocumentation(container);
    }

    /**
     * Create the text widget used to filter elements in the filtered list.
     * 
     * @param parent
     *            the parent composite.
     */
    private void createMetamodelFilterText(Composite parent) {
        this.metamodelFilterText = new Text(parent, SWT.BORDER);
        this.metamodelFilterText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        this.metamodelFilterText.setText(this.metamodelFilter == null ? "" : this.metamodelFilter); //$NON-NLS-1$
        this.metamodelFilterText.addListener(SWT.Modify, event -> this.metamodelFilteredList.setFilter(this.metamodelFilterText.getText()));
        this.metamodelFilterText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.keyCode == SWT.ARROW_DOWN) {
                    metamodelFilteredList.setFocus();
                }
            }
        });
    }

    /**
     * Create the filtered list widget used to display the {@link EPackage}s contained in the given
     * {@link #packageRegistry}.
     * 
     * @param parent
     *            the parent composite.
     */
    private void createMetamodelFilteredList(Composite parent) {
        this.metamodelFilteredList = new FilteredList(parent, SWT.BORDER | SWT.FULL_SELECTION | SWT.V_SCROLL | SWT.H_SCROLL | SWT.SINGLE, new MetamodelsListLabelProvider(), true, false, false);
        this.metamodelFilteredList.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
        this.metamodelFilteredList.setElements(this.packageRegistry.entrySet().toArray());
        this.metamodelFilteredList.setFilter(this.metamodelFilter == null ? "" : this.metamodelFilter); //$NON-NLS-1$

        this.metamodelFilteredList.addSelectionListener(new SelectionAdapter() {
            @SuppressWarnings("unchecked")
            @Override
            public void widgetSelected(SelectionEvent e) {
                Object[] selection = metamodelFilteredList.getSelection();
                if (selection.length == 1) {
                    dataModel.setSelectedPackage(((Entry<String, Object>) selection[0]).getValue());
                    String documentation = getDocumentation(dataModel.getSelectedPackage());
                    if (documentationBrowser != null) {
                        documentationBrowser.setText(documentation);
                    }
                    setPageComplete(true);
                } else {
                    dataModel.setSelectedPackage(null);
                    if (documentationBrowser != null) {
                        documentationBrowser.setText(""); //$NON-NLS-1$
                    }
                    setPageComplete(false);
                }
            }
        });
    }

    /**
     * Create a browser that allows to display documentation related to the selected metamodel.
     * 
     * @param parent
     *            the parent composite.
     */
    private void createMetamodelDocumentation(Composite parent) {
        Label lblDocumentation = new Label(parent, SWT.NONE);
        lblDocumentation.setText(Messages.SelectEMFMetamodelWizardPage_documentationLabel);

        try {
            this.documentationBrowser = new Browser(parent, SWT.BORDER);
            this.documentationBrowser.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 2));
        } catch (SWTError error) {
            /*
             * The browser could not be created: "hide" the "Documentation" label.
             */
            lblDocumentation.setText(""); //$NON-NLS-1$
        }
    }

    /**
     * Get the qualified name of the given {@link EPackage}.
     * 
     * @param ePackage
     *            the given {@link EPackage}.
     * @return the qualified name of the given {@link EPackage}.
     */
    private String getQualifiedName(EPackage ePackage) {
        String ePackageQualifiedName = ""; //$NON-NLS-1$
        if (ePackage != null) {
            EPackage eSuperPackage = ePackage.getESuperPackage();
            String eSuperPackageName = getQualifiedName(eSuperPackage);
            if (eSuperPackageName != null && !eSuperPackageName.isEmpty()) {
                ePackageQualifiedName += eSuperPackageName;
                ePackageQualifiedName += PACKAGE_SEPARATOR;
            }
            ePackageQualifiedName += ePackage.getName();
        }
        return ePackageQualifiedName;
    }

    /**
     * Get the label for the given {@link EPackage} from the registry of EPackageExtraData.
     * 
     * @see EPackageExtraDataRegistry
     * @param ePackage
     *            the given {@link EPackage}.
     * @return the label for the given {@link EPackage}, or null if it can't be found.
     */
    private String getLabelFromEPackageExtraData(EPackage ePackage) {
        EPackageMetaData metaData = DslCommonPlugin.INSTANCE.getEPackageMetaData(ePackage.getNsURI());
        if (metaData != null) {
            return metaData.getDisplayName();
        } else {
            return null;
        }
    }

    /**
     * Get the documentation for the given {@link EPackage} from the registry of EPackageExtraData.
     * 
     * @see EPackageExtraDataRegistry
     * @param ePackage
     *            the given {@link EPackage}.
     * @return the documentation for the given {@link EPackage}, or null if it can't be found.
     */
    private String getDocumentationFromEPackageExtraData(EPackage ePackage) {
        EPackageMetaData metaData = DslCommonPlugin.INSTANCE.getEPackageMetaData(ePackage.getNsURI());
        if (metaData != null) {
            return metaData.getDocumentation();
        } else {
            return null;
        }
    }

    /**
     * Get the documentation for the given {@link EPackage} from the registry of EPackageExtraData.
     * 
     * @see EPackageExtraDataRegistry
     * @param ePackage
     *            the given {@link EPackage}.
     * @return the documentation for the given {@link EPackage}, or null if it can't be found.
     */
    private String getDocumentation(EPackage ePackage) {
        if (ePackage == null) {
            return null;
        } else {
            String documentation = getDocumentationFromEPackageExtraData(ePackage);
            if (documentation == null) {
                documentation = "<code>" + ePackage.getNsURI() + "</code>"; //$NON-NLS-1$//$NON-NLS-2$
            } else {
                documentation = documentation + "<br><br><smaller><code>" + ePackage.getNsURI() + "</code></smaller>"; //$NON-NLS-1$ //$NON-NLS-2$
            }
            return documentation;
        }
    }

    /**
     * A label provider for the {@link SelectEMFMetamodelWizardPage#metamodelFilteredList}.
     */
    @SuppressWarnings("unchecked")
    private final class MetamodelsListLabelProvider extends LabelProvider {
        @Override
        public String getText(Object element) {
            if (element instanceof Entry) {
                String label = null;
                Object value = ((Entry<String, Object>) element).getValue();
                if (value instanceof EPackage) {
                    label = getLabelFromEPackageExtraData((EPackage) value);
                    if (label == null) {
                        label = getQualifiedName((EPackage) value);
                    }
                } else {
                    label = ((Entry<String, Object>) element).getKey();
                }
                return label;
            }
            return super.getText(element);
        }

        @Override
        public Image getImage(Object element) {
            return ePacakgeIcon;
        }
    }
}
