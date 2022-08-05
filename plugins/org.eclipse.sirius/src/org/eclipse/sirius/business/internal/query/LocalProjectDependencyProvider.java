/*******************************************************************************
 * Copyright (c) 2022 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.business.internal.query;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.sirius.business.api.query.SiriusProjectDependencies;
import org.eclipse.sirius.business.api.query.SiriusProjectDependencyQuery;
import org.eclipse.sirius.business.internal.session.parser.RepresentationsFileSaxParser;

/**
 * Implementation of {@link IProjectDependencyProvider} for connected project.
 * 
 * @author lfasani
 */
public class LocalProjectDependencyProvider implements IProjectDependencyProvider {

    @Override
    public boolean canHandle(IProject project) {
        return true;
    }

    @Override
    public SiriusProjectDependencies getDirectDependencies(IProject project) {
        Set<String> imageProjectsDirectDependencies = new LinkedHashSet<>();

        Set<String> generalProjectDependencies = new LinkedHashSet<>();

        List<URI> mainAirdURIs = new SiriusProjectQuery(project).getMainAirdURIs();
        for (URI airdResourceURI : mainAirdURIs) {
            String platformString = airdResourceURI.toPlatformString(true);
            IResource airdResource = ResourcesPlugin.getWorkspace().getRoot().findMember(platformString);
            if (airdResource instanceof IFile) {
                RepresentationsFileSaxParser representationsFileSaxParser = new RepresentationsFileSaxParser((IFile) airdResource);
                representationsFileSaxParser.analyze();
                Set<URI> referencedAnalysis = representationsFileSaxParser.getReferencedAnalysis();
                Set<URI> semanticElements = representationsFileSaxParser.getSemanticElements();
                imageProjectsDirectDependencies = representationsFileSaxParser.getImageDependencies();

                Stream.of(referencedAnalysis, semanticElements).flatMap(Set::stream).forEach(uri -> {
                    // a non platform URI means that the resource is local to the project(not a dependency)
                    if (uri.isPlatformResource()) {
                        IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(uri.toPlatformString(true)));
                        IProject fileProject = file.getProject();
                        generalProjectDependencies.add(fileProject.getName());
                    }
                });
            }
        }
        // remove the current project
        generalProjectDependencies.remove(project.getName());
        imageProjectsDirectDependencies.remove(project.getName());

        return new SiriusProjectDependencies(imageProjectsDirectDependencies, generalProjectDependencies, new LinkedHashSet<>(), new LinkedHashSet<>());
    }

    private SiriusProjectDependencies addAllGeneralDependencies(IProject projectToAnalyse, Set<String> allGeneralProjectDependencies, Set<String> notAnalysedGeneralProjectDependencies) {
        SiriusProjectDependencies allDependencies = new SiriusProjectDependencyQuery(projectToAnalyse).getDirectDependencies();

        for (String projectName : allDependencies.getGeneralProjectDirectDependencies()) {
            // test the presence to avoid infinite loop
            if (!allGeneralProjectDependencies.contains(projectName)) {
                IProject currentProject = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
                if (currentProject.exists()) {
                    allGeneralProjectDependencies.add(projectName);
                    addAllGeneralDependencies(currentProject, allGeneralProjectDependencies, notAnalysedGeneralProjectDependencies);
                } else {
                    notAnalysedGeneralProjectDependencies.add(projectName);
                }
            }
        }

        return allDependencies;
    }

    @Override
    public SiriusProjectDependencies getAllDependencies(IProject project) {
        Set<String> allGeneralProjectDependencies = new LinkedHashSet<>();
        Set<String> notAnalysedGeneralProjectDependencies = new LinkedHashSet<>();
        SiriusProjectDependencies directDependencies = addAllGeneralDependencies(project, allGeneralProjectDependencies, notAnalysedGeneralProjectDependencies);
        allGeneralProjectDependencies.removeAll(directDependencies.getGeneralProjectDirectDependencies());

        return new SiriusProjectDependencies(directDependencies.getImageProjectsDirectDependencies(), directDependencies.getGeneralProjectDirectDependencies(), allGeneralProjectDependencies,
                notAnalysedGeneralProjectDependencies);
    }
}
