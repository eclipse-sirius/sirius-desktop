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
package org.eclipse.sirius.business.api.query;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * POJO representing the project dependencies.
 * 
 * @author lfasani
 */
public class SiriusProjectDependencies {
    Set<String> imageProjectsDirectDependencies = new LinkedHashSet<>();

    Set<String> generalProjectDirectDependencies = new LinkedHashSet<>();

    Set<String> generalProjectTransitiveDependencies = new LinkedHashSet<>();

    Set<String> notAnalysedGeneralProjectDependencies = new LinkedHashSet<>();

    /**
     * Default constructor.
     */
    public SiriusProjectDependencies(Set<String> imageProjectsDirectDependencies, Set<String> generalProjectDirectDependencies, Set<String> generalProjectTransitiveDependencies,
            Set<String> notAnalysedGeneralProjectDependencies) {
        super();
        this.imageProjectsDirectDependencies = imageProjectsDirectDependencies;
        this.generalProjectDirectDependencies = generalProjectDirectDependencies;
        this.generalProjectTransitiveDependencies = generalProjectTransitiveDependencies;
        this.notAnalysedGeneralProjectDependencies = notAnalysedGeneralProjectDependencies;
    }

    public Set<String> getImageProjectsDirectDependencies() {
        return imageProjectsDirectDependencies;
    }

    public Set<String> getNotAnalysedGeneralProjectDependencies() {
        return notAnalysedGeneralProjectDependencies;
    }

    public Set<String> getGeneralProjectDirectDependencies() {
        return generalProjectDirectDependencies;
    }

    public Set<String> getGeneralProjectTransitiveDependencies() {
        return generalProjectTransitiveDependencies;
    }

}
