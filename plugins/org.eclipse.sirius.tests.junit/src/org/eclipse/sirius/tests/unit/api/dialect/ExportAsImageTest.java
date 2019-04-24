/*******************************************************************************
 * Copyright (c) 2010, 2019 THALES GLOBAL SERVICES and others.
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

import static org.easymock.EasyMock.replay;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;

import org.apache.batik.dom.svg.SVGDOMImplementation;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.TranscodingHints;
import org.apache.batik.transcoder.image.ImageTranscoder;
import org.apache.batik.util.SVGConstants;
import org.easymock.EasyMock;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.query.URIQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.resource.ImageFileFormat;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.diagram.ui.business.api.DiagramExportResult;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.SaveAsImageFileAction;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.SiriusAssert;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.diagram.migration.AbstractRepairMigrateTest;
import org.eclipse.sirius.tools.api.interpreter.InterpreterRegistry;
import org.eclipse.sirius.tools.internal.resource.InMemoryResourceImpl;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.dialect.ExportFormat;
import org.eclipse.sirius.ui.business.api.dialect.ExportFormat.ExportDocumentFormat;
import org.eclipse.sirius.ui.business.api.dialect.ExportFormat.ScalingPolicy;
import org.eclipse.sirius.ui.tools.api.actions.export.ExportAction;
import org.eclipse.sirius.ui.tools.api.actions.export.SizeTooLargeException;
import org.eclipse.sirius.ui.tools.internal.actions.export.AbstractExportRepresentationsAction;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.ViewpointFactory;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.junit.Ignore;

/**
 * Tests the export of diagrams as image after the migration of the session
 * 
 * @author lchituc
 */
public class ExportAsImageTest extends AbstractRepairMigrateTest {

    private static final String SEMANTIC_MODEL_FILE_NAME = "My.ecore";

    private static final String SEMANTIC_MODEL_RELATIVE_PATH = "/data/unit/ExportAsImage/" + SEMANTIC_MODEL_FILE_NAME;

    private static final String SEMANTIC_MODEL_WORKSPACE_PATH = TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILE_NAME;

    private static final String MODELER_FILE_NAME = "My.odesign";

    private static final String MODELER_RELATIVE_PATH = "/data/unit/ExportAsImage/" + MODELER_FILE_NAME;

    private static final String MODELER_WORKSPACE_PATH = TEMPORARY_PROJECT_NAME + "/" + MODELER_FILE_NAME;

    private static final String AIRD_FILE_NAME = "My.aird";

    private static final String AIRD_RELATIVE_PATH = "/data/unit/ExportAsImage/" + AIRD_FILE_NAME;

    private static final String AIRD_WORKSPACE_PATH = TEMPORARY_PROJECT_NAME + "/" + AIRD_FILE_NAME;

    private static final String SESSION_MODEL_FILENAME = "My.aird";

    private static final String IMAGE_FILE_NAME = "new diagExportAsImage.";

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, MODELER_RELATIVE_PATH, MODELER_WORKSPACE_PATH);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, SEMANTIC_MODEL_RELATIVE_PATH, SEMANTIC_MODEL_WORKSPACE_PATH);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, AIRD_RELATIVE_PATH, AIRD_WORKSPACE_PATH);

        genericSetUp(SEMANTIC_MODEL_WORKSPACE_PATH, MODELER_WORKSPACE_PATH, AIRD_WORKSPACE_PATH);

        TestsUtil.emptyEventsFromUIThread();
    }

    private DRepresentation getRepresentation() throws Exception {

        // Reopen the session( because after migration the session is closed)
        session = SessionManager.INSTANCE.getSession(URI.createPlatformResourceURI("/" + TEMPORARY_PROJECT_NAME + "/" + SESSION_MODEL_FILENAME, true), new NullProgressMonitor());
        session.open(new NullProgressMonitor());
        TestsUtil.emptyEventsFromUIThread();

        DRepresentation representation = getRepresentation("new diagExportAsImage");
        return representation;
    }

    private DRepresentation getRepresentation(String representationName) {
        Collection<DRepresentation> allRepresentations = DialectManager.INSTANCE.getAllRepresentations(session);
        for (DRepresentation dRepresentation : allRepresentations) {
            if (representationName.equals(dRepresentation.getName())) {
                return dRepresentation;
            }
        }
        return null;
    }

    /**
     * Returns a representation that will not be applied the auto scale when in mode
     * {@link ExportFormat.ScalingPolicy#AUTO_SCALING_IF_LARGER}.
     * 
     * @return a representation that will not be applied the auto scale when in mode
     *         {@link ExportFormat.ScalingPolicy#AUTO_SCALING_IF_LARGER}.
     * @throws Exception
     */
    private DRepresentation getRepresentationNoAutoScaleInLargerMode() throws Exception {

        // Reopen the session( because after migration the session is closed)
        session = SessionManager.INSTANCE.getSession(URI.createPlatformResourceURI("/" + TEMPORARY_PROJECT_NAME + "/" + SESSION_MODEL_FILENAME, true), new NullProgressMonitor());
        session.open(new NullProgressMonitor());
        TestsUtil.emptyEventsFromUIThread();

        DRepresentation representation = getRepresentation("NoScaleIfLarger");
        return representation;
    }

    private DiagramExportResult exportImage(DRepresentation representation, ImageFileFormat fileFormat, ScalingPolicy scalingPolicy) throws SizeTooLargeException {
        IPath absoluteImagePath = ResourcesPlugin.getWorkspace().getRoot().getProject(TEMPORARY_PROJECT_NAME).getLocation().append(IMAGE_FILE_NAME + fileFormat.getName().toLowerCase());
        DiagramExportResult exportResult = (DiagramExportResult) DialectUIManager.INSTANCE.exportWithResult(representation, session, absoluteImagePath,
                new ExportFormat(ExportDocumentFormat.NONE, fileFormat, scalingPolicy), new NullProgressMonitor(), false);
        return exportResult;
    }

    /**
     * Produce a {@link BufferedImage} corresponding to the SVG image represented by the given {@link File}.
     * 
     * @param svgFile
     *            the file from which we want to produce the buffered image.
     * @return a {@link BufferedImage} corresponding to the SVG image represented by the given {@link File}.
     * @throws IOException
     *             if a problem occurs.
     */
    public static BufferedImage rasterize(File svgFile) throws IOException {

        final BufferedImage[] imagePointer = new BufferedImage[1];

        String css = "svg {" + "shape-rendering: geometricPrecision;" + "text-rendering:  geometricPrecision;" + "color-rendering: optimizeQuality;" + "image-rendering: optimizeQuality;" + "}";
        File cssFile = File.createTempFile("batik-default-override-", ".css");

        Files.write(Paths.get(cssFile.getAbsolutePath()), css.getBytes());

        TranscodingHints transcoderHints = new TranscodingHints();
        transcoderHints.put(ImageTranscoder.KEY_XML_PARSER_VALIDATING, Boolean.FALSE);
        transcoderHints.put(ImageTranscoder.KEY_DOM_IMPLEMENTATION, SVGDOMImplementation.getDOMImplementation());
        transcoderHints.put(ImageTranscoder.KEY_DOCUMENT_ELEMENT_NAMESPACE_URI, SVGConstants.SVG_NAMESPACE_URI);
        transcoderHints.put(ImageTranscoder.KEY_DOCUMENT_ELEMENT, "svg");
        transcoderHints.put(ImageTranscoder.KEY_USER_STYLESHEET_URI, cssFile.toURI().toString());

        try {

            TranscoderInput input = new TranscoderInput(new FileInputStream(svgFile));

            ImageTranscoder t = new ImageTranscoder() {

                @Override
                public BufferedImage createImage(int w, int h) {
                    return new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
                }

                @Override
                public void writeImage(BufferedImage image, TranscoderOutput out) throws TranscoderException {
                    imagePointer[0] = image;
                }
            };
            t.setTranscodingHints(transcoderHints);
            t.transcode(input, null);
        } catch (TranscoderException ex) {
            throw new IOException("Couldn't convert " + svgFile, ex);
        } finally {
            cssFile.delete();
        }

        return imagePointer[0];
    }

    /**
     * Tests the export of diagrams as image after the migration of the session
     * 
     * @throws Exception
     */
    public void testExportAsImage() throws Exception {
        // The session must be close before launching the migration (see
        // RepresentationFilesMigration.run and
        // RepresentationFilesMigrationValidator);
        closeSession(session);

        // Migrates the session
        runRepairProcess(SESSION_MODEL_FILENAME);
        DRepresentation representation = getRepresentation();
        exportImage(representation, ImageFileFormat.PNG, ExportFormat.ScalingPolicy.NO_SCALING);
        // Asserts that the image had been created
        SiriusAssert.assertFileExists("/" + TEMPORARY_PROJECT_NAME + "/" + IMAGE_FILE_NAME + ImageFileFormat.PNG.getName().toLowerCase());
    }

    /**
     * Tests the export of a diagram too large to fit in the image creation buffer.
     * 
     * @throws Exception
     */
    public void testExportTooLargeDiagram() {
        doTestToLargeFileExport("ExportLargeDiagramError");
    }

    /**
     * Tests the export of a diagram very large that cause export related computation to produce a NaN: the result of
     * some computation is way bigger than int and can produce incoherent result (sometime negative) which may be use in
     * a square root operation, producing the NaN value.
     * 
     * @throws Exception
     */
    public void testExportTooLargeDiagramThatLeadsToNaNComputation() {
        doTestToLargeFileExport("ExportVeryLargeDiagramErrorThatLeadsToNaNComputation");
    }

    private void doTestToLargeFileExport(String representationName) {
        String outputPath = "/" + TEMPORARY_PROJECT_NAME + "/" + IMAGE_FILE_NAME + ImageFileFormat.PNG.getName().toLowerCase();
        List<DRepresentation> representationsToExport = new ArrayList<>();
        representationsToExport.add(getRepresentation(representationName));
        ExportAction exportAction = new ExportAction(session, representationsToExport, new Path(outputPath), ImageFileFormat.PNG, false, true);

        String errorMessage = Messages.ExportAction_imagesTooLargeMessage + "\n - " + representationName;

        try {
            exportAction.run(new NullProgressMonitor());
            fail("The export should fail because the diagram is too big.");
        } catch (InvocationTargetException | InterruptedException e) {
            assertEquals(InvocationTargetException.class, e.getClass());
            assertEquals(CoreException.class, e.getCause().getClass());
            assertEquals(errorMessage, e.getMessage());
        }
    }

    /**
     * Test that the export of diagrams as image works properly even if the GMF model is unsynchronized with the Sirius
     * one. This test checks that no model changes out of a transaction occurs (in that case a warning message is
     * logged)
     * 
     * @throws Exception
     */
    public void testExportAsImageWithUnsynchronizedGMFModel() throws Exception {
        setWarningCatchActive(true);
        try {
            DiagramExportResult exportResult = exportImage(getRepresentation("unsyncGMFDiagExportAsImage"), ImageFileFormat.JPG, ExportFormat.ScalingPolicy.AUTO_SCALING);
            checkResultsWithAutoUpScale(exportResult);
        } finally {
            setWarningCatchActive(false);
        }
    }

    /**
     * Tests the export of diagrams as image with JPG format and auto-scale activated when an up scale is done.
     * 
     * @throws Exception
     */
    public void testExportAsJPGAutoScaling() throws Exception {
        DiagramExportResult exportResult = exportImage(getRepresentation(), ImageFileFormat.JPG, ExportFormat.ScalingPolicy.AUTO_SCALING);
        checkResultsWithAutoUpScale(exportResult);
    }

    /**
     * Tests the export of diagrams as image with JPEG format and auto-scale activated when an up scale is done.
     * 
     * @throws Exception
     */
    public void testExportAsJPEGAutoScaling() throws Exception {
        DiagramExportResult exportResult = exportImage(getRepresentation(), ImageFileFormat.JPEG, ExportFormat.ScalingPolicy.AUTO_SCALING);
        checkResultsWithAutoUpScale(exportResult);
    }

    /**
     * Tests the export of diagrams as image with BMP format and auto-scale activated when an up scale is done.
     * 
     * @throws Exception
     */
    @Ignore("To reactivate or delete when #527514 is handled.")
    public void ignore_testExportAsBMPAutoScaling() throws Exception {
        DiagramExportResult exportResult = exportImage(getRepresentation(), ImageFileFormat.BMP, ExportFormat.ScalingPolicy.AUTO_SCALING);
        checkResultsWithAutoUpScale(exportResult);
    }

    /**
     * Tests the export of diagrams as image with GIF format and auto-scale activated when an up scale is done.
     * 
     * @throws Exception
     */
    public void testExportAsGIFAutoScaling() throws Exception {
        DiagramExportResult exportResult = exportImage(getRepresentation(), ImageFileFormat.GIF, ExportFormat.ScalingPolicy.AUTO_SCALING);
        checkResultsWithAutoUpScale(exportResult);
    }

    /**
     * Tests the export of diagrams as image with PNG format and auto-scale activated when an up scale is done.
     * 
     * @throws Exception
     */
    public void testExportAsPNGAutoScaling() throws Exception {
        DiagramExportResult exportResult = exportImage(getRepresentation(), ImageFileFormat.PNG, ExportFormat.ScalingPolicy.AUTO_SCALING);
        checkResultsWithAutoUpScale(exportResult);
    }

    /**
     * Tests the export of diagrams as image with SVG format and auto-scale activated. SVG export does not support
     * auto-scaling so it should not be taken in consideration.
     * 
     * @throws Exception
     */
    public void testExportAsSVGAutoScaling() throws Exception {
        DiagramExportResult exportResult = exportImage(getRepresentation(), ImageFileFormat.SVG, ExportFormat.ScalingPolicy.AUTO_SCALING);
        checkResultsNoAutoScaling(exportResult, true);
    }

    /**
     * Tests the export of diagrams as image with SVGZ format and auto-scale activated when an up scale is done.
     * 
     * @throws Exception
     */
    public void testExportAsSVGZAutoScaling() throws Exception {
        DiagramExportResult exportResult = exportImage(getRepresentation(), ImageFileFormat.SVGZ, ExportFormat.ScalingPolicy.AUTO_SCALING);
        checkResultsWithAutoUpScale(exportResult);
    }

    /**
     * Tests the export of diagrams as image with JPG format and auto-scale activated when a down scale is done.
     * 
     * @throws Exception
     */
    public void testExportAsJPGAutoScalingDownScale() throws Exception {
        DiagramExportResult exportResult = exportImage(getRepresentationNoAutoScaleInLargerMode(), ImageFileFormat.JPG, ExportFormat.ScalingPolicy.AUTO_SCALING);
        checkResultsWithAutoDownScale(exportResult);
    }

    /**
     * Tests the export of diagrams as image with JPEG format and auto-scale activated when a down scale is done.
     * 
     * @throws Exception
     */
    public void testExportAsJPEGAutoScalingDownScale() throws Exception {
        DiagramExportResult exportResult = exportImage(getRepresentationNoAutoScaleInLargerMode(), ImageFileFormat.JPEG, ExportFormat.ScalingPolicy.AUTO_SCALING);
        checkResultsWithAutoDownScale(exportResult);
    }

    /**
     * Tests the export of diagrams as image with BMP format and auto-scale activated when a down scale is done.
     * 
     * @throws Exception
     */
    @Ignore("To reactivate or delete when #527514 is handled.")
    public void ignore_testExportAsBMPAutoScalingDownScale() throws Exception {
        DiagramExportResult exportResult = exportImage(getRepresentationNoAutoScaleInLargerMode(), ImageFileFormat.BMP, ExportFormat.ScalingPolicy.AUTO_SCALING);
        checkResultsWithAutoDownScale(exportResult);
    }

    /**
     * Tests the export of diagrams as image with GIF format and auto-scale activated when a down scale is done.
     * 
     * @throws Exception
     */
    public void testExportAsGIFAutoScalingDownScale() throws Exception {
        DiagramExportResult exportResult = exportImage(getRepresentationNoAutoScaleInLargerMode(), ImageFileFormat.GIF, ExportFormat.ScalingPolicy.AUTO_SCALING);
        checkResultsWithAutoDownScale(exportResult);
    }

    /**
     * Tests the export of diagrams as image with PNG format and auto-scale activated when a down scale is done.
     * 
     * @throws Exception
     */
    public void testExportAsPNGAutoScalingDownScale() throws Exception {
        DiagramExportResult exportResult = exportImage(getRepresentationNoAutoScaleInLargerMode(), ImageFileFormat.PNG, ExportFormat.ScalingPolicy.AUTO_SCALING);
        checkResultsWithAutoDownScale(exportResult);
    }

    /**
     * Tests the export of diagrams as image with SVG format and auto-scale activated. SVG export does not support
     * auto-scaling so it should not be taken in consideration.
     * 
     * @throws Exception
     */
    public void testExportAsSVGAutoScalingDownScale() throws Exception {
        DiagramExportResult exportResult = exportImage(getRepresentationNoAutoScaleInLargerMode(), ImageFileFormat.SVG, ExportFormat.ScalingPolicy.AUTO_SCALING);
        checkResults(exportResult, 10090, 4956, 0.0);
    }

    /**
     * Tests the export of diagrams as image with SVGZ format and auto-scale activated when a down scale is done.
     * 
     * @throws Exception
     */
    public void testExportAsSVGZAutoScalingDownScale() throws Exception {
        DiagramExportResult exportResult = exportImage(getRepresentationNoAutoScaleInLargerMode(), ImageFileFormat.SVGZ, ExportFormat.ScalingPolicy.AUTO_SCALING);
        checkResultsWithAutoDownScale(exportResult);
    }

    /**
     * Tests the export of diagrams as image with JPG format and auto-scale if larger activated. Auto scaling should
     * apply.
     * 
     * @throws Exception
     */
    public void testExportAsJPGAutoScalingIfLarger() throws Exception {
        DiagramExportResult exportResult = exportImage(getRepresentation(), ImageFileFormat.JPG, ExportFormat.ScalingPolicy.AUTO_SCALING_IF_LARGER);
        checkResultsWithAutoUpScale(exportResult);
    }

    /**
     * Tests the export of diagrams as image with JPEG format and auto-scale if larger activated. Auto scaling should
     * apply.
     * 
     * @throws Exception
     */
    public void testExportAsJPEGAutoScalingIfLarger() throws Exception {
        DiagramExportResult exportResult = exportImage(getRepresentation(), ImageFileFormat.JPEG, ExportFormat.ScalingPolicy.AUTO_SCALING_IF_LARGER);
        checkResultsWithAutoUpScale(exportResult);
    }

    /**
     * Tests the export of diagrams as image with BMP format and auto-scale if larger activated. Auto scaling should
     * apply.
     * 
     * @throws Exception
     */
    @Ignore("To reactivate or delete when #527514 is handled.")
    public void ignore_testExportAsBMPAutoScalingIfLarger() throws Exception {
        DiagramExportResult exportResult = exportImage(getRepresentation(), ImageFileFormat.BMP, ExportFormat.ScalingPolicy.AUTO_SCALING_IF_LARGER);
        checkResultsWithAutoUpScale(exportResult);
    }

    /**
     * Tests the export of diagrams as image with GIF format and auto-scale if larger activated. Auto scaling should
     * apply.
     * 
     * @throws Exception
     */
    public void testExportAsGIFAutoScalingIfLarger() throws Exception {
        DiagramExportResult exportResult = exportImage(getRepresentation(), ImageFileFormat.GIF, ExportFormat.ScalingPolicy.AUTO_SCALING_IF_LARGER);
        checkResultsWithAutoUpScale(exportResult);
    }

    /**
     * Tests the export of diagrams as image with PNG format and auto-scale if larger activated. Auto scaling should
     * apply.
     * 
     * @throws Exception
     */
    public void testExportAsPNGAutoScalingIfLarger() throws Exception {
        DiagramExportResult exportResult = exportImage(getRepresentation(), ImageFileFormat.PNG, ExportFormat.ScalingPolicy.AUTO_SCALING_IF_LARGER);
        checkResultsWithAutoUpScale(exportResult);
    }

    /**
     * Tests the export of diagrams as image with SVG format and auto-scale activated. SVG export does not support
     * auto-scaling so it should not be taken in consideration.
     * 
     * @throws Exception
     */
    public void testExportAsSVGAutoScalingIfLarger() throws Exception {
        DiagramExportResult exportResult = exportImage(getRepresentation(), ImageFileFormat.SVG, ExportFormat.ScalingPolicy.AUTO_SCALING_IF_LARGER);
        checkResultsNoAutoScaling(exportResult, true);
    }

    /**
     * Tests the export of diagrams as image with SVGZ format and auto-scale if larger activated. Auto scaling should
     * apply.
     * 
     * @throws Exception
     */
    public void testExportAsSVGZAutoScalingIfLarger() throws Exception {
        DiagramExportResult exportResult = exportImage(getRepresentation(), ImageFileFormat.SVGZ, ExportFormat.ScalingPolicy.AUTO_SCALING_IF_LARGER);
        checkResultsWithAutoUpScale(exportResult);
    }

    /**
     * Tests the export of diagrams as image with JPG format and auto-scale if larger activated. The exception
     * {@link SizeTooLargeException} should be thrown because the maximum image size is lower than the minimum image
     * size when factor is under 1.
     * 
     * @throws Exception
     */
    public void testExportAsJPGAutoScalingIfLarger2() throws Exception {
        try {
            exportImage(getRepresentationNoAutoScaleInLargerMode(), ImageFileFormat.JPG, ExportFormat.ScalingPolicy.AUTO_SCALING_IF_LARGER);
            fail("The AUTO_SCALING_IF_LARGER mode did not support export with previous maximum image size 50000000. Test should be updated.");
        } catch (SizeTooLargeException e) {
            // current OK behavior.
        }
    }

    /**
     * Tests the export of diagrams as image with JPEG format and auto-scale if larger activated. The exception
     * {@link SizeTooLargeException} should be thrown because the maximum image size is lower than the minimum image
     * size when factor is under 1.
     * 
     * @throws Exception
     */
    public void testExportAsJPEGAutoScalingIfLarger2() throws Exception {
        try {
            exportImage(getRepresentationNoAutoScaleInLargerMode(), ImageFileFormat.JPEG, ExportFormat.ScalingPolicy.AUTO_SCALING_IF_LARGER);
            fail("The AUTO_SCALING_IF_LARGER mode did not support export with previous maximum image size 50000000. Test should be updated.");
        } catch (SizeTooLargeException e) {
            // current OK behavior.
        }
    }

    /**
     * Tests the export of diagrams as image with BMP format and auto-scale if larger activated. The exception
     * {@link SizeTooLargeException} should be thrown because the maximum image size is lower than the minimum image
     * size when factor is under 1.
     * 
     * @throws Exception
     */
    public void testExportAsBMPAutoScalingIfLarger2() throws Exception {
        try {
            exportImage(getRepresentationNoAutoScaleInLargerMode(), ImageFileFormat.BMP, ExportFormat.ScalingPolicy.AUTO_SCALING_IF_LARGER);
            fail("The AUTO_SCALING_IF_LARGER mode did not support export with previous maximum image size 50000000. Test should be updated.");
        } catch (SizeTooLargeException e) {
            // current OK behavior.
        }
    }

    /**
     * Tests the export of diagrams as image with GIF format and auto-scale if larger activated. The exception
     * {@link SizeTooLargeException} should be thrown because the maximum image size is lower than the minimum image
     * size when factor is under 1.
     * 
     * @throws Exception
     */
    public void testExportAsGIFAutoScalingIfLarger2() throws Exception {
        try {
            exportImage(getRepresentationNoAutoScaleInLargerMode(), ImageFileFormat.GIF, ExportFormat.ScalingPolicy.AUTO_SCALING_IF_LARGER);
            fail("The AUTO_SCALING_IF_LARGER mode did not support export with previous maximum image size 50000000. Test should be updated.");
        } catch (SizeTooLargeException e) {
            // current OK behavior.
        }
    }

    /**
     * Tests the export of diagrams as image with PNG format and auto-scale if larger activated. The exception
     * {@link SizeTooLargeException} should be thrown because the maximum image size is lower than the minimum image
     * size when factor is under 1.
     * 
     * @throws Exception
     */
    public void testExportAsPNGAutoScalingIfLarger2() throws Exception {
        try {
            exportImage(getRepresentationNoAutoScaleInLargerMode(), ImageFileFormat.PNG, ExportFormat.ScalingPolicy.AUTO_SCALING_IF_LARGER);
            fail("The AUTO_SCALING_IF_LARGER mode did not support export with previous maximum image size 50000000. Test should be updated.");
        } catch (SizeTooLargeException e) {
            // current OK behavior.
        }

    }

    /**
     * Tests the export of diagrams as image with SVG format and auto-scale activated. SVG export does not support
     * auto-scaling so it should not be taken in consideration.
     * 
     * @throws Exception
     */
    public void testExportAsSVGAutoScalingIfLarger2() throws Exception {
        DiagramExportResult exportResult = exportImage(getRepresentationNoAutoScaleInLargerMode(), ImageFileFormat.SVG, ExportFormat.ScalingPolicy.AUTO_SCALING_IF_LARGER);
        checkResults(exportResult, 10090, 4956, 0.0);
    }

    /**
     * Tests the export of diagrams as image with SVGZ format and auto-scale if larger activated. The exception
     * {@link SizeTooLargeException} should be thrown because the maximum image size is lower than the minimum image
     * size when factor is under 1.
     * 
     * @throws Exception
     */
    public void testExportAsSVGZAutoScalingIfLarger2() throws Exception {
        try {
            exportImage(getRepresentationNoAutoScaleInLargerMode(), ImageFileFormat.SVGZ, ExportFormat.ScalingPolicy.AUTO_SCALING_IF_LARGER);
            fail("The AUTO_SCALING_IF_LARGER mode did not support export with previous maximum image size 50000000. Test should be updated.");
        } catch (SizeTooLargeException e) {
            // current OK behavior.
        }
    }

    /**
     * Tests the export of diagrams as image with JPG format and auto-scale deactivated.
     * 
     * @throws Exception
     */
    public void testExportAsJPGNoAutoScaling() throws Exception {
        DiagramExportResult exportResult = exportImage(getRepresentation(), ImageFileFormat.JPG, ExportFormat.ScalingPolicy.NO_SCALING);
        checkResultsNoAutoScaling(exportResult, false);
    }

    /**
     * Tests the export of diagrams as image with JPEG format and auto-scale deactivated.
     * 
     * @throws Exception
     */
    public void testExportAsJPEGNoAutoScaling() throws Exception {
        DiagramExportResult exportResult = exportImage(getRepresentation(), ImageFileFormat.JPEG, ExportFormat.ScalingPolicy.NO_SCALING);
        checkResultsNoAutoScaling(exportResult, false);
    }

    /**
     * Tests the export of diagrams as image with BMP format and auto-scale deactivated.
     * 
     * @throws Exception
     */
    public void testExportAsBMPNoAutoScaling() throws Exception {
        DiagramExportResult exportResult = exportImage(getRepresentation(), ImageFileFormat.BMP, ExportFormat.ScalingPolicy.NO_SCALING);
        checkResultsNoAutoScaling(exportResult, false);
    }

    /**
     * Tests the export of diagrams as image with GIF format and auto-scale deactivated.
     * 
     * @throws Exception
     */
    public void testExportAsGIFNoAutoScaling() throws Exception {
        DiagramExportResult exportResult = exportImage(getRepresentation(), ImageFileFormat.GIF, ExportFormat.ScalingPolicy.NO_SCALING);
        checkResultsNoAutoScaling(exportResult, false);
    }

    /**
     * Tests the export of diagrams as image with PNG format and auto-scale deactivated.
     * 
     * @throws Exception
     */
    public void testExportAsPNGNoAutoScaling() throws Exception {
        DiagramExportResult exportResult = exportImage(getRepresentation(), ImageFileFormat.PNG, ExportFormat.ScalingPolicy.NO_SCALING);
        checkResultsNoAutoScaling(exportResult, false);
    }

    /**
     * Tests the export of diagrams as image with SVG format and auto-scale deactivated. SVG export does not support
     * auto-scaling so it should not be taken in consideration.
     * 
     * @throws Exception
     */
    public void testExportAsSVGNoAutoScaling() throws Exception {
        DiagramExportResult exportResult = exportImage(getRepresentation(), ImageFileFormat.SVG, ExportFormat.ScalingPolicy.NO_SCALING);
        checkResultsNoAutoScaling(exportResult, true);
    }

    /**
     * Tests the export of diagrams as image with SVGZ format and auto-scale deactivated.
     * 
     * @throws Exception
     */
    public void testExportAsSVGZNoAutoScaling() throws Exception {
        DiagramExportResult exportResult = exportImage(getRepresentation(), ImageFileFormat.SVGZ, ExportFormat.ScalingPolicy.NO_SCALING);
        checkResultsNoAutoScaling(exportResult, false);
    }

    private void checkResultsWithAutoUpScale(DiagramExportResult exportResult) throws IOException {
        checkResults(exportResult, true);
    }

    private void checkResultsWithAutoDownScale(DiagramExportResult exportResult) throws IOException {
        checkResults(exportResult, false);
    }

    private void checkResults(DiagramExportResult exportResult, int height, int width, double scalingFactor) throws IOException {
        Set<IPath> exportedFiles = exportResult.getExportedFiles();
        assertEquals("No export file has been produced", 1, exportedFiles.size());

        double scalingFactorComputed = exportResult.getScalingFactor();
        assertEquals("The scaling factor was wrongly computed.", scalingFactor, scalingFactorComputed);

        int margin = exportResult.getMargin();
        assertEquals("The expected margin is not right.", 10, margin);

        BufferedImage image = getBufferedImage(exportedFiles);
        int heightComputed = image.getHeight();
        assertEquals("The image height is wrong. Auto scale failed.", height, heightComputed, 1.0);
        int widthComputed = image.getWidth();
        assertEquals("The image width is wrong. Auto scale failed.", width, widthComputed, 2.0);
    }

    private void checkResults(DiagramExportResult exportResult, boolean upscale) throws IOException {
        Set<IPath> exportedFiles = exportResult.getExportedFiles();
        assertEquals("No export file has been produced", 1, exportedFiles.size());

        double scalingFactorComputed = exportResult.getScalingFactor();
        if (upscale) {
            assertTrue("The scaling factor should be greater than 1 so the test can make sense", scalingFactorComputed > 1);
        } else {
            assertTrue("The scaling factor should be lesser than 1 so the test can make sense", scalingFactorComputed < 1);
        }

        int margin = exportResult.getMargin();
        assertEquals("The expected margin is not right.", 10, margin);

        BufferedImage image = getBufferedImage(exportedFiles);
        int heightComputed = image.getHeight();
        int widthComputed = image.getWidth();
        double originalHeight;
        double originalWidth;
        if (upscale) {
            originalHeight = 77;
            originalWidth = 150;
        } else {
            originalHeight = 10070;
            originalWidth = 4936;
        }

        double originalHeightComputed = heightComputed / scalingFactorComputed;
        double originalWidthComputed = widthComputed / scalingFactorComputed;

        assertEquals("The ratio height/scaling factor regarding original size and margin is wrong.", originalHeight + (margin * 2), originalHeightComputed, 3.0);

        assertEquals("The ratio width/scaling factor regarding original size and margin is wrong.", originalWidth + (margin * 2), originalWidthComputed, 3.0);
    }

    private BufferedImage getBufferedImage(Set<IPath> exportedFiles) throws IOException {
        IPath filePath = exportedFiles.iterator().next();
        BufferedImage image = ImageIO.read(filePath.toFile());
        if (image == null) {
            // case where we have SVG image
            image = rasterize(filePath.toFile());
        }
        return image;
    }

    private void checkResultsNoAutoScaling(DiagramExportResult exportResult, boolean isSVGExport) throws IOException {
        if (isSVGExport) {
            checkResults(exportResult, 97, 170, 0.0);
        } else {
            checkResults(exportResult, 97, 170, 1.0);
        }
    }

    /**
     * Tests the export of diagrams as image after the migration of the session (but in other session as it can be the
     * case).
     * 
     * @throws Exception
     */
    public void testExportAsImageFromAnotherSession() throws Exception {

        // The session must be close before launching the migration (see
        // RepresentationFilesMigration.run and
        // RepresentationFilesMigrationValidator);
        closeSession(session);

        // Migrates the session
        runRepairProcess(SESSION_MODEL_FILENAME);

        // Reopen the session( because after migration the session is closed)
        URI sessionResourceURI = URI.createPlatformResourceURI("/" + TEMPORARY_PROJECT_NAME + "/" + SESSION_MODEL_FILENAME, true);
        session = SessionManager.INSTANCE.getSession(sessionResourceURI, new NullProgressMonitor());
        TestsUtil.emptyEventsFromUIThread();

        // Get the Root of the given aird file (i.e. a DAnalysis)
        Session newSession;
        IFile airdFile = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path("/" + TEMPORARY_PROJECT_NAME + "/" + SESSION_MODEL_FILENAME));
        assertTrue("The file " + "/" + TEMPORARY_PROJECT_NAME + "/" + SESSION_MODEL_FILENAME + " should exist in the workspace.", airdFile.exists());
        // Load the resource in a new TransactionalEditingDomain

        newSession = SessionManager.INSTANCE.getSession(sessionResourceURI, new NullProgressMonitor());
        if (!newSession.isOpen()) {
            newSession.open(new NullProgressMonitor());
        }

        final IInterpreter interpreter = newSession.getInterpreter();
        InterpreterRegistry.prepareImportsFromSession(interpreter, newSession);

        exportImage(getRepresentation(), ImageFileFormat.PNG, ExportFormat.ScalingPolicy.NO_SCALING);

        // Asserts that the image had been created
        SiriusAssert.assertFileExists("/" + TEMPORARY_PROJECT_NAME + "/" + IMAGE_FILE_NAME + ImageFileFormat.PNG.getName().toLowerCase());
    }

    /**
     * Test that no NPE occurs for InMemory uri with no opaque part and thaht the returned path corresponds to the
     * Platform location.
     * 
     * @throws Exception
     */
    public void testInMemoryResourceExportPathComputation() throws Exception {
        URI uri = URI.createURI(URIQuery.INMEMORY_URI_SCHEME + ":/" + TEMPORARY_PROJECT_NAME + "/" + SESSION_MODEL_FILENAME);
        InMemoryResourceImpl res = new InMemoryResourceImpl(uri);
        ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getResources().add(res);
        DDiagram diag = DiagramFactory.eINSTANCE.createDDiagram();
        res.getContents().add(diag);

        // Start recording
        Session session = EasyMock.createMock(Session.class);
        EasyMock.expect(session.getSessionResource()).andReturn(res);
        replay(session);
        // stop recording

        Object path = null;
        SaveAsImageFileAction saveAsImageFileAction = new SaveAsImageFileAction();
        try {
            DAnalysis analysis = ViewpointFactory.eINSTANCE.createDAnalysis();
            DView dView = ViewpointFactory.eINSTANCE.createDView();
            analysis.getOwnedViews().add(dView);
            res.getContents().add(0, analysis);
            DRepresentationDescriptor createDRepresentationDescriptor = ViewpointFactory.eINSTANCE.createDRepresentationDescriptor();
            createDRepresentationDescriptor.setRepresentation(diag);
            dView.getOwnedRepresentationDescriptors().add(createDRepresentationDescriptor);
            Method method = AbstractExportRepresentationsAction.class.getDeclaredMethod("getExportPath", DRepresentationDescriptor.class, Session.class);
            method.setAccessible(true);
            path = method.invoke(saveAsImageFileAction, createDRepresentationDescriptor, session);
        } catch (Exception e) {
            fail("The test should be checked, the tested method could no more exist.");
        }
        assertEquals("The returned path should correspond to the platform location for the InMemory test aird without real semantic file.", Platform.getLocation(), path);
    }

    public void testExportWithNegativeScaleThrowsException() throws Exception {
        Integer scaleLevel = Integer.valueOf(-1);
        exportWithInvalidScaleLevel(scaleLevel);
    }

    public void testExportWithTooLargeScaleThrowsException() throws Exception {
        Integer scaleLevel = Integer.valueOf(101);
        exportWithInvalidScaleLevel(scaleLevel);
    }

    public void testExportWithNullScaleThrowsException() throws Exception {
        exportWithInvalidScaleLevel(null);
    }

    private void exportWithInvalidScaleLevel(Integer scaleLevel) throws Exception {
        String outputPath = "/" + TEMPORARY_PROJECT_NAME + "/" + IMAGE_FILE_NAME + ImageFileFormat.PNG.getName().toLowerCase();
        String failureMessage = "The export should throw an IllegalArgumentException because the scale level is invalid.";
        try {
            ExportAction exportAction = new ExportAction(session, Collections.singletonList(getRepresentation()), new Path(outputPath), ImageFileFormat.PNG, false, true);
            exportAction.setDiagramScaleLevel(scaleLevel);
            fail(failureMessage);
        } catch (IllegalArgumentException iae) {
            assertEquals("The scale level must be a percentage (between 0 to 100).", iae.getMessage());
        } catch (Exception e) {
            fail(failureMessage);
        }
    }
}
