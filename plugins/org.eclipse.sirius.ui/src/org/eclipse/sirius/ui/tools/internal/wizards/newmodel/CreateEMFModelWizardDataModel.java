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

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

/**
 * A data model that works along with {@link CreateEMFModelWizard}.
 * 
 * @see CreateEMFModelWizard
 * 
 * @author <a href="mailto:axel.richard@obeo.fr">Axel Richard</a>
 */
public class CreateEMFModelWizardDataModel {

    /** The constant for the event representing a selection of a new package. */
    public static final String SELECTED_PACKAGE_EVENT = "selectedPackage"; //$NON-NLS-1$

    /**
     * The constant for the event representing a selection of a new root element.
     */
    public static final String SELECTED_ROOT_ELEMENT_EVENT = "selectedRootElement"; //$NON-NLS-1$

    /** The PropertyChangeSupport to notify changes about this data model. */
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    /** The selected {@link EPackage}. */
    private Object selectedPackage;

    /** The selected root element. */
    private EClass selectedRootElement;

    /**
     * Add a PropertyChangeListener to this data model.
     * 
     * @param listener
     *            The PropertyChangeListener to be added.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    /**
     * Remove a PropertyChangeListener from this data model.
     * 
     * @param listener
     *            The PropertyChangeListener to be removed.
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }

    /**
     * Get the selected {@link EPackage} from the wizard.
     * 
     * @return the selected {@link EPackage} from the wizard.
     */
    public EPackage getSelectedPackage() {
        EPackage ePackage = null;
        if (this.selectedPackage instanceof EPackage) {
            ePackage = (EPackage) this.selectedPackage;
        } else if (this.selectedPackage instanceof EPackage.Descriptor) {
            ePackage = ((EPackage.Descriptor) this.selectedPackage).getEPackage();
        }
        return ePackage;
    }

    /**
     * Set the selected {@link EPackage}.
     * 
     * @param selectedPackage
     *            the selected {@link EPackage}
     */
    public void setSelectedPackage(Object selectedPackage) {
        Object oldSelectedPackage = this.selectedPackage;
        this.selectedPackage = selectedPackage;
        this.pcs.firePropertyChange(SELECTED_PACKAGE_EVENT, oldSelectedPackage, this.selectedPackage);
    }

    /**
     * Get the selected root element from the wizard.
     * 
     * @return the selected root element from the wizard.
     */
    public EClass getSelectedRootElement() {
        return selectedRootElement;
    }

    /**
     * Set the selected root element.
     * 
     * @param selectedRootElement
     *            the selected root element.
     */
    public void setSelectedRootElement(EClass selectedRootElement) {
        EClass oldSelectedRootElement = this.selectedRootElement;
        this.selectedRootElement = selectedRootElement;
        this.pcs.firePropertyChange(SELECTED_ROOT_ELEMENT_EVENT, oldSelectedRootElement, this.selectedRootElement);
    }
}
