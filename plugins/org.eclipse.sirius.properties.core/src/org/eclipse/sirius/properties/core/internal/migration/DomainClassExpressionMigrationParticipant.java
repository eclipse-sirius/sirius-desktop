/*******************************************************************************
 * Copyright (c) 2016, 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.properties.core.internal.migration;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.business.api.migration.AbstractVSMMigrationParticipant;
import org.eclipse.sirius.properties.PropertiesPackage;
import org.osgi.framework.Version;

/**
 * This class is responsible for the migration of the structural feature
 * "domainClassExpression" to "iterableExpression" in a dynamic mapping for.
 * 
 * @author sbegaudeau
 */
public class DomainClassExpressionMigrationParticipant extends AbstractVSMMigrationParticipant {

    private static final Version MIGRATION_VERSION = new Version("11.0.0.201608291200"); //$NON-NLS-1$

    private static final String DOMAIN_CLASS_EXPRESSION_NAME = "domainClassExpression"; //$NON-NLS-1$

    @Override
    public Version getMigrationVersion() {
        return MIGRATION_VERSION;
    }

    @Override
    public EStructuralFeature getAttribute(EClass eClass, String name, String loadedVersion) {
        if (Version.parseVersion(loadedVersion).compareTo(MIGRATION_VERSION) < 0 && PropertiesPackage.Literals.ABSTRACT_DYNAMIC_MAPPING_FOR_DESCRIPTION.equals(eClass) && DOMAIN_CLASS_EXPRESSION_NAME.equals(name)) {
            return PropertiesPackage.Literals.ABSTRACT_DYNAMIC_MAPPING_FOR_DESCRIPTION__ITERABLE_EXPRESSION;
        }
        return super.getAttribute(eClass, name, loadedVersion);
    }

}
