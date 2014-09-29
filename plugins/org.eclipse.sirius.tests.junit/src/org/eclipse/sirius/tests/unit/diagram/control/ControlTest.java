/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.diagram.control;

import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.EMFCompare;
import org.eclipse.emf.compare.match.DefaultComparisonFactory;
import org.eclipse.emf.compare.match.DefaultEqualityHelperFactory;
import org.eclipse.emf.compare.match.DefaultMatchEngine;
import org.eclipse.emf.compare.match.IComparisonFactory;
import org.eclipse.emf.compare.match.IMatchEngine;
import org.eclipse.emf.compare.match.eobject.IEObjectMatcher;
import org.eclipse.emf.compare.match.impl.MatchEngineFactoryImpl;
import org.eclipse.emf.compare.match.impl.MatchEngineFactoryRegistryImpl;
import org.eclipse.emf.compare.scope.FilterComparisonScope;
import org.eclipse.emf.compare.utils.UseIdentifiers;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.api.session.resource.AirdResource;
import org.eclipse.sirius.business.internal.command.control.ControlCommand;
import org.eclipse.sirius.business.internal.command.control.UncontrolCommand;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;

public class ControlTest extends SiriusDiagramTestCase {

    private static final String PATH = "/data/unit/control";

    private static final String SEMANTIC_MODEL_FILENAME_1 = "/base/parent.uml";

    private static final String SEMANTIC_MODEL_FILENAME_2 = "/controlled/parent.uml";

    private static final String SEMANTIC_MODEL_FILENAME_3 = "/controlled/controlled.uml";

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_SAVE_WHEN_NO_EDITOR.name(), false);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + SEMANTIC_MODEL_FILENAME_1, "/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME_1);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + SEMANTIC_MODEL_FILENAME_2, "/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME_2);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + SEMANTIC_MODEL_FILENAME_3, "/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME_3);
        genericSetUp();
    }

    public void testControl() throws IOException {
        ResourceSet rs = session.getTransactionalEditingDomain().getResourceSet();
        URI parentUri = URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/base/parent.uml", true);
        Resource res = rs.getResource(parentUri, true);
        assertNotNull("Test input model could not be loaded (" + parentUri + ")", res);
        Package controlTarget = (Package) findNamedElement(res.getContents().get(0), "PackageToControl");
        assertNotNull("Invalid test input model", controlTarget);

        assertFileExists(TEMPORARY_PROJECT_NAME + "/base/parent.uml");
        assertFileDoesNotExists(TEMPORARY_PROJECT_NAME + "/base/controlled.uml");
        ControlCommand cmd = new ControlCommand(controlTarget, URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/base/controlled.uml", true));
        executeCommand(cmd);
        saveAll(rs);
        assertFileExists(TEMPORARY_PROJECT_NAME + "/base/parent.uml");
        assertFileExists(TEMPORARY_PROJECT_NAME + "/base/controlled.uml");

        ResourceSet expectedRs = new ResourceSetImpl();
        expectedRs.getResource(URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/controlled/parent.uml", true), true);
        EcoreUtil.resolveAll(expectedRs);

        assertSameModels(expectedRs, rs);
    }

    public void testControlThenUncontrolThenControl() throws IOException, CoreException {
        ResourceSet rs = session.getTransactionalEditingDomain().getResourceSet();
        URI parentUri = URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/base/parent.uml", true);
        Resource res = rs.getResource(parentUri, true);
        assertNotNull("Test input model could not be loaded (" + parentUri + ")", res);
        Package controlTarget = (Package) findNamedElement(res.getContents().get(0), "PackageToControl");
        assertNotNull("Invalid test input model", controlTarget);

        assertFileExists(TEMPORARY_PROJECT_NAME + "/base/parent.uml");
        assertFileDoesNotExists(TEMPORARY_PROJECT_NAME + "/base/controlled.uml");
        Command cmd = new ControlCommand(controlTarget, URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/base/controlled.uml", true));
        session.getTransactionalEditingDomain().getCommandStack().execute(cmd);
        saveAll(rs);
        assertFileExists(TEMPORARY_PROJECT_NAME + "/base/parent.uml");
        assertFileExists(TEMPORARY_PROJECT_NAME + "/base/controlled.uml");

        cmd = new UncontrolCommand(controlTarget);
        session.getTransactionalEditingDomain().getCommandStack().execute(cmd);
        saveAll(rs);
        ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(TEMPORARY_PROJECT_NAME + "/base/controlled.uml")).delete(true, new NullProgressMonitor());

        assertFileExists(TEMPORARY_PROJECT_NAME + "/base/parent.uml");
        assertFileDoesNotExists(TEMPORARY_PROJECT_NAME + "/base/controlled.uml");
        cmd = new ControlCommand(controlTarget, URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/base/controlled.uml", true));
        session.getTransactionalEditingDomain().getCommandStack().execute(cmd);
        saveAll(rs);
        assertFileExists(TEMPORARY_PROJECT_NAME + "/base/parent.uml");
        assertFileExists(TEMPORARY_PROJECT_NAME + "/base/controlled.uml");
    }

    public void testUncontrol() throws IOException {
        ResourceSet rs = session.getTransactionalEditingDomain().getResourceSet();
        URI parentUri = URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/controlled/parent.uml", true);
        Resource res = rs.getResource(parentUri, true);
        assertNotNull("Test input model could not be loaded (" + parentUri + ")", res);
        Package controlTarget = (Package) findNamedElement(res.getContents().get(0), "PackageToControl");
        assertNotNull("Invalid test input model", controlTarget);

        assertFileExists(TEMPORARY_PROJECT_NAME + "/controlled/parent.uml");
        assertFileExists(TEMPORARY_PROJECT_NAME + "/controlled/controlled.uml");
        UncontrolCommand cmd = new UncontrolCommand(controlTarget);
        executeCommand(cmd);
        saveAll(rs);
        assertFileExists(TEMPORARY_PROJECT_NAME + "/controlled/parent.uml");

        Package unconontrolledPackage = (Package) findNamedElement(res.getContents().get(0), "PackageToControl");
        assertNotNull(unconontrolledPackage);
        assertEquals("PackageToControl", unconontrolledPackage.getName());
        assertSame(res.getContents().get(0), unconontrolledPackage.getModel());
        assertSame(res, unconontrolledPackage.eResource());
    }

    private void assertFileDoesNotExists(String wksPath) {
        assertFalse(ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(wksPath)).exists());
    }

    private void assertFileExists(String wksPath) {
        assertTrue(ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(wksPath)).exists());
    }

    private void assertSameModels(ResourceSet expected, ResourceSet actual) {
        IEObjectMatcher matcher = DefaultMatchEngine.createDefaultEObjectMatcher(UseIdentifiers.NEVER);
        IComparisonFactory comparisonFactory = new DefaultComparisonFactory(new DefaultEqualityHelperFactory());
        IMatchEngine.Factory matchEngineFactory = new MatchEngineFactoryImpl(matcher, comparisonFactory);
        IMatchEngine.Factory.Registry matchEngineRegistry = new MatchEngineFactoryRegistryImpl();
        matchEngineRegistry.add(matchEngineFactory);
        EMFCompare comparator = EMFCompare.builder().setMatchEngineFactoryRegistry(matchEngineRegistry).build();
        FilterComparisonScope scope = new FilterComparisonScope(actual, expected, null) {
            public Iterator<? extends Resource> getCoveredResources(ResourceSet resourceSet) {
                final Iterator<? extends Resource> coveredResources = super.getCoveredResources(resourceSet);
                return new Iterator<Resource>() {
                    private Resource r;

                    public boolean hasNext() {
                        Resource r = null;
                        while (coveredResources.hasNext() && r == null) {
                            Resource next = coveredResources.next();
                            if (!(next instanceof AirdResource)) {
                                r = next;
                                break;
                            }
                        }
                        this.r = r;
                        return r != null;
                    }

                    public Resource next() {
                        return r;
                    }

                    public void remove() {
                        throw new UnsupportedOperationException();

                    }

                };
            }
        };

        // scope.setResourceSetContentFilter(not(instanceOf(AirdResource.class)));
        Comparison comparison = comparator.compare(scope);
        assertEquals(0, comparison.getDifferences().size());
    }

    private void saveAll(ResourceSet rs) throws IOException {
        for (Resource r : rs.getResources()) {
            r.save(Collections.emptyMap());
        }
    }

    private NamedElement findNamedElement(EObject root, String pkgName) {
        TreeIterator<EObject> iter = root.eAllContents();
        while (iter.hasNext()) {
            EObject current = iter.next();
            if (current instanceof NamedElement && ((NamedElement) current).getName().equals(pkgName)) {
                return (NamedElement) current;
            }
        }
        return null;
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
