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
package org.eclipse.sirius.business.api.logger;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * Interface that log runtime errors.
 * 
 * @author smonnier
 * 
 */
public interface RuntimeLogger {

    /**
     * Add error entry to the logger.
     * 
     * @param odesignObject
     *            EObject where the error come from
     * @param feature
     *            element of the EObject where the error come from. May be
     *            <code>null</code>.
     * @param message
     *            error message we want to display
     */
    void error(EObject odesignObject, EStructuralFeature feature, String message);

    /**
     * Add error entry to the logger.
     * 
     * @param odesignObject
     *            EObject where the error come from
     * @param feature
     *            element of the EObject where the error come from. May be
     *            <code>null</code>.
     * @param exception
     *            a low-level exception, or <code>null</code> if not applicable
     */
    void error(EObject odesignObject, EStructuralFeature feature, Throwable exception);

    /**
     * Add error entry to the logger.
     * 
     * @param odesignObject
     *            EObject where the warning come from
     * @param feature
     *            element of the EObject where the warning come from. May be
     *            <code>null</code>.
     * @param exception
     *            a low-level exception, or <code>null</code> if not applicable
     */
    void warning(EObject odesignObject, EStructuralFeature feature, Throwable exception);

    /**
     * Add error entry to the logger.
     * 
     * @param odesignObject
     *            EObject where the warning come from
     * @param feature
     *            element of the EObject where the warning come from. May be
     *            <code>null</code>.
     * @param message
     *            error message we want to display
     */
    void warning(EObject odesignObject, EStructuralFeature feature, String message);

    /**
     * Add error entry to the logger.
     * 
     * @param odesignObject
     *            EObject where the info come from
     * @param feature
     *            element of the EObject where the info come from. May be
     *            <code>null</code>.
     * @param message
     *            error message we want to display
     */
    void info(EObject odesignObject, EStructuralFeature feature, String message);

    /**
     * Add error entry to the logger.
     * 
     * @param odesignObject
     *            EObject where the info come from
     * @param feature
     *            element of the EObject where the info come from. May be
     *            <code>null</code>.
     * @param exception
     *            a low-level exception, or <code>null</code> if not applicable
     */
    void info(EObject odesignObject, EStructuralFeature feature, Throwable exception);

    /**
     * Clears all logged entries.
     */
    void clearAll();

    /**
     * Clears all logged entries for the EObject.
     * 
     * @param eObject
     *            EObject we want to clearAll logged entries
     */
    void clear(EObject eObject);
}
