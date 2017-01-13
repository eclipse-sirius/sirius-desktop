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
package org.eclipse.sirius.tests.ui.properties.internal.converters;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.eclipse.eef.EEFViewDescription;
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
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.sirius.properties.PropertiesPackage;
import org.eclipse.sirius.properties.ViewExtensionDescription;
import org.eclipse.sirius.properties.core.api.SiriusInputDescriptor;
import org.eclipse.sirius.ui.properties.internal.tabprovider.ViewDescriptionConverter;
import org.junit.Test;

/**
 * Tests of the IDescriptionConverters.
 * 
 * @author sbegaudeau
 */
@SuppressWarnings("restriction")
public class ConverterTests {
    /**
     * The path of the Sirius model in the bundle.
     */
    private static final String SIRIUS_MODEL_PATH = "/data/sirius.odesign";

    /**
     * This test is used to ensure the proper transformation of a Sirius
     * Properties model into an EEF one.
     */
    @Test
    public void testDescriptionConverter() {
        EObject siriusEObject = this.convert(this.load(SIRIUS_MODEL_PATH).getContents().get(0));
        EObject eefEObject = this.load("/data/eef.xmi").getContents().get(0);

        IComparisonScope scope = new DefaultComparisonScope(siriusEObject, eefEObject, null);
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
     * Transforms the given Sirius EObject into an EEF EObject.
     * 
     * @param eObject
     *            The Sirius EObject
     * @return The EEF EObject created form the Sirius one
     */
    private EObject convert(EObject eObject) {
        if (eObject instanceof ViewExtensionDescription) {
            ViewExtensionDescription viewExtensionDescription = (ViewExtensionDescription) eObject;
            ViewDescriptionConverter converter = new ViewDescriptionConverter(viewExtensionDescription.getPages());
            SiriusInputDescriptor input = new SiriusInputDescriptor(EcoreFactory.eINSTANCE.createEObject());
            EEFViewDescription eefViewDescription = converter.convert(input);

            ResourceSet resourceSet = new ResourceSetImpl();
            resourceSet.getPackageRegistry().put(EefPackage.eNS_URI, EefPackage.eINSTANCE);
            resourceSet.getPackageRegistry().put(PropertiesPackage.eNS_URI, PropertiesPackage.eINSTANCE);
            resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl()); //$NON-NLS-1$
            Resource resource = resourceSet.createResource(URI.createURI(SIRIUS_MODEL_PATH));
            resource.getContents().add(eefViewDescription);

            return eefViewDescription;
        }
        return null;
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
        resource.setURI(URI.createURI(SIRIUS_MODEL_PATH));
        return resource;
    }
}
