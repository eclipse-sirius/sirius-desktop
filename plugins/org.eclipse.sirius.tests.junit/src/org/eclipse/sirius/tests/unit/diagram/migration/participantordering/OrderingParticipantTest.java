/*******************************************************************************
 * Copyright (c) 2023 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.diagram.migration.participantordering;

import java.util.List;
import java.util.Optional;

import org.eclipse.core.internal.registry.ExtensionRegistry;
import org.eclipse.core.internal.registry.spi.ConfigurationElementAttribute;
import org.eclipse.core.internal.registry.spi.ConfigurationElementDescription;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.spi.RegistryContributor;
import org.eclipse.sirius.business.api.migration.IMigrationParticipant;
import org.eclipse.sirius.business.internal.migration.AbstractSiriusMigrationService;
import org.eclipse.sirius.business.internal.migration.RepresentationsFileMigrationService;
import org.eclipse.sirius.common.tools.api.util.ReflectionHelper;
import org.eclipse.sirius.tests.SiriusTestsPlugin;

import junit.framework.TestCase;

/**
 * Test the order of the aird migration participant after
 * RepresentationsFileMigrationService.MIGRATION_VERSION_FROM_WHICH_ORDER_PARTICIPANT = new
 * Version("15.1.0.000000000000")
 * 
 * @author Laurent Fasani
 */
public class OrderingParticipantTest extends TestCase {
    private Object registryToken = null;

    public void testMigrationParticipantOrdering() {

        ExtensionRegistry extensionRegistry = (ExtensionRegistry) Platform.getExtensionRegistry();
        registryToken = ReflectionHelper.getFieldValueWithoutException(extensionRegistry, "userToken").get(); //$NON-NLS-1$

        addExtension(extensionRegistry, Mp4.class);
        addExtension(extensionRegistry, Mp3.class);
        addExtension(extensionRegistry, Mp2.class);
        addExtension(extensionRegistry, Mp1.class);
        RepresentationsFileMigrationService repMigService = RepresentationsFileMigrationService.getInstance();

        Optional<Object> fieldValueWithoutExceptionObj = ReflectionHelper.getFieldValueWithoutException(repMigService, "delegatesParticipants", AbstractSiriusMigrationService.class); //$NON-NLS-1$
        List<IMigrationParticipant> migrationParticipants = (List<IMigrationParticipant>) fieldValueWithoutExceptionObj.get();
        Mp1 mp1 = (Mp1) migrationParticipants.stream().filter(Mp1.class::isInstance).findFirst().get();
        Mp2 mp2 = (Mp2) migrationParticipants.stream().filter(Mp2.class::isInstance).findFirst().get();
        Mp3 mp3 = (Mp3) migrationParticipants.stream().filter(Mp3.class::isInstance).findFirst().get();
        Mp4 mp4 = (Mp4) migrationParticipants.stream().filter(Mp4.class::isInstance).findFirst().get();

        // check that participant before 15.1.0.0000000 are not ordered
        assertTrue(migrationParticipants.indexOf(mp1) == migrationParticipants.indexOf(mp2) + 1);
        // check that participant after 15.1.0.0000000 are ordered
        assertTrue(migrationParticipants.indexOf(mp3) > migrationParticipants.indexOf(mp2));
        assertTrue(migrationParticipants.indexOf(mp4) > migrationParticipants.indexOf(mp3));
    }

    private void addExtension(ExtensionRegistry extensionRegistry, Class<? extends IMigrationParticipant> clazz) {
        String bundleId = Long.toString(SiriusTestsPlugin.getDefault().getBundle().getBundleId());
        String name = clazz.getName();
        ConfigurationElementAttribute classAttr = new ConfigurationElementAttribute("class", clazz.getName());
        ConfigurationElementAttribute kindAttr = new ConfigurationElementAttribute("kind", "RepresentationsFile");
        ConfigurationElementDescription description = new ConfigurationElementDescription("participant", new ConfigurationElementAttribute[] { classAttr, kindAttr }, null, null);
        extensionRegistry.addExtension(name, new RegistryContributor(bundleId, name, null, null), false, "", "org.eclipse.sirius.migrationParticipant", description, registryToken);
    }

    @Override
    protected void tearDown() throws Exception {
        IExtensionRegistry extensionRegistry = Platform.getExtensionRegistry();
        Platform.getExtensionRegistry().removeExtension(extensionRegistry.getExtension(Mp1.class.getName()), registryToken);
        Platform.getExtensionRegistry().removeExtension(extensionRegistry.getExtension(Mp2.class.getName()), registryToken);
        Platform.getExtensionRegistry().removeExtension(extensionRegistry.getExtension(Mp3.class.getName()), registryToken);
        Platform.getExtensionRegistry().removeExtension(extensionRegistry.getExtension(Mp4.class.getName()), registryToken);

        super.tearDown();
    }
}
