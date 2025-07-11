/*******************************************************************************
 * Copyright (c) 2015, 2024 Obeo.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.tools.api.interpreter;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.ecore.extender.business.api.accessor.EcoreMetamodelDescriptor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.MetamodelDescriptor;

/**
 * Utility methods for working with IInterpreterContexts.
 * 
 * @author <a href="mailto:cedric.brun@obeo.fr">Cedric Brun</a>
 *
 */
public final class IInterpreterContextUtils {

    private IInterpreterContextUtils() {

    }

    /**
     * Configure the given {@link IInterpreter} instance with the metamodels,
     * dependencies and scope settings provided by the
     * {@link IInterpreterContext}.
     * 
     * @param interpreter
     *            the interpreter to configure.
     * @param context
     *            the context holding the settings.
     */
    public static void configureInterpreter(IInterpreter interpreter, IInterpreterContext context) {
        Set<String> projectsOrBundleInScope = collectProjectsOrPlugins(context);
        interpreter.setProperty(IInterpreter.FILES, projectsOrBundleInScope);

        Collection<MetamodelDescriptor> availableMetamodels = new LinkedHashSet<>();
        for (EPackage pak : context.getAvailableEPackages()) {
            if (pak != null && pak.eResource() != null) {
                availableMetamodels.add(new EcoreMetamodelDescriptor(pak));
            }
        }
        interpreter.activateMetamodels(availableMetamodels);

        interpreter.clearImports();
        for (String dependency : context.getDependencies()) {
            interpreter.addImport(dependency);
        }

    }

    static Set<String> collectProjectsOrPlugins(IInterpreterContext context) {
        /*
         * use the VSM resource to declare dependent project/bundles.
         */
        EObject vsmElement = context.getElement();
        Set<String> projectsOrBundleInScope = new LinkedHashSet<>();
        if (vsmElement != null && vsmElement.eResource() != null) {
            collectProjectName(vsmElement.eResource(), projectsOrBundleInScope);
            if (vsmElement.eResource().getResourceSet() != null) {
                for (Resource other : vsmElement.eResource().getResourceSet().getResources()) {
                    if (other != vsmElement.eResource()) {
                        collectProjectName(other, projectsOrBundleInScope);
                    }
                }
            }
        }
        return projectsOrBundleInScope;
    }

    private static void collectProjectName(Resource eResource, Set<String> projectsOrBundleInScope) {
        if (eResource.getURI() != null && eResource.getURI().segmentCount() >= 2) {
            URI vsmURI = eResource.getURI();
            if (vsmURI.isPlatform()) {
                String bundleOrProjectName = vsmURI.segment(1);
                if (!StringUtil.isEmpty(bundleOrProjectName)) {
                    projectsOrBundleInScope.add(bundleOrProjectName);
                }
            }

        }
    }

    /**
     * Utility method to compare two contexts in order to detect if it is
     * necessary to re-configure an {@link IInterpreter}.
     * 
     * Only the information related to the search scope of an IInterpreter are
     * compared : the available metamodels, the imported classes, the available
     * EPackages.
     * 
     * @param a
     *            a context.
     * @param b
     *            another context.
     * @return true if both context have the same scope definition, false
     *         otherwise.
     */
    public static boolean haveSameScopeDefinition(IInterpreterContext a, IInterpreterContext b) {
        Set<String> aDependencies = new LinkedHashSet<>(a.getDependencies());
        Set<String> bDependencies = new LinkedHashSet<>(b.getDependencies());
        Set<String> aNSURI = collectNSUris(a);
        Set<String> bNSURI = collectNSUris(b);
        Set<String> aProjects = collectProjectsOrPlugins(a);
        Set<String> bProjects = collectProjectsOrPlugins(b);
        return aDependencies.equals(bDependencies) && aNSURI.equals(bNSURI) && aProjects.equals(bProjects);
    }

    private static Set<String> collectNSUris(IInterpreterContext a) {
        Set<String> aNSURI = new LinkedHashSet<>();
        for (EPackage pak : a.getAvailableEPackages()) {
            String nsURI = pak.getNsURI();
            if (!StringUtil.isEmpty(nsURI)) {
                aNSURI.add(nsURI);
            }
        }
        return aNSURI;
    }
}
