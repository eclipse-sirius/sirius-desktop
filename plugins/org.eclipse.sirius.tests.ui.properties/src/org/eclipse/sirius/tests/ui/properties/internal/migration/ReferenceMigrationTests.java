/*******************************************************************************
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.ui.properties.internal.migration;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.eclipse.eef.EefPackage;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.compare.EMFCompare;
import org.eclipse.emf.compare.match.DefaultComparisonFactory;
import org.eclipse.emf.compare.match.DefaultEqualityHelperFactory;
import org.eclipse.emf.compare.match.DefaultMatchEngine;
import org.eclipse.emf.compare.match.IComparisonFactory;
import org.eclipse.emf.compare.match.IMatchEngine;
import org.eclipse.emf.compare.match.eobject.IEObjectMatcher;
import org.eclipse.emf.compare.match.impl.MatchEngineFactoryImpl;
import org.eclipse.emf.compare.match.impl.MatchEngineFactoryRegistryImpl;
import org.eclipse.emf.compare.scope.DefaultComparisonScope;
import org.eclipse.emf.compare.scope.IComparisonScope;
import org.eclipse.emf.compare.utils.UseIdentifiers;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.sirius.properties.PropertiesPackage;
import org.eclipse.sirius.viewpoint.description.Group;
import org.junit.Test;

/**
 * Tests of the migration of the reference widget in an odesign file.
 * 
 * @author sbegaudeau
 */
public class ReferenceMigrationTests {
    /**
     * The path of the Sirius model before the migration in the bundle.
     */
    private static final String OLD_MODEL_PATH = "/data/migration/before_reference.odesign";

    /**
     * The path of the Sirius model after the migration in the bundle.
     */
    private static final String NEW_MODEL_PATH = "/data/migration/after_reference.odesign";

    /**
     * This test will ensure that the reference widget is properly transformed
     * into a label, hyperlink and a list.
     */
    @Test
    public void testReferenceMigration() {
        EObject beforeMigration = this.load(OLD_MODEL_PATH).getContents().get(0);
        if (beforeMigration instanceof Group && ((Group) beforeMigration).getExtensions().size() == 1) {
            beforeMigration = ((Group) beforeMigration).getExtensions().get(0);
        }

        EObject afterMigration = this.load(NEW_MODEL_PATH).getContents().get(0);
        if (afterMigration instanceof Group && ((Group) afterMigration).getExtensions().size() == 1) {
            afterMigration = ((Group) afterMigration).getExtensions().get(0);
        }

        IComparisonScope scope = new DefaultComparisonScope(beforeMigration, afterMigration, null);
        IEObjectMatcher matcher = DefaultMatchEngine.createDefaultEObjectMatcher(UseIdentifiers.NEVER);
        IComparisonFactory comparisonFactory = new DefaultComparisonFactory(new DefaultEqualityHelperFactory());
        IMatchEngine.Factory matchEngineFactory = new MatchEngineFactoryImpl(matcher, comparisonFactory);
        matchEngineFactory.setRanking(20);
        IMatchEngine.Factory.Registry matchEngineRegistry = new MatchEngineFactoryRegistryImpl();
        matchEngineRegistry.add(matchEngineFactory);

        EMFCompare comparator = EMFCompare.builder().setMatchEngineFactoryRegistry(matchEngineRegistry).build();
        Comparison comparison = comparator.compare(scope);
        List<Diff> differences = comparison.getDifferences();

        assertEquals(0, differences.size());
    }

    /**
     * Loads the resource with the given URI in a new resource set and return
     * it.
     * 
     * @param uri
     *            The URI of the resource to load
     * @return The resource loaded
     */
    private Resource load(String uri) {
        ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getPackageRegistry().put(EefPackage.eNS_URI, EefPackage.eINSTANCE);
        resourceSet.getPackageRegistry().put(PropertiesPackage.eNS_URI, PropertiesPackage.eINSTANCE);
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl()); //$NON-NLS-1$
        Resource resource = resourceSet.getResource(URI.createFileURI(System.getProperty("user.dir") + uri), true);
        resource.setURI(URI.createURI(OLD_MODEL_PATH));
        return resource;
    }
}
