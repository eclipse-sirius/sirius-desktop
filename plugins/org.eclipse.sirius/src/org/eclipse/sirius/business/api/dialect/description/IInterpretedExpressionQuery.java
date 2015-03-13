/*******************************************************************************
 * Copyright (c) 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.api.dialect.description;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.sirius.common.tools.api.interpreter.VariableType;
import org.eclipse.sirius.ext.base.Option;

/**
 * Query inspecting the Description of representations to determine useful
 * informations (like the target domain classes and the available packages for
 * Interpreted expressions.
 * 
 * @since 0.9.0
 * @author alagarde
 * 
 */
public interface IInterpretedExpressionQuery {

    /**
     * Returns the expected DomainClass(es) for the given expression.
     * 
     * @return the expected DomainClass(es) name for the given expression
     */
    Option<Collection<String>> getTargetDomainClasses();

    /**
     * Returns the list of EPackages to import to be able to interpret the given
     * expression.
     * 
     * @return the list of EPackages to import
     */
    Collection<EPackage> getPackagesToImport();

    /**
     * Returns the list of dependencies to import to be able to interpret the
     * given expression.
     * 
     * @return the list of dependencies to import.
     */
    Collection<String> getDependencies();

    /**
     * Returns a map representing all available variables. Keys of the map are
     * the variable names, and values of the map their type.
     * 
     * @return a map representing all available variables
     */
    Map<String, VariableType> getAvailableVariables();

}
