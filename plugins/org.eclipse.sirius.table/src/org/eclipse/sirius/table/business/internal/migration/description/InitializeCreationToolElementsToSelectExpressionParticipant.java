/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.business.internal.migration.description;

import java.util.Iterator;

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.business.api.migration.AbstractVSMMigrationParticipant;
import org.eclipse.sirius.common.tools.api.interpreter.StandardServices;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.table.metamodel.table.description.CreateTool;
import org.eclipse.sirius.table.metamodel.table.description.TableDescription;
import org.eclipse.sirius.viewpoint.description.DescriptionFactory;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.sirius.viewpoint.description.JavaExtension;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.osgi.framework.Version;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;

/**
 * Set an empty expression as "Elements To Select" expression on table creation
 * tool. This is necessary, as we want to stay iso functional and because the
 * default behavior now selects new created representation elements after
 * creation tool.
 * 
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 *
 */
public class InitializeCreationToolElementsToSelectExpressionParticipant extends AbstractVSMMigrationParticipant {

    /**
     * The Sirius version for which this migration is added.
     */
    public static final Version MIGRATION_VERSION = new Version("10.1.0.201507271600"); //$NON-NLS-1$

    /**
     * Expression used to initialize expression.
     */
    public static final String ELEMENTS_TO_SELECT_EXPRESSION = "service:stdEmptyCollection"; //$NON-NLS-1$

    private static final String JAVA_EXTENSION_QUALIFIED_NAME = StandardServices.class.getName();

    @Override
    public Version getMigrationVersion() {
        return MIGRATION_VERSION;
    }

    @Override
    protected void postLoad(Group group, Version loadedVersion) {
        if (loadedVersion.compareTo(MIGRATION_VERSION) < 0) {
            EList<Viewpoint> ownedViewpoints = group.getOwnedViewpoints();
            for (Viewpoint viewpoint : ownedViewpoints) {
                boolean atLeastOneChange = false;
                for (TableDescription tableDescription : Iterables.filter(viewpoint.getOwnedRepresentations(), TableDescription.class)) {
                    Iterator<CreateTool> creationTools = Iterators.filter(tableDescription.eAllContents(), CreateTool.class);
                    while (creationTools.hasNext()) {
                        CreateTool tool = creationTools.next();
                        if (StringUtil.isEmpty(tool.getElementsToSelect())) {
                            tool.setElementsToSelect(ELEMENTS_TO_SELECT_EXPRESSION);
                            atLeastOneChange = true;
                        }
                    }
                }

                if (atLeastOneChange) {
                    // Add the Java Extension to use the service:
                    if (!Iterables.any(viewpoint.getOwnedJavaExtensions(), new Predicate<JavaExtension>() {
                        @Override
                        public boolean apply(JavaExtension input) {
                            return JAVA_EXTENSION_QUALIFIED_NAME.equals(input.getQualifiedClassName());
                        }
                    })) {
                        JavaExtension javaExtension = DescriptionFactory.eINSTANCE.createJavaExtension();
                        javaExtension.setQualifiedClassName(JAVA_EXTENSION_QUALIFIED_NAME);
                        viewpoint.getOwnedJavaExtensions().add(javaExtension);
                    }
                }
            }
        }
    }
}
