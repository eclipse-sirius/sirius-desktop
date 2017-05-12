/**
 * Copyright (c) 2017 Obeo
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Obeo - initial API and implementation
 */
package org.eclipse.sirius.ui.tools.internal.wizards.newmodel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.provider.EcoreEditPlugin;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.common.tools.api.ecore.EPackageMetaData;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.FilteredList;

/**
 * A wizard page allowing to select a root element ({@link EClass}) from an {@link EPackage}.
 * 
 * @see CreateEMFModelWizard
 * 
 * @author <a href="mailto:axel.richard@obeo.fr">Axel Richard</a>
 */
public class SelectRootElementWizardPage extends WizardPage implements PropertyChangeListener {

    /**
     * The icon used to represent EClasses.
     */
    private Image eClassIcon = ExtendedImageRegistry.getInstance().getImage(EcoreEditPlugin.INSTANCE.getImage("full/obj16/EClass")); //$NON-NLS-1$

    /** The text field allowing to filter the list of root elements. */
    private Text rootElementText;

    /**
     * The checkbox allowing to only display root elements that are not child of other elements.
     */
    private Button rootElementCheckbox;

    /** The list of root elements. */
    private FilteredList rootElementFilteredList;

    /** The filter used by the text field and the filtered list. */
    private String rootElementFilter;

    /** The data model used by the wizard and its pages. */
    private CreateEMFModelWizardDataModel dataModel;

    /**
     * Default constructor for this page.
     * 
     * @param dataModel
     *            the data model used by this page.
     */
    public SelectRootElementWizardPage(CreateEMFModelWizardDataModel dataModel) {
        super("SelectRootElementWizardPage"); //$NON-NLS-1$
        this.dataModel = dataModel;
        setTitle(Messages.SelectRootElementWizardPage_title);
        setDescription(Messages.SelectRootElementWizardPage_description);
    }

    /**
     * Sets the filter pattern.
     *
     * @param filter
     *            the filter pattern.
     */
    public void setRootElementFilter(String filter) {
        if (this.rootElementText == null) {
            this.rootElementFilter = filter;
        } else {
            this.rootElementText.setText(filter);
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

        CLabel lblSelectRootElement = new CLabel(container, SWT.NONE);
        lblSelectRootElement.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
        lblSelectRootElement.setText(Messages.SelectRootElementWizardPage_label);

        createRootElementFilterText(container);
        createRootElementCheckbox(container);
        createRootElementFilteredList(container);
        setRootElementFilter("*"); //$NON-NLS-1$
        this.rootElementCheckbox.setSelection(true);
    }

    /**
     * Create the text widget used to filter elements in the filtered list.
     * 
     * @param parent
     *            the parent composite.
     */
    private void createRootElementFilterText(Composite parent) {
        this.rootElementText = new Text(parent, SWT.BORDER);
        this.rootElementText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        this.rootElementText.setText(this.rootElementFilter == null ? "" : this.rootElementFilter); //$NON-NLS-1$
        this.rootElementText.addListener(SWT.Modify, event -> this.rootElementFilteredList.setFilter(this.rootElementText.getText()));
        this.rootElementText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.keyCode == SWT.ARROW_DOWN) {
                    rootElementFilteredList.setFocus();
                }
            }
        });
    }

    /**
     * Create the checkbox widget used to display only elements that are not child of other elements in the filtered
     * list.
     * 
     * @param parent
     *            the parent composite.
     */
    private void createRootElementCheckbox(Composite parent) {
        this.rootElementCheckbox = new Button(parent, SWT.CHECK);
        this.rootElementCheckbox.setLayoutData(new GridData(SWT.FILL, SWT.LEFT, true, false, 1, 1));
        this.rootElementCheckbox.setText(Messages.SelectRootElementWizardPage_checkboxLabel);
        this.rootElementCheckbox.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                updateRootElementFilteredList();
            }
        });
    }

    /**
     * Create the filtered list widget used to display the root elements contained in the given
     * {@link #selectedPackage}.
     * 
     * @param parent
     *            the parent composite.
     */
    private void createRootElementFilteredList(Composite parent) {
        this.rootElementFilteredList = new FilteredList(parent, SWT.BORDER | SWT.FULL_SELECTION | SWT.V_SCROLL | SWT.H_SCROLL | SWT.SINGLE, new RootElementsListLabelProvider(), true, false, false);
        this.rootElementFilteredList.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
        this.rootElementFilteredList.setFilter(this.rootElementFilter == null ? "" : this.rootElementFilter); //$NON-NLS-1$
        this.rootElementFilteredList.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                updateSelectedRootElement();
            }
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (CreateEMFModelWizardDataModel.SELECTED_PACKAGE_EVENT.equals(evt.getPropertyName())) {
            updateRootElementFilteredList();
            updateSelectedRootElement();
        }
    }

    /**
     * Update the selected root element and validate the page.
     */
    private void updateSelectedRootElement() {
        Object[] selection = this.rootElementFilteredList.getSelection();
        if (selection.length == 1) {
            this.dataModel.setSelectedRootElement((EClass) selection[0]);
            setPageComplete(true);
        } else {
            EClass preferredRootElement = getPreferredRootElementFromEPackageExtraData(this.dataModel.getSelectedPackage());
            if (preferredRootElement != null) {
                this.dataModel.setSelectedRootElement(preferredRootElement);
                setPageComplete(true);
            } else {
                this.dataModel.setSelectedRootElement(null);
                setPageComplete(false);
            }
        }
    }

    /**
     * Update the root element filtered list according to the value of the {@link #rootElementCheckbox}.
     */
    private void updateRootElementFilteredList() {
        if (this.rootElementCheckbox != null) {
            Object[] classesArray;
            if (this.rootElementCheckbox.getSelection()) {
                classesArray = getFilteredConcreteClasses(this.dataModel.getSelectedPackage()).toArray();
            } else {
                classesArray = getConcreteClasses(this.dataModel.getSelectedPackage()).toArray();
            }
            this.rootElementFilteredList.setElements(classesArray);
            EClass preferredRootElement = getPreferredRootElementFromEPackageExtraData(this.dataModel.getSelectedPackage());
            if (preferredRootElement != null) {
                this.rootElementFilteredList.setSelection(new Object[] { preferredRootElement });
            }
        }
    }

    /**
     * Get the concrete classes from the given {@link EPackage} (classes that are not abstract and that are not
     * interfaces).
     * 
     * @param ePackage
     *            the given {@link EPackage}.
     * @return the concrete classes from the given {@link EPackage}.
     */
    private Collection<EClass> getConcreteClasses(EPackage ePackage) {
        Collection<EClass> concreteClasses = new HashSet<>();
        if (ePackage != null) {
            for (EClassifier eClassifier : ePackage.getEClassifiers()) {
                if (eClassifier instanceof EClass && !((EClass) eClassifier).isAbstract() && !((EClass) eClassifier).isInterface()) {
                    concreteClasses.add((EClass) eClassifier);
                }
            }
        }
        return concreteClasses;
    }

    /**
     * Get the filtered concrete classes from the given {@link EPackage} (classes that are not abstract and that are not
     * interfaces, and that are not child of other classes).
     * 
     * @param ePackage
     *            the given {@link EPackage}.
     * @return the filtered concrete classes from the given {@link EPackage}.
     */
    private Collection<EClass> getFilteredConcreteClasses(EPackage ePackage) {
        /*
         * We'll only consider actually instanciable classes.
         */
        Collection<EClass> concreteClasses = getConcreteClasses(ePackage);

        /*
         * If we have explicit metadata associated with the EPackage, use the suggested roots.
         */
        if (ePackage != null) {
            String nsURI = ePackage.getNsURI();
            EPackageMetaData metaData = DslCommonPlugin.INSTANCE.getEPackageMetaData(nsURI);
            List<EClass> roots = new ArrayList<>();
            if (metaData != null && !metaData.getSuggestedRoots().isEmpty()) {
                roots = concreteClasses.stream().filter(c -> metaData.getSuggestedRoots().contains(c.getName())).collect(Collectors.toList());
            }
            if (!roots.isEmpty()) {
                return roots;
            }
        }

        /*
         * Otherwise, or if there is no instanciable suggested root, try to infer good candidates from the metamodel's
         * structure (i.e. prefer elements which are not contained by anything).
         */
        return inferRootElementsCandidates(concreteClasses);
    }

    private Collection<EClass> inferRootElementsCandidates(Collection<EClass> concreteClasses) {
        Collection<EClass> filteredConcreteClasses = new HashSet<>(concreteClasses);
        for (EClass eClass : concreteClasses) {
            if (filteredConcreteClasses.contains(eClass)) {
                eClass.getEAllReferences().stream().filter(EReference::isContainment).forEach(eReference -> {
                    EClassifier eType = eReference.getEType();
                    if (concreteClasses.contains(eType) && eType != eClass) {
                        filteredConcreteClasses.remove(eType);
                    } else {
                        concreteClasses.stream().filter(c -> c.getEAllSuperTypes().contains(eType)).forEach(c -> filteredConcreteClasses.remove(c));
                    }
                });
            }
        }
        return filteredConcreteClasses;
    }

    /**
     * Get the preferred root element for the given {@link EPackage} from the registry of EPackageExtraData.
     * 
     * @see EPackageExtraDataRegistry
     * @param ePackage
     *            the given {@link EPackage}.
     * @return the preferred root element for the given {@link EPackage}, or null if it can't be found.
     */
    private EClass getPreferredRootElementFromEPackageExtraData(EPackage ePackage) {
        if (ePackage != null) {
            String nsURI = ePackage.getNsURI();
            EPackageMetaData metaData = DslCommonPlugin.INSTANCE.getEPackageMetaData(nsURI);
            if (metaData != null && !metaData.getSuggestedRoots().isEmpty()) {
                EClassifier result = ePackage.getEClassifier(metaData.getSuggestedRoots().get(0));
                if (result instanceof EClass) {
                    return (EClass) result;
                }
            }
        }
        return null;
    }

    /**
     * A label provider for the {@link SelectRootElementWizardPage#rootElementFilteredList}.
     */
    private class RootElementsListLabelProvider extends LabelProvider {

        @Override
        public String getText(Object element) {
            if (element instanceof EClass) {
                return ((EClass) element).getName();
            }
            return super.getText(element);
        }

        @Override
        public Image getImage(Object element) {
            return eClassIcon;
        }
    }

}
