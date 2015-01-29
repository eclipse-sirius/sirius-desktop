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
package org.eclipse.sirius.tests.unit.contribution;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;
import org.eclipse.sirius.business.internal.contribution.IdentifierBasedMatcher;
import org.eclipse.sirius.business.internal.contribution.Updater;
import org.eclipse.sirius.ecore.extender.tool.api.ModelUtils;
import org.eclipse.sirius.ext.emf.AllContents;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.google.common.base.Function;
import com.google.common.collect.Maps;

/**
 * Unit tests for the in-place model {@link Updater}.
 * 
 * @author pierre-charles.david@obeo.fr
 */
@RunWith(value = Parameterized.class)
public class UpdaterTest {

    @Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][] { { "original", "add_edge_mapping" }, { "original", "various_changes" }, { "original", "add_layer" }, { "original", "change_attributes" },
                { "original", "reorder_mappings" }, { "interaction", "interaction_modified" }, { "original", "original" }, { "add_layer", "add_layer" }, { "interaction", "interaction" } };
        return Arrays.asList(data);
    }

    /**
     * Make sure EMF is properly initialized when we are run outside of Eclipse.
     */
    @BeforeClass
    @SuppressWarnings("unused")
    public static void initializeEMF() {
        if (!EMFPlugin.IS_ECLIPSE_RUNNING) {
            EPackage vsm = DescriptionPackage.eINSTANCE;
            EPackage tables = org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage.eINSTANCE;
            EPackage sequence = org.eclipse.sirius.diagram.sequence.description.DescriptionPackage.eINSTANCE;
            // Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("odesign",
            // new XMIResourceFactoryImpl());
        }
    }

    private String beforeName;

    private String afterName;

    private EObject v0;

    private EObject v1;

    private ResourceSetImpl rs;

    public UpdaterTest(String beforeName, String afterName) {
        this.beforeName = beforeName;
        this.afterName = afterName;
    }

    @Before
    public void setUp() {
        rs = new ResourceSetImpl();
        if (EMFPlugin.IS_ECLIPSE_RUNNING) {
            v0 = rs.getResource(URI.createPlatformPluginURI("/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/movida/" + beforeName + ".odesign", true), true).getContents().get(0);
            v1 = rs.getResource(URI.createPlatformPluginURI("/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/movida/" + afterName + ".odesign", true), true).getContents().get(0);
        } else {
            v0 = rs.getResource(URI.createFileURI("data/unit/movida/" + beforeName + ".odesign"), true).getContents().get(0);
            v1 = rs.getResource(URI.createFileURI("data/unit/movida/" + afterName + ".odesign"), true).getContents().get(0);
        }
        EcoreUtil.resolveAll(rs);
    }

    @After
    public void tearDown() {
        v0 = null;
        v1 = null;
        rs = null;
    }

    @Test
    public void update_whole_model() throws IOException {
        Copier copier = new EcoreUtil.Copier();
        EObject v1Copy = copier.copy(v1);
        copier.copyReferences();
        final Map<EObject, Object> inputIds = Maps.newHashMap();
        addIntrinsicIds(v0, inputIds);
        for (EObject obj : AllContents.of(v1, true)) {
            inputIds.put(copier.get(obj), getIntrinsicId(obj));
        }

        IdentifierBasedMatcher matcher = new IdentifierBasedMatcher(new Function<EObject, Object>() {
            public Object apply(EObject from) {
                if (inputIds.containsKey(from)) {
                    return inputIds.get(from);
                } else {
                    return EcoreUtil.getURI(from);
                }
            }
        });
        if (beforeName.equals(afterName)) {
            Freezer.freeze(AllContents.of(v0, true));
            Freezer.freeze(AllContents.of(v1Copy, true));
        }
        Updater updater = new Updater(matcher, v0, v1Copy);
        EObject result = updater.update();

        assertSame(result, v0);
        String v1Text = ModelUtils.serialize(v1);
        String v0Text = ModelUtils.serialize(v0);
        assertEquals(v1Text, v0Text);

        if (beforeName.equals(afterName)) {
            Freezer.thaw(AllContents.of(v0, true));
            Freezer.thaw(AllContents.of(v1Copy, true));
        }
    }

    public void addIntrinsicIds(EObject root, Map<EObject, Object> map) {
        for (EObject obj : AllContents.of(root, true)) {
            map.put(obj, getIntrinsicId(obj));
        }
    }

    private Object getIntrinsicId(EObject obj) {
        return obj.eResource().getURIFragment(obj);
    }
}
