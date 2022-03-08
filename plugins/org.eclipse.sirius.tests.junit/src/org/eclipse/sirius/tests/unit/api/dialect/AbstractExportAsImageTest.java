/*******************************************************************************
 * Copyright (c) 2010, 2022 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.tests.unit.api.dialect;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.api.resource.ImageFileFormat;
import org.eclipse.sirius.diagram.ui.business.api.DiagramExportResult;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.diagram.migration.AbstractRepairMigrateTest;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.dialect.ExportFormat;
import org.eclipse.sirius.ui.business.api.dialect.ExportFormat.ExportDocumentFormat;
import org.eclipse.sirius.ui.business.api.dialect.ExportFormat.ScalingPolicy;
import org.eclipse.sirius.ui.tools.api.actions.export.SizeTooLargeException;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Tests the export of diagrams as image after the migration of the session
 * 
 * @author lchituc
 */
public abstract class AbstractExportAsImageTest extends AbstractRepairMigrateTest {

    protected static final String SEMANTIC_MODEL_FILE_NAME = "My.ecore"; //$NON-NLS-1$

    protected static final String SESSION_MODEL_FILENAME = "representations.aird"; //$NON-NLS-1$

    protected static final String AIRD_FILE_NAME = SESSION_MODEL_FILENAME;

    protected static final String SEMANTIC_MODEL_WORKSPACE_PATH = TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILE_NAME; //$NON-NLS-1$

    protected static final String AIRD_WORKSPACE_PATH = TEMPORARY_PROJECT_NAME + "/" + SESSION_MODEL_FILENAME; //$NON-NLS-1$

    protected DRepresentation getRepresentation(String representationName) throws Exception {

        // Reopen the session( because after migration the session is closed)
        session = SessionManager.INSTANCE.getSession(URI.createPlatformResourceURI("/" + TEMPORARY_PROJECT_NAME + "/" + SESSION_MODEL_FILENAME, true), new NullProgressMonitor()); //$NON-NLS-1$ //$NON-NLS-2$
        session.open(new NullProgressMonitor());
        TestsUtil.emptyEventsFromUIThread();

        DRepresentation representation = getRepresentationFromDescriptors(representationName);
        return representation;
    }

    protected DRepresentation getRepresentationFromDescriptors(String representationName) {
        Collection<DRepresentationDescriptor> allRepresentationDescriptors = DialectManager.INSTANCE.getAllRepresentationDescriptors(session);
        for (DRepresentationDescriptor representationDescriptor : allRepresentationDescriptors) {
            if (representationName.equals(representationDescriptor.getName())) {
                return representationDescriptor.getRepresentation();
            }
        }
        return null;
    }

    public DiagramExportResult exportImage(DRepresentation representation, ImageFileFormat fileFormat, ScalingPolicy scalingPolicy) throws SizeTooLargeException {
        return this.exportImage(representation, fileFormat, scalingPolicy, false);
    }

    protected DiagramExportResult exportImage(DRepresentation representation, ImageFileFormat fileFormat, ScalingPolicy scalingPolicy, boolean traceability) throws SizeTooLargeException {
        IPath absoluteImagePath = ResourcesPlugin.getWorkspace().getRoot().getProject(TEMPORARY_PROJECT_NAME).getLocation().append(representation.getName() + "." + fileFormat.getName().toLowerCase()); //$NON-NLS-1$
        ExportFormat exportFormat = new ExportFormat(ExportDocumentFormat.NONE, fileFormat, scalingPolicy);
        exportFormat.setSemanticTraceabilityEnabled(traceability);
        DiagramExportResult exportResult = (DiagramExportResult) DialectUIManager.INSTANCE.exportWithResult(representation, session, absoluteImagePath, exportFormat, new NullProgressMonitor(), false);
        return exportResult;
    }

    protected void checkTraceability(Set<IPath> paths, boolean traceability) {

        Optional<IPath> optionalFirstFile = paths.stream().findFirst();
        IPath path = optionalFirstFile.get();
        SAXParserFactory factory = SAXParserFactory.newInstance();

        try {
            SAXParser saxParser = factory.newSAXParser();
            SVGUIDHandler handler = new SVGUIDHandler();
            saxParser.parse(path.toOSString(), handler);
            Map<String, Integer> semanticTargetIdToOccurrences = handler.getSemanticTargetIdToOccurrences();
            if (!traceability) {
                assertTrue("The semantic target id should not be exported in the SVG file", semanticTargetIdToOccurrences.isEmpty()); //$NON-NLS-1$
            } else {
                checkTraceability(semanticTargetIdToOccurrences);
            }
        } catch (ParserConfigurationException e) {
        } catch (SAXException e) {
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    protected abstract void checkTraceability(Map<String, Integer> semanticTargetIdToOccurrences);

    protected class SVGUIDHandler extends DefaultHandler {

        private Map<String, Integer> semanticTargetIdToOccurrences = new HashMap<>();

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            String idValue = attributes.getValue("diagram:semanticTargetId"); //$NON-NLS-1$
            if (idValue != null) {
                Integer occurence = semanticTargetIdToOccurrences.get(idValue);
                if (occurence == null) {
                    occurence = 0;
                }
                semanticTargetIdToOccurrences.put(idValue, ++occurence);
            }
        }

        public Map<String, Integer> getSemanticTargetIdToOccurrences() {
            return semanticTargetIdToOccurrences;
        }
    }
}
